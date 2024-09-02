package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepthFirstSearchStackTest {

    private static List<List<Integer>> graph;

    @BeforeAll
    static void setUp() {
        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
        graph = DepthFirstSearchStack.createEmptyGraph(13);
        DepthFirstSearchStack.addUndirectedEdge(graph, 0, 7);
        DepthFirstSearchStack.addUndirectedEdge(graph, 0, 9);
        DepthFirstSearchStack.addUndirectedEdge(graph, 0, 11);
        DepthFirstSearchStack.addUndirectedEdge(graph, 7, 6);
        DepthFirstSearchStack.addUndirectedEdge(graph, 7, 3);
        DepthFirstSearchStack.addUndirectedEdge(graph, 6, 5);
        DepthFirstSearchStack.addUndirectedEdge(graph, 3, 4);
        DepthFirstSearchStack.addUndirectedEdge(graph, 2, 3);
        DepthFirstSearchStack.addUndirectedEdge(graph, 2, 12);
        DepthFirstSearchStack.addUndirectedEdge(graph, 12, 8);
        DepthFirstSearchStack.addUndirectedEdge(graph, 8, 1);
        DepthFirstSearchStack.addUndirectedEdge(graph, 1, 10);
        DepthFirstSearchStack.addUndirectedEdge(graph, 10, 9);
        DepthFirstSearchStack.addUndirectedEdge(graph, 9, 8);
    }

    @Test
    void givenSimpleGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
        List<List<Integer>> simpleGraph = DepthFirstSearchStack.createEmptyGraph(5);
        DepthFirstSearchStack.addUndirectedEdge(simpleGraph, 0, 1);
        DepthFirstSearchStack.addUndirectedEdge(simpleGraph, 0, 2);
        DepthFirstSearchStack.addUndirectedEdge(simpleGraph, 0, 3);
        DepthFirstSearchStack.addUndirectedEdge(simpleGraph, 1, 4);
        DepthFirstSearchStack.addUndirectedEdge(simpleGraph, 2, 3);
        DepthFirstSearchStack.addUndirectedEdge(simpleGraph, 3, 4);

        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(simpleGraph, 0, 5);

        List<Integer> expectedOrder = List.of(0, 3, 4, 1, 2);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(graph, 0, 13);

        List<Integer> expectedOrder = List.of(0, 11, 9, 8, 1, 10, 12, 2, 3, 4, 7, 6, 5);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexGraph_whenDFSStartingFromVertex7_thenCorrectTraversalOrder() {
        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(graph, 7, 13);

        List<Integer> expectedOrder = List.of(7, 3, 2, 12, 8, 9, 10, 1, 0, 11, 4, 6, 5);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexGraph_whenDFSStartingFromVertex2_thenCorrectTraversalOrder() {
        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(graph, 2, 13);

        List<Integer> expectedOrder = List.of(2, 12, 8, 9, 10, 1, 0, 11, 7, 3, 4, 6, 5);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenDisconnectedGraph_whenDFSStartingFromDifferentVertices_thenCorrectTraversalOrders() {
        //     0 -- 1
        //     |
        //     2    3 -- 4   5
        List<List<Integer>> disconnectedGraph = DepthFirstSearchStack.createEmptyGraph(6);
        DepthFirstSearchStack.addUndirectedEdge(disconnectedGraph, 0, 1);
        DepthFirstSearchStack.addUndirectedEdge(disconnectedGraph, 0, 2);
        DepthFirstSearchStack.addUndirectedEdge(disconnectedGraph, 3, 4);
        // Node 5 is isolated

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrderFrom0 = DepthFirstSearchStack.depthFirstSearch(disconnectedGraph, 0, 6);

        // Then: The traversal order starting from vertex 0 should only visit the connected component [0, 2, 1]
        List<Integer> expectedOrderFrom0 = List.of(0, 2, 1);
        assertEquals(expectedOrderFrom0, traversalOrderFrom0);

        // When: DFS is performed starting from vertex 3
        List<Integer> traversalOrderFrom3 = DepthFirstSearchStack.depthFirstSearch(disconnectedGraph, 3, 6);

        // Then: The traversal order starting from vertex 3 should only visit its connected component [3, 4]
        List<Integer> expectedOrderFrom3 = List.of(3, 4);
        assertEquals(expectedOrderFrom3, traversalOrderFrom3);

        // When: DFS is performed starting from isolated vertex 5
        List<Integer> traversalOrderFrom5 = DepthFirstSearchStack.depthFirstSearch(disconnectedGraph, 5, 6);

        // Then: The traversal order starting from vertex 5 should only visit vertex 5
        List<Integer> expectedOrderFrom5 = List.of(5);
        assertEquals(expectedOrderFrom5, traversalOrderFrom5);
    }

}
