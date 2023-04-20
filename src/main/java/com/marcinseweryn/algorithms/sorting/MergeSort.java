package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Implements the MergeSort algorithm to sort an array of elements in
 * ascending order.
 * MergeSort works by dividing the array into two halves, recursively sorting
 * each half,
 * and then merging the two sorted halves into a single sorted array.
 */
public class MergeSort {
    private MergeSort() {
        // Utility class
    }

    /**
     * Sorts the given array of comparable elements using the merge sort
     * algorithm.
     *
     * @param array the array of comparable elements to be sorted
     * @param <T>   the type of elements in the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(Object[] array) {
        int arrayLength = array.length;

        // Base case: if the array has length less than 2,
        // it is already sorted
        if (arrayLength < 2) return;

        // Divide the array into two halves and recursively sort each half
        int mid = arrayLength / 2;
        Object[] left = new Object[mid];
        Object[] right = new Object[arrayLength - mid];
        System.arraycopy(array, 0, left, 0, mid);
        if (arrayLength - mid >= 0)
            System.arraycopy(array, mid, right, 0, arrayLength - mid);
        sort(left);
        sort(right);

        // Merge the sorted halves back into a single sorted array
        merge(left, right, array);
    }


    /**
     * Merges two sorted arrays into a single sorted array.
     *
     * @param left  the left half of the array to be merged
     * @param right the right half of the array to be merged
     * @param array the array to hold the merged result
     * @param <T>   the type of elements in the arrays to be merged, must
     *              implement Comparable
     */
    private static <T extends Comparable<T>> void merge(Object[] left,
                                                        Object[] right,
                                                        Object[] array) {

        // Initialize variables to track the indices of the left and
        // right sub-arrays, as well as the merged array
        int k = 0;
        int i = 0;
        int j = 0;

        //  Iterate over both sub-arrays and compare their elements,
        //  copying them to the merged array in the appropriate order
        while (i < left.length && j < right.length) {
            if (((T) left[i]).compareTo((T) right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        // Copy any remaining elements from the left
        // or right sub-array to the merged array
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        out.println("-----------MERGE SORT------------");
        out.println("Integer[]");
        Integer[] array = {2, 8, 1, 3, 6, 7, 5, 4};
        out.println("unsorted" + Arrays.toString(array));
        MergeSort.sort(array);
        out.println("sorted" + Arrays.toString(array));
        out.println("String[]");
        String[] stringArray = {"z", "Z", "C", "D", "X", "v", "Y", "A", "a",
                                "L", "M"};
        out.println("unsorted" + Arrays.toString(stringArray));
        MergeSort.sort(stringArray);
        out.println("sorted" + Arrays.toString(stringArray));
    }
}
