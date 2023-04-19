package com.marcinseweryn.algorithms.graphs.matrix;


import java.util.Arrays;

/**
 * This class provides a topological sort implementation using
 * a recursive depth-first search algorithm.
 */
public class TopologicalSortRecursive {

    /**
     * Returns the topological ordering of the given directed
     * acyclic graph represented as an adjacency matrix.
     *
     * @param graph an N x N adjacency matrix representing a directed
     *             acyclic graph
     * @return an array of integers representing the topological ordering
     *         of the graph
     */
    public static int[] getTopologicalOrder(int[][] graph) {
        int N = graph.length;
        boolean[] visited = new boolean[N];
        int[] order = new int[N];
        int index = N - 1;

        for (int i = 0; i < N; i++) {

            // Ensure that each of vertex was processed in case
            // of starting from the vertex that has dependencies
            if (!visited[i]) {
                index = dfs(graph, visited, index, i, order);
            }
        }
        return order;
    }

    /**
     * Recursively performs a depth-first search on the given directed
     * acyclic graph to determine the topological ordering.
     *
     * @param graph   an N x N adjacency matrix representing a directed
     *                acyclic graph
     * @param visited an array indicating which vertices have been visited
     * @param index   the current index of the order array to update
     * @param actual  the vertex to start the search from
     * @param order   an array of integers representing the topological
     *                ordering of the graph
     * @return the updated index for the order array after processing the
     *         current vertex and its neighbors
     */
    public static int dfs(int[][] graph,
                          boolean[] visited,
                          int index,
                          int actual,
                          int[] order) {

        // If already visited continue to go deeper
        // with the same index.
        if (visited[actual]) {
            return index;
        }

        // Ensure that vertex won't be visited twice
        visited[actual] = true;

        // recursive call for all the vertex neighbors
        // Continue if vertex has no more neighbors
        for (int i = 0; i < graph.length; i++) {
            if (graph[actual][i] != 0 && !visited[i]) {
                index = dfs(graph, visited, index, i, order);
            }
        }

        // return updated index for stopped recursive calls
        order[index] = actual;
        return index - 1;
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
     * Adds a directed edge from a source vertex to a target vertex
     * in the given adjacency matrix.
     *
     * @param graph the N x N adjacency matrix representing a directed
     *             acyclic graph
     * @param from            the source vertex
     * @param to              the target vertex
     */
    public static void addDirectedEdge(int[][] graph, int from, int to) {
        graph[from][to] = 1;
    }

    public static void main(String[] args) {

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        final int N = 13;
        int[][] graph = createGraph(N);
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

        int[] order = getTopologicalOrder(graph);
        System.out.println(Arrays.toString(order));

        // Output:
        //[0, 11, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4]

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >0      >5
        //                  \       /       /
        //                   3  --> 7  --> 6
        //                    \
        //                    >11
        int[][] graph2 = TopologicalSortRecursive.createGraph(13);
        addDirectedEdge(graph2, 3, 11);
        addDirectedEdge(graph2, 3, 7);
        addDirectedEdge(graph2, 3, 9);
        addDirectedEdge(graph2, 9, 10);
        addDirectedEdge(graph2, 10, 1);
        addDirectedEdge(graph2, 1, 8);
        addDirectedEdge(graph2, 8, 12);
        addDirectedEdge(graph2, 7, 0);
        addDirectedEdge(graph2, 7, 6);
        addDirectedEdge(graph2, 6, 5);
        addDirectedEdge(graph2, 0, 2);
        addDirectedEdge(graph2, 2, 4);
        order = getTopologicalOrder(graph2);
        System.out.println(Arrays.toString(order));
    }
}

