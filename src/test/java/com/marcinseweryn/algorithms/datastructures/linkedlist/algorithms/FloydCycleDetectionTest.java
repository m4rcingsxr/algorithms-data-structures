package com.marcinseweryn.algorithms.datastructures.linkedlist.algorithms;

import com.marcinseweryn.algorithms.datastructures.linkedlist.algorithms.FloydCycleDetection.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FloydCycleDetectionTest {
    private Node head;

    @BeforeEach
    void setUp() {
        // Create a linked list with 6 elements
        head = new Node(null, 1);
        head.next = new Node(null, 2);
        head.next.next = new Node(null, 3);
        head.next.next.next = new Node(null, 4);
        head.next.next.next.next = new Node(null, 5);
        head.next.next.next.next.next = new Node(null, 6);
    }

    @Test
    void testDetectCycle() {
        // Create a cycle in the linked list
        head.next.next.next.next.next = head.next.next;
        assertTrue(FloydCycleDetection.detectCycle(head));

        // Remove the cycle and check again
        head.next.next.next.next.next = null;
        assertFalse(FloydCycleDetection.detectCycle(head));
    }

    @Test
    void testDetectMid() {
        assertEquals(4, detectMid(head));

        // Add one more element to the list and check again
        head.next.next.next.next.next = new Node(null, 7);
        assertEquals(4, detectMid(head));
    }

    // Helper method to find the middle element of a linked list
    private int detectMid(Node head) {
        Node tortoise = head;
        Node hare = head;

        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
        }

        return tortoise.val;
    }
}