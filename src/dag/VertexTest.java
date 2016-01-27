package dag;

import dag.Vertex;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Jonas on 2016-01-27.
 */
public class VertexTest {
    private Vertex v1;
    private Vertex v2;

    @Before
    public void setUp() throws Exception {
         v1 = new Vertex(11);
         v2 = new Vertex(11);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testSetWeight() throws Exception {
            assertNotEquals(v1.getId(), v2.getId());

    }
}