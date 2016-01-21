package forestfire;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oguz
 */
public class NormalDataSet {
    
    private DataSet dataSet = new DataSet();
    
    private int sunday[] = {1,0,0,0,0,0,0};
    private int monday[] = {0,1,0,0,0,0,0};
    private int tuesday[] = {0,0,1,0,0,0,0};
    private int wednesday[] = {0,0,0,1,0,0,0};
    private int thursday[] = {0,0,0,0,1,0,0};
    private int friday[] = {0,0,0,0,0,1,0};
    private int saturday[] = {0,0,0,0,0,0,1};
    
    private Map<String, int[]> dayMap = new HashMap<>(); {
        dayMap.put("sun", sunday);
        dayMap.put("mon", monday);
        dayMap.put("tue", tuesday);
        dayMap.put("wed", wednesday);
        dayMap.put("thu", thursday);
        dayMap.put("fri", friday);
        dayMap.put("sat", saturday);
    }
    
    private Map<Integer, int[]> encodedDayMap = new HashMap<>();
    private Map<Integer, Double> normalFfmcMap = new HashMap<>();
    private Map<Integer, Double> normalDmcMap = new HashMap<>();
    private Map<Integer, Double> normalDcMap = new HashMap<>();
    private Map<Integer, Double> normalIsiMap = new HashMap<>();
    private Map<Integer, Double> normalTempMap = new HashMap<>();
    private Map<Integer, Double> normalRhMap = new HashMap<>();
    private Map<Integer, Double> normalWindMap = new HashMap<>();
    private Map<Integer, Double> normalRainMap = new HashMap<>();
    private Map<Integer, Double> normalAreaMap = new HashMap<>();
    
    public NormalDataSet () {
        
        for (int i = 0; i < 440; i++) {
           
            String day = dataSet.getDay(i);
            int[] encodedDay = dayMap.get(day);
            
            encodedDayMap.put(i, encodedDay);
            normalFfmcMap.put(i, normalize(dataSet.getFfmc(i), dataSet.getMinFfmc(), dataSet.getMaxFfmc()));
            normalDmcMap.put(i, normalize(dataSet.getDmc(i), dataSet.getMinDmc(), dataSet.getMaxDmc()));
            normalDcMap.put(i, normalize(dataSet.getDc(i), dataSet.getMinDc(), dataSet.getMaxDc()));
            normalIsiMap.put(i, normalize(dataSet.getIsi(i), dataSet.getMinIsi(), dataSet.getMaxIsi()));
            normalTempMap.put(i, normalize(dataSet.getTemp(i), dataSet.getMinTemp(), dataSet.getMaxTemp()));
            normalRhMap.put(i, normalize(dataSet.getRh(i), dataSet.getMinRh(), dataSet.getMaxRh()));
            normalWindMap.put(i, normalize(dataSet.getWind(i), dataSet.getMinWind(), dataSet.getMaxWind()));
            normalRainMap.put(i, normalize(dataSet.getRain(i), dataSet.getMinRain(), dataSet.getMaxRain()));
            normalAreaMap.put(i, normalize(dataSet.getArea(i), dataSet.getMinArea(), dataSet.getMaxArea()));
            
        }
        
    }
    
    private double normalize(double value, double min, double max) {
        return (value-min)/(max-min);
    }
    
    public double denormalize(double normalData) {
        return normalData *(dataSet.getMaxArea()-dataSet.getMinArea())+dataSet.getMinArea();
    }
    
    public int[] getEncodedDay(int recordNumber) {
        return encodedDayMap.get(recordNumber);
    }
    
    public double getNormalFfmc(int recordNumber) {
        return normalFfmcMap.get(recordNumber);
    }
    
    public double getNormalDmc(int recordNumber) {
        return normalDmcMap.get(recordNumber);
    }
    
    public double getNormalDc(int recordNumber) {
        return normalDcMap.get(recordNumber);
    }
    
    public double getNormalIsi(int recordNumber) {
        return normalIsiMap.get(recordNumber);
    }
    
    public double getNormalTemp(int recordNumber) {
        return normalTempMap.get(recordNumber);
    }
    
    public double getNormalRh(int recordNumber) {
        return normalRhMap.get(recordNumber);
    }
    
    public double getNormalWind(int recordNumber) {
        return normalWindMap.get(recordNumber);
    }
    
    public double getNormalRain(int recordNumber) {
        return normalRainMap.get(recordNumber);
    }
    
    public double getNormalArea(int recordNumber) {
        return normalAreaMap.get(recordNumber);
    }
}