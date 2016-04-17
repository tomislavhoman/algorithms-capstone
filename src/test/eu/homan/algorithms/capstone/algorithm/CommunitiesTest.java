package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.Communities;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CommunitiesTest {

    @Test
    public void testTwoCommunities() {

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities.txt");

        final Communities<Integer> communitiesAlgorithm = new Communities<>(graph, 2);
        Assert.assertEquals(2, communitiesAlgorithm.getNumberOfCommunities());

        final Map<Integer, Integer> communities = communitiesAlgorithm.getCommunities();
        Assert.assertEquals(1, (int) communities.get(1));
        Assert.assertEquals(1, (int) communities.get(2));
        Assert.assertEquals(1, (int) communities.get(3));
        Assert.assertEquals(1, (int) communities.get(4));
        Assert.assertEquals(2, (int) communities.get(5));
        Assert.assertEquals(2, (int) communities.get(6));
        Assert.assertEquals(2, (int) communities.get(7));
    }

    @Test
    public void testAnalyseUcsdWith10Communities() {
        //analyse("UCSD graph", "./data/facebook_ucsd.txt", 10);
    }

    @Test
    public void testAnalyseFacebook1000With10Communities() {
        analyse("Facebook 1000 graph", "./data/facebook_1000.txt", 10);
    }

    @Test
    public void testAnalyseFacebook2000With10Communities() {
        analyse("Facebook 2000 graph", "./data/facebook_2000.txt", 10);
    }

    private void analyse(final String graphName,
                         final String graphFile,
                         final int numberOfCommunities) {
        System.out.println("Analysing " + graphName);
        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, graphFile);

        System.out.println(String.format("Graph has %d vertices and %d edges", graph.v(), graph.e()));

        final long startTime = System.currentTimeMillis();
        final Communities<Integer> communities = new Communities<>(graph, numberOfCommunities);
        final long endTime = System.currentTimeMillis();

        System.out.println(String.format("Communities calculated in %d millis", (endTime - startTime)));
        System.out.println(String.format("Graph contains %d communities", communities.getNumberOfCommunities()));
    }
}
