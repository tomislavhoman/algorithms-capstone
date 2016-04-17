package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.ConnectedComponents;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class ConnectedComponentsTest {

    @Test
    public void testOneComponent() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities.txt");

        final Map<Integer, Integer> connectedComponents =
                new ConnectedComponents<>(graph).components();

        Assert.assertEquals(1, (int) connectedComponents.get(1));
        Assert.assertEquals(1, (int) connectedComponents.get(2));
        Assert.assertEquals(1, (int) connectedComponents.get(3));
        Assert.assertEquals(1, (int) connectedComponents.get(4));
        Assert.assertEquals(1, (int) connectedComponents.get(5));
        Assert.assertEquals(1, (int) connectedComponents.get(6));
        Assert.assertEquals(1, (int) connectedComponents.get(7));
    }

    @Test
    public void testTwoComponents() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities.txt");
        graph.removeEdge(3, 5);
        graph.removeEdge(5, 3);

        final Map<Integer, Integer> connectedComponents =
                new ConnectedComponents<>(graph).components();

        Assert.assertEquals(1, (int) connectedComponents.get(1));
        Assert.assertEquals(1, (int) connectedComponents.get(2));
        Assert.assertEquals(1, (int) connectedComponents.get(3));
        Assert.assertEquals(1, (int) connectedComponents.get(4));
        Assert.assertEquals(2, (int) connectedComponents.get(5));
        Assert.assertEquals(2, (int) connectedComponents.get(6));
        Assert.assertEquals(2, (int) connectedComponents.get(7));
    }

    @Test
    public void testMultipleComponents() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities.txt");
        graph.removeEdge(3, 5);
        graph.removeEdge(5, 3);
        graph.removeEdge(5, 6);
        graph.removeEdge(6, 5);
        graph.removeEdge(6, 7);
        graph.removeEdge(7, 6);

        final Map<Integer, Integer> connectedComponents =
                new ConnectedComponents<>(graph).components();

        Assert.assertEquals(1, (int) connectedComponents.get(1));
        Assert.assertEquals(1, (int) connectedComponents.get(2));
        Assert.assertEquals(1, (int) connectedComponents.get(3));
        Assert.assertEquals(1, (int) connectedComponents.get(4));
        Assert.assertEquals(2, (int) connectedComponents.get(5));
        Assert.assertEquals(3, (int) connectedComponents.get(6));
        Assert.assertEquals(2, (int) connectedComponents.get(7));
    }
}
