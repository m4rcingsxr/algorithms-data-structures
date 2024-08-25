package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;
import java.util.Comparator;


/**
 * This class implements the Bubble Sort algorithm for sorting arrays.
 * Bubble Sort is a simple comparison-based sorting algorithm that repeatedly steps through
 * the list, compares adjacent elements, and swaps them if they are in the wrong order.
 * The pass through the list is repeated until the list is sorted.
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best case:</b> O(n) when the array is already sorted.</li>
 *     <li><b>Average case:</b> O(n<sup>2</sup>) due to the nested loops comparing and swapping elements.</li>
 *     <li><b>Worst case:</b> O(n<sup>2</sup>) when the array is sorted in reverse order.</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b> O(1), since Bubble Sort is an in-place sorting algorithm.</p>
 *
 * <p><b>Stability:</b> Bubble Sort is a stable sorting algorithm. This means that two equal elements
 * will retain their relative order after sorting. This is because Bubble Sort only swaps
 * elements if they are in the wrong order, which preserves the original order of equal elements.</p>
 */
public class BubbleSort {

    // Private constructor to prevent instantiation
    private BubbleSort() {
    }

    /**
     * Sorts the specified array using the Bubble Sort algorithm with a custom comparator.
     * This version of Bubble Sort performs a fixed number of passes through the array.
     *
     * @param arr        the array to be sorted
     * @param comparator the comparator defining the order of the elements
     * @param <T>        the type of elements in the array
     *
     * Time Complexity: O(n^2) in the average and worst case; O(n) in the best case when the
     *           array is already sorted.
     * Space Complexity: O(1) (in-place sorting)
     */
    public static <T> void sort(T[] arr, Comparator<T> comparator) {
        if (arr == null) return;

        // Perform bubble sort
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                // Compare adjacent elements and swap if out of order
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * Swaps two elements in an array.
     *
     * @param arr the array in which elements are to be swapped
     * @param i   the index of the first element to swap
     * @param j   the index of the second element to swap
     * @param <T> the type of elements in the array
     */
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        // Example usage with integers
        Integer[] intArray = {5, 3, 8, 1, 2, 7};
        System.out.println("Before sorting: " + Arrays.toString(intArray));
        BubbleSort.sort(intArray, Comparator.naturalOrder());
        System.out.println("After sorting: " + Arrays.toString(intArray));

        // Example usage with strings
        String[] strArray = {"banana", "apple", "cherry", "date"};
        System.out.println("Before sorting: " + Arrays.toString(strArray));
        BubbleSort.sort(strArray, Comparator.naturalOrder());
        System.out.println("After sorting: " + Arrays.toString(strArray));
    }
}
