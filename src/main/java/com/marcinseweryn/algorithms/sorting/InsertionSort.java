package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Implements the InsertionSort algorithm to sort an array of elements in ascending/descending order.
 * InsertionSort works by iterating over the array and inserting each element into its correct
 * position in a sorted sub-array to its left.
 */
public class InsertionSort {
    private InsertionSort() {
        // No instantiate
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < array.length; i++) {
            T temp = array[i];
            int j = i;
            while (j > 0 && temp.compareTo(array[j - 1]) < 0) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public static <T extends Comparable<T>> void descendingSort(T[] array) {
        if (array == null) {
            throw new NullPointerException();
        }
        for (int i = 0; i < array.length; i++) {
            T temp = array[i];
            int j = i;
            while (j > 0 && temp.compareTo(array[j - 1]) > 0) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        out.println("-----------INSERTION SORT------------");
        out.println("Integer[]");
        Integer[] array = {2, 8, 1, 3, 6, 7, 5, 4};
        out.println(Arrays.toString(array) + "\nAscending:");
        BubbleSort.sort(array);
        out.println(Arrays.toString(array) + "\nDescending:");
        BubbleSort.descendingSort(array);
        out.println(Arrays.toString(array) + "\nString");
        String[] stringArray = {"z", "Z", "C", "D", "X", "v", "Y", "A", "a", "L", "M"};
        out.println(Arrays.toString(stringArray) + "\nAscending");
        BubbleSort.sort(stringArray);
        out.println(Arrays.toString(stringArray) + "\nDescending");
        BubbleSort.descendingSort(stringArray);
        out.print(Arrays.toString(stringArray));
    }
}
