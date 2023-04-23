package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.marcinseweryn.algorithms.graphs.list.EagerDijkstra.*;

class EagerDijkstraTest {

    @ParameterizedTest(name = "{index} => {0} : {1}")
    @MethodSource("graphProvider")
    void testDijkstra(List<List<Edge>> graph, double[] expected) {
        double[] dist = dijkstra(graph, 0);
        Assertions.assertArrayEquals(expected, dist);
    }

    static List<List<Edge>> graph1() {
        final int N = 7;
        List<List<Edge>> graph = createGraph(N);
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

    static List<List<Edge>> graph2() {
        final int N = 6;
        List<List<Edge>> graph = createGraph(N);
        addDirectedEdge(graph, 0, 1, 5);
        addDirectedEdge(graph, 0, 2, 3);
        addDirectedEdge(graph, 1, 3, 6);
        addDirectedEdge(graph, 1, 2, 2);
        addDirectedEdge(graph, 2, 4, 4);
        addDirectedEdge(graph, 2, 5, 2);
        addDirectedEdge(graph, 2, 3, 7);
        addDirectedEdge(graph, 3, 4, -1);
        addDirectedEdge(graph, 4, 5, -2);
        return graph;
    }

    static List<List<Edge>> graph3() {
        final int N = 4;
        List<List<Edge>> graph = createGraph(N);
        addDirectedEdge(graph, 0, 1, 1);
        addDirectedEdge(graph, 0, 2, 4);
        addDirectedEdge(graph, 1, 2, 2);
        addDirectedEdge(graph, 1, 3, 7);
        addDirectedEdge(graph, 2, 3, 3);
        return graph;
    }

    static Stream<Arguments> graphProvider() {
        return Stream.of(
                Arguments.of(graph1(),
                             new double[]{0.0, 2.0, 5.0, 3.0, 5.0, 13.0, 14.0}
                ),
                Arguments.of(graph2(),
                             new double[]{0.0, 5.0, 3.0, 10.0, 7.0, 5.0}
                ),
                Arguments.of(graph3(), new double[]{0.0, 1.0, 3.0, 6.0})
        );
    }


}