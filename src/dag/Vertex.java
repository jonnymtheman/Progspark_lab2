package dag;

class Vertex {
    private Object weight;
    protected boolean visited;

    protected Vertex(Object weight){
        this.weight = weight;
    }


    protected Object getWeight(){
        return weight;
    }

    protected  int getId() {
        return this.hashCode();
    }

}