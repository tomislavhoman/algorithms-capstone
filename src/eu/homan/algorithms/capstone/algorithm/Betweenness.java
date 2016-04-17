package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.graph.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Betweenness<T> {

    private final Graph<T> graph;

    private Map<Pair<T>, Float> flows;
    private Pair<T> maxBetweennessEdge;

    public Betweenness(Graph<T> graph) {
        this.graph = graph;

        flows = new HashMap<>();
        graph.forEach(v -> {
            final Map<Pair<T>, Float> flowsForAVertex = caluclateBetweennessForVertex(v);
            for (final Pair<T> edge : flowsForAVertex.keySet()) {
                if (!flows.containsKey(edge)) {
                    flows.put(edge, 0.0f);
                }
                flows.put(edge, flows.get(edge) + flowsForAVertex.get(edge));
            }
        });

        for (final Pair<T> edge : flows.keySet()) {
            flows.put(edge, flows.get(edge) / 2.0f);
        }

        float maxBetweenness = 0.0f;
        Pair<T> maxBetweennessEdge = null;
        for (final Pair<T> edge : flows.keySet()) {
            final float betweenness = flows.get(edge);
            if (betweenness > maxBetweenness) {
                maxBetweenness = betweenness;
                maxBetweennessEdge = edge;
            }
        }

        this.maxBetweennessEdge = maxBetweennessEdge;
    }

    public Betweenness(Graph<T> graph, T vertex) {
        this.graph = graph;

        flows = caluclateBetweennessForVertex(vertex);
    }

    private Map<Pair<T>, Float> caluclateBetweennessForVertex(final T vertex) {

        final Map<Pair<T>, Float> flows = new HashMap<>();
        final BfsVisit<T> bfsVisit = new BfsVisit<>(graph);
        bfsVisit.start(vertex, v -> {
        });
        final Map<T, List<T>> goesTo = bfsVisit.getGoesTo();
        final Map<T, Integer> totalNumberOfPathsTo = bfsVisit.getNumberOfTotalPathsTo();
        final Map<T, List<T>> pathsTo = bfsVisit.getPathsTo();

        bfsVisit.reverseLayerOrder().forEach(v -> {
            final float[] inputFlow = {0.0f};
            goesTo.get(v).forEach(t -> {
                final Pair<T> edge = new Pair<>(v, t);
                if (flows.containsKey(edge)) {
                    inputFlow[0] += flows.get(edge);
                }
            });
            inputFlow[0] += 1.0f;

            final List<T> pathsToV = pathsTo.get(v);
            if (pathsToV != null) {
                pathsToV.forEach(t -> {
                    float totalNumberOfPathsToT = (float) totalNumberOfPathsTo.get(t);
                    if (totalNumberOfPathsToT == 0.0f) {
                        totalNumberOfPathsToT = 1.0f;
                    }
                    final float totalNumberOfPathsToV = (float) totalNumberOfPathsTo.get(v);
                    final float flow = inputFlow[0] * totalNumberOfPathsToT / totalNumberOfPathsToV;
                    flows.put(new Pair<>(v, t), flow);
                    flows.put(new Pair<>(t, v), flow);
                });
            }
        });
        return flows;
    }

    public float between(final T vertex1, final T vertex2) {
        return flows.get(new Pair<>(vertex1, vertex2));
    }

    public Pair<T> getMaxBetweennessEdge() {
        return maxBetweennessEdge;
    }
}
