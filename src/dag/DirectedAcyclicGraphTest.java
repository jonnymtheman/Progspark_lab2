package dag;

import org.junit.*;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.util.ArrayList;
/**
 * Conduction of a series of tests for the dag.
 */
public class DirectedAcyclicGraphTest {

    private DirectedAcyclicGraph dag;
    int a,b,c,d,e,f;

    @Before
    public void setUp() throws Exception {
        dag = new DirectedAcyclicGraph();

        a = dag.addVertex('a');
        b = dag.addVertex('b');
        c = dag.addVertex('c');
        d = dag.addVertex('d');
        e = dag.addVertex('e');

        dag.addEdge(a, b, 'b');
        dag.addEdge(a, c, 'b');
        dag.addEdge(b, d, 'a');
        dag.addEdge(c, e, 'a');
        dag.addEdge(d, e, 'c');

    }

    @After
    public void tearDown() throws Exception {
        dag = null;
    }

    /**
     * Methods to use as the f and g functions in the data type.
     * @param o object
     * @return int a weight
     */
    public int fFunc (Object o){
        switch (o.toString()){
            case "a":
                return 1;
            case "b":
                return 2;
            case "c":
                return 3;
            case "d":
                return 4;
            case "e":
                return 5;
            case "f":
                return 6;
            case "g":
                return 7;
            default:
                return 1;
        }
    }

    /**
     * Methods to use as the f and g functions in the data type.
     * @param o object
     * @return int a weight
     */
    public int gFunc (Object o){
        switch (o.toString()){
            case "a":
                return 1;
            case "b":
                return 2;
            case "c":
                return 3;
            case "d":
                return 4;
            case "e":
                return 5;
            case "f":
                return 6;
            case "g":
                return 7;
            default:
                return 1;
        }
    }

    @Test
    public void testLongestPathTraversal() throws Exception {
            Method f = this.getClass().getMethod("fFunc",Object.class);
            Method g = this.getClass().getMethod("gFunc",Object.class);
            dag.getWeightOflongestPath(dag.getVertexHashList().get(a), dag.getVertexHashList().get(e),f,g);
    }


    /**
     * Test shall get an exception because the edge to be added creates a cycle.
     */
    @Test
    public void testCyclicAdd(){
            Assert.assertFalse(dag.addEdge(d, a, 'b'));
    }

    /**
     * See output for judgment on whether the topological sorting works, I say it does, but who am I? ;)
     * @throws Exception
     */
    @Test
    public void testTopologicalOrdering() throws Exception {
        ArrayList<Vertex> s;
        s=dag.topologicalOrdering();
        Assert.assertNotNull(s);
        for (Vertex v: s) {
            System.out.println(v.getWeight());
        }
    }




}
