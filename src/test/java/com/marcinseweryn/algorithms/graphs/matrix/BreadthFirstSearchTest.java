package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BreadthFirstSearchTest {

    @ParameterizedTest
    @MethodSource("graphProvider")
    public void testPrintBreadthFirstSearch(int[][] adjacencyMatrix,
                                            int start, String expected) {
        BreadthFirstSearch bfs = new BreadthFirstSearch(adjacencyMatrix);
        String actual = bfs.printBreadthFirstSearch(start);
        assertEquals(expected, actual);
    }

    static Collection<Object[]> graphProvider() {
        return Arrays.asList(new Object[][]{

                // 0 --- 1
                //   |     |
                //   3 --- 2
                // Graph 1
                {new int[][]{
                        {0, 1, 1, 0},
                        {1, 0, 1, 1},
                        {1, 1, 0, 1},
                        {0, 1, 1, 0}
                }, 0, "Breadth First Search from vertex 0:[0 1 2 3]"},

                //  0 --- 1
                //    \   /
                //     \ /
                //      2
                // Graph 2
                {new int[][]{
                        {0, 1, 1},
                        {1, 0, 1},
                        {1, 1, 0}
                }, 1, "Breadth First Search from vertex 1:[1 0 2]"},

                //   0 --- 1
                //    \   / \
                //     \ /   |
                //      2 --- 3
                // Graph 3
                {new int[][]{
                        {0, 1, 0, 0},
                        {1, 0, 1, 1},
                        {0, 1, 0, 1},
                        {0, 1, 1, 0}
                }, 3, "Breadth First Search from vertex 3:[3 1 2 0]"},

                });
    }
}