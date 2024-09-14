package com.marcinseweryn.algorithms.graphs.list;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BellmanFordEdgeListTest {

    private static BellmanFordEdgeList.Edge[] edgesWithoutNegativeCycle;
    private static BellmanFordEdgeList.Edge[] edgesWithNegativeCycle;
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
        edgesWithoutNegativeCycle = new BellmanFordEdgeList.Edge[8];
        edgesWithoutNegativeCycle[0] = new BellmanFordEdgeList.Edge(0, 2, 6);
        edgesWithoutNegativeCycle[1] = new BellmanFordEdgeList.Edge(0, 3, 6);
        edgesWithoutNegativeCycle[2] = new BellmanFordEdgeList.Edge(1, 0, 3);
        edgesWithoutNegativeCycle[3] = new BellmanFordEdgeList.Edge(2, 3, 1);
        edgesWithoutNegativeCycle[4] = new BellmanFordEdgeList.Edge(3, 2, 2);
        edgesWithoutNegativeCycle[5] = new BellmanFordEdgeList.Edge(3, 1, 1);
        edgesWithoutNegativeCycle[6] = new BellmanFordEdgeList.Edge(4, 1, 4);
        edgesWithoutNegativeCycle[7] = new BellmanFordEdgeList.Edge(4, 3, 2);

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
        edgesWithNegativeCycle = new BellmanFordEdgeList.Edge[8];
        edgesWithNegativeCycle[0] = new BellmanFordEdgeList.Edge(0, 2, 6);
        edgesWithNegativeCycle[1] = new BellmanFordEdgeList.Edge(0, 3, -6);
        edgesWithNegativeCycle[2] = new BellmanFordEdgeList.Edge(1, 0, 3);
        edgesWithNegativeCycle[3] = new BellmanFordEdgeList.Edge(2, 3, 1);
        edgesWithNegativeCycle[4] = new BellmanFordEdgeList.Edge(3, 2, 2);
        edgesWithNegativeCycle[5] = new BellmanFordEdgeList.Edge(3, 1, 1);
        edgesWithNegativeCycle[6] = new BellmanFordEdgeList.Edge(4, 1, 4);
        edgesWithNegativeCycle[7] = new BellmanFordEdgeList.Edge(4, 3, 2);
    }

    @Test
    void givenGraphWithoutNegativeCycle_whenBellmanFord_thenCorrectDistances() {
        // When: Running Bellman-Ford algorithm on a graph without negative weight cycles
        double[] distances = BellmanFordEdgeList.bellmanFord(edgesWithoutNegativeCycle, 4, V);

        // Then: The correct distances should be computed
        double[] expectedDistances = {6.0, 3.0, 4.0, 2.0, 0.0};
        assertArrayEquals(expectedDistances, distances, "The computed distances are incorrect.");
    }

    @Test
    void givenGraphWithNegativeCycle_whenBellmanFord_thenDetectNegativeCycle() {
        // When: Running Bellman-Ford algorithm on a graph with a negative weight cycle
        double[] distances = BellmanFordEdgeList.bellmanFord(edgesWithNegativeCycle, 4, V);

        // Then: The algorithm should detect the negative cycle
        double[] expectedDistances = {Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY, 0.0};
        assertArrayEquals(expectedDistances, distances, "The algorithm did not correctly detect the negative cycle.");
    }

    @Test
    void givenGraphWithIsolatedVertex_whenBellmanFord_thenCorrectDistances() {
        // Given: An edge list where vertex 3 is isolated
        BellmanFordEdgeList.Edge[] edges = new BellmanFordEdgeList.Edge[2];
        edges[0] = new BellmanFordEdgeList.Edge(0, 1, 1);
        edges[1] = new BellmanFordEdgeList.Edge(1, 2, 2);

        // When: Running Bellman-Ford algorithm from vertex 0
        double[] distances = BellmanFordEdgeList.bellmanFord(edges, 0, 4); // Graph has 4 vertices

        // Then: The isolated vertex should have a distance of infinity
        double[] expectedDistances = {0.0, 1.0, 3.0, Double.POSITIVE_INFINITY};
        assertArrayEquals(expectedDistances, distances, "The distances for the graph with an isolated vertex are incorrect.");
    }

    @Test
    void givenGraphWithDisconnectedComponents_whenBellmanFord_thenCorrectDistances() {
        // Given: An edge list where the graph has two disconnected components
        BellmanFordEdgeList.Edge[] edges = new BellmanFordEdgeList.Edge[2];
        edges[0] = new BellmanFordEdgeList.Edge(0, 1, 1);
        edges[1] = new BellmanFordEdgeList.Edge(2, 3, 2);

        // When: Running Bellman-Ford algorithm from vertex 0
        double[] distances = BellmanFordEdgeList.bellmanFord(edges, 0, 4); // Graph has 4 vertices

        // Then: Distances should be computed only for the connected component of the start vertex
        double[] expectedDistances = {0.0, 1.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY};
        assertArrayEquals(expectedDistances, distances, "The distances for the graph with disconnected components are incorrect.");
    }

}
