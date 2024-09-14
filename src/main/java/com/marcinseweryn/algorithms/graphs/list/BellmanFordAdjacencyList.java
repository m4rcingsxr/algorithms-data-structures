package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * The {@code BellmanFordAdjacencyList} class represents a graph data structure using an adjacency list,
 * and provides methods to run the Bellman-Ford algorithm to find the shortest paths from a given starting
 * vertex to all other vertices in the graph.
 *
 * <p>The Bellman-Ford algorithm is a well-known algorithm for finding shortest paths in a weighted graph
 * (with both positive and negative weights). Unlike Dijkstra's algorithm, Bellman-Ford can handle graphs
 * with negative weight edges, making it suitable for a broader range of applications, such as detecting
 * negative weight cycles.
 *
 * <h2>Complexity</h2>
 * <ul>
 *   <li>Time Complexity: O(V * E), where V is the number of vertices and E is the number of edges.
 *       This is because, in the worst case, the algorithm will relax every edge (E) for each vertex (V - 1) times.</li>
 *   <li>Space Complexity: O(V) for the distance array used to store the shortest path estimates to each vertex.</li>
 * </ul>
 *
 * <h2>Applicable Graph Types</h2>
 * <ul>
 *   <li>Graphs with both positive and negative edge weights.</li>
 *   <li>Graphs where the shortest path may involve negative weight edges.</li>
 *   <li>Directed and undirected graphs. For undirected graphs, each edge should be added twice to simulate
 *       bidirectional edges.</li>
 *   <li>Graphs that may contain negative weight cycles (the algorithm can detect these cycles).</li>
 * </ul>
 *
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Finding shortest paths in graphs with negative weights, where Dijkstra's algorithm cannot be applied.</li>
 *   <li>Detecting negative weight cycles in financial models or networks where arbitrage is possible.</li>
 *   <li>Used in networking to find the shortest path with potentially variable link weights, such as in distance vector routing protocols.</li>
 *   <li>Suitable for educational purposes to understand the relaxation technique and graph traversal.</li>
 * </ul>
 *
 * <p>If a negative weight cycle is detected, the distance to vertices involved in the cycle will be set to negative infinity.</p>
 */
public class BellmanFordAdjacencyList {

    /**
     * The Edge class represents a weighted directed edge in the graph.
     */
    public static class Edge {
        double weight; // The weight of the edge
        int from; // The starting vertex of the edge
        int to; // The ending vertex of the edge

        /**
         * Constructs an edge with a specified weight, starting vertex, and ending vertex.
         *
         * @param weight the weight of the edge
         * @param from   the starting vertex of the edge
         * @param to     the ending vertex of the edge
         */
        public Edge(double weight, int from, int to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Creates a new adjacency list graph with the specified number of vertices.
     *
     * @param N the number of vertices in the graph
     * @return a list of adjacency lists representing the graph
     */
    public static List<List<Edge>> createGraph(final int N) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>()); // Initialize adjacency list for each vertex
        }
        return graph;
    }

    /**
     * Adds a directed edge with a specified weight from one vertex to another in the graph.
     *
     * @param graph  the graph to add the edge to
     * @param from   the starting vertex of the edge
     * @param to     the ending vertex of the edge
     * @param weight the weight of the edge
     */
    public static void addDirectedEdge(List<List<Edge>> graph, int from, int to, double weight) {
        graph.get(from).add(new Edge(weight, from, to)); // Add edge to the adjacency list of the starting vertex
    }

    /**
     * Runs the Bellman-Ford algorithm on the specified graph starting from the given vertex,
     * and returns an array of distances representing the shortest path from the starting vertex
     * to all other vertices in the graph.
     *
     * @param graph the graph to run the algorithm on
     * @param N     the number of vertices in the graph
     * @param start the starting vertex for the algorithm
     * @return an array of distances representing the shortest path from the starting vertex to all other vertices
     */
    public static double[] bellmanFord(List<List<Edge>> graph, int N, int start) {
        // Initialize the distance array with positive infinity, indicating all vertices are unreachable
        double[] distance = new double[N];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;  // Distance to the start vertex is zero

        // Relax all edges N-1 times to find shortest paths
        for (int i = 0; i < N - 1; i++) {
            for (List<Edge> edges : graph) {
                for (Edge edge : edges) {
                    if (distance[edge.from] + edge.weight < distance[edge.to]) {
                        distance[edge.to] = distance[edge.from] + edge.weight;
                    }
                }
            }
        }

        // Check for negative weight cycles by trying to relax edges one more time
        for (int i = 0; i < N - 1; i++) {
            for (List<Edge> edges : graph) {
                for (Edge edge : edges) {
                    if (distance[edge.from] + edge.weight < distance[edge.to]) {
                        distance[edge.to] = Double.NEGATIVE_INFINITY;
                    }
                }
            }
        }

        return distance;
    }

    public static void main(String[] args) {
        // Number of vertices in the graph
        final int N = 5;
        List<List<Edge>> graph = createGraph(N);

        // Add edges to the graph
        addDirectedEdge(graph, 0, 2, 6);
        addDirectedEdge(graph, 0, 3, 6);
        addDirectedEdge(graph, 1, 0, 3);
        addDirectedEdge(graph, 2, 3, 1);
        addDirectedEdge(graph, 3, 2, 2);
        addDirectedEdge(graph, 3, 1, 1);
        addDirectedEdge(graph, 4, 1, 4);
        addDirectedEdge(graph, 4, 3, 2);

        /*
          (0) --> 2(6) --> 3(6)
          (1) --> 0(3)
          (2) --> 3(1)
          (3) --> 2(2) --> 1(1)
          (4) --> 1(4) --> 3(2)
        */
        System.out.println("Running Bellman-Ford Algorithm (No Negative Cycle):");
        double[] distances = bellmanFord(graph, N, 4);

        System.out.println("Shortest path distances from vertex 4:");
        for (int i = 0; i < N; i++) {
            System.out.println("Distance to vertex " + i + ": " + distances[i]);
        }

        System.out.println("\nModifying the graph to include a negative weight cycle...");
        graph.get(0).get(0).weight = -6;

        /*
          (0) --> 2(-6) --> 3(6)
          (1) --> 0(3)
          (2) --> 3(1)
          (3) --> 2(2) --> 1(1)
          (4) --> 1(4) --> 3(2)
        */
        System.out.println("Running Bellman-Ford Algorithm (With Negative Cycle):");
        distances = bellmanFord(graph, N, 4);

        System.out.println("Shortest path distances from vertex 4 (with negative cycle detection):");
        for (int i = 0; i < N; i++) {
            if (distances[i] == Double.NEGATIVE_INFINITY) {
                System.out.println("Distance to vertex " + i + ": Negative Infinity (part of a negative weight cycle)");
            } else {
                System.out.println("Distance to vertex " + i + ": " + distances[i]);
            }
        }
    }

}
