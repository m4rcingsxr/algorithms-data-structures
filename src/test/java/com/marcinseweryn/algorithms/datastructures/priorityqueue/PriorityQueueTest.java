package com.marcinseweryn.algorithms.datastructures.priorityqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @BeforeEach
    void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    void testIsEmpty() {
        assertTrue(pq.isEmpty());
        pq.add(1);
        assertFalse(pq.isEmpty());
    }

    @Test
    void testClear() {
        pq.add(1);
        pq.add(2);
        pq.add(3);
        pq.clear();
        assertTrue(pq.isEmpty());
    }

    @Test
    void testSize() {
        assertEquals(0, pq.size());
        pq.add(1);
        pq.add(2);
        pq.add(3);
        assertEquals(3, pq.size());
    }

    @Test
    void testPeek() {
        assertNull(pq.peek());
        pq.add(2);
        assertEquals(2, pq.peek());
        pq.add(1);
        assertEquals(1, pq.peek());
        pq.add(3);
        assertEquals(1, pq.peek());
    }

    @Test
    void testPoll() {
        assertNull(pq.poll());
        pq.add(2);
        pq.add(1);
        pq.add(3);
        assertEquals(1, pq.poll());
        assertEquals(2, pq.poll());
        assertEquals(3, pq.poll());
        assertTrue(pq.isEmpty());
    }

    @Test
    void testContains() {
        assertFalse(pq.contains(1));
        pq.add(1);
        assertTrue(pq.contains(1));
        pq.add(2);
        pq.add(3);
        assertTrue(pq.contains(2));
        assertFalse(pq.contains(4));
    }

    @Test
    void testAdd() {
        pq.add(1);
        pq.add(3);
        pq.add(2);
        assertEquals(1, pq.poll());
        assertEquals(2, pq.poll());
        assertEquals(3, pq.poll());
    }

    @Test
    void testConstructorWithCollection() {
        List<Integer> list = Arrays.asList(3, 1, 2);
        pq = new PriorityQueue<>(list);
        assertEquals(1, pq.poll());
        assertEquals(2, pq.poll());
        assertEquals(3, pq.poll());
    }

    @Test
    void testConstructorWithArray() {
        Integer[] arr = {3, 1, 2};
        pq = new PriorityQueue<>(arr);
        assertEquals(1, pq.poll());
        assertEquals(2, pq.poll());
        assertEquals(3, pq.poll());
    }

    @Test
    void testRandomizedAddAndPoll() {
        Random random = new Random();
        int n = 10000;
        for (int i = 0; i < n; i++) {
            pq.add(random.nextInt());
        }
        assertEquals(n, pq.size());
        int prev = pq.poll();
        for (int i = 1; i < n; i++) {
            int curr = pq.poll();
            assertTrue(curr >= prev);
            prev = curr;
        }
        assertTrue(pq.isEmpty());
    }
}