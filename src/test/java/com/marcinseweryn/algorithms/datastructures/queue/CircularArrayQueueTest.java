package com.marcinseweryn.algorithms.datastructures.queue;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CircularArrayQueueTest {
    CircularArrayQueue<Integer> queue;

    @BeforeEach()
    void beforeEach() {
        queue = new CircularArrayQueue<>(5);
    }

    @Test
    void testEmptyQueue() {
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest(name = "{index} -> {0}")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            expected_size        |       data
            5                    |        1,2,3,4,5
            3                    |        1,2,3
            1                    |        1
            0                    |        ''
            """)
    void testEnqueue(int size, @ConvertWith(ToIntegerArrayConverter.class) Integer... in) {
        for (Integer integer : in) {
            queue.enqueue(integer);
        }
        for (int i = 0; i < in.length; i++) {
            assertEquals(in[i], queue.dequeue());
        }
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}")
    @CsvSource(useHeadersInDisplayName = true, delimiter = '|', textBlock = """
            expected_size   |       data
            5               |       1,2,3,4,5
            3               |       1,2,3
            1               |       1
            0               |       ''
            """)
    void testDequeue(int size, @ConvertWith(ToIntegerArrayConverter.class) Integer... in) {
        Random random = new Random();
        for (Integer integer : in) {
            queue.enqueue(integer);
        }

        Integer[] expected = new Integer[in.length];
        for (int i = 0; i < in.length; i++) {
            expected[i] = queue.dequeue();
        }
        assertAll(
                () -> assertArrayEquals(expected, in)
        );
    }

    @Test
    void testIsFull() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        assertTrue(queue.isFull());
    }

    @Test
    void shouldReturnFalseIfNotFull() {
        assertAll(
                () -> assertTrue(queue.isEmpty()),
                () -> assertFalse(queue.isFull())
        );
    }

    @Test
    void testAll() {
        queue = new CircularArrayQueue<>(10);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        assertAll(
                () -> assertTrue(queue.isFull())
        );

        // Test circular array
        Integer actual = queue.dequeue();
        Integer expected = 0;
        assertAll(
                () -> assertEquals(expected, actual),
                () -> assertFalse(queue.isFull())
        );


        assertAll(
                () -> assertEquals(1, queue.dequeue()),
                () -> assertFalse(queue.isFull())
        );

        for (int i = 0; i < 8; i++) {
            queue.dequeue();
        }
        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
    }

    @Test
    void testQueueIsFullShouldThrowExceptionWhenInsert() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        Executable actual = () -> queue.enqueue(1);

        assertAll(
                () -> assertTrue(queue.isFull()),
                () -> assertThrows(IllegalStateException.class, actual)
        );

    }

    public static class ToIntegerArrayConverter extends TypedArgumentConverter<String, Integer[]> {
        protected ToIntegerArrayConverter() {
            super(String.class, Integer[].class);
        }

        @Override
        protected Integer[] convert(String source) throws ArgumentConversionException {
            if (source.isEmpty()) {
                return new Integer[0];
            }
            return Arrays.stream(source.split(",")).map(Integer::valueOf).toArray(Integer[]::new);
        }
    }
}