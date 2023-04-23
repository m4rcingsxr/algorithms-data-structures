package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

public class LazyPrims {
    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return weight - o.weight;
        }

        @Override
        public String toString() {
            return System.lineSeparator() + "Edge{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to && weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }
    }

    public static class Result {

        private final int mstSum;
        private final Edge[] mst;

        public Result(int mstSum, Edge[] mst) {
            this.mstSum = mstSum;
            this.mst = mst;
        }

        @Override
        public String toString() {
            if (mstSum == -1) {
                return "Minimum Spanning Tree does not exist";
            }
            Arrays.sort(mst, Comparator.comparingInt(e -> e.to));
            return String.format("Minimum Spanning Tree:%nEdges:%s%nSum:%d",
                                 Arrays.toString(mst), mstSum
            );
        }
    }

    public static Result prims(List<List<Edge>> graph) {
        int mstSize = 0;
        int mstSum = 0;

        //number of edges in MST - maximum path from vertex
        // a to vertex b is V - 1
        int maxPath = graph.size() - 1;
        Edge[] mst = new Edge[maxPath];
        boolean[] visited = new boolean[graph.size()];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        addEdges(graph, pq, 0, visited);

        while (!pq.isEmpty() && mstSize != maxPath) {
            Edge current = pq.poll();
            int nodeIndex = current.to;

            // skip edge that points to a visited node
            if (visited[nodeIndex]) {
                continue;
            }

            mst[mstSize++] = current;
            mstSum += current.weight;
            addEdges(graph, pq, nodeIndex, visited);
        }
        if (mstSize != maxPath) {
            return new Result(-1, null);
        }

        return new Result(mstSum, mst);
    }

    private static void addEdges(List<List<Edge>> graph, PriorityQueue<Edge> pq,
                                 int index, boolean[] visited) {
        visited[index] = true;
        List<Edge> neighbors = graph.get(index);
        for (Edge neighbor : neighbors) {
            if (!visited[neighbor.to]) {
                pq.offer(neighbor);
            }
        }
    }

    public static List<List<Edge>> createGraph(int noVertices) {
        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i < noVertices; i++) {
            graph.add(new ArrayList<>());
        }
        return graph;
    }

    private static void addDirectedEdge(List<List<Edge>> graph, int from,
                                        int to, int weight) {
        graph.get(from).add(new Edge(from, to, weight));
    }

    public static void addUndirectedEdge(List<List<Edge>> graph, int from,
                                         int to, int weight) {
        addDirectedEdge(graph, from, to, weight);
        addDirectedEdge(graph, to, from, weight);
    }

    public static void main(String[] args) {

        //                    (0)
        //                1 ------- 4
        //          (10)/ |(3)   (1)| \(8)
        //             0--2-------- 5-----7
        //             \(1)|  (8)  /| (9)/
        //          (4) \  |(2) /(2)|   / (12)
        //               \ | /      |  /
        //                3 ------- 6
        //                    (7)
        int n = 8;
        List<List<Edge>> g = createGraph(n);

        addDirectedEdge(g, 0, 1, 10);
        addDirectedEdge(g, 0, 2, 1);
        addDirectedEdge(g, 0, 3, 4);

        addDirectedEdge(g, 2, 1, 3);
        addDirectedEdge(g, 2, 5, 8);
        addDirectedEdge(g, 2, 3, 2);
        addDirectedEdge(g, 2, 0, 1);

        addDirectedEdge(g, 3, 2, 2);
        addDirectedEdge(g, 3, 5, 2);
        addDirectedEdge(g, 3, 6, 7);
        addDirectedEdge(g, 3, 0, 4);

        addDirectedEdge(g, 5, 2, 8);
        addDirectedEdge(g, 5, 4, 1);
        addDirectedEdge(g, 5, 7, 9);
        addDirectedEdge(g, 5, 6, 6);
        addDirectedEdge(g, 5, 3, 2);

        addDirectedEdge(g, 4, 1, 0);
        addDirectedEdge(g, 4, 5, 1);
        addDirectedEdge(g, 4, 7, 8);

        addDirectedEdge(g, 1, 0, 10);
        addDirectedEdge(g, 1, 2, 3);
        addDirectedEdge(g, 1, 4, 0);

        addDirectedEdge(g, 6, 3, 7);
        addDirectedEdge(g, 6, 5, 6);
        addDirectedEdge(g, 6, 7, 12);

        addDirectedEdge(g, 7, 4, 8);
        addDirectedEdge(g, 7, 5, 9);
        addDirectedEdge(g, 7, 6, 12);
        System.out.println(prims(g));
    }
}
