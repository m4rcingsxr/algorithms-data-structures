package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

public class SingleSourceShortestPathBetweenTwo {
    public static class Edge {
        int from;
        int next;

        public Edge(int from, int to) {
            this.from = from;
            this.next = to;
        }
    }


    // Start breadth first search on a graph starting from vertex
    // specified at 'start' index in the list
    // Helper algorithm for fill parent array which contains
    // 'parent' of each vertex
    public static void bfs(List<List<Edge>> graph, int start,
                           Integer[] parent) {
        final int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            List<Edge> edges = graph.get(vertex);

            // iterate through each vertex edges
            // and add all closest neighbors to the queue
            for (Edge edge : edges) {

                // Check if neighbor is already visited
                if (!visited[edge.next]) {
                    visited[edge.next] = true;

                    // parent length is number of all vertices
                    // Similarly to visited array for each slot that
                    // represent each vertex it contains data
                    // parent - parent of vertex, visited - was already
                    //                            vertex visited
                    parent[edge.next] = vertex;
                    queue.add(edge.next);
                }
            }
        }
    }

    // Single Source The Shortest Path Problem between 2 vertex
    // directed or undirected edges - only unweighted
    // Return empty list if vertices are not connected
    public static List<Integer> singleSourceShortestPath(List<List<Edge>> graph,
                                                         int start, int end) {
        Integer[] parent = new Integer[graph.size()];

        // Fill parent array with parents of vertices
        bfs(graph, start, parent);
        List<Integer> path = new ArrayList<>();

        // Iterate backward to get parent for each vertex
        // and fill path list from backward
        // current = null if vertex has no parent
        for (Integer current = end; current != null; current =
                parent[current]) {
            path.add(current);
        }

        Collections.reverse(path);

        // If backward iteration result compute other
        // result it means that vertices are not connected
        if (path.get(0) != start) {
            path.clear();
        }
        return path;
    }

    public static List<List<Edge>> createGraph(int noElements) {
        List<List<Edge>> graph = new ArrayList<>();

        // create noElements times new ArrayList
        // Each list represent vertex
        // Must be done to achieve access to index
        // that represent vertex in enclosing list
        for (int i = 0; i < noElements; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    public static void addDirectedEdge(List<List<Edge>> graph, int from,
                                       int to) {
        graph.get(from).add(new Edge(from, to));
    }

    public static void addUndirectedEdge(List<List<Edge>> graph, int from,
                                         int to) {
        addDirectedEdge(graph, from, to);
        addDirectedEdge(graph, to, from);
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
        List<List<Edge>> graph =
                SingleSourceShortestPathBetweenTwo.createGraph(13);
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

        List<Integer> path = singleSourceShortestPath(graph, 0, 5);
        path.forEach(e -> System.out.print(e + " "));

        // 0 7 6 5
    }

}
