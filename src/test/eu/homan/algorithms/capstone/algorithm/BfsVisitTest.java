package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.BfsVisit;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsVisitTest {

    @Test
    public void testBfs() {

        final Queue<Integer> visitingOrder = new LinkedList<>();
        visitingOrder.add(5);
        visitingOrder.add(2);
        visitingOrder.add(3);
        visitingOrder.add(6);
        visitingOrder.add(9);
        visitingOrder.add(1);
        visitingOrder.add(7);
        visitingOrder.add(10);
        visitingOrder.add(4);
        visitingOrder.add(11);
        visitingOrder.add(8);

        final Graph<Integer> graph = new CapstoneGraph<>();
        GraphLoader.loadUndirectedGraph(graph, "./data/test_communities2.txt");

        final BfsVisit<Integer> bfsVisit = new BfsVisit<>(graph);
        bfsVisit.start(5, visited -> Assert.assertEquals(visitingOrder.poll(), visited));

        final Map<Integer, Integer> immediatePathsTo = bfsVisit.getNumberOfImmediatePathsTo();
        Assert.assertEquals((Integer) 0, immediatePathsTo.get(5));
        Assert.assertEquals((Integer) 1, immediatePathsTo.get(2));
        Assert.assertEquals((Integer) 1, immediatePathsTo.get(3));
        Assert.assertEquals((Integer) 1, immediatePathsTo.get(6));
        Assert.assertEquals((Integer) 1, immediatePathsTo.get(9));
        Assert.assertEquals((Integer) 2, immediatePathsTo.get(1));
        Assert.assertEquals((Integer) 1, immediatePathsTo.get(7));
        Assert.assertEquals((Integer) 2, immediatePathsTo.get(10));
        Assert.assertEquals((Integer) 2, immediatePathsTo.get(4));
        Assert.assertEquals((Integer) 2, immediatePathsTo.get(11));
        Assert.assertEquals((Integer) 2, immediatePathsTo.get(8));

        final Map<Integer, Integer> totalPathsTo = bfsVisit.getNumberOfTotalPathsTo();
        Assert.assertEquals((Integer) 0, totalPathsTo.get(5));
        Assert.assertEquals((Integer) 1, totalPathsTo.get(2));
        Assert.assertEquals((Integer) 1, totalPathsTo.get(3));
        Assert.assertEquals((Integer) 1, totalPathsTo.get(6));
        Assert.assertEquals((Integer) 1, totalPathsTo.get(9));
        Assert.assertEquals((Integer) 2, totalPathsTo.get(1));
        Assert.assertEquals((Integer) 1, totalPathsTo.get(7));
        Assert.assertEquals((Integer) 2, totalPathsTo.get(10));
        Assert.assertEquals((Integer) 3, totalPathsTo.get(4));
        Assert.assertEquals((Integer) 3, totalPathsTo.get(11));
        Assert.assertEquals((Integer) 6, totalPathsTo.get(8));

        final Map<Integer, List<Integer>> pathsTo = bfsVisit.getPathsTo();
        Assert.assertEquals(null, pathsTo.get(5));
        Assert.assertEquals((Integer) 5, pathsTo.get(2).get(0));
        Assert.assertEquals((Integer) 5, pathsTo.get(3).get(0));
        Assert.assertEquals((Integer) 5, pathsTo.get(6).get(0));
        Assert.assertEquals((Integer) 5, pathsTo.get(9).get(0));
        Assert.assertEquals((Integer) 2, pathsTo.get(1).get(0));
        Assert.assertEquals((Integer) 3, pathsTo.get(1).get(1));
        Assert.assertEquals((Integer) 6, pathsTo.get(7).get(0));
        Assert.assertEquals((Integer) 6, pathsTo.get(10).get(0));
        Assert.assertEquals((Integer) 9, pathsTo.get(10).get(1));
        Assert.assertEquals((Integer) 1, pathsTo.get(4).get(0));
        Assert.assertEquals((Integer) 7, pathsTo.get(4).get(1));
        Assert.assertEquals((Integer) 7, pathsTo.get(11).get(0));
        Assert.assertEquals((Integer) 10, pathsTo.get(11).get(1));
        Assert.assertEquals((Integer) 4, pathsTo.get(8).get(0));
        Assert.assertEquals((Integer) 11, pathsTo.get(8).get(1));

        final Map<Integer, List<Integer>> goesTo = bfsVisit.getGoesTo();
        Assert.assertEquals((Integer) 2, goesTo.get(5).get(0));
        Assert.assertEquals((Integer) 3, goesTo.get(5).get(1));
        Assert.assertEquals((Integer) 6, goesTo.get(5).get(2));
        Assert.assertEquals((Integer) 9, goesTo.get(5).get(3));
        Assert.assertEquals((Integer) 1, goesTo.get(2).get(0));
        Assert.assertEquals((Integer) 1, goesTo.get(3).get(0));
        Assert.assertEquals((Integer) 7, goesTo.get(6).get(0));
        Assert.assertEquals((Integer) 10, goesTo.get(6).get(1));
        Assert.assertEquals((Integer) 10, goesTo.get(9).get(0));
        Assert.assertEquals((Integer) 4, goesTo.get(1).get(0));
        Assert.assertEquals((Integer) 4, goesTo.get(7).get(0));
        Assert.assertEquals((Integer) 11, goesTo.get(7).get(1));
        Assert.assertEquals((Integer) 11, goesTo.get(10).get(0));
        Assert.assertEquals((Integer) 8, goesTo.get(4).get(0));
        Assert.assertEquals((Integer) 8, goesTo.get(11).get(0));

        final Map<Integer, Integer> layerOf = bfsVisit.getLayers();
        Assert.assertEquals((Integer) 1, layerOf.get(5));
        Assert.assertEquals((Integer) 2, layerOf.get(2));
        Assert.assertEquals((Integer) 2, layerOf.get(3));
        Assert.assertEquals((Integer) 2, layerOf.get(6));
        Assert.assertEquals((Integer) 2, layerOf.get(9));
        Assert.assertEquals((Integer) 3, layerOf.get(1));
        Assert.assertEquals((Integer) 3, layerOf.get(7));
        Assert.assertEquals((Integer) 3, layerOf.get(10));
        Assert.assertEquals((Integer) 4, layerOf.get(4));
        Assert.assertEquals((Integer) 4, layerOf.get(11));
        Assert.assertEquals((Integer) 5, layerOf.get(8));

        final Queue<Integer> reverseLayerOrder = new LinkedList<>();
        reverseLayerOrder.add(8);
        reverseLayerOrder.add(11);
        reverseLayerOrder.add(4);
        reverseLayerOrder.add(10);
        reverseLayerOrder.add(7);
        reverseLayerOrder.add(1);
        reverseLayerOrder.add(9);
        reverseLayerOrder.add(6);
        reverseLayerOrder.add(3);
        reverseLayerOrder.add(2);
        reverseLayerOrder.add(5);
        bfsVisit.reverseLayerOrder().forEach(
                v -> Assert.assertEquals(reverseLayerOrder.poll(), v));
    }
}
