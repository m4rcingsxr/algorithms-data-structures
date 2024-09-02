package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * A utility class for performing depth-first search (DFS) on a graph using a stack.
 * This class also includes methods for creating graphs and adding edges.
 * The graph is represented using an adjacency list, which is efficient for sparse graphs.
 *
 * <h2>Complexity Analysis:</h2>
 * <h3>Time Complexity:</h3>
 * <p>
 * The time complexity of the DFS traversal using a stack is O(V + E), where V is the number of vertices
 * and E is the number of edges. This is because:
 * <ul>
 *   <li>Each vertex is pushed and popped from the stack exactly once.</li>
 *   <li>Each edge is considered once when exploring the adjacency list of each vertex.</li>
 * </ul>
 * Therefore, the total time complexity is linear with respect to the size of the graph.
 * </p>
 *
 * <h3>Space Complexity:</h3>
 * <p>
 * The space complexity of the DFS traversal is O(V), where V is the number of vertices. This is due to:
 * <ul>
 *   <li>The stack used to manage the DFS traversal, which may contain up to V vertices in the worst case.</li>
 *   <li>The `visited` array, which tracks the visited status of each vertex.</li>
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
 *
 * <h2>Implementation Notes:</h2>
 * <p>
 * The iterative approach with a stack is used to perform DFS, which avoids the potential
 * stack overflow issues that can occur with recursive DFS in deep or large graphs.
 * The `depthFirstSearch` method processes vertices and edges in an order determined
 * by the stack, ensuring that all reachable vertices are visited.
 * </p>
 */
public class DepthFirstSearchStack {

    /**
     * Performs depth-first search (DFS) on the given graph starting from the specified vertex.
     * This method uses an iterative approach with a stack to avoid recursion and prevent stack
     * overflow
     * in deep or large graphs. The function returns the DFS traversal as a list of visited
     * vertices.
     *
     * @param graph      the graph to search, represented as an adjacency list
     * @param start      the starting vertex for the DFS
     * @param noElements the number of vertices in the graph
     * @return a list of integers representing the order of visited vertices in DFS
     *
     * Time Complexity: O(V + E), where V is the number of vertices and E is the number of edges.
     * This is because each vertex is pushed and popped from the stack exactly once, and all
     * edges are considered once.
     *
     * Space Complexity: O(V), where V is the number of vertices (for the visited array and stack).
     * In the worst case, all vertices could be in the stack if the graph is a linear structure.
     */
    public static List<Integer> depthFirstSearch(List<List<Integer>> graph, int start,
                                                 int noElements) {
        List<Integer> result = new ArrayList<>(); // List to store the order of visited vertices.

        // Array to keep track of visited nodes to prevent revisiting and getting stuck in cycles.
        boolean[] visited = new boolean[noElements];

        // Using Deque as a stack for DFS to efficiently add and remove elements from the end.
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start); // Start the DFS from the given vertex.

        while (!stack.isEmpty()) { // Continue until all reachable vertices are visited.
            int vertex = stack.pop(); // Pop the top vertex from the stack.

            if (!visited[vertex]) { // If the vertex hasn't been visited yet, process it.
                result.add(vertex); // Add vertex to result list indicating it has been visited.
                visited[vertex] = true; // Mark the vertex as visited.

                // Retrieve all adjacent vertices (neighbors) of the current vertex.
                List<Integer> neighbors = graph.get(vertex);

                // Push all unvisited neighbors to the stack to continue the DFS traversal.
                for (Integer neighbor : neighbors) {
                    if (!visited[neighbor]) {

                        // *** visited[neighbor] = true; ***
                        // This prevents the node from being pushed onto the stack again, even if
                        // it's reachable through another path. However, if a cycle or different
                        // path leads to the same node, and the node is marked as visited
                        // prematurely, it might be ignored before it should be processed.
                        stack.push(neighbor);

                    }
                }
            }
        }

        return result; // Return the list of vertices in the order they were visited.
    }

    /**
     * Adds a directed edge from one vertex to another in the given graph.
     * This operation modifies the adjacency list of the graph.
     *
     * @param graph the graph to add the edge to, represented as an adjacency list
     * @param from  the source vertex of the edge
     * @param to    the target vertex of the edge
     */
    public static void addDirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to); // Add the directed edge from 'from' to 'to'.
    }

    /**
     * Adds an undirected edge between two vertices in the given graph.
     * This is achieved by adding two directed edges, one in each direction.
     *
     * @param graph the graph to add the edge to, represented as an adjacency list
     * @param from  one of the vertices to connect
     * @param to    the other vertex to connect
     */
    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        addDirectedEdge(graph, from, to); // Add edge from 'from' to 'to'.
        addDirectedEdge(graph, to, from); // Add edge from 'to' to 'from' to make it undirected.
    }

    /**
     * Creates an empty graph with the specified number of vertices.
     * The graph is represented as an adjacency list, with each vertex having its own list of
     * adjacent vertices.
     *
     * @param size the number of vertices in the graph
     * @return a new empty graph, represented as an adjacency list
     */
    public static List<List<Integer>> createEmptyGraph(int size) {
        List<List<Integer>> newGraph = new ArrayList<>(); // Initialize the list to hold
        // adjacency lists for each vertex.
        for (int i = 0; i < size; i++) {
            newGraph.add(new ArrayList<>()); // Create an empty list for each vertex.
        }
        return newGraph; // Return the newly created graph.
    }

    public static void main(String[] args) {
        List<List<Integer>> graph = createEmptyGraph(5);

        // 0 -- 1
        // | \    \
        // |  \    4
        // |   \  /
        // 2 -- 3
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 0, 2);
        addUndirectedEdge(graph, 0, 3);
        addUndirectedEdge(graph, 1, 4);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 3, 4);

        System.out.println(depthFirstSearch(graph, 0, 5));

        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
        graph = createEmptyGraph(13);
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

        System.out.println(depthFirstSearch(graph, 0, 13));

        // Expected outputs:
        // [0, 3, 4, 1, 2] - for the first graph
        // [0, 11, 9, 8, 1, 10, 12, 2, 3, 4, 7, 6, 5] - for the second graph
    }
}
