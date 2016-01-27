package dag;

import dag.Vertex;

import java.util.ArrayList;
import java.util.Hashtable;


public class DirectedAcyclicGraph {

    //private ArrayList<Vertex> vertexList = new ArrayList<>();
    private Hashtable<Integer,Vertex> vertexList;
    private ArrayList<Edge> edgeList;

    public DirectedAcyclicGraph() {
        vertexList = new Hashtable<>();
        edgeList = new ArrayList<>();
    }

    public int addVertex(Object weight) {
        Vertex v = new Vertex(weight);
        vertexList.put(v.getId(), v);
        return v.getId();
    }

    public void addEdge(char a, char b, char c) {

    }

    /* PSEUDO code for topological ordering
    function visit(node n)
    if n has a temporary mark then stop (not a DAG)
    if n is not marked (i.e. has not been visited yet) then
        mark n temporarily
        for each node m with an edge from n to m do
            visit(m)
        mark n permanently
        unmark n temporarily
        add n to head of L
     */

    public void topologicalOrdering() {

    }

    public void addEdge(Vertex a, Vertex b, int weight) {

    }
}