package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

/**
 * The TopologicalSortStack class provides a static method to perform a topological sort on a directed acyclic graph (DAG).
 * This implementation uses a stack-based iterative depth-first search (DFS) approach to ensure a valid topological ordering of the vertices.
 *
 * <p>
 * Topological sort is useful for scenarios where you need to determine an ordering of tasks or events that must be performed
 * in a specific sequence, such as scheduling tasks or resolving dependencies. The stack-based approach avoids the use of recursion
 * and is effective for managing large graphs where recursion might lead to stack overflow issues.
 * </p>
 *
 * <h2>Key Concepts:</h2>
 * <ul>
 *   <li><b>Directed Acyclic Graph (DAG):</b> A graph with directed edges and no cycles, suitable for topological sorting.</li>
 *   <li><b>Stack-Based DFS:</b> Uses an explicit stack to manage the depth-first traversal, avoiding recursion and its limitations.</li>
 * </ul>
 *
 * <h2>Complexity Analysis:</h2>
 * <h3>Time Complexity:</h3>
 * <p>
 * The time complexity of the topological sort algorithm is O(V + E), where V is the number of vertices and E is the number of edges.
 * Each vertex and edge is processed once during the traversal.
 * </p>
 *
 * <h3>Space Complexity:</h3>
 * <p>
 * The space complexity is O(V), due to the storage required for the visited array, stack, and the order array.
 * </p>
 *
 * <h2>Usage:</h2>
 * <p>
 * This class provides static methods to create a graph, add directed edges, and perform a topological sort using an iterative
 * stack-based DFS approach. It assumes that the input graph is a directed acyclic graph (DAG). If the graph contains cycles,
 * the algorithm may not produce a valid topological order.
 * </p>
 */
public class TopologicalSortStack {

    // Private constructor to prevent instantiation
    private TopologicalSortStack() {
    }

    /**
     * Performs a topological sort on a directed acyclic graph using an iterative stack-based DFS approach.
     *
     * @param graph the graph to sort, represented as an adjacency list where each index represents a vertex and its list contains all vertices it points to
     * @return an array representing the topologically sorted order of the graph's vertices
     */
    public static int[] topologicalSort(List<List<Integer>> graph) {
        int N = graph.size(); // Number of vertices in the graph
        int[] order = new int[N]; // Array to store the topological order
        boolean[] visited = new boolean[N]; // Array to track visited vertices
        Deque<Integer> stack = new ArrayDeque<>(); // Stack for DFS traversal
        int index = N - 1; // Start filling the order array from the end

        // Iterate over each vertex to perform DFS
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                stack.push(i); // Start DFS from the unvisited vertex

                while (!stack.isEmpty()) {
                    int vertex = stack.peek();

                    if (!visited[vertex]) {
                        visited[vertex] = true; // Mark the vertex as visited

                        // Push all unvisited neighbors onto the stack
                        for (int neighbor : graph.get(vertex)) {
                            if (!visited[neighbor]) {
                                stack.push(neighbor);
                            }
                        }
                    } else {
                        // If all neighbors are processed, add to result order and pop
                        if (stack.peek() == vertex) {
                            stack.pop();
                            order[index--] = vertex;
                        }
                    }
                }
            }
        }

        return order; // Return the topological order
    }

    /**
     * Creates a graph with the specified number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return an adjacency list representation of the graph
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>(); // Initialize the graph as an ArrayList of ArrayLists
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>()); // Add an empty list for each vertex
        }
        return graph; // Return the empty graph
    }

    /**
     * Adds a directed edge from one vertex to another in the graph.
     *
     * @param graph the graph to which the edge is added
     * @param from the starting vertex of the edge
     * @param to the ending vertex of the edge
     */
    public static void addDirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to); // Add the edge from 'from' to 'to' vertex
    }

    /**
     * Main method to demonstrate the topological sorting of a sample graph.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        final int N = 13; // Number of vertices in the sample graph
        List<List<Integer>> graph = createGraph(N); // Create the graph with N vertices

        // Add directed edges to the graph to represent the DAG
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

        // Perform topological sort on the graph
        int[] ordering = topologicalSort(graph);

        // Print the expected topological order
        System.out.println(Arrays.toString(ordering)); // [0, 11, 7, 3, 2, 4, 6, 5, 9, 10, 1, 8, 12]
    }
}
