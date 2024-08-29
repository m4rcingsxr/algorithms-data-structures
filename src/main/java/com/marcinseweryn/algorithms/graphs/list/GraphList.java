package com.marcinseweryn.algorithms.graphs.list;

import com.marcinseweryn.algorithms.graphs.GraphNode;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

public class GraphList<T> {
    List<GraphNode<T>> nodeList;

    public GraphList(List<GraphNode<T>> nodeList) {
        this.nodeList = nodeList;
    }

    public void addDirectedEdge(int i, int j) {
        GraphNode<T> first = nodeList.get(i);
        GraphNode<T> second = nodeList.get(j);
        first.getNeighbors().add(second);
    }

    public void addUndirectedEdge(int i, int j) {
        GraphNode<T> first = nodeList.get(i);
        GraphNode<T> second = nodeList.get(j);
        first.getNeighbors().add(second);
        second.getNeighbors().add(first);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GraphNode<T> tGraphNode : nodeList) {
            sb.append(tGraphNode.getElement()).append(": ");
            for (int j = 0; j < tGraphNode.getNeighbors().size(); j++) {
                sb.append(tGraphNode.getNeighbors().get(j).getElement());
                if (j < tGraphNode.getNeighbors().size() - 1) {
                    sb.append("->");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList<GraphNode<String>> nodeList = new ArrayList<>();
        nodeList.add(new GraphNode<>("A"));
        nodeList.add(new GraphNode<>("B"));
        nodeList.add(new GraphNode<>("C"));
        nodeList.add(new GraphNode<>("D"));
        nodeList.add(new GraphNode<>("E"));
        GraphList<String> graphList = new GraphList<>(nodeList);

        graphList.addUndirectedEdge(0, 1);
        graphList.addUndirectedEdge(0, 2);
        graphList.addUndirectedEdge(0, 3);
        graphList.addUndirectedEdge(1, 4);
        graphList.addUndirectedEdge(2, 3);
        graphList.addUndirectedEdge(3, 4);
        System.out.println(graphList);

        /*
            A: B->C->D
            B: A->E
            C: A->D
            D: A->C->E
            E: B->D
         */
    }
}
