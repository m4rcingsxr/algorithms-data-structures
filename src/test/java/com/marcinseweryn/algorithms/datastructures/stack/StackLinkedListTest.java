package com.marcinseweryn.algorithms.datastructures.stack;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class StackLinkedListTest {

    @Test
    void givenNewStack_whenCreated_thenShouldBeEmpty() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void givenEmptyStack_whenPopCalled_thenShouldThrowException() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void givenEmptyStack_whenPeekCalled_thenShouldThrowException() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    void givenSingleElement_whenPushed_thenShouldBeAbleToPop() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(10);
        assertEquals(1, stack.size());
        assertEquals(10, stack.peek());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void givenMultipleElements_whenPushed_thenShouldPopInReverseOrder() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals(30, stack.pop());
        assertEquals(20, stack.pop());
        assertEquals(10, stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void givenStack_whenIterated_thenShouldIterateInLIFOOrder() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        Iterator<Integer> iterator = stack.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenMultipleElements_whenPeekCalled_thenShouldNotRemoveElement() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);

        assertEquals(3, stack.size());
        assertEquals(30, stack.peek());
        assertEquals(3, stack.size());
    }

    @Test
    void givenEmptyStack_whenIteratorCalled_thenHasNextShouldReturnFalse() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        Iterator<Integer> iterator = stack.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenLargeNumberOfElements_whenPushedAndPopped_thenShouldHandleCorrectly() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            stack.push(i);
        }
        assertEquals(size, stack.size());

        for (int i = size - 1; i >= 0; i--) {
            assertEquals(i, stack.pop());
        }
        assertTrue(stack.isEmpty());
    }

    @Test
    void givenStack_whenPushedNullElement_thenShouldHandleCorrectly() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        stack.push(null);
        assertEquals(1, stack.size());
        assertNull(stack.peek());
        assertNull(stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    void givenStack_whenPopAndPeekCalledOnEmptyStack_thenShouldThrowEmptyStackException() {
        StackLinkedList<Integer> stack = new StackLinkedList<>();
        assertThrows(EmptyStackException.class, stack::pop);
        assertThrows(EmptyStackException.class, stack::peek);
    }
}
