package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void givenEmptyArray_whenSortCalled_thenArrayRemainsEmpty() {
        Integer[] array = {};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{}, array, "Empty array should remain unchanged.");
    }

    @Test
    void givenSingleElementArray_whenSortCalled_thenArrayRemainsUnchanged() {
        Integer[] array = {42};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{42}, array, "Array with single element should remain unchanged.");
    }

    @Test
    void givenAlreadySortedArray_whenSortCalled_thenArrayRemainsSorted() {
        Integer[] array = {1, 2, 3, 4, 5};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array, "Already sorted array should remain unchanged.");
    }

    @Test
    void givenReverseSortedArray_whenSortCalled_thenArrayIsSortedAscending() {
        Integer[] array = {5, 4, 3, 2, 1};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, array, "Reverse sorted array should be sorted in ascending order.");
    }

    @Test
    void givenArrayWithDuplicates_whenSortCalled_thenArrayIsSortedAndDuplicatesHandled() {
        Integer[] array = {3, 1, 2, 3, 2};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{1, 2, 2, 3, 3}, array, "Array with duplicates should be sorted correctly.");
    }

    @Test
    void givenArrayWithNegativeNumbers_whenSortCalled_thenArrayIsSortedCorrectly() {
        Integer[] array = {3, -1, -4, 2, 0};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{-4, -1, 0, 2, 3}, array, "Array with negative numbers should be sorted correctly.");
    }

    @Test
    void givenArrayWithCustomComparator_whenSortCalled_thenArrayIsSortedAccordingToComparator() {
        String[] array = {"apple", "banana", "pear", "grape"};
        Comparator<String> lengthComparator = Comparator.comparingInt(String::length);
        InsertionSort.sort(array, lengthComparator);
        assertArrayEquals(new String[]{"pear", "apple", "grape", "banana"}, array, "Array should be sorted by string length.");
    }

    @Test
    void givenArrayWithIdenticalElements_whenSortCalled_thenArrayRemainsUnchanged() {
        Integer[] array = {7, 7, 7, 7, 7};
        InsertionSort.sort(array, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{7, 7, 7, 7, 7}, array, "Array with identical elements should remain unchanged.");
    }

    @Test
    void givenArray_whenSortCalledWithDescendingComparator_thenArrayIsSortedDescending() {
        Integer[] array = {1, 2, 3, 4, 5};
        InsertionSort.sort(array, Comparator.reverseOrder());
        assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, array, "Array should be sorted in descending order using reverse order comparator.");
    }
}
