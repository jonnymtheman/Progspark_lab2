package dag;

import dag.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
            assertNotEquals(true,v1.equals(v2));
            assertNotEquals(v1.getId(), v2.getId());
            assertNotEquals(true,v1.equals(v2));
    }
}
