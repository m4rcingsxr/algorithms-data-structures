package com.marcinseweryn.algorithms.datastructures.stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackArrayTest {

    StackArray<Integer> stack;

    @BeforeEach
    void setup() {
        stack = new StackArray<>(5);
    }

    @Test
    void peekTestShouldThrowEmptyStackExceptionIfStackIsEmpty() {
        Executable actual = () -> stack.peek();

        assertThrows(EmptyStackException.class, actual);
    }

    @ParameterizedTest(name = "[{index}] => values = {0}")
    @MethodSource("com.marcinseweryn.algorithms.datastructures.stack.ArgumentProvider#pushIncreaseValueOnFullStack")
    void pushIncreaseCapacityOnFullStack(Integer[] values, Integer size) {
        for (Integer value : values) {
            stack.push(value);
        }
        assertEquals(size, stack.size());
    }

    @Test
    void popShouldThrowEmptyStackExceptionIfStackIsEmpty() {
        Executable actual = () -> stack.pop();

        assertThrows(EmptyStackException.class, actual);
    }

    @ParameterizedTest(name = "[{index}] => values=[{0}], expectedSize=[{1}]")
    @MethodSource("com.marcinseweryn.algorithms.datastructures.stack.ArgumentProvider#pushTest")
    void pushTest(Integer[] values, Integer expectedSize) {
        for (Integer value : values) {
            stack.push(value);
        }
        Integer actual = stack.size();
        assertEquals(expectedSize, actual);

    }

    @ParameterizedTest(name = "[{index}] => values=[{0}], expected=[{1}], size=[{2}]")
    @MethodSource("com.marcinseweryn.algorithms.datastructures.stack.ArgumentProvider#popTest")
    void popTest(Integer[] values, Integer expectedPop, Integer sizeAfterPop) {
        for (Integer value : values) {
            stack.push(value);
        }

        Integer actual = stack.pop();
        Integer actualSize = stack.size();
        assertAll(
                () -> assertEquals(expectedPop, actual),
                () -> assertEquals(sizeAfterPop, actualSize)
        );

    }

    @ParameterizedTest(name = "[{index} => values = {0}, expected = [{1}]")
    @MethodSource("com.marcinseweryn.algorithms.datastructures.stack.ArgumentProvider#peekTest")
    void peekTest(Integer[] values, Integer expected) {
        for (Integer value : values) {
            stack.push(value);
        }
        Integer actual = stack.peek();

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "[{index}] => values = {0}, expected = [{1}]")
    @MethodSource("com.marcinseweryn.algorithms.datastructures.stack.ArgumentProvider#toStringTest")
    void toStringTest(Integer[] values, String expected) {
        for (Integer value : values) {
            stack.push(value);
        }
        String actual = stack.toString();

        assertEquals(expected, actual);
    }

    @Test
    void testAll() {
        for (int i = 0; i < 10_000; i++) {
            stack.push(i);
        }
        assertEquals(10_000, stack.size());
        int expected = 9999;
        for (int i = 0; i < 10_000; i++) {
            assertEquals(expected, stack.peek());
            assertEquals(expected--, stack.pop());
        }

        Executable actual =() -> stack.pop();
        assertThrows(EmptyStackException.class, actual);

        assertAll(
                () -> assertEquals(0, stack.size()),
                () -> assertTrue(stack.isEmpty())
        );
    }
}