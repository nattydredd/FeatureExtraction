package utilities;

import dataStructures.Star;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nate
 */
public class ThresholderTest {
    
    public ThresholderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of normalise method, of class Thresholder.
     */
    @Test
    public void testNormalise1() {
        System.out.println("normalise");
        double[] luminosity = {0.8, 0.7, 0.75, 0.77, 0.68, 0.66, 0.78, 0.9, 0.86, 0.88};
        Thresholder instance = new Thresholder();
        
        double expResult = 10;
        double result = instance.normalise(luminosity).length;
        assertEquals(expResult, result, 0.0);
    }
    
}
