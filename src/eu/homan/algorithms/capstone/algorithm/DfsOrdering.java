package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public final class DfsOrdering<T> {

    private final Queue<T> preOrder = new LinkedList<>();
    private final Queue<T> postOrder = new LinkedList<>();
    private final Stack<T> reversePostOrderStack = new Stack<>();
    private final Queue<T> reversePostOrder = new LinkedList<>();

    public DfsOrdering(final Graph<T> graph, final T s) {

        HashSet<T> visited = new HashSet<>();
        dfsVisit(graph, s, visited);

        while (!reversePostOrderStack.empty()) {
            reversePostOrder.add(reversePostOrderStack.pop());
        }
    }

    public Iterable<T> preOrder() {
        return preOrder;
    }

    public Iterable<T> postOrder() {
        return postOrder;
    }

    public Iterable<T> reversePostOrder() {
        return reversePostOrder;
    }

    private void dfsVisit(final Graph<T> graph, final T v, final HashSet<T> visited) {

        preOrder.add(v);
        visited.add(v);

        graph.adj(v).forEach(neighbour -> {
            if (!visited.contains(neighbour)) {
                dfsVisit(graph, neighbour, visited);
            }
        });

        postOrder.add(v);
        reversePostOrderStack.add(v);
    }
}
