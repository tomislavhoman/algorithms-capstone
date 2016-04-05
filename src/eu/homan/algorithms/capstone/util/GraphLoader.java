/**
 * @author UCSD MOOC development team
 * <p>
 * Utility class to add vertices and edges to a graph
 */
package eu.homan.algorithms.capstone.util;

import eu.homan.algorithms.capstone.graph.Graph;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GraphLoader {

    /**
     * Loads undirected graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex. It is represented as directed graph with
     * 2 directed edges for every edge
     */
    public static void loadUndirectedGraph(Graph g, String filename) {
        loadGraph(g, filename, true);

    }

    /**
     * Loads directed graph with data from a file.
     * The file should consist of lines with 2 integers each, corresponding
     * to a "from" vertex and a "to" vertex.
     */
    public static void loadDirectedGraph(Graph g, String filename) {
        loadGraph(g, filename, false);
    }

    private static void loadGraph(Graph g, String filename, boolean undirected) {
        Set<Integer> seen = new HashSet<>();
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Iterate over the lines in the file, adding new
        // vertices as they are found and connecting them with edges.
        while (sc.hasNextInt()) {
            int v1 = sc.nextInt();
            int v2 = sc.nextInt();
            if (!seen.contains(v1)) {
                g.addVertex(v1);
                seen.add(v1);
            }
            if (!seen.contains(v2)) {
                g.addVertex(v2);
                seen.add(v2);
            }
            g.addEdge(v1, v2);

            if (undirected) {
                g.addEdge(v2, v1);
            }
        }

        sc.close();
    }
}
