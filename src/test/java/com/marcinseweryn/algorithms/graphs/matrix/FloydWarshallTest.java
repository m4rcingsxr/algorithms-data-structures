package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FloydWarshallTest {

    @Test
    void testFloydWarshallWithNegativeCycle() {
        double[][] graph = {
                {0.0, 1.0, 2.0},
                {2.0, 0.0, -1.0},
                {-4.0, 3.0, 0.0}
        };
        assertThrows(IllegalStateException.class,
                     () -> FloydWarshall.floydWarshall(graph)
        );
    }

    @ParameterizedTest
    @MethodSource("graphProvider")
    public void testFloydWarshall(double[][] graph, double[][] expected) {
        double[][] result = FloydWarshall.floydWarshall(graph);
        Assertions.assertArrayEquals(expected, result);
    }

    private static Object[][] graphProvider() {
        double[][] graph1 = FloydWarshall.createGraph(5);
        FloydWarshall.addDirectedWeightedEdge(graph1, 0, 1, 2);
        FloydWarshall.addDirectedWeightedEdge(graph1, 0, 2, 7);
        FloydWarshall.addDirectedWeightedEdge(graph1, 0, 4, 6);
        FloydWarshall.addDirectedWeightedEdge(graph1, 1, 3, 2);
        FloydWarshall.addDirectedWeightedEdge(graph1, 3, 4, 1);
        FloydWarshall.addDirectedWeightedEdge(graph1, 4, 2, 1);
        double[][] expected1 = {
                {0.0, 2.0, 6.0, 4.0, 5.0},
                {Double.POSITIVE_INFINITY, 0.0, 4.0, 2.0, 3.0},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0.0,
                 Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 2.0, 0.0
                        , 1.0},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1.0,
                 Double.POSITIVE_INFINITY, 0.0}
        };
        return new Object[][]{{graph1, expected1}};
    }


}