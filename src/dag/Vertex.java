package dag;

import java.util.ArrayList;

/**
 * The internal vertex class in this implementation of a DAG.
 *
 * Many of the methods in this class are self explanatory.
 */
public class Vertex {
    private Object weight;
    protected ArrayList<Edge> incEdges = new ArrayList<>();
    protected boolean tempMarked = false;
    protected boolean permMarked = false;
    private ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

    protected void addNeighbour(Vertex n){
        neighbours.add(n);
    }

    protected Vertex(Object weight){
        this.weight = weight;
    }

    protected void setTempMarked(Boolean b) {
        tempMarked = b;
    }

    protected void setPermMarked(Boolean b) {
        permMarked = b;
    }

    protected boolean isPermMarked() {
        return permMarked;
    }

    protected boolean isTempMarked() {
        return tempMarked;
    }

    protected Object getWeight(){
        return weight;
    }

    protected  int getId() {
        return this.hashCode();
    }

    public ArrayList<Vertex> getNeighbours() {
        return neighbours;
    }

}