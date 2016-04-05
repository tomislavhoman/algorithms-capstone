package eu.homan.algorithms.capstone.graph;

import java.util.HashMap;
import java.util.HashSet;

public final class CapstoneGraph implements Graph {

    private final HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
    private int v = 0;
    private int e = 0;

    /**
     * @inheritDoc
     */
    @Override
    public void addVertex(final int vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new HashSet<>());
            v++;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addEdge(final int vertex1, final int vertex2) {
        checkVertex(vertex1);
        checkVertex(vertex2);

        graph.get(vertex1).add(vertex2);
        e++;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int v() {
        return this.v;
    }

    /**
     * @inheritDoc
     */
    @Override
    public int e() {
        return this.e;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Graph transpose() {

        final Graph transposedGraph = new CapstoneGraph();

        graph.keySet().forEach(transposedGraph::addVertex);

        graph.keySet().forEach(v ->
                graph.get(v).forEach(neighbour -> transposedGraph.addEdge(neighbour, v)));

        return transposedGraph;
    }

    private void checkVertex(final int vertex) {
        if (!graph.containsKey(vertex)) {
            throw new IllegalArgumentException(String.format("The graph does not contain vertex %d", vertex));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CapstoneGraph that = (CapstoneGraph) o;

        if (v != that.v) return false;
        if (e != that.e) return false;
        return !(graph != null ? !graph.equals(that.graph) : that.graph != null);

    }

    @Override
    public int hashCode() {
        int result = graph != null ? graph.hashCode() : 0;
        result = 31 * result + v;
        result = 31 * result + e;
        return result;
    }

    @Override
    public String toString() {
        return "CapstoneGraph{" +
                "graph=" + graph +
                ", v=" + v +
                ", e=" + e +
                '}';
    }
}
