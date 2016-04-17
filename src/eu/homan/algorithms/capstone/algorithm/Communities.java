package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.graph.Pair;

import java.util.Map;

public final class Communities<T> {

    private final Map<T, Integer> commmunities;
    private final int numberOfCommunities;

    public Communities(Graph<T> inputGraph, int numberOfCommunities) {

        final Graph<T> graph = inputGraph.copy();

        for (int i = 1; i < numberOfCommunities; i++) {
            final Betweenness<T> betweenness = new Betweenness<>(graph);
            final Pair<T> maxBetweennessEdge = betweenness.getMaxBetweennessEdge();
            graph.removeEdge(maxBetweennessEdge.first, maxBetweennessEdge.second);
            graph.removeEdge(maxBetweennessEdge.second, maxBetweennessEdge.first);
        }

        final ConnectedComponents<T> connectedComponents = new ConnectedComponents<>(graph);
        this.commmunities = connectedComponents.components();

        int realNumberOfCommunities = 1;
        for (T vertex : commmunities.keySet()) {
            if (commmunities.get(vertex) > realNumberOfCommunities) {
                realNumberOfCommunities = commmunities.get(vertex);
            }
        }
        this.numberOfCommunities = realNumberOfCommunities;
    }

    public Map<T, Integer> getCommunities() {
        return commmunities;
    }

    public int getNumberOfCommunities() {
        return numberOfCommunities;
    }
}
