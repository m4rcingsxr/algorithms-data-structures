package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.priorityqueue.RecursiveBinaryHeap;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class RecursiveBinaryHeapTest {

    @Test
    void givenEmptyMinHeap_whenCreated_thenShouldBeEmpty() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenEmptyMaxHeap_whenCreated_thenShouldBeEmpty() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenSingleElement_whenAddedToMinHeap_thenShouldBePresentAndBeRoot() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(10);
        assertEquals(1, minHeap.size());
        assertEquals(10, minHeap.peek());
    }

    @Test
    void givenSingleElement_whenAddedToMaxHeap_thenShouldBePresentAndBeRoot() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        assertEquals(1, maxHeap.size());
        assertEquals(10, maxHeap.peek());
    }

    @Test
    void givenMultipleElements_whenAddedToMinHeap_thenShouldMaintainHeapProperty() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(10);

        assertEquals(3, minHeap.size());
        assertEquals(10, minHeap.peek());
        assertTrue(minHeap.contains(20));
        assertFalse(minHeap.contains(40));
    }

    @Test
    void givenMultipleElements_whenAddedToMaxHeap_thenShouldMaintainHeapProperty() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);

        assertEquals(3, maxHeap.size());
        assertEquals(30, maxHeap.peek());
        assertTrue(maxHeap.contains(20));
        assertFalse(maxHeap.contains(5));
    }

    @Test
    void givenDuplicateElement_whenAddedToMinHeap_thenShouldNotAdd() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(10);
        assertFalse(minHeap.add(10));
        assertEquals(1, minHeap.size());
    }

    @Test
    void givenDuplicateElement_whenAddedToMaxHeap_thenShouldNotAdd() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        assertFalse(maxHeap.add(10));
        assertEquals(1, maxHeap.size());
    }

    @Test
    void givenMinHeapWithElements_whenPolled_thenShouldReturnAndRemoveMinElement() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(10);

        assertEquals(10, minHeap.poll());
        assertEquals(2, minHeap.size());
        assertEquals(20, minHeap.peek());
    }

    @Test
    void givenMaxHeapWithElements_whenPolled_thenShouldReturnAndRemoveMaxElement() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);

        assertEquals(30, maxHeap.poll());
        assertEquals(2, maxHeap.size());
        assertEquals(20, maxHeap.peek());
    }

    @Test
    void givenMinHeapWithElements_whenRemovedSpecificElement_thenShouldMaintainHeapProperty() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(10);
        minHeap.add(40);

        assertTrue(minHeap.remove(20));
        assertEquals(3, minHeap.size());
        assertEquals(10, minHeap.peek());
        assertFalse(minHeap.contains(20));
    }

    @Test
    void givenMaxHeapWithElements_whenRemovedSpecificElement_thenShouldMaintainHeapProperty() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        maxHeap.add(40);

        assertTrue(maxHeap.remove(20));
        assertEquals(3, maxHeap.size());
        assertEquals(40, maxHeap.peek());
        assertFalse(maxHeap.contains(20));
    }

    @Test
    void givenMinHeapWithElements_whenIteratedInOrder_thenShouldReturnElementsInHeapOrder() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(40);
        minHeap.add(20);
        minHeap.add(60);
        minHeap.add(10);
        minHeap.add(30);
        minHeap.add(50);
        minHeap.add(70);

        Iterator<Integer> inOrder = minHeap.inOrderIterator();
        assertTrue(inOrder.hasNext());
        assertEquals(40, inOrder.next());
        assertEquals(20, inOrder.next());
        assertEquals(30, inOrder.next());
        assertEquals(10, inOrder.next());
        assertEquals(60, inOrder.next());
        assertEquals(50, inOrder.next());
        assertEquals(70, inOrder.next());
        assertFalse(inOrder.hasNext());
    }


    @Test
    void givenMinHeapWithElements_whenCleared_thenShouldBeEmpty() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(10);
        minHeap.add(20);
        minHeap.clear();
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenMaxHeapWithElements_whenCleared_thenShouldBeEmpty() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.clear();
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenMinHeapWithOneElement_whenRemoved_thenShouldBeEmpty() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(10);
        minHeap.remove(10);
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenMaxHeapWithOneElement_whenRemoved_thenShouldBeEmpty() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        maxHeap.remove(10);
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenLargeNumberOfElements_whenAddedToMinHeap_thenShouldHandleCorrectly() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);

        // Add elements to the Min Heap
        for (int i = 0; i < 10000; i++) {
            minHeap.add(i);
        }

        assertEquals(10000, minHeap.size());
        assertTrue(minHeap.contains(0));
        assertTrue(minHeap.contains(9999));

        for (int i = 0; i < 10000; i++) {
            assertEquals(i, minHeap.poll());
        }

        assertTrue(minHeap.isEmpty());
    }

    @Test
    void givenLargeNumberOfElements_whenAddedToMaxHeap_thenShouldHandleCorrectly() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);

        // Add elements to the Max Heap
        for (int i = 0; i < 10000; i++) {
            maxHeap.add(i);
        }

        assertEquals(10000, maxHeap.size());
        assertTrue(maxHeap.contains(0));
        assertTrue(maxHeap.contains(9999));

        for (int i = 9999; i >= 0; i--) {
            assertEquals(i, maxHeap.poll());
        }

        assertTrue(maxHeap.isEmpty());
    }

    @Test
    void givenLargeNumberOfElements_whenRemovedFromMinHeap_thenShouldHandleCorrectly() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        for (int i = 0; i < 10000; i++) {
            minHeap.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            assertTrue(minHeap.remove(i));
        }
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenLargeNumberOfElements_whenRemovedFromMaxHeap_thenShouldHandleCorrectly() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        for (int i = 0; i < 10000; i++) {
            maxHeap.add(i);
        }
        for (int i = 9999; i >= 0; i--) {
            assertTrue(maxHeap.remove(i));
        }
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenMinHeap_whenRemoveNonExistentElement_thenShouldReturnFalse() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(10);
        assertFalse(minHeap.remove(20));
        assertEquals(1, minHeap.size());
    }

    @Test
    void givenMaxHeap_whenRemoveNonExistentElement_thenShouldReturnFalse() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        assertFalse(maxHeap.remove(20));
        assertEquals(1, maxHeap.size());
    }

    @Test
    void givenMinHeap_whenPollCalledOnEmptyHeap_thenShouldReturnNull() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        assertNull(minHeap.poll());
    }

    @Test
    void givenMaxHeap_whenPollCalledOnEmptyHeap_thenShouldReturnNull() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        assertNull(maxHeap.poll());
    }

    @Test
    void givenMinHeap_whenContainsCalled_thenShouldFindCorrectElement() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        RecursiveBinaryHeap<Integer> minHeap = new RecursiveBinaryHeap<>(10, comparator);
        minHeap.add(10);
        minHeap.add(20);
        assertTrue(minHeap.contains(10));
        assertFalse(minHeap.contains(30));
    }

    @Test
    void givenMaxHeap_whenContainsCalled_thenShouldFindCorrectElement() {
        Comparator<Integer> comparator = Comparator.reverseOrder();
        RecursiveBinaryHeap<Integer> maxHeap = new RecursiveBinaryHeap<>(10, comparator);
        maxHeap.add(10);
        maxHeap.add(20);
        assertTrue(maxHeap.contains(20));
        assertFalse(maxHeap.contains(5));
    }
}
