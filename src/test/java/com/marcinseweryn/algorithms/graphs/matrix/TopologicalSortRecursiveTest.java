package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.marcinseweryn.algorithms.graphs.matrix.TopologicalSortRecursive.addDirectedEdge;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("TopologicalSortRecursive Tests")
class TopologicalSortRecursiveTest {

    @ParameterizedTest(name = "{index} => Given graph {0} should return {1}")
    @MethodSource("graphProvider")
    void shouldReturnTopologicalOrder(int[][] graph, int[] expected) {
        int[] actual = TopologicalSortRecursive.getTopologicalOrder(graph);
        assertArrayEquals(expected, actual);
    }

    private static Stream<Arguments> graphProvider() {
        return Stream.of(
                Arguments.of(createGraph1(), new int[]{0, 1, 2, 3, 4}),
                Arguments.of(createGraph2(), new int[]{0, 11, 9, 10, 1, 8, 12, 7, 6, 5, 3, 2, 4}),
                Arguments.of(createGraph3(), new int[]{3, 11, 9, 10, 7, 6, 5, 1, 8, 12, 0, 2, 4})
        );
    }

    private static int[][] createGraph1() {
        int[][] graph = TopologicalSortRecursive.createGraph(5);
        addDirectedEdge(graph, 0, 1);
        addDirectedEdge(graph, 1, 2);
        addDirectedEdge(graph, 2, 3);
        addDirectedEdge(graph, 3, 4);
        return graph;
    }

    private static int[][] createGraph2() {

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >3      >5
        //                  \       /       /
        //                   0  --> 7  --> 6
        //                    \
        //                    >11
        int[][] graph = TopologicalSortRecursive.createGraph(13);
        addDirectedEdge(graph, 0, 11);
        addDirectedEdge(graph, 0, 7);
        addDirectedEdge(graph, 0, 9);
        addDirectedEdge(graph, 9, 10);
        addDirectedEdge(graph, 10, 1);
        addDirectedEdge(graph, 1, 8);
        addDirectedEdge(graph, 8, 12);
        addDirectedEdge(graph, 7, 3);
        addDirectedEdge(graph, 7, 6);
        addDirectedEdge(graph, 6, 5);
        addDirectedEdge(graph, 3, 2);
        addDirectedEdge(graph, 2, 4);
        return graph;
    }

    private static int[][] createGraph3() {

        //                > 1    > 12
        //               /   \ /
        //             10<    >8     2 --> 4
        //               \           /
        //                9<         >0      >5
        //                  \       /       /
        //                   3  --> 7  --> 6
        //                    \
        //                    >11
        int[][] graph = TopologicalSortRecursive.createGraph(13);
        addDirectedEdge(graph, 3, 11);
        addDirectedEdge(graph, 3, 7);
        addDirectedEdge(graph, 3, 9);
        addDirectedEdge(graph, 9, 10);
        addDirectedEdge(graph, 10, 1);
        addDirectedEdge(graph, 1, 8);
        addDirectedEdge(graph, 8, 12);
        addDirectedEdge(graph, 7, 0);
        addDirectedEdge(graph, 7, 6);
        addDirectedEdge(graph, 6, 5);
        addDirectedEdge(graph, 0, 2);
        addDirectedEdge(graph, 2, 4);
        return graph;
    }
}