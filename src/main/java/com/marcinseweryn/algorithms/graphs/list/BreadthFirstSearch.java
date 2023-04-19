package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.System.out;

/**
 * The BreadthFirstSearch class provides methods for creating and
 * traversing an undirected graph using BFS algorithm.
 */
public class BreadthFirstSearch {

    private BreadthFirstSearch() {
        // Utility class
    }

    /**
     * Creates a new graph with the given number of vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return the new graph as a list of adjacency lists
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }

        return graph;


    }

    /**
     * Adds an undirected edge between the two given vertices in the given graph.
     *
     * @param graph the graph to add the edge to
     * @param from the vertex the edge starts from
     * @param to the vertex the edge goes to
     */
    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    /**
     * Performs a breadth-first search traversal of the given graph starting from the given vertex.
     *
     * @param graph the graph to traverse
     * @param start the starting vertex for the traversal
     * @return a string representation of the traversal
     *         path in the format "Breadth First Search from vertex {start}:{path}"
     */
    public static String bfs(List<List<Integer>> graph, int start) {
        StringBuilder bfs = new StringBuilder();
        bfs.append(
                String.format("Breadth First Search from vertex %d:[", start)
        );

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[graph.size()];
        queue.add(start);

        while (!queue.isEmpty()) {
            int current = queue.remove();
            visited[current] = true;
            bfs.append(current).append(" ");

            // Traverse each neighbor of the current vertex
            for (Integer neighbor : graph.get(current)) {

                // Add the neighbor to the queue if it hasn't been visited yet
                if (!visited[neighbor]) {
                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }

        bfs.replace(bfs.length() - 1, bfs.length(), "]");
        return bfs.toString();
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
        //                    11
        List<List<Integer>> graph = createGraph(13);
        addUndirectedEdge(graph, 0, 7);
        addUndirectedEdge(graph, 0, 9);
        addUndirectedEdge(graph, 0, 11);
        addUndirectedEdge(graph, 7, 11);
        addUndirectedEdge(graph, 7, 6);
        addUndirectedEdge(graph, 7, 3);
        addUndirectedEdge(graph, 6, 5);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 2, 12);
        addUndirectedEdge(graph, 12, 8);
        addUndirectedEdge(graph, 8, 1);
        addUndirectedEdge(graph, 1, 10);
        addUndirectedEdge(graph, 10, 9);
        addUndirectedEdge(graph, 9, 8);

        out.println(bfs(graph, 0));

        // Output:
        // Breadth First Search from vertex 0:[0 7 9 11 6 3 10 8 5 4 2 1 12]
    }
}
