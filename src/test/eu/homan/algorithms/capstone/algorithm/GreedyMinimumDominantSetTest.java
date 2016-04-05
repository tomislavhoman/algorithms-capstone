package test.eu.homan.algorithms.capstone.algorithm;


import eu.homan.algorithms.capstone.algorithm.GreedyMinimumDominantSet;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class GreedyMinimumDominantSetTest {

    @Test
    public void testBigGraph() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_maximum_dominant_set.txt");

        final Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(6);
        expectedSet.add(8);

        Assert.assertEquals(expectedSet, new GreedyMinimumDominantSet<>(graph).get());
    }

    @Test
    public void testSmallGraph() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_maximum_dominant_set_wrong.txt");

        final Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(5);
        expectedSet.add(8);
        expectedSet.add(1);

        Assert.assertEquals(expectedSet, new GreedyMinimumDominantSet<>(graph).get());
    }
}