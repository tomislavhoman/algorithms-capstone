package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class StronglyConnectedComponents<T> {

    private int numberOfComponents = 0;
    private Map<T, Integer> components = new HashMap<>();

    public StronglyConnectedComponents(final Graph<T> graph) {

        final Graph<T> transposedGraph = graph.transpose();
        final HashSet<T> visited = new HashSet<>();
        new DfsOrdering<>(graph).reversePostOrder().forEach(v -> {
            if (!visited.contains(v)) {
                numberOfComponents++;
                new DfsVisit<>(transposedGraph).start(v,
                        t -> {
                            if (!visited.contains(t)) {
                                components.put(t, numberOfComponents);
                                visited.add(t);
                            }
                        },
                        t -> {
                        });
            }
        });
    }

    public int numberOfComponents() {
        return numberOfComponents;
    }

    public int componentFor(final T v) {
        return components.get(v);
    }
}
