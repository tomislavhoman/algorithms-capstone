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

    @Test
    public void testAnalyseFacebook1000With100InitialA3B1() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 100, 3, 1);
    }

    @Test
    public void testAnalyseFacebook1000With1InitialA3B1() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 1, 3, 1);
    }

    @Test
    public void testAnalyseFacebook1000With100InitialA1B1() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 100, 1, 1);
    }

    @Test
    public void testAnalyseFacebook1000With1InitialA1B1() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 1, 1, 1);
    }

    @Test
    public void testAnalyseFacebook2000With100InitialA3B1() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 100, 3, 1);
    }

    @Test
    public void testAnalyseFacebook2000With1InitialA3B1() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 1, 3, 1);
    }

    @Test
    public void testAnalyseFacebook2000With100InitialA1B1() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 100, 1, 1);
    }

    @Test
    public void testAnalyseFacebook2000With1InitialA1B1() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 1, 1, 1);
    }

    @Test
    public void testAnalyseFacebookUcsdWith100InitialA3B1() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 100, 3, 1);
    }

    @Test
    public void testAnalyseFacebookUcsdWith1InitialA3B1() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 1, 3, 1);
    }

    @Test
    public void testAnalyseFacebookUcsdWith100InitialA1B1() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 100, 1, 1);
    }

    @Test
    public void testAnalyseFacebookUcsdWith1InitialA1B1() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 1, 1, 1);
    }

    private Graph<Integer> loadTestGraph() {
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_information_flow.txt");
        return graph;
    }

    private void analyse(final String graphName,
                         final String graphFile,
                         final int numberOfSources,
                         final int a,
                         final int b) {
        System.out.println("Analysing " + graphName);
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, graphFile);

        System.out.println(String.format("Graph has %d vertices and %d edges", graph.v(), graph.e()));
        System.out.println(String.format("Number of sources: %d, a: %d, b: %d", numberOfSources, a, b));

        final Set<Integer> sources = new HashSet<>();
        int numberOfAdded = 0;
        for (int vertex : graph) {
            sources.add(vertex);
            numberOfAdded++;

            if (numberOfAdded >= numberOfSources) {
                break;
            }
        }

        final long startTime = System.currentTimeMillis();
        final InformationFlow<Integer> informationFlow = new InformationFlow<>(graph, a, b, sources);
        final long endTime = System.currentTimeMillis();

        System.out.println(String.format("Information flow calculated in %d millis", (endTime - startTime)));
        if (informationFlow.reachedEquilibrium()) {
            System.out.println("Information flow has reached the equilibrium");
        } else {
            System.out.println("Information flow has not reached the equilibrium");
        }
        System.out.println(String.format("%d nodes have switched\n", informationFlow.switched().size()));
    }
}
