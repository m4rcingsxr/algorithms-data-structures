package com.marcinseweryn.algorithms.datastructures.queue;

import java.util.NoSuchElementException;

/**
 * Class presents Circular Array implementation of Queue.
 *
 * @author Marcin Seweryn
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public class CircularArrayQueue<T> {
    Object[] arr;
    private int beginning;
    private int top;

    /**
     * Construct an empty Queue with specified maximum capacity
     *
     * @param maxCapacity max numbers of elements that this
     *                    queue is able to contain
     */
    public CircularArrayQueue(int maxCapacity) {
        arr = new Object[maxCapacity];
        beginning = -1;
        top = -1;
    }

    /**
     * Inserts the specified element into this Queue
     *
     * @param element to insert
     * @throws IllegalStateException if the next inserted element
     *                          exceeds capacity
     */
    public void enqueue(T element) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full[capacity(" + arr.length + ")]");
        } else if (isEmpty()) {
            beginning = 0;
            top = 0;
        } else {
            top = (top + 1) % arr.length;
        }
        arr[top] = element;
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
        T temp = (T) arr[beginning];
        arr[beginning] = null;
        if (beginning == top) {
            beginning = -1;
            top = -1;
        } else {
            beginning = (beginning + 1) % arr.length;
        }
        return temp;
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
        return (T) arr[beginning];
    }


    /**
     * Return {@code true} if this Queue contains no elements
     *
     * @return {@code true} if this Queue contains no elements
     */
    public boolean isEmpty() {
        return beginning == -1 && top == -1;
    }

    /**
     * Return {@code true} if the number of items in the
     * queue is equal to the maximum capacity
     *
     * @return {@code true} if the number of items in the
     * queue is equal to the maximum capacity
     */
    public boolean isFull() {
        return (top + 1) % arr.length == beginning;
    }


}
