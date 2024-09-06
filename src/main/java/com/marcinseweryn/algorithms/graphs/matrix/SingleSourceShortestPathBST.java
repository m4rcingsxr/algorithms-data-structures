package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.*;

/**
 * This class provides an implementation of the Single Source Shortest Path (SSSP) problem using Breadth-First Search (BFS).
 * It finds the shortest path in an unweighted graph represented by an adjacency matrix. This implementation
 * assumes that all edge weights are non-negative integers where any positive value represents an edge, and zero
 * represents no edge.
 *
 * <p>BFS is appropriate for unweighted graphs because it guarantees the shortest path in terms of the number of edges.
 * The graph is represented by an adjacency matrix where the cell at index [i][j] indicates the presence of an edge from
 * vertex i to vertex j. In this implementation, a non-zero value is considered as an edge (regardless of the actual weight).</p>
 *
 * <h2>Time Complexity</h2>
 * <p>The time complexity of the BFS algorithm used for finding the shortest path is O(V + E), where V is the number of vertices
 * and E is the number of edges. This complexity arises because each vertex and edge is processed at most once.</p>
 *
 * <h2>Space Complexity</h2>
 * <p>The space complexity is O(V), where V is the number of vertices. This includes space for the parent array, the visited
 * array, and the queue used for BFS.</p>
 */
public class SingleSourceShortestPathBST {

    private SingleSourceShortestPathBST() {
        // Utility class
    }

    public static List<Integer> sssp(int[][] graph, int start, int end) {
        Integer[] parent = bfs(graph, start);
        List<Integer> sssp = new ArrayList<>();
        for (Integer i = end; i != null ; i = parent[i]) {
            sssp.add(i);
        }

        Collections.reverse(sssp);

        // no path to start exist
        if(sssp.get(0) != start) {
            return List.of();
        }

        return sssp;
    }

    /**
     * Performs a Breadth First Search on the specified graph starting from
     * the specified node.
     *
     * @param graph the adjacency matrix representing the graph.
     * @param start the start node for the search.
     * @return an array of parent nodes, where parent[i] is the parent of
     * node i in the Breadth First Search tree.
     */
    public static Integer[] bfs(int[][] graph, int start) {
        int N = graph.length;
        Integer[] parent = new Integer[N];
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            for (int i = 0; i < graph[current].length; i++) {
                if (graph[current][i] > 0 && !visited[i]) {
                    queue.add(i);

                    parent[i] = current;
                    visited[i] = true;
                }
            }
        }

        return parent;
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
        int[][] matrix = {
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

        System.out.println(sssp(matrix, 0, 4)); // [0, 7, 3, 4]
    }
}
