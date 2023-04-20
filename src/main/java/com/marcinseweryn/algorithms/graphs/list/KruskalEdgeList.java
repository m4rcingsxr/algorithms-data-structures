package com.marcinseweryn.algorithms.graphs.list;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represent implementation of the Kruskal's minimum
 * spanning tree algorithm.
 * <p>
 * Time Complexity: O(NlogN), N - number of elements
 */
public class KruskalEdgeList {

    static class UnionFind {
        private int[] parent;
        private int[] componentSize;

        public UnionFind(int e) {
            parent = new int[e];
            componentSize = new int[e];
            for (int i = 0; i < e; i++) {
                parent[i] = i;
                componentSize[i] = 1;
            }
        }

        public int find(int p) {
            int root = p;
            while (root != parent[root]) {
                root = parent[root];
            }
            while (p != root) {
                int next = parent[p];
                parent[p] = root;
                p = next;
            }
            return root;
        }

        public int size(int p) {
            return componentSize[find(p)];
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int root1 = find(p);
            int root2 = find(q);
            if (root1 == root2) {
                return;
            }
            if (componentSize[root1] < componentSize[root2]) {
                componentSize[root2] += componentSize[root1];
                parent[root1] = root2;
            } else {
                componentSize[root1] += componentSize[root2];
                parent[root2] = root1;
            }
        }
    }

    static class Edge {
        int from;
        int next;
        int weight;

        public Edge(int from, int next, int weight) {
            this.from = from;
            this.next = next;
            this.weight = weight;
        }
    }

    /**
     * This method takes a graph represented as an edge list and
     * finds the cost of the Minimum Spanning Tree (MST) if one
     * exists. If no MST exists, the method returns null.
     *
     * @param edges      list of edges represent graph
     * @param noElements number of vertices
     * @return cost of the Minimum Spanning Tree (MST)
     */
    static Long Kruskals(Edge[] edges, int noElements) {
        if (edges == null) return null;

        long sum = 0L;
        Arrays.sort(edges, Comparator.comparingInt(e -> e.weight));
        UnionFind unionFind = new UnionFind(noElements);

        for (Edge edge : edges) {

            // Skip already connected edges to avoid creating a cycle in MST
            if (unionFind.connected(edge.from, edge.next)) {
                continue;
            }

            // Otherwise include this edge, merge to the same component
            // and reconstruct path for efficiency
            unionFind.union(edge.from, edge.next);
            sum += edge.weight;

            // Early stopping optimization: if we find a MST
            // that includes all nodes, we can stop the algorithm early.
            if (unionFind.size(0) == noElements) {
                break;
            }

        }

        // Make sure we have a MST that includes all the nodes
        if (unionFind.size(0) != noElements) return null;

        return sum;
    }

    public static void main(String[] args) {

        // this is the same graph I come up with in my
        // notes on this algorithm
        Edge[] edges = new Edge[18];
        edges[0] = new Edge(0, 1, 5);
        edges[1] = new Edge(1, 2, 4);
        edges[2] = new Edge(2, 9, 2);
        edges[3] = new Edge(0, 4, 1);
        edges[4] = new Edge(0, 3, 4);
        edges[5] = new Edge(1, 3, 2);
        edges[6] = new Edge(2, 7, 4);
        edges[7] = new Edge(2, 8, 1);
        edges[8] = new Edge(9, 8, 0);
        edges[9] = new Edge(4, 5, 1);
        edges[10] = new Edge(5, 6, 7);
        edges[11] = new Edge(6, 8, 4);
        edges[12] = new Edge(4, 3, 2);
        edges[13] = new Edge(5, 3, 5);
        edges[14] = new Edge(3, 6, 11);
        edges[15] = new Edge(6, 7, 1);
        edges[16] = new Edge(3, 7, 2);
        edges[17] = new Edge(7, 8, 6);

        Long msp = Kruskals(edges, 10);
        System.out.printf("Minimum spanning tree result=%d", msp);
    }
}
