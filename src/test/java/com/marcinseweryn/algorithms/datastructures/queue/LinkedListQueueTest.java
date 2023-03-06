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
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {
    LinkedListQueue<Integer> queue;

    @BeforeEach
    void beforeEach() {
        queue = new LinkedListQueue<>();
    }

    @Test
    void testisEmpty() {
        queue = new LinkedListQueue<>();

        assertAll(
                () -> assertTrue(queue.isEmpty()),
                () -> assertEquals(0, queue.size())
        );
    }

    @Test
    void testPeekEmptyQueueShouldThrowNoSuchElementException() {
        Executable executable = () -> queue.peek();

        assertThrows(NoSuchElementException.class, executable);
    }

    @Test
    void testEnqueueEmptyQueueShouldThrowNoSuchElementException() {
        Executable actual = () -> queue.dequeue();

        assertThrows(NoSuchElementException.class, actual);
    }

    // setting different delimiter is critical here
    @ParameterizedTest(name = "{index} -> {0}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            data
            1,2,3,4,5,6,7,8
            1,2,3,4,5,6
            1,2,3
            ''
            1,3,4,2,4,5,6,4,3,5,6,5,43,4,6,5,4
            2
            """)
    void testEnqueue(@ConvertWith(ToIntegerArrayConverter.class) Integer[] array) {
        for (Integer integer : array) {
            queue.enqueue(integer);
        }

        assertEquals(array.length, queue.size());
    }


    @ParameterizedTest(name = "{index} -> {0}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, textBlock = """
            data
            1,2,3,4,5,6,7,8
            1,2,3,4,5,6
            1,2,3
            ''
            1,3,4,2,4,5,6,4,3,5,6,5,43,4,6,5,4
            2
            """)
    void testDequeue(@ConvertWith(ToIntegerArrayConverter.class) Integer... expected) {
        for (Integer integer : expected) {
            queue.enqueue(integer);
        }
        Integer[] actual = new Integer[expected.length];
        for (int i = 0; i < expected.length; i++) {
            actual[i] = queue.dequeue();
        }

        assertAll(
                () -> assertTrue(queue.isEmpty()),
                () -> assertEquals(0, queue.size()),
                () -> assertArrayEquals(expected, actual)
        );
    }

    @ParameterizedTest(name = "{index} -> {0}, {1}")
    @CsvSource(delimiter = '|', useHeadersInDisplayName = true, ignoreLeadingAndTrailingWhitespace = true, textBlock = """
            expected        |        data                                    
            1               |         1,2,3,4,5,6,7,8                         
            1               |         1,2,3,4,5,6                            
            7               |         7,2,3                                   
            9               |         9,3,4,2,4,5,6,4,3,5,6,5,43,4,6,5,4      
            """)
    void testPeek(int expected, @ConvertWith(ToIntegerArrayConverter.class) Integer... array) {
        for (Integer integer : array) {
            queue.enqueue(integer);
        }

        assertAll(
                () -> assertEquals(array.length, queue.size()),
                () -> assertEquals(queue.peek(), expected)
        );
    }

    @Test
    void testAll() {
        for (int i = 0; i < 10_000; i++) {
            queue.enqueue(i);
        }
        for (int i = 0; i < 10_000; i++) {
            assertEquals(queue.dequeue(), i);
        }
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());

        Random random = new Random();
        Integer[] expected = new Integer[10_000];
        for (int i = 0; i < 10_000; i++) {
            int randomInt = random.nextInt(1000);
            queue.enqueue(randomInt);
            expected[i] = randomInt;
        }
        assertEquals(10_000, queue.size());
        Integer[] actual = new Integer[10000];
        for (int i = 0; i < 10_000; i++) {
            actual[i] = queue.dequeue();
        }

        assertAll(
                () -> assertEquals(0, queue.size()),
                () -> assertTrue(queue.isEmpty()),
                () -> assertArrayEquals(expected, actual)
        );
    }

    static class ToIntegerArrayConverter extends TypedArgumentConverter<String, Integer[]> {
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