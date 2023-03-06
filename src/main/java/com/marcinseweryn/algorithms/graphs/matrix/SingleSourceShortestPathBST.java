package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class SingleSourceShortestPathBST {

    public static void printSSSP(int[][] matrix, int start) {
        int N = matrix.length;
        Integer[] parent = new Integer[N];
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new ArrayDeque<>();

        // Breadth first search
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();

            for (int i = 0; i < matrix[current].length; i++) {
                if (matrix[current][i] != 0 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                    parent[i] = current;
                }
            }
        }

        // print path
        for (int i = 0; i < N; i++) {
            System.out.print("Shortest path from " + start + " to " + i + " [");
            printPath(parent, i, i);
            System.out.println("]");
        }
    }

    private static void printPath(Integer[] parent, int index, int end) {
        if (parent[index] != null) {
            printPath(parent, parent[index], end);
        }
        if (index != end) {
            System.out.print(index + "->");
        } else {
            System.out.print(index);
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
