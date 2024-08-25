package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @Test
    void givenAlreadySortedArray_whenSorting_thenArrayShouldRemainSorted() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        QuickSort.sort(sortedArray, 0, sortedArray.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedArray, "The sorted array should remain unchanged.");
    }

    @Test
    void givenReverseSortedArray_whenSorting_thenArrayShouldBeSortedInAscendingOrder() {
        int[] reverseSortedArray = {5, 4, 3, 2, 1};
        QuickSort.sort(reverseSortedArray, 0, reverseSortedArray.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, reverseSortedArray, "The array should be sorted in ascending order.");
    }

    @Test
    void givenArrayWithDuplicateValues_whenSorting_thenArrayShouldBeSortedWithDuplicatesPreserved() {
        int[] arrayWithDuplicates = {4, 1, 3, 2, 4, 5, 3};
        QuickSort.sort(arrayWithDuplicates, 0, arrayWithDuplicates.length - 1);
        assertArrayEquals(new int[]{1, 2, 3, 3, 4, 4, 5}, arrayWithDuplicates, "The array should be sorted, preserving duplicate values.");
    }

    @Test
    void givenEmptyArray_whenSorting_thenArrayShouldRemainEmpty() {
        int[] emptyArray = {};
        QuickSort.sort(emptyArray, 0, emptyArray.length - 1);
        assertArrayEquals(new int[]{}, emptyArray, "The empty array should remain unchanged.");
    }

    @Test
    void givenSingleElementArray_whenSorting_thenArrayShouldRemainUnchanged() {
        int[] singleElementArray = {1};
        QuickSort.sort(singleElementArray, 0, singleElementArray.length - 1);
        assertArrayEquals(new int[]{1}, singleElementArray, "The single-element array should remain unchanged.");
    }

    @Test
    void givenArrayWithNegativeNumbers_whenSorting_thenArrayShouldBeSortedInAscendingOrder() {
        int[] arrayWithNegatives = {-5, -1, -3, -2, -4, 0, 2, 1};
        QuickSort.sort(arrayWithNegatives, 0, arrayWithNegatives.length - 1);
        assertArrayEquals(new int[]{-5, -4, -3, -2, -1, 0, 1, 2}, arrayWithNegatives, "The array with negative numbers should be sorted in ascending order.");
    }

    @Test
    void givenArrayWithAllSameValues_whenSorting_thenArrayShouldRemainUnchanged() {
        int[] sameValuesArray = {7, 7, 7, 7, 7};
        QuickSort.sort(sameValuesArray, 0, sameValuesArray.length - 1);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, sameValuesArray, "The array with all same values should remain unchanged.");
    }

    @Test
    void givenArrayWithMinAndMaxIntegers_whenSorting_thenArrayShouldBeSortedCorrectly() {
        int[] extremeValuesArray = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        QuickSort.sort(extremeValuesArray, 0, extremeValuesArray.length - 1);
        assertArrayEquals(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, extremeValuesArray, "The array with min and max integer values should be sorted correctly.");
    }

    @Test
    void givenArrayWithZerosAndOnes_whenSorting_thenArrayShouldBeSortedCorrectly() {
        int[] zerosAndOnesArray = {0, 1, 0, 1, 1, 0, 1, 0};
        QuickSort.sort(zerosAndOnesArray, 0, zerosAndOnesArray.length - 1);
        assertArrayEquals(new int[]{0, 0, 0, 0, 1, 1, 1, 1}, zerosAndOnesArray, "The array with zeros and ones should be sorted correctly.");
    }

    @Test
    void givenArrayWithAlternatingHighAndLowValues_whenSorting_thenArrayShouldBeSortedCorrectly() {
        int[] alternatingArray = {100, -100, 90, -90, 80, -80, 70, -70};
        QuickSort.sort(alternatingArray, 0, alternatingArray.length - 1);
        assertArrayEquals(new int[]{-100, -90, -80, -70, 70, 80, 90, 100}, alternatingArray, "The array with alternating high and low values should be sorted correctly.");
    }

    @Test
    void givenLargeArray_whenSorting_thenArrayShouldBeSortedCorrectly() {
        int size = 10000;
        int[] veryLargeArray = new int[size];
        for (int i = 0; i < size; i++) {
            veryLargeArray[i] = size - i;
        }
        QuickSort.sort(veryLargeArray, 0, veryLargeArray.length - 1);

        int[] expectedArray = new int[size];
        for (int i = 0; i < size; i++) {
            expectedArray[i] = i + 1;
        }
        assertArrayEquals(expectedArray, veryLargeArray, "The very large array should be sorted correctly.");
    }
}