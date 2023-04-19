package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearchRecursive {

    private DepthFirstSearchRecursive() {
        // Utility class
    }

    public static List<List<Integer>> createGraph(int noVertices) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }

        return graph;
    }

    public static void addUndirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    public static void addDirectedEdge(List<List<Integer>> graph, int from, int to) {
        graph.get(from).add(to);
    }


    public static String dfs(List<List<Integer>> graph, int start) {
        StringBuilder dfsPath = new StringBuilder(
                "Depth First Search starting from vertex "
                        + start + ":["
        );
        boolean[] visited = new boolean[graph.size()];
        dfs(graph, visited, start, dfsPath);

        return dfsPath.replace(
                dfsPath.length() - 1, dfsPath.length(), "]"
        ).toString();
    }

    private static void dfs(List<List<Integer>> graph,
                            boolean[] visited,
                            int index,
                            StringBuilder dfsPath) {
        if (visited[index]) return;

        dfsPath.append(index).append(",");
        visited[index] = true;
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





