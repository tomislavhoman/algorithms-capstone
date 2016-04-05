package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class DfsOrderingTest {

    private Graph<Integer> graph1;
    private Graph<Integer> graph2;
    private Graph<Integer> graph3;

    @Before
    public void setUp() {

        graph1 = new CapstoneGraph<>();
        graph2 = new CapstoneGraph<>();
        graph3 = new CapstoneGraph<>();

        GraphLoader.loadDirectedGraph(graph1, "./data/test_simple.txt");
        GraphLoader.loadDirectedGraph(graph2, "./data/test_circular.txt");
        GraphLoader.loadDirectedGraph(graph3, "./data/test_scc.txt");
    }

    @Test
    public void testSimple() {
        Assert.assertTrue(false);
    }

    @Test
    public void testCircular() {
        Assert.assertTrue(false);
    }

    @Test
    public void testScc() {
        Assert.assertTrue(false);
    }
}