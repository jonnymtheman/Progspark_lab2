package dag;

import dag.Edge;

class Vertex {
    private Object weight;
    private Edge edge;

    public Vertex(Object weight){
        this.weight = weight;
    }

    public void setWeight(int w){
        this.weight = w;
    }

    public int getId() {
        return this.hashCode();
    }
}