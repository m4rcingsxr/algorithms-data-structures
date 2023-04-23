package com.marcinseweryn.algorithms.graphs.list;

import java.util.Arrays;

import static java.lang.System.out;

public class BellmanFordEdgeList {

    public static class Edge {
        int from;
        int to;
        double weight;

        public Edge(int from, int to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Finds the shortest path from a given starting vertex to all other
     * vertices in the graph using the Bellman-Ford algorithm.
     * The graph is represented by an array of edges, and the number of
     * vertices is given by the parameter N.
     * The algorithm can handle graphs with negative edge weights but must
     * not have negative weight cycles. If the graph
     * contains a negative weight cycle, the algorithm can detect it and
     * return a negative infinity value for at least one
     * vertex distance.
     *
     * @param edges an array of edges representing the graph
     * @param start the starting vertex for which to find the shortest paths
     *              to all other vertices
     * @param N     the number of vertices in the graph
     * @return an array of doubles representing the shortest distances from
     * the starting vertex to all other vertices.
     *
     * If a vertex is not reachable from the starting vertex, its distance is
     * Double.POSITIVE_INFINITY. If the graph contains a negative weight cycle,
     * the distance for at least one vertex is Double.NEGATIVE_INFINITY.
     */
    public static double[] bellmanFord(Edge[] edges, int start, int N) {
        double[] distance = new double[N];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;

        // Vertices - 1, does not matter in what order we process edges
        // at the end we finish with the same result
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.from] + edge.weight < distance[edge.to]) {
                    distance[edge.to] = distance[edge.from] + edge.weight;
                }
            }
        }

        // Check if vertices are getting relaxed
        for (int i = 0; i < N - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.from] + edge.weight < distance[edge.to]) {
                    distance[edge.to] = Double.NEGATIVE_INFINITY;
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        final int E = 8; // no edges
        final int V = 5; // no vertices

        Edge[] edges = new Edge[E];
        edges[0] = new Edge(0, 2, 6);
        edges[1] = new Edge(0, 3, 6);
        edges[2] = new Edge(1, 0, 3);
        edges[3] = new Edge(2, 3, 1);
        edges[4] = new Edge(3, 2, 2);
        edges[5] = new Edge(3, 1, 1);
        edges[6] = new Edge(4, 1, 4);
        edges[7] = new Edge(4, 3, 2);

        out.println("Present Bellman Ford without negative cycle: ");
        for (int i = 0; i < V; i++) {
            out.print(i + "   ");
        }
        out.println();
        Arrays.stream(bellmanFord(edges, 4, V)).forEach(e -> out.print(e + " "
        ));

        /* OUTPUT:
            Present Bellman Ford without negative cycle:
            0   1   2   3   4
            6.0 3.0 4.0 2.0 0.0
         */
        out.println();

        // Negative cycle
        edges[1] = new Edge(0, 3, -6);
        out.println("Present Bellman Ford WITH negative cycle: ");
        for (int i = 0; i < V; i++) {
            out.print(i + "   ");
        }
        out.println();
        Arrays.stream(bellmanFord(edges, 4, V)).forEach(e -> out.print(e + " "
        ));

        /* OUTPUT:
            Present Bellman Ford WITH negative cycle:
            0   1   2   3   4
            -Infinity -Infinity -Infinity -Infinity 0.0
         */
    }
}
