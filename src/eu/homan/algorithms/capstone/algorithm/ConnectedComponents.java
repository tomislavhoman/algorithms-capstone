package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashMap;
import java.util.Map;

public final class ConnectedComponents<T> {

    private final Map<T, Integer> components;

    public ConnectedComponents(Graph<T> graph) {
        components = new HashMap<>();

        final DfsVisit<T> dfsVisit = new DfsVisit<>(graph);
        final int[] currentComponent = {0};
        dfsVisit.start(
                v -> {
                    components.put(v, currentComponent[0]);
                },
                v -> {
                },
                component -> {
                    currentComponent[0] = component;
                }
        );
    }

    public Map<T, Integer> components() {
        return components;
    }
}
