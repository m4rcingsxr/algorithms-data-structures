package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

public class TopologicalSortStack {
    static class Edge {
        int next;
        int from;

        public Edge(int from, int next) {
            this.from = from;
            this.next = next;
        }
    }


    static int[] topologicalSort(Map<Integer, List<Edge>> graph, int noElements) {
        boolean[] visited = new boolean[noElements];
        Deque<Integer> stack = new ArrayDeque<>();
        int[] order = new int[noElements];
        for (int actual = 0; actual < noElements; actual++) {
            if (!visited[actual]) {
                dfs(stack, actual, graph, visited, order);
            }
        }

        return order;
    }

    private static void dfs(Deque<Integer> stack, int start, Map<Integer, List<Edge>> graph, boolean[] visited, int[] order) {
        stack.push(start);
        int i = 0;
        while (!stack.isEmpty()) {
            int current = stack.pop();
            visited[current] = true;
            order[i++] = current;
            List<Edge> neighbors = graph.get(current);
            for (Edge neighbor : neighbors) {
                if (!visited[neighbor.next]) {
                    visited[current] = true;
                    stack.push(neighbor.next);
                }
            }
        }
    }


    public static void main(String[] args) {
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
