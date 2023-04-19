package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 *  This class implements the Single Source Shortest Path algorithm using
 *  Breadth First Search for a given adjacency matrix.
 */
public class SingleSourceShortestPathBST {

    private SingleSourceShortestPathBST() {
        // Utility class
    }

    /**
     * Prints the shortest path to all vertices
     * in the graph from the specified start node.
     * @param graph the adjacency matrix representing the graph.
     * @param start the start node for the path.
     */
    public static void printSSSP(int[][] graph, int start) {

        // Breadth first search
        Integer[] parent = bfs(graph, start);
        System.out.println(Arrays.toString(parent));
        // Print path to each vertex from start node
        for (int i = 0; i < graph.length; i++) {
            StringBuilder path = new StringBuilder();
            printPath(i, path, parent);
            path.replace(path.indexOf(","),
                    path.indexOf(",") + 1, String.format(
                            "Shortest path from %d to %d: [", start, i
                    ));
            path.append("]");
            System.out.println(path);
        }
    }

    /**
     * Recursive method to reconstruct the path from the start node to the end node.
     * @param end the end node of the path.
     * @param sb the StringBuilder object used to build the path.
     * @param parent the array of parent nodes returned by the Breadth First Search.
     */
    private static void printPath(int end, StringBuilder sb, Integer[] parent) {

        // Start node is self-root, parent[i] == null
        for (Integer i = end; i != null; i = parent[i]) {
            sb.append(i).append(",");
        }
        sb.reverse();
    }

    /**
     * Performs a Breadth First Search on the specified graph starting from the specified node.
     * @param graph the adjacency matrix representing the graph.
     * @param start the start node for the search.
     * @return an array of parent nodes, where parent[i] is the parent of
     *              node i in the Breadth First Search tree.
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

                    // bijection, self loop - main root
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

        printSSSP(matrix, 0);
    }
}
