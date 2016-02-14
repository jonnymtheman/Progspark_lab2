package dag;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class DirectedAcyclicGraphTest {

    private DirectedAcyclicGraph dag;
    int a,b,c,d,e,f;

    @Before
    public void setUp() throws Exception {
        dag = new DirectedAcyclicGraph();

        a = dag.addVertex('d');
        b = dag.addVertex('e');
        c = dag.addVertex('c');
        d = dag.addVertex('b');
        e = dag.addVertex('g');

        dag.addEdge(a, b, 'b');
        dag.addEdge(a, c, 'b');
        dag.addEdge(b, d, 'a');
        dag.addEdge(c, d, 'a');
        dag.addEdge(c, e, 'a');
        dag.addEdge(d, e, 'c');
    }
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

    @Test
    public void testTopologicalOrdering() throws Exception {
        ArrayList<Vertex> s;
        s=dag.topologicalOrdering();
        for (Vertex v: s) {
           // System.out.println(v.getWeight());
        }
    }


}
