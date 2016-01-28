package dag;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Stack;

import static org.junit.Assert.*;


public class DirectedAcyclicGraphTest {

    private DirectedAcyclicGraph dag;
    int a,b,c,d,e;

    @Before
    public void setUp() throws Exception {
        dag = new DirectedAcyclicGraph();
        /* a = new Vertex(4);
         b = new Vertex(5);
         c = new Vertex(3);
         d = new Vertex(2);
         e = new Vertex(7);
        System.out.println(a.getId());
        System.out.println(b.getId());
        System.out.println(c.getId());
        System.out.println(d.getId());
        System.out.println(e.getId());
        System.out.println("kuk"); */

        a = dag.addVertex(1);
        b = dag.addVertex(2);
        c = dag.addVertex(3);
        d= dag.addVertex(4);
        e =dag.addVertex(5);

        dag.addEdge(a, b, 2);
        dag.addEdge(a, c, 2);
        dag.addEdge(b, d, 1);
        dag.addEdge(d,a,3);
        dag.addEdge(c, d, 1);
        dag.addEdge(c, e, 1);
        dag.addEdge(d, e, 3);

    }

    @Test
    public void testTopologicalOrdering() throws Exception {
        ArrayList<Vertex> s;
        s=dag.topologicalOrdering();
        for (Vertex v: s) {
           // System.out.println(v.getId());
            System.out.println(v.getWeight());
        }
    }
}
