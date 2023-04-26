package com.marcinseweryn.algorithms.datastructures.linkedlist.algorithms;

/**
 * The FloydCycleDetection class provides methods for detecting a cycle in a
 * linked list using the Floyd's cycle-finding algorithm,
 * <p>
 * and finding the middle element of a linked list.
 */
public class FloydCycleDetection {

    /**
     * The Node class represents a node in a linked list.
     */
    public static class Node {
        Node next;
        int val;

        /**
         * Constructs a node with the specified value and next node.
         *
         * @param next the next node in the linked list
         * @param val  the value of the node
         */
        public Node(Node next, int val) {
            this.next = next;
            this.val = val;
        }
    }

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private FloydCycleDetection() {
    }

    /**
     * Detects if a cycle exists in a linked list using Floyd's cycle-finding
     * algorithm. Also, this algorithm can find the middle element of a
     * linked list.
     *
     * @param head the head of the linked list to check for a cycle
     * @return true if a cycle exists, false otherwise
     */
    public static boolean detectCycle(Node head) {
        Node tortoise = head;
        Node hare = head;

        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return true;
            }
        }
        return false;
    }

}