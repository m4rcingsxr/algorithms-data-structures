package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepthFirstSearchRecursiveTest {

    @Test
    void givenSimpleGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
        // Given
        //    0 -- 1
        //   /  \    \
        //  2 -- 3    4
        List<List<Integer>> graph = DepthFirstSearchRecursive.createGraph(5);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 1);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 2);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 3);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 1, 4);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 2, 3);

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchRecursive.dfs(graph, 0);

        // Then: The traversal order should be [0, 1, 4, 2, 3]
        List<Integer> expectedOrder = List.of(0, 1, 4, 2, 3);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexUndirectedGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
        // Given
        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
        List<List<Integer>> graph = DepthFirstSearchRecursive.createGraph(13);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 7);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 9);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 11);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 7, 6);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 7, 3);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 6, 5);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 3, 4);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 2, 3);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 2, 12);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 12, 8);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 8, 1);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 1, 10);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 10, 9);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 9, 8);

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchRecursive.dfs(graph, 0);

        // Then: The traversal order should be [0, 7, 6, 5, 3, 4, 2, 12, 8, 1, 10, 9, 11]
        List<Integer> expectedOrder = List.of(0, 7, 6, 5, 3, 4, 2, 12, 8, 1, 10, 9, 11);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexDirectedGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
        // Given
        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        List<List<Integer>> graph = DepthFirstSearchRecursive.createGraph(13);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 0, 11);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 0, 7);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 0, 9);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 9, 10);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 10, 1);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 1, 8);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 8, 12);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 7, 3);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 7, 6);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 6, 5);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 3, 2);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 2, 4);

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchRecursive.dfs(graph, 0);

        // Then: The traversal order should be [0, 11, 7, 3, 2, 4, 6, 5, 9, 10, 1, 8, 12]
        List<Integer> expectedOrder = List.of(0, 11, 7, 3, 2, 4, 6, 5, 9, 10, 1, 8, 12);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenGraphWithDisconnectedComponents_whenDFSStartingFromDifferentVertices_thenCorrectTraversalOrders() {
        // Given
        //     0 -- 1
        //     |
        //     2    3 -- 4   5
        List<List<Integer>> graph = DepthFirstSearchRecursive.createGraph(6);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 1);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 2);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 3, 4);
        // Node 5 is isolated

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrderFrom0 = DepthFirstSearchRecursive.dfs(graph, 0);

        // Then: The traversal order starting from vertex 0 should only visit the connected component [0, 1, 2]
        List<Integer> expectedOrderFrom0 = List.of(0, 1, 2);
        assertEquals(expectedOrderFrom0, traversalOrderFrom0);

        // When: DFS is performed starting from vertex 3
        List<Integer> traversalOrderFrom3 = DepthFirstSearchRecursive.dfs(graph, 3);

        // Then: The traversal order starting from vertex 3 should only visit its connected component [3, 4]
        List<Integer> expectedOrderFrom3 = List.of(3, 4);
        assertEquals(expectedOrderFrom3, traversalOrderFrom3);

        // When: DFS is performed starting from isolated vertex 5
        List<Integer> traversalOrderFrom5 = DepthFirstSearchRecursive.dfs(graph, 5);

        // Then: The traversal order starting from vertex 5 should only visit vertex 5
        List<Integer> expectedOrderFrom5 = List.of(5);
        assertEquals(expectedOrderFrom5, traversalOrderFrom5);
    }
}
