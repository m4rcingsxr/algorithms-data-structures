package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.marcinseweryn.algorithms.graphs.list.BreadthFirstSearch.addUndirectedEdge;
import static com.marcinseweryn.algorithms.graphs.list.BreadthFirstSearch.createGraph;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    @DisplayName("Should traverse graph using BFS")
    @ParameterizedTest(name = "Graph {index}")
    @MethodSource("graphsProvider")
    void bfs(List<List<Integer>> graph, int start, String expected) {
        String actual = BreadthFirstSearch.bfs(graph, start);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> graphsProvider() {
        List<List<Integer>> graph1 = createGraph(7);
        addUndirectedEdge(graph1, 0, 1);
        addUndirectedEdge(graph1, 0, 2);
        addUndirectedEdge(graph1, 1, 3);
        addUndirectedEdge(graph1, 1, 4);
        addUndirectedEdge(graph1, 2, 5);
        addUndirectedEdge(graph1, 2, 6);
        String expected1 = "Breadth First Search from vertex 0:[0 1 2 3 4 5 6]";
        Arguments arg1 = Arguments.of(graph1, 0, expected1);

        List<List<Integer>> graph2 = createGraph(6);
        addUndirectedEdge(graph2, 0, 1);
        addUndirectedEdge(graph2, 0, 2);
        addUndirectedEdge(graph2, 1, 3);
        addUndirectedEdge(graph2, 1, 4);
        addUndirectedEdge(graph2, 2, 5);
        String expected2 = "Breadth First Search from vertex 0:[0 1 2 3 4 5]";
        Arguments arg2 = Arguments.of(graph2, 0, expected2);

        List<List<Integer>> graph3 = createGraph(10);
        addUndirectedEdge(graph3, 0, 1);
        addUndirectedEdge(graph3, 0, 2);
        addUndirectedEdge(graph3, 1, 3);
        addUndirectedEdge(graph3, 1, 4);
        addUndirectedEdge(graph3, 2, 5);
        addUndirectedEdge(graph3, 2, 6);
        addUndirectedEdge(graph3, 3, 7);
        addUndirectedEdge(graph3, 3, 8);
        addUndirectedEdge(graph3, 4, 9);
        String expected3 = "Breadth First Search from vertex 0:[0 1 2 3 4 5 6 7 8 9]";
        Arguments arg3 = Arguments.of(graph3, 0, expected3);

        List<List<Integer>> graph4 = createGraph(4);
        addUndirectedEdge(graph4, 0, 1);
        addUndirectedEdge(graph4, 0, 2);
        addUndirectedEdge(graph4, 1, 3);
        String expected4 = "Breadth First Search from vertex 0:[0 1 2 3]";
        Arguments arg4 = Arguments.of(graph4, 0, expected4);

        return Stream.of(
                arg1, arg2, arg3, arg4
        );
    }
}