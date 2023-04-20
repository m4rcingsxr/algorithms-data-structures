package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * The TopologicalSortStack class provides methods to perform topological sorting
 * on a directed acyclic graph using a stack-based algorithm.
 */
public class TopologicalSortStack {

    private TopologicalSortStack() {
        // Utility class
    }

    /**
     * Computes the topological order of a given directed acyclic graph
     * using a stack-based algorithm.
     *
     * @param graph the adjacency matrix representation of the directed acyclic graph
     * @return the topological order of the graph as an array of integers
     */
    public static int[] getTopologicalOrder(int[][] graph) {
        int N = graph.length;
        boolean[] visited = new boolean[N];
        int[] order = new int[N];
        int index = 0;

        // It must begin from vertex that has no dependencies
        // It will work for 2 separate components if each
        // of them start dfs with vertex without dependencies
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                index = dfs(graph, visited, i, index, order);
            }
        }
        return order;
    }

    /**
     * Performs a depth-first search (DFS) on the given graph, starting from the specified vertex,
     * and returns the order in which vertices are visited.
     *
     * @param graph   the adjacency matrix representing the directed acyclic graph to traverse
     * @param visited an array indicating which vertices have already been visited
     * @param actual  the current vertex being visited
     * @param index   the current index in the order array to insert the visited vertex
     * @param order   an array to store the order in which vertices are visited
     * @return the updated index in the order array after visiting all
     * reachable vertices from the current vertex
     */
    private static int dfs(int[][] graph,
                           boolean[] visited,
                           int actual,
                           int index,
                           int[] order) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(actual);
        visited[actual] = true;

        while (!stack.isEmpty()) {
            int current = stack.pop();
            order[index++] = current;
            for (int i = 0; i < graph.length; i++) {
                if (!visited[i] && graph[current][i] != 0) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        return index;
    }

    /**
     * Creates a new N x N adjacency matrix for a directed acyclic graph.
     *
     * @param N the number of vertices in the graph
     * @return a new N x N adjacency matrix
     */
    public static int[][] createGraph(final int N) {
        return new int[N][N];
    }

    /**
     * Adds a directed edge from a source vertex to a target vertex
     * in the given adjacency matrix.
     *
     * @param graph the N x N adjacency matrix representing a directed
     *              acyclic graph
     * @param from  the source vertex
     * @param to    the target vertex
     */
    public static void addDirectedEdge(int[][] graph, int from, int to) {
        graph[from][to] = 1;
    }

    public static void main(String[] args) {

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        final int N = 13;
        int[][] graph = createGraph(N);
        addDirectedEdge(graph, 0, 11);
        addDirectedEdge(graph, 0, 7);
        addDirectedEdge(graph, 0, 9);
        addDirectedEdge(graph, 9, 10);
        addDirectedEdge(graph, 10, 1);
        addDirectedEdge(graph, 1, 8);
        addDirectedEdge(graph, 8, 12);
        addDirectedEdge(graph, 7, 3);
        addDirectedEdge(graph, 7, 6);
        addDirectedEdge(graph, 6, 5);
        addDirectedEdge(graph, 3, 2);
        addDirectedEdge(graph, 2, 4);

        int[] order = getTopologicalOrder(graph);
        System.out.println(Arrays.toString(order));

        // Output:
        //[0, 11, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4]

        //                > 1                     12  --> 15
        //               /   \                      \
        //             10<    >8     2 --> 4         \
        //               \           /                 > 14 ---> 13
        //                9<         >3      >5         |
        //                  \       /       /           |
        //                   0  --> 7  --> 6             > 16
        //                    \
        //                    >11
        int[][] graph2 = TopologicalSortRecursive.createGraph(17);
        addDirectedEdge(graph2, 0, 11);
        addDirectedEdge(graph2, 0, 7);
        addDirectedEdge(graph2, 0, 9);
        addDirectedEdge(graph2, 9, 10);
        addDirectedEdge(graph2, 10, 1);
        addDirectedEdge(graph2, 1, 8);
        addDirectedEdge(graph2, 7, 3);
        addDirectedEdge(graph2, 7, 6);
        addDirectedEdge(graph2, 6, 5);
        addDirectedEdge(graph2, 3, 2);
        addDirectedEdge(graph2, 2, 4);
        addDirectedEdge(graph2, 12, 15);
        addDirectedEdge(graph2, 12, 14);
        addDirectedEdge(graph2, 14, 13);
        addDirectedEdge(graph2, 14, 16);

        order = getTopologicalOrder(graph2);
        System.out.println(Arrays.toString(order));

        //Output
        //[0, 11, 9, 10, 1, 8, 7, 6, 5, 3, 2, 4, 12, 15, 14, 16, 13]

        // LIMITATION -> We are not able to get ordering if we start
        //               from vertex that has dependencies
        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >0      >5
        //                  \       /       /
        //                   3  --> 7  --> 6
        //                    \
        //                    >11
        int[][] graph3 = TopologicalSortRecursive.createGraph(13);
        addDirectedEdge(graph3, 3, 11);
        addDirectedEdge(graph3, 3, 7);
        addDirectedEdge(graph3, 3, 9);
        addDirectedEdge(graph3, 9, 10);
        addDirectedEdge(graph3, 10, 1);
        addDirectedEdge(graph3, 1, 8);
        addDirectedEdge(graph3, 7, 0);
        addDirectedEdge(graph3, 7, 6);
        addDirectedEdge(graph3, 6, 5);
        addDirectedEdge(graph3, 0, 2);
        addDirectedEdge(graph3, 2, 4);
        addDirectedEdge(graph3, 8, 12);

        order = getTopologicalOrder(graph3);
        System.out.println(Arrays.toString(order));

        // WRONG Output:
        // [0, 2, 4, 1, 8, 12, 3, 11, 9, 10, 7, 6, 5]
    }
}
