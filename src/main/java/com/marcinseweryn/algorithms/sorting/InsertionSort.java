package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Implements the Insertion Sort algorithm to sort an array of elements in ascending or descending order.
 * <p>
 * Insertion Sort works by iterating over the array and inserting each element into its correct position
 * in a sorted sub-array to its left. It is particularly efficient for small data sets or nearly sorted arrays.
 * </p>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(n) when the array is already sorted (only one comparison per element).</li>
 *     <li><b>Average Case:</b> O(n<sup>2</sup>) due to the nested loops required for each element in the worst scenario.</li>
 *     <li><b>Worst Case:</b> O(n<sup>2</sup>) when the array is sorted in reverse order.</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b> O(1) because it is an in-place sort (no additional memory is used other than for the swap operation).</p>
 *
 * <p><b>Stability:</b> Insertion Sort is a stable sorting algorithm, meaning that it preserves the relative order of equal elements.</p>
 *
 */
public class InsertionSort {

    // Private constructor to prevent instantiation
    private InsertionSort() {}

    /**
     * Sorts the specified array using the Insertion Sort algorithm with a custom comparator.
     * This method sorts the array in-place in ascending or descending order based on the comparator provided.
     *
     * @param arr        the array to be sorted
     * @param comparator the comparator defining the order of the elements
     * @param <T>        the type of elements in the array
     *
     * Time Complexity:
     * - Best Case: O(n) when the array is already sorted (only one comparison per element).
     * - Worst Case: O(n^2) due to the nested loops.
     *
     * Space Complexity: O(1) because it is an in-place sort (no additional memory is used other than for the swap operation).
     */
    public static<T> void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 1; i < arr.length; i++) {  // Start from the second element
            T key = arr[i];  // The element to be inserted into the sorted portion
            int j = i - 1;  // Start comparing with the element before it

            // Shift elements of the sorted portion that are greater than the key
            while (j >= 0 && comparator.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the key element at the correct position
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        // Create a sample array of integers
        Integer[] array = {5, 3, 8, 1, 2, 7};

        // Print the array before sorting
        System.out.println("Before sorting: " + Arrays.toString(array));

        // Sort the array in ascending order
        InsertionSort.sort(array, Comparator.naturalOrder());
        System.out.println("After ascending sort: " + Arrays.toString(array));

        // Reset the array to its original state
        array = new Integer[]{5, 3, 8, 1, 2, 7};

        // Print the array before sorting in descending order
        System.out.println("Before sorting (descending): " + Arrays.toString(array));

        // Sort the array in descending order
        InsertionSort.sort(array, Comparator.reverseOrder());
        System.out.println("After descending sort: " + Arrays.toString(array));
    }
}
