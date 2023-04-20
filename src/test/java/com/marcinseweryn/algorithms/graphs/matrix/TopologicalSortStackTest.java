package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class TopologicalSortStackTest {

    @Test
    void testGetTopologicalOrderSingleComponent() {
        int[][] graph2 = TopologicalSortStack.createGraph(13);
        TopologicalSortStack.addDirectedEdge(graph2, 0, 11);
        TopologicalSortStack.addDirectedEdge(graph2, 0, 7);
        TopologicalSortStack.addDirectedEdge(graph2, 0, 9);
        TopologicalSortStack.addDirectedEdge(graph2, 9, 10);
        TopologicalSortStack.addDirectedEdge(graph2, 10, 1);
        TopologicalSortStack.addDirectedEdge(graph2, 1, 8);
        TopologicalSortStack.addDirectedEdge(graph2, 8, 12);
        TopologicalSortStack.addDirectedEdge(graph2, 7, 3);
        TopologicalSortStack.addDirectedEdge(graph2, 7, 6);
        TopologicalSortStack.addDirectedEdge(graph2, 6, 5);
        TopologicalSortStack.addDirectedEdge(graph2, 3, 2);
        TopologicalSortStack.addDirectedEdge(graph2, 2, 4);

        int[] expected2 = {0, 11, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4};
        int[] actual2 = TopologicalSortStack.getTopologicalOrder(graph2);
        assertArrayEquals(expected2, actual2);
    }

    @Test
    void testGetTopologicalOrderSeparateComponents() {
        int[][] graph1 = TopologicalSortStack.createGraph(17);
        TopologicalSortStack.addDirectedEdge(graph1, 0, 11);
        TopologicalSortStack.addDirectedEdge(graph1, 0, 7);
        TopologicalSortStack.addDirectedEdge(graph1, 0, 9);
        TopologicalSortStack.addDirectedEdge(graph1, 9, 10);
        TopologicalSortStack.addDirectedEdge(graph1, 10, 1);
        TopologicalSortStack.addDirectedEdge(graph1, 1, 8);
        TopologicalSortStack.addDirectedEdge(graph1, 7, 3);
        TopologicalSortStack.addDirectedEdge(graph1, 7, 6);
        TopologicalSortStack.addDirectedEdge(graph1, 6, 5);
        TopologicalSortStack.addDirectedEdge(graph1, 3, 2);
        TopologicalSortStack.addDirectedEdge(graph1, 2, 4);
        TopologicalSortStack.addDirectedEdge(graph1, 12, 15);
        TopologicalSortStack.addDirectedEdge(graph1, 12, 14);
        TopologicalSortStack.addDirectedEdge(graph1, 14, 13);
        TopologicalSortStack.addDirectedEdge(graph1, 14, 16);

        int[] expected1 = {0, 11, 9, 10, 1, 8, 7, 6, 5, 3, 2, 4, 12, 15, 14,
                           16, 13};
        int[] actual1 = TopologicalSortStack.getTopologicalOrder(graph1);
        assertArrayEquals(expected1, actual1);
    }
}