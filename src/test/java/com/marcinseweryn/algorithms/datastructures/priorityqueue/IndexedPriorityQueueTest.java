package com.marcinseweryn.algorithms.datastructures.priorityqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IndexedPriorityQueueTest {

    private IndexedPriorityQueue<Integer> pq;

    @BeforeEach
    void setUp() {
        pq = new IndexedPriorityQueue<>(2, 10);
        pq.insert(0, 10);
        pq.insert(1, 5);
        pq.insert(2, 20);
        pq.insert(3, 3);
        pq.insert(4, 15);
    }


    @Test
    void testSize() {
        assertEquals(5, pq.size());
        pq.delete(0);
        assertEquals(4, pq.size());
        pq.delete(1);
        pq.delete(2);
        assertEquals(2, pq.size());
        pq.delete(3);
        pq.delete(4);
        assertEquals(0, pq.size());
    }

    @Test
    void testIsEmpty() {
        assertFalse(pq.isEmpty());
        pq.delete(0);
        assertFalse(pq.isEmpty());
        pq.delete(1);
        pq.delete(2);
        assertFalse(pq.isEmpty());
        pq.delete(3);
        pq.delete(4);
        assertTrue(pq.isEmpty());
    }


    @Test
    void testContains() {
        assertTrue(pq.contains(0));
        assertTrue(pq.contains(1));
        assertTrue(pq.contains(2));
        assertTrue(pq.contains(3));
        assertTrue(pq.contains(4));
        pq.delete(0);
        assertFalse(pq.contains(0));
        pq.delete(1);
        assertFalse(pq.contains(1));
        assertTrue(pq.contains(2));
        pq.delete(3);
        assertFalse(pq.contains(3));
        assertTrue(pq.contains(4));
    }


    @Test
    void testPeekMinKeyIndex() {
        assertEquals(3, pq.peekMinElementIndex());
        pq.delete(3);
        assertEquals(1, pq.peekMinElementIndex());
        pq.delete(1);
        assertEquals(0, pq.peekMinElementIndex());
        pq.delete(4);
        assertEquals(0, pq.peekMinElementIndex());
    }

    @Test
    void testPollMinKeyIndex() {
        assertEquals(3, pq.pollMinElementIndex());
        assertEquals(1, pq.pollMinElementIndex());
        assertEquals(0, pq.pollMinElementIndex());
        assertEquals(4, pq.pollMinElementIndex());
        assertEquals(2, pq.pollMinElementIndex());
        assertTrue(pq.isEmpty());
    }


    @Test
    void testPeekMinValue() {
        assertEquals(3, pq.peekMinElementIndex());
        assertEquals(3, pq.peekMinValue());
        pq.delete(3);
        assertEquals(1, pq.peekMinElementIndex());
        assertEquals(5, pq.peekMinValue());
        pq.delete(1);
        assertEquals(0, pq.peekMinElementIndex());
        assertEquals(10, pq.peekMinValue());
        pq.delete(4);
        assertEquals(0, pq.peekMinElementIndex());
        assertEquals(10, pq.peekMinValue());
    }

    @Test
    void testDelete() {
        pq.delete(0);
        assertEquals(4, pq.size());
        assertFalse(pq.contains(0));
        assertEquals(3, pq.peekMinElementIndex());
        pq.delete(1);
        pq.delete(2);
        pq.delete(3);
        pq.delete(4);
        assertTrue(pq.isEmpty());
    }

    @Test
    void testInsertWithDuplicateIndicesThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            pq.insert(2, 30);
        });
    }

    @Test
    void testPeekMinValueWithEmptyQueueThrowsException() {
        pq.delete(0);
        pq.delete(1);
        pq.delete(2);
        pq.delete(3);
        pq.delete(4);
        assertThrows(NoSuchElementException.class, () -> {
            pq.peekMinValue();
        });
    }

    @Test
    void testPollMinKeyIndexWithEmptyQueueThrowsException() {
        pq.delete(0);
        pq.delete(1);
        pq.delete(2);
        pq.delete(3);
        pq.delete(4);
        assertThrows(NoSuchElementException.class, () -> {
            pq.pollMinElementIndex();
        });
    }

    @Test
    void testDeleteWithNonexistentIndexThrowsException() {
        assertThrows(NoSuchElementException.class, () -> {
            pq.delete(5);
        });
    }

    @Test
    void testUpdate() {
        pq.update(3, 2);
        assertEquals(3, pq.peekMinElementIndex());
        assertEquals(2, pq.peekMinValue());
        pq.update(4, 1);
        assertEquals(4, pq.peekMinElementIndex());
        assertEquals(1, pq.peekMinValue());
        pq.update(0, 12);
        assertEquals(4, pq.peekMinElementIndex());
        assertEquals(1, pq.peekMinValue());
        pq.delete(4);
        pq.update(0, -1);
        assertEquals(0, pq.peekMinElementIndex());
        assertEquals(-1, pq.peekMinValue());
        pq.update(0, 30);
        assertEquals(3, pq.peekMinElementIndex());
        assertEquals(2, pq.peekMinValue());
    }
}