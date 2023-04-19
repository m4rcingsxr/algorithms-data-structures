
package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The DepthFirstSearchStack class provides a static method for performing a depth-first search
 * on a graph represented as an adjacency matrix using a stack.
 */
public class DepthFirstSearchStack {

    // Private constructor to prevent instantiation of this utility class
    private DepthFirstSearchStack() {
        // Utility class
    }

    /**
     * Performs a depth-first search on a graph represented as an adjacency matrix using a stack.
     *
     * @param graph the adjacency matrix representing the graph
     * @param start the index of the starting vertex for the search
     * @return a string representing the path of the search, in the format "Depth First Search starting
     * from vertex x:[v1,v2,v3,...]"
     */
    public static String depthFirstSearch(int[][] graph, int start) {
        StringBuilder dfsPath = new StringBuilder(
                "Depth First Search starting from vertex "
                        + start + ":["
        );
        int length = graph.length;

        // Create a boolean array to keep track of visited vertices
        boolean[] visited = new boolean[length];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        // Mark the starting vertex as visited
        visited[start] = true;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            dfsPath.append(current).append(",");

            // Loop through the neighbors of the current vertex
            for (int i = 0; i < graph[current].length; i++) {

                // If the neighbor is adjacent and has not been visited,
                // push it onto the stack and mark it as visited
                if (graph[current][i] != 0 && !visited[i]) {
                    stack.push(i);
                    visited[i] = true;
                }
            }
        }
        // Replace the last comma with a closing bracket and return the string
        return dfsPath.replace(
                dfsPath.length() - 1, dfsPath.length(), "]"
        ).toString();
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
        System.out.println(depthFirstSearch(matrix, 0));

        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
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

        System.out.println(depthFirstSearch(matrix, 0));

        /* OUTPUT:
           Depth First Search starting from vertex 0:[0,3,4,2,1]
            Depth First Search starting from vertex 0:[0,11,9,10,1,8,12,2,3,4,7,6,5]
         */
    }
}
