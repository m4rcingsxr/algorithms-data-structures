package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class implements the Selection Sort algorithm for sorting arrays.
 * <p>
 * Selection Sort is an in-place comparison sorting algorithm that divides the array
 * into a sorted and an unsorted region. It repeatedly selects the smallest (or largest)
 * element from the unsorted region and moves it to the end of the sorted region.
 * </p>
 *
 * <p><b>Time Complexity:</b></p>
 * <ul>
 *     <li><b>Best Case:</b> O(n^2) - Selection Sort always performs O(n^2) comparisons.</li>
 *     <li><b>Average Case:</b> O(n^2) - The number of comparisons remains O(n^2) regardless of the initial order.</li>
 *     <li><b>Worst Case:</b> O(n^2) - Selection Sort performs O(n^2) swaps in the worst case.</li>
 * </ul>
 *
 * <p><b>Space Complexity:</b> O(1) - Selection Sort is an in-place sorting algorithm that requires only a constant amount of extra space.</p>
 *
 * <p><b>Stability:</b> Selection Sort is not a stable sorting algorithm, meaning it may change the relative order of equal elements.</p>
 *
 * @param <T> the type of elements in the array
 */
public class SelectionSort {

    // Private constructor to prevent instantiation
    private SelectionSort() {}

    /**
     * Sorts the specified array using the Selection Sort algorithm.
     *
     * @param arr        the array to be sorted
     * @param comparator the comparator to determine the order of the elements
     * @param <T>        the type of elements in the array
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1) (in-place sorting)
     */
    public static<T> void sort(T[] arr, Comparator<T> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i; // Assume the minimum is the first element
            for (int j = i + 1; j < arr.length; j++) {
                // Find the index of the minimum element in the remaining unsorted part
                if(comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            // Swap the found minimum element with the first element of the unsorted part
            swap(arr, i, min);
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
    private static<T> void swap(T[] arr, int i, int j) {
        T temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static void main(String[] args) {
        Integer[] intArray = {5, 3, 8, 1, 2, 7};
        System.out.println("Before sorting (integers): " + Arrays.toString(intArray));
        SelectionSort.sort(intArray, Comparator.naturalOrder());
        System.out.println("After sorting (integers): " + Arrays.toString(intArray));
        String[] strArray = {"apple", "orange", "banana", "pear", "grape"};
        System.out.println("Before sorting (strings): " + Arrays.toString(strArray));
        SelectionSort.sort(strArray, Comparator.naturalOrder());
        System.out.println("After sorting (strings): " + Arrays.toString(strArray));
    }

}
