package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DepthFirstSearchStackTest {

    @Test
    void givenSimpleGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
        // Given
        // 0 -- 1
        // | \    \
        // |  \    4
        // |   \  /
        // 2 -- 3
        int[][] matrix = {
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(matrix, 0);

        // Then: The traversal order should be [0, 3, 4, 1, 2]
        List<Integer> expectedOrder = List.of(0, 3, 4, 1, 2);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexGraph_whenDFSStartingFromVertex0_thenCorrectTraversalOrder() {
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
        int[][] matrix = {
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0},
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
        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(matrix, 0);

        // Then: The traversal order should be [0, 11, 9, 10, 1, 8, 12, 2, 3, 7, 6, 5, 4]
        List<Integer> expectedOrder = List.of(0, 11, 9, 10, 1, 8, 12, 2, 3, 7, 6, 5, 4);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenGraphWithCycle_whenDFSStartingFromVertex0_thenCorrectTraversalOrderWithoutRevisitingNodes() {
        // Given
        // 0 -- 1
        // |    |
        // 2 -- 3
        int[][] matrix = {
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        };

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrder = DepthFirstSearchStack.depthFirstSearch(matrix, 0);

        // Then: The traversal order should correctly traverse without revisiting nodes [0, 2, 3, 1]
        List<Integer> expectedOrder = List.of(0, 2, 3, 1);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenDisconnectedGraph_whenDFSStartingFromDifferentVertices_thenCorrectTraversalOrders() {
        // Given
        // 0 -- 1
        // |
        // 2    3 -- 4   5
        int[][] matrix = {
                {0, 1, 1, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0}
        };

        // When: DFS is performed starting from vertex 0
        List<Integer> traversalOrderFrom0 = DepthFirstSearchStack.depthFirstSearch(matrix, 0);

        // Then: The traversal order starting from vertex 0 should only visit the connected component [0, 2, 1]
        List<Integer> expectedOrderFrom0 = List.of(0, 2, 1);
        assertEquals(expectedOrderFrom0, traversalOrderFrom0);

        // When: DFS is performed starting from vertex 3
        List<Integer> traversalOrderFrom3 = DepthFirstSearchStack.depthFirstSearch(matrix, 3);

        // Then: The traversal order starting from vertex 3 should only visit its connected component [3, 4]
        List<Integer> expectedOrderFrom3 = List.of(3, 4);
        assertEquals(expectedOrderFrom3, traversalOrderFrom3);

        // When: DFS is performed starting from isolated vertex 5
        List<Integer> traversalOrderFrom5 = DepthFirstSearchStack.depthFirstSearch(matrix, 5);

        // Then: The traversal order starting from vertex 5 should only visit vertex 5
        List<Integer> expectedOrderFrom5 = List.of(5);
        assertEquals(expectedOrderFrom5, traversalOrderFrom5);
    }

}
