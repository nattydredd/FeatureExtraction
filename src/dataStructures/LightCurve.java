package dataStructures;

// @author Nate
import java.util.ArrayList;
import java.util.Arrays;

public class LightCurve {

    //Variables
    private String starKepId;
    private int[] dataIndex;
    private double[] time;
    private double[] luminosity;
    private int length;

    //Constructors
    public LightCurve() {
    }

    public LightCurve(String starKepId, int[] dataIndex, double[] time, double[] luminosity, int length) {
        this.starKepId = starKepId;
        this.dataIndex = dataIndex;
        this.time = time;
        this.luminosity = luminosity;
        this.length = length;
    }

    //Methods
    public String getStarKepId() {
        return starKepId;
    }

    public void setStarKepId(String starKepId) {
        this.starKepId = starKepId;
    }

    public int[] getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(int[] dataIndex) {
        this.dataIndex = dataIndex;
    }

    public double[] getTime() {
        return time;
    }

    public void setTime(double[] time) {
        this.time = time;
    }

    public double[] getLuminosity() {
        return luminosity;
    }

    public void setLuminosity(double[] luminosity) {
        this.luminosity = luminosity;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    //Separates the lightcurve on large gaps
    //Returns an array of smaller lightcurves
    public ArrayList separateLightCurve() {

        ArrayList<LightCurve> segments = new ArrayList<LightCurve>();

        //If time value jumps more than 0.5 create segment        
        ArrayList<Integer> indexList = new <Integer>ArrayList();
        for (int i = 0; i < time.length - 1; i++) {
            if ((time[i] + 0.5) < time[i + 1]) {
                indexList.add(i + 1);
            }
        }
        //Add the final peice
        indexList.add(time.length);

        //Create index, time and luminosity arrays for each segment
        LightCurve segment;
        int startIndex = 0;
        for (int i = 0; i < indexList.size(); i++) {
            segment = new LightCurve();

            //Star ID
            segment.setStarKepId(starKepId);
            //Index
            segment.setDataIndex(Arrays.copyOfRange(dataIndex, startIndex, indexList.get(i)));
            //Time
            segment.setTime(Arrays.copyOfRange(time, startIndex, indexList.get(i)));
            //Luminosity
            segment.setLuminosity(Arrays.copyOfRange(luminosity, startIndex, indexList.get(i)));
            //Length
            segment.setLength(dataIndex.length);

            //Add to output list
            segments.add(segment);

            //Update index for beginning of next segment
            startIndex = indexList.get(i);
        }
        return segments;
    }

    @Override
    public String toString() {

        String returnVal = "Light Curve for Star: " + starKepId + "\n";
        returnVal += "Number of instances " + length + "\n";

        for (int i = 0; i < getLength(); i++) {
            returnVal += String.format("%d, %.6f, %.6f", dataIndex[i], time[i], luminosity[i]);
            returnVal += "\n";
        }

        return returnVal;
    }

}//End LightCurve
