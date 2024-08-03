package com.marcinseweryn.algorithms.datastructures.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {

    @Test
    void givenNewQueue_whenCreated_thenShouldBeEmpty() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void givenSingleElement_whenAdded_thenShouldPeekCorrectly() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        assertEquals(10, queue.peek());
    }

    @Test
    void givenSingleElement_whenRemoved_thenShouldBeEmpty() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        assertEquals(10, queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void givenEmptyQueue_whenPeekOrDequeueCalled_thenShouldReturnNull() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        assertNull(queue.peek());
        assertThrows(IndexOutOfBoundsException.class , queue::dequeue);
    }

    @Test
    void givenMultipleElements_whenEnqueued_thenShouldPeekFirstCorrectly() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(10, queue.peek());
    }

    @Test
    void givenMultipleElements_whenDequeued_thenShouldDequeueInCorrectOrder() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(10, queue.dequeue());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void givenQueue_whenIteratorUsed_thenShouldTraverseInCorrectOrder() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        Iterator<Integer> iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenQueue_whenExhaustIterator_thenShouldThrowException() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(10);
        Iterator<Integer> iterator = queue.iterator();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void givenEmptyQueue_whenIterate_thenHasNextShouldReturnFalse() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        Iterator<Integer> iterator = queue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenEmptyQueue_whenNextCalled_thenShouldThrowException() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        Iterator<Integer> iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    static Stream<Arguments> givenElements_whenEnqueueAndDequeueCalled_thenShouldReturnCorrectResults() {
        return Stream.of(
                Arguments.of(new Integer[]{10, 20, 30}, new Integer[]{10, 20, 30}),
                Arguments.of(new Integer[]{1, 2, 3, 4, 5}, new Integer[]{1, 2, 3, 4, 5}),
                Arguments.of(new Integer[]{}, new Integer[]{})
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenElements_whenEnqueueAndDequeueCalled_thenShouldReturnCorrectResults(
            Integer[] toEnqueue, Integer[] expectedOrder) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        for (Integer elem : toEnqueue) {
            queue.enqueue(elem);
        }

        for (Integer expected : expectedOrder) {
            assertEquals(expected, queue.dequeue());
        }

        assertTrue(queue.isEmpty());
    }

    @Test
    void givenQueueWithNullElements_whenAdded_thenShouldHandleCorrectly() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        queue.enqueue(null);
        assertNull(queue.peek());
        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void givenLargeNumberOfElements_whenAdded_thenShouldHandleCorrectly() {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10000; i++) {
            queue.enqueue(i);
        }
        assertEquals(0, queue.peek());
        for (int i = 0; i < 10000; i++) {
            assertEquals(i, queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }
}
