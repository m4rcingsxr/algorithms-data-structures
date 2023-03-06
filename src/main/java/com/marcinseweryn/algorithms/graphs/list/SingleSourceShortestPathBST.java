package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

import static java.lang.System.out;

public class SingleSourceShortestPathBST {

    static class Edge {
        int next;
        int from;

        public Edge(int from, int to) {
            this.next = to;
            this.from = from;
        }
    }

    static Integer[] parent;

    public static void addDirectedEdge(List<List<Edge>> graph, int from, int to) {
        graph.get(from).add(new Edge(from, to));
    }

    public static void addUndirectedEdge(List<List<Edge>> graph, int from, int to) {
        addDirectedEdge(graph, from, to);
        addDirectedEdge(graph, to, from);
    }

    static List<List<Edge>> createGraph(int size) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static void sssppBST(List<List<Edge>> graph, int start) {
        parent = new Integer[graph.size()];
        bst(graph, parent, start);
        for (int i = 0; i < graph.size(); i++) {
            out.print("Shortest path[" + start + "->" + i + "]: ");

            printPath(i);
            out.println();
        }
    }

    public static void printPath(int node) {
        if (parent[node] != null) {
            printPath(parent[node]);
        }
        out.print(node + " ");
    }

    private static void reconstructSSSPP(int start, int end, Integer[] parent) {
        List<Integer> path = new ArrayList<>();
        for (Integer i = end; i != null; i = parent[i]) {
            path.add(i);
        }
        Collections.reverse(path);

        if (path.get(0) != start) {
            out.println("No connected");
        } else {
            out.println(path);
        }
    }

    private static void bst(List<List<Edge>> graph, Integer[] parent, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.remove();
            List<Edge> edges = graph.get(current);
            assert edges != null;
            for (Edge edge : edges) {
                if (!visited[edge.next]) {
                    visited[edge.next] = true;
                    queue.add(edge.next);
                    parent[edge.next] = current;
                }
            }
        }
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
        //                      11
        List<List<Edge>> graph = SingleSourceShortestPathBST.createGraph(13);
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

        SingleSourceShortestPathBST.sssppBST(graph, 0);

        //Shortest path[0->0]: 0
        //Shortest path[0->1]: 0 9 10 1
        //Shortest path[0->2]: 0 7 3 2
        //Shortest path[0->3]: 0 7 3
        //Shortest path[0->4]: 0 7 3 4
        //Shortest path[0->5]: 0 7 6 5
        //Shortest path[0->6]: 0 7 6
        //Shortest path[0->7]: 0 7
        //Shortest path[0->8]: 0 9 8
        //Shortest path[0->9]: 0 9
        //Shortest path[0->10]: 0 9 10
        //Shortest path[0->11]: 0 11
        //Shortest path[0->12]: 0 9 8 12
    }

}
