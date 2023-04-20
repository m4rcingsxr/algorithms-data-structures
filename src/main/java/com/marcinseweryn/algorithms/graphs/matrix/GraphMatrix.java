package com.marcinseweryn.algorithms.graphs.matrix;

import com.marcinseweryn.algorithms.graphs.list.GraphNode;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

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
        for (int i = 0; i < nodeList.size(); i++) {
            sb.append(nodeList.get(i).getElement()).append(" ");
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
        GraphNode<Character> a = new GraphNode<>('A');
        GraphNode<Character> b = new GraphNode<>('B');
        GraphNode<Character> c = new GraphNode<>('C');
        GraphNode<Character> d = new GraphNode<>('D');
        GraphNode<Character> e = new GraphNode<>('E');
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

        GraphMatrix<Character> graphMatrix = new GraphMatrix<>(listNode,
                                                               matrix
        );
        out.println(graphMatrix);

        /*   Output:
           A B C D E
        A: 0 1 1 1 0
        B: 1 0 0 0 1
        C: 1 0 0 1 0
        D: 1 0 1 0 1
        E: 0 1 0 1 0 */

    }
}
