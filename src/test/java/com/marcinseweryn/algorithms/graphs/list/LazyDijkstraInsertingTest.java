package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static com.marcinseweryn.algorithms.graphs.list.LazyDijkstraInserting.*;
import static org.junit.jupiter.api.Assertions.*;

class LazyDijkstraInsertingTest {
    static List<List<LazyDijkstraInserting.Edge>> graph;

    @BeforeEach
    void beforeEach() {
        final int N = 7;
        graph = createEmptyGraph(N);

        //              (3)
        //         > 1 ----> 5
        //    (2) /  |\(1)     \(9)
        //       /   | \        \
        //     0     |  >3        > 6
        //      \ (6)|          /
        //    (5)\   >         /(7)
        //        >  2 -----> 5
        //              (8)
        addDirectedEdge(graph, 0, 1, 2);
        addDirectedEdge(graph, 0, 2, 5);
        addDirectedEdge(graph, 1, 4, 3);
        addDirectedEdge(graph, 1, 3, 1);
        addDirectedEdge(graph, 1, 2, 6);
        addDirectedEdge(graph, 2, 5, 8);
        addDirectedEdge(graph, 3, 4, 4);
        addDirectedEdge(graph, 4, 6, 9);
        addDirectedEdge(graph, 5, 6, 7);
    }

    @ParameterizedTest(name = "{index} => {0}, {1}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            expected array                                       | start
             0.0,  2.0,  5.0,  3.0,  5.0,  13.0, 14.0            | 0
            -1.0,  0.0,  6.0,  1.0,  3.0,  14.0, 12.0            | 1
            -1.0, -1.0,  0.0, -1.0, -1.0,  8.0,  15.0            | 2
            -1.0, -1.0, -1.0,  0.0,  4.0, -1.0,  13.0            | 3
            -1.0, -1.0, -1.0, -1.0,  0.0, -1.0,  9.0             | 4
            -1.0, -1.0, -1.0, -1.0, -1.0,  0.0,  7.0             | 5
            -1.0, -1.0, -1.0, -1.0, -1.0, -1.0,  0.0             | 6
            """)
    void testDijkstra(@ConvertWith(ToDoubleArrayConverter.class) Double[] expected, int start) {
        for (int i = 0; i < expected.length; i++) {
            if (expected[i] == -1.0) {
                expected[i] = Double.POSITIVE_INFINITY;
            }
        }

        Double[] actual = dijkstra(graph, start);
        assertArrayEquals(expected, actual);
    }
    @ParameterizedTest(name = "{index} => {0}, {1}, {2}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            expected array           | start            | end
            0,1,4,6                  | 0                | 6
            3,4                      | 3                | 4
            2,5,6                    | 2                | 6
            """)
    void testFindShortestPath(@ConvertWith(ToIntegerArrayConverter.class) Integer[] expected, int start, int end) {

        List<Integer> actualPath = findShortestPath(graph, start, end);
        Integer[] actual = new Integer[actualPath.size()];
        for (int i = 0; i < actual.length; i++) {
            actual[i] = actualPath.get(i);
        }

        assertArrayEquals(expected, actual);
    }


    public static class ToDoubleArrayConverter extends TypedArgumentConverter<String, Double[]> {
        protected ToDoubleArrayConverter() {
            super(String.class, Double[].class);
        }

        @Override
        protected Double[] convert(String source) throws ArgumentConversionException {
                return Arrays.stream(source.split(",")).map(Double::valueOf).toArray(Double[]::new);
        }
    }

    public static class ToIntegerArrayConverter extends TypedArgumentConverter<String, Integer[]> {
        protected ToIntegerArrayConverter() {
            super(String.class, Integer[].class);
        }

        @Override
        protected Integer[] convert(String source) throws ArgumentConversionException {
            return Arrays.stream(source.split(",")).map(Integer::valueOf).toArray(Integer[]::new);
        }
    }
}