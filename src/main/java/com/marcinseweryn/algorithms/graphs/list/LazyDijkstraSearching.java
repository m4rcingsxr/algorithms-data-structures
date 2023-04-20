package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class LazyDijkstraSearching {
    private static class WeightedNode implements Comparable<WeightedNode> {
        private final String name;
        ArrayList<WeightedNode> neighbors;
        HashMap<WeightedNode, Integer> weightMap;
        WeightedNode parent;
        int distance;
        private final int index;

        public WeightedNode(String name, int index) {
            neighbors = new ArrayList<>();
            weightMap = new HashMap<>();
            this.name = name;

            // temp fix
            distance = Integer.MAX_VALUE - 1000;
            this.index = index;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(WeightedNode o) {
            return this.distance - o.distance;
        }

    }

    private final ArrayList<WeightedNode> nodeList;

    public LazyDijkstraSearching(ArrayList<WeightedNode> nodeList) {
        this.nodeList = nodeList;
    }

    // Dijkstra's algorithm
    void dijkstra(WeightedNode node) {
        PriorityQueue<WeightedNode> queue = new PriorityQueue<>();
        node.distance = 0;
        queue.addAll(nodeList);
        while (!queue.isEmpty()) {
            WeightedNode current = queue.remove();
            for (WeightedNode neighbor : current.neighbors) {

                // Check neighbor is visited
                if (queue.contains(neighbor)) {
                    if (neighbor.distance > current.distance + current.weightMap.get(neighbor)) {

                        // No heapify after editing object!
                        neighbor.distance =
                                current.distance + current.weightMap.get(neighbor);
                        neighbor.parent = current;

                        // Refresh queue
                        queue.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        print();
    }

    private void print() {
        for (WeightedNode weightedNode : nodeList) {
            System.out.print("Node " + weightedNode + ", distance: " + weightedNode.distance + ", Path: ");
            pathPrint(weightedNode);
            System.out.println();
        }
    }

    private void pathPrint(WeightedNode weightedNode) {
        if (weightedNode.parent != null) {
            pathPrint(weightedNode.parent);
        }
        System.out.print(weightedNode + " ");
    }

    public void addWeightedEdge(int from, int to, int weight) {
        WeightedNode first = nodeList.get(from);
        WeightedNode second = nodeList.get(to);
        first.neighbors.add(second);
        first.weightMap.put(second, weight);
    }

    public static void main(String[] args) {
        ArrayList<WeightedNode> nodeList = new ArrayList<>();
        nodeList.add(new WeightedNode("A", 0));
        nodeList.add(new WeightedNode("B", 1));
        nodeList.add(new WeightedNode("C", 2));
        nodeList.add(new WeightedNode("D", 3));
        nodeList.add(new WeightedNode("E", 4));
        nodeList.add(new WeightedNode("F", 5));
        nodeList.add(new WeightedNode("G", 6));
        LazyDijkstraSearching weigthedGraphDijkstra =
                new LazyDijkstraSearching(nodeList);
        weigthedGraphDijkstra.addWeightedEdge(0, 1, 2);
        weigthedGraphDijkstra.addWeightedEdge(0, 2, 5);
        weigthedGraphDijkstra.addWeightedEdge(1, 3, 1);
        weigthedGraphDijkstra.addWeightedEdge(1, 2, 6);
        weigthedGraphDijkstra.addWeightedEdge(1, 4, 3);
        weigthedGraphDijkstra.addWeightedEdge(2, 5, 8);
        weigthedGraphDijkstra.addWeightedEdge(2, 4, 4);
        weigthedGraphDijkstra.addWeightedEdge(4, 6, 9);
        weigthedGraphDijkstra.addWeightedEdge(5, 6, 7);

        System.out.println("Printing Dijkstra from source: A");
        weigthedGraphDijkstra.dijkstra(nodeList.get(0));
    }
}
