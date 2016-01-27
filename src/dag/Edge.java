package dag;


class Edge {

    private Vertex origin, destination;
    Object weight;

    public Edge(Vertex a, Vertex b, Object weight) {
        origin = a;
        destination = b;
        this.weight = weight;
    }

    protected Vertex getOrigin() { return origin; }


    protected Vertex getDestination() { return destination; }

    protected Object getWeight() { return weight; }
}
