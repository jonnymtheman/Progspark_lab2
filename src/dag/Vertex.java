package dag;

import dag.Edge;

class Vertex {
    private Object weight;
    //private Edge edge;
    protected boolean visited;

    protected Vertex(Object weight){
        this.weight = weight;
    }

    protected void setWeight(int w){
        this.weight = w;
    }

    protected Object getWeight(){
        return weight;
    }

    protected  int getId() {
        return this.hashCode();
    }
}