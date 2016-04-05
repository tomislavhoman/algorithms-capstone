package eu.homan.algorithms.capstone.algorithm;

import eu.homan.algorithms.capstone.graph.Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public final class DfsOrdering<T> {

    private final Queue<T> preOrder = new LinkedList<>();
    private final Queue<T> postOrder = new LinkedList<>();
    private final Stack<T> reversePostOrderStack = new Stack<>();
    private final Queue<T> reversePostOrder = new LinkedList<>();

    public DfsOrdering(final Graph<T> graph) {

        final DfsVisit<T> dfs = new DfsVisit<>(graph);

        dfs.start(
                preOrder::add,
                v -> {
                    postOrder.add(v);
                    reversePostOrderStack.add(v);
                });

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
}
