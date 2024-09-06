package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SingleSourceShortestPathBSTTest {

    @Test
    void givenComplexGraph_whenFindingShortestPath_thenCorrectPath() {
        // Given
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11
        List<List<Integer>> graph = SingleSourceShortestPathBST.createGraph(13);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 0, 7);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 0, 9);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 0, 11);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 7, 6);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 7, 3);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 6, 5);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 3, 4);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 2, 3);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 2, 12);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 12, 8);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 8, 1);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 1, 10);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 10, 9);
        SingleSourceShortestPathBST.addUndirectedEdge(graph, 9, 8);

        // When finding the shortest path from 0 to 12
        List<Integer> path = SingleSourceShortestPathBST.shortestPath(graph, 0, 12);

        // Then the path should be [0, 9, 8, 12]
        assertEquals(List.of(0, 9, 8, 12), path, "Expected path is [0, 9, 8, 12]");
    }

    @Test
    void givenGraphWithNoPath_whenFindingShortestPath_thenEmptyPath() {
        // Given a graph with disconnected components
        // 0 --- 1   2 --- 3   4 (5 is isolated)
        List<List<Integer>> disconnectedGraph = SingleSourceShortestPathBST.createGraph(5);
        SingleSourceShortestPathBST.addUndirectedEdge(disconnectedGraph, 0, 1);
        SingleSourceShortestPathBST.addUndirectedEdge(disconnectedGraph, 2, 3);

        // When finding the shortest path from 0 to 3
        List<Integer> path = SingleSourceShortestPathBST.shortestPath(disconnectedGraph, 0, 3);

        // Then the path should be empty
        assertTrue(path.isEmpty(), "Expected an empty path");
    }

    @Test
    void givenGraphWithSelfLoops_whenFindingShortestPath_thenIgnoreSelfLoops() {
        // Given a graph with self-loops
        // 0 --- 1 --- 2
        //       | (self-loop on 1)
        //       |
        //       v
        //       1
        List<List<Integer>> graphWithSelfLoops = SingleSourceShortestPathBST.createGraph(3);
        SingleSourceShortestPathBST.addUndirectedEdge(graphWithSelfLoops, 0, 1);
        SingleSourceShortestPathBST.addDirectedEdge(graphWithSelfLoops, 1, 1); // Self-loop
        SingleSourceShortestPathBST.addUndirectedEdge(graphWithSelfLoops, 1, 2);

        // When finding the shortest path from 0 to 2
        List<Integer> path = SingleSourceShortestPathBST.shortestPath(graphWithSelfLoops, 0, 2);

        // Then the path should be [0, 1, 2]
        assertEquals(List.of(0, 1, 2), path, "Expected path is [0, 1, 2]");
    }

    @Test
    void givenGraphWithZeroWeightEdges_whenFindingShortestPath_thenCorrectPath() {
        // Given
        // 0 --- 1 --- 2
        // (all edges are treated the same in BFS)
        List<List<Integer>> graphWithZeroWeightEdges = SingleSourceShortestPathBST.createGraph(3);
        SingleSourceShortestPathBST.addUndirectedEdge(graphWithZeroWeightEdges, 0, 1);
        SingleSourceShortestPathBST.addUndirectedEdge(graphWithZeroWeightEdges, 1, 2);

        // When finding the shortest path from 0 to 2
        List<Integer> path = SingleSourceShortestPathBST.shortestPath(graphWithZeroWeightEdges, 0, 2);

        // Then the path should be [0, 1, 2]
        assertEquals(List.of(0, 1, 2), path, "Expected path is [0, 1, 2]");
    }

    @Test
    void givenFullyConnectedGraph_whenFindingShortestPath_thenCorrectPath() {
        // Given
        //    0 ---- 1
        //    |\    /|
        //    | \  / |
        //    |  \/  |
        //    |  /\  |
        //    | /  \ |
        //    |/    \|
        //    2 ---- 3
        List<List<Integer>> fullyConnectedGraph = SingleSourceShortestPathBST.createGraph(4);
        SingleSourceShortestPathBST.addUndirectedEdge(fullyConnectedGraph, 0, 1);
        SingleSourceShortestPathBST.addUndirectedEdge(fullyConnectedGraph, 0, 2);
        SingleSourceShortestPathBST.addUndirectedEdge(fullyConnectedGraph, 0, 3);
        SingleSourceShortestPathBST.addUndirectedEdge(fullyConnectedGraph, 1, 2);
        SingleSourceShortestPathBST.addUndirectedEdge(fullyConnectedGraph, 1, 3);
        SingleSourceShortestPathBST.addUndirectedEdge(fullyConnectedGraph, 2, 3);

        // When finding the shortest path from 0 to 3
        List<Integer> path = SingleSourceShortestPathBST.shortestPath(fullyConnectedGraph, 0, 3);

        // Then the path should be [0, 3] (direct path)
        assertEquals(List.of(0, 3), path, "Expected path is [0, 3]");
    }

    @Test
    void givenStartEqualsEnd_whenFindingShortestPath_thenSingleElementPath() {
        // Given
        // 0 --- 1 --- 2
        List<List<Integer>> anyGraph = SingleSourceShortestPathBST.createGraph(3);
        SingleSourceShortestPathBST.addUndirectedEdge(anyGraph, 0, 1);
        SingleSourceShortestPathBST.addUndirectedEdge(anyGraph, 1, 2);

        // When finding the shortest path from a node to itself
        List<Integer> path = SingleSourceShortestPathBST.shortestPath(anyGraph, 0, 0);

        // Then the path should be [0] because start and end are the same
        assertEquals(List.of(0), path, "Expected path is [0]");
    }
}
