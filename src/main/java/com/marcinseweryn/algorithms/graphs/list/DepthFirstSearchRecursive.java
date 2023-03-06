package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearchRecursive {
    static class Edge {
        int next;
        int from;

        public Edge(int from, int next) {
            this.from = from;
            this.next = next;
        }
    }

    public static void printDepthFirstSearch(Map<Integer, List<Edge>> graph, int start, int noOfVertex) {
        boolean[] visited = new boolean[noOfVertex];
        printDepthFirstSearch(graph, visited, start);
    }

    private static void printDepthFirstSearch(Map<Integer, List<Edge>> graph, boolean[] visited, int actual) {

        // Base case
        if (visited[actual]) {
            return;
        }
        visited[actual] = true;

        // Print current vertex
        System.out.print(actual + " ");
        List<Edge> edges = graph.get(actual);
        if (edges != null) {
            for (Edge edge : edges) {
                printDepthFirstSearch(graph, visited, edge.next);
            }
        }
    }

    public static void directedEdge(Map<Integer, List<Edge>> graph, int from, int next) {
        List<Edge> list = graph.computeIfAbsent(from, k -> new ArrayList<>());
        list.add(new Edge(from, next));
    }

    public static void addUndirectedEdge(Map<Integer, List<Edge>> graph, int from, int next) {
        directedEdge(graph, from, next);
        directedEdge(graph, next, from);
    }

    public static void main(String[] args) {
        Map<Integer, List<Edge>> graph = new HashMap<>();

        // A -- B
        // | \    \
        // |  \    E
        // |   \  /
        // C -- D
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 0, 2);
        addUndirectedEdge(graph, 0, 3);
        addUndirectedEdge(graph, 1, 4);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 3, 4);
        printDepthFirstSearch(graph, 0, 5);

        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
        System.out.println();
        graph = new HashMap<>();
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
        printDepthFirstSearch(graph, 0, 13);

        /* OUTPUT:
                    0 1 4 3 2
                    0 7 6 5 3 4 2 12 8 1 10 9 11
         */
    }
}
