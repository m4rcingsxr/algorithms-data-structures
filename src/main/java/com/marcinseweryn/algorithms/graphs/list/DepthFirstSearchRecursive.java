package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for creating and performing depth-first search (DFS) on a graph using recursion.
 * It contains utility methods for creating a graph with a specified number of vertices, adding edges to the graph
 * (both directed and undirected), and performing DFS on the graph starting from a specified vertex.
 * The graph is represented using an adjacency list, which is efficient for sparse graphs.
 *
 * <h2>Complexity Analysis:</h2>
 * <h3>Time Complexity:</h3>
 * <p>
 * The time complexity of the DFS traversal is O(V + E), where V is the number of vertices
 * and E is the number of edges. This is because:
 * <ul>
 *   <li>Each vertex is visited exactly once.</li>
 *   <li>Each edge is considered once when exploring the adjacency list of each vertex.</li>
 * </ul>
 * Thus, the overall time complexity is linear with respect to the size of the graph.
 * </p>
 *
 * <h3>Space Complexity:</h3>
 * <p>
 * The space complexity of the DFS traversal is O(V), where V is the number of vertices. This is due to:
 * <ul>
 *   <li>The recursion stack, which can go up to a depth of V in the worst case (for example, in a deeply nested graph).</li>
 *   <li>The `visited` array and the `result` list, both of which store information for each vertex.</li>
 * </ul>
 * Therefore, the space complexity is proportional to the number of vertices in the graph.
 * </p>
 *
 * <h2>Graph Representation:</h2>
 * <p>
 * The graph is represented using an adjacency list, where each vertex has a list of
 * connected vertices. This representation is efficient for sparse graphs and allows
 * for quick access to the neighbors of any given vertex.
 * </p>
 */

public class DepthFirstSearchRecursive {

    /**
     * Private constructor to prevent instantiation of this utility class.
     * The class is designed to provide static utility methods only.
     */
    private DepthFirstSearchRecursive() {
    }

    /**
     * Creates an adjacency list representation of a graph with the specified number of vertices.
     * Each vertex is represented by a list, and the graph is a list of these lists.
     *
     * @param noVertices the number of vertices in the graph
     * @return the adjacency list representation of the graph
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        // Initialize the adjacency list for the graph
        List<List<Integer>> graph = new ArrayList<>();
        // Create an empty list for each vertex
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph; // Return the newly created graph
    }

    /**
     * Adds an undirected edge between two vertices in the graph.
     * This means both vertices are added to each other's adjacency lists.
     *
     * @param graph the graph to add the edge to
     * @param from  the starting vertex of the edge
     * @param to    the ending vertex of the edge
     */
    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to); // Add an edge from 'from' to 'to'
        graph.get(to).add(from); // Add an edge from 'to' to 'from' to make it undirected
    }

    /**
     * Adds a directed edge from one vertex to another in the graph.
     * This operation modifies the adjacency list of the graph.
     *
     * @param graph the graph to add the edge to
     * @param from  the starting vertex of the edge
     * @param to    the ending vertex of the edge
     */
    public static void addDirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to); // Add a directed edge from 'from' to 'to'
    }

    /**
     * Performs a Depth-First Search (DFS) on the graph starting from the specified vertex.
     * Uses recursion to explore each vertex's neighbors before backtracking.
     *
     * @param graph the graph to perform DFS on
     * @param start the starting vertex for the DFS
     * @return a list of integers representing the order in which vertices are visited during the DFS
     */
    public static List<Integer> dfs(List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>(); // List to store the order of visited vertices
        boolean[] visited = new boolean[graph.size()]; // Array to track visited vertices to avoid revisiting
        dfs(graph, start, visited, result); // Perform DFS starting from the initial vertex
        return result; // Return the list of visited vertices in the order they were visited
    }

    /**
     * A private helper method that performs the recursive depth-first search (DFS).
     * It visits all unvisited neighbors of the current vertex before backtracking.
     *
     * @param graph   the graph to perform DFS on
     * @param vertex  the current vertex being explored
     * @param visited an array to track visited vertices
     * @param result  a list to store the order of visited vertices
     */
    private static void dfs(List<List<Integer>> graph, Integer vertex, boolean[] visited, List<Integer> result) {
        visited[vertex] = true; // Mark the current vertex as visited
        result.add(vertex); // Add the current vertex to the result list
        List<Integer> neighbors = graph.get(vertex); // Retrieve all neighbors of the current vertex
        for (Integer neighbor : neighbors) { // Iterate through all neighbors
            if (!visited[neighbor]) { // If the neighbor has not been visited, recursively perform DFS on it
                dfs(graph, neighbor, visited, result);
            }
        }
    }

    /**
     * The main method to test the depth-first search implementation with example graphs.
     * It creates two graphs represented by adjacency lists and performs DFS starting from vertex 0.
     *
     * Example Graph 1 (Undirected):
     * Graph structure:
     *                 1     12
     *               /   \ /   \
     *             10     8     2   4
     *               \   /       \ /
     *                9          3       5
     *                  \       /       /
     *                   0  -- 7  -- 6
     *                    \
     *                      11
     *
     * Example Graph 2 (Directed):
     * Graph structure:
     *                > 1    > 12
     *               /   \ /
     *             10<    >8     2 --> 4
     *               \           /
     *                9<         >3      >5
     *                  \       /       /
     *                   0  --> 7  --> 6
     *                    \
     *                    >11
     *
     * The graphs are tested with DFS starting from vertex 0, and the results are printed.
     */
    public static void main(String[] args) {
        // Example Graph 1: Undirected graph
        List<List<Integer>> graph = createGraph(13);

        // Add undirected edges to the graph
        addUndirectedEdge(graph, 0, 7);
        addUndirectedEdge(graph, 0, 9);
        addUndirectedEdge(graph, 0, 11);
        addUndirectedEdge(graph, 7, 6);
        addUndirectedEdge(graph, 7, 3);
        addUndirectedEdge(graph, 6, 5);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 2, 12);
        addUndirectedEdge(graph, 12, 8);
        addUndirectedEdge(graph, 8, 1);
        addUndirectedEdge(graph, 1, 10);
        addUndirectedEdge(graph, 10, 9);
        addUndirectedEdge(graph, 9, 8);

        // Perform DFS starting from vertex 0 and print the traversal order
        System.out.println(dfs(graph, 0)); // Expected output: [0, 7, 6, 5, 3, 4, 2, 12, 8, 1, 10, 9, 11]

        // Example Graph 2: Directed graph
        List<List<Integer>> graph2 = createGraph(13);

        // Add directed edges to the graph
        addDirectedEdge(graph2, 0, 11);
        addDirectedEdge(graph2, 0, 7);
        addDirectedEdge(graph2, 0, 9);
        addDirectedEdge(graph2, 9, 10);
        addDirectedEdge(graph2, 10, 1);
        addDirectedEdge(graph2, 1, 8);
        addDirectedEdge(graph2, 8, 12);
        addDirectedEdge(graph2, 7, 3);
        addDirectedEdge(graph2, 7, 6);
        addDirectedEdge(graph2, 6, 5);
        addDirectedEdge(graph2, 3, 2);
        addDirectedEdge(graph2, 2, 4);

        // Perform DFS starting from vertex 0 and print the traversal order
        System.out.println(dfs(graph2, 0)); // Expected output: [0, 11, 7, 3, 2, 4, 6, 5, 9, 10, 1, 8, 12]
    }
}
