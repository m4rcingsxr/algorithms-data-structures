package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SingleSourceShortestPathBSTTest {
    @DisplayName("Test BFS algorithm")
    @ParameterizedTest(name = "Graph: {0}, start: {1} -> expected: {2}")
    @MethodSource("testData")
    void testBfs(int[][] graph, int start, Integer[] expected) {
        assertArrayEquals(expected, SingleSourceShortestPathBST.bfs(graph, start));
    }

    private static Stream<Arguments> testData() {
        return Stream.of(
                Arguments.of(
                        new int[][]{
                                {0, 1, 0, 1},
                                {1, 0, 1, 0},
                                {0, 1, 0, 1},
                                {1, 0, 1, 0}},
                        0, new Integer[]{null, 0, 1, 0}),
                Arguments.of(
                        new int[][]{
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
                        },
                        0, new Integer[]{null, 8, 12, 7, 3, 6, 7, 0, 9, 0, 9, 0, 8}
                )
        );
    }
}