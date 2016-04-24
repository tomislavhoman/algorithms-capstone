package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.InformationFlow;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class InformationFlowTest {


    @Test
    public void testSingleSourceEqualPrize() {

        final Graph<Integer> graph = loadTestGraph();
        final Set<Integer> sources = new HashSet<Integer>() {{
            add(3);
        }};
        final InformationFlow<Integer> informationFlow = new InformationFlow<>(graph, 1, 1, sources);

        final Set<Integer> expectedToSwitch = new HashSet<Integer>() {{
            add(3);
        }};

        Assert.assertTrue(informationFlow.reachedEquilibrium());
        Assert.assertEquals(expectedToSwitch, informationFlow.switched());
    }

    @Test
    public void testTwoSourcesEqualPrize() {
        final Graph<Integer> graph = loadTestGraph();
        final Set<Integer> sources = new HashSet<Integer>() {{
            add(3);
            add(7);
        }};
        final InformationFlow<Integer> informationFlow = new InformationFlow<>(graph, 1, 1, sources);

        final Set<Integer> expectedToSwitch = new HashSet<Integer>() {{
            add(3);
            add(4);
            add(6);
            add(7);
        }};

        Assert.assertTrue(informationFlow.reachedEquilibrium());
        Assert.assertEquals(expectedToSwitch, informationFlow.switched());
    }

    @Test
    public void testSingleSourceBiggerPrizeToSwitch() {
        final Graph<Integer> graph = loadTestGraph();
        final Set<Integer> sources = new HashSet<Integer>() {{
            add(3);
        }};
        final InformationFlow<Integer> informationFlow = new InformationFlow<>(graph, 3, 1, sources);

        final Set<Integer> expectedToSwitch = new HashSet<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
            add(6);
            add(7);
        }};

        Assert.assertTrue(informationFlow.reachedEquilibrium());
        Assert.assertEquals(expectedToSwitch, informationFlow.switched());
    }

    private Graph<Integer> loadTestGraph() {
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_information_flow.txt");
        return graph;
    }
}
