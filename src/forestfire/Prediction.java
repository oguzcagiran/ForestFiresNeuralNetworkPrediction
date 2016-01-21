/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forestfire;

/**
 *
 * @author oguz
 */
public class Prediction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MultiLayerPerceptron mlp = new MultiLayerPerceptron(57, 0.345354, 0.583674, 550);

        mlp.start();

    }
}