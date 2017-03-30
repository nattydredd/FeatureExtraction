package utilities;

// @author Nate
public class StatisticsCalculator {

    //Variables
    private double mean;
    private double median;
    private double standardDeviation;
    private double min;
    private double max;

    //Constructors   
    public StatisticsCalculator() {
        this.mean = 0;
        this.median = 0;
        this.standardDeviation = 0;
        this.min = 0;
        this.max = 0;
    }

    //Methods
    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMean() {
        return mean;
    }

    public double getMedian() {
        return median;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    //Find minimum value of supplied array
    public double min(double[] data) {

        double minVal = data[0];

        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {

                if (data[i] < minVal) {
                    minVal = data[i];
                }
            }
        }

        setMin(minVal);

        return minVal;
    }

    //Find maximum value of supplied array
    public double max(double[] data) {

        double maxVal = data[0];

        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {

                if (data[i] > maxVal) {
                    maxVal = data[i];
                }
            }
        }

        setMax(maxVal);

        return maxVal;
    }

    //Find mean value of supplied array
    public double mean(double[] data) {
        
        double tmpSum = 0.0;
        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {
                tmpSum += data[i];
            }
        }

        mean = tmpSum / data.length;
        return mean;
    }

    //Find mean value of supplied array
    public double median(double[] data) {
        
        double min = data[0];
        double max = data[0];

        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {
                if (data[i] > max) {
                    max = data[i];
                }
                if (data[i] < min) {
                    min = data[i];
                }
            }
        }

        setMin(min);
        setMax(max);

        median = (min + max) / 2;

        return median;
    }

    //Find standard deviation value of supplied array
    public double standardDeviation(double[] data) {

        mean = mean(data);

        //Sum values of square distance to mean
        double tmpSum = 0.0;
        for (int i = 0; i < data.length; i++) {
            if (!Double.isNaN(data[i])) {
                tmpSum += Math.pow((data[i] - mean), 2);
            }
        }

        //Divide by number of data points and square root
        standardDeviation = Math.sqrt(tmpSum / data.length);

        return standardDeviation;
    }
}//End StatisticsCalculator
