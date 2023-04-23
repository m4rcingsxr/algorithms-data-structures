package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.marcinseweryn.algorithms.graphs.list.LazyDijkstra.addDirectedEdge;
import static com.marcinseweryn.algorithms.graphs.list.LazyDijkstra.createGraph;

class LazyDijkstraTest {

    @ParameterizedTest(name = "{index} => {0} : {1}")
    @MethodSource
    void testDijkstra(List<List<LazyDijkstra.Edge>> graph, Double[] expected) {


        Double[] distances = LazyDijkstra.dijkstra(graph, 0);
        Assertions.assertArrayEquals(expected, distances);
    }

    private static List<List<LazyDijkstra.Edge>> graph1() {
        List<List<LazyDijkstra.Edge>> graph = createGraph(6);
        addDirectedEdge(graph, 0, 1, 1);
        addDirectedEdge(graph, 0, 2, 7);
        addDirectedEdge(graph, 1, 2, 2);
        addDirectedEdge(graph, 1, 3, 4);
        addDirectedEdge(graph, 1, 4, 3);
        addDirectedEdge(graph, 2, 3, 1);
        addDirectedEdge(graph, 2, 4, 5);
        addDirectedEdge(graph, 3, 4, 1);
        addDirectedEdge(graph, 4, 5, 2);
        return graph;
    }

    private static List<List<LazyDijkstra.Edge>> graph2() {
        final int N = 7;
        List<List<LazyDijkstra.Edge>> graph = createGraph(N);

        //              (3)
        //         > 1 ----> 4
        //    (2) /  |\(1) />  \(9)
        //       /   | \  /(4)   \
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
        return graph;
    }

    private static Stream<Arguments> testDijkstra() {
        return Stream.of(
                Arguments.of(
                        graph1(), new Double[]{0.0, 1.0, 3.0, 4.0, 4.0, 6.0}
                ),
                Arguments.of(
                        graph2(),
                        new Double[]{0.0, 2.0, 5.0, 3.0, 5.0, 13.0, 14.0}
                )
        );
    }
}