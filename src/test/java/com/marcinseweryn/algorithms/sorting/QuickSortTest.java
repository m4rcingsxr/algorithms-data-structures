package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class QuickSortTest {

    @ParameterizedTest(name = "[{index}] => {0}")
    @CsvFileSource(delimiter = '|', resources = "resources/QuickSort.csv", useHeadersInDisplayName = true)
    void quickSortTest(@ConvertWith(ToIntArrayConverter.class) int[] in) {
        int[] expected = Arrays.copyOf(in, in.length);
        Arrays.sort(expected);
        int[] actual = in;
        QuickSort.sort(actual);

        assertArrayEquals(expected, actual);
    }

    @RepeatedTest(10)
    void quickSortRandomTest() {
        int[] actual = getRandomArray(10);
        int[] expected = Arrays.copyOf(actual, actual.length);

        // When
        Arrays.sort(expected);
        QuickSort.sort(actual);
        // Then
        assertArrayEquals(expected, actual);
    }

    private static int[] getRandomArray(int size) {
        int randomSize = (int) (Math.random() * size + 1);
        int[] arr = new int[randomSize];
        for (int i = 0; i < randomSize; i++) {
            int random = (int) (Math.random() * 100);
            arr[i] = random;
        }
        return arr;
    }


    static class ToIntArrayConverter extends TypedArgumentConverter<String, int[]> {
        protected ToIntArrayConverter() {
            super(String.class, int[].class);
        }

        @Override
        protected int[] convert(String source) throws ArgumentConversionException {
            if (source.equals("")) {
                return new int[0];
            }
            String[] chars = source.split(",");
            int[] result = new int[chars.length];
            for (int i = 0; i < chars.length; i++) {
                result[i] = Integer.parseInt(chars[i]);
            }
            return result;
        }
    }

}