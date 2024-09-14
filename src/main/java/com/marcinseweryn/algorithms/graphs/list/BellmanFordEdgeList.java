package com.marcinseweryn.algorithms.graphs.list;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * The BellmanFordEdgeList class provides an implementation of the Bellman-Ford algorithm
 * to find the shortest paths from a starting vertex to all other vertices in a weighted graph.
 * This algorithm can handle graphs with negative edge weights and is capable of detecting negative weight cycles.
 * The graph is represented using an edge list, which is a collection of all edges in the graph. This representation
 * is particularly useful for dynamically changing graphs where the number of edges is variable or when edge weights
 * frequently change.
 *
 * <h2>Key Features:</h2>
 * <ul>
 *   <li>Handles negative weight edges and detects negative weight cycles.</li>
 *   <li>Uses edge list representation to efficiently process edge relaxations.</li>
 *   <li>Runs in O(V * E) time complexity, where V is the number of vertices and E is the number of edges.</li>
 * </ul>
 *
 * <h2>Graph Representation:</h2>
 * <p>
 * This implementation uses an edge list to represent the graph. Each edge is represented by a tuple
 * containing a start vertex, an end vertex, and a weight. This allows the algorithm to easily iterate
 * over all edges for the relaxation process.
 * </p>
 *
 * <h2>Use Cases:</h2>
 * <ul>
 *   <li>Finding shortest paths in graphs with both positive and negative weights.</li>
 *   <li>Detecting negative weight cycles in graphs, which can indicate problems such as arbitrage in financial graphs.</li>
 *   <li>Useful in network routing algorithms where path weights can vary, such as in distance-vector routing protocols.</li>
 * </ul>
 *
 * <p>If a negative weight cycle is detected, the distance to vertices involved in the cycle will be set to negative infinity.</p>
 */
public class BellmanFordEdgeList {

    /**
     * Inner class representing an edge in the graph.
     * Each edge has a starting vertex ('from'), an ending vertex ('to'), and a 'weight'.
     */
    public static class Edge {
        int from;   // Starting vertex of the edge
        int to;     // Ending vertex of the edge
        double weight;  // Weight of the edge

        /**
         * Constructs an edge with a given starting vertex, ending vertex, and weight.
         *
         * @param from   the starting vertex of the edge
         * @param to     the ending vertex of the edge
         * @param weight the weight of the edge
         */
        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Finds the shortest path from a given starting vertex to all other vertices in the graph using the Bellman-Ford algorithm.
     * The algorithm is capable of handling graphs with negative edge weights but will detect and indicate if a negative weight cycle exists.
     *
     * @param edges an array of edges representing the graph
     * @param start the starting vertex for which to find the shortest paths to all other vertices
     * @param N     the number of vertices in the graph
     * @return an array of doubles representing the shortest distances from the starting vertex to all other vertices.
     * If a vertex is not reachable from the starting vertex, its distance is Double.POSITIVE_INFINITY.
     * If the graph contains a negative weight cycle, the distance for at least one vertex is Double.NEGATIVE_INFINITY.
     *
     * <p>
     * The Bellman-Ford algorithm relaxes all edges up to N-1 times to ensure the shortest paths are found,
     * as a path can have at most (V - 1) edges in a graph with V vertices.
     * Reference image explaining why N-1 iterations are necessary:
     * <a href="https://i.sstatic.net/sMv2f.jpg">Bellman-Ford Explanation</a>
     * </p>
     */
    public static double[] bellmanFord(Edge[] edges, int start, int N) {
        // Initialize distance array with positive infinity, indicating unreachable vertices
        double[] distance = new double[N];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;  // Distance to the start vertex is zero

        // Relax all edges N-1 times to ensure shortest paths are found
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                // Check if the current edge can provide a shorter path to edge.to
                if (distance[edge.from] + edge.weight < distance[edge.to]) {
                    distance[edge.to] = distance[edge.from] + edge.weight;
                }
            }
        }

        // Detect negative weight cycles by performing one more relaxation iteration
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.from] + edge.weight < distance[edge.to]) {
                    // If we can still relax an edge, then a negative weight cycle exists
                    distance[edge.to] = Double.NEGATIVE_INFINITY;
                }
            }
        }
        return distance;  // Return the shortest path distances or detection of a negative weight cycle
    }

    public static void main(String[] args) {
        final int E = 8; // Number of edges in the graph
        final int V = 5; // Number of vertices in the graph

        // Create the graph using an edge list representation
        Edge[] edges = new Edge[E];
        edges[0] = new Edge(0, 2, 6);
        edges[1] = new Edge(0, 3, 6);
        edges[2] = new Edge(1, 0, 3);
        edges[3] = new Edge(2, 3, 1);
        edges[4] = new Edge(3, 2, 2);
        edges[5] = new Edge(3, 1, 1);
        edges[6] = new Edge(4, 1, 4);
        edges[7] = new Edge(4, 3, 2);

        /*
         Edge List:
         (0) -> (2) weight: 6
         (0) -> (3) weight: 6
         (1) -> (0) weight: 3
         (2) -> (3) weight: 1
         (3) -> (2) weight: 2
         (3) -> (1) weight: 1
         (4) -> (1) weight: 4
         (4) -> (3) weight: 2
        */

        // Run Bellman-Ford algorithm from vertex 4
        System.out.println("Running Bellman-Ford Algorithm (No Negative Cycle):");
        double[] distances = bellmanFord(edges, 4, V);

        // Print distances after Bellman-Ford
        System.out.println("Shortest path distances from vertex 4:");
        for (int i = 0; i < V; i++) {
            System.out.println("Distance to vertex " + i + ": " + distances[i]);
        }

        // Modify the graph to introduce a negative cycle
        System.out.println("\nModifying the graph to include a negative weight cycle...");
        edges[1] = new Edge(0, 3, -6);

        /*
         Edge List:
         (0) -> (2) weight: 6
         (0) -> (3) weight: -6  (modified)
         (1) -> (0) weight: 3
         (2) -> (3) weight: 1
         (3) -> (2) weight: 2
         (3) -> (1) weight: 1
         (4) -> (1) weight: 4
         (4) -> (3) weight: 2
        */

        // Run Bellman-Ford algorithm again to detect the negative cycle
        System.out.println("Running Bellman-Ford Algorithm (With Negative Cycle):");
        distances = bellmanFord(edges, 4, V);

        // Print distances after Bellman-Ford with negative cycle
        System.out.println("Shortest path distances from vertex 4 (with negative cycle detection):");
        for (int i = 0; i < V; i++) {
            if (distances[i] == Double.NEGATIVE_INFINITY) {
                System.out.println("Distance to vertex " + i + ": Negative Infinity (part of a negative weight cycle)");
            } else {
                System.out.println("Distance to vertex " + i + ": " + distances[i]);
            }
        }
    }

}
