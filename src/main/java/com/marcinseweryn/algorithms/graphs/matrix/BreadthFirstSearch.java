package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

public class BreadthFirstSearch {
    int[][] adjacencyMatrix;

    public BreadthFirstSearch(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void printBreadthFirstSearch(int start) {
        boolean[] visited = new boolean[adjacencyMatrix.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            System.out.print(current + " ");

            // neighbors
            for (int j = 0; j < adjacencyMatrix[current].length; j++) {
                // int start has a neighbor
                if (adjacencyMatrix[current][j] == 1 && (!visited[j])) {
                        queue.add(j);
                        visited[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        // A -- B
        // | \    \
        // |  \    E
        // |   \  /
        // C -- D
        int[][] matrix = new int[][]{
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };
        BreadthFirstSearch bfs = new BreadthFirstSearch(matrix);
        bfs.printBreadthFirstSearch(0);
        System.out.println();

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

        bfs = new BreadthFirstSearch(matrix);
        bfs.printBreadthFirstSearch(0);

        // Print:
        //0 1 2 3 4
        //0 7 9 11 3 6 8 10 4 5 1 12 2
    }
}
