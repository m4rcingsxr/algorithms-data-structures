package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.Arrays;

public class TopologicalSortRecursiveArray {
    public static int[] topologicalSort(int[][] adjacencyMatrix) {
        int length = adjacencyMatrix.length;
        boolean[] visited = new boolean[length];
        int[] topologicalOrder = new int[length];
        int index = length - 1;

        for (int i = 0; i < length; i++) {
            if (!visited[i]) {
                index = depthFirstSearch(adjacencyMatrix, visited, index, i, topologicalOrder);
            }
        }

        return topologicalOrder;
    }

    private static int depthFirstSearch(int[][] adjacencyMatrix, boolean[] visited, int index, int actual, int[] order) {
        if (visited[actual]) {
            return index;
        }

        visited[actual] = true;
        for (int i = 0; i < adjacencyMatrix[actual].length; i++) {
            if (adjacencyMatrix[actual][i] != 0 && !visited[i]) {
                index = depthFirstSearch(adjacencyMatrix, visited, index, i, order);
            }
        }
        order[index] = actual;
        return index - 1;
    }

    public static void main(String[] args) {
        final int N = 7;
        int[][] adjMatrix = new int[N][N];
        adjMatrix[0][1] = 1;
        adjMatrix[0][2] = 1;
        adjMatrix[0][5] = 1;
        adjMatrix[1][3] = 1;
        adjMatrix[1][2] = 1;
        adjMatrix[2][3] = 1;
        adjMatrix[2][4] = 1;
        adjMatrix[3][4] = 1;
        adjMatrix[5][4] = 1;

        int[] ordering = topologicalSort(adjMatrix);

        // [6, 0, 5, 1, 2, 3, 4]
        System.out.println(Arrays.toString(ordering));
    }
}
