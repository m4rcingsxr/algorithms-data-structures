package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

/**
 * Implementation of the QuickSort algorithm.
 *
 * <p>QuickSort is an efficient, in-place sorting algorithm that uses a divide-and-conquer approach
 * to sort elements in an array. The algorithm selects a 'pivot' element from the array and partitions
 * the other elements into two sub-arrays according to whether they are less than or greater than the pivot.
 * The sub-arrays are then sorted recursively.
 *
 * <p>Note: The implementation here uses the Lomuto partition scheme.
 */
public class QuickSort {

    private QuickSort() {}

    /**
     * Sorts the array using the QuickSort algorithm.
     *
     * @param arr   The array to be sorted.
     * @param left  The starting index of the array/sub-array to be sorted.
     * @param right The ending index of the array/sub-array to be sorted.
     */
    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            // Partition the array around a pivot element and get the pivot index.
            int pivot = partition(arr, left, right);
            // Recursively sort the elements before the pivot.
            sort(arr, left, pivot - 1);
            // Recursively sort the elements after the pivot.
            sort(arr, pivot + 1, right);
        }
    }

    /**
     * Partitions the array around a pivot element.
     *
     * <p>The pivot is chosen to be the last element in the array (arr[right]). The partitioning process
     * rearranges the array such that all elements less than the pivot are on the left of the pivot and all
     * elements greater than the pivot are on the right.
     *
     * @param arr   The array to be partitioned.
     * @param left  The starting index of the partitioning process.
     * @param right The ending index of the partitioning process.
     * @return The index of the pivot element after partitioning.
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right]; // Pivot element is the last element
        int i = left - 1; // Index of the smaller element

        // Iterate over the array and rearrange elements based on pivot
        for (int j = left; j < right; j++) {
            // If current element is smaller than or equal to pivot, swap it with the element at i
            if (arr[j] < pivot) {
                i++; // Increment index of smaller element
                swap(arr, i, j); // Swap the elements at indices i and j
            }
        }

        // Swap the pivot element with the element at index i + 1
        swap(arr, i + 1, right);
        return (i + 1); // Return the pivot index
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr The array containing the elements to swap.
     * @param i   The index of the first element.
     * @param j   The index of the second element.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] intPrimitiveArray = {5, 3, 8, 1, 2, 7};
        System.out.println("Before sorting (integers): " + Arrays.toString(intPrimitiveArray));
        QuickSort.sort(intPrimitiveArray, 0, intPrimitiveArray.length - 1);
        System.out.println("After sorting (integers): " + Arrays.toString(intPrimitiveArray));

        // Test with an array of strings
        String[] strArray = {"apple", "orange", "banana", "pear", "grape"};

        // Print the array before sorting
        System.out.println("Before sorting (strings): " + Arrays.toString(strArray));

        // Convert String array to array of ints for QuickSort (using length for sorting)
        int[] strLengths = Arrays.stream(strArray).mapToInt(String::length).toArray();

        // Sort the lengths using QuickSort
        QuickSort.sort(strLengths, 0, strLengths.length - 1);

        // Create a sorted array based on lengths
        String[] sortedStrArray = Arrays.stream(strLengths)
                .mapToObj(length -> Arrays.stream(strArray)
                        .filter(s -> s.length() == length)
                        .findFirst()
                        .orElse(""))
                .toArray(String[]::new);

        // Print the sorted array
        System.out.println("After sorting (strings by length): " + Arrays.toString(sortedStrArray));
    }

}
