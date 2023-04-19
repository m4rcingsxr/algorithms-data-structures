package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * Implements the BubbleSort algorithm to sort an array of elements in ascending/descending order.
 * BubbleSort works by repeatedly swapping adjacent elements in the array if they are in the wrong order.
 * The algorithm continues until no more swaps are needed, indicating the array is sorted.
 */
public class BubbleSort {
    private BubbleSort() {
        // Utility class
    }

    public static <T extends Comparable<T>> void sort(T[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        int N = array.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static <T extends Comparable<T>> void descendingSort(T[] array) {
        if(array == null) {
            throw new NullPointerException();
        }
        int N = array.length;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) < 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        out.println("-----------BUBBLE SORT------------");
        out.println("Integer[]");
        Integer[] array = {2, 8, 1, 3, 6, 7, 5, 4};
        out.println(Arrays.toString(array) + "\nAscending:");
        BubbleSort.sort(array);
        out.println(Arrays.toString(array) + "\nDescending:");
        BubbleSort.descendingSort(array);
        out.println(Arrays.toString(array) + "\nString[]");
        String[] stringArray = {"z", "Z", "C", "D", "X", "v", "Y", "A", "a", "L", "M"};
        out.println(Arrays.toString(stringArray) + "\nAscending");
        BubbleSort.sort(stringArray);
        out.println(Arrays.toString(stringArray) + "\nDescending");
        BubbleSort.descendingSort(stringArray);
        out.print(Arrays.toString(stringArray));
    }

}
