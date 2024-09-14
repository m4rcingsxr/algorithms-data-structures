package com.marcinseweryn.algorithms.graphs.matrix;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class BellmanFordTest {

    private static Integer[][] graphWithoutNegativeCycle;
    private static Integer[][] graphWithNegativeCycle;
    private static final int V = 5; // Number of vertices in the graph

    @BeforeAll
    static void setUp() {
        // Create a graph without a negative weight cycle
        //     1      4
        //   /|\    / \
        // 3/ | \ 4/   \2
        // /  |  \v     v
        //0   |   1 <---3
        // \  v   ^   /|
        //6 \  \  |  / |1
        //   \  > v /  |
        //    2 <--3   2
        //      1      2
        graphWithoutNegativeCycle = BellmanFord.createGraph(V);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 0, 2, 6);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 0, 3, 6);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 1, 0, 3);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 2, 3, 1);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 3, 2, 2);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 3, 1, 1);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 4, 1, 4);
        BellmanFord.addDirectedEdge(graphWithoutNegativeCycle, 4, 3, 2);

        // Create a graph with a negative weight cycle
        //    1      4
        //   /|\    / \
        // 3/ | \ 4/   \2
        // /  |  \v     v
        //0   |   1 <---3
        // \  v   ^   /|
        //-6 \  \  |  / |1
        //   \  > v /  |
        //    2 <--3   2
        //      1      2
        graphWithNegativeCycle = BellmanFord.createGraph(V);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 0, 2, 6);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 0, 3, -6);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 1, 0, 3);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 2, 3, 1);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 3, 2, 2);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 3, 1, 1);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 4, 1, 4);
        BellmanFord.addDirectedEdge(graphWithNegativeCycle, 4, 3, 2);
    }

    @Test
    void givenGraphWithoutNegativeCycle_whenBellmanFord_thenCorrectDistances() {
        // When: Running Bellman-Ford algorithm on a graph without negative weight cycles
        double[] distances = BellmanFord.bellmanFord(graphWithoutNegativeCycle, 4);

        // Then: The correct distances should be computed
        double[] expectedDistances = {6.0, 3.0, 4.0, 2.0, 0.0};
        assertArrayEquals(expectedDistances, distances, "The computed distances are incorrect.");
    }

    @Test
    void givenGraphWithNegativeCycle_whenBellmanFord_thenDetectNegativeCycle() {
        // When: Running Bellman-Ford algorithm on a graph with a negative weight cycle
        double[] distances = BellmanFord.bellmanFord(graphWithNegativeCycle, 4);

        // Then: The algorithm should detect the negative cycle
        double[] expectedDistances = {Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 0.0};
        assertArrayEquals(expectedDistances, distances, "The algorithm did not correctly detect the negative cycle.");
    }

    @Test
    void givenGraphWithIsolatedVertex_whenBellmanFord_thenCorrectDistances() {
        // Given: A graph where vertex 3 is isolated
        Integer[][] graph = BellmanFord.createGraph(4);
        BellmanFord.addDirectedEdge(graph, 0, 1, 1);
        BellmanFord.addDirectedEdge(graph, 1, 2, 2);

        // When: Running Bellman-Ford algorithm from vertex 0
        double[] distances = BellmanFord.bellmanFord(graph, 0);

        // Then: The isolated vertex should have a distance of infinity
        double[] expectedDistances = {0.0, 1.0, 3.0, Double.POSITIVE_INFINITY};
        assertArrayEquals(expectedDistances, distances, "The distances for the graph with an isolated vertex are incorrect.");
    }

    @Test
    void givenGraphWithDisconnectedComponents_whenBellmanFord_thenCorrectDistances() {
        // Given: A graph with two disconnected components
        Integer[][] graph = BellmanFord.createGraph(4);
        BellmanFord.addDirectedEdge(graph, 0, 1, 1);
        BellmanFord.addDirectedEdge(graph, 2, 3, 2);

        // When: Running Bellman-Ford algorithm from vertex 0
        double[] distances = BellmanFord.bellmanFord(graph, 0);

        // Then: Distances should be computed only for the connected component of the start vertex
        double[] expectedDistances = {0.0, 1.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        assertArrayEquals(expectedDistances, distances, "The distances for the graph with disconnected components are incorrect.");
    }
}