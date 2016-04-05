package test.eu.homan.algorithms.capstone.graph;

import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class CapstoneGraphTest {

    @Test
    public void testAddVertexAndEdge() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        Assert.assertEquals(0, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addVertex(0);

        Assert.assertEquals(1, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addVertex(0);

        Assert.assertEquals(1, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addVertex(1);

        Assert.assertEquals(2, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addEdge(0, 1);

        Assert.assertEquals(2, graph.v());
        Assert.assertEquals(1, graph.e());
    }

    @Test
    public void testTranspose() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(graph, "./data/test_simple.txt");

        final Graph<Integer> transposedGraph = new CapstoneGraph<>();
        transposedGraph.addVertex(1);
        transposedGraph.addVertex(2);
        transposedGraph.addVertex(3);
        transposedGraph.addVertex(4);
        transposedGraph.addEdge(2, 1);
        transposedGraph.addEdge(3, 2);
        transposedGraph.addEdge(4, 2);
        transposedGraph.addEdge(4, 3);

        Assert.assertEquals(transposedGraph, graph.transpose());
    }

    @Test
    public void testNeighbourhood() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(graph, "./data/test_scc.txt");

        final Queue<Integer> expectedNeighbourhoodOf3 = new LinkedList<>();
        expectedNeighbourhoodOf3.add(4);
        expectedNeighbourhoodOf3.add(6);
        expectedNeighbourhoodOf3.add(7);

        final Queue<Integer> expectedNeighbourhoodOf7 = new LinkedList<>();
        expectedNeighbourhoodOf7.add(4);
        expectedNeighbourhoodOf7.add(5);

        graph.adj(3).forEach(v -> Assert.assertEquals(expectedNeighbourhoodOf3.poll(), v));
        graph.adj(7).forEach(v -> Assert.assertEquals(expectedNeighbourhoodOf7.poll(), v));
    }

    @Test
    public void testIterating() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(graph, "./data/test_scc.txt");

        final Queue<Integer> allVertices = new LinkedList<>();
        allVertices.add(1);
        allVertices.add(2);
        allVertices.add(3);
        allVertices.add(4);
        allVertices.add(5);
        allVertices.add(6);
        allVertices.add(7);

        graph.forEach(v -> Assert.assertEquals(allVertices.poll(), v));
    }
}