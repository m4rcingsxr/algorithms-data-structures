package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DepthFirstSearchRecursiveTest {

    @Test
    @DisplayName("Test dfs on empty graph")
    void testDfsOnEmptyGraph() {
        int[][] graph = new int[0][0];
        String expected = "Depth First Search starting from vertex 0:[]";
        String actual = DepthFirstSearchRecursive.dfs(graph, 0);
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("graphProvider")
    @DisplayName("Test dfs on various graphs")
    void testDfs(int[][] graph, int start, String expected) {
        String actual = DepthFirstSearchRecursive.dfs(graph, start);
        Assertions.assertEquals(expected, actual);
    }

    static Stream<Arguments> graphProvider() {
        int[][] graph1 = new int[][]{
                {0, 1, 1, 0},
                {1, 0, 0, 1},
                {1, 0, 0, 1},
                {0, 1, 1, 0}
        };
        String expected1 = "Depth First Search starting from vertex 0:[0,1,3,2]";

        int[][] graph2 = new int[][]{
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };
        String expected2 = "Depth First Search starting from vertex 0:[0,1,2,3,4]";

        //   2---3
        //  /|\ /|\
        // 1 | X | 4
        //  \|/ \|/
        //   0---5
        int[][] graph3 = new int[][]{
                {0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {1, 1, 0, 1, 1, 0},
                {1, 0, 1, 0, 1, 1},
                {0, 0, 1, 1, 0, 1},
                {0, 0, 0, 1, 1, 0}
        };
        String expected3 = "Depth First Search starting from vertex 0:[0,2,1,3,4,5]";

        return Stream.of(
                Arguments.of(graph1, 0, expected1),
                Arguments.of(graph2, 0, expected2),
                Arguments.of(graph3, 0, expected3)
        );
    }
}