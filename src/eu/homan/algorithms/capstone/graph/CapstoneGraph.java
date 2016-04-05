package eu.homan.algorithms.capstone.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.TreeMap;
import java.util.function.Consumer;

public final class CapstoneGraph<T> implements Graph<T> {

    private final Map<T, List<T>> graph = new TreeMap<>();
    private int v = 0;
    private int e = 0;

    /**
     * @inheritDoc
     */
    @Override
    public void addVertex(final T vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new LinkedList<>());
            v++;
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addEdge(final T vertex1, final T vertex2) {
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
    public Iterable<T> adj(final T v) {
        return graph.get(v);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Graph<T> transpose() {

        final Graph<T> transposedGraph = new CapstoneGraph<>();

        graph.keySet().forEach(transposedGraph::addVertex);

        graph.keySet().forEach(v ->
                graph.get(v).forEach(neighbour -> transposedGraph.addEdge(neighbour, v)));

        return transposedGraph;
    }

    private void checkVertex(final T vertex) {
        if (!graph.containsKey(vertex)) {
            throw new IllegalArgumentException("The graph does not contain vertex");
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

    @Override
    public Iterator<T> iterator() {
        return graph.keySet().iterator();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        graph.keySet().forEach(action::accept);
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }
}
