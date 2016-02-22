package dag;

/**
 * The Edge in this implementation of a DAG.
 */
class Edge {

    private Vertex origin, destination;
    Object weight;

    /**
     * Constructor
     * @param a origin vertex
     * @param b destination vertex
     * @param weight
     */
    public Edge(Vertex a, Vertex b, Object weight) {
        origin = a;
        destination = b;
        this.weight = weight;
    }

    protected Vertex getOrigin() {
        return origin;
    }

    protected Vertex getDestination() {
        return destination;
    }

    /**
     * To see if a vertex has a destination.
     * @param n the origin vertex
     * @return boolean
     */
    protected boolean hasDestination(Vertex n) {
        if (origin==n) {
            if (destination != null) {
                return true;
            }
        }
        return false;
    }

    protected Object getWeight() {
        return weight;
    }
}
