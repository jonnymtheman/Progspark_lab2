package dag;

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

    public void addEdge(Object a, Object b, Object weight) {
        Vertex vertexA = vertexList.get(a);
        Vertex vertexB = vertexList.get(b);
        Edge edge = new Edge(vertexA,vertexB,weight);
        edgeList.add(edge);
    }

    public void topologicalOrdering() {

    }

    public void addEdge(Vertex a, Vertex b, int weight) {

    }
}