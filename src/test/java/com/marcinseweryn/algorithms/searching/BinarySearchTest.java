package com.marcinseweryn.algorithms.searching;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("BinarySearch")
class BinarySearchTest {

    @DisplayName("search")
    @ParameterizedTest(name = "{index} => array={0}, value={1}, expected={2}")
    @MethodSource
    void testSearch(Integer[] array, Integer value, boolean expected) {
        assertEquals(expected, BinarySearch.search(array, value));
    }

    private static Stream<Arguments> testSearch() {
        return Stream.of(
                Arguments.of(
                        new Integer[]{1, 2, 3, 4, 5},
                        3,
                        true
                ),
                Arguments.of(
                        new Integer[]{1, 2, 3, 4, 5},
                        6,
                        false
                ),
                Arguments.of(
                        new Integer[]{1, 2, 3, 4, 5},
                        0,
                        false
                ),
                Arguments.of(
                        new Integer[]{10, 20, 30, 40, 50, 60, 70},
                        60,
                        true
                ),
                Arguments.of(
                        generateRandomArray(1000),
                        1000,
                        false
                )
        );
    }

    private static Integer[] generateRandomArray(int size) {
        Integer[] array = new Integer[size];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(size);
        }
        Arrays.sort(array);
        return array;
    }
}