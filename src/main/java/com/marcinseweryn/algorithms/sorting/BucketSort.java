package com.marcinseweryn.algorithms.sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.System.out;

public class BucketSort {
    private BucketSort() {
        // No instantiate
    }

    public static void sort(int[] array, int noBuckets) {
        if (array == null) {
            throw new NullPointerException();
        }
        List<Integer>[] buckets = new List[noBuckets];
        double max = getMax(array);

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int i = 0; i < array.length; i++) {
            int index = hash(array[i], noBuckets, max);
            buckets[index].add(array[i]);
        }

        for (int i = 0; i < buckets.length; i++) {
            Collections.sort(buckets[i]);
        }

        int x = 0;
        for (int i = 0; i < buckets.length; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                array[x++] = buckets[i].get(j);
            }
        }
    }

    private static double getMax(int[] array) {
        int max = Integer.MIN_VALUE;
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

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
