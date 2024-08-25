package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {

    private final HeapSort heapSort = new HeapSort();

    @Test
    void givenAlreadySortedArray_whenSortingAscending_thenArrayShouldRemainSorted() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        heapSort.sort(sortedArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, sortedArray, "The sorted array should remain unchanged.");
    }

    @Test
    void givenAlreadySortedArray_whenSortingDescending_thenArrayShouldBeReversed() {
        int[] sortedArray = {1, 2, 3, 4, 5};
        heapSort.sort(sortedArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, sortedArray, "The sorted array should be reversed.");
    }

    @Test
    void givenReverseSortedArray_whenSortingAscending_thenArrayShouldBeSortedInAscendingOrder() {
        int[] reverseSortedArray = {5, 4, 3, 2, 1};
        heapSort.sort(reverseSortedArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, reverseSortedArray, "The array should be sorted in ascending order.");
    }

    @Test
    void givenReverseSortedArray_whenSortingDescending_thenArrayShouldRemainSorted() {
        int[] reverseSortedArray = {5, 4, 3, 2, 1};
        heapSort.sort(reverseSortedArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{5, 4, 3, 2, 1}, reverseSortedArray, "The reverse sorted array should remain unchanged.");
    }

    @Test
    void givenArrayWithDuplicateValues_whenSortingAscending_thenArrayShouldBeSortedWithDuplicatesPreserved() {
        int[] arrayWithDuplicates = {4, 1, 3, 2, 4, 5, 3};
        heapSort.sort(arrayWithDuplicates, Comparator.naturalOrder());
        assertArrayEquals(new int[]{1, 2, 3, 3, 4, 4, 5}, arrayWithDuplicates, "The array should be sorted, preserving duplicate values.");
    }

    @Test
    void givenArrayWithDuplicateValues_whenSortingDescending_thenArrayShouldBeSortedWithDuplicatesPreserved() {
        int[] arrayWithDuplicates = {4, 1, 3, 2, 4, 5, 3};
        heapSort.sort(arrayWithDuplicates, Comparator.reverseOrder());
        assertArrayEquals(new int[]{5, 4, 4, 3, 3, 2, 1}, arrayWithDuplicates, "The array should be sorted in descending order, preserving duplicate values.");
    }

    @Test
    void givenEmptyArray_whenSortingAscending_thenArrayShouldRemainEmpty() {
        int[] emptyArray = {};
        heapSort.sort(emptyArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{}, emptyArray, "The empty array should remain unchanged.");
    }

    @Test
    void givenEmptyArray_whenSortingDescending_thenArrayShouldRemainEmpty() {
        int[] emptyArray = {};
        heapSort.sort(emptyArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{}, emptyArray, "The empty array should remain unchanged.");
    }

    @Test
    void givenSingleElementArray_whenSortingAscending_thenArrayShouldRemainUnchanged() {
        int[] singleElementArray = {1};
        heapSort.sort(singleElementArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{1}, singleElementArray, "The single-element array should remain unchanged.");
    }

    @Test
    void givenSingleElementArray_whenSortingDescending_thenArrayShouldRemainUnchanged() {
        int[] singleElementArray = {1};
        heapSort.sort(singleElementArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{1}, singleElementArray, "The single-element array should remain unchanged.");
    }

    @Test
    void givenLargeArray_whenSortingAscending_thenArrayShouldBeSortedCorrectly() {
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = largeArray.length - i;
        }
        heapSort.sort(largeArray, Comparator.naturalOrder());

        int[] expectedArray = new int[1000];
        for (int i = 0; i < expectedArray.length; i++) {
            expectedArray[i] = i + 1;
        }
        assertArrayEquals(expectedArray, largeArray, "The large array should be sorted correctly.");
    }

    @Test
    void givenLargeArray_whenSortingDescending_thenArrayShouldBeSortedCorrectly() {
        int[] largeArray = new int[1000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1;
        }
        heapSort.sort(largeArray, Comparator.reverseOrder());

        int[] expectedArray = new int[1000];
        for (int i = 0; i < expectedArray.length; i++) {
            expectedArray[i] = 1000 - i;
        }
        assertArrayEquals(expectedArray, largeArray, "The large array should be sorted in descending order.");
    }

    @Test
    void givenArrayWithNegativeNumbers_whenSortingAscending_thenArrayShouldBeSortedInAscendingOrder() {
        int[] arrayWithNegatives = {-5, -1, -3, -2, -4, 0, 2, 1};
        heapSort.sort(arrayWithNegatives, Comparator.naturalOrder());
        assertArrayEquals(new int[]{-5, -4, -3, -2, -1, 0, 1, 2}, arrayWithNegatives, "The array with negative numbers should be sorted in ascending order.");
    }

    @Test
    void givenArrayWithNegativeNumbers_whenSortingDescending_thenArrayShouldBeSortedInDescendingOrder() {
        int[] arrayWithNegatives = {-5, -1, -3, -2, -4, 0, 2, 1};
        heapSort.sort(arrayWithNegatives, Comparator.reverseOrder());
        assertArrayEquals(new int[]{2, 1, 0, -1, -2, -3, -4, -5}, arrayWithNegatives, "The array with negative numbers should be sorted in descending order.");
    }

    @Test
    void givenArrayWithAllSameValues_whenSortingAscending_thenArrayShouldRemainUnchanged() {
        int[] sameValuesArray = {7, 7, 7, 7, 7};
        heapSort.sort(sameValuesArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, sameValuesArray, "The array with all same values should remain unchanged.");
    }

    @Test
    void givenArrayWithAllSameValues_whenSortingDescending_thenArrayShouldRemainUnchanged() {
        int[] sameValuesArray = {7, 7, 7, 7, 7};
        heapSort.sort(sameValuesArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, sameValuesArray, "The array with all same values should remain unchanged.");
    }

    @Test
    void givenArrayWithMinAndMaxIntegers_whenSortingAscending_thenArrayShouldBeSortedCorrectly() {
        int[] extremeValuesArray = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        heapSort.sort(extremeValuesArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, extremeValuesArray, "The array with min and max integer values should be sorted correctly.");
    }

    @Test
    void givenArrayWithMinAndMaxIntegers_whenSortingDescending_thenArrayShouldBeSortedCorrectly() {
        int[] extremeValuesArray = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        heapSort.sort(extremeValuesArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{Integer.MAX_VALUE, 1, 0, -1, Integer.MIN_VALUE}, extremeValuesArray, "The array with min and max integer values should be sorted in descending order.");
    }

    @Test
    void givenArrayWithZerosAndOnes_whenSortingAscending_thenArrayShouldBeSortedCorrectly() {
        int[] zerosAndOnesArray = {0, 1, 0, 1, 1, 0, 1, 0};
        heapSort.sort(zerosAndOnesArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{0, 0, 0, 0, 1, 1, 1, 1}, zerosAndOnesArray, "The array with zeros and ones should be sorted correctly.");
    }

    @Test
    void givenArrayWithZerosAndOnes_whenSortingDescending_thenArrayShouldBeSortedCorrectly() {
        int[] zerosAndOnesArray = {0, 1, 0, 1, 1, 0, 1, 0};
        heapSort.sort(zerosAndOnesArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{1, 1, 1, 1, 0, 0, 0, 0}, zerosAndOnesArray, "The array with zeros and ones should be sorted in descending order.");
    }

    @Test
    void givenArrayWithRandomValues_whenSortingAscending_thenArrayShouldBeSortedCorrectly() {
        int[] randomArray = {42, 23, 4, 16, 8, 15, 9};
        heapSort.sort(randomArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{4, 8, 9, 15, 16, 23, 42}, randomArray, "The array with random values should be sorted correctly.");
    }

    @Test
    void givenArrayWithRandomValues_whenSortingDescending_thenArrayShouldBeSortedCorrectly() {
        int[] randomArray = {42, 23, 4, 16, 8, 15, 9};
        heapSort.sort(randomArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{42, 23, 16, 15, 9, 8, 4}, randomArray, "The array with random values should be sorted in descending order.");
    }

    @Test
    void givenArrayWithAlternatingHighAndLowValues_whenSortingAscending_thenArrayShouldBeSortedCorrectly() {
        int[] alternatingArray = {100, -100, 90, -90, 80, -80, 70, -70};
        heapSort.sort(alternatingArray, Comparator.naturalOrder());
        assertArrayEquals(new int[]{-100, -90, -80, -70, 70, 80, 90, 100}, alternatingArray, "The array with alternating high and low values should be sorted correctly.");
    }

    @Test
    void givenArrayWithAlternatingHighAndLowValues_whenSortingDescending_thenArrayShouldBeSortedCorrectly() {
        int[] alternatingArray = {100, -100, 90, -90, 80, -80, 70, -70};
        heapSort.sort(alternatingArray, Comparator.reverseOrder());
        assertArrayEquals(new int[]{100, 90, 80, 70, -70, -80, -90, -100}, alternatingArray, "The array with alternating high and low values should be sorted in descending order.");
    }
}
