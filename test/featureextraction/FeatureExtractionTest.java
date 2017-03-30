package featureextraction;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author Nate
 */
public class FeatureExtractionTest {
    
    public FeatureExtractionTest() {
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
     * Test of main method, of class FeatureExtraction.
     */
    @Test
    public void testMainArgs() throws Exception {
        System.out.println("main");
        String[] args = null;
        exceptionThrown.expect(NullPointerException.class);
        FeatureExtraction.main(args);      
    }
    
    @Test
    public void testMainArgsLength1() throws Exception {
        System.out.println("main");
        String[] args = {"host", "nonHost"};
        exceptionThrown.expect(Exception.class);
        FeatureExtraction.main(args);      
    }
    
    @Test
    public void testMainArgsLength2() throws Exception {
        System.out.println("main");
        String[] args = {"host", "nonHost", "test", "tooManyArgs"};
        exceptionThrown.expect(Exception.class);
        FeatureExtraction.main(args);      
    }
       
}
