package featureextraction;

// @author Nate
import dataStructures.Star;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import utilities.FeatureExtractor;
import utilities.LcDataLoader;
import utilities.Thresholder;

public class FeatureExtraction {

    //Resources directory location
    private static String resourceDirectory = "./resources";//must replace '.' with 'src' if running in IDE

    //Utillity variables
    private static LcDataLoader lcLoader;
    private static Thresholder thresholder = new Thresholder();
    private static FeatureExtractor featureExtractor = new FeatureExtractor();

    //Input lists
    private static ArrayList<String> hostList;
    private static ArrayList<String> nonHostList;

    //Results
    private static ArrayList<String> hostResults;
    private static ArrayList<String> nonHostResults;

    public static void main(String[] args) throws Exception {

        if (args.length != 3 || args == null) {
            System.err.println("Invalid argument length!");
            throw new Exception();
        }
        
        //List of stars to process and name of set
        String hostSet = args[0];//"HostTEST";
        String nonHostSet = args[1];//"NonHostTEST";
        String setName = args[2];//"TEST";

        //read in host/non-host lists
        hostList = readList(hostSet);
        nonHostList = readList(nonHostSet);

        //Initialise results arrays
        hostResults = new ArrayList<>();
        nonHostResults = new ArrayList<>();

        //Process each input star light curve
        for (String starId : hostList) {
            hostResults.add(processStar(starId, hostSet));
        }

        for (String starId : nonHostList) {
            nonHostResults.add(processStar(starId, nonHostSet));
        }

        //Write to output .erff file
        writeToFile(setName, 29, hostResults, nonHostResults);
    }//End main

    //Load star and process light curve into string of feature attributes
    private static String processStar(String starId, String setFolder) {

        //Creat new star and light curve
        lcLoader = new LcDataLoader(resourceDirectory + "/" + setFolder + "/");
        Star star = new Star(starId);
        star.setLightCurve(lcLoader.getLcData(starId));

        //Threshold stars data
        star = thresholder.run(star);
        
        //Extract features
        featureExtractor.extractFeatures(star);

        //return features as string
        return featureExtractor.getResultsString();
    }

    //Writes a new .arff file with specified name and number of attributes
    private static void writeToFile(String setName, int numAttributes, ArrayList<String> hostResults, ArrayList<String> nonHostResults) {

        try {
            PrintWriter out = new PrintWriter(new FileWriter(resourceDirectory + "/output/" + setName + "Set.arff", true));

            //Print .arff file header
            out.println("@relation " + setName + "Set");
            out.println("");
            out.println("@attribute starID numeric");
            for (int i = 1; i <= numAttributes; i++) {
                out.println("@attribute att#" + i + " numeric");
            }
            out.println("@attribute class {0,1}");
            out.println("");

            //Print host/non-host instances
            out.println("@data");
            for (int i = 0; i < hostList.size(); i++) {
                out.println(hostList.get(i) + "," + hostResults.get(i) + "1");
            }

            for (int i = 0; i < nonHostList.size(); i++) {
                out.println(nonHostList.get(i) + "," + nonHostResults.get(i) + "0");
            }

            out.flush();

        } catch (IOException e) {
            System.out.println("Error writing to file " + setName + "Set.arff");
        }
    }

    //Reads input list file and returns list
    private static ArrayList readList(String list) {

        String nextLine = null;
        ArrayList<String> returnList = new ArrayList<>();
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(resourceDirectory + "/" + list + ".txt"));

            while ((nextLine = bufferedReader.readLine()) != null) {
                returnList.add(nextLine);
            }
        } catch (IOException e) {
            System.out.println("Error reading file " + list);
        }

        return returnList;
    }
}//End FeatureExtraction
