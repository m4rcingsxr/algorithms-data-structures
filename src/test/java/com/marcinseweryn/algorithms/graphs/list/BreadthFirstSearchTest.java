package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {
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
        //                    11
        graph = BreadthFirstSearch.createGraph(13);
        BreadthFirstSearch.addUndirectedEdge(graph, 0, 7);
        BreadthFirstSearch.addUndirectedEdge(graph, 0, 9);
        BreadthFirstSearch.addUndirectedEdge(graph, 0, 11);
        BreadthFirstSearch.addUndirectedEdge(graph, 7, 6);
        BreadthFirstSearch.addUndirectedEdge(graph, 7, 3);
        BreadthFirstSearch.addUndirectedEdge(graph, 6, 5);
        BreadthFirstSearch.addUndirectedEdge(graph, 3, 4);
        BreadthFirstSearch.addUndirectedEdge(graph, 2, 3);
        BreadthFirstSearch.addUndirectedEdge(graph, 2, 12);
        BreadthFirstSearch.addUndirectedEdge(graph, 12, 8);
        BreadthFirstSearch.addUndirectedEdge(graph, 8, 1);
        BreadthFirstSearch.addUndirectedEdge(graph, 1, 10);
        BreadthFirstSearch.addUndirectedEdge(graph, 10, 9);
        BreadthFirstSearch.addUndirectedEdge(graph, 9, 8);
    }

    @Test
    void whenPerformingBFSFromVertex0_thenCorrectTraversalOrder() {
        List<Integer> traversalOrder = BreadthFirstSearch.bfs(graph, 0);

        List<Integer> expectedOrder = List.of(0, 7, 9, 11, 6, 3, 10, 8, 5, 4, 2, 1, 12);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void whenPerformingBFSFromVertex1_thenCorrectTraversalOrder() {
        int startVertex = 1;

        List<Integer> traversalOrder = BreadthFirstSearch.bfs(graph, startVertex);

        List<Integer> expectedOrder = List.of(1, 8, 10, 12, 9, 2, 0, 3, 7, 11, 4, 6, 5);
        assertEquals(expectedOrder, traversalOrder);
    }

    @Test
    void whenPerformingBFSFromVertex5_thenCorrectTraversalOrder() {
        int startVertex = 5;

        List<Integer> traversalOrder = BreadthFirstSearch.bfs(graph, startVertex);

        List<Integer> expectedOrder = List.of(5, 6, 7, 0, 3, 9, 11, 4, 2, 10, 8, 12, 1);
        assertEquals(expectedOrder, traversalOrder);
    }
}