
import java.util.ArrayList;

public class DirectedAcyclicGraph {

    private ArrayList<Vertex> dagList = new ArrayList<>();

    public int addVertex(Object weight) {
        int identifier = 0;

        dagList.add(new Vertex(weight));

        return identifier;
    }

    public void addEdge(char a, char b, char c) {

    }

    public ArrayList<Vertex> topologicalOrdering() {

        return dagList;
    }

    public void addEdge(Vertex a, Vertex b, int weight) {

    }
}