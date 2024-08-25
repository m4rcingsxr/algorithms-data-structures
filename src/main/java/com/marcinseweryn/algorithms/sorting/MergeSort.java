package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class implements the Merge Sort algorithm for sorting arrays.
 * <p>
 * Merge Sort is a divide-and-conquer algorithm that recursively divides
 * the array into halves, sorts each half, and merges them back together.
 * </p>
 * <p><b>Time Complexity:</b> O(n log n) for all cases (best, average, and worst) because the array is always split in half and requires linear time to merge.</p>
 * <p><b>Space Complexity:</b> O(n) due to the temporary arrays used for merging.</p>
 */
public class MergeSort {

    // Private constructor to prevent instantiation
    private MergeSort() {
    }

    /**
     * Sorts the specified array using the Merge Sort algorithm.
     *
     * @param arr        the array to be sorted
     * @param comparator the comparator to determine the order of the elements
     * @param start      the starting index of the array to be sorted
     * @param end        the ending index of the array to be sorted
     * @param <T>        the type of elements in the array
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(n) due to the additional arrays used for merging
     */
    public static <T> void sort(T[] arr, Comparator<T> comparator, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            sort(arr, comparator, start, mid);       // Sort the first half
            sort(arr, comparator, mid + 1, end);     // Sort the second half
            merge(arr, comparator, start, mid, end); // Merge the sorted halves
        }
    }

    /**
     * Merges two sorted subarrays into a single sorted subarray.
     * The first subarray is arr[left...mid]
     * The second subarray is arr[mid+1...right]
     *
     * @param arr        the array to merge
     * @param comparator the comparator to determine the order of the elements
     * @param left       the starting index of the first subarray
     * @param mid        the ending index of the first subarray
     * @param right      the ending index of the second subarray
     * @param <T>        the type of elements in the array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n) for the temporary arrays used during the merge process
     */
    private static <T> void merge(T[] arr, Comparator<T> comparator, int left, int mid, int right) {
        int length1 = mid - left + 1;
        int length2 = right - mid;

        // Create temporary arrays for left and right subarrays
        T[] leftArr = (T[]) new Object[length1];
        T[] rightArr = (T[]) new Object[length2];

        // Copy data to temporary arrays
        for (int i = 0; i < length1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int i = 0; i < length2; i++) {
            rightArr[i] = arr[mid + 1 + i];
        }

        int i = 0, j = 0, k = left;

        // Merge the temporary arrays back into the original array
        while (i < length1 && j < length2) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements of leftArr, if any
        while (i < length1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }

        // Copy any remaining elements of rightArr, if any
        while (j < length2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        // Create a sample array of integers
        Integer[] array = {5, 3, 8, 1, 2, 7};

        // Print the array before sorting
        System.out.println("Before sorting: " + Arrays.toString(array));

        // Sort the array in ascending order
        MergeSort.sort(array, Comparator.naturalOrder(), 0, array.length - 1);
        System.out.println("After ascending sort: " + Arrays.toString(array));

        // Reset the array to its original state
        array = new Integer[]{5, 3, 8, 1, 2, 7};

        // Print the array before sorting in descending order
        System.out.println("Before sorting (descending): " + Arrays.toString(array));

        // Sort the array in descending order
        MergeSort.sort(array, Comparator.reverseOrder(), 0, array.length - 1);
        System.out.println("After descending sort: " + Arrays.toString(array));
    }

}
