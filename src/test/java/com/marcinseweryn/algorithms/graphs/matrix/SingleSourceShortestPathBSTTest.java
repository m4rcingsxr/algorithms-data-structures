package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleSourceShortestPathBSTTest {

    @Test
    void givenComplexGraph_whenFindingShortestPath_thenCorrectPath() {
        // Given a complex graph
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

        // When finding the shortest path from 0 to 4
        List<Integer> path = SingleSourceShortestPathBST.sssp(graph, 0, 4);

        // Then the path should be [0, 7, 3, 4]
        assertEquals(List.of(0, 7, 3, 4), path, "Expected path is [0, 7, 3, 4]");
    }

    @Test
    void givenDisconnectedGraph_whenFindingShortestPath_thenEmptyPath() {
        // Given a disconnected graph
        int[][] disconnectedGraph = {
                {0, 1, 0},
                {1, 0, 0},
                {0, 0, 0}
        };

        // When finding the shortest path from 0 to 2
        List<Integer> path = SingleSourceShortestPathBST.sssp(disconnectedGraph, 0, 2);

        // Then the path should be empty
        assertTrue(path.isEmpty(), "Expected an empty path");
    }

    @Test
    void givenGraphWithSelfLoops_whenFindingShortestPath_thenIgnoreSelfLoops() {
        // Given a graph with self-loops
        int[][] graphWithSelfLoops = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };

        // When finding the shortest path from 0 to 2
        List<Integer> path = SingleSourceShortestPathBST.sssp(graphWithSelfLoops, 0, 2);

        // Then the path should be [0, 1, 2]
        assertEquals(List.of(0, 1, 2), path, "Expected path is [0, 1, 2]");
    }

    @Test
    void givenGraphWithZeroWeightEdges_whenFindingShortestPath_thenCorrectPath() {
        // Given a graph where edges have zero weight
        int[][] graphWithZeroWeightEdges = {
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        // When finding the shortest path from 0 to 2
        List<Integer> path = SingleSourceShortestPathBST.sssp(graphWithZeroWeightEdges, 0, 2);

        // Then the path should be [0, 1, 2]
        assertEquals(List.of(0, 1, 2), path, "Expected path is [0, 1, 2]");
    }

    @Test
    void givenFullyConnectedGraph_whenFindingShortestPath_thenCorrectPath() {
        // Given a fully connected graph
        int[][] fullyConnectedGraph = {
                {0, 1, 1},
                {1, 0, 1},
                {1, 1, 0}
        };

        // When finding the shortest path from 0 to 2
        List<Integer> path = SingleSourceShortestPathBST.sssp(fullyConnectedGraph, 0, 2);

        // Then the path should be [0, 2] (direct path)
        assertEquals(List.of(0, 2), path, "Expected path is [0, 2]");
    }

    @Test
    void givenStartEqualsEnd_whenFindingShortestPath_thenSingleElementPath() {
        // Given a simple graph
        int[][] graph = {
                {0, 1},
                {1, 0}
        };

        // When finding the shortest path from a node to itself
        List<Integer> path = SingleSourceShortestPathBST.sssp(graph, 0, 0);

        // Then the path should be [0] because start and end are the same
        assertEquals(List.of(0), path, "Expected path is [0]");
    }
}
