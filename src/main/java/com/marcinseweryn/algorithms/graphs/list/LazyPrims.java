package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

/**
 * The LazyPrims class provides a static method for computing the Minimum
 * Spanning Tree of an undirected weighted graph using the Lazy Prim's
 * algorithm. The class also contains utility methods for creating and
 * modifying a graph represented as an adjacency list.
 */
public class LazyPrims {

    private LazyPrims() {
    }

    /**
     * The Edge class represents a weighted edge in a graph.
     * <p>
     * It implements the Comparable interface to allow for sorting in a
     * priority queue.
     */
    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            return System.lineSeparator() + "Edge{" + "from=" + from + ", " + "to=" + to + ", weight=" + weight + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to && weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }

    /**
     * The Result class represents the result of the Prim's algorithm for
     * computing the     Minimum Spanning Tree of a graph.     It contains
     * the total weight of the MST and an array of Edge objects representing
     * the MST.
     */
    public static class Result {

        private final int mstSum;
        private final Edge[] mst;

        public Result(int mstSum, Edge[] mst) {
            this.mstSum = mstSum;
            this.mst = mst;
        }

        @Override
        public String toString() {
            if (mstSum == -1) {
                return "Minimum Spanning Tree does not exist";
            }
            Arrays.sort(mst, Comparator.comparingInt(e -> e.to));
            return String.format("Minimum Spanning Tree:%nEdges:%s%nSum:%d",
                                 Arrays.toString(mst), mstSum
            );
        }
    }

    /**
     * Runs Prim's algorithm on the provided graph to find the minimum
     * spanning tree.
     *
     * @param graph the graph to find the minimum spanning tree of
     * @return a Result object containing the sum of the weights of the minimum
     * spanning tree     and an array of Edge objects representing the edges
     * in the
     * minimum spanning tree   (null if the graph is disconnected)
     */
    public static Result prims(List<List<Edge>> graph) {
        int mstSize = 0;
        int mstSum = 0;

        //number of edges in MST - maximum path from vertex
        // a to vertex b is V - 1
        int maxPath = graph.size() - 1;
        Edge[] mst = new Edge[maxPath];
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        addEdges(graph, pq, 0, visited);

        while (!pq.isEmpty() && mstSize != maxPath) {
            Edge current = pq.poll();
            int nodeIndex = current.to;

            // skip edge that points to a visited node
            if (visited[nodeIndex]) {
                continue;
            }

            mst[mstSize++] = current;
            mstSum += current.weight;
            addEdges(graph, pq, nodeIndex, visited);
        }
        if (mstSize != maxPath) {
            return new Result(-1, null);
        }

        return new Result(mstSum, mst);
    }

    /**
     * Adds edges to the provided priority queue from the specified index in
     * the graph, skipping edges that point to already visited nodes.
     *
     * @param graph   the graph to add edges from
     * @param pq      the priority queue to add edges to
     * @param index   the index of the node to add edges from
     * @param visited a boolean array indicating whether each node has been
     *                visited
     */
    private static void addEdges(List<List<Edge>> graph, PriorityQueue<Edge> pq,
                                 int index, boolean[] visited) {
        visited[index] = true;
        List<Edge> neighbors = graph.get(index);
        for (Edge neighbor : neighbors) {
            if (!visited[neighbor.to]) {
                pq.offer(neighbor);
            }
        }
    }

    /**
     * Creates a graph with the specified number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return a List of Lists representing the graph (initially empty)
     */
    public static List<List<Edge>> createGraph(int noVertices) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Adds a directed edge with the specified weight from the "from" vertex
     * to the "to" vertex in the provided graph.
     *
     * @param graph  the graph to add the edge to
     * @param from   the index of the "from" vertex
     * @param to     the index of the "to" vertex
     * @param weight the weight of the edge
     */
    private static void addDirectedEdge(List<List<Edge>> graph, int from,
                                        int to, int weight) {
        graph.get(from).add(new Edge(from, to, weight));
    }

    /**
     * Adds an undirected edge with the specified weight between the "from"
     * vertex and the "to" vertex
     * in the provided graph.
     *
     * @param graph  the graph to add the edge to
     * @param from   the index of the first vertex
     * @param to     the index of the second vertex
     * @param weight the weight of the edge
     */
    public static void addUndirectedEdge(List<List<Edge>> graph, int from,
                                         int to, int weight) {
        addDirectedEdge(graph, from, to, weight);
        addDirectedEdge(graph, to, from, weight);
    }

    public static void main(String[] args) {

        //                    (0)
        //                1 ------- 4
        //          (10)/ |(3)   (1)| \(8)
        //             0--2-------- 5-----7
        //             \(1)|  (8)  /| (9)/
        //          (4) \  |(2) /(2)|   / (12)
        //               \ | /      |  /
        //                3 ------- 6
        //                    (7)
        int n = 8;
        List<List<Edge>> g = createGraph(n);

        addDirectedEdge(g, 0, 1, 10);
        addDirectedEdge(g, 0, 2, 1);
        addDirectedEdge(g, 0, 3, 4);

        addDirectedEdge(g, 2, 1, 3);
        addDirectedEdge(g, 2, 5, 8);
        addDirectedEdge(g, 2, 3, 2);
        addDirectedEdge(g, 2, 0, 1);

        addDirectedEdge(g, 3, 2, 2);
        addDirectedEdge(g, 3, 5, 2);
        addDirectedEdge(g, 3, 6, 7);
        addDirectedEdge(g, 3, 0, 4);

        addDirectedEdge(g, 5, 2, 8);
        addDirectedEdge(g, 5, 4, 1);
        addDirectedEdge(g, 5, 7, 9);
        addDirectedEdge(g, 5, 6, 6);
        addDirectedEdge(g, 5, 3, 2);

        addDirectedEdge(g, 4, 1, 0);
        addDirectedEdge(g, 4, 5, 1);
        addDirectedEdge(g, 4, 7, 8);

        addDirectedEdge(g, 1, 0, 10);
        addDirectedEdge(g, 1, 2, 3);
        addDirectedEdge(g, 1, 4, 0);

        addDirectedEdge(g, 6, 3, 7);
        addDirectedEdge(g, 6, 5, 6);
        addDirectedEdge(g, 6, 7, 12);

        addDirectedEdge(g, 7, 4, 8);
        addDirectedEdge(g, 7, 5, 9);
        addDirectedEdge(g, 7, 6, 12);
        System.out.println(prims(g));

        /*
        Minimum Spanning Tree:
        Edges:[
        Edge{from=4, to=1, weight=0},
        Edge{from=0, to=2, weight=1},
        Edge{from=2, to=3, weight=2},
        Edge{from=5, to=4, weight=1},
        Edge{from=3, to=5, weight=2},
        Edge{from=5, to=6, weight=6},
        Edge{from=4, to=7, weight=8}]
        Sum:20
         */
    }
}
