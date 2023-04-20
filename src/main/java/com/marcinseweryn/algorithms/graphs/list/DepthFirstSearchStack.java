package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DepthFirstSearchStack {

    static class Edge {
        int from;
        int next;

        public Edge(int from, int to) {
            this.from = from;
            this.next = to;
        }
    }

    static void depthFirstSearch(List<List<Edge>> graph, int start,
                                 int noElements) {
        boolean[] visited = new boolean[noElements];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            System.out.print(vertex + " ");
            List<Edge> edges = graph.get(vertex);
            visited[vertex] = true;

            for (Edge edge : edges) {
                if (!visited[edge.next]) {

                    visited[vertex] = true;

                    // Last in with most dependencies
                    stack.push(edge.next);
                }
            }
        }
    }

    static void addDirectedEdge(List<List<Edge>> graph, int from, int to) {
        Edge edge = new Edge(from, to);
        graph.get(from).add(edge);
    }

    static void addUndirectedEdge(List<List<Edge>> graph, int from, int to) {
        addDirectedEdge(graph, from, to);
        addDirectedEdge(graph, to, from);
    }

    static List<List<Edge>> createEmptyGraph(int size) {
        List<List<Edge>> newGraph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newGraph.add(new ArrayList<>());
        }
        return newGraph;
    }

    public static void main(String[] args) {
        List<List<Edge>> graph = createEmptyGraph(5);

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
        depthFirstSearch(graph, 0, 5);

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
        depthFirstSearch(graph, 0, 13);

        //0 3 4 1 2 2 1
        //0 11 9 8 1 10 12 2 3 4 7 6 5 10 7
    }

}
