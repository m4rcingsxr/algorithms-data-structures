package com.marcinseweryn.algorithms.datastructures.tree.priorityqueue;

import com.marcinseweryn.algorithms.datastructures.tree.priorityqueue.BinaryHeapFastRemove;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapFastRemoveTest {

    @Test
    void givenEmptyMinHeap_whenCreated_thenShouldBeEmpty() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenEmptyMaxHeap_whenCreated_thenShouldBeEmpty() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenSingleElement_whenAddedToMinHeap_thenShouldBePresentAndBeRoot() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(10);
        assertEquals(1, minHeap.size());
        assertEquals(10, minHeap.peek());
    }

    @Test
    void givenSingleElement_whenAddedToMaxHeap_thenShouldBePresentAndBeRoot() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        assertEquals(1, maxHeap.size());
        assertEquals(10, maxHeap.peek());
    }

    @Test
    void givenMultipleElements_whenAddedToMinHeap_thenShouldMaintainHeapProperty() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
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
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);

        assertEquals(3, maxHeap.size());
        assertEquals(30, maxHeap.peek());
        assertTrue(maxHeap.contains(20));
        assertFalse(maxHeap.contains(5));
    }

    @Test
    void givenDuplicateElement_whenAddedToMinHeap_thenShouldBeAdded() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(10);
        assertTrue(minHeap.add(10)); // Now allows duplicates
        assertEquals(2, minHeap.size());
    }

    @Test
    void givenDuplicateElement_whenAddedToMaxHeap_thenShouldBeAdded() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        assertTrue(maxHeap.add(10)); // Now allows duplicates
        assertEquals(2, maxHeap.size());
    }

    @Test
    void givenMinHeapWithElements_whenPolled_thenShouldReturnAndRemoveMinElement() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(10);

        assertEquals(10, minHeap.poll());
        assertEquals(2, minHeap.size());
        assertEquals(20, minHeap.peek());
    }

    @Test
    void givenMaxHeapWithElements_whenPolled_thenShouldReturnAndRemoveMaxElement() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);

        assertEquals(30, maxHeap.poll());
        assertEquals(2, maxHeap.size());
        assertEquals(20, maxHeap.peek());
    }

    @Test
    void givenMinHeapWithElements_whenRemovedSpecificElement_thenShouldMaintainHeapProperty() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(30);
        minHeap.add(20);
        minHeap.add(10);
        minHeap.add(40);
        minHeap.add(20); // Adding a duplicate element

        assertTrue(minHeap.remove(20)); // Should remove one occurrence of 20
        assertEquals(4, minHeap.size());
        assertEquals(10, minHeap.peek());
        assertTrue(minHeap.contains(20)); // 20 should still be present due to duplication
        assertFalse(minHeap.remove(100)); // Non-existent element
    }

    @Test
    void givenMaxHeapWithElements_whenRemovedSpecificElement_thenShouldMaintainHeapProperty() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.add(30);
        maxHeap.add(40);
        maxHeap.add(20); // Adding a duplicate element

        assertTrue(maxHeap.remove(20)); // Should remove one occurrence of 20
        assertEquals(4, maxHeap.size());
        assertEquals(40, maxHeap.peek());
        assertTrue(maxHeap.contains(20)); // 20 should still be present due to duplication
        assertFalse(maxHeap.remove(100)); // Non-existent element
    }

    @Test
    void givenMinHeapWithElements_whenIteratedInOrder_thenShouldReturnElementsInHeapOrder() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
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
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(10);
        minHeap.add(20);
        minHeap.clear();
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenMaxHeapWithElements_whenCleared_thenShouldBeEmpty() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        maxHeap.add(20);
        maxHeap.clear();
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenMinHeapWithOneElement_whenRemoved_thenShouldBeEmpty() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(10);
        minHeap.remove(10);
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenMaxHeapWithOneElement_whenRemoved_thenShouldBeEmpty() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        maxHeap.remove(10);
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.size());
    }

    @Test
    void givenLargeNumberOfElements_whenAddedToMinHeap_thenShouldHandleCorrectly() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);

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
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);

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
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        for (int i = 0; i < 10000; i++) {
            minHeap.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            assertTrue(minHeap.contains(i));
            assertTrue(minHeap.remove(i));
        }
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.size());
    }

    @Test
    void givenLargeNumberOfElements_whenRemovedFromMaxHeap_thenShouldHandleCorrectly() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
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
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(10);
        assertFalse(minHeap.remove(20));
        assertEquals(1, minHeap.size());
    }

    @Test
    void givenMaxHeap_whenRemoveNonExistentElement_thenShouldReturnFalse() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        assertFalse(maxHeap.remove(20));
        assertEquals(1, maxHeap.size());
    }

    @Test
    void givenMinHeap_whenPollCalledOnEmptyHeap_thenShouldReturnNull() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        assertNull(minHeap.poll());
    }

    @Test
    void givenMaxHeap_whenPollCalledOnEmptyHeap_thenShouldReturnNull() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        assertNull(maxHeap.poll());
    }

    @Test
    void givenMinHeap_whenContainsCalled_thenShouldFindCorrectElement() {
        BinaryHeapFastRemove<Integer> minHeap = BinaryHeapFastRemove.createMinHeap(10);
        minHeap.add(10);
        minHeap.add(20);
        assertTrue(minHeap.contains(10));
        assertFalse(minHeap.contains(30));
    }

    @Test
    void givenMaxHeap_whenContainsCalled_thenShouldFindCorrectElement() {
        BinaryHeapFastRemove<Integer> maxHeap = BinaryHeapFastRemove.createMaxHeap(10);
        maxHeap.add(10);
        maxHeap.add(20);
        assertTrue(maxHeap.contains(20));
        assertFalse(maxHeap.contains(5));
    }
}
