package com.marcinseweryn.algorithms.graphs.matrix;

import static java.lang.Math.min;

public class FloydWarshall {

    public static double[][] floydWarshall(double[][] matrix) {
        final int N = matrix.length;

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        return matrix;
    }


    public static void main(String[] args) {
        double[][] matrix = {
                {0, 2, 7, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, 2, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 0, 1},
                {Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, 1, Double.POSITIVE_INFINITY, 0}
        };

        floydWarshall(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

}
