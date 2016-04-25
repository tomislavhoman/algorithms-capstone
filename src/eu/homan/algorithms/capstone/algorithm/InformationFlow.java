package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashSet;
import java.util.Set;

public final class InformationFlow<T> {

    private final Set<T> switched;
    private final Set<T> notSwitched;
    private final boolean hasReachedEquilibrium;
    private final int numberOfSteps;

    public InformationFlow(Graph<T> graph, int a, int b, Set<T> sources) {

        final float threshold = (float) b / ((float) a + (float) b);
        final int[] numberOfSteps = new int[]{0};
        this.switched = new HashSet<>();
        this.hasReachedEquilibrium = cascadeNetwork(graph, threshold, sources, switched, numberOfSteps);
        this.notSwitched = getNotSwitched(switched, graph);
        this.numberOfSteps = numberOfSteps[0];
    }

    private boolean cascadeNetwork(final Graph<T> graph,
                                   final float threshold,
                                   final Set<T> sources,
                                   final Set<T> switched,
                                   int[] numberOfSteps) {

        for (T vertex : sources) {
            switched.add(vertex);
        }

        // The neighbourhood of switched vertices
        final Set<T> candidatesToSwitch = new HashSet<>();

        final int maxNumberOfIterations = graph.v() + 1;
        boolean hasReachedEquilibrium = false;
        int currentIteration = 0;

        // Loop until reached equilibrium or hit the maximum number of iterations
        while (!hasReachedEquilibrium && currentIteration < maxNumberOfIterations) {

            // Expand the candidates front
            fillNewCandidates(candidatesToSwitch, switched, graph);

            int numberOfSwitched = 0;
            final Set<T> switchedInThisIteration = new HashSet<>();
            for (T candidate : candidatesToSwitch) {
                final boolean hasSwitched = trySwitchingTheCandidate(candidate, switched, graph, threshold);
                if (hasSwitched) {
                    numberOfSwitched++;

                    // Cannot remove immediately from candidatesToSwitch
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

        numberOfSteps[0] = currentIteration;

        return hasReachedEquilibrium;
    }

    // Implementation of the formula given in the lectures
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

    // Just add the neighbours of the currently switched front to the candidates
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

    /**
     * Returns the set of vertices that have switched
     *
     * @return switched
     */
    public Set<T> switched() {
        return switched;
    }

    /**
     * Returns the set of vertices that haven't switched
     *
     * @return notSwitched
     */
    public Set<T> notSwitched() {
        return notSwitched;
    }

    /**
     * Returns the flag indicating the network has reached the equilibrium
     *
     * @return hasReachedEquilibrium
     */
    public boolean reachedEquilibrium() {
        return hasReachedEquilibrium;
    }

    /**
     * Returns number of steps the algorithm took to reach the equilibrium
     *
     * @return numberOfSteps
     */
    public int getNumberOfSteps() {
        return numberOfSteps;
    }
}
