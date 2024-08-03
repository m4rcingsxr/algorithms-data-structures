package com.marcinseweryn.algorithms.datastructures.queue;

import com.marcinseweryn.algorithms.datastructures.linkedlist.DoublyLinkedList;

import java.util.Iterator;

/**
 * LinkedListQueue implements a queue using a DoublyLinkedList.
 * This implementation provides a dynamic and flexible queue with efficient enqueue and dequeue operations.
 *
 * @param <T> the type of elements held in this queue
 */
public class LinkedListQueue<T> implements Queue<T> {

    private final DoublyLinkedList<T> linkedList;

    /**
     * Constructs a new LinkedListQueue.
     */
    public LinkedListQueue() {
        this.linkedList = new DoublyLinkedList<>();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return this.linkedList.size();
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public T peek() {
        return this.linkedList.peekFirst();
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be added
     */
    @Override
    public void enqueue(T element) {
        this.linkedList.addLast(element);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public T dequeue() {
        return this.linkedList.removeFirst();
    }

    /**
     * Returns an iterator over the elements in this queue.
     *
     * @return an iterator over the elements in this queue
     */
    @Override
    public Iterator<T> iterator() {
        return this.linkedList.iterator();
    }
}
