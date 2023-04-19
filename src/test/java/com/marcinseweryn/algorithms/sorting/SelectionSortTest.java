package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("SelectionSort")
class SelectionSortTest {

    @DisplayName("ascendingSort")
    @ParameterizedTest(name = "{index} => array={0}, sorted={1}")
    @MethodSource()
    void testAscendingSort(Integer[] array, Integer[] sorted) {
        SelectionSort.sort(array);
        assertArrayEquals(sorted, array);
    }

    @DisplayName("descendingSort")
    @ParameterizedTest(name = "{index} => array={0}, sorted={1}")
    @MethodSource
    void testDescendingSort(Integer[] array, Integer[] sorted) {
        SelectionSort.descendingSort(array);
        assertArrayEquals(sorted, array);
    }

    private static Stream<Arguments> testAscendingSort() {
        Integer[] array1 = {2, 8, 1, 3, 6, 7, 5, 4};
        Integer[] sorted1 = {1, 2, 3, 4, 5, 6, 7, 8};

        Integer[] array2 = {2, -8, 1, 0, -6, 7, -5, 4};
        Integer[] sorted2 = {-8, -6, -5, 0, 1, 2, 4, 7};

        Integer[] array3 = {5, 4, 3, 2, 1};
        Integer[] sorted3 = {1, 2, 3, 4, 5};

        Integer[] array4 = {1, 1, 1, 1, 1};
        Integer[] sorted4 = {1, 1, 1, 1, 1};

        Integer[] array5 = {1, 2, 3, 4, 5};
        Integer[] sorted5 = {1, 2, 3, 4, 5};

        return Stream.of(
                Arguments.of(array1, sorted1),
                Arguments.of(array2, sorted2),
                Arguments.of(array3, sorted3),
                Arguments.of(array4, sorted4),
                Arguments.of(array5, sorted5)
        );
    }

    static Stream<Object[]> testDescendingSort() {
        return Stream.of(
                new Object[]{
                        new Integer[]{},
                        new Integer[]{}
                },
                new Object[]{
                        new Integer[]{1},
                        new Integer[]{1}
                },
                new Object[]{
                        new Integer[]{3, 1, 2},
                        new Integer[]{3, 2, 1}
                },
                new Object[]{
                        new Integer[]{6, 4, 7, 5, 3, 1, 2},
                        new Integer[]{7, 6, 5, 4, 3, 2, 1}
                },
                new Object[]{
                        new Integer[]{-5, 2, 0, -3, 10, 5, -1},
                        new Integer[]{10, 5, 2, 0, -1, -3, -5}
                }
        );
    }
}