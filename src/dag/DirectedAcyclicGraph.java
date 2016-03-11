package dag;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;

/**
 * An implementation of the Abstract data type Directed Acyclic Graph
 */
public class DirectedAcyclicGraph {

    private Hashtable<Integer,Vertex> vertexHashList;
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    private ArrayList<Edge> edgeList;
    private ArrayList<Vertex> lList;
    private Stack<Edge> pathStack;
    private Stack<Vertex> stackOfNodes;
    private ArrayList<Stack<Edge>> pathArrList;

    /**
     * Constructor
     */
    public DirectedAcyclicGraph() {
        vertexHashList = new Hashtable<>();
        pathStack = new Stack<>();
        stackOfNodes = new Stack<>();
        edgeList = new ArrayList<>();
        lList = new ArrayList<>();
        pathArrList = new ArrayList<>();
    }

    /**
     * addVertex - Method for adding a vertex to the dag.
     * @param weight the weight of the vertex.
     * @return int the id of the vertex
     */
    public int addVertex(Object weight) {
        Vertex v = new Vertex(weight);
        int id = v.getId();
        vertexHashList.put(id, v);
        vertices.add(v);

        return id;
    }

    /**
     * Method for adding an edge between two vertices.
     * @param a origin vertex
     * @param b destination vertex
     * @param weight the weight of the egde.
     */
    public void addEdge(Object a, Object b, Object weight) {
        Vertex vertexA = vertexHashList.get(a);
        Vertex vertexB = vertexHashList.get(b);
        vertexA.addNeighbour(vertexB);
        Edge edge = new Edge(vertexA,vertexB,weight);

        //vertexA.outEdges.add(edge);
        vertexB.incEdges.add(edge);
        edgeList.add(edge);


        if (topologicalOrdering()==null){
            System.err.println("Can't add edge, creates a cycle");
            vertexB.incEdges.remove((vertexB.incEdges.size()-1));
            edgeList.remove((edgeList.size()-1));
            vertexA.getNeighbours().remove((vertexA.getNeighbours().size()-1));
        }

    }


    /**
     * topologicalOrdering - Part of the user visible interface of methods.
     * Sorts the dag topologically and returns an arraylist of vertices in
     * order.
     * @return ArrayList<Vertex> of vertices
     */
    public ArrayList<Vertex> topologicalOrdering() {
        while (isUnmarked()) {
            for (Vertex v : vertices) {
                if (!v.isPermMarked()) {
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
        resetMarks();
        return lList;
    }

    private void resetMarks(){
        for (Vertex v: vertices) {
            v.setPermMarked(false);
            v.setTempMarked(false);
        }
    }

    /**
     * isUnmarked - Internal marking method for the DAG.
     * @return boolean
     */
    private boolean isUnmarked() {
        for (Vertex v : vertices) {
            if (v.isPermMarked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Internal method for visiting the the nodes/vertices in the dag.
     * @param n vertex n
     * @throws NotADagException If no dag, will be caught and the user will be told.
     */
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
            n.setPermMarked(true);
            n.setTempMarked(false);
            lList.add(n);
        }
    }

    /**
     * Internal method for getting the edges from a vertex.
     * @param n the vertex from where the edges origin.
     * @return ArrayList<Vertex> with vertices outgoing.
     */
    private ArrayList<Vertex> getEdgesFromVertex(Vertex n) {
        ArrayList<Vertex> tempList = new ArrayList<>();
        for (Edge e : edgeList) {
            if (e.hasDestination(n)) {
                tempList.add(e.getDestination());
            }
        }
        return tempList;
    }

    public Hashtable<Integer, Vertex> getVertexHashList() {
        return vertexHashList;
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

    /**
     * getWeightOflongestPath - Public method for getting the longest path within the DAG.
     * @param start start vertex.
     * @param goal goal vertex.
     * @param f A method provided by the user for dealing with the weight of the edges.
     * @param g A method provided by the user for dealing with the weight of the vertices.
     * @return int The weight of the longest path as an int.
     */
    public int getWeightOflongestPath(Vertex start, Vertex goal, Method f, Method g){
        traverse(start,goal,start);
        System.out.println("Paths found from: "+ start.getWeight() + " to " + goal.getWeight() + " = " + pathArrList.size());
        System.out.println("Longest path: "+sumOfFFunc(pathArrList,f,g));
        return 0;
    }

    /**
     * Internal method for traversing the dag.
     * @param n vertex n
     * @param goalNode vertex
     * @param lastNode vertex
     */
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

    /**
     * Internal method for calculating the sum of the weights.
     * @return int the total sum
     */
    private int sumOfFFunc(ArrayList<Stack<Edge>> paths, Method f,Method g){

        ArrayList<Integer> weights = new ArrayList<>();
        Object fInst=null;
        Object gInst=null;

        try {
            Class fClass = f.getDeclaringClass();
            Class gClass = g.getDeclaringClass();
            gInst = gClass.newInstance();
            fInst = fClass.newInstance();

        } catch (Exception e){
            e.printStackTrace();
        }

        //Check edge weights
        for (Stack<Edge> e: paths){
            int edgeWeight=0;
            int vertexWeight=0;
            int lastVertexW=0;
            //Loops through one path
            for (Edge edge: e) {

                try {
                    vertexWeight += (int)g.invoke(gInst,edge.getOrigin().getWeight());
                    edgeWeight += (int)f.invoke(fInst,edge.getWeight());
                    lastVertexW = (int)g.invoke(gInst,edge.getDestination().getWeight());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //System.out.println("VertexW: "+ vertexWeight + " EdgeW: "+ edgeWeight);
            }
            vertexWeight += lastVertexW;
            //adds the total length of the path to a list.
            int totalW = (edgeWeight+vertexWeight);
            weights.add(totalW);
            System.out.println("Total Weight: "+ totalW + "\n");
        }
        int maxW=0;
        for (Integer i: weights) {
            if (i>maxW){
                maxW = i;
            }
            //System.out.println("Weight: "+i);
        }
        return maxW; //Returns the longest path
    }
}