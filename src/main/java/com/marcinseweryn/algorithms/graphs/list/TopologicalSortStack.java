package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

/**
 * Provides a static method for performing topological sort on a directed
 * acyclic graph using a stack-based approach.
 */
public class TopologicalSortStack {

    private TopologicalSortStack() {
        // Private constructor to prevent instantiation of this utility class
    }

    /**
     * Performs a topological sort on a directed acyclic graph using a
     * stack-based approach.
     *
     * @param graph the graph to sort
     * @return an array representing the topologically sorted order of the
     * graph's vertices
     */
    static int[] topologicalSort(List<List<Integer>> graph) {
        int N = graph.size();
        int[] order = new int[N];
        boolean[] visited = new boolean[N];
        Deque<Integer> stack = new ArrayDeque<>();
        int index = 0;

        // Iterate over every vertex in the graph in case of
        // separate components. We cant start from arbitrary vertex
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {

                // If this vertex hasn't been visited, perform a DFS on it
                // starting with the current index and using the stack as a
                // helper
                index = dfs(graph, stack, i, index, visited, order);
            }
        }

        return order;
    }

    /**
     * Recursive helper method that performs a DFS on a given vertex, adding
     * its neighbors to the stack and processing them in a LIFO order.
     *
     * @param graph   the graph being sorted
     * @param stack   the stack being used to track vertices
     * @param current the current vertex being processed
     * @param index   the current index in the order array
     * @param visited the array of visited vertices
     * @param order   the array representing the topologically sorted order
     *                of the graph's vertices
     * @return the updated index after the DFS has been completed
     */
    private static int dfs(List<List<Integer>> graph, Deque<Integer> stack,
                           int current, int index, boolean[] visited,
                           int[] order) {
        stack.push(current);
        visited[current] = true;

        while (!stack.isEmpty()) {
            current = stack.pop();
            order[index++] = current;

            for (Integer neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    stack.push(neighbor);
                }
            }
        }
        return index;
    }

    /**
     * Creates a new graph with the given number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return the newly created graph
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
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
    public static void addDirectedEdge(List<List<Integer>> graph, int from,
                                       int to) {
        graph.get(from).add(to);
    }

    public static void main(String[] args) {
        final int N = 13;
        List<List<Integer>> graph = createGraph(N);

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
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

        int[] orderding = topologicalSort(graph);

        // [0, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4, 11]
        System.out.println(
                "TOPOLOGICAL ORDERING\n" + Arrays.toString(orderding));
    }


}
