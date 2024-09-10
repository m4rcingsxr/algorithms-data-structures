package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LazyDijkstraTest {

    private LazyDijkstra lazyDijkstra;

    @Test
    void givenSimpleGraph_whenFindingShortestPath_thenCorrectDistanceAndPath() {
        // Given
        //    (4)
        // 0 ----> 1
        lazyDijkstra = new LazyDijkstra(2);
        lazyDijkstra.addDirectedEdge(0, 1, 4);

        // When finding the shortest path from 0 to 1
        double distance = lazyDijkstra.dijkstra(0, 1);
        List<Integer> path = lazyDijkstra.shortestPath(0, 1);

        // Then the distance should be 4 and the path should be [0, 1]
        assertEquals(4.0, distance, "Expected shortest distance is 4.0");
        assertEquals(List.of(0, 1), path, "Expected path is [0, 1]");
    }

    @Test
    void givenDisconnectedGraph_whenFindingShortestPath_thenInfinity() {
        // Given
        // 0     1
        lazyDijkstra = new LazyDijkstra(2);

        // When finding the shortest path from 0 to 1
        double distance = lazyDijkstra.dijkstra(0, 1);
        List<Integer> path = lazyDijkstra.shortestPath(0, 1);

        // Then the distance should be infinity and the path should be empty
        assertEquals(Double.POSITIVE_INFINITY, distance, "Expected shortest distance is infinity");
        assertTrue(path.isEmpty(), "Expected path to be empty");
    }

    @Test
    void givenGraphWithMultiplePaths_whenFindingShortestPath_thenCorrectDistanceAndPath() {
        //     (2)       (3)
        //  0 ----> 1 ----> 3
        //   \               ^
        //  (5)\           /
        //       > 2 -----
        //           (1)
        lazyDijkstra = new LazyDijkstra(4);
        lazyDijkstra.addDirectedEdge(0, 1, 2);
        lazyDijkstra.addDirectedEdge(1, 3, 3);
        lazyDijkstra.addDirectedEdge(0, 2, 5);
        lazyDijkstra.addDirectedEdge(2, 3, 1);

        // When finding the shortest path from 0 to 3
        double distance = lazyDijkstra.dijkstra(0, 3);
        List<Integer> path = lazyDijkstra.shortestPath(0, 3);

        // Then the distance should be 5 and the path should be [0, 1, 3]
        assertEquals(5.0, distance, "Expected shortest distance is 5.0");
        assertEquals(List.of(0, 1, 3), path, "Expected path is [0, 1, 3]");
    }

    @Test
    void givenGraphWithCycles_whenFindingShortestPath_thenCorrectDistanceAndPath() {
        // Given a graph with a cycle
        //     (1)
        //  0 ----> 1
        //  ^       /
        //   \     /
        //   (4)  (1)
        //      2
        lazyDijkstra = new LazyDijkstra(3);
        lazyDijkstra.addDirectedEdge(0, 1, 1);
        lazyDijkstra.addDirectedEdge(1, 2, 1);
        lazyDijkstra.addDirectedEdge(2, 0, 4);

        // When finding the shortest path from 0 to 2
        double distance = lazyDijkstra.dijkstra(0, 2);
        List<Integer> path = lazyDijkstra.shortestPath(0, 2);

        // Then the distance should be 2 and the path should be [0, 1, 2]
        assertEquals(2.0, distance, "Expected shortest distance is 2.0");
        assertEquals(List.of(0, 1, 2), path, "Expected path is [0, 1, 2]");
    }

    @Test
    void givenComplexGraph_whenFindingShortestPath_thenCorrectDistanceAndPath() {
        // Given
        //              (6)
        //        > 1 ------> 4
        //   (2) /   | \(1) /  \(9)
        //      /    |  \(4)/    \
        //    0      |   >3        > 6
        //     \ (5) |          /
        //   (6) \   >        /(7)
        //        > 2 -----> 5
        //             (8)
        lazyDijkstra = new LazyDijkstra(7);
        lazyDijkstra.addDirectedEdge(0, 1, 2);
        lazyDijkstra.addDirectedEdge(0, 2, 5);
        lazyDijkstra.addDirectedEdge(1, 4, 6);
        lazyDijkstra.addDirectedEdge(1, 3, 1);
        lazyDijkstra.addDirectedEdge(1, 2, 6);
        lazyDijkstra.addDirectedEdge(2, 5, 8);
        lazyDijkstra.addDirectedEdge(3, 4, 4);
        lazyDijkstra.addDirectedEdge(4, 6, 9);
        lazyDijkstra.addDirectedEdge(5, 6, 7);

        // When finding the shortest path from 0 to 6
        double distance = lazyDijkstra.dijkstra(0, 6);
        List<Integer> path = lazyDijkstra.shortestPath(0, 6);

        // Then the distance should be 16 and the path should be [0, 1, 3, 4, 6]
        assertEquals(16.0, distance, "Expected shortest distance is 16.0");
        assertEquals(List.of(0, 1, 3, 4, 6), path, "Expected path is [0, 1, 3, 4, 6]");
    }

    @Test
    void givenGraphWithNegativeWeights_whenFindingShortestPath_thenIncorrectBehavior() {
        // Given
        //    (2)
        // 0 ----> 1
        // |       |
        //(1)     (-3)
        // |       |
        // v       v
        // 2 ----> 3
        //     (1)
        lazyDijkstra = new LazyDijkstra(4);
        lazyDijkstra.addDirectedEdge(0, 1, 2);
        lazyDijkstra.addDirectedEdge(1, 3, -3);  // Negative weight
        lazyDijkstra.addDirectedEdge(0, 2, 1);
        lazyDijkstra.addDirectedEdge(2, 3, 1);

        // When finding the shortest path from 0 to 3
        double distance = lazyDijkstra.dijkstra(0, 3);
        List<Integer> path = lazyDijkstra.shortestPath(0, 3);

        // Then the algorithm might not provide correct results due to negative weights
        assertNotEquals(0.0, distance,
                        "Dijkstra's algorithm should not handle negative weights correctly"
        );
    }

    @Test
    void givenFullyConnectedGraph_whenFindingShortestPath_thenCorrectDistanceAndPath() {
        // Given
        //     0 --(1)-- 1
        //     |  \     /  |
        //   (1)  (1)(1) (1)
        //     |  /     \  |
        //     2 --(1)-- 3
        lazyDijkstra = new LazyDijkstra(4);
        lazyDijkstra.addDirectedEdge(0, 1, 1);
        lazyDijkstra.addDirectedEdge(0, 2, 1);
        lazyDijkstra.addDirectedEdge(0, 3, 1);
        lazyDijkstra.addDirectedEdge(1, 2, 1);
        lazyDijkstra.addDirectedEdge(1, 3, 1);
        lazyDijkstra.addDirectedEdge(2, 3, 1);

        // When finding the shortest path from 0 to 3
        double distance = lazyDijkstra.dijkstra(0, 3);
        List<Integer> path = lazyDijkstra.shortestPath(0, 3);

        // Then the distance should be 1 and path should be [0, 3]
        assertEquals(1.0, distance, "Expected shortest distance is 1.0");
        assertEquals(2, path.size(), "Expected path length is 2");
    }

    @Test
    void givenGraphWithSelfLoops_whenFindingShortestPath_thenIgnoreSelfLoops() {
        // Given
        //     (3)  (2) (self loop)
        // 0 ---->1 <--
        // |  (2)(self loop)
        // |    ^
        // |____|
        lazyDijkstra = new LazyDijkstra(2);
        lazyDijkstra.addDirectedEdge(0, 0, 2); // Self-loop
        lazyDijkstra.addDirectedEdge(0, 1, 3);
        lazyDijkstra.addDirectedEdge(1, 1, 2); // Self-loop

        // When finding the shortest path from 0 to 1
        double distance = lazyDijkstra.dijkstra(0, 1);
        List<Integer> path = lazyDijkstra.shortestPath(0, 1);

        // Then the distance should be 3 and the path should be [0, 1]
        assertEquals(3.0, distance, "Expected shortest distance is 3.0");
        assertEquals(List.of(0, 1), path, "Expected path is [0, 1]");
    }

    @Test
    void givenGraphWithZeroWeightEdges_whenFindingShortestPath_thenCorrectDistanceAndPath() {
        // Given
        //    (0)
        // 0 ----> 1
        //  \      |
        //  (1)   (0)
        //    \  /
        //      2
        lazyDijkstra = new LazyDijkstra(3);
        lazyDijkstra.addDirectedEdge(0, 1, 0);
        lazyDijkstra.addDirectedEdge(1, 2, 0);
        lazyDijkstra.addDirectedEdge(0, 2, 1);

        // When finding the shortest path from 0 to 2
        double distance = lazyDijkstra.dijkstra(0, 2);
        List<Integer> path = lazyDijkstra.shortestPath(0, 2);

        // Then the distance should be 0 and the path should be [0, 1, 2]
        assertEquals(0.0, distance, "Expected shortest distance is 0.0");
        assertEquals(List.of(0, 1, 2), path, "Expected path is [0, 1, 2]");
    }

}
