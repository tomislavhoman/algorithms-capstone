package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.Betweenness;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

public class BetweennessTest {

    @Test
    public void testBetweennessForOneVertex() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities2.txt");

        final Betweenness<Integer> betweenness = new Betweenness<>(graph, 5);

        Assert.assertEquals(2.0f, betweenness.between(5, 2), 0.1f);
        Assert.assertEquals(2.0f, betweenness.between(5, 3), 0.1f);
        Assert.assertEquals(4.0f, betweenness.between(5, 6), 0.1f);
        Assert.assertEquals(2.0f, betweenness.between(5, 9), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(2, 1), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(3, 1), 0.1f);
        Assert.assertEquals(2.0f, betweenness.between(6, 7), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(6, 10), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(9, 10), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(1, 4), 0.1f);
        Assert.assertEquals(0.5f, betweenness.between(7, 4), 0.1f);
        Assert.assertEquals(0.5f, betweenness.between(7, 11), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(10, 11), 0.1f);
        Assert.assertEquals(0.5f, betweenness.between(4, 8), 0.1f);
        Assert.assertEquals(0.5f, betweenness.between(11, 8), 0.1f);
    }

    @Test
    public void testBetweenness() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities.txt");

        final Betweenness<Integer> betweenness = new Betweenness<>(graph);

        Assert.assertEquals(3.0f, betweenness.between(2, 1), 0.1f);
        Assert.assertEquals(3.0f, betweenness.between(2, 4), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(1, 4), 0.1f);
        Assert.assertEquals(6.0f, betweenness.between(1, 3), 0.1f);
        Assert.assertEquals(6.0f, betweenness.between(4, 3), 0.1f);
        Assert.assertEquals(12.0f, betweenness.between(3, 5), 0.1f);
        Assert.assertEquals(5.0f, betweenness.between(5, 6), 0.1f);
        Assert.assertEquals(5.0f, betweenness.between(5, 7), 0.1f);
        Assert.assertEquals(1.0f, betweenness.between(6, 7), 0.1f);
    }
}
