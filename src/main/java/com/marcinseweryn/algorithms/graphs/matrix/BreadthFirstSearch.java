package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Implements a Breadth-First Search algorithm on a graph represented as an
 * adjacency matrix.
 */
public class BreadthFirstSearch {
    int[][] adjacencyMatrix;

    /**
     * Constructs a new BreadthFirstSearch object with the given adjacency
     * matrix.
     *
     * @param adjacencyMatrix the adjacency matrix representing the graph
     */
    public BreadthFirstSearch(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    /**
     * Performs a Breadth-First Search on the graph starting from the given
     * vertex,
     * and return visited vertices.
     *
     * @param start the starting vertex for the search
     * @return String representation of executed breadth first search
     * algorithm on the graph
     */
    public String printBreadthFirstSearch(int start) {
        StringBuilder bfs = new StringBuilder();
        bfs.append(
                String.format("Breadth First Search from vertex %d:[", start)
        );

        // Create a boolean array to keep track of visited vertices
        boolean[] visited = new boolean[adjacencyMatrix.length];

        // Create a Queue to keep track of vertices to be visited
        Queue<Integer> queue = new ArrayDeque<>();

        // Add the starting vertex to the queue and mark it as visited
        queue.add(start);
        visited[start] = true;

        // Continue until all vertices have been visited
        while (!queue.isEmpty()) {

            // Remove the next vertex from the queue and print it
            int current = queue.remove();
            bfs.append(current).append(" ");

            // Visit all neighbors of the current vertex
            for (int j = 0; j < adjacencyMatrix[current].length; j++) {

                // If the neighbor is adjacent and hasn't been visited yet,
                // add it to the queue and mark it as visited
                if (adjacencyMatrix[current][j] == 1 && (!visited[j])) {
                    queue.add(j);
                    visited[j] = true;
                }
            }
        }
        bfs.replace(bfs.length() - 1, bfs.length(), "]");
        return bfs.toString();
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
        BreadthFirstSearch bfs = new BreadthFirstSearch(matrix);
        System.out.println(bfs.printBreadthFirstSearch(0));

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
        System.out.println(bfs.printBreadthFirstSearch(0));

        // Output:
        //Breadth First Search from vertex 0:[0 1 2 3 4]
        //Breadth First Search from vertex 0:[0 7 9 11 3 6 8 10 4 5 1 12 2]

    }
}
