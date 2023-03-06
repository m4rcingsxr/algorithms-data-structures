package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

/**
 * Directed acyclic graphs only.
 */
public class TopologicalSort {
    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    // Find element without dependencies then return
    // updated index for next element
    private static int dfs(int index, int actual, boolean[] visited, int[] topologicalOrderding, Map<Integer, List<Edge>> graph) {
        if (visited[actual]) {
            return index;
        }

        visited[actual] = true;

        List<Edge> edges = graph.get(actual);

        if (edges != null) {

            // Loop through neighbors, no neighbors - base case
            for (Edge edge : edges) {

                // update index with depth first search
                index = dfs(index, edge.to, visited, topologicalOrderding, graph);
            }
        }

        // element without neighbors is the element
        // with most dependencies (base case)
        topologicalOrderding[index] = actual;
        return index - 1;
    }

    // Algorithm that's executing recursive depth first search for arbitrary
    // nodes. Return array that contains topological sort of given graph
    // noNodes is no necessarily the number of nodes current existing
    // in graph! Graph don't contains real numbers of elements
    public static int[] topologicalSort(Map<Integer, List<Edge>> graph, int noNodes) {

        int[] ordering = new int[noNodes];
        boolean[] visited = new boolean[noNodes];

        int index = noNodes - 1;
        for (int actual = 0; actual < noNodes; actual++) {

            // If vertex is not visited then perform
            // depth first search on this vertex and
            // fill ordering array from backward if
            // vertex does not have a children
            if (!visited[actual]) {

                // We update index after each addition
                // to the ordering array
                index = dfs(index, actual, visited, ordering, graph);
            }
        }

        return ordering;
    }

    public static void main(String[] args) {
        // Graph setup
        final int N = 13;
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        graph.get(0).add(new Edge(0, 11));
        graph.get(0).add(new Edge(0, 7));
        graph.get(0).add(new Edge(0, 9));
        graph.get(9).add(new Edge(9, 10));
        graph.get(10).add(new Edge(10, 1));
        graph.get(1).add(new Edge(1, 8));
        graph.get(8).add(new Edge(8, 12));
        graph.get(7).add(new Edge(7, 3));
        graph.get(7).add(new Edge(7, 6));
        graph.get(6).add(new Edge(6, 5));
        graph.get(3).add(new Edge(3, 2));
        graph.get(2).add(new Edge(2, 4));

        int[] orderding = topologicalSort(graph, N);

        // [0, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4, 11]
        System.out.println("TOPOLOGICAL ORDERING\n" + Arrays.toString(orderding));
    }
}
