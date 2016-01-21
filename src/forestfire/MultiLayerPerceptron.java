package forestfire;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author oguz
 */
public class MultiLayerPerceptron {

    private static final int INPUT_NODE_NUMBER = 15;

    private final NormalDataSet normalDataSet = new NormalDataSet();
    private final DataSet dataSet = new DataSet();

    private String[] testSetRange = new String[5];

    private int hiddenLayerNodeNumber;
    private double learningRate;
    private double momentum;
    private int epochNumber;

    public MultiLayerPerceptron(int hiddenNode, double learningRate, double momentum, int epochNumber) {

        this.hiddenLayerNodeNumber = hiddenNode;
        this.learningRate = learningRate;
        this.momentum = momentum;
        this.epochNumber = epochNumber;

        testSetRange[4] = "352,440";
        testSetRange[3] = "264,351";
        testSetRange[2] = "176,263";
        testSetRange[1] = "88,175";
        testSetRange[0] = "0,87";

    }

    public void start() {

        double[] inputValues = new double[INPUT_NODE_NUMBER];
        double[][] inputLayerWeights = new double[INPUT_NODE_NUMBER][hiddenLayerNodeNumber];
        double[] hiddenLayerNetInputs = new double[hiddenLayerNodeNumber];
        double[] hiddenLayerNetOutputs = new double[hiddenLayerNodeNumber];
        double[] outputLayerWeights = new double[hiddenLayerNodeNumber];
        double[] hiddenLayerErrorNode = new double[hiddenLayerNodeNumber];

        double outputLayerNetInput = 0;
        double outputLayerNetOutput;
        double errorAtOutputNode;
        double allRootMeanSquaredError = 0;

        Arrays.fill(inputValues, 0);
        fillInputHiddenWeightArray(inputLayerWeights);
        Arrays.fill(hiddenLayerNetInputs, 0);
        Arrays.fill(hiddenLayerNetOutputs, 0);
        Arrays.fill(outputLayerWeights, -0.8);
        Arrays.fill(hiddenLayerErrorNode, 0);

        for (int k = 0; k < 5; k++) {

            String range = testSetRange[k];
            String[] ranges = range.split(",");

            int lowerBoundary = Integer.parseInt(ranges[0]);
            int upperBoundary = Integer.parseInt(ranges[1]);

            for (int i = 0; i < epochNumber; i++) {

                //Train start here
                for (int dataNumber = 0; dataNumber < 440; dataNumber++) {

                    //If data is test data do nothing and continue
                    if (dataNumber >= lowerBoundary && dataNumber <= upperBoundary) {
                        continue;
                    }

                    int[] day = normalDataSet.getEncodedDay(dataNumber);

                    inputValues[0] = day[0];
                    inputValues[1] = day[1];
                    inputValues[2] = day[2];
                    inputValues[3] = day[3];
                    inputValues[4] = day[4];
                    inputValues[5] = day[5];
                    inputValues[6] = day[6];
                    inputValues[7] = normalDataSet.getNormalFfmc(dataNumber);
                    inputValues[8] = normalDataSet.getNormalDmc(dataNumber);
                    inputValues[9] = normalDataSet.getNormalDc(dataNumber);
                    inputValues[10] = normalDataSet.getNormalIsi(dataNumber);
                    inputValues[11] = normalDataSet.getNormalTemp(dataNumber);
                    inputValues[12] = normalDataSet.getNormalRh(dataNumber);
                    inputValues[13] = normalDataSet.getNormalWind(dataNumber);
                    inputValues[14] = normalDataSet.getNormalRain(dataNumber);

                    //Calculates all input value of hidden layers
                    for (int inputNode = 0; inputNode < INPUT_NODE_NUMBER; inputNode++) {

                        for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                            hiddenLayerNetInputs[hiddenNode]
                                    += (inputValues[inputNode] * inputLayerWeights[inputNode][hiddenNode]);

                        }
                    }

                    //Calculates hidden nodes output values
                    for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                        hiddenLayerNetOutputs[hiddenNode] = calculateSigmoidOutput(hiddenLayerNetInputs[hiddenNode]);

                    }

                    //Calculates sum of input values to output node
                    for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                        outputLayerNetInput += hiddenLayerNetOutputs[hiddenNode] * outputLayerWeights[hiddenNode];

                    }

                    outputLayerNetOutput = calculateSigmoidOutput(outputLayerNetInput);

                    errorAtOutputNode = outputLayerNetOutput * (1 - outputLayerNetOutput)
                            * (normalDataSet.getNormalArea(dataNumber) - outputLayerNetOutput);

                    //Calculates Hidden node error
                    for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                        double output = hiddenLayerNetOutputs[hiddenNode];

                        hiddenLayerErrorNode[hiddenNode]
                                = outputLayerWeights[hiddenNode] * output * (1 - output) * errorAtOutputNode;

                    }

                    //Weight update between hidden and output nodes
                    for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                        outputLayerWeights[hiddenNode]
                                += (learningRate * momentum * errorAtOutputNode * hiddenLayerNetOutputs[hiddenNode]);

                    }

                    //Weight update between input and hindden nodes
                    for (int inputNode = 0; inputNode < INPUT_NODE_NUMBER; inputNode++) {

                        for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                            inputLayerWeights[inputNode][hiddenNode]
                                    += (learningRate * momentum * hiddenLayerErrorNode[hiddenNode] * inputValues[inputNode]);
                        }
                    }

                    //After one iteration reset variables except weigths
                    //Because train set weights according to errors with backpropagation
                    //every iteration 
                    Arrays.fill(hiddenLayerNetInputs, 0);
                    Arrays.fill(hiddenLayerNetOutputs, 0);
                    outputLayerNetInput = 0;
                    outputLayerNetOutput = 0;
                    errorAtOutputNode = 0;

                }//Train end
            }

            //Test starts here
            List<Double> rootMeanList = new ArrayList<>();

            for (int dataNumber = lowerBoundary; dataNumber < upperBoundary; dataNumber++) {

                int[] day = normalDataSet.getEncodedDay(dataNumber);

                inputValues[0] = day[0];
                inputValues[1] = day[1];
                inputValues[2] = day[2];
                inputValues[3] = day[3];
                inputValues[4] = day[4];
                inputValues[5] = day[5];
                inputValues[6] = day[6];
                inputValues[7] = normalDataSet.getNormalFfmc(dataNumber);
                inputValues[8] = normalDataSet.getNormalDmc(dataNumber);
                inputValues[9] = normalDataSet.getNormalDc(dataNumber);
                inputValues[10] = normalDataSet.getNormalIsi(dataNumber);
                inputValues[11] = normalDataSet.getNormalTemp(dataNumber);
                inputValues[12] = normalDataSet.getNormalRh(dataNumber);
                inputValues[13] = normalDataSet.getNormalWind(dataNumber);
                inputValues[14] = normalDataSet.getNormalRain(dataNumber);

                for (int inputNode = 0; inputNode < INPUT_NODE_NUMBER; inputNode++) {

                    for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                        hiddenLayerNetInputs[hiddenNode]
                                += (inputValues[inputNode] * inputLayerWeights[inputNode][hiddenNode]);

                    }
                }

                for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                    hiddenLayerNetOutputs[hiddenNode] = calculateSigmoidOutput(hiddenLayerNetInputs[hiddenNode]);

                }

                for (int hiddenNode = 0; hiddenNode < hiddenLayerNodeNumber; hiddenNode++) {

                    outputLayerNetInput += hiddenLayerNetOutputs[hiddenNode] * outputLayerWeights[hiddenNode];

                }

                outputLayerNetOutput = calculateSigmoidOutput(outputLayerNetInput);

                System.out.print("actual = " + dataSet.getArea(dataNumber));
                System.out.println("     predicted = " + normalDataSet.denormalize(outputLayerNetOutput));

                rootMeanList.add(Math.pow((dataSet.getArea(dataNumber) - normalDataSet.denormalize(outputLayerNetOutput)), 2));

                Arrays.fill(hiddenLayerNetInputs, 0);
                Arrays.fill(hiddenLayerNetOutputs, 0);
                outputLayerNetInput = 0;
                outputLayerNetOutput = 0;
                errorAtOutputNode = 0;
            }//Train end

            double rootMeanRes = 0;

            //This calculates root mean squared error of current fold
            for (int i = 0; i < rootMeanList.size(); i++) {

                rootMeanRes = rootMeanRes + rootMeanList.get(i);

            }

            System.out.println("For k = " + (k + 1) + " fold RootMeanSquaredError = "
                    + Math.sqrt(rootMeanRes / rootMeanList.size()));

            System.out.println("==============================================");

            allRootMeanSquaredError += Math.sqrt(rootMeanRes / rootMeanList.size());

            //After one fold train test reset all weights, inputs and outputs for
            //test and train for another fold
            Arrays.fill(inputValues, 0);
            fillInputHiddenWeightArray(inputLayerWeights);
            Arrays.fill(hiddenLayerNetInputs, 0);
            Arrays.fill(hiddenLayerNetOutputs, 0);
            Arrays.fill(outputLayerWeights, -0.8);
            Arrays.fill(hiddenLayerErrorNode, 0);
        }

        System.out.println("RootMeanSquaredError = " + allRootMeanSquaredError / 5);

    }

    private double calculateSigmoidOutput(double input) {

        return (1 / (1 + Math.pow(Math.E, (-1 * input))));

    }

    private void fillInputHiddenWeightArray(double[][] array) {

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = -0.5;
            }
        }

    }

}