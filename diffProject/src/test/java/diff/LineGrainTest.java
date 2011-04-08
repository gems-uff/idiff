/*
 * LineGrainTest
 */

package diff;

import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fernanda Floriano Silva
 */
public class LineGrainTest {

    public LineGrainTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class LineGrain.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        File file = null;
        LineGrain instance = new LineGrain();
        List expResult = null;
        List result = instance.start(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}