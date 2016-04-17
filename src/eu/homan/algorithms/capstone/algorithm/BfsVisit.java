package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;

public final class BfsVisit<T> {

    private final Graph<T> graph;
    private Map<T, Integer> numberOfImmediatePathsTo;
    private Map<T, Integer> numberOfTotalPathsTo;
    private Map<T, List<T>> pathsTo;
    private Map<T, List<T>> goesTo;
    private Map<T, Integer> layerOf;
    private Queue<T> reverseLayerOrder;

    public BfsVisit(Graph<T> graph) {
        this.graph = graph;
        layerOf = new HashMap<>();
        numberOfImmediatePathsTo = new HashMap<>();
        numberOfTotalPathsTo = new HashMap<>();
        pathsTo = new HashMap<>();
        goesTo = new HashMap<>();
        for (T vertex : graph) {
            pathsTo.put(vertex, new LinkedList<>());
            goesTo.put(vertex, new LinkedList<>());
        }
        reverseLayerOrder = new LinkedList<>();
    }

    public void start(final T startingVertex,
                      final Consumer<? super T> vertexVisited) {

        final HashSet<T> visited = new HashSet<>();
        final Queue<T> queue = new LinkedList<>();
        final Stack<T> reverseLayerStack = new Stack<>();

        visited.add(startingVertex);
        queue.add(startingVertex);
        reverseLayerStack.push(startingVertex);
        layerOf.put(startingVertex, 1);
        numberOfImmediatePathsTo.put(startingVertex, 0);
        numberOfTotalPathsTo.put(startingVertex, 0);
        pathsTo.put(startingVertex, null);

        while (!queue.isEmpty()) {
            final T currentVertex = queue.poll();
            vertexVisited.accept(currentVertex);
            for (T neighbour : graph.adj(currentVertex)) {
                if (!visited.contains(neighbour)) {
                    queue.add(neighbour);
                    visited.add(neighbour);
                    layerOf.put(neighbour, layerOf.get(currentVertex) + 1);
                    reverseLayerStack.push(neighbour);
                }

                if (layerOf.containsKey(neighbour) && layerOf.get(neighbour) > layerOf.get(currentVertex)) {
                    pathsTo.get(neighbour).add(currentVertex);
                    goesTo.get(currentVertex).add(neighbour);
                    increaseNumberOfImmediatePaths(neighbour, numberOfImmediatePathsTo);
                    updateNumberOfTotalPaths(neighbour, numberOfTotalPathsTo, pathsTo.get(neighbour));
                }
            }
        }

        while (!reverseLayerStack.empty()) {
            reverseLayerOrder.add(reverseLayerStack.pop());
        }
    }

    private void increaseNumberOfImmediatePaths(final T vertex,
                                                final Map<T, Integer> numberOfPathsTo) {
        if (!numberOfPathsTo.containsKey(vertex)) {
            numberOfPathsTo.put(vertex, 0);
        }

        numberOfPathsTo.put(vertex, numberOfPathsTo.get(vertex) + 1);
    }

    private void updateNumberOfTotalPaths(final T vertex,
                                          final Map<T, Integer> numberOfTotalPathsTo,
                                          final List<T> pathsTo) {
        int sum = 0;
        for (T incomingVertex : pathsTo) {
            sum += numberOfTotalPathsTo.get(incomingVertex);
        }
        if (sum == 0) {
            sum = 1;
        }
        numberOfTotalPathsTo.put(vertex, sum);
    }

    public Map<T, Integer> getNumberOfImmediatePathsTo() {
        return numberOfImmediatePathsTo;
    }

    public Map<T, Integer> getNumberOfTotalPathsTo() {
        return numberOfTotalPathsTo;
    }

    public Map<T, List<T>> getPathsTo() {
        return pathsTo;
    }

    public Map<T, List<T>> getGoesTo() {
        return goesTo;
    }

    public Map<T, Integer> getLayers() {
        return layerOf;
    }

    public Queue<T> reverseLayerOrder() {
        return reverseLayerOrder;
    }
}
