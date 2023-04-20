package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Implements the SelectionSort algorithm to sort an array of elements in
 * ascending/descending order.
 * SelectionSort works by repeatedly finding the minimum element in the
 * unsorted part of the array,
 * swapping it with the first unsorted element, and then advancing the
 * boundary between the sorted
 * and unsorted parts of the array.
 */
public class SelectionSort {
    private SelectionSort() {
        // Utility class
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        int N = array.length;
        for (int i = 0; i < N - 1; i++) {
            T min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (array[j].compareTo(min) < 0) {
                    min = array[j];
                    minIndex = j;
                }
            }
            array[minIndex] = array[i];
            array[i] = min;
        }
    }

    public static <T extends Comparable<T>> void descendingSort(T[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        int N = array.length;
        for (int i = 0; i < N - 1; i++) {
            T max = array[i];
            int maxIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (array[j].compareTo(max) > 0) {
                    max = array[j];
                    maxIndex = j;
                }
            }
            array[maxIndex] = array[i];
            array[i] = max;
        }
    }

    public static void main(String[] args) {
        out.println("-----------SELECTION SORT------------");
        out.println("Integer[]");
        Integer[] array = {2, 8, 1, 3, 6, 7, 5, 4};
        out.println(Arrays.toString(array) + "\nAscending:");
        SelectionSort.sort(array);
        out.println(Arrays.toString(array) + "\nDescending:");
        SelectionSort.descendingSort(array);
        out.println(Arrays.toString(array) + "\nString[]");
        String[] stringArray = {"z", "Z", "C", "D", "X", "v", "Y", "A", "a",
                "L", "M"};
        out.println(Arrays.toString(stringArray) + "\nAscending");
        SelectionSort.sort(stringArray);
        out.println(Arrays.toString(stringArray) + "\nDescending");
        SelectionSort.descendingSort(stringArray);
        out.print(Arrays.toString(stringArray));
    }
}
