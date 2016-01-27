package dag;

import java.util.ArrayList;
import java.util.Hashtable;


public class DirectedAcyclicGraph {

    //private ArrayList<Vertex> vertexHashList = new ArrayList<>();
    private Hashtable<Integer,Vertex> vertexHashList;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> edgeList;

    public DirectedAcyclicGraph() {
        vertexHashList = new Hashtable<>();
        edgeList = new ArrayList<>();
    }

    public int addVertex(Object weight) {
        Vertex v = new Vertex(weight);
        int id = v.getId();
        vertexHashList.put(id, v);
        vertices.add(v);

        return id;
    }

    public void addEdge(Object a, Object b, Object weight) {
        Vertex vertexA = vertexHashList.get(a);
        Vertex vertexB = vertexHashList.get(b);
        Edge edge = new Edge(vertexA,vertexB,weight);
        edgeList.add(edge);
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
        ArrayList<Vertex> unmarkedVertices = vertices;

        for (Vertex v: unmarkedVertices) {
            if (v.marked){
                continue;
            } else {
                v.marked = true;
            }





        }
    }

    public void addEdge(Vertex a, Vertex b, int weight) {

    }
}