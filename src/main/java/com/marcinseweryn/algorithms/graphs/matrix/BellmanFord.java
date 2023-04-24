package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.Arrays;

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
