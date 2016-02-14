package dag;

class Vertex {
    private Object weight;



    protected boolean temp_marked = false;
    protected boolean perm_marked = false;

    protected Vertex(Object weight){
        this.weight = weight;
    }

    protected void setTemp_marked(Boolean b) {
        temp_marked = b;
    }

    protected void setPerm_maked(Boolean b) {
        perm_marked = b;
    }

    protected boolean isPerm_marked() {
        return perm_marked;
    }

    protected boolean isTemp_marked() {
        return temp_marked;
    }

    protected Object getWeight(){
        return weight;
    }

    protected  int getId() {
        return this.hashCode();
    }

}