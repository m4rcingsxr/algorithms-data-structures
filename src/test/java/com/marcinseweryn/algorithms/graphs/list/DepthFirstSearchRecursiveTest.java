package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class DepthFirstSearchRecursiveTest {

    @ParameterizedTest
    @MethodSource("graphProvider")
    void dfs(List<List<Integer>> graph, int start, String expected) {
        String actual = DepthFirstSearchRecursive.dfs(graph, start);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> graphProvider() {
        return Stream.of(
                Arguments.of(
                        createGraph1(),
                        0,
                        "Depth First Search starting from vertex 0:[0,1,2,3,4]"
                ),
                Arguments.of(
                        createGraph2(),
                        0,
                        "Depth First Search starting from vertex 0:[0,1,3,2,4]"
                )
        );
    }

    private static List<List<Integer>> createGraph1() {
        List<List<Integer>> graph = DepthFirstSearchRecursive.createGraph(5);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 1);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 0, 2);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 1, 2);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 2, 3);
        DepthFirstSearchRecursive.addUndirectedEdge(graph, 3, 4);
        return graph;
    }

    private static List<List<Integer>> createGraph2() {

        //    0 ---> 1
        //    |      |
        //    |      |
        //    >2     >3
        //      \
        //       \> 4
        List<List<Integer>> graph = DepthFirstSearchRecursive.createGraph(5);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 0, 1);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 0, 2);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 1, 3);
        DepthFirstSearchRecursive.addDirectedEdge(graph, 2, 4);
        return graph;
    }
}