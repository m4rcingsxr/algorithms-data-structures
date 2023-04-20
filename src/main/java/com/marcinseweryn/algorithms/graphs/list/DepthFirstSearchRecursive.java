package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods for creating and performing depth-first search
 * (DFS)
 * on a graph using recursion. The class contains utility methods for
 * creating a graph
 * with a specified number of vertices, adding edges to the graph (both
 * directed and
 * undirected),and performing DFS on the graph starting from a specified vertex.
 */
public class DepthFirstSearchRecursive {


    private DepthFirstSearchRecursive() {
    }

    /**
     * Creates an adjacency list representation of the graph with
     * noVertices vertices.
     *
     * @param noVertices the number of vertices in the graph
     * @return the adjacency list representation of the graph
     */
    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    /**
     * Adds an undirected edge between vertices 'from' and 'to' to the graph.
     *
     * @param graph the graph to add the edge to
     * @param from  the starting vertex of the edge
     * @param to    the ending vertex of the edge
     */
    public static void addUndirectedEdge(List<List<Integer>> graph, int from,
                                         int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    /**
     * Adds a directed edge from vertex 'from' to vertex 'to' to the graph.
     *
     * @param graph the graph to add the edge to
     * @param from  the starting vertex of the edge
     * @param to    the ending vertex of the edge
     */
    public static void addDirectedEdge(List<List<Integer>> graph, int from,
                                       int to) {
        graph.get(from).add(to);
    }

    /**
     * Performs a Depth First Search on the graph starting from the vertex
     * 'start'.
     * Returns a string representation of the DFS path.
     *
     * @param graph the graph to perform DFS on
     * @param start the starting vertex for the DFS
     * @return a string representation of the DFS path
     */
    public static String dfs(List<List<Integer>> graph, int start) {
        StringBuilder dfsPath = new StringBuilder(
                "Depth First Search starting from vertex "
                        + start + ":["
        );
        boolean[] visited = new boolean[graph.size()];
        dfs(graph, visited, start, dfsPath);

        // Replaces the last ',' in the DFS path with ']'
        // and returns the resulting string.
        return dfsPath.replace(
                dfsPath.length() - 1, dfsPath.length(), "]"
        ).toString();
    }

    /**
     * Helper method to perform DFS recursively.
     *
     * @param graph   the graph to perform DFS on
     * @param visited an array to keep track of visited vertices
     * @param index   the current vertex in the DFS
     * @param dfsPath the StringBuilder object to build the DFS path
     */
    private static void dfs(List<List<Integer>> graph,
                            boolean[] visited,
                            int index,
                            StringBuilder dfsPath) {

        // If the vertex at 'index' has already
        // been visited, return from the method.
        if (visited[index]) return;

        // Append the vertex at 'index' to the DFS
        // path and mark it as visited.
        dfsPath.append(index).append(",");
        visited[index] = true;

        // For each unvisited neighbor of the vertex
        // at 'index', perform DFS recursively.
        for (Integer neighbor : graph.get(index)) {
            dfs(graph, visited, neighbor, dfsPath);
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> graph = createGraph(13);


        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
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

        System.out.println(dfs(graph, 0));

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        List<List<Integer>> graph2 = createGraph(13);
        addDirectedEdge(graph2, 0, 11);
        addDirectedEdge(graph2, 0, 7);
        addDirectedEdge(graph2, 0, 9);
        addDirectedEdge(graph2, 9, 10);
        addDirectedEdge(graph2, 10, 1);
        addDirectedEdge(graph2, 1, 8);
        addDirectedEdge(graph2, 8, 12);
        addDirectedEdge(graph2, 7, 3);
        addDirectedEdge(graph2, 7, 6);
        addDirectedEdge(graph2, 6, 5);
        addDirectedEdge(graph2, 3, 2);
        addDirectedEdge(graph2, 2, 4);
        System.out.println(dfs(graph2, 0));
    }

}





