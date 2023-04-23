package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

/**
 * This class provides a lazy implementation of Dijkstra's algorithm to find
 * the shortest path between two vertices in a graph.
 */
public class LazyDijkstra {

    /**
     * Represents an edge in the graph, containing the weight and the ID of
     * the next vertex.
     */
    public static class Edge {
        int weight;
        int next;

        public Edge(int next, int weight) {
            this.weight = weight;
            this.next = next;
        }
    }

    /**
     * Represents a pair of a vertex ID and a value, used to maintain a
     * priority queue of vertices to visit during Dijkstra's algorithm.
     */
    private static class Pair {
        int id;
        double value;

        public Pair(int id, double value) {
            this.id = id;
            this.value = value;
        }

    }

    private static class Result {
        List<Integer> path;
        double distance;
        int start, end;

        public Result(List<Integer> path, double distance, int start, int end) {
            this.path = path;
            this.distance = distance;
            this.start = start;
            this.end = end;
        }

        public String toString() {
            return String.format(
                    "Shortest path from %d to %d:%s, with distance: %d",
                    start, end, path.toString(), (int) distance
            );
        }
    }

    private LazyDijkstra() {
        // Private constructor to prevent instantiation of this class
    }

    /**
     * Creates an empty graph with the specified number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return a list of empty lists representing the graph
     */
    public static List<List<Edge>> createGraph(int noVertices) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Adds a directed edge to the graph with the specified weight,
     * originating from the 'from' vertex and terminating at the 'to' vertex.
     *
     * @param graph  the graph to add the edge to
     * @param from   the vertex that the edge originates from
     * @param to     the vertex that the edge terminates at
     * @param weight the weight of the edge
     */
    public static void addDirectedEdge(List<List<Edge>> graph, int from,
                                       int to, int weight) {
        graph.get(from).add(new Edge(to, weight));
    }

    /**
     * Computes the shortest path between the start vertex and all other
     * vertices in the graph using Dijkstra's algorithm.
     *
     * @param graph    the graph to compute the shortest path on
     * @param start    the starting vertex
     * @param previous an array to store the previous vertex in the shortest
     *                 path to each vertex
     * @return an array containing the shortest path to each vertex from the
     * start vertex
     */
    private static Double[] dijkstra(List<List<Edge>> graph, int start,
                                     Integer[] previous) {
        boolean[] visited = new boolean[graph.size()];
        Double[] distance = new Double[graph.size()];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0.0;
        PriorityQueue<Pair> queue = new PriorityQueue<>(
                Comparator.comparingDouble(e -> distance[e.id]));
        queue.add(new Pair(start, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.remove();
            visited[current.id] = true;

            // If we have already found a shorter path to this node, skip it
            if (distance[current.id] < current.value) {
                continue;
            }

            List<Edge> neighbors = graph.get(current.id);
            for (Edge edge : neighbors) {

                // If the vertex has already been visited, we cannot process
                // a smaller value to it
                if (visited[edge.next]) {
                    continue;
                }

                // Compute path
                double newDist = distance[current.id] + edge.weight;

                // If computed distance is smaller than already
                // existed distance(it might be already updated through other
                // vertices)
                // then we can update it
                if (newDist < distance[edge.next]) {
                    distance[edge.next] = newDist;

                    // Set shortest path parent of vertex
                    previous[edge.next] = current.id;

                    // Add to pq updated vertex -> consider it as the shortest
                    // path it is more efficient to insert a new key-value -
                    // O(log(N)) - inserting pair than it is to update and
                    // existing key's value(O(N)) - requires searching
                    queue.add(new Pair(edge.next, newDist));
                }
            }
        }
        return distance;
    }

    /**
     * Compute the shortest path from a starting vertex to all other vertices
     * in a given graph using Dijkstra's algorithm
     *
     * @param graph the graph to compute the shortest paths on
     * @param start the starting vertex
     * @return an array of the shortest distances from the starting vertex to
     * all other vertices
     */
    public static Double[] dijkstra(List<List<Edge>> graph, int start) {
        return dijkstra(graph, start, new Integer[graph.size()]);
    }

    /**
     * Finds the shortest path between two vertices in a graph using
     * Dijkstra's algorithm.
     *
     * @param graph the graph represented as a list of adjacency lists
     * @param start the starting vertex
     * @param end   the ending vertex
     * @return the shortest path result containing the path as a list of
     * vertices,
     */
    public static Result findShortestPath(List<List<Edge>> graph,
                                          int start, int end) {
        Integer[] previous = new Integer[graph.size()];
        Double[] distance = dijkstra(graph, start, previous);
        List<Integer> path = new ArrayList<>();

        // No possible path exist to end vertex from start vertex
        if (distance[end] == Double.POSITIVE_INFINITY) {
            return null;
        }

        // add parent until current is equal to null
        // If we reach null that means we reach start vertex
        for (Integer i = end; i != null; i = previous[i]) {
            path.add(i);
        }
        Collections.reverse(path);
        return new Result(path, distance[end], start, end);
    }

    public static void main(String[] args) {
        final int N = 7;
        List<List<Edge>> graph = createGraph(N);

        //              (3)
        //         > 1 ----> 4
        //    (2) /  |\(1) />  \(9)
        //       /   | \  /(4)   \
        //     0     |  >3        > 6
        //      \ (6)|          /
        //    (5)\   >         /(7)
        //        >  2 -----> 5
        //              (8)
        addDirectedEdge(graph, 0, 1, 2);
        addDirectedEdge(graph, 0, 2, 5);
        addDirectedEdge(graph, 1, 4, 3);
        addDirectedEdge(graph, 1, 3, 1);
        addDirectedEdge(graph, 1, 2, 6);
        addDirectedEdge(graph, 2, 5, 8);
        addDirectedEdge(graph, 3, 4, 4);
        addDirectedEdge(graph, 4, 6, 9);
        addDirectedEdge(graph, 5, 6, 7);

        /*
            [0.0, 2.0, 5.0, 3.0, 5.0, 13.0, 14.0]
            Shortest path from 0 to 6:[0, 1, 4, 6], with distance: 14
         */
        Double[] distance = dijkstra(graph, 0);
        System.out.println(Arrays.toString(distance));
        System.out.println(findShortestPath(graph, 0, 6));
    }

}
