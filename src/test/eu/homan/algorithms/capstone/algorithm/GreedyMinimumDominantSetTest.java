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
        GraphLoader.loadUndirectedGraph(graph, "./data/test_minimum_dominant_set.txt");

        final Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(6);
        expectedSet.add(8);

        Assert.assertEquals(expectedSet, new GreedyMinimumDominantSet<>(graph).get());
    }

    @Test
    public void testSmallGraph() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_minimum_dominant_set_wrong.txt");

        final Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(5);
        expectedSet.add(8);
        expectedSet.add(1);

        Assert.assertEquals(expectedSet, new GreedyMinimumDominantSet<>(graph).get());
    }

    @Test
    public void testAnalyseUcsd() {

        System.out.println("Analysing UCSD graph");
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/facebook_ucsd.txt");

        System.out.println(String.format("Graph has %d vertices and %d edges", graph.v(), graph.e()));

        final long startTime = System.currentTimeMillis();
        final Set<Integer> minimumDominantSet = new GreedyMinimumDominantSet<>(graph).get();
        final long endTime = System.currentTimeMillis();

        System.out.println(String.format("Minimum dominant set calculated in %d millis", (endTime - startTime)));
        System.out.println(String.format("Minimum dominant set has %d elements", minimumDominantSet.size()));
    }

    @Test
    public void testAnalyseFacebook1000() {

        System.out.println("Analysing Facebook 1000 graph");
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/facebook_1000.txt");

        System.out.println(String.format("Graph has %d vertices and %d edges", graph.v(), graph.e()));

        final long startTime = System.currentTimeMillis();
        final Set<Integer> minimumDominantSet = new GreedyMinimumDominantSet<>(graph).get();
        final long endTime = System.currentTimeMillis();

        System.out.println(String.format("Minimum dominant set calculated in %d millis", (endTime - startTime)));
        System.out.println(String.format("Minimum dominant set has %d elements", minimumDominantSet.size()));
    }

    @Test
    public void testAnalyseFacebook2000() {

        System.out.println("Analysing Facebook 2000 graph");
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/facebook_2000.txt");

        System.out.println(String.format("Graph has %d vertices and %d edges", graph.v(), graph.e()));

        final long startTime = System.currentTimeMillis();
        final Set<Integer> minimumDominantSet = new GreedyMinimumDominantSet<>(graph).get();
        final long endTime = System.currentTimeMillis();

        System.out.println(String.format("Minimum dominant set calculated in %d millis", (endTime - startTime)));
        System.out.println(String.format("Minimum dominant set has %d elements", minimumDominantSet.size()));
    }
}