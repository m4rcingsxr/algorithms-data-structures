package com.marcinseweryn.algorithms.graphs.matrix;

public class DepthFirstSearchRecursive {
    public static String dfs(int[][] graph, int start) {
        boolean[] visited = new boolean[graph.length];
        StringBuilder dfs = new StringBuilder();
        dfs.append(String.format(
                "Depth First Search starting from vertex %d:[", start
        ));
        dfs(graph, visited, start, dfs);
        dfs.replace(dfs.length() - 1, dfs.length(), "]");
        return dfs.toString();
    }

    private static void dfs(
            int[][] graph,
            boolean[] visited,
            int index,
            StringBuilder sb
    ) {

        // Base case
        if (visited[index]) return;

        sb.append(index).append(",");
        visited[index] = true;

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
