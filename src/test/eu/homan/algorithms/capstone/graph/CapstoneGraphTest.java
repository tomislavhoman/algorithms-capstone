package test.eu.homan.algorithms.capstone.graph;

import eu.homan.algorithms.capstone.graph.CapstoneGraph;
import eu.homan.algorithms.capstone.graph.Graph;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CapstoneGraphTest {

    @Test
    public void testAddVertexAndEdge() {

        final Graph graph = new CapstoneGraph();
        Assert.assertEquals(0, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addVertex(0);

        Assert.assertEquals(1, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addVertex(0);

        Assert.assertEquals(1, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addVertex(1);

        Assert.assertEquals(2, graph.v());
        Assert.assertEquals(0, graph.e());

        graph.addEdge(0, 1);

        Assert.assertEquals(2, graph.v());
        Assert.assertEquals(1, graph.e());
    }

    @Test
    public void testTranspose() {

        /*
               +-----+
               |     v
            0->1->2->3
         */
        final Graph graph = new CapstoneGraph();
        graph.addVertex(0);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 3);

        /*
               +-----+
               v     |
            0<-1<-2<-3
         */
        final Graph transposedGraph = new CapstoneGraph();
        transposedGraph.addVertex(0);
        transposedGraph.addVertex(1);
        transposedGraph.addVertex(2);
        transposedGraph.addVertex(3);
        transposedGraph.addEdge(1, 0);
        transposedGraph.addEdge(2, 1);
        transposedGraph.addEdge(3, 2);
        transposedGraph.addEdge(3, 1);

        Assert.assertEquals(transposedGraph, graph.transpose());
    }
}