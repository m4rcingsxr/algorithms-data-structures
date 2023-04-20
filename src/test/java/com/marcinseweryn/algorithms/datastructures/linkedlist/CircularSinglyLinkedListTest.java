package com.marcinseweryn.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CircularSinglyLinkedListTest {
    CircularSinglyLinkedList csll;

    @BeforeEach
    void createSLL() {
        csll = new CircularSinglyLinkedList();
    }


    @Test
    void testEmptyList() {
        int expectedSize = 0;

        int actualSize = csll.size();
        assertAll(
                () -> assertTrue(csll.isEmpty()),
                () -> assertEquals(expectedSize, actualSize)
        );
    }

    @Test()
    void testRemoveFirstOfEmptyShouldReturnFalse() {

        assertAll(
                () -> assertFalse(csll.remove(0)),
                () -> assertFalse(csll.remove(1)),
                () -> assertFalse(csll.remove(4))
        );
    }

    @Test
    void testRemoveLastOfEmptyShouldReturnFalse() {

        assertAll(
                () -> assertFalse(csll.remove(csll.size())),
                () -> assertFalse(csll.remove(csll.size())),
                () -> assertFalse(csll.remove(csll.size()))
        );
    }

    @Test
    void testPeekFirstOnEmptyList() {
        int expected = -1;
        int actual = csll.get(0);
        assertEquals(expected, csll.get(0));
    }

    @Test
    void testPeekLastOnEmptyList() {
        int expected = -1;
        assertEquals(expected, csll.get(csll.size()));
    }


    @ParameterizedTest(name = "[{index} => values = [{0}], expectedSize " +
            "=[{1}]]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       3
                1,4,3,2             |       4
                2,4,2,1,2           |       5
                2,1,6,4,4,5,7,5,3   |       9 
            """)
    void testAddFirst(@ConvertWith(ToIntArrayConverter.class) int[] values,
                      int expectedSize) {
        for (int value : values) {
            csll.add(0, value);
        }
        int actualSize = csll.size();
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest(name = "[{index} => values = [{0}], expectedSize " +
            "=[{1}]]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       3
                1,4,3,2             |       4
                2,4,2,1,2           |       5
                2,1,6,4,4,5,7,5,3   |       9 
            """)
    void testAddLast(@ConvertWith(ToIntArrayConverter.class) int[] values,
                     int expectedSize) {
        for (int value : values) {
            csll.add(csll.size(), value);
        }
        int actualSize = csll.size();
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest(name = "[{index} => values = [{0}], expectedSize " +
            "=[{1}]], index =[{2}]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       3       |       1
                1,4,3,2             |       4       |       2
                2,4,2,1,2           |       5       |       3
                2,1,6,4,4,5,7,5,3   |       9       |       6
            """)
    void testAddAt(@ConvertWith(ToIntArrayConverter.class) int[] values,
                   int expectedSize, int index) {
        for (int value : values) {
            csll.add(index, value);
        }
        int actualSize = csll.size();
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest(name = "[{index} => values = [{0}], expectedFirst " +
            "=[{1}]]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       1       
                1,4,3,2             |       1       
                2,4,2,1,2           |       2       
                8,1,6,4,4,5,7,5,3   |       8       
            """)
    void testGetFirst(@ConvertWith(ToIntArrayConverter.class) int[] values,
                      int expectedFirst) {
        for (int value : values) {
            csll.add(csll.size(), value);
        }

        int actualFirst = csll.get(0);
        assertEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expectedSize = " +
            "[{1}]," +
            " expectedBoolean = [{2}]")
    @CsvSource(delimiter = '|', textBlock = """
            1,2,3,4         |       3       |       true
            1,2,3,4,5,6,4,2 |       7       |       true
            4,2,1,8         |       3       |       true
            ''              |       0       |       false
            """)
    void testRemoveFirst(@ConvertWith(ToIntArrayConverter.class) int[] values,
                         int expectedSize, boolean expected) {
        for (int value : values) {
            csll.add((int) (Math.random() * values.length + 1), value);
        }
        boolean actualResult = csll.remove(0);
        int actualSize = csll.size();
        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertEquals(expected, actualResult)
        );

    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expectedSize = " +
            "[{1}]," +
            " expectedBoolean = [{2}]")
    @CsvSource(delimiter = '|', textBlock = """
            1,2,3,4         |       3       |       true
            1,2,3,4,5,6,4,2 |       7       |       true
            4,2,1,8         |       3       |       true
            ''              |       0       |       false
            """)
    void testRemoveLast(@ConvertWith(ToIntArrayConverter.class) int[] values,
                        int expectedSize, boolean expected) {
        for (int value : values) {
            csll.add((int) (Math.random() * values.length + 1), value);
        }
        boolean actualResult = csll.remove(csll.size());
        int actualSize = csll.size();
        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertEquals(expected, actualResult)
        );

    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expectedSize = " +
            "[{1}]," +
            " expectedBoolean = [{2}]")
    @CsvSource(delimiter = '|', textBlock = """
            1,2,3,4         |    1      |     3       |       true
            1,2,3,4,5,6,4,2 |    2      |     7       |       true
            4,2,1,8         |    3      |     3       |       true
            ''              |    4      |     0       |       false
            """)
    void testRemoveAt(@ConvertWith(ToIntArrayConverter.class) int[] values,
                      int index, int expectedSize, boolean expected) {
        for (int value : values) {
            csll.add((int) (Math.random() * values.length + 1), value);
        }
        boolean actualResult = csll.remove(index);
        int actualSize = csll.size();
        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertEquals(expected, actualResult)
        );

    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expected = [{1}]," +
            " expectedBoolean = [{2}]")
    @CsvSource(delimiter = '|', textBlock = """
            1,2,3,4         |       4       
            1,2,3,4,5,6,4,2 |       2       
            4,2,1,8         |       8      
            """)
    void testPeekLast(@ConvertWith(ToIntArrayConverter.class) int[] values,
                      int expected) {
        for (int value : values) {
            csll.add(csll.size(), value);
        }
        int actualResult = csll.get(csll.size() - 1);
        assertAll(
                () -> assertEquals(expected, actualResult)
        );

    }

    @ParameterizedTest(name = "[{index} => {0}]")
    @CsvSource(delimiter = '|', textBlock = """
            1,2,3,4,5       |       1       |       2
            9,2,5,7,1       |       3       |       7
            5,1,1,2,4       |       4       |       4
            5,1,1,2,4       |       0       |       5
            5,1,1,2,4       |       2       |       1
            5,1,1,2,4       |       1       |       1
            """)
    void testPeeking(@ConvertWith(ToIntArrayConverter.class) int[] values,
                     int index, int expected) {
        for (int value : values) {
            csll.add(csll.size(), value);
        }
        int actual = csll.get(index);
        assertEquals(expected, actual);
    }

    @Test
    void testRemoving() {
        for (int i = 0; i < 10; i++) {
            csll.add(i, i);
        }
        for (int i = 0; i < 10; i++) {
            csll.remove(i);
        }
        assertEquals(0, csll.size());
    }

    private static class ToIntArrayConverter
            extends TypedArgumentConverter<String, int[]> {
        protected ToIntArrayConverter() {
            super(String.class, int[].class);
        }

        @Override
        protected int[] convert(String source)
                throws ArgumentConversionException {
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