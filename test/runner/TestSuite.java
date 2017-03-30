package runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Nate
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({utilities.UtilitiesSuite.class, featureextraction.FeatureextractionSuite.class})
public class TestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
}
