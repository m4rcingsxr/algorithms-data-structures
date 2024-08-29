package com.marcinseweryn.algorithms.graphs.matrix;

import com.marcinseweryn.algorithms.graphs.GraphNode;

import java.util.ArrayList;
import java.util.List;

public class GraphMatrix<T> {
    List<GraphNode<T>> nodeList;
    int[][] adjacencyMatrix;

    public GraphMatrix(List<GraphNode<T>> nodeList) {
        this.nodeList = nodeList;
        int nodeListSize = nodeList.size();
        adjacencyMatrix = new int[nodeListSize][nodeListSize];
    }

    public GraphMatrix(List<GraphNode<T>> nodeList, int[][] adjacencyMatrix) {
        this.nodeList = nodeList;
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
        adjacencyMatrix[j][i] = 1;
    }

    public void addDirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("   ");
        for (GraphNode<T> tGraphNode : nodeList) {
            sb.append(tGraphNode.getElement()).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < nodeList.size(); i++) {
            sb.append(nodeList.get(i).getElement()).append(": ");
            for (int j : adjacencyMatrix[i]) {
                sb.append(j).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayList<GraphNode<Character>> listNode = new ArrayList<>();
        GraphNode<Character> a = new GraphNode<>('0');
        GraphNode<Character> b = new GraphNode<>('1');
        GraphNode<Character> c = new GraphNode<>('2');
        GraphNode<Character> d = new GraphNode<>('3');
        GraphNode<Character> e = new GraphNode<>('4');

        listNode.add(a);
        listNode.add(b);
        listNode.add(c);
        listNode.add(d);
        listNode.add(e);

        int[][] matrix = new int[][]{
                {0, 1, 1, 1, 0},
                {1, 0, 0, 0, 1},
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };

        GraphMatrix<Character> graphMatrix = new GraphMatrix<>(listNode, matrix);

        System.out.println(graphMatrix);

        GraphMatrix<Character> graphMatrix2 = new GraphMatrix<>(listNode);
        graphMatrix2.addUndirectedEdge(0, 1);
        graphMatrix2.addUndirectedEdge(0, 2);
        graphMatrix2.addUndirectedEdge(0, 3);
        graphMatrix2.addUndirectedEdge(1, 0);
        graphMatrix2.addUndirectedEdge(1, 4);
        graphMatrix2.addUndirectedEdge(2, 0);
        graphMatrix2.addUndirectedEdge(2, 3);
        graphMatrix2.addUndirectedEdge(3, 0);
        graphMatrix2.addUndirectedEdge(3, 2);
        graphMatrix2.addUndirectedEdge(3, 4);
        graphMatrix2.addUndirectedEdge(4, 1);
        graphMatrix2.addUndirectedEdge(4, 3);

        System.out.println(graphMatrix2);


        /*
               0 1 2 3 4
            0: 0 1 1 1 0
            1: 1 0 0 0 1
            2: 1 0 0 1 0
            3: 1 0 1 0 1
            4: 0 1 0 1 0
        */
    }
}
