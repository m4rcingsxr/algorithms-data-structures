package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

public class QuickSort {
    private QuickSort() {
        // No instantiate
    }
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int start, int end) {

        // Base case
        if(start >= end) return;
        int pivot = partition(array, start, end);
        quickSort(array,start, pivot - 1);
        quickSort(array,pivot + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int pIndex = start;
        for (int i = start; i < end; i++) {
            if(array[i] <= pivot) {
                int temp = array[i];
                array[i] = array[pIndex];
                array[pIndex] = temp;
                pIndex++;
            }
        }
        array[end] = array[pIndex];
        array[pIndex] = pivot;
        return pIndex;
    }

    public static void main(String[] args) {
        int[] array = {2, 8, 1, 3, 6, 13, 7, 5, 4, 0, 11, 17};
        out.println("-----------------------QUICK SORT---------------------");
        out.println("Unsorted array " + Arrays.toString(array));
        QuickSort.sort(array);
        out.print("Sorted array " + Arrays.toString(array));
    }
}
