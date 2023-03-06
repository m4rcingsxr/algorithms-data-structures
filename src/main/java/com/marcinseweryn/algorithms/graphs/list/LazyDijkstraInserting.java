package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

public class LazyDijkstraInserting {

    private static class Edge {
        int next;
        int from;
        double weight;

        public Edge(int from, int next, double weight) {
            this.next = next;
            this.from = from;
            this.weight = weight;
        }

    }

    private static class Node implements Comparable<Node> {
        int id;
        double value;

        public Node(int id, double value) {
            this.id = id;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.value - o.value);
        }
    }

    private LazyDijkstraInserting() {
        // No instantiable
    }

    public static List<List<Edge>> createEmptyGraph(int size) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static void addDirectedEdge(List<List<Edge>> graph, int from, int next, double weight) {
        graph.get(from).add(new Edge(from, next, weight));
    }

    private static double[] dijkstra(List<List<Edge>> graph, int start, Integer[] previous) {
        boolean[] visited = new boolean[graph.size()];
        double[] distance = new double[graph.size()];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        distance[start] = 0;

        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.add(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            visited[current.id] = true;

            // Already found better path routing through other nodes
            // before we process this node
            // Lazy implementation contain duplicate key-value pairs
            // Key is index, value is the shortest path
            if (distance[current.id] < current.value) {
                continue;
            }
            List<Edge> edges = graph.get(current.id);
            for (Edge edge : edges) {

                // If vertex is already visited then it mean
                // we cannot process smaller value to this index
                // Visited vertex -> the shortest path (no negative cycles)
                if (visited[edge.next]) {
                    continue;
                }

                // Compute path
                double newDist = distance[current.id] + edge.weight;

                // If computed distance is smaller than already
                // existed distance(it might be already updated through other vertices)
                // then we can update it
                if (newDist < distance[edge.next]) {
                    distance[edge.next] = newDist;

                    // Set shortest path parent of vertex
                    previous[edge.next] = current.id;

                    // Add to pq updated vertex -> consider it as the shortest path
                    // it is more efficient to insert a new key-value - O(log(N)) - inserting
                    // pair than it is to update and existing key's value(O(N)) - requires searching
                    queue.add(new Node(edge.next, newDist));
                }
            }
        }
        return distance;
    }

    // Return array with the shortest path between the given vertex and
    // all others vertices
    public static double[] dijkstra(List<List<Edge>> graph, int start) {
        return dijkstra(graph, start, new Integer[graph.size()]);
    }

    // Reconstruct the shortest path between 2 specific vertices
    public static List<Integer> findShortestPath(List<List<Edge>> graph, int start, int end) {
        Integer[] previous = new Integer[graph.size()];
        double[] distance = dijkstra(graph, start, previous);
        List<Integer> path = new ArrayList<>();

        // No possible path exist to end vertex from start vertex
        if (distance[end] == Double.POSITIVE_INFINITY) {
            return path;
        }

        // add parent until current is equal to null
        // If we reach null that means we reach start vertex
        for (Integer i = end; i != null; i = previous[i]) {
            path.add(i);
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        final int N = 7;
        List<List<Edge>> graph = createEmptyGraph(N);

        //              (3)
        //         > 1 ----> 5
        //    (2) /  |\(1)     \(9)
        //       /   | \        \
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

        double[] distance = dijkstra(graph, 0);
        System.out.println("The shortest path for vertex(" + 0 + "):");
        for (int i = 0; i < distance.length; i++) {
            if (i == distance.length - 1) {
                System.out.println(i);
            } else if(distance[i] >= 10){
                System.out.print(i + " ".repeat(4));
            } else {
                System.out.print(i + " ".repeat(3));

            }
        }
        Arrays.stream(distance).forEach(e -> System.out.print(e + " "));

        /*  OUTPUT:
            0   1   2   3   4   5    6
            0.0 2.0 5.0 3.0 5.0 13.0 14.0
         */

        List<Integer> shortestPath = findShortestPath(graph,0, 6);
        System.out.print("\nThe shortest path from 0 to 6 [");
        shortestPath.forEach(e -> System.out.print(e + " "));
        System.out.print("]");
    }

}
