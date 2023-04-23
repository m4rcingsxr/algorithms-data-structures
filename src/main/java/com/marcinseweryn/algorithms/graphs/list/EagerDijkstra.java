package com.marcinseweryn.algorithms.graphs.list;

import com.marcinseweryn.algorithms.datastructures.priorityqueue.IndexedPriorityQueue;

import java.util.*;

/**
 * This class implements Dijkstra's shortest path algorithm using an eager
 * version of the algorithm.
 * <p>
 * It provides a method to create a graph, add directed edges, and to compute
 * the shortest distance to all nodes in the graph from a specified start node.
 * <p>
 * The time complexity of the methods is as follows:
 * <ul>
 * <li>createGraph: O(V), where V is the number of vertices in the graph </li>
 * <li>addDirectedEdge: O(1) </li>
 * <li>dijkstra: O(E * log(V)), where E is the number of edges in the graph
 * and V
 * is the number of vertices in the graph. This is due to the use of an
 * indexed priority queue forselecting the next node to visit during the
 * algorithm, which takes O(log(V)) time to insert, decrease, and  poll the
 * minimum value. </li>
 * </ul>
 * <p>
 */
public class EagerDijkstra {

    public static class Edge {
        int to;
        double weight;

        public Edge(int to, double weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    /**
     * Represents a node in the graph, used for keeping track of the shortest
     * distance to each node during the algorithm.
     */
    private static class Node implements Comparable<Node> {
        int index;
        double distance;

        public Node(int index, double distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (distance - o.distance);
        }
    }

    private static class Result {
        Integer[] path;
        double distance;
        int start, end;

        public Result(int start, int end, Integer[] path, double distance) {
            this.path = path;
            this.distance = distance;
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object obj) {
            return Objects.equals(path, obj);
        }

        @Override
        public String toString() {
            if (path.length == 0) {
                return String.format(
                        "Path from %d to %d does not exist",
                        start, end
                );
            }
            return String.format(
                    "Single Source Shortest Path from vertex: %d to vertex: " +
                            "%d is: %s with distance: %d",
                    start, end, Arrays.toString(path), (int) distance
            );
        }
    }

    /**
     * Creates an empty graph with the specified number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return an empty graph with the specified number of vertices
     */
    public static List<List<Edge>> createGraph(int noVertices) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Adds a directed edge with the specified weight from the 'from' vertex
     * to the 'to' vertex in the graph.
     *
     * @param graph  the graph to add the edge to
     * @param from   the index of the vertex the edge starts from
     * @param to     the index of the vertex the edge goes to
     * @param weight the weight of the edge
     */
    public static void addDirectedEdge(List<List<Edge>> graph, int from,
                                       int to, double weight) {
        graph.get(from).add(new Edge(to, weight));
    }

    /**
     * Computes the shortest distance to all nodes in the graph from the
     * specified start node using Dijkstra's algorithm.
     *
     * @param graph the graph to compute the shortest distances in
     * @param start the index of the start node
     * @return an array containing the shortest distance to each node in the
     * graph from the start node
     */
    public static double[] dijkstra(List<List<Edge>> graph, int start) {
        return dijkstra(graph, start, new Integer[graph.size()]);
    }


    private static double[] dijkstra(List<List<Edge>> graph, int start,
                                     Integer[] parent) {
        int N = graph.size();
        boolean[] visited = new boolean[N];
        double[] distance = new double[N];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);
        IndexedPriorityQueue<Node> ipq =
                new IndexedPriorityQueue<>(2, N);
        distance[start] = 0;
        ipq.insert(start, new Node(start, 0));

        while (!ipq.isEmpty()) {
            Node current = ipq.pollMinValue();
            visited[current.index] = true;

            List<Edge> neighbors = graph.get(current.index);
            for (Edge neighbor : neighbors) {
                double newDistance = current.distance + neighbor.weight;
                int neighborIndex = neighbor.to;

                if (!visited[neighborIndex] &&
                        distance[neighborIndex] > newDistance) {
                    distance[neighborIndex] = newDistance;
                    parent[neighborIndex] = current.index;

                    if (ipq.contains(neighborIndex)) {
                        ipq.decrease(neighborIndex,
                                     new Node(neighborIndex, newDistance)
                        );
                    } else {
                        ipq.insert(neighborIndex,
                                   new Node(neighborIndex, newDistance)
                        );
                    }
                }
            }
        }

        return distance;
    }


    public static Result reconstructPath(List<List<Edge>> graph, int start,
                                         int end) {
        Integer[] parent = new Integer[graph.size()];
        double[] distance = dijkstra(graph, start, parent);

        Integer[] path = reconstructPath(graph, parent, start, end);
        return new Result(start, end, path, distance[end]);
    }

    private static Integer[] reconstructPath(List<List<Edge>> graph,
                                             Integer[] parent,
                                             int start, int end) {
        List<Integer> path = new ArrayList<>();
        for (Integer i = end; i != null; i = parent[i]) {
            path.add(i);
        }
        Collections.reverse(path);
        if (path.get(0) != start) return new Integer[0];

        return path.toArray(new Integer[0]);
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

        System.out.println(Arrays.toString(dijkstra(graph, 0)));
        System.out.println(reconstructPath(graph, 0, 0));
        System.out.println(reconstructPath(graph, 0, 1));
        System.out.println(reconstructPath(graph, 0, 2));
        System.out.println(reconstructPath(graph, 0, 3));
        System.out.println(reconstructPath(graph, 0, 4));
        System.out.println(reconstructPath(graph, 0, 5));
        System.out.println(reconstructPath(graph, 0, 6));

        /*
            [0.0, 2.0, 5.0, 3.0, 5.0, 13.0, 14.0]
            Single Source Shortest Path from vertex: 0 to vertex: 0 is: [0] with distance: 0
            Single Source Shortest Path from vertex: 0 to vertex: 1 is: [0, 1] with distance: 2
            Single Source Shortest Path from vertex: 0 to vertex: 2 is: [0, 2] with distance: 5
            Single Source Shortest Path from vertex: 0 to vertex: 3 is: [0, 1, 3] with distance: 3
            Single Source Shortest Path from vertex: 0 to vertex: 4 is: [0, 1, 4] with distance: 5
            Single Source Shortest Path from vertex: 0 to vertex: 5 is: [0, 2, 5] with distance: 13
            Single Source Shortest Path from vertex: 0 to vertex: 6 is: [0, 1, 4, 6] with distance: 14
         */
    }
}