package utilities;

// @author Nate
import dataStructures.LightCurve;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LcDataLoader {

    //Variables
    private String filePath;
    private ArrayList<String> rawData;

    //Constructors   
    public LcDataLoader(String filePath) {
        this.filePath = filePath;
        this.rawData = new <String>ArrayList();
    }

    //Methods
    //Loads the light curve data and converts to LightCurve object
    public LightCurve getLcData(String starKepId) {
        
        //Load raw file data
        loadLcData(filePath + starKepId + ".txt");
        
        //Creat new LightCurve object
        LightCurve lightCurve = new LightCurve();
        
        int numInstances = rawData.size();
        
        int[] dataIndex = new int[numInstances];
        double[] time = new double[numInstances];
        double[] lum = new double[numInstances];
        
        //Parse loaded string
        for (int i = 0; i < numInstances; i++) {
            dataIndex[i] = i + 1;
            time[i] = Double.parseDouble(rawData.get(i).split(",")[0]);
            lum[i] = Double.parseDouble(rawData.get(i).split(",")[1]);
        }
        
        lightCurve.setStarKepId(starKepId);
        lightCurve.setDataIndex(dataIndex);
        lightCurve.setTime(time);
        lightCurve.setLuminosity(lum);
        lightCurve.setLength(numInstances);
        
        return lightCurve;
    }
    
    //Reads the raw light curve data from the supplied file
    private void loadLcData(String filePath) {
        
        String nextLine = null;
        
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            //Skip first line (time,lum labels)
            bufferedReader.readLine();
            while ((nextLine = bufferedReader.readLine()) != null) {
                rawData.add(nextLine);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}//End LcDataLoader
