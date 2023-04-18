package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.marcinseweryn.algorithms.graphs.list.KruskalEdgeList.Edge;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KruskalEdgeListTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testKruskals(Edge[] edges, int noElements, Long expectedMSP) {
        Long actualMSP = KruskalEdgeList.Kruskals(edges, noElements);
        assertEquals(expectedMSP, actualMSP);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(getEdges(), 10, 14L)
        );
    }

    private static Edge[] getEdges() {
        Edge[] edges = new Edge[18];
        edges[0] = new Edge(0, 1, 5);
        edges[1] = new Edge(1, 2, 4);
        edges[2] = new Edge(2, 9, 2);
        edges[3] = new Edge(0, 4, 1);
        edges[4] = new Edge(0, 3, 4);
        edges[5] = new Edge(1, 3, 2);
        edges[6] = new Edge(2, 7, 4);
        edges[7] = new Edge(2, 8, 1);
        edges[8] = new Edge(9, 8, 0);
        edges[9] = new Edge(4, 5, 1);
        edges[10] = new Edge(5, 6, 7);
        edges[11] = new Edge(6, 8, 4);
        edges[12] = new Edge(4, 3, 2);
        edges[13] = new Edge(5, 3, 5);
        edges[14] = new Edge(3, 6, 11);
        edges[15] = new Edge(6, 7, 1);
        edges[16] = new Edge(3, 7, 2);
        edges[17] = new Edge(7, 8, 6);
        return edges;
    }
}