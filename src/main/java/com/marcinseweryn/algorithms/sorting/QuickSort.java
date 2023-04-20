package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Implements the QuickSort algorithm to sort an array of elements in ascending order.
 * QuickSort works by selecting a pivot element, partitioning the array into two sub-arrays
 * based on the pivot, and recursively sorting the sub-arrays.
 */
public class QuickSort {
    private QuickSort() {
        // Utility class
    }

    /**
     * Sorts the given array of integers using the quick sort algorithm.
     *
     * @param array the array of integers to be sorted
     */
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    /**
     * Sorts the given sub-array of integers using the quick sort algorithm.
     *
     * @param array the array of integers to be sorted
     * @param start the starting index of the sub-array to be sorted
     * @param end   the ending index of the sub-array to be sorted
     */
    private static void quickSort(int[] array, int start, int end) {

        // Base case: if the sub-array has length less than 2, it is already sorted
        if (start >= end) return;

        // Choose a pivot element and partition the sub-array around it
        int pivot = partition(array, start, end);

        // Recursively sort the two partitions
        quickSort(array, start, pivot - 1);
        quickSort(array, pivot + 1, end);
    }

    /**
     * Partitions the given sub-array of integers around a pivot element.
     *
     * @param array the array of integers to be partitioned
     * @param start the starting index of the sub-array to be partitioned
     * @param end   the ending index of the sub-array to be partitioned
     * @return the index of the pivot element after partitioning
     */
    private static int partition(int[] array, int start, int end) {

        // Choose the last element of the sub-array as the pivot
        int pivot = array[end];
        int pIndex = start;
        for (int i = start; i < end; i++) {

            // Partition the sub-array into elements less than or equal to the pivot,
            // and elements greater than the pivot
            if (array[i] <= pivot) {
                int temp = array[i];
                array[i] = array[pIndex];
                array[pIndex] = temp;
                pIndex++;
            }
        }

        // Move the pivot element to its final position in the partitioned sub-array
        array[end] = array[pIndex];
        array[pIndex] = pivot;

        // Return the index of the pivot element
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
