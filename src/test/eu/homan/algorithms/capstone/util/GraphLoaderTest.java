package test.eu.homan.algorithms.capstone.util;

import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import junit.framework.Assert;
import org.junit.Test;

public class GraphLoaderTest {

    @Test
    public void testLoadGraph() {

        final Graph graph = new CapstoneGraph();
        GraphLoader.loadGraph(graph, "./data/test_graph.txt");

        Assert.assertEquals(6, graph.v());
        Assert.assertEquals(5, graph.e());
    }
}