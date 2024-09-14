package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.Arrays;

/**
 * The {@code BellmanFord} class provides methods to compute the shortest paths
 * from a single source vertex to all other vertices in a weighted graph using
 * the Bellman-Ford algorithm. This algorithm is capable of handling graphs with
 * negative edge weights and can also detect negative weight cycles.
 *
 * <p>The Bellman-Ford algorithm is useful for graphs with negative weight edges
 * and can detect negative weight cycles, unlike Dijkstra's algorithm which cannot
 * handle negative weights and does not detect cycles.
 *
 * <h2>Complexity</h2>
 * <ul>
 *   <li>Time Complexity: O(V * E), where V is the number of vertices and E is
 *       the number of edges. This is due to the algorithm relaxing every edge
 *       (E) for each vertex (V - 1) times.</li>
 *   <li>Space Complexity: O(V) for the distance array used to store the shortest
 *       path estimates to each vertex.</li>
 * </ul>
 *
 * <h2>Applicable Graph Types</h2>
 * <ul>
 *   <li>Graphs with positive and negative edge weights.</li>
 *   <li>Graphs where the shortest path may involve negative weight edges.</li>
 *   <li>Directed graphs. For undirected graphs, each edge should be added twice
 *       to simulate bidirectional edges.</li>
 * </ul>
 *
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Finding shortest paths in graphs where edge weights can be negative.</li>
 *   <li>Detecting negative weight cycles in financial models, network routing
 *       protocols, or other applications where such cycles are relevant.</li>
 * </ul>
 */
public class BellmanFord {

    /**
     * The Bellman-Ford algorithm is used to find the shortest path from a
     * single source to all other vertices in a weighted graph.
     *
     * @param graph an adjacency matrix representation of the weighted graph
     * @param start the starting vertex from which to find the shortest paths
     * @return an array of doubles representing the shortest distance from
     * the start vertex to each vertex in the graph
     */
    public static double[] bellmanFord(Integer[][] graph, int start) {
        int N = graph.length;
        double[] distance = new double[N];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;
        for (int i = 0; i < N - 1; i++) {

            // all edges:
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (graph[j][k] != null
                            && distance[j] + graph[j][k] < distance[k]) {
                        distance[k] = distance[j] + graph[j][k];
                    }
                }
            }
        }

        return distance;
    }

    /**
     * Creates an empty adjacency matrix for a graph with a given number of
     * vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return an empty adjacency matrix for the graph
     */
    public static Integer[][] createGraph(int noVertices) {
        return new Integer[noVertices][noVertices];
    }

    /**
     * Adds a directed edge with a given weight between two vertices in an
     * adjacency matrix representation of a graph.
     *
     * @param graph  the adjacency matrix representation of the graph
     * @param from   the index of the vertex where the edge starts
     * @param to     the index of the vertex where the edge ends
     * @param weight the weight of the edge
     */
    public static void addDirectedEdge(Integer[][] graph, int from, int to,
                                       int weight) {
        graph[from][to] = weight;
    }

    public static void main(String[] args) {
        final int V = 5; // no vertices
        Integer[][] graph = createGraph(V);

        addDirectedEdge(graph, 0, 2, 6);
        addDirectedEdge(graph, 0, 3, 6);
        addDirectedEdge(graph, 1, 0, 3);
        addDirectedEdge(graph, 2, 3, 1);
        addDirectedEdge(graph, 3, 2, 2);
        addDirectedEdge(graph, 3, 1, 1);
        addDirectedEdge(graph, 4, 1, 4);
        addDirectedEdge(graph, 4, 3, 2);

        // [6.0, 3.0, 4.0, 2.0, 0.0]
        System.out.println(Arrays.toString(bellmanFord(graph, 4)));

    }

}
