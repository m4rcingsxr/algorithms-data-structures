package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopologicalSortRecursiveTest {

    @Test
    void givenDAG_whenTopologicalSort_thenCorrectOrder() {
        // Given a directed acyclic graph (DAG)
        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        final int N = 13;
        int[][] graph = TopologicalSortRecursive.createGraph(N);
        TopologicalSortRecursive.addDirectedEdge(graph, 0, 11);
        TopologicalSortRecursive.addDirectedEdge(graph, 0, 7);
        TopologicalSortRecursive.addDirectedEdge(graph, 0, 9);
        TopologicalSortRecursive.addDirectedEdge(graph, 9, 10);
        TopologicalSortRecursive.addDirectedEdge(graph, 10, 1);
        TopologicalSortRecursive.addDirectedEdge(graph, 1, 8);
        TopologicalSortRecursive.addDirectedEdge(graph, 8, 12);
        TopologicalSortRecursive.addDirectedEdge(graph, 7, 3);
        TopologicalSortRecursive.addDirectedEdge(graph, 7, 6);
        TopologicalSortRecursive.addDirectedEdge(graph, 6, 5);
        TopologicalSortRecursive.addDirectedEdge(graph, 3, 2);
        TopologicalSortRecursive.addDirectedEdge(graph, 2, 4);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortRecursive.getTopologicalOrder(graph);
        System.out.println(Arrays.toString(topologicalOrder));

        // Then the topological order should be correct
        int[] expectedOrder = {0, 11, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenDisconnectedDAG_whenTopologicalSort_thenCorrectOrder() {
        // Given a directed acyclic graph (DAG) with disconnected components
        // ASCII representation:
        // 0 ---> 1        2 ---> 3
        final int N = 4;
        int[][] graph = TopologicalSortRecursive.createGraph(N);
        TopologicalSortRecursive.addDirectedEdge(graph, 0, 1);
        TopologicalSortRecursive.addDirectedEdge(graph, 2, 3);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortRecursive.getTopologicalOrder(graph);

        // Then the topological order should handle disconnected components
        int[] expectedOrder = {2, 3, 0, 1};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenGraphWithSingleNode_whenTopologicalSort_thenSingleNodeInOrder() {
        // Given a graph with a single node
        final int N = 1;
        int[][] graph = TopologicalSortRecursive.createGraph(N);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortRecursive.getTopologicalOrder(graph);

        // Then the topological order should contain the single node
        int[] expectedOrder = {0};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect for single node graph.");
    }

    @Test
    void givenDAGWithMultipleEdges_whenTopologicalSort_thenCorrectOrder() {
        // Given a DAG with multiple edges
        //    5 --> 2 --> 3
        //     \        /
        //      \--> 0 /
        //      /     /
        //     4 --> />1
        final int N = 6;
        int[][] graph = TopologicalSortRecursive.createGraph(N);
        TopologicalSortRecursive.addDirectedEdge(graph, 5, 2);
        TopologicalSortRecursive.addDirectedEdge(graph, 5, 0);
        TopologicalSortRecursive.addDirectedEdge(graph, 4, 0);
        TopologicalSortRecursive.addDirectedEdge(graph, 4, 1);
        TopologicalSortRecursive.addDirectedEdge(graph, 2, 3);
        TopologicalSortRecursive.addDirectedEdge(graph, 3, 1);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortRecursive.getTopologicalOrder(graph);

        // Then the topological order should be correct
        int[] expectedOrder = {5, 4, 2, 3, 1, 0}; // Example of a valid topological order
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenEmptyGraph_whenTopologicalSort_thenEmptyOrder() {
        // Given an empty graph
        final int N = 0;
        int[][] graph = TopologicalSortRecursive.createGraph(N);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortRecursive.getTopologicalOrder(graph);

        // Then the topological order should be empty
        int[] expectedOrder = {};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect for an empty graph.");
    }
}