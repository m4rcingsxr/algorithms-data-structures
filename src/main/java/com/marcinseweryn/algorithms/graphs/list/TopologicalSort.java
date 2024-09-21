package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides an implementation of the Topological Sort algorithm
 * for directed acyclic graphs (DAGs). Topological Sort is used to order the
 * vertices of a DAG such that for every directed edge from vertex `u` to
 * vertex `v`, vertex `u` appears before vertex `v` in the ordering.
 *
 * <p>
 * Topological sorting is often used in scenarios like task scheduling, where
 * certain tasks must be completed before others, and in resolving dependencies,
 * such as in build systems or course prerequisite chains.
 * </p>
 *
 * <h2>Key Concepts:</h2>
 * <ul>
 *   <li><b>Directed Acyclic Graph (DAG):</b> A graph with directed edges and no cycles. Topological sorting is only applicable to DAGs.</li>
 *   <li><b>Depth-First Search (DFS):</b> A traversal algorithm used here to explore the graph and compute the topological order.</li>
 * </ul>
 *
 * <h2>Complexity Analysis:</h2>
 * <h3>Time Complexity:</h3>
 * <p>
 * The time complexity of the topological sort algorithm is O(V + E), where
 * V is the number of vertices and E is the number of edges. This is because
 * each vertex and edge is processed once during the DFS traversal.
 * </p>
 *
 * <h3>Space Complexity:</h3>
 * <p>
 * The space complexity is O(V), due to the storage of the visited array
 * and the topological order array.
 * </p>
 *
 * <h2>Usage:</h2>
 * <p>
 * This class provides static methods for creating a graph, adding edges,
 * and performing a topological sort. It is designed to work with directed
 * acyclic graphs (DAGs). If the graph contains cycles, topological sort is
 * not applicable, and this implementation assumes the input graph is a DAG.
 * </p>
 */
public class TopologicalSort {

    private TopologicalSort() {}

    /**
     * Creates a new empty graph with the specified number of vertices.
     *
     * @param noVertices the number of vertices to be included in the graph
     * @return a new empty graph represented as a List of Lists of Integers
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>()); // Initialize each vertex's adjacency list as empty
        }
        return graph;
    }

    /**
     * Adds a directed edge to the graph connecting the specified source and
     * target vertices.
     *
     * @param graph the graph to which the edge is to be added
     * @param from  the source vertex of the edge
     * @param to    the target vertex of the edge
     */
    public static void addDirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to); // Add the directed edge from 'from' to 'to'
    }

    /**
     * Private helper method that performs depth-first search on the given vertex
     * and updates the topological ordering of nodes.
     *
     * @param graph   the graph to be searched
     * @param index   the current index in the ordering array
     * @param actual  the vertex currently being searched
     * @param visited an array of booleans indicating whether a node has been visited or not
     * @param order   an array of integers containing the topological ordering of the nodes in the graph
     * @return an integer representing the updated index for the next element
     */
    private static int dfs(List<List<Integer>> graph, int index, int actual, boolean[] visited, int[] order) {
        if (visited[actual]) {
            return index; // If the vertex is already visited, return the current index
        }
        visited[actual] = true; // Mark the current vertex as visited

        // Recursively visit all adjacent vertices (neighbors)
        for (Integer neighbor : graph.get(actual)) {
            index = dfs(graph, index, neighbor, visited, order); // Update the index based on recursive calls
        }

        order[index] = actual; // Place the current vertex at the current index in the topological order
        return index - 1; // Decrement the index for the next vertex to be placed in the order
    }

    /**
     * Executes a depth-first search-based topological sort on the provided graph.
     * This method sorts the nodes in a directed acyclic graph such that for every
     * directed edge `u -> v`, `u` comes before `v` in the ordering.
     *
     * @param graph the graph to be sorted
     * @return an array of integers representing the topological ordering of the nodes in the graph
     */
    public static int[] topologicalSort(List<List<Integer>> graph) {
        int length = graph.size(); // Number of vertices in the graph
        int index = length - 1; // Initialize the index to the last position in the topological order
        int[] topologicalSort = new int[length]; // Array to store the topological order
        boolean[] visited = new boolean[length]; // Array to track visited vertices

        // Perform DFS for each vertex to ensure all nodes are covered (even in disconnected graphs)
        for (int i = 0; i < length; i++) {
            index = dfs(graph, index, i, visited, topologicalSort); // Update index based on DFS results
        }

        return topologicalSort; // Return the computed topological order
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

        System.out.println(Arrays.toString(topologicalSort(graph))); // [0, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4, 11]
    }
}
