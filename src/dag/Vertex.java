package dag;

class Vertex {
    private int weight;

    protected boolean tempMarked = false;
    protected boolean permMarked = false;

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

}