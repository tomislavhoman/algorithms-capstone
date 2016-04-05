package test.eu.homan.algorithms.capstone.util;

import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import junit.framework.Assert;
import org.junit.Test;

public class GraphLoaderTest {

    @Test
    public void testLoadEdgesAndVertices() {

        final Graph directedGraph = new CapstoneGraph();
        final Graph undirectedGraph = new CapstoneGraph();
        GraphLoader.loadDirectedGraph(directedGraph, "./data/test_graph.txt");
        GraphLoader.loadUndirectedGraph(undirectedGraph, "./data/test_graph.txt");

        Assert.assertEquals(6, directedGraph.v());
        Assert.assertEquals(7, directedGraph.e());

        Assert.assertEquals(6, undirectedGraph.v());
        Assert.assertEquals(14, undirectedGraph.e());
    }

    @Test
    public void testLoadDirectedGraph() {

        final Graph expectedGraph = new CapstoneGraph();
        expectedGraph.addVertex(0);
        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        expectedGraph.addVertex(3);
        expectedGraph.addVertex(4);
        expectedGraph.addVertex(5);
        expectedGraph.addEdge(0, 1);
        expectedGraph.addEdge(1, 2);
        expectedGraph.addEdge(2, 3);
        expectedGraph.addEdge(3, 4);
        expectedGraph.addEdge(2, 5);
        expectedGraph.addEdge(5, 4);
        expectedGraph.addEdge(3, 3);

        final Graph graph = new CapstoneGraph();
        GraphLoader.loadDirectedGraph(graph, "./data/test_graph.txt");

        Assert.assertEquals(expectedGraph, graph);
    }

    @Test
    public void testLoadUndirectedGraph() {

        final Graph expectedGraph = new CapstoneGraph();
        expectedGraph.addVertex(0);
        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        expectedGraph.addVertex(3);
        expectedGraph.addVertex(4);
        expectedGraph.addVertex(5);
        expectedGraph.addEdge(0, 1);
        expectedGraph.addEdge(1, 0);
        expectedGraph.addEdge(1, 2);
        expectedGraph.addEdge(2, 1);
        expectedGraph.addEdge(2, 3);
        expectedGraph.addEdge(3, 2);
        expectedGraph.addEdge(3, 4);
        expectedGraph.addEdge(4, 3);
        expectedGraph.addEdge(2, 5);
        expectedGraph.addEdge(5, 2);
        expectedGraph.addEdge(5, 4);
        expectedGraph.addEdge(4, 5);
        expectedGraph.addEdge(3, 3);
        expectedGraph.addEdge(3, 3);

        final Graph graph = new CapstoneGraph();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_graph.txt");

        Assert.assertEquals(expectedGraph, graph);
    }
}