package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopologicalSortStackTest {

    @Test
    void givenDAG_whenTopologicalSort_thenCorrectOrder() {
        // Given a directed acyclic graph
        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        List<List<Integer>> graph = TopologicalSortStack.createGraph(13);
        TopologicalSortStack.addDirectedEdge(graph, 0, 11);
        TopologicalSortStack.addDirectedEdge(graph, 0, 7);
        TopologicalSortStack.addDirectedEdge(graph, 0, 9);
        TopologicalSortStack.addDirectedEdge(graph, 9, 10);
        TopologicalSortStack.addDirectedEdge(graph, 10, 1);
        TopologicalSortStack.addDirectedEdge(graph, 1, 8);
        TopologicalSortStack.addDirectedEdge(graph, 8, 12);
        TopologicalSortStack.addDirectedEdge(graph, 7, 3);
        TopologicalSortStack.addDirectedEdge(graph, 7, 6);
        TopologicalSortStack.addDirectedEdge(graph, 6, 5);
        TopologicalSortStack.addDirectedEdge(graph, 3, 2);
        TopologicalSortStack.addDirectedEdge(graph, 2, 4);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortStack.topologicalSort(graph);
        System.out.println(Arrays.toString(topologicalOrder));

        // Then the topological order should be correct
        int[] expectedOrder = {0, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4, 11};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenDisconnectedDAG_whenTopologicalSort_thenCorrectOrder() {
        // Given a directed acyclic graph (DAG) with disconnected components
        // 0 ---> 1        2 ---> 3
        List<List<Integer>> graph = TopologicalSortStack.createGraph(4);
        TopologicalSortStack.addDirectedEdge(graph, 0, 1);
        TopologicalSortStack.addDirectedEdge(graph, 2, 3);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortStack.topologicalSort(graph);

        // Then the topological order should handle disconnected components
        int[] expectedOrder = {2, 3, 0, 1};  // Order might vary, as 2-3 and 0-1 are independent
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenGraphWithSingleNode_whenTopologicalSort_thenSingleNodeInOrder() {
        // Given a graph with a single node
        List<List<Integer>> graph = TopologicalSortStack.createGraph(1);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortStack.topologicalSort(graph);

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
        List<List<Integer>> graph = TopologicalSortStack.createGraph(6);
        TopologicalSortStack.addDirectedEdge(graph, 5, 2);
        TopologicalSortStack.addDirectedEdge(graph, 5, 0);
        TopologicalSortStack.addDirectedEdge(graph, 4, 0);
        TopologicalSortStack.addDirectedEdge(graph, 4, 1);
        TopologicalSortStack.addDirectedEdge(graph, 2, 3);
        TopologicalSortStack.addDirectedEdge(graph, 3, 1);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortStack.topologicalSort(graph);
        // Then the topological order should be correct
        int[] expectedOrder = {5, 4, 2, 3, 1, 0}; // Example of a valid topological order
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect.");
    }

    @Test
    void givenEmptyGraph_whenTopologicalSort_thenEmptyOrder() {
        // Given an empty graph
        List<List<Integer>> graph = TopologicalSortStack.createGraph(0);

        // When performing topological sort
        int[] topologicalOrder = TopologicalSortStack.topologicalSort(graph);

        // Then the topological order should be empty
        int[] expectedOrder = {};
        assertArrayEquals(expectedOrder, topologicalOrder, "Expected topological order is incorrect for an empty graph.");
    }
}
