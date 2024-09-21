package com.marcinseweryn.algorithms.graphs.matrix;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * The TopologicalSortStack class provides methods to perform topological sorting
 * on a directed acyclic graph (DAG) using a stack-based iterative depth-first search (DFS) approach.
 *
 * <p>Topological sorting orders the vertices of a DAG such that for every directed edge from vertex u to vertex v,
 * u comes before v in the ordering. This class uses a stack to achieve this order, which can be beneficial
 * to avoid recursion depth issues and to provide an iterative solution.</p>
 *
 * <h2>Time Complexity</h2>
 * <p>The time complexity of this topological sorting algorithm is O(V + E), where V is the number of vertices
 * and E is the number of edges. Each vertex and edge is processed at most once during the DFS traversal.</p>
 *
 * <h2>Space Complexity</h2>
 * <p>The space complexity is O(V), where V is the number of vertices. This includes space for the visited array
 * and the stack used for DFS traversal, as well as the order array used to store the topological order.</p>
 */
public class TopologicalSortStack {

    // Private constructor to prevent instantiation
    private TopologicalSortStack() {
    }

    /**
     * Performs a topological sort on a directed acyclic graph (DAG) using an iterative stack-based DFS approach.
     *
     * @param graph the graph to sort, represented as an adjacency matrix where graph[i][j] != 0 indicates an edge from vertex i to vertex j
     * @return an array representing the topologically sorted order of the graph's vertices
     */
    public static int[] getTopologicalOrder(int[][] graph) {
        int N = graph.length; // Number of vertices in the graph
        int[] order = new int[N]; // Array to store the topological order
        boolean[] visited = new boolean[N]; // Array to track visited vertices
        Deque<Integer> stack = new ArrayDeque<>(); // Stack for DFS traversal
        int index = N - 1; // Start filling the order array from the end

        // Iterate over each vertex to perform DFS
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                stack.push(i); // Start DFS from the unvisited vertex

                while (!stack.isEmpty()) {
                    int vertex = stack.peek();

                    if (!visited[vertex]) {
                        visited[vertex] = true; // Mark the vertex as visited

                        // Push all unvisited neighbors onto the stack
                        for (int j = graph[vertex].length - 1; j >= 0; j--) {
                            if (graph[vertex][j] != 0 && !visited[j]) {
                                stack.push(j);
                            }
                        }
                    } else {
                        // If all neighbors are processed, add to result order and pop
                        if (stack.peek() == vertex) {
                            stack.pop();
                            order[index--] = vertex;
                        }
                    }
                }
            }
        }

        return order; // Return the topological order
    }

    /**
     * Creates a new N x N adjacency matrix for a directed acyclic graph.
     *
     * @param N the number of vertices in the graph
     * @return a new N x N adjacency matrix
     */
    public static int[][] createGraph(final int N) {
        return new int[N][N];
    }

    /**
     * Adds a directed edge from a source vertex to a target vertex in the given adjacency matrix.
     *
     * @param graph the N x N adjacency matrix representing a directed acyclic graph
     * @param from  the source vertex
     * @param to    the target vertex
     */
    public static void addDirectedEdge(int[][] graph, int from, int to) {
        graph[from][to] = 1;
    }

    public static void main(String[] args) {
        final int N = 13; // Number of vertices in the sample graph
        int[][] graph = createGraph(N); // Create the graph with N vertices

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        // Add directed edges to the graph to represent the DAG
        addDirectedEdge(graph, 0, 11);
        addDirectedEdge(graph, 0, 7);
        addDirectedEdge(graph, 0, 9);
        addDirectedEdge(graph, 9, 10);
        addDirectedEdge(graph, 10, 1);
        addDirectedEdge(graph, 1, 8);
        addDirectedEdge(graph, 8, 12);
        addDirectedEdge(graph, 7, 3);
        addDirectedEdge(graph, 7, 6);
        addDirectedEdge(graph, 6, 5);
        addDirectedEdge(graph, 3, 2);
        addDirectedEdge(graph, 2, 4);

        // Perform topological sort on the graph
        int[] ordering = getTopologicalOrder(graph);

        // Print the expected topological order
        System.out.println(Arrays.toString(ordering)); // [0, 9, 10, 1, 8, 12, 7, 3, 2, 4, 6, 5, 11]
    }
}
