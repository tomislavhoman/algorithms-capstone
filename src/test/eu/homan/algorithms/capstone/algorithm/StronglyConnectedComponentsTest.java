package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.StronglyConnectedComponents;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

public class StronglyConnectedComponentsTest {

    @Test
    public void testSimpleDag() {

        final Graph<Integer> simpleDag = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(simpleDag, "./data/test_simple.txt");

        final StronglyConnectedComponents<Integer> scc = new StronglyConnectedComponents<>(simpleDag);

        Assert.assertEquals(4, scc.numberOfComponents());

        Assert.assertEquals(1, scc.componentFor(1));
        Assert.assertEquals(2, scc.componentFor(2));
        Assert.assertEquals(3, scc.componentFor(3));
        Assert.assertEquals(4, scc.componentFor(4));
    }

    @Test
    public void testScc() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(graph, "./data/test_scc.txt");

        final StronglyConnectedComponents<Integer> scc = new StronglyConnectedComponents<>(graph);

        Assert.assertEquals(4, scc.numberOfComponents());

        Assert.assertEquals(1, scc.componentFor(3));
        Assert.assertEquals(1, scc.componentFor(4));
        Assert.assertEquals(1, scc.componentFor(6));
        Assert.assertEquals(1, scc.componentFor(7));
        Assert.assertEquals(2, scc.componentFor(5));
        Assert.assertEquals(3, scc.componentFor(2));
        Assert.assertEquals(4, scc.componentFor(1));
    }
}