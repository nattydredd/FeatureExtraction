package utilities;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nate
 */
public class StatisticsCalculatorTest {

    public StatisticsCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of min method, of class StatisticsCalculator.
     */
    @Test
    public void testMin() {
        System.out.println("min");
        double[] data = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        StatisticsCalculator instance = new StatisticsCalculator();
        double expResult = 0.0;
        double result = instance.min(data);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of max method, of class StatisticsCalculator.
     */
    @Test
    public void testMax() {
        System.out.println("max");
        double[] data = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        StatisticsCalculator instance = new StatisticsCalculator();
        double expResult = 5.0;
        double result = instance.max(data);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of mean method, of class StatisticsCalculator.
     */
    @Test
    public void testMean() {
        System.out.println("mean");
        double[] data = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        StatisticsCalculator instance = new StatisticsCalculator();
        double expResult = 2.5;
        double result = instance.mean(data);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of median method, of class StatisticsCalculator.
     */
    @Test
    public void testMedian() {
        System.out.println("median");
        double[] data = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        StatisticsCalculator instance = new StatisticsCalculator();
        double expResult = 3.0;
        double result = instance.median(data);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of standardDeviation method, of class StatisticsCalculator.
     */
    @Test
    public void testStandardDeviation() {
        System.out.println("standardDeviation");
        double[] data = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0};
        StatisticsCalculator instance = new StatisticsCalculator();
        double expResult = 1.708;
        double result = instance.standardDeviation(data);

        DecimalFormat format = new DecimalFormat("#.###");
        format.setRoundingMode(RoundingMode.CEILING);
        result = Double.valueOf(format.format(result));
        assertEquals(expResult, result, 0.0);
    }

}
