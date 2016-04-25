package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashSet;
import java.util.Set;

public final class GreedyMinimumDominantSet<T> implements MinimumDominantSet<T> {

    private final Set<T> minimumDominantSet = new HashSet<>();

    public GreedyMinimumDominantSet(final Graph<T> graph) {

        final Set<T> uncovered = new HashSet<>();
        graph.forEach(uncovered::add);

        // Greedily adding vertices that cover the most neighbour to the minimum dominant set
        while (!uncovered.isEmpty()) {
            final T nextDominant = maxCovering(uncovered, graph);
            if (nextDominant == null) {
                return;
            }
            minimumDominantSet.add(nextDominant);
            uncovered.remove(nextDominant);
            graph.adj(nextDominant).forEach(uncovered::remove);
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public Set<T> get() {
        return minimumDominantSet;
    }

    private T maxCovering(final Set<T> uncovered, final Graph<T> graph) {

        final int[] maxCoverage = {0};
        final T[] currentDominant = (T[]) new Object[]{null};

        // Finding the next vertex that can cover the most neighbours
        uncovered.forEach(v -> {
            final Set<T> uncoveredNeighbours = new HashSet<>();
            uncoveredNeighbours.add(v);
            graph.adj(v).forEach(t -> {
                if (uncovered.contains(t)) {
                    uncoveredNeighbours.add(t);
                }
            });

            if (uncoveredNeighbours.size() > maxCoverage[0]) {
                maxCoverage[0] = uncoveredNeighbours.size();
                currentDominant[0] = v;
            }
        });

        return currentDominant[0];
    }
}
