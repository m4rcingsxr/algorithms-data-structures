package com.marcinseweryn.algorithms.datastructures.queue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CircularArrayQueueTest {

    @Test
    void givenNewQueue_whenCreated_thenShouldBeEmpty() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void givenSingleElement_whenAdded_thenShouldPeekCorrectly() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        queue.enqueue(10);
        assertEquals(10, queue.peek());
    }

    @Test
    void givenSingleElement_whenRemoved_thenShouldBeEmpty() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        queue.enqueue(10);
        assertEquals(10, queue.dequeue());
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    void givenEmptyQueue_whenPeekOrDequeueCalled_thenShouldThrowException() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        assertThrows(IllegalStateException.class, queue::peek);
        assertThrows(IllegalStateException.class, queue::dequeue);
    }

    @Test
    void givenFullQueue_whenEnqueueCalled_thenShouldThrowException() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertThrows(IllegalStateException.class, () -> queue.enqueue(40));
    }

    @Test
    void givenQueue_whenElementsWrappedAround_thenShouldHandleCorrectly() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(3);
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        assertEquals(10, queue.dequeue());
        queue.enqueue(40);
        assertEquals(20, queue.peek());
        assertEquals(20, queue.dequeue());
        assertEquals(30, queue.dequeue());
        assertEquals(40, queue.peek());
        assertEquals(40, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void givenQueue_whenIteratorUsed_thenShouldTraverseCorrectly() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
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
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        queue.enqueue(10);
        Iterator<Integer> iterator = queue.iterator();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void givenEmptyQueue_whenIterate_thenHasNextShouldReturnFalse() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        Iterator<Integer> iterator = queue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenEmptyQueue_whenNextCalled_thenShouldThrowException() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
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
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(toEnqueue.length + 1);

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
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(5);
        queue.enqueue(null);
        assertNull(queue.peek());
        assertNull(queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
    void givenLargeNumberOfElements_whenAdded_thenShouldHandleCorrectly() {
        CircularArrayQueue<Integer> queue = new CircularArrayQueue<>(10000);
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
