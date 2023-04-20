package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class provides an implementation of topological sort algorithm for
 * directed acyclic graphs.
 * Topological sort is an algorithm that sorts the nodes of a directed
 * acyclic graph in such a way that
 * every directed edge from vertex u to vertex v satisfies u comes before v
 * in the ordering.
 */
public class TopologicalSort {

    /**
     * Creates a new empty graph with the specified number of vertices.
     *
     * @param noVertices the number of vertices to be included in the graph
     * @return a new empty graph represented as a List of Lists of Integers
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Adds a directed edge to the graph connecting the specified source and
     * target vertices.
     *
     * @param graph the graph to which the edge is to be added
     * @param from  the source vertex of the edge
     * @param to    the target vertex of the edge
     */
    public static void addDirectedEdge(List<List<Integer>> graph, int from,
                                       int to) {
        graph.get(from).add(to);
    }

    /**
     * Private helper method that performs depth first search on the given
     * vertex and returns an updated
     * index for the next element.
     *
     * @param graph   the graph to be searched
     * @param index   the current index in the ordering array
     * @param actual  the vertex currently being searched
     * @param visited an array of booleans indicating whether a node has been
     *                visited or not
     * @param order   an array of integers containing the topological
     *                ordering of the nodes in the graph
     * @return an integer representing the updated index for the next element
     */
    private static int dfs(List<List<Integer>> graph, int index, int actual,
                           boolean[] visited, int[] order) {
        if (visited[actual]) {
            return index;
        }
        visited[actual] = true;

        // Loop through neighbors, no neighbors - base case
        for (Integer neighbor : graph.get(actual)) {

            // update index with depth first search
            index = dfs(graph, index, neighbor, visited, order);
        }

        // element without neighbors is the element
        // with most dependencies
        order[index] = actual;
        return index - 1;
    }

    /**
     * Executes recursive depth first search for arbitrary nodes and returns
     * an array that contains
     * topological sort of the given graph.
     *
     * @param graph the graph to be sorted
     * @return an array of integers representing the topological ordering of
     * the nodes in the graph
     */
    public static int[] topologicalSort(List<List<Integer>> graph) {
        int N = graph.size();
        int[] order = new int[N];
        boolean[] visited = new boolean[N];
        int index = N - 1;

        for (int actual = 0; actual < N; actual++) {

            // If vertex is not visited then perform
            // depth first search on this vertex and
            // fill order array from backward if
            // vertex does not have a children
            if (!visited[actual]) {

                // We update index after each addition
                // to the order array
                index = dfs(graph, index, actual, visited, order);
            }
        }

        return order;
    }

    public static void main(String[] args) {
        // Graph setup
        final int N = 13;
        List<List<Integer>> graph = createGraph(N);

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
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

        // [0, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4, 11]
        System.out.println("TOPOLOGICAL ORDERING\n" +
                                   Arrays.toString(topologicalSort(graph))
        );
    }
}
