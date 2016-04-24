package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.GreedyMinimumDominantSet;
import eu.homan.algorithms.capstone.algorithm.InformationFlow;
import eu.homan.algorithms.capstone.algorithm.MinimumDominantSet;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class InformationFlowFromInfluencers {

    @Test
    public void testAnalyseFacebook1000With100InitialA3B1Influencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 100, 3, 1, true);
    }

    @Test
    public void testAnalyseFacebook1000With1InitialA3B1Influencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 1, 3, 1, true);
    }

    @Test
    public void testAnalyseFacebook1000With100InitialA1B1Influencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 100, 1, 1, true);
    }

    @Test
    public void testAnalyseFacebook1000With1InitialA1B1Influencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 1, 1, 1, true);
    }

    @Test
    public void testAnalyseFacebook2000With100InitialA3B1Influencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 100, 3, 1, true);
    }

    @Test
    public void testAnalyseFacebook2000With1InitialA3B1Influencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 1, 3, 1, true);
    }

    @Test
    public void testAnalyseFacebook2000With100InitialA1B1Influencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 100, 1, 1, true);
    }

    @Test
    public void testAnalyseFacebook2000With1InitialA1B1Influencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 1, 1, 1, true);
    }

    @Test
    public void testAnalyseFacebookUcsdWith100InitialA3B1Influencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 100, 3, 1, true);
    }

    @Test
    public void testAnalyseFacebookUcsdWith1InitialA3B1Influencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 1, 3, 1, true);
    }

    @Test
    public void testAnalyseFacebookUcsdWith100InitialA1B1Influencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 100, 1, 1, true);
    }

    @Test
    public void testAnalyseFacebookUcsdWith1InitialA1B1Influencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 1, 1, 1, true);
    }

    @Test
    public void testAnalyseFacebook1000With100InitialA3B1NotInfluencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 100, 3, 1, false);
    }

    @Test
    public void testAnalyseFacebook1000With1InitialA3B1NotInfluencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 1, 3, 1, false);
    }

    @Test
    public void testAnalyseFacebook1000With100InitialA1B1NotInfluencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 100, 1, 1, false);
    }

    @Test
    public void testAnalyseFacebook1000With1InitialA1B1NotInfluencers() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 1, 1, 1, false);
    }

    @Test
    public void testAnalyseFacebook2000With100InitialA3B1NotInfluencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 100, 3, 1, false);
    }

    @Test
    public void testAnalyseFacebook2000With1InitialA3B1NotInfluencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 1, 3, 1, false);
    }

    @Test
    public void testAnalyseFacebook2000With100InitialA1B1NotInfluencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 100, 1, 1, false);
    }

    @Test
    public void testAnalyseFacebook2000With1InitialA1B1NotInfluencers() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 1, 1, 1, false);
    }

    @Test
    public void testAnalyseFacebookUcsdWith100InitialA3B1NotInfluencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 100, 3, 1, false);
    }

    @Test
    public void testAnalyseFacebookUcsdWith1InitialA3B1NotInfluencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 1, 3, 1, false);
    }

    @Test
    public void testAnalyseFacebookUcsdWith100InitialA1B1NotInfluencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 100, 1, 1, false);
    }

    @Test
    public void testAnalyseFacebookUcsdWith1InitialA1B1NotInfluencers() {
        analyse("Facebook UCSD graph", "./data/facebook_ucsd.txt", 1, 1, 1, false);
    }

    private void analyse(final String graphName,
                         final String graphFile,
                         final int numberOfSources,
                         final int a,
                         final int b,
                         final boolean startFromInfluencers) {
        System.out.println("Analysing " + graphName);
        final Graph<Integer> graph = new CapstoneGraph<>();

        long startTime = System.currentTimeMillis();
        GraphLoader.loadUndirectedGraph(graph, graphFile);
        long endTime = System.currentTimeMillis();
        System.out.println(String.format("Graph loaded in %d milliseconds", endTime - startTime));
        System.out.println(String.format("Graph has %d vertices and %d edges", graph.v(), graph.e()));
        System.out.println(String.format("Number of sources: %d, a: %d, b: %d", numberOfSources, a, b));

        startTime = System.currentTimeMillis();
        final MinimumDominantSet<Integer> minimumDominantSet = new GreedyMinimumDominantSet<>(graph);
        endTime = System.currentTimeMillis();
        System.out.println(String.format("Influencers found in %d milliseconds", endTime - startTime));

        final Set<Integer> influencers = minimumDominantSet.get();
        System.out.println(String.format("Graph has %d influencers", influencers.size()));

        final Set<Integer> sources = new HashSet<>();
        int numberOfAdded = 0;
        for (int vertex : graph) {

            if (startFromInfluencers && influencers.contains(vertex)) {
                sources.add(vertex);
                numberOfAdded++;
            } else if (!startFromInfluencers && !influencers.contains(vertex)) {
                sources.add(vertex);
                numberOfAdded++;
            }

            if (numberOfAdded >= numberOfSources) {
                break;
            }
        }

        if (startFromInfluencers) {
            System.out.println("Starting information flow from influencers");
        } else {
            System.out.println("Starting information flow from nodes that are not influencers");
        }

        startTime = System.currentTimeMillis();
        final InformationFlow<Integer> informationFlow = new InformationFlow<>(graph, a, b, sources);
        endTime = System.currentTimeMillis();

        System.out.println(String.format("Information flow calculated in %d millis", endTime - startTime));
        if (informationFlow.reachedEquilibrium()) {
            System.out.println("Information flow has reached the equilibrium");
        } else {
            System.out.println("Information flow has not reached the equilibrium");
        }
        System.out.println(String.format("%d nodes have switched", informationFlow.switched().size()));
        System.out.println(String.format("Equilibrium reached in %d steps\n", informationFlow.getNumberOfSteps()));
    }
}
