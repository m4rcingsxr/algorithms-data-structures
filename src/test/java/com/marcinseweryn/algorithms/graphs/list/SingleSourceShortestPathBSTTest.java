package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static com.marcinseweryn.algorithms.graphs.list.SingleSourceShortestPathBST.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleSourceShortestPathBSTTest {

    public static List<List<Integer>> graph1() {
        List<List<Integer>> graph = createGraph(13);
        addUndirectedEdge(graph, 0, 7);
        addUndirectedEdge(graph, 0, 9);
        addUndirectedEdge(graph, 0, 11);
        addUndirectedEdge(graph, 7, 6);
        addUndirectedEdge(graph, 7, 3);
        addUndirectedEdge(graph, 6, 5);
        addUndirectedEdge(graph, 3, 4);
        addUndirectedEdge(graph, 2, 3);
        addUndirectedEdge(graph, 2, 12);
        addUndirectedEdge(graph, 12, 8);
        addUndirectedEdge(graph, 8, 1);
        addUndirectedEdge(graph, 1, 10);
        addUndirectedEdge(graph, 10, 9);
        addUndirectedEdge(graph, 9, 2);
        return graph;
    }

    public static List<List<Integer>> graph2() {
        List<List<Integer>> graph = createGraph(5);
        addUndirectedEdge(graph, 0, 1);
        addUndirectedEdge(graph, 0, 2);
        addUndirectedEdge(graph, 1, 3);
        addUndirectedEdge(graph, 2, 4);
        return graph;
    }

    public static List<List<Integer>> graph3() {
        List<List<Integer>> graph = createGraph(4);
        addDirectedEdge(graph, 0, 1);
        addDirectedEdge(graph, 1, 2);
        addDirectedEdge(graph, 2, 3);
        return graph;
    }

    @ParameterizedTest
    @MethodSource("graphProvider")
    void ssspTwoVertices(List<List<Integer>> graph, int start, int end, List<Integer> expected) {
        List<Integer> actual = SingleSourceShortestPathBST.ssspTwoVertices(graph, start, end);
        assertEquals(expected, actual);
    }

    static Object[][] graphProvider() {
        return new Object[][]{
                {graph1(), 0, 5, Arrays.asList(0, 7, 6, 5)},
                {graph1(), 0, 2, Arrays.asList(0, 9, 2)},
                {graph1(), 3, 12, Arrays.asList(3, 2, 12)},
                {graph2(), 0, 3, Arrays.asList(0, 1, 3)},
                {graph2(), 0, 4, Arrays.asList(0, 2, 4)},
                {graph3(), 0, 3, Arrays.asList(0, 1, 2, 3)}
        };
    }
}