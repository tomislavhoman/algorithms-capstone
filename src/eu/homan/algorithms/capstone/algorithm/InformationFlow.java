package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashSet;
import java.util.Set;

public final class InformationFlow<T> {

    private final Set<T> switched;
    private final Set<T> notSwitched;
    private final boolean hasReachedEquilibrium;


    public InformationFlow(Graph<T> graph, int a, int b, Set<T> sources) {

        final float threshold = (float) b / ((float) a + (float) b);
        this.switched = new HashSet<>();
        this.hasReachedEquilibrium = cascadeNetwork(graph, threshold, sources, switched);
        this.notSwitched = getNotSwitched(switched, graph);
    }

    private boolean cascadeNetwork(final Graph<T> graph, final float threshold, final Set<T> sources, final Set<T> switched) {

        for (T vertex : sources) {
            switched.add(vertex);
        }

        // The neighbourhood of switched vertices
        final Set<T> candidatesToSwitch = new HashSet<>();

        final int maxNumberOfIterations = graph.v() + 1;
        boolean hasReachedEquilibrium = false;
        int currentIteration = 0;
        while (!hasReachedEquilibrium && currentIteration < maxNumberOfIterations) {

            fillNewCandidates(candidatesToSwitch, switched, graph);

            int numberOfSwitched = 0;
            final Set<T> switchedInThisIteration = new HashSet<>();
            for (T candidate : candidatesToSwitch) {
                final boolean hasSwitched = trySwitchingTheCandidate(candidate, switched, graph, threshold);
                if (hasSwitched) {
                    numberOfSwitched++;
                    switchedInThisIteration.add(candidate);
                }
            }

            for (T vertex : switchedInThisIteration) {
                if (candidatesToSwitch.contains(vertex)) {
                    candidatesToSwitch.remove(vertex);
                }
            }

            if (numberOfSwitched == 0 || switched.size() == graph.v()) {
                hasReachedEquilibrium = true;
            }

            currentIteration++;
        }

        return hasReachedEquilibrium;
    }

    private boolean trySwitchingTheCandidate(final T candidate, final Set<T> switched, final Graph<T> graph, final float threshold) {

        int d = 0;
        int p = 0;
        for (T vertex : graph.adj(candidate)) {
            if (switched.contains(vertex)) {
                p++;
            }
            d++;
        }

        if ((float) p / (float) d > threshold) {
            switched.add(candidate);
            return true;
        }

        return false;
    }

    private void fillNewCandidates(final Set<T> candidatesToSwitch, final Set<T> switched, final Graph<T> graph) {

        for (T vertex : switched) {
            for (T neighbour : graph.adj(vertex)) {
                if (!candidatesToSwitch.contains(neighbour) && !switched.contains(neighbour)) {
                    candidatesToSwitch.add(neighbour);
                }
            }
        }
    }

    private Set<T> getNotSwitched(final Set<T> switched, final Graph<T> graph) {

        final Set<T> notSwitched = new HashSet<>();
        for (T vertex : graph) {
            if (!switched.contains(vertex)) {
                notSwitched.add(vertex);
            }
        }
        return notSwitched;
    }

    public Set<T> switched() {
        return switched;
    }

    public Set<T> notSwitched() {
        return notSwitched;
    }

    public boolean reachedEquilibrium() {
        return hasReachedEquilibrium;
    }
}
