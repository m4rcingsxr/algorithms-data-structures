package com.marcinseweryn.algorithms.graphs.list;

import com.marcinseweryn.algorithms.datastructures.queue.LinkedListQueue;
import com.marcinseweryn.algorithms.datastructures.queue.Queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

/**
 * The BreadthFirstSearch class provides methods for creating and
 * traversing an undirected graph using the Breadth-First Search (BFS) algorithm.
 * It allows for the creation of a graph with a specified number of vertices,
 * adding undirected edges, and performing BFS traversal to explore the graph.
 *
 * <h2>Complexity Analysis:</h2>
 * <h3>Time Complexity:</h3>
 * <p>
 * The time complexity of the BFS traversal is O(V + E), where V is the number of vertices
 * and E is the number of edges. This is because:
 * <ul>
 *   <li>Each vertex is enqueued and dequeued exactly once.</li>
 *   <li>Each edge is considered once when exploring the adjacency list of each vertex.</li>
 * </ul>
 * Therefore, the overall time complexity is linear with respect to the size of the graph.
 * </p>
 *
 * <h3>Space Complexity:</h3>
 * <p>
 * The space complexity of the BFS traversal is O(V), where V is the number of vertices. This is due to:
 * <ul>
 *   <li>The queue used to manage the BFS traversal, which holds at most V vertices in the worst case.</li>
 *   <li>The visited array, which tracks whether each vertex has been visited.</li>
 * </ul>
 * Thus, the space complexity is proportional to the number of vertices in the graph.
 * </p>
 *
 * <h2>Graph Representation:</h2>
 * <p>
 * The graph is represented using an adjacency list, where each vertex has a list of
 * connected vertices. This representation is efficient for sparse graphs and allows
 * for quick access to the neighbors of any given vertex.
 * </p>
 */
public class BreadthFirstSearch {

    // Private constructor to prevent instantiation of this utility class
    private BreadthFirstSearch() {}

    /**
     * Creates a new graph with the given number of vertices.
     * The graph is represented as an adjacency list, where each vertex
     * has a list of connected vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return the new graph as a list of adjacency lists
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        // Initialize the graph with empty adjacency lists for each vertex
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Adds an undirected edge between the two given vertices in the provided graph.
     * This means both vertices will be added to each other's adjacency lists.
     *
     * @param graph the graph to add the edge to
     * @param from  the vertex the edge starts from
     * @param to    the vertex the edge goes to
     */
    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    /**
     * Performs a breadth-first search traversal of the given graph starting
     * from the given vertex. It explores all vertices at the present depth level
     * before moving on to vertices at the next depth level.
     *
     * @param graph the graph to traverse
     * @param start the starting vertex for the traversal
     * @return a list of integers representing the order of traversal
     *
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     * Each vertex is processed once and each edge is considered once.
     *
     * Space Complexity: O(V), where V is the number of vertices.
     * This is for the queue and visited array, which store information for each vertex.
     */
    public static List<Integer> bfs(List<List<Integer>> graph, int start) {
        List<Integer> result = new ArrayList<>(); // List to store the BFS traversal order

        Queue<Integer> queue = new LinkedListQueue<>(); // Queue for managing the BFS
        boolean[] visited = new boolean[graph.size()]; // Array to track visited vertices

        // Start BFS from the initial vertex
        queue.enqueue(start);
        visited[start] = true; // Mark the start vertex as visited

        while (!queue.isEmpty()) {
            // Dequeue a vertex from the queue and mark it as visited
            int current = queue.dequeue();
            result.add(current); // Add the current vertex to the result list

            // Check all adjacent vertices of the current vertex
            for (int neighbor : graph.get(current)) {
                // If the neighbor has not been visited, enqueue it and mark as visited
                if (!visited[neighbor]) {
                    queue.enqueue(neighbor);
                    visited[neighbor] = true; // Mark as visited to avoid re-queuing
                }
            }
        }

        return result; // Return the BFS traversal order
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

        // Perform BFS starting from vertex 0 and print the traversal order
        // Expected output: [0, 7, 9, 11, 6, 3, 10, 8, 5, 4, 2, 1, 12]
        System.out.println(bfs(graph, 0));
    }
}
