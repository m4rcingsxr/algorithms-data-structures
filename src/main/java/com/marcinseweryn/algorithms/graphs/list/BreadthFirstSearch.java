package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static java.lang.System.out;

public class BreadthFirstSearch {

    static class GraphNode {
        private String name;

        private ArrayList<GraphNode> neighbors;

        private boolean visited;

        public GraphNode(String name) {
            this.name = name;
            neighbors = new ArrayList<>();
            visited = false;
        }
    }

    private List<GraphNode> graph;

    private BreadthFirstSearch(List<GraphNode> graph) {
        this.graph = graph;
    }

    public void addDirectedEdge(int i, int j) {
        graph.get(i).neighbors.add(graph.get(j));
    }

    public void addUndirectedEdge(int i, int j) {
        addDirectedEdge(i, j);
        addDirectedEdge(j, i);
    }

    public void printBreathFirstSearch(GraphNode start) {
        Queue<GraphNode> queue = new ArrayDeque<>();
        queue.add(start);
        start.visited = true;
        while (!queue.isEmpty()) {
            GraphNode current = queue.remove();
            out.print(current.name + " ");
            for (GraphNode neighbor : current.neighbors) {
                if (!neighbor.visited) {
                    queue.add(neighbor);
                    neighbor.visited = true;
                }
            }
        }
        graph = new ArrayList<>();
    }

    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<>();
        nodeList.add(new GraphNode("A"));
        nodeList.add(new GraphNode("B"));
        nodeList.add(new GraphNode("C"));
        nodeList.add(new GraphNode("D"));
        nodeList.add(new GraphNode("E"));
        BreadthFirstSearch g = new BreadthFirstSearch(nodeList);

        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 2);
        g.addUndirectedEdge(0, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(3, 4);


        g.printBreathFirstSearch(nodeList.get(0));
        out.println();
        nodeList = new ArrayList<>();
        nodeList.add(new GraphNode("0"));
        nodeList.add(new GraphNode("1"));
        nodeList.add(new GraphNode("2"));
        nodeList.add(new GraphNode("3"));
        nodeList.add(new GraphNode("4"));
        nodeList.add(new GraphNode("5"));
        nodeList.add(new GraphNode("6"));
        nodeList.add(new GraphNode("7"));
        nodeList.add(new GraphNode("8"));
        nodeList.add(new GraphNode("9"));
        nodeList.add(new GraphNode("10"));
        nodeList.add(new GraphNode("11"));
        nodeList.add(new GraphNode("12"));

        // A -- B
        // | \    \
        // |  \    E
        // |   \  /
        // C -- D

        g = new BreadthFirstSearch(nodeList);
        g.addUndirectedEdge(0, 7);
        g.addUndirectedEdge(0, 9);
        g.addUndirectedEdge(0, 11);
        g.addUndirectedEdge(7, 11);
        g.addUndirectedEdge(7, 6);
        g.addUndirectedEdge(7, 3);
        g.addUndirectedEdge(6, 5);
        g.addUndirectedEdge(3, 4);
        g.addUndirectedEdge(2, 3);
        g.addUndirectedEdge(2, 12);
        g.addUndirectedEdge(12, 8);
        g.addUndirectedEdge(8, 1);
        g.addUndirectedEdge(1, 10);
        g.addUndirectedEdge(10, 9);
        g.addUndirectedEdge(9, 8);

        //                 1     12
        //               /   \ /   \
        //             10     8     2   4
        //               \   /       \ /
        //                9          3       5
        //                  \       /       /
        //                   0  -- 7  -- 6
        //                    \
        //                      11

        g.printBreathFirstSearch(nodeList.get(0));

        //A B C D E
        //0 7 9 11 6 3 10 8 5 4 2 1 12
    }
}
