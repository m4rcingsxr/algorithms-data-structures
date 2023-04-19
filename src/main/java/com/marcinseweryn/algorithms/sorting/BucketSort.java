package com.marcinseweryn.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

/**
 * The BucketSort class provides a static method to sort an array of
 * integers using the bucket sort algorithm.
 */
public class BucketSort {
    private BucketSort() {
        // No instantiate
    }

    /**
     * Sorts an array of integers using the bucket sort algorithm.
     *
     * @param array     the array to be sorted.
     * @param noBuckets the number of buckets to use for the sorting.
     * @throws NullPointerException if the input array is null.
     */
    public static void sort(int[] array, int noBuckets) {
        if (array == null) {
            throw new NullPointerException();
        }

        // Create an array of buckets
        List<Integer>[] buckets = new List[noBuckets];

        // Get the maximum value in the array
        double max = getMax(array);

        // Initialize each bucket as an empty list
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribute each element of the array into its corresponding bucket
        for (int i = 0; i < array.length; i++) {
            int index = hash(array[i], noBuckets, max);
            buckets[index].add(array[i]);
        }

        // Sort each bucket individually
        for (int i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]);
        }

        // Concatenate all the buckets back into the original array
        int x = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                array[x++] = buckets[i].get(j);
            }
        }
    }

    /**
     * Returns the maximum value in an array of integers.
     *
     * @param array the input array.
     * @return the maximum value in the input array.
     */
    private static double getMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    /**
     * Computes the bucket index for a given value.
     *
     * @param value     the value to be hashed.
     * @param noBuckets the number of buckets to use for the hashing.
     * @param max       the maximum value in the input array.
     * @return the bucket index for the input value.
     */
    private static int hash(int value, int noBuckets, double max) {
        int divider = (int) Math.ceil((max + 1) / noBuckets);
        return (int) Math.floor((double) value / divider);
    }

    public static void main(String[] args) {
        int[] array = {2, 8, 1, 3, 6, 13, 7, 5, 4, 0, 11, 17};
        out.println("Bucket sort:");
        out.println("Unsorted array " + Arrays.toString(array));
        BucketSort.sort(array, 10);
        out.print("Sorted array " + Arrays.toString(array));
    }
}
