package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * This class implements the Single Source Shortest Path algorithm using
 * Breadth First Search for a given adjacency matrix.
 */
public class SingleSourceShortestPathBST {

    private SingleSourceShortestPathBST() {
        // Utility class
    }

    /**
     * Prints the shortest path to all vertices
     * in the graph from the specified start node.
     *
     * @param graph the adjacency matrix representing the graph.
     * @param start the start node for the path.
     */
    public static void printSSSP(int[][] graph, int start) {

        // Breadth first search
        Integer[] parent = bfs(graph, start);
        // Print path to each vertex from start node
        for (int i = 0; i < graph.length; i++) {
            StringBuilder path = new StringBuilder();
            printPath(start, i, path, parent);
            path.replace(path.indexOf(","),
                         path.indexOf(",") + 1, String.format(
                            "Shortest path from %d to %d: [", start, i
                    )
            );
            path.append("]");
            System.out.println(path);
        }
    }

    /**
     * Recursive method to reconstruct the path from the start node to the
     * end node.
     *
     * @param end    the end node of the path.
     * @param sb     the StringBuilder object used to build the path.
     * @param parent the array of parent nodes returned by the Breadth First
     *               Search.
     */
    private static void printPath(int start, int end, StringBuilder sb,
                                  Integer[] parent) {

        // Start node is self-root, parent[i] == null
        for (Integer i = end; i != null; i = parent[i]) {
            if (i % 10 == i) {
                sb.append(i).append(",");
            } else {

                // Solve problem of reversing more than 1 digit number
                sb.append(
                        new StringBuilder(String.valueOf(i)).reverse()).append(
                        ",");
            }
        }
        sb.reverse();

        // If reconstruction does not reach starting vertex
        // that's mean vertices are not connected
        if (Integer.parseInt(sb.substring(1, 2)) != start) { // ",A "
            sb.replace(0, sb.length(), "not connected");
        }
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

        /*
            Shortest path from 0 to 0: [0]
            Shortest path from 0 to 1: [0,9,8,1]
            Shortest path from 0 to 2: [0,9,8,12,2] ????
            Shortest path from 0 to 3: [0,7,3]
            Shortest path from 0 to 4: [0,7,3,4]
            Shortest path from 0 to 5: [0,7,6,5]
            Shortest path from 0 to 6: [0,7,6]
            Shortest path from 0 to 7: [0,7]
            Shortest path from 0 to 8: [0,9,8]
            Shortest path from 0 to 9: [0,9]
            Shortest path from 0 to 10: [0,9,10]
            Shortest path from 0 to 11: [0,11]
            Shortest path from 0 to 12: [0,9,8,12]
         */
        printSSSP(matrix, 0);
    }
}
