package com.marcinseweryn.algorithms.graphs.matrix;

import static java.lang.Math.min;

/**
 * The FloydWarshall class provides a static implementation of the Floyd-Warshall
 * algorithm for finding the shortest path between all pairs of vertices in a
 * weighted graph with or without negative cycles.
 */
public class FloydWarshall {

    /**
     * Applies the Floyd-Warshall algorithm to the given graph.
     *
     * @param graph the weighted graph, represented as a 2D array of doubles
     * @return a 2D array of doubles representing the shortest path between all pairs of vertices in the graph
     * @throws IllegalStateException if the graph contains a negative cycle
     */
    public static double[][] floydWarshall(double[][] graph) {
        final int N = graph.length;

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        if (containsNegativeCycle(graph)) {
            throw new IllegalStateException("Graph contains negative cycle!");
        }

        return graph;
    }

    /**
     * Checks if the given graph contains a negative cycle.
     *
     * @param graph the weighted graph, represented as a 2D array of doubles
     * @return true if the graph contains a negative cycle, false otherwise
     */
    private static boolean containsNegativeCycle(double[][] graph) {

        // Check the diagonal elements of the distance
        // matrix for negative values.
        for (int i = 0; i < graph.length; i++) {

            // If a negative value is found in the diagonal,
            // there is a negative cycle in the graph.
            if (graph[i][i] < 0.0) {
                return true;
            }

        }

        // If no negative value is found, there
        // is no negative cycle in the graph.
        return false;
    }

    /**
     * Adds a directed weighted edge to the given graph.
     *
     * @param graph  the weighted graph, represented as a 2D array of doubles
     * @param from   the source vertex
     * @param to     the destination vertex
     * @param weight the weight of the edge
     */
    public static void addDirectedWeightedEdge(double[][] graph, int from, int to, int weight) {
        graph[from][to] = weight;
    }

    /**
     * Creates an empty weighted graph with the given number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return a 2D array of doubles representing the empty graph
     */
    public static double[][] createGraph(final int noVertices) {
        double[][] graph = new double[noVertices][noVertices];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                if (i == j) {
                    graph[i][j] = 0.0;
                } else {
                    graph[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }
        return graph;
    }

    /**
     * Prints the shortest path between all pairs of vertices in the given graph.
     *
     * @param graph the weighted graph, represented as a 2D array of doubles
     */
    public static void printFloydWarshall(double[][] graph) {
        for (int i = 0; i < graph.length; i++) {
            System.out.println("------------------Shortest paths for [" + i + "]------------------");
            for (int j = 0; j < graph[i].length; j++) {
                if (graph[i][j] != Double.POSITIVE_INFINITY) {
                    System.out.printf("Shortest path from %d to %d is:[%d]%n", i, j, (int) graph[i][j]);
                } else {
                    System.out.printf("Path from %d to %d does not exist%n", i, j);
                }
            }
        }
    }

    public static void main(String[] args) {

        /*         (7)
            0 -----------> 2
            |  \          /\
            |    \         |
         (2)|   (6) \      |
            |        \     |(1)
            |          \   |
            |           \  |
            \/           \>|
            1 ----> 3 ---> 4
               (2)    (1)
         */
        double[][] graph = createGraph(5);
        addDirectedWeightedEdge(graph, 0, 1, 2);
        addDirectedWeightedEdge(graph, 0, 2, 7);
        addDirectedWeightedEdge(graph, 0, 4, 6);
        addDirectedWeightedEdge(graph, 1, 3, 2);
        addDirectedWeightedEdge(graph, 3, 4, 1);
        addDirectedWeightedEdge(graph, 4, 2, 1);

        floydWarshall(graph);
        printFloydWarshall(graph);

        /* NEGATIVE CYCLE, exception will be thrown
                (7)
            0 -----------> 2
            |  <\          /\
            |    \         |
         (2)|  (-6) \      |
            |        \     |(1)
            |          \   |
            |           \  |
            \/           \|
            1 ----> 3 ---> 4
               (2)    (1)
         */
        double[][] negativeCycleGraph = createGraph(5);
        addDirectedWeightedEdge(negativeCycleGraph, 0, 1, 2);
        addDirectedWeightedEdge(negativeCycleGraph, 0, 2, 7);
        addDirectedWeightedEdge(negativeCycleGraph, 4, 0, -6);
        addDirectedWeightedEdge(negativeCycleGraph, 1, 3, 2);
        addDirectedWeightedEdge(negativeCycleGraph, 3, 4, 1);
        addDirectedWeightedEdge(negativeCycleGraph, 4, 2, 1);
        floydWarshall(negativeCycleGraph);
    }

}
