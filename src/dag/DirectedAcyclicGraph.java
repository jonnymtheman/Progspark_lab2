package dag;

import dag.Vertex;

import java.util.ArrayList;

public class DirectedAcyclicGraph {

    private ArrayList<Vertex> dagList = new ArrayList<>();

    public int addVertex(Object weight) {
        Vertex v = new Vertex(weight);
        dagList.add(v);
        return v.getId();
    }

    public void addEdge(char a, char b, char c) {

    }

    public ArrayList<Vertex> topologicalOrdering() {

        return dagList;
    }

    public void addEdge(Vertex a, Vertex b, int weight) {

    }
}