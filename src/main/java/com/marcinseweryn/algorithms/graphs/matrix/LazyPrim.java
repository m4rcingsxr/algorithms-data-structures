package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The {@code LazyPrim} class implements Prim's algorithm for finding the Minimum Spanning Tree (MST) of a graph
 * represented by an adjacency matrix. This implementation uses a priority queue (min-heap) to efficiently
 * select the minimum weight edge at each step of the algorithm.
 *
 * <p>The graph is represented as an adjacency matrix where the cell at index [i][j] contains the weight of
 * the edge from vertex i to vertex j. If there is no edge, the weight is represented as -1.</p>
 *
 * <h2>Time Complexity</h2>
 * <p>The time complexity of the Lazy Prim's algorithm is O(E log V), where E is the number of edges and V is the number of vertices.
 * This is because each edge is added and removed from the priority queue at most once, and each operation on the priority queue takes
 * O(log V) time.</p>
 *
 * <h2>Space Complexity</h2>
 * <p>The space complexity is O(V + E). This includes space for the priority queue, the visited array, and the MST edges array.</p>
 */
public class LazyPrim {

    /**
     * An inner class representing an Edge in the graph.
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
            return System.lineSeparator() +
                    "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    /**
     * An inner class representing the result of running the Prim's algorithm.
     */
    public static class MST {
        Edge[] mstEdges;
        int mstCost;

        public MST(Edge[] mstEdges, int mstCost) {
            this.mstEdges = mstEdges;
            this.mstCost = mstCost;
        }

        @Override
        public String toString() {
            if (mstEdges.length == 0) {
                return "MST does not exist";
            }
            Arrays.sort(mstEdges, Comparator.comparingInt(e -> e.from));
            return String.format(
                    "Minimum Spanning Tree%nCost:[%d]%nEdges:%s",
                    mstCost, Arrays.toString(mstEdges)
            );
        }
    }

    /**
     * Creates a new adjacency matrix for a graph with the specified number
     * of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return a new adjacency matrix
     */
    public static int[][] createGraph(int noVertices) {
        int[][] graph = new int[noVertices][noVertices];
        for (int[] ints : graph) {
            for (int i = 0; i < ints.length; i++) {
                ints[i] = -1;
            }
        }
        return graph;
    }

    /**
     * Adds a directed edge to the adjacency matrix representing the graph.
     *
     * @param graph  the adjacency matrix representing the graph
     * @param from   the source vertex of the edge
     * @param to     the target vertex of the edge
     * @param weight the weight of the edge
     */
    public static void addDirectedEdge(int[][] graph, int from, int to,
                                       int weight) {
        graph[from][to] = weight;
    }

    /**
     * This method implements the Lazy Prim's algorithm to find the minimum
     * spanning tree of a given graph represented by its adjacency matrix.
     *
     * @param graph the adjacency matrix representation of the graph.
     * @return a Result object containing the edges of the minimum spanning
     * tree and its total weight, or an empty edge array and -1 if no minimum
     * spanning tree exists.
     */
    public static MST prim(int[][] graph) {
        int N = graph.length;
        int maxPath = N - 1;
        boolean[] visited = new boolean[N];
        Edge[] mstEdges = new Edge[maxPath];
        int mstSum = 0;
        int mstSize = 0;
        java.util.PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < graph[0].length; i++) {
            if (graph[0][i] > -1) {
                pq.add(new Edge(0, i, graph[0][i]));
            }
        }
        visited[0] = true;

        while (!pq.isEmpty() && mstSize != maxPath) {
            Edge current = pq.poll();
            int nodeIndex = current.to;

            // Lazy removi
            // ng outdated edges
            if (visited[nodeIndex]) continue;
            mstEdges[mstSize++] = current;
            visited[nodeIndex] = true;
            mstSum += current.weight;
            for (int i = 0; i < graph[nodeIndex].length; i++) {
                if (!visited[i] && graph[nodeIndex][i] > -1) {
                    pq.add(new Edge(nodeIndex, i, graph[nodeIndex][i]));
                }
            }
        }

        if (mstSize != maxPath) {
            return new MST(new Edge[0], -1);
        }
        return new MST(mstEdges, mstSum);
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
        int[][] g = createGraph(n);
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
        System.out.println(prim(g));
    }


}
