package com.marcinseweryn.algorithms.datastructures.queue;

import com.marcinseweryn.algorithms.datastructures.linkedlist.LinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class presents Linked List implementation of Queue.
 */
public class LinkedListQueue<T> implements Iterable<T> {
    LinkedList<T> list = new LinkedList<>();

    /**
     * Construct an empty Queue
     */
    public LinkedListQueue() {
    }

    /**
     * Construct a Queue with specified initial element
     */
    public LinkedListQueue(T element) {
        list.add(element);
    }

    /**
     * Return the number of elements the queue has
     *
     * @return the number of elements the queue has
     */

    public int size() {
        return list.size();
    }

    /**
     * Return {@code true} if this Queue contains no elements
     *
     * @return {@code true} if this Queue contains no elements
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Retrieves, but does not remove, the head of this queue.
     *
     * @return the head of this Queue.
     * @throws NoSuchElementException if Queue contains no element
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.peekFirst();
    }

    /**
     * Inserts the specified element into this Queue
     *
     * @param element to insert
     */
    public void enqueue(T element) {
        list.addLast(element);
    }

    /**
     * Retrieves and removes the head of this Queue
     *
     * @return the head of this Queue
     * @throws NoSuchElementException if Queue contains no element
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return list.removeFirst();
    }

    /**
     * Return an iterator over collection
     *
     * @return an iterator over collection
     */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

}
