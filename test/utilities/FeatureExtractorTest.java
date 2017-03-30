package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Nate
 */
public class FeatureExtractorTest {

    public FeatureExtractorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Rule
    public ExpectedException exceptionThrown = ExpectedException.none();

    /**
     * Test of calcFraction method, of class FeatureExtractor.
     */
    @Test
    public void testCalcFraction1() {
        System.out.println("calcFraction");
        ArrayList data = new ArrayList(Arrays.asList(1, 0, 1, 0, 1));
        FeatureExtractor instance = new FeatureExtractor();
        
        double expResult = 0.6;
        double result = instance.calcFraction(data);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalcFraction2() {
        System.out.println("calcFraction");
        ArrayList data = new ArrayList(Arrays.asList(1, 1, 1, 1, 1));
        FeatureExtractor instance = new FeatureExtractor();
        
        double expResult = 1;
        double result = instance.calcFraction(data);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalcFraction3() {
        System.out.println("calcFraction");
        ArrayList data = new ArrayList(Arrays.asList(0, 0, 0, 0, 0));
        FeatureExtractor instance = new FeatureExtractor();
        
        double expResult = 0;
        double result = instance.calcFraction(data);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalcFraction4() {
        System.out.println("calcFraction");
        ArrayList data = new ArrayList(Arrays.asList(-1, 0, 3, 0, 4));
        FeatureExtractor instance = new FeatureExtractor();
        
        double expResult = 0;
        double result = instance.calcFraction(data);
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalcFraction5() {
        System.out.println("calcFraction");
        ArrayList data = new ArrayList(Arrays.asList("1", "2"));
        FeatureExtractor instance = new FeatureExtractor();
        
        exceptionThrown.expect(ClassCastException.class);
        instance.calcFraction(data);
    }

    /**
     * Test of calcDistinctPeakStats method, of class FeatureExtractor.
     */
    @Test
    public void testCalcDistinctPeakStats1() {
        System.out.println("calcDistinctPeakStats");
        ArrayList data = new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 0, 0));
        double[] luminosity = {0.8, 0.7, 0.75, 0.77, 0.68, 0.66, 0.78, 0.9, 0.86, 0.88};
        FeatureExtractor instance = new FeatureExtractor();
        
        double expResult = 7;
        double result = instance.calcDistinctPeakStats(data, luminosity).length;
        assertEquals(expResult, result, 0.0);
    }

    @Test
    public void testCalcDistinctPeakStats2() {
        System.out.println("calcDistinctPeakStats");
        ArrayList data = new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 0, 0));
        double[] luminosity = {0.8, 0.7, 0.75, 0.77, 0.68, 0.66, 0.78, 0.9, 0.86, 0.88};
        FeatureExtractor instance = new FeatureExtractor();
        
        double[] expResult = new double[]{1, 3, 0, 0.9, 0, 0.7799999999999999, 0.09797958971132711};
        double[] result = instance.calcDistinctPeakStats(data, luminosity);
        assertArrayEquals(expResult, result, 0.0);
    }
    
    @Test
    public void testCalcDistinctPeakStats3() {
        System.out.println("calcDistinctPeakStats");
        ArrayList data = new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 0, 0));
        double[] luminosity = {0.8, 0.7, 0.75, 0.77, 0.68};
        FeatureExtractor instance = new FeatureExtractor();
        
        exceptionThrown.expect(IndexOutOfBoundsException.class);
        instance.calcDistinctPeakStats(data, luminosity);
    }    
    
    /**
     * Test of calcNonPeakWidthStats method, of class FeatureExtractor.
     */
    @Test
    public void testCalcNonPeakWidthStats1() {
        System.out.println("calcNonPeakWidthStats");
        ArrayList data = new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 0, 0));
        FeatureExtractor instance = new FeatureExtractor();
        
        double expResult = 2;
        double result = instance.calcNonPeakWidthStats(data).length;
        assertEquals(expResult, result, 0.0);
    }
    
      @Test
    public void testCalcNonPeakWidthStats2() {
        System.out.println("calcNonPeakWidthStats");
        ArrayList data = new ArrayList(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 0, 0));
        FeatureExtractor instance = new FeatureExtractor();
        
        double[] expResult = new double[]{3,0};
        double[] result = instance.calcNonPeakWidthStats(data);
        assertArrayEquals(expResult, result, 0.0);
    }

    /**
     * Test of toDoubleArray method, of class FeatureExtractor.
     */
    @Test
    public void testToDoubleArray() {
        System.out.println("toDoubleArray");
        ArrayList list = new ArrayList(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0));
        FeatureExtractor instance = new FeatureExtractor();
        
        double[] expResult = {0, 0, 0, 0, 0, 1, 1, 1, 0, 0};
        double[] result = instance.toDoubleArray(list);
        assertArrayEquals(expResult, result, 0.0);
    }

}
