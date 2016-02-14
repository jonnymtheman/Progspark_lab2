package dag;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class DirectedAcyclicGraphTest {

    private DirectedAcyclicGraph dag;
    int a,b,c,d,e,f;

    @Before
    public void setUp() throws Exception {
        dag = new DirectedAcyclicGraph();

        a = dag.addVertex(1);
        b = dag.addVertex(2);
        c = dag.addVertex(3);
        d = dag.addVertex(4);
        e = dag.addVertex(5);
        f = dag.addVertex(6);

        dag.addEdge(a, b, 2);
        dag.addEdge(a, c, 2);
        dag.addEdge(b, d, 1);
        dag.addEdge(c, d, 1);
        dag.addEdge(c, e, 1);
        dag.addEdge(d, e, 3);
        dag.addEdge(b, f, 3);

    }

    @Test
    public void testLongestPathTraversal() throws Exception {
            dag.getWeightOflongestPath(dag.getVertexHashList().get(a),
                                       dag.getVertexHashList().get(e));
    }

    @Test
    public void testTopologicalOrdering() throws Exception {
        ArrayList<Vertex> s;
        s=dag.topologicalOrdering();
        for (Vertex v: s) {
            System.out.println(v.getWeight());
        }
    }
}
