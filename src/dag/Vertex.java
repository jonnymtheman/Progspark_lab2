package dag;

import java.util.ArrayList;

class Vertex {
    private int weight;
    protected ArrayList<Edge> incEdges = new ArrayList<>();
    protected ArrayList<Edge> outEdges = new ArrayList<>();

    protected boolean tempMarked = false;
    protected boolean permMarked = false;
    private ArrayList<Vertex> neighbours = new ArrayList<Vertex>();

    protected void addNeighbour(Vertex n){
        neighbours.add(n);
    }

    protected Vertex(int weight){
        this.weight = weight;
    }

    protected void setTempMarked(Boolean b) {
        tempMarked = b;
    }

    protected void setPerm_marked(Boolean b) {
        permMarked = b;
    }

    protected boolean isPerm_marked() {
        return permMarked;
    }

    protected boolean isTempMarked() {
        return tempMarked;
    }

    protected int getWeight(){
        return weight;
    }

    protected  int getId() {
        return this.hashCode();
    }

    public ArrayList<Vertex> getNeighbours() {
        return neighbours;
    }

}