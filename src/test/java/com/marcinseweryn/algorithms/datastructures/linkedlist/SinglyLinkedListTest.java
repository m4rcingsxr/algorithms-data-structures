package com.marcinseweryn.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    SinglyLinkedList sll;

    @BeforeEach
    void createSLL() {
        sll = new SinglyLinkedList();
    }


    @Test
    void testEmptyList() {
        int expectedSize = 0;

        int actualSize = sll.size();
        assertAll(
                () -> assertTrue(sll.isEmpty()),
                () -> assertEquals(expectedSize, actualSize)
        );
    }

    @Test()
    void testRemoveFirstOfEmptyShouldReturnFalse() {

        assertAll(
                () -> assertFalse(sll.remove(0)),
                () -> assertFalse(sll.remove(1)),
                () -> assertFalse(sll.remove(4))
        );
    }

    @Test
    void testRemoveLastOfEmptyShouldReturnFalse() {

        assertAll(
                () -> assertFalse(sll.remove(sll.size())),
                () -> assertFalse(sll.remove(sll.size())),
                () -> assertFalse(sll.remove(sll.size()))
        );
    }

    @Test
    void testPeekFirstOnEmptyList() {
        int expected = -1;
        int actual = sll.get(0);
        assertEquals(expected, sll.get(0));
    }

    @Test
    void testPeekLastOnEmptyList() {
        int expected = -1;
        assertEquals(expected, sll.get(sll.size()));
    }


    @ParameterizedTest(name = "[{index} => values = [{0}], expectedSize =[{1}]]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       3
                1,4,3,2             |       4
                2,4,2,1,2           |       5
                2,1,6,4,4,5,7,5,3   |       9 
            """)
    void testAddFirst(@ConvertWith(ToIntArrayConverter.class) int[] values, int expectedSize) {
        for (int value : values) {
            sll.add(0, value);
        }
        int actualSize = sll.size();
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest(name = "[{index} => values = [{0}], expectedSize =[{1}]]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       3
                1,4,3,2             |       4
                2,4,2,1,2           |       5
                2,1,6,4,4,5,7,5,3   |       9 
            """)
    void testAddLast(@ConvertWith(ToIntArrayConverter.class) int[] values, int expectedSize) {
        for (int value : values) {
            sll.add(sll.size(), value);
        }
        int actualSize = sll.size();
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest(name = "[{index} => values = [{0}], expectedSize =[{1}]], index =[{2}]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       3       |       1
                1,4,3,2             |       4       |       2
                2,4,2,1,2           |       5       |       3
                2,1,6,4,4,5,7,5,3   |       9       |       6
            """)
    void testAddAt(@ConvertWith(ToIntArrayConverter.class) int[] values, int expectedSize, int index) {
        for (int value : values) {
            sll.add(index, value);
        }
        int actualSize = sll.size();
        assertEquals(expectedSize, actualSize);
    }

    @ParameterizedTest(name = "[{index} => values = [{0}], expectedFirst =[{1}]]")
    @CsvSource(delimiter = '|', textBlock = """
                1,2,3               |       1       
                1,4,3,2             |       1       
                2,4,2,1,2           |       2       
                8,1,6,4,4,5,7,5,3   |       8       
            """)
    void testGetFirst(@ConvertWith(ToIntArrayConverter.class) int[] values, int expectedFirst) {
        for (int value : values) {
            sll.add(sll.size(), value);
        }

        int actualFirst = sll.get(0);
        assertEquals(expectedFirst, actualFirst);
    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expectedSize = [{1}]," +
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
            sll.add((int) (Math.random() * values.length + 1), value);
        }
        boolean actualResult = sll.remove(0);
        int actualSize = sll.size();
        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertEquals(expected, actualResult)
        );

    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expectedSize = [{1}]," +
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
            sll.add((int) (Math.random() * values.length + 1), value);
        }
        boolean actualResult = sll.remove(sll.size());
        int actualSize = sll.size();
        assertAll(
                () -> assertEquals(expectedSize, actualSize),
                () -> assertEquals(expected, actualResult)
        );

    }

    @ParameterizedTest(name = "[{index}] => values = [{0}], expectedSize = [{1}]," +
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
            sll.add((int) (Math.random() * values.length + 1), value);
        }
        boolean actualResult = sll.remove(index);
        int actualSize = sll.size();
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
            sll.add(sll.size(), value);
        }
        int actualResult = sll.get(sll.size() - 1);
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
            sll.add(sll.size(), value);
        }
        int actual = sll.get(index);
        assertEquals(expected, actual);
    }

    @Test
    void testRemoving() {
        for (int i = 0; i < 10; i++) {
            sll.add(i, i);
        }
        for (int i = 0; i < 10; i++) {
            sll.remove(i);
        }
        assertEquals(0, sll.size());
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