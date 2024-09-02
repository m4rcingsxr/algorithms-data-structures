package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DepthFirstSearchRecursive} class provides a method to perform Depth-First Search (DFS)
 * on a graph represented by an adjacency matrix using recursion. DFS explores as far along a branch as
 * possible before backtracking.
 *
 * <p>The adjacency matrix representation of the graph is a 2D array where the cell at index [i][j] is 1
 * if there is an edge between vertex i and vertex j, and 0 otherwise. The DFS algorithm uses recursion
 * to traverse the graph depth-wise, exploring each vertex's adjacent vertices before moving on to the next vertex.</p>
 *
 * <h2>Time Complexity</h2>
 * <p>The time complexity of the DFS traversal is O(V^2), where V is the number of vertices in the graph.
 * This complexity arises because the adjacency matrix requires checking each cell to determine the presence of edges.</p>
 *
 * <h2>Space Complexity</h2>
 * <p>The space complexity of the DFS traversal is O(V), where V is the number of vertices. This space is used
 * for the recursive call stack and the visited array. The recursive call stack depth is proportional to the number
 * of vertices in the worst case (when the graph is a single path). The space complexity does not depend on the number of edges.</p>
 *
 * <h2>Applicable Graph Types</h2>
 * <ul>
 *   <li>Undirected graphs.</li>
 *   <li>Directed graphs.</li>
 *   <li>Graphs where edge weights are irrelevant, as DFS focuses on vertex connectivity rather than edge weights.</li>
 * </ul>
 */
public class DepthFirstSearchRecursive {

    private DepthFirstSearchRecursive() {}

    public static List<Integer> dfs(int[][] graph, int start) {
        boolean[] visited = new boolean[graph.length];
        List<Integer> dfs = new ArrayList<>();
        dfs(graph, start, dfs, visited);
        return dfs;
    }

    private static void dfs(int[][] graph, int vertex, List<Integer> dfs, boolean[] visited) {
        visited[vertex] = true;
        dfs.add(vertex);
        for (int i = 0; i < graph[vertex].length; i++) {
            if(graph[vertex][i] == 1 && !visited[i]) {
                dfs(graph, i, dfs, visited);
            }
        }
    }


    public static void main(String[] args) {

        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
        int[][] matrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
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

        System.out.println(dfs(matrix, 0)); // [0, 7, 3, 2, 12, 8, 1, 10, 9, 4, 6, 5, 11]
    }
}
