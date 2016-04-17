package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashSet;
import java.util.function.Consumer;

public final class DfsVisit<T> {

    private final Graph<T> graph;

    public DfsVisit(Graph<T> graph) {
        this.graph = graph;
    }

    public void start(final Consumer<? super T> beginProcessing,
                      final Consumer<? super T> doneProcessing) {

        start(beginProcessing, doneProcessing, null);
    }

    public void start(final Consumer<? super T> beginProcessing,
                      final Consumer<? super T> doneProcessing,
                      final Consumer<Integer> newComponent) {

        final HashSet<T> visited = new HashSet<>();

        final int[] component = {0};
        graph.forEach(v -> {
            if (!visited.contains(v)) {
                component[0]++;
                if (newComponent != null) {
                    newComponent.accept(component[0]);
                }
                dfsVisit(graph, v, visited, beginProcessing, doneProcessing);
            }
        });
    }

    public void start(final T s,
                      final Consumer<? super T> beginProcessing,
                      final Consumer<? super T> doneProcessing) {

        final HashSet<T> visited = new HashSet<>();
        dfsVisit(graph, s, visited, beginProcessing, doneProcessing);
    }

    private void dfsVisit(final Graph<T> graph, final T v, final HashSet<T> visited,
                          final Consumer<? super T> beginProcessing,
                          final Consumer<? super T> doneProcessing) {

        if (visited.contains(v)) {
            return;
        }

        beginProcessing.accept(v);
        visited.add(v);

        graph.adj(v).forEach(neighbour -> {
            if (!visited.contains(neighbour)) {
                dfsVisit(graph, neighbour, visited, beginProcessing, doneProcessing);
            }
        });

        doneProcessing.accept(v);
    }
}
