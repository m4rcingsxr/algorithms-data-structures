package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * The BellmanFordAdjacencyList class represents a graph data structure using an
 * adjacency list, and provides methods to run the Bellman-Ford algorithm on
 * this graph to find the shortest paths from a given starting vertex to all
 * other vertices in the graph.
 */
public class BellmanFordAdjacencyList {

    /**
     * The Edge class represents a weighted directed edge in the graph.
     */
    public static class Edge {
        double weight;
        int from;
        int to;

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
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Runs the Bellman-Ford algorithm on the specified graph starting from
     * the given vertex, and returns an array of distances representing the
     * shortest path from the starting vertex to all other vertices in the
     * graph.
     *
     * @param graph the graph to run the algorithm on
     * @param N     the number of vertices in the graph
     * @param start the starting vertex for the algorithm
     * @return an array of distances representing the shortest path from the
     * starting vertex to all other vertices in the graph
     */
    public static double[] bellmanFord(List<List<Edge>> graph, int N,
                                       int start) {
        double[] distance = new double[N];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;

        // Max path from start vertex is N-1
        // Apply relaxation for all the edges
        for (int i = 0; i < N - 1; i++) {

            // 2 loops here indicates all edges
            for (List<Edge> edges : graph) {
                for (Edge edge : edges) {
                    if (distance[edge.from] + edge.weight < distance[edge.to]) {
                        distance[edge.to] = distance[edge.from] + edge.weight;
                    }
                }
            }
        }

        // Run algorithm one more time to detect negative cycles.
        // A negative cycle appear if algorithm find the better path
        // beyond already computed solution
        // Algorithm will assign negative infinity if vertex is a
        // part of negative cycle
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

    /**
     * Creates a new edge with the specified weight and endpoints.
     *
     * @param weight the weight of the edge
     * @param from   the starting vertex of the edge
     * @param to     the ending vertex of the edge
     */
    public static void addDirectedEdge(List<List<Edge>> graph, int from,
                                       int to, double weight) {
        graph.get(from).add(new Edge(weight, from, to));
    }

    public static void main(String[] args) {

        // No negative cycle
        final int N = 5;
        List<List<Edge>> graph = createGraph(N);
        addDirectedEdge(graph, 0, 2, 6);
        addDirectedEdge(graph, 0, 3, 6);
        addDirectedEdge(graph, 1, 0, 3);
        addDirectedEdge(graph, 2, 3, 1);
        addDirectedEdge(graph, 3, 2, 2);
        addDirectedEdge(graph, 3, 1, 1);
        addDirectedEdge(graph, 4, 1, 4);
        addDirectedEdge(graph, 4, 3, 2);

        out.println("Present Bellman Ford without negative cycle: ");
        for (int i = 0; i < N; i++) {
            out.print(i + "   ");
        }
        out.println();
        Arrays.stream(bellmanFord(graph, N, 4)).forEach(e -> out.print(e + " "
        ));

        /*  OUTPUT:
            Present Bellman Ford without negative cycle:
            0   1   2   3   4
            6.0 3.0 4.0 2.0 0.0
         */

        out.println();
        graph.get(0).get(0).weight = -6;
        out.println("Present Bellman Ford WITH negative cycle: ");
        for (int i = 0; i < N; i++) {
            out.print(i + "   ");
        }
        out.println();
        Arrays.stream(bellmanFord(graph, N, 4)).forEach(e -> out.print(e + " "
        ));

        /*  OUTPUT:
            Present Bellman Ford WITH negative cycle:
            0   1   2   3   4
            -Infinity -Infinity -Infinity -Infinity 0.0
         */
    }

}
