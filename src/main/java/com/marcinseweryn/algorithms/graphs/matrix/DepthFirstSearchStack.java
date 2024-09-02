package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * The {@code DepthFirstSearchStack} class provides a static method for performing a
 * depth-first search (DFS) on a graph represented as an adjacency matrix using an explicit stack.
 * This iterative approach manages traversal with a stack, avoiding the recursive method's call stack overhead.
 *
 * <p>The adjacency matrix representation of the graph is a 2D array where the cell at index [i][j] is 1
 * if there is an edge between vertex i and vertex j, and 0 otherwise. The DFS algorithm uses an explicit
 * stack to manage the traversal order.</p>
 *
 * <h2>Time Complexity</h2>
 * <p>The time complexity of the DFS traversal is O(V^2), where V is the number of vertices in the graph.
 * This is because the adjacency matrix is of size V x V, and we potentially check each cell to determine
 * the presence of edges.</p>
 *
 * <h2>Space Complexity</h2>
 * <p>The space complexity of the DFS traversal is O(V), where V is the number of vertices. This space is used
 * for the stack and the visited array. The stack can grow to a size of V in the worst case, such as when the graph
 * is a single path, and the visited array has a size of V. The space complexity does not depend on the number of edges.</p>
 *
 * <h2>Applicable Graph Types</h2>
 * <ul>
 *   <li>Undirected graphs.</li>
 *   <li>Directed graphs.</li>
 *   <li>Graphs where edge weights are irrelevant, as DFS focuses on vertex connectivity rather than edge weights.</li>
 * </ul>
 */
public class DepthFirstSearchStack {

    /**
     * Private constructor to prevent instantiation of the utility class.
     */
    private DepthFirstSearchStack() {}

    /**
     * Performs a depth-first search (DFS) on a graph represented as an adjacency
     * matrix using an iterative approach with a stack.
     * The method explores as far as possible along each branch before backtracking.
     *
     * @param graph the adjacency matrix representing the graph, where graph[i][j] = 1
     *              indicates an edge from vertex i to vertex j, and graph[i][j] = 0 indicates no edge.
     * @param start the index of the starting vertex for the DFS traversal.
     * @return a list of integers representing the order in which vertices are visited during the DFS.
     *
     * Time Complexity: O(V^2), where V is the number of vertices in the graph. This is because
     * each vertex can potentially connect to every other vertex, requiring checking all entries
     * in the adjacency matrix.
     */
    public static List<Integer> depthFirstSearch(int[][] graph, int start) {
        List<Integer> dfs = new ArrayList<>();  // List to store the order of visited vertices.
        Deque<Integer> stack = new ArrayDeque<>();  // Stack to manage the DFS traversal.
        boolean[] visited = new boolean[graph.length];  // Array to track visited vertices.

        stack.push(start);  // Start the DFS from the specified starting vertex.

        while (!stack.isEmpty()) {  // Continue until all reachable vertices are visited.
            Integer current = stack.pop();  // Pop the top vertex from the stack.

            if (!visited[current]) {  // If the vertex has not been visited yet.
                visited[current] = true;  // Mark the vertex as visited.
                dfs.add(current);  // Add the vertex to the result list.

                // Iterate over all possible vertices to find neighbors in the graph.
                for (int i = 0; i < graph[current].length; i++) {
                    // If an edge exists to an unvisited vertex, push it onto the stack.
                    if(graph[current][i] == 1 && !visited[i]) {
                        stack.push(i);
                    }
                }
            }
        }

        return dfs;  // Return the list of vertices in the order they were visited.
    }

    public static void main(String[] args) {
        //      0 -- 1
        //      | \    \
        //      |  \    4
        //      |   \  /
        //      2 -- 3
        int[][] matrix = new int[][]{
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };
        System.out.println(depthFirstSearch(matrix, 0));  // Expected output: [0, 3, 4, 2, 1]

        //                        1     12
        //                      /   \ /   \
        //                    10     8     2   4
        //                      \   /       \ /
        //                       9          3       5
        //                         \       /       /
        //                          0  -- 7  -- 6
        //                           \
        //                             11
        matrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
        };

        System.out.println(depthFirstSearch(matrix, 0));  // Expected output: [0, 11, 9, 10, 1, 8, 12, 2, 3, 4, 7, 6, 5]
    }
}
