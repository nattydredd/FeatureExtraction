package utilities;

// @author Nate
import dataStructures.Star;
import java.util.ArrayList;
import utilities.StatisticsCalculator;

public class FeatureExtractor {

    //Variables
    private StatisticsCalculator stats = new StatisticsCalculator();

    //Features variables
    private double globalMean;
    private double globalMedian;
    private double globalStandardDeviation;

    private double lowPeakFraction;
    private double medPeakFraction;
    private double highPeakFraction;
    private double nonPeakFraction;

    private double[] lowPeakStats;
    private double[] medPeakStats;
    private double[] highPeakStats;
    private double[] nonPeakStats;

    //Constructors
    public FeatureExtractor() {
        
    }

    //Methods
    //Extract statistical features
    public void extractFeatures(Star star) {

        //Retreive data from star
        double[] luminosity = star.getLightCurve().getLuminosity();
        ArrayList lowThreshold = star.getLowThreshold();
        ArrayList medThreshold = star.getMedThreshold();
        ArrayList highThreshold = star.getHighThreshold();
        ArrayList nonPeak = star.getNonPeaks();

        //Calculate global stats
        this.globalMean = stats.mean(luminosity);
        this.globalMedian = stats.median(luminosity);
        this.globalStandardDeviation = stats.standardDeviation(luminosity);

        //Calculate fraction of peaks and nonPeaks
        this.lowPeakFraction = calcFraction(lowThreshold);
        this.medPeakFraction = calcFraction(medThreshold);
        this.highPeakFraction = calcFraction(highThreshold);
        this.nonPeakFraction = calcFraction(nonPeak);

        //Calculate number of distinct peaks, width, magnitude and value statistics
        this.lowPeakStats = calcDistinctPeakStats(lowThreshold, luminosity);
        this.medPeakStats = calcDistinctPeakStats(medThreshold, luminosity);
        this.highPeakStats = calcDistinctPeakStats(highThreshold, luminosity);

        //Calculate nonPeak statistics
        this.nonPeakStats = calcNonPeakWidthStats(nonPeak);
    }

    //Calculates the fraction of points that belong to each threshold
    public double calcFraction(ArrayList data) {

        int count = 0;

        for (int i = 0; i < data.size(); i++) {
            if ((int) data.get(i) == 1) {
                count++;
            }
        }

        return ((double) count / data.size());
    }

    //Calculate peak width, max magnitude, actuall values
    public double[] calcDistinctPeakStats(ArrayList data, double[] luminosity) {

        double[] result = new double[7];

        int count = 0;
        ArrayList<Double> peakWidth = new ArrayList();
        ArrayList<Double> peakMaxMag = new ArrayList();
        ArrayList<Double> peakValues = new ArrayList();

        int index = 0;
        double width = 0;
        double magnitude = 0;
        for (int i = 0; i < data.size() - 1; i++) {

            //If this is the start of a new peak
            if ((int) data.get(i) == 1 && (int) data.get(i + 1) == 1 && (int) data.get(i - 1) == 0) {

                //Increase count
                count++;

                //For the duration of the peak
                while ((int) data.get(i + index) == 1 && (i + index) < data.size() - 1) {

                    //Get the larget magnitude for luminosity of this peak
                    if (luminosity[i + index] > magnitude) {
                        magnitude = luminosity[i + index];
                    }

                    //Add all of the peak point values to the list
                    peakValues.add(luminosity[i + index]);

                    //Increase the width and index for this peak
                    width++;
                    index++;
                }

                //If the width is not 0, then record it
                if (width > 0) {
                    peakWidth.add(width);
                }

                //If the magnitude is not 0, then record it
                if (magnitude > 0) {
                    peakMaxMag.add(magnitude);
                }

                //Reset variables
                index = 0;
                width = 0;
                magnitude = 0;
            }
        }

        //Set results
        result[0] = count;

        result[1] = stats.mean(toDoubleArray(peakWidth));
        result[2] = stats.standardDeviation(toDoubleArray(peakWidth));

        result[3] = stats.mean(toDoubleArray(peakMaxMag));
        result[4] = stats.standardDeviation(toDoubleArray(peakMaxMag));

        result[5] = stats.mean(toDoubleArray(peakValues));
        result[6] = stats.standardDeviation(toDoubleArray(peakValues));

        return result;
    }

    //Calculate nonpeak width stats
    public double[] calcNonPeakWidthStats(ArrayList data) {

        double[] result = new double[2];

        //Get nonPeak widths
        ArrayList<Double> nonPeakWidths = new ArrayList();

        double width = 0;
        int index = 0;
        for (int i = 1; i <= data.size() - 1; i++) {

            //If this is part of an inter-peak section
            if ((int) data.get(i) == 1 && (int) data.get(i - 1) == 0) {

                //For the duration of the section
                while ((int) data.get(i + index) == 1 && (i + index) < data.size() - 1) {

                    //Increase width value
                    width++;
                    index++;
                }

                //If the section is not part of 'noise' it should be longer than 1
                if (width > 1) {
                    nonPeakWidths.add(width);
                }

                index = 0;
                width = 0;
            }
        }

        //Set Results
        result[0] = stats.mean(toDoubleArray(nonPeakWidths));
        result[1] = stats.standardDeviation(toDoubleArray(nonPeakWidths));

        return result;
    }

    //returns compleate feature set as a string
    public String getResultsString() {

        String returnVal = String.format(
                "%.8f,%.8f,%.8f,%.8f,"
                + "%.8f,%.8f,",
                lowPeakFraction, medPeakFraction, highPeakFraction, nonPeakFraction,
                globalMedian, globalStandardDeviation);
        returnVal += arrayToString(lowPeakStats);
        returnVal += arrayToString(medPeakStats);
        returnVal += arrayToString(highPeakStats);
        returnVal += String.format("%.8f,%.8f,", nonPeakStats[0], nonPeakStats[1]);
        return returnVal;
    }

    //Converts an array of features to a comma separated string
    public String arrayToString(double[] array) {
        String returnVal = "";

        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                returnVal += String.format("%.1f,", array[i]);
            } else {
                returnVal += String.format("%.8f,", array[i]);
            }

        }

        return returnVal;
    }

    public double[] toDoubleArray(ArrayList list) {
        double[] returnVal = new double[list.size()];

        for (int i = 0; i < list.size(); i++) {
            returnVal[i] = (double) list.get(i);
        }
        return returnVal;
    }
}//End FeatureExtractor
