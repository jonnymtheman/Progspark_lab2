package dag;

import dag.Vertex;

import java.util.ArrayList;
import java.util.Objects;

public class DirectedAcyclicGraph {

    private ArrayList<Vertex> dagList = new ArrayList<>();

    public int addVertex(Object weight) {
        Vertex v = new Vertex(weight);
        dagList.add(v);
        return v.getId();
    }

    public void addEdge(Object a, Object b, Object weight) {

    }

    public ArrayList<Vertex> topologicalOrdering() {

        return dagList;
    }

    public void addEdge(Vertex a, Vertex b, int weight) {

    }
}