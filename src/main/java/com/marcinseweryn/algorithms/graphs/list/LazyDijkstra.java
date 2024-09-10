package com.marcinseweryn.algorithms.graphs.list;

import java.util.*;

/**
 * A lazy implementation of Dijkstra's algorithm for finding the shortest path between two vertices in a graph.
 * This implementation is suitable for graphs with non-negative edge weights. It is termed "lazy" because it
 * may reinsert nodes into the priority queue with updated distances if a shorter path is discovered later.
 * This approach is well-suited for graphs with cycles but not for graphs with negative edge weights.
 *
 * <h2>Complexity Analysis:</h2>
 * <h3>Time Complexity:</h3>
 * <p>
 * The time complexity of Dijkstra's algorithm with a priority queue is O((V + E) log V), where V is the number of vertices
 * and E is the number of edges. This is because:
 * <ul>
 *   <li>Each vertex and edge is processed at most once, with each vertex insertion and update operation in the priority queue
 *       taking O(log V) time.</li>
 *   <li>Thus, the overall time complexity is influenced by the operations on the priority queue, which is O((V + E) log V).</li>
 * </ul>
 * </p>
 *
 * <h3>Space Complexity:</h3>
 * <p>
 * The space complexity of the algorithm is O(V + E), where V is the number of vertices and E is the number of edges. This
 * includes:
 * <ul>
 *   <li>The space required to store the graph, which is O(E) as each edge is stored once.</li>
 *   <li>The space for the `distance`, `previous`, and `visited` arrays, which is O(V) as each vertex requires constant space.</li>
 * </ul>
 * </p>
 *
 * <h2>Graph Representation:</h2>
 * <p>
 * The graph is represented using an adjacency list, where each vertex has a list of outgoing edges. This representation
 * is efficient for sparse graphs and allows quick access to neighboring vertices.
 * </p>
 *
 * <h2>Implementation Notes:</h2>
 * <p>
 * The algorithm maintains a priority queue to always process the vertex with the smallest known distance. It uses a "lazy"
 * approach where vertices might be reinserted into the queue with updated distances. This is necessary because the shortest
 * path to a vertex might not be finalized until all possible shorter paths are explored. The `shortestPath` method reconstructs
 * the shortest path by backtracking from the end vertex using the `previous` array.
 * </p>
 *
 * <h2>Usage:</h2>
 * <p>
 * This implementation can be used to find the shortest path between any two vertices in a weighted graph with non-negative
 * weights. It can also be adapted to find the shortest paths from a single start node to all other nodes.
 * </p>
 */
public class LazyDijkstra {

    /**
     * Edge class representing a directed edge between two nodes with a specific weight.
     * Used to model the graph's edges.
     */
    public static class Edge {
        private final double weight;
        private final int from;
        private final int to;

        /**
         * Constructor to create an edge with a specified weight, starting point, and ending point.
         *
         * @param weight The weight of the edge.
         * @param from   The starting vertex of the edge.
         * @param to     The ending vertex of the edge.
         */
        public Edge(double weight, int from, int to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    /**
     * Node class representing a node in the graph with an ID and a tentative shortest path value (distance).
     * Used in the priority queue to determine the next node to process.
     */
    public static class Node {
        private final int id;
        private final double value;

        /**
         * Constructor to create a node with a specific ID and associated distance value.
         *
         * @param id    The identifier of the node.
         * @param value The current shortest known distance to this node.
         */
        public Node(int id, double value) {
            this.id = id;
            this.value = value;
        }

        /**
         * Gets the current distance value of the node.
         *
         * @return The distance value.
         */
        public double getValue() {
            return value;
        }
    }

    private final int noVertices;  // Number of vertices in the graph
    private List<List<Edge>> graph;  // Adjacency list representation of the graph
    private double[] distance;  // Array to hold the shortest distance to each vertex from the start vertex
    private Integer[] previous;  // Array to hold the previous node in the shortest path to reconstruct the path

    /**
     * Initializes the graph with a given number of vertices.
     * The graph is initially empty, with no edges.
     *
     * @param noVertices The number of vertices in the graph.
     */
    public LazyDijkstra(int noVertices) {
        this.noVertices = noVertices;
        this.initializeGraph();
    }

    /**
     * Adds a directed edge to the graph from a source vertex to a destination vertex with a specified weight.
     *
     * @param from   The source vertex.
     * @param to     The destination vertex.
     * @param weight The weight of the edge.
     */
    public void addDirectedEdge(int from, int to, int weight) {
        this.graph.get(from).add(new Edge(weight, from, to));  // Add the directed edge to the graph.
    }

    /**
     * Finds the shortest path from the start vertex to the end vertex using Dijkstra's algorithm.
     *
     * @param start The starting vertex.
     * @param end   The ending vertex.
     * @return A list of vertices representing the shortest path from start to end.
     *         If no path is found, returns an empty list.
     * @throws IllegalArgumentException if the start or end vertex is invalid.
     */
    public List<Integer> shortestPath(int start, int end) {
        if (end < 0 || end >= noVertices) throw new IllegalArgumentException("Invalid node index");
        if (start < 0 || start >= noVertices) throw new IllegalArgumentException("Invalid node index");

        double dist = this.dijkstra(start, end);
        List<Integer> path = new ArrayList<>();
        if (Double.isInfinite(dist)) return path;  // If the distance is infinite, there is no path.

        // Backtrack from the end node to the start node using the 'previous' array to reconstruct the path.
        for (Integer i = end; i != null; i = previous[i]) path.add(i);
        Collections.reverse(path);  // The path is reconstructed in reverse order, so reverse it to get the correct order.

        if(path.get(0) != start) return Collections.emptyList();
        return path;
    }

    /**
     * Runs Dijkstra's algorithm on the graph to find the shortest path from the start node to the end node.
     * The algorithm maintains a priority queue to always expand the most promising node next based on the
     * current known shortest distance.
     *
     * @param start The starting vertex for Dijkstra's algorithm.
     * @param end   The ending vertex for which the shortest path is sought.
     * @return The shortest distance from the start to the end vertex. Returns Double.POSITIVE_INFINITY if the end is unreachable.
     */
    public double dijkstra(int start, int end) {
        this.distance = new double[noVertices];
        Arrays.fill(distance, Double.POSITIVE_INFINITY);  // Initialize all distances to infinity.
        this.previous = new Integer[noVertices];  // Initialize the array for tracking the shortest path.
        boolean[] visited = new boolean[noVertices];  // Array to track visited nodes to avoid revisiting them.
        this.distance[start] = 0;  // The distance to the start node is zero.

        // Priority queue to select the node with the smallest tentative distance.
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(Node::getValue));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();  // Get the node with the smallest distance.
            visited[node.id] = true;  // Mark the node as visited.

            if(node.id == end) return distance[node.id];

            // If the current node's distance is outdated (greater than the known shortest), skip processing.
            if (this.distance[node.id] < node.value) continue;

            // Explore all edges of the current node to potentially update the shortest path to its neighbors.
            List<Edge> edges = graph.get(node.id);
            for (Edge edge : edges) {
                if (visited[edge.to]) continue;  // Skip already visited nodes.

                double newDistance = this.distance[edge.from] + edge.weight;
                if (newDistance < this.distance[edge.to]) {  // Relaxation step: check if a shorter path to 'edge.to' is found.
                    this.distance[edge.to] = newDistance;
                    this.previous[edge.to] = edge.from;
                    pq.offer(new Node(edge.to, this.distance[edge.to]));  // Push the updated distance to the priority queue.
                }
            }
        }

        // Check if the end node was reachable. If not, return infinity.
        if (Double.isInfinite(this.distance[end])) {
            return Double.POSITIVE_INFINITY;  // End node is unreachable.
        } else {
            return this.distance[end];  // Return the shortest distance to the end node.
        }
    }

    /**
     * Initializes an empty graph with the specified number of vertices.
     * The graph is represented as an adjacency list where each vertex has a list of outgoing edges.
     */
    private void initializeGraph() {
        this.graph = new ArrayList<>();  // Create a list to hold lists of edges for each vertex.
        for (int i = 0; i < this.noVertices; i++) {
            this.graph.add(new ArrayList<>());  // Initialize each vertex's edge list as empty.
        }
    }

    public static void main(String[] args) {
        final int N = 7;
        LazyDijkstra lazyDijkstra = new LazyDijkstra(N);

        //              (6)
        //         > 1 ----> 4
        //    (2) /  |\(1) />  \(9)
        //       /   | \  /(4)   \
        //     0     |  >3        > 6
        //      \ (6)|          /
        //    (5)\   >         /(7)
        //        >  2 -----> 5
        //              (8)
        lazyDijkstra.addDirectedEdge(0, 1, 2);
        lazyDijkstra.addDirectedEdge(0, 2, 5);
        lazyDijkstra.addDirectedEdge(1, 4, 6);
        lazyDijkstra.addDirectedEdge(1, 3, 1);
        lazyDijkstra.addDirectedEdge(1, 2, 6);
        lazyDijkstra.addDirectedEdge(2, 5, 8);
        lazyDijkstra.addDirectedEdge(3, 4, 4);
        lazyDijkstra.addDirectedEdge(4, 6, 9);
        lazyDijkstra.addDirectedEdge(5, 6, 7);

        System.out.println(lazyDijkstra.dijkstra(0, 6)); // 16.0
        System.out.println(lazyDijkstra.shortestPath(0, 6)); // [0, 1, 3, 4, 6]

        System.out.println(Arrays.toString(lazyDijkstra.distance)); // [0.0, 2.0, 5.0, 3.0, 7.0, 13.0, 16.0]
        System.out.println(Arrays.toString(lazyDijkstra.previous)); // [null, 0, 0, 1, 3, 2, 4]
    }

}