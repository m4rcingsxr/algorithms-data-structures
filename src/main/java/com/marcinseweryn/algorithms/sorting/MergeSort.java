package com.marcinseweryn.algorithms.sorting;

import java.util.Arrays;

import static java.lang.System.out;

public class MergeSort {
    private MergeSort() {
        // No instantiate
    }
    public static <T extends Comparable<T>> void sort(Object[] array) {
        int arrayLength = array.length;

        // Base case
        if (arrayLength < 2) return;
        int mid = arrayLength / 2;
        Object[] left = new Object[mid];
        Object[] right = new Object[arrayLength - mid];
        for (int i = 0; i < mid; i++) {
            left[i] =  array[i];
        }
        for (int i = mid; i < arrayLength; i++) {
            right[i - mid] =  array[i];
        }
        sort(left);
        sort(right);
        merge(left, right, array);
    }

    private static <T extends Comparable<T>> void merge(Object[] left, Object[] right, Object[] array) {
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (((T) left[i]).compareTo((T) right[j]) < 0) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
        }

        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        out.println("-----------MERGE SORT------------");
        out.println("Integer[]");
        Integer[] array = {2, 8, 1, 3, 6, 7, 5, 4};
        out.println("unsorted" + Arrays.toString(array));
        MergeSort.sort(array);
        out.println("sorted" + Arrays.toString(array));
        out.println("String[]");
        String[] stringArray = {"z", "Z", "C", "D", "X", "v", "Y", "A", "a", "L", "M"};
        out.println("unsorted" + Arrays.toString(stringArray));
        MergeSort.sort(stringArray);
        out.println("sorted" + Arrays.toString(stringArray));
    }
}
