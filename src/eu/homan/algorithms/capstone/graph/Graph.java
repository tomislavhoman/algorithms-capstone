package eu.homan.algorithms.capstone.graph;

public interface Graph<T> {

    /**
     * Creates a vertex with the given number. */

    /**
     * Creates a vertex with the given number.
     *
     * @param vertex - a vertex
     */
    void addVertex(T vertex);

    /* Creates an edge from the first vertex to the second. */

    /**
     * Creates an edge from the first vertex to the second.
     *
     * @param vertex1 - from vertex
     * @param vertex2 - to vertex
     */
    void addEdge(T vertex1, T vertex2);

    /**
     * Returns number of vertices
     *
     * @return number of vertices
     */
    int v();

    /**
     * Returns number of edges
     *
     * @return number of edges
     */
    int e();

    /**
     * Builds and returns the graph that is the same as original,
     * but has all the edges reversed
     *
     * @return transposed graph
     */
    Graph<T> transpose();
}
