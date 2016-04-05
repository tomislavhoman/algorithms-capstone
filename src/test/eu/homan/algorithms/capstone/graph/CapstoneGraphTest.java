package test.eu.homan.algorithms.capstone.graph;

import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

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
        transposedGraph.addEdge(4, 3);
        transposedGraph.addEdge(4, 2);

        Assert.assertEquals(transposedGraph, graph.transpose());
    }
}