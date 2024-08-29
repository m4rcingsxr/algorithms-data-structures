package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    @Test
    void givenSimpleGraph_whenBFSStartingFromVertex0_thenCorrectTraversalOrder() {
        // Given: A simple graph represented by an adjacency matrix
        // Graph visualization:
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

        // When: BFS is performed starting from vertex 0
        List<Integer> traversalOrder = BreadthFirstSearch.bfs(matrix, 0);

        // Then: The traversal order should be [0, 1, 2, 3, 4]
        List<Integer> expectedOrder = List.of(0, 1, 2, 3, 4);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenComplexGraph_whenBFSStartingFromVertex0_thenCorrectTraversalOrder() {
        // Given
        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                    11
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

        // When: BFS is performed starting from vertex 0
        List<Integer> traversalOrder = BreadthFirstSearch.bfs(matrix, 0);

        // Then: The traversal order should be [0, 7, 9, 11, 3, 6, 8, 10, 4, 5, 1, 12, 2]
        List<Integer> expectedOrder = List.of(0, 7, 9, 11, 3, 6, 8, 10, 4, 5, 1, 12, 2);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenGraphWithSingleNode_whenBFSStartingFromThatNode_thenSingleNodeInTraversal() {
        // Given
        int[][] matrix = {
                {0}
        };

        // When: BFS is performed starting from vertex 0
        List<Integer> traversalOrder = BreadthFirstSearch.bfs(matrix, 0);

        // Then: The traversal order should be [0]
        List<Integer> expectedOrder = List.of(0);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenGraphWithDisconnectedComponents_whenBFSStartingFromVertex0_thenOnlyConnectedComponentVisited() {
        // Given
        // 0 -- 1
        // |
        // 2    3 -- 4
        int[][] matrix = {
                {0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 0}
        };

        // When: BFS is performed starting from vertex 0
        List<Integer> traversalOrder = BreadthFirstSearch.bfs(matrix, 0);

        // Then: The traversal order should only visit the connected component [0, 1, 2]
        List<Integer> expectedOrder = List.of(0, 1, 2);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void givenGraphWithCycle_whenBFSStartingFromVertex0_thenCorrectTraversalOrderWithoutRevisitingNodes() {
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

        // When: BFS is performed starting from vertex 0
        List<Integer> traversalOrder = BreadthFirstSearch.bfs(matrix, 0);

        // Then: The traversal order should correctly traverse without revisiting nodes [0, 1, 2, 3]
        List<Integer> expectedOrder = List.of(0, 1, 2, 3);
        assertEquals(expectedOrder, traversalOrder);
    }
}

