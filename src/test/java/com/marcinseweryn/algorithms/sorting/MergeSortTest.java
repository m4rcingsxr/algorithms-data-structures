package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @ParameterizedTest(name = "[{index}] => {0}")
    @CsvFileSource(delimiter = '|', resources = "resources/MergeSort.csv", useHeadersInDisplayName = true)
    void mergeSortTest(@ConvertWith(ToIntegerArrayConverter.class)  Integer... in) {
        Integer[] expected = Arrays.copyOf(in, in.length);
        Arrays.sort(expected);
        Integer[] actual = in;
        MergeSort.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @RepeatedTest(10)
    void mergeSortRandomTest() {
        Integer[] actual = getRandomArray(10);
        Integer[] expected = Arrays.copyOf(actual, actual.length);

        // When
        Arrays.sort(expected);
        MergeSort.sort(actual);
        // Then
        assertArrayEquals(expected, actual);
    }

    private static Integer[] getRandomArray(int size) {
        int randomSize = (int) (Math.random() * size + 1);
        Integer[] arr = new Integer[randomSize];
        for (int i = 0; i < randomSize; i++) {
            int random = (int) (Math.random() * 100);
            arr[i] = random;
        }
        return arr;
    }

    public static class ToIntegerArrayConverter extends TypedArgumentConverter<String, Integer[]> {
        protected ToIntegerArrayConverter() {
            super(String.class, Integer[].class);
        }

        @Override
        protected Integer[] convert(String source) throws ArgumentConversionException {
            return Arrays.stream(source.split(",")).map(Integer::valueOf).toArray(Integer[]::new);
        }
    }
}
