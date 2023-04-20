package com.marcinseweryn.algorithms.sorting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class HeapSortTest {

    @ParameterizedTest(name = "{index} -> {0}")
    @CsvFileSource(delimiter = '|', resources = "resources/HeapSort.csv",
            useHeadersInDisplayName = true)
    void heapSortTest(
            @ConvertWith(ToIntegerArrayConverter.class) Integer... in) {
        Integer[] expected = Arrays.copyOf(in, in.length);
        Arrays.sort(expected);
        HeapSort.sort(in);
        assertArrayEquals(expected, in);
    }

    public static class ToIntegerArrayConverter
            extends TypedArgumentConverter<String, Integer[]> {
        protected ToIntegerArrayConverter() {
            super(String.class, Integer[].class);
        }

        @Override
        protected Integer[] convert(String source)
                throws ArgumentConversionException {
            return Arrays.stream(source.split(",")).map(
                    Integer::valueOf).toArray(Integer[]::new);
        }
    }
}
