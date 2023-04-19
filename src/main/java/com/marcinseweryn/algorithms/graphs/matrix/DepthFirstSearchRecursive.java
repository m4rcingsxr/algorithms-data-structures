package com.marcinseweryn.algorithms.graphs.matrix;

/**
 * This class contains a static method for performing
 * depth first search on a graph recursively.
 */
public class DepthFirstSearchRecursive {

    private DepthFirstSearchRecursive() {
        // Utility class
    }

    /**
     * This method performs depth first search on a given graph starting
     * from a specified vertex, and returns the result as a string.
     *
     * @param graph the graph represented as an adjacency matrix
     * @param start the vertex to start the search from
     * @return a string representation of the depth first search path
     */
    public static String dfs(int[][] graph, int start) {

        // Create a boolean array to keep track of visited vertices
        boolean[] visited = new boolean[graph.length];

        // Create and set up a StringBuilder object to build
        // the depth first search path string
        StringBuilder dfs = new StringBuilder();
        dfs.append(String.format("Depth First Search starting from vertex %d:[", start));

        // Call the recursive dfs method to perform the search
        if (graph.length != 0) {
            dfs(graph, visited, start, dfs);
            dfs.replace(dfs.length() - 1, dfs.length(), "]");
        } else {
            dfs.append("]");
        }

        // Replace the trailing comma with a closing bracket

        // Return the depth first search path as a string
        return dfs.toString();
    }

    /**
     * This is a recursive helper method that performs depth first search on a
     * given graph starting from a specified vertex, and appends the vertices
     * visited to the given StringBuilder object.
     *
     * @param graph   the graph represented as an adjacency matrix
     * @param visited a boolean array to keep track of visited vertices
     * @param index   the current vertex being visited
     * @param sb      the StringBuilder object to append the visited vertices to
     */
    private static void dfs(int[][] graph, boolean[] visited, int index, StringBuilder sb) {

        // Base case:
        // If the current vertex has already been visited, return
        if (visited[index]) return;

        // Append the current vertex to the StringBuilder object
        sb.append(index).append(",");

        // Mark the current vertex as visited
        visited[index] = true;

        // Recursively visit all adjacent vertices
        for (int i = 0; i < graph[index].length; i++) {
            if (graph[index][i] != 0) {
                dfs(graph, visited, i, sb);
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
        System.out.println(dfs(matrix, 0));

        // Output:
        //Depth First Search starting from vertex 0:[0,7,3,2,12,8,1,10,9,4,6,5,11]
    }
}
