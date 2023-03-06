package com.marcinseweryn.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedListTest {
    LinkedList<Integer> list;

    @BeforeEach
    void beforeEach() {
        list = new LinkedList<>();
    }

    @Test
    void testEmptyList() {
        LinkedList<Integer> list = this.list;
        int expectedSize = 0;

        int actualSize = list.size();
        boolean actualIsEmpty = list.isEmpty();

        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertTrue(actualIsEmpty)
        );
    }

    @Test
    void testPeekFirstOnEmptyListShouldThrowNoSuchElementException() {
        Executable actual = () -> list.peekFirst();
        Class<? extends NoSuchElementException> expected = NoSuchElementException.class;

        assertThrows(expected, actual);
    }

    @Test
    void testPeekLastOnEmptyListShouldThrowNoSuchElementException() {
        Executable actual = () -> list.peekLast();
        Class<NoSuchElementException> expected = NoSuchElementException.class;

        assertThrows(expected, actual);
    }

    @ParameterizedTest()
    @MethodSource
    void peekFirstTest(int[] values, int expected, int size) {
        for (int value : values) {
            list.add(value);
        }

        int actual = list.peekFirst();
        int actualSize = list.size();
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(size, actualSize)
        );
    }

    @ParameterizedTest
    @MethodSource
    void peekLastTest(int[] values, int expected, int size) {
        for (int value : values) {
            list.add(value);
        }

        int actual = list.peekLast();
        int actualSize = list.size();
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertEquals(size, actualSize)
        );
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}")
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            index   ,   value
            -2      ,   5   
            100     ,   6    
            -4      ,   14    
            """)
    void testAddWithWrongIndexShouldThrowIndexOutOfBoundsException(int index, int value) {

        Executable actual = () -> list.add(index, value);

        assertThrows(IndexOutOfBoundsException.class, actual);
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            elements    |       size
            1,2,4       |       3
            7,5         |       2
            4           |       1
            ''          |       0
            """)
    void testAddFirst(@ConvertWith(ToIntArrayConverter.class) int[] values, int size) {
        for (int i = 0; i < size; i++) {
            list.addFirst(values[i]);
        }
        int actualSize = list.size();

        assertEquals(size, actualSize);

    }

    @ParameterizedTest(name = "{index} -> {0}, {1}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            elements    |       size
            1,2,4       |       3
            7,5         |       2
            4           |       1
            ''          |       0
            """)
    void testAddLast(@ConvertWith(ToIntArrayConverter.class) int[] values, int size) {
        for (int i = 0; i < size; i++) {
            list.addLast(values[i]);
        }
        int actualSize = list.size();

        assertEquals(size, actualSize);

    }

    @ParameterizedTest(name = "{index} -> {0}, {1}, {2}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            elements    |       indexes     |      size
            1,2,4,9,0   |       0,1,0,3,2   |      5
            1,2,4,9,0   |       0,0,0,3,1   |      5
            1,2,4,9,0   |       0,1,1,2,2   |      5
            1,2,4,9,0,2 |       0,1,1,2,2,5 |      6
            """)
    void testAddAtSpecifiedIndex(@ConvertWith(ToIntArrayConverter.class) int[] values,
                                 @ConvertWith(ToIntArrayConverter.class) int[] indexes, int size) {
        for (int i = 0; i < size; i++) {
            list.add(indexes[i], values[i]);

        }
        assertEquals(list.size(), size);
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            elements        |      size
            1,2,4,9,0       |      5
            1,2,4,9,0       |      5
            1,2,4,9,0       |      5
            1,2,4,9,0,2     |      6
            """)
    void testAdd(@ConvertWith(ToIntArrayConverter.class) int[] values, int size) {
        for (int i = 0; i < size; i++) {
            list.add(values[i]);
        }
        assertEquals(list.size(), size);

    }

    @Test
    void testRemoveFirstOnEmptyListShouldThrowNoSuchElementException() {
        Executable actual = () -> list.removeFirst();

        assertThrows(NoSuchElementException.class, actual);
    }

    @Test
    void testRemoveLastOnEmptyListShouldThrowNoSuchElementException() {
        Executable actual = () -> list.removeLast();

        assertThrows(NoSuchElementException.class, actual);
    }

    @Test
    void testRemoveAtOnEmptyListShouldReturnNoSuchElementException() {
        Executable actual = () -> list.remove(2);

        assertThrows(NoSuchElementException.class, actual);
    }

    @Test
    void testRemoveWithWrongArgumentShouldThrowIndexOutOfBoundsException() {
        list.add(1);
        list.add(2);
        list.add(3);

        Executable actual1 = () -> list.remove(-1);
        Executable actual2 = () -> list.remove(list.size() + 1);
        assertAll(
                () -> assertThrows(IndexOutOfBoundsException.class, actual1),
                () -> assertThrows(IndexOutOfBoundsException.class, actual2)
        );

    }

    @ParameterizedTest(name = "{index} -> {0}, {1}, {2}")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            values          |       expected         |        size
            1,2,4,9,0       |       1                |        4
            8,2,4,9,0       |       8                |        4
            7,2,4,9,0       |       7                |        4
            5,2,4,9,0,2     |       5                |        5
            5               |       5                |        0
            """)
    void testRemoveFirst(@ConvertWith(ToIntArrayConverter.class) int[] values,
                         int expected, int size) {
        for (int value : values) {
            list.add(value);
        }
        int actualRemoveFirst = list.removeFirst();
        int actualSize = list.size();

        assertAll(
                () -> assertEquals(expected, actualRemoveFirst),
                () -> assertEquals(size, actualSize)
        );
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}, {2}")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            values          |       expected         |        size
            1,2,4,9,2       |       2                |        4
            8,2,4,9,4       |       4                |        4
            7,2,4,9,5       |       5                |        4
            5,2,4,9,0,1     |       1                |        5
            5               |       5                |        0
            """)
    void testRemoveLast(@ConvertWith(ToIntArrayConverter.class) int[] values,
                        int expected, int size) {
        for (int value : values) {
            list.add(value);
        }
        int actualRemoveLast = list.removeLast();
        int actualSize = list.size();

        assertAll(
                () -> assertEquals(expected, actualRemoveLast),
                () -> assertEquals(size, actualSize)
        );
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}, {2}, {3}")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            values          |       expected         |        size      |       index    
            1,2,4,9,2       |       1                |        4         |       0
            8,2,4,9,4       |       2                |        4         |       1
            7,2,4,9,5       |       4                |        4         |       2
            5,2,4,9,0,1     |       1                |        5         |       5
            5               |       5                |        0         |       0
            """)
    void testRemoveAt(@ConvertWith(ToIntArrayConverter.class) int[] values,
                      int expected, int size, int index) {
        for (int value : values) {
            list.add(value);
        }
        int actualRemoveAt = list.remove(index);
        int actualSize = list.size();

        assertAll(
                () -> assertEquals(expected, actualRemoveAt),
                () -> assertEquals(size, actualSize)
        );
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}, {2}, {3}")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            values          |       expected        |       element    
            1,2,4,9,2       |       true            |       9
            8,2,4,9,4       |       true            |       8
            7,2,4,9,5       |       false           |       10
            5,2,4,9,0,1     |       true            |       1
            5               |       true            |       5
            """)
    void testContains(@ConvertWith(ToIntArrayConverter.class) int[] values,
                      boolean expected, int element) {
        for (int value : values) {
            list.add(value);
        }
        boolean actualContains = list.contains(element);


        assertEquals(expected, actualContains);
    }


    private static Stream<Arguments> peekFirstTest() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4}, 1, 4),
                Arguments.arguments(new int[]{2, 1, 3, 4, 7, 5}, 2, 6),
                Arguments.arguments(new int[]{420}, 420, 1)
        );
    }

    private static Stream<Arguments> peekLastTest() {
        return Stream.of(
                Arguments.arguments(new int[]{1, 2, 3, 4}, 4, 4),
                Arguments.arguments(new int[]{2, 1, 3, 4, 7, 5}, 5, 6),
                Arguments.arguments(new int[]{420}, 420, 1)
        );
    }

    private static class ToIntArrayConverter extends TypedArgumentConverter<String, int[]> {
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
