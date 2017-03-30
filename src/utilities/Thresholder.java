package utilities;

// @author Nate
import dataStructures.LightCurve;
import dataStructures.Star;
import java.util.ArrayList;

public class Thresholder {

    //Variables
    private StatisticsCalculator stats = new StatisticsCalculator();

    private ArrayList lowThreshold;
    private ArrayList medThreshold;
    private ArrayList highThreshold;
    private ArrayList nonPeaks;

    //Constructors   
    public Thresholder() {

    }

    //Methods
    //Applies normalisation and thresholds to stars light curve and adds to star
    public Star run(Star star) {
        
        //Create new threshold list for adding to the star
        this.lowThreshold = new ArrayList();
        this.medThreshold = new ArrayList();
        this.highThreshold = new ArrayList();
        this.nonPeaks = new ArrayList();
        
        //Sparate lightcurve at gaps
        ArrayList<LightCurve> sections = star.getLightCurve().separateLightCurve();

        //Apply normalisation algorithm to each section and record thresholds
        LightCurve currentLightCurve;
        for (int j = 0; j < sections.size(); j++) {
            currentLightCurve = sections.get(j);

            //Current light curve section data
            double[] luminosity = currentLightCurve.getLuminosity();

            //Normalise luminosity
            double[] normalisedLum = normalise(luminosity);
            //Calculate standard deviation of normalised luminosity
            double stDeviation = stats.standardDeviation(normalisedLum);

            //Threshold
            threshold(normalisedLum, stDeviation);
        }//End sections

        //Set stars threshold values
        star.setLowThreshold(lowThreshold);
        star.setMedThreshold(medThreshold);
        star.setHighThreshold(highThreshold);
        star.setNonPeaks(nonPeaks);

        return star;
    }

    //Threshold normalised luminosity at 1, 1.5 and 2 times standard deviation
    public void threshold(double[] normalisedLum, double stDeviation) {

        int[] threshLow = new int[normalisedLum.length];
        int[] threshMed = new int[normalisedLum.length];
        int[] threshHigh = new int[normalisedLum.length];
        int[] nonPeak = new int[normalisedLum.length];

        for (int i = 0; i < normalisedLum.length; i++) {
            if (normalisedLum[i] > (stDeviation * 1)) {
                threshLow[i] = 1;

            }
            if (normalisedLum[i] > (stDeviation * 1.5)) {
                threshMed[i] = 1;

            }
            if (normalisedLum[i] > (stDeviation * 2)) {
                threshHigh[i] = 1;

            }
            if (threshLow[i] == 0 && threshMed[i] == 0 && threshHigh[i] == 0) {
                nonPeak[i] = 1;

            }
        }

        //Added thresholded values to the global list
        for (int i = 0; i < normalisedLum.length; i++) {
            this.lowThreshold.add(threshLow[i]);
            this.medThreshold.add(threshMed[i]);
            this.highThreshold.add(threshHigh[i]);
            this.nonPeaks.add(nonPeak[i]);
        }
    }

    //Normalise luminosity to percentage change from a local baseline
    public double[] normalise(double[] luminosity) {

        double[] normalisedLum = new double[luminosity.length];
        int window = 8;
        for (int i = window; i < (luminosity.length - window); i++) {

            //Calc baseline
            double baseLine = luminosity[i];

            for (int k = 1; k <= window; k++) {
                baseLine += luminosity[i - k];
                baseLine += luminosity[i + k];
            }

            baseLine = baseLine / ((window * 2) + 1);

            //Calculate percentage change
            normalisedLum[i] = ((baseLine - luminosity[i]) / luminosity[i]) * 100;
        }

        return normalisedLum;
    }
}//End Thresholder
