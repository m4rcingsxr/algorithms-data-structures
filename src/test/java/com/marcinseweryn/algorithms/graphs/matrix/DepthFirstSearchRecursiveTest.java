package com.marcinseweryn.algorithms.graphs.matrix;

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
        int[][] graph = {
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0}
        };

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
        int[][] graph = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}
        };

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchRecursive.dfs(graph, 0);

        // Then: The traversal order should be [0, 7, 3, 2, 12, 8, 1, 10, 9, 4, 6, 5, 11]
        List<Integer> expectedOrder = List.of(0, 7, 3, 2, 12, 8, 1, 10, 9, 4, 6, 5, 11);
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
        int[][] graph = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0},
                {1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchRecursive.dfs(graph, 0);

        List<Integer> expectedOrder = List.of(0, 7, 3, 2, 4, 6, 5, 9, 10, 1, 8, 12, 11);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenGraphWithDisconnectedComponents_whenDFSStartingFromDifferentVertices_thenCorrectTraversalOrders() {
        // Given
        //     0 -- 1
        //     |
        //     2    3 -- 4   5
        int[][] graph = {
                {0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

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
