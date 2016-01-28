package dag;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;


public class DirectedAcyclicGraph {

    //private ArrayList<Vertex> vertexHashList = new ArrayList<>();
    private Hashtable<Integer,Vertex> vertexHashList;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> edgeList;
    private ArrayList<Vertex> lList;

    public DirectedAcyclicGraph() {
        vertexHashList = new Hashtable<>();
        edgeList = new ArrayList<>();
        lList = new ArrayList<>();
    }

    public int addVertex(int weight) {
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
        L ← Empty list that will contain the sorted nodes
        while there are unmarked nodes do
        select an unmarked node n
        visit(n)
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

    public ArrayList<Vertex> topologicalOrdering() {
        while (isUnmarked()) {
            for (Vertex v : vertices) {
                if (!v.isPerm_marked()) {
                    try {
                        visit(v);
                    } catch (NotADagException e) {
                        System.err.println("Not a DAG; Contains cycles");
                        return null;
                    }
                }
            }
        }
        return lList;
    }

    private boolean isUnmarked() {
        for (Vertex v : vertices) {
            if (!v.isPerm_marked()) {
                return true;
            }
        }
        return false;
    }

    private void visit(Vertex n) throws NotADagException {
        if (n.isTempMarked()) {
            throw new NotADagException();
        }
        if (!n.permMarked) {
            n.setTempMarked(true);

            ArrayList<Vertex> mList = getEdgesFromVertex(n);
            for (Vertex m : mList) {
                visit(m);
            }
            n.setPerm_marked(true);
            n.setTempMarked(false);
            lList.add(n);
        }
    }

    private ArrayList<Vertex> getEdgesFromVertex(Vertex n) {
            ArrayList<Vertex> tempList = new ArrayList<>();
            for (Edge e : edgeList) {
                if (e.hasDestination(n)) {
                    tempList.add(e.getDestination());
                }
            }
        return tempList;
    }

}