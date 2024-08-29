package com.marcinseweryn.algorithms.graphs.matrix;

import com.marcinseweryn.algorithms.datastructures.queue.LinkedListQueue;
import com.marcinseweryn.algorithms.datastructures.queue.Queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BreadthFirstSearch} class provides methods to perform a Breadth-First Search (BFS)
 * on an undirected graph represented as an adjacency matrix. BFS is a fundamental algorithm used to
 * explore the nodes and edges of a graph systematically. It visits all vertices at the present depth level
 * before moving on to vertices at the next depth level.
 *
 * <p>The adjacency matrix representation of the graph is a 2D array where the cell at index [i][j] is 1
 * if there is an edge between vertex i and vertex j, and 0 otherwise. The BFS algorithm uses a queue to
 * manage the order of vertex visits and ensures that each vertex is visited exactly once.</p>
 *
 * <h2>Time Complexity</h2>
 * <p>The time complexity of the BFS traversal is O(V^2), where V is the number of vertices in the graph.
 * This is because the adjacency matrix requires checking each cell to determine the presence of edges,
 * resulting in a time complexity of O(V^2).</p>
 *
 * <h2>Space Complexity</h2>
 * <p>The space complexity of the BFS traversal is O(V), where V is the number of vertices in the graph.
 * This space is used for the visited array to keep track of visited vertices and the queue that stores
 * the vertices to be processed. The space complexity does not depend on the number of edges in the graph.</p>
 *
 * <h2>Applicable Graph Types</h2>
 * <ul>
 *   <li>Undirected graphs.</li>
 *   <li>Graphs where edge weights are irrelevant, as BFS focuses on vertex connectivity rather than edge weights.</li>
 * </ul>
 *
 * <h2>Use Cases</h2>
 * <ul>
 *   <li>Finding the shortest path in an unweighted graph, where all edges are considered equal.</li>
 *   <li>Exploring all nodes in a network or graph to perform operations such as search or connectivity checks.</li>
 *   <li>Used in algorithms that require a systematic exploration of all reachable nodes from a starting point.</li>
 * </ul>
 */
public class BreadthFirstSearch {

    /**
     * Performs a Breadth-First Search (BFS) traversal on a graph represented by an adjacency matrix.
     * This method starts from the specified vertex and explores all reachable vertices using the BFS algorithm.
     *
     * @param graph The adjacency matrix representing the graph.
     *                        If there is an edge between vertices i and j, graph[i][j] is 1; otherwise, it's 0.
     * @param start The starting vertex for the BFS traversal.
     * @return A list of integers representing the order of vertices visited during the BFS traversal.
     *
     * Time Complexity: O(V^2), where V is the number of vertices.
     * This is because the adjacency matrix is of size V x V, and we potentially check each cell.
     *
     * Space Complexity: O(V), for the queue and visited array, where V is the number of vertices.
     */
    public static List<Integer> bfs(int[][] graph, int start) {
        List<Integer> bfs = new ArrayList<>(); // List to store the order of BFS traversal
        boolean[] visited = new boolean[graph.length]; // Track visited vertices to avoid cycles
        Queue<Integer> queue = new LinkedListQueue<>(); // Queue for BFS to handle the next vertex to visit

        // Start BFS from the initial vertex
        queue.enqueue(start);
        visited[start] = true; // Mark the start vertex as visited

        while (!queue.isEmpty()) {
            int current = queue.dequeue(); // Dequeue a vertex from the front of the queue
            bfs.add(current); // Add it to the BFS result list

            // Explore all vertices adjacent to the current vertex
            for (int i = 0; i < graph.length; i++) {
                // If an edge exists and the vertex has not been visited
                if (graph[current][i] == 1 && !visited[i]) {
                    queue.enqueue(i); // Enqueue the adjacent vertex
                    visited[i] = true; // Mark the vertex as visited to prevent re-queueing
                }
            }
        }

        return bfs; // Return the BFS traversal order
    }

    public static void main(String[] args) {

        // 0 -- 1
        // | \    \
        // |  \    4
        // |   \  /
        // 2 -- 3
        int[][] matrix = new int[][]{
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };

        // Perform BFS from vertex 0
        System.out.println(BreadthFirstSearch.bfs(matrix, 0)); // Expected output: [0, 1, 2, 3, 4]

        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                    11
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

        // Perform BFS from vertex 0
        System.out.println(BreadthFirstSearch.bfs(matrix, 0)); // Expected output: [0, 7, 9, 11, 3, 6, 8, 10, 4, 5, 1, 12, 2]
    }
}
