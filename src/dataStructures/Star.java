package dataStructures;

// @author Nate
import java.util.ArrayList;

public class Star {

    //Variables
    private String kepId;
    private LightCurve lightCurve;

    private ArrayList lowThreshold;
    private ArrayList medThreshold;
    private ArrayList highThreshold;
    private ArrayList nonPeaks;

    //Constructors   
    public Star(String kepId) {
        this.kepId = kepId;
        this.lowThreshold = new ArrayList();
        this.medThreshold = new ArrayList();
        this.highThreshold = new ArrayList();
        this.nonPeaks = new ArrayList();
    }

    //Methods
    public String getKepId() {
        return kepId;
    }

    public void setKepId(String kepId) {
        this.kepId = kepId;
    }

    public LightCurve getLightCurve() {
        return lightCurve;
    }

    public void setLightCurve(LightCurve lightCurve) {
        this.lightCurve = lightCurve;
    }

    public ArrayList getLowThreshold() {
        return lowThreshold;
    }

    public void setLowThreshold(ArrayList lowThreshold) {
        this.lowThreshold = lowThreshold;
    }

    public ArrayList getMedThreshold() {
        return medThreshold;
    }

    public void setMedThreshold(ArrayList medThreshold) {
        this.medThreshold = medThreshold;
    }

    public ArrayList getHighThreshold() {
        return highThreshold;
    }

    public void setHighThreshold(ArrayList highThreshold) {
        this.highThreshold = highThreshold;
    }

    public ArrayList getNonPeaks() {
        return nonPeaks;
    }

    public void setNonPeaks(ArrayList nonPeaks) {
        this.nonPeaks = nonPeaks;
    }

    @Override
    public String toString() {
        String returnVal = "Star ID: " + kepId;

        returnVal += "\n";
        returnVal += lightCurve.toString();

        return returnVal;
    }

}//End Star
