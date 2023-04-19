package com.marcinseweryn.algorithms.sorting;


import com.marcinseweryn.algorithms.datastructures.tree.binary.BinaryHeap;

import java.util.Arrays;

import static java.lang.System.out;

/**
 * The HeapSort class provides a static method for sorting an array
 * of comparable elements using the heap sort algorithm.
 */
public class HeapSort {
    private HeapSort() {
        // Utility class
    }

    /**
     * Sorts the given array using the heap sort algorithm.
     *
     * @param array the array to be sorted
     * @param <T>   the type of elements in the array, must implement the Comparable interface
     */
    public static <T extends Comparable<T>> void sort(Object[] array) {

        // Create a binary heap of minimum values
        BinaryHeap<T> bh = new BinaryHeap<>(array.length);
        for (Object o : array) {
            bh.add((T) o, BinaryHeap.MIN_HEAP);
        }

        // Extract the minimum value from the heap and add it to the sorted array
        for (int i = 0; i < array.length; i++) {
            array[i] = bh.extractHead(BinaryHeap.MIN_HEAP);
        }
    }

    public static void main(String[] args) {
        out.println("-----------HEAP SORT------------");
        out.println("Integer[]");
        Integer[] array = {2, 8, 1, 3, 6, 7, 5, 4};
        out.println("unsorted" + Arrays.toString(array));
        HeapSort.sort(array);
        out.println("sorted" + Arrays.toString(array));
        out.println("String[]");
        String[] stringArray = {"z", "Z", "C", "D", "X", "v", "Y", "A", "a", "L", "M"};
        out.println("unsorted" + Arrays.toString(stringArray));
        HeapSort.sort(stringArray);
        out.println("sorted" + Arrays.toString(stringArray));
    }
}
