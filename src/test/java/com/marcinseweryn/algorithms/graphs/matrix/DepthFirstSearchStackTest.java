package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepthFirstSearchStackTest {

    @ParameterizedTest
    @MethodSource("graphProvider")
    void testDepthFirstSearch(int[][] graph, int start, String expectedPath) {
        String actualPath = DepthFirstSearchStack.depthFirstSearch(graph,
                                                                   start
        );
        assertEquals(expectedPath, actualPath);
    }

    private static Stream<Arguments> graphProvider() {
        return Stream.of(
                Arguments.of(
                        new int[][]{{0, 1, 0, 0}, {1, 0, 1, 1}, {0, 1, 0, 1},
                                    {0, 1, 1, 0}},
                        0, "Depth First Search starting from vertex 0:[0,1,3,2]"
                ),
                Arguments.of(
                        new int[][]{{0, 1, 0}, {1, 0, 1}, {0, 1, 0}},
                        2, "Depth First Search starting from vertex 2:[2,1,0]"
                ),
                Arguments.of(
                        new int[][]{{0}},
                        0, "Depth First Search starting from vertex 0:[0]"
                )
        );
    }
}