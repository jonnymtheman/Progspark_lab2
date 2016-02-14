package dag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;


public class DirectedAcyclicGraph {

    public Hashtable<Integer, Vertex> getVertexHashList() {
        return vertexHashList;
    }

    private Hashtable<Integer,Vertex> vertexHashList;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> edgeList;
    private ArrayList<Vertex> lList;
    private Stack<Edge> pathStack = new Stack<>();
    private Stack<Vertex> stackOfNodes = new Stack<>();
    private ArrayList<Stack<Edge>> pathArrList = new ArrayList<>();


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
        vertexA.addNeighbour(vertexB);
        Edge edge = new Edge(vertexA,vertexB,weight);

        vertexA.outEdges.add(edge);
        vertexB.incEdges.add(edge);

        edgeList.add(edge);
    }


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
        Collections.reverse(lList);
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

    /*
     traverse(node n, goalNode)
         if n has incoming edge
            if inc edge from last node in path stack or path stack is empty
                push new edge to path stack
            else if from predecessor
               pop edge from stack
               push edge from predecessor to node n
            else
               pop edge from stack
               traverse(n , goalNode);

         push neighbours of n to stack of nodes

         if goalNode=currentNode
           push path stack to arraylist.
           pop edge from path stack (don't save)

        if else stack of nodes empty - Done

        traverse(pop stack of nodes)
     *  */

    public int getWeightOflongestPath(Vertex start, Vertex goal){
        traverse(start,goal,start);
        System.out.println("Paths found from: "+ start.getWeight() + " to " + goal.getWeight() + " = " + pathArrList.size());
        return 0;
    }

    private void traverse(Vertex n, Vertex goalNode, Vertex lastNode){
        Boolean foundEdge = false;
        if (!n.incEdges.isEmpty()){ //if n has incoming edges

            if (pathStack.isEmpty()){
                for (Edge e:n.incEdges) {
                    if (e.getOrigin().getId() == lastNode.getId()){
                        pathStack.push(e);
                        foundEdge=true;
                        break;
                    }
                }
            }

            if (!foundEdge && !pathStack.isEmpty()){
                for (Edge e: n.incEdges) {
                    if (e.getOrigin().getId() == pathStack.peek().getDestination().getId()){
                        pathStack.push(e);
                        foundEdge=true;
                        break;
                    }
                }

                if (!foundEdge){
                    Edge tmpEdge = pathStack.pop();
                    traverse(n,goalNode,tmpEdge.getOrigin());
                    return;
                }
            }
        }

        if (n.getId() == goalNode.getId()){
            pathArrList.add(((Stack<Edge>) pathStack.clone()));
            pathStack.pop();
            n=lastNode;
        } else if (!n.getNeighbours().isEmpty()){
            for (Vertex v: n.getNeighbours()) {
                stackOfNodes.push(v);
            }
        }

        if (stackOfNodes.isEmpty()){//Algorithm is done.
            return;
        } else {
            traverse(stackOfNodes.pop(),goalNode,n);
        }

    }

    private int fFunc(ArrayList<ArrayList<Vertex>> paths){
        int weight = 0;
        ArrayList<Integer> weights = new ArrayList<>();

        for (ArrayList<Vertex> av: paths){
            //Loops through one path
            for (Vertex v: av) {
                weight += v.getWeight();
            }
            //adds the total length of the path to a list.
            weights.add(weight);
        }

        int vertexWeightsLongest = 0;

        for (Integer i: weights) {
            if (i > vertexWeightsLongest){
                vertexWeightsLongest = i;
            }
        }
        //Returns the longest path
        return vertexWeightsLongest;
    }
}