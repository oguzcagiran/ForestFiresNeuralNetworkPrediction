package forestfire;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author oguz
 */
public class DataSet {

    private Map<Integer, String> dayMap = new HashMap<>();
    private Map<Integer, Double> ffmcMap = new HashMap<>();
    private Map<Integer, Double> dmcMap = new HashMap<>();
    private Map<Integer, Double> dcMap = new HashMap<>();
    private Map<Integer, Double> isiMap = new HashMap<>();
    private Map<Integer, Double> tempMap = new HashMap<>();
    private Map<Integer, Double> rhMap = new HashMap<>();
    private Map<Integer, Double> windMap = new HashMap<>();
    private Map<Integer, Double> rainMap = new HashMap<>();
    private Map<Integer, Double> areaMap = new HashMap<>();

    public DataSet() {
        
        loadDataMapsFromFile();
        
    }

    private void loadDataMapsFromFile() {
        
        File file = new File("forest.txt");
        
        try {

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            String[] buffer;
            int recordNumber = 0;
            
            while ((line = bufferedReader.readLine()) != null) {
                buffer = line.split(",");
                
                String day = buffer[0];
                double ffmc = Double.parseDouble(buffer[1]);
                double dmc = Double.parseDouble(buffer[2]);
                double dc = Double.parseDouble(buffer[3]);
                double isi = Double.parseDouble(buffer[4]);
                double temp = Double.parseDouble(buffer[5]);
                double rh = Double.parseDouble(buffer[6]);
                double wind = Double.parseDouble(buffer[7]);
                double rain = Double.parseDouble(buffer[8]);
                double area = Double.parseDouble(buffer[9]);
                
                dayMap.put(recordNumber, day);
                ffmcMap.put(recordNumber, ffmc);
                dmcMap.put(recordNumber, dmc);
                dcMap.put(recordNumber, dc);
                isiMap.put(recordNumber, isi);
                tempMap.put(recordNumber, temp);
                rhMap.put(recordNumber, rh);
                windMap.put(recordNumber, wind);
                rainMap.put(recordNumber, rain);
                areaMap.put(recordNumber, area);
                
                recordNumber++;
            }
            
            bufferedReader.close();
            fileReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
    
    public String getDay(int recordNumber) {
        return dayMap.get(recordNumber);
    }
    
    public double getFfmc(int recordNumber) {
        return ffmcMap.get(recordNumber);
    }
    
    public double getMinFfmc() {
        return Collections.min(ffmcMap.values());
    }
    
    public double getMaxFfmc() {
        return Collections.max(ffmcMap.values());
    }
    
    public double getDmc(int recordNumber) {
        return dmcMap.get(recordNumber);
    }
    
    public double getMinDmc() {
        return Collections.min(dmcMap.values());
    }
    
    public double getMaxDmc() {
        return Collections.max(dmcMap.values());
    }
    
    public double getDc(int recordNumber) {
        return dcMap.get(recordNumber);
    }
    
    public double getMinDc() {
        return Collections.min(dcMap.values());
    }
    
    public double getMaxDc() {
        return Collections.max(dcMap.values());
    }
    
    public double getIsi(int recordNumber) {
        return isiMap.get(recordNumber);
    }
    
    public double getMinIsi() {
        return Collections.min(isiMap.values());
    }
    
    public double getMaxIsi() {
        return Collections.max(isiMap.values());
    }
    
    public double getTemp(int recordNumber) {
        return tempMap.get(recordNumber);
    }
    
    public double getMinTemp() {
        return Collections.min(tempMap.values());
    }
    
    public double getMaxTemp() {
        return Collections.max(tempMap.values());
    }
    
    public double getRh(int recordNumber) {
        return rhMap.get(recordNumber);
    }
    
    public double getMinRh() {
        return Collections.min(rhMap.values());
    }
    
    public double getMaxRh() {
        return Collections.max(rhMap.values());
    }
    
    public double getWind(int recordNumber) {
        return windMap.get(recordNumber);
    }
    
    public double getMinWind() {
        return Collections.min(windMap.values());
    }
    
    public double getMaxWind() {
        return Collections.max(windMap.values());
    }
    
    public double getRain(int recordNumber) {
        return rainMap.get(recordNumber);
    }
    
    public double getMinRain() {
        return Collections.min(rainMap.values());
    }
    
    public double getMaxRain() {
        return Collections.max(rainMap.values());
    }
    
    public double getArea(int recordNumber) {
        return areaMap.get(recordNumber);
    }
    
    public double getMinArea() {
        return Collections.min(areaMap.values());
    }
    
    public double getMaxArea() {
        return Collections.max(areaMap.values());
    }
    
}