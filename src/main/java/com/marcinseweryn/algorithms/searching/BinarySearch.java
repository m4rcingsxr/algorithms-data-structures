package com.marcinseweryn.algorithms.searching;

import java.util.Arrays;
import java.util.Random;

import static java.lang.System.out;

/**
 * Binary search compares the target value to the middle element of the array. If they are not equal,
 * the half in which the target cannot lie is eliminated and the search continues on the remaining half,
 * again taking the middle element to compare to the target value, and repeating this until the target value
 * is found. If the search ends with the remaining half being empty, the target is not in the array.
 *
 * @https://en.wikipedia.org/wiki/Binary_search_algorithm
 */
public class BinarySearch {
    private BinarySearch() {
        // No instantiate
    }

    /**
     * Performs binary search on the given array to find the specified value.
     *
     * @param arr   the sorted array to search in
     * @param value the value to search for
     * @param <T>   the type of elements in the array, must implement Comparable interface
     * @return true if the value is found, false otherwise
     */
    public static <T extends Comparable<T>> boolean search(T[] arr, T value) {
        int start = 0;
        int end = arr.length - 1;
        int mid = arr.length / 2;
        while (start <= end && arr[mid] != value) {
            if (value.compareTo(arr[mid]) < 0) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            mid = (start + end) / 2;
        }
        return arr[mid] == value;
    }

    public static void main(String[] args) {
        Integer[] randomUnsortedArray = new Integer[10];
        Random random = new Random();
        for (int i = 0; i < randomUnsortedArray.length; i++) {
            randomUnsortedArray[i] = random.nextInt(100);
        }
        Integer[] randomSortedArray = Arrays.copyOf(randomUnsortedArray, randomUnsortedArray.length);
        Arrays.sort(randomSortedArray);
        out.println("Unsorted array: " + Arrays.toString(randomUnsortedArray));
        out.println("Sorted array: " + Arrays.toString(randomSortedArray));
        for (int i = 0; i < 10; i++) {
            if (!BinarySearch.search(randomSortedArray, randomUnsortedArray[i])) {
                out.println("Binary Search algorithm does not found " + randomUnsortedArray[i]);
                break;
            } else {
                out.println(i + ": BinarySearch.search(" + randomUnsortedArray[i] + ") : true");
            }
        }
    }
}
