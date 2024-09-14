package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordAdjacencyListTest {

    private static List<List<BellmanFordAdjacencyList.Edge>> graphWithoutNegativeCycle;
    private static List<List<BellmanFordAdjacencyList.Edge>> graphWithNegativeCycle;
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
        graphWithoutNegativeCycle = BellmanFordAdjacencyList.createGraph(V);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 0, 2, 6);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 0, 3, 6);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 1, 0, 3);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 2, 3, 1);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 3, 2, 2);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 3, 1, 1);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 4, 1, 4);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithoutNegativeCycle, 4, 3, 2);

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
        graphWithNegativeCycle = BellmanFordAdjacencyList.createGraph(V);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 0, 2, 6);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 0, 3, -6);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 1, 0, 3);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 2, 3, 1);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 3, 2, 2);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 3, 1, 1);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 4, 1, 4);
        BellmanFordAdjacencyList.addDirectedEdge(graphWithNegativeCycle, 4, 3, 2);
    }

    @Test
    void givenGraphWithoutNegativeCycle_whenBellmanFord_thenCorrectDistances() {
        // When: Running Bellman-Ford algorithm on a graph without negative weight cycles
        double[] distances = BellmanFordAdjacencyList.bellmanFord(graphWithoutNegativeCycle, V, 4);

        // Then: The correct distances should be computed
        double[] expectedDistances = {6.0, 3.0, 4.0, 2.0, 0.0};
        assertArrayEquals(expectedDistances, distances, "The computed distances are incorrect.");
    }

    @Test
    void givenGraphWithNegativeCycle_whenBellmanFord_thenDetectNegativeCycle() {
        // When: Running Bellman-Ford algorithm on a graph with a negative weight cycle
        double[] distances = BellmanFordAdjacencyList.bellmanFord(graphWithNegativeCycle, V, 4);

        // Then: The algorithm should detect the negative cycle
        double[] expectedDistances = {Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 0.0};
        assertArrayEquals(expectedDistances, distances, "The algorithm did not correctly detect the negative cycle.");
    }

    @Test
    void givenGraphWithIsolatedVertex_whenBellmanFord_thenCorrectDistances() {
        // Given: A graph where vertex 3 is isolated
        List<List<BellmanFordAdjacencyList.Edge>> graph = BellmanFordAdjacencyList.createGraph(4);
        BellmanFordAdjacencyList.addDirectedEdge(graph, 0, 1, 1);
        BellmanFordAdjacencyList.addDirectedEdge(graph, 1, 2, 2);

        // When: Running Bellman-Ford algorithm from vertex 0
        double[] distances = BellmanFordAdjacencyList.bellmanFord(graph, 4, 0);

        // Then: The isolated vertex should have a distance of infinity
        double[] expectedDistances = {0.0, 1.0, 3.0, Double.POSITIVE_INFINITY};
        assertArrayEquals(expectedDistances, distances, "The distances for the graph with an isolated vertex are incorrect.");
    }

    @Test
    void givenGraphWithDisconnectedComponents_whenBellmanFord_thenCorrectDistances() {
        // Given: A graph with two disconnected components
        List<List<BellmanFordAdjacencyList.Edge>> graph = BellmanFordAdjacencyList.createGraph(4);
        BellmanFordAdjacencyList.addDirectedEdge(graph, 0, 1, 1);
        BellmanFordAdjacencyList.addDirectedEdge(graph, 2, 3, 2);

        // When: Running Bellman-Ford algorithm from vertex 0
        double[] distances = BellmanFordAdjacencyList.bellmanFord(graph, 4, 0);

        // Then: Distances should be computed only for the connected component of the start vertex
        double[] expectedDistances = {0.0, 1.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        assertArrayEquals(expectedDistances, distances, "The distances for the graph with disconnected components are incorrect.");
    }
}
