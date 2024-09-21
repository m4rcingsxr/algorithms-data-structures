package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopologicalSortTest {

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
        List<List<Integer>> graph = TopologicalSort.createGraph(13);
        TopologicalSort.addDirectedEdge(graph, 0, 11);
        TopologicalSort.addDirectedEdge(graph, 0, 7);
        TopologicalSort.addDirectedEdge(graph, 0, 9);
        TopologicalSort.addDirectedEdge(graph, 9, 10);
        TopologicalSort.addDirectedEdge(graph, 10, 1);
        TopologicalSort.addDirectedEdge(graph, 1, 8);
        TopologicalSort.addDirectedEdge(graph, 8, 12);
        TopologicalSort.addDirectedEdge(graph, 7, 3);
        TopologicalSort.addDirectedEdge(graph, 7, 6);
        TopologicalSort.addDirectedEdge(graph, 6, 5);
        TopologicalSort.addDirectedEdge(graph, 3, 2);
        TopologicalSort.addDirectedEdge(graph, 2, 4);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSort.topologicalSort(graph);
        System.out.println(Arrays.toString(topologicalOrder));

        // Then the topological order should be correct
        int[] expectedOrder = {0, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4, 11};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenDisconnectedDAG_whenTopologicalSort_thenCorrectOrder() {
        // Given a directed acyclic graph (DAG) with disconnected components
        // ASCII representation:
        // 0 ---> 1        2 ---> 3
        List<List<Integer>> graph = TopologicalSort.createGraph(4);
        TopologicalSort.addDirectedEdge(graph, 0, 1);
        TopologicalSort.addDirectedEdge(graph, 2, 3);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSort.topologicalSort(graph);

        // Then the topological order should handle disconnected components
        int[] expectedOrder = {2, 3, 0, 1};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenGraphWithSingleNode_whenTopologicalSort_thenSingleNodeInOrder() {
        // Given a graph with a single node
        List<List<Integer>> graph = TopologicalSort.createGraph(1);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSort.topologicalSort(graph);

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
        List<List<Integer>> graph = TopologicalSort.createGraph(6);
        TopologicalSort.addDirectedEdge(graph, 5, 2);
        TopologicalSort.addDirectedEdge(graph, 5, 0);
        TopologicalSort.addDirectedEdge(graph, 4, 0);
        TopologicalSort.addDirectedEdge(graph, 4, 1);
        TopologicalSort.addDirectedEdge(graph, 2, 3);
        TopologicalSort.addDirectedEdge(graph, 3, 1);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSort.topologicalSort(graph);
        // Then the topological order should be correct
        int[] expectedOrder = {5, 4, 2, 3, 1, 0}; // Example of a valid topological order
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenEmptyGraph_whenTopologicalSort_thenEmptyOrder() {
        // Given an empty graph
        List<List<Integer>> graph = TopologicalSort.createGraph(0);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSort.topologicalSort(graph);

        // Then the topological order should be empty
        int[] expectedOrder = {};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect for an empty graph.");
    }
}
