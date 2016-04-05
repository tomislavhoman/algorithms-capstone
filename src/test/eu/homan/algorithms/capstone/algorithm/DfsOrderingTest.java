package test.eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.algorithm.DfsOrdering;
import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import eu.homan.algorithms.capstone.util.GraphLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class DfsOrderingTest {

    @Test
    public void testSimple() {

        final Graph<Integer> simpleGraph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(simpleGraph, "./data/test_simple.txt");

        final Queue<Integer> expectedPreOrder = new LinkedList<>();
        expectedPreOrder.add(1);
        expectedPreOrder.add(2);
        expectedPreOrder.add(3);
        expectedPreOrder.add(4);

        final Queue<Integer> expectedPostOrder = new LinkedList<>();
        expectedPostOrder.add(4);
        expectedPostOrder.add(3);
        expectedPostOrder.add(2);
        expectedPostOrder.add(1);

        final Queue<Integer> expectedReversePostOrder = new LinkedList<>();
        expectedReversePostOrder.add(1);
        expectedReversePostOrder.add(2);
        expectedReversePostOrder.add(3);
        expectedReversePostOrder.add(4);

        testOrdering(simpleGraph, 1, expectedPreOrder, expectedPostOrder, expectedReversePostOrder);
    }

    @Test
    public void testCircular() {
        final Graph<Integer> circularGraph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(circularGraph, "./data/test_circular.txt");

        final Queue<Integer> expectedPreOrder = new LinkedList<>();
        expectedPreOrder.add(1);
        expectedPreOrder.add(2);
        expectedPreOrder.add(3);
        expectedPreOrder.add(4);

        final Queue<Integer> expectedPostOrder = new LinkedList<>();
        expectedPostOrder.add(4);
        expectedPostOrder.add(3);
        expectedPostOrder.add(2);
        expectedPostOrder.add(1);

        final Queue<Integer> expectedReversePostOrder = new LinkedList<>();
        expectedReversePostOrder.add(1);
        expectedReversePostOrder.add(2);
        expectedReversePostOrder.add(3);
        expectedReversePostOrder.add(4);

        testOrdering(circularGraph, 1, expectedPreOrder, expectedPostOrder, expectedReversePostOrder);
    }

    @Test
    public void testScc() {
        final Graph<Integer> sccGraph = new CapstoneGraph<>();
        GraphLoader.loadDirectedGraph(sccGraph, "./data/test_scc.txt");

        final Queue<Integer> expectedPreOrder = new LinkedList<>();
        expectedPreOrder.add(7);
        expectedPreOrder.add(4);
        expectedPreOrder.add(3);
        expectedPreOrder.add(6);
        expectedPreOrder.add(5);
        expectedPreOrder.add(1);
        expectedPreOrder.add(2);

        final Queue<Integer> expectedPostOrder = new LinkedList<>();
        expectedPostOrder.add(6);
        expectedPostOrder.add(3);
        expectedPostOrder.add(4);
        expectedPostOrder.add(1);
        expectedPostOrder.add(2);
        expectedPostOrder.add(5);
        expectedPostOrder.add(7);

        final Queue<Integer> expectedReversePostOrder = new LinkedList<>();
        expectedReversePostOrder.add(7);
        expectedReversePostOrder.add(5);
        expectedReversePostOrder.add(2);
        expectedReversePostOrder.add(1);
        expectedReversePostOrder.add(4);
        expectedReversePostOrder.add(3);
        expectedReversePostOrder.add(6);

        testOrdering(sccGraph, 7, expectedPreOrder, expectedPostOrder, expectedReversePostOrder);
    }

    private void testOrdering(final Graph<Integer> graph,
                              final int s,
                              final Queue<Integer> expectedPreOrder,
                              final Queue<Integer> expectedPostOrder,
                              final Queue<Integer> expectedReversePostOrder) {

        final DfsOrdering<Integer> dfsOrdering = new DfsOrdering<>(graph, s);
        final Iterable<Integer> preOrder = dfsOrdering.preOrder();
        final Iterable<Integer> postOrder = dfsOrdering.postOrder();
        final Iterable<Integer> reversePostOrder = dfsOrdering.reversePostOrder();

        preOrder.forEach(v -> Assert.assertEquals(expectedPreOrder.poll(), v));
        postOrder.forEach(v -> Assert.assertEquals(expectedPostOrder.poll(), v));
        reversePostOrder.forEach(v -> Assert.assertEquals(expectedReversePostOrder.poll(), v));
    }
}