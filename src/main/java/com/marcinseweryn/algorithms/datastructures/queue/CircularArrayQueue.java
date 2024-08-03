package com.marcinseweryn.algorithms.datastructures.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * CircularArrayQueue implements a queue using a circular array.
 * This allows the queue to efficiently utilize the array space by wrapping around when it
 * reaches the end.
 *
 * @param <T> the type of elements held in this queue
 */
public class CircularArrayQueue<T> implements Queue<T> {

    private T[] queue;
    private int tail;
    private int head;

    /**
     * Constructs a new CircularArrayQueue with the specified maximum size.
     *
     * @param maxSize the maximum size of the queue
     */
    public CircularArrayQueue(int maxSize) {
        this.queue = (T[]) new Object[maxSize];
        // Initialize the head and tail to -1, indicating the queue is empty.
        this.head = this.tail = -1;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        if (this.isEmpty()) {
            return 0;
        } else if (this.tail >= this.head) {
            return this.tail - this.head + 1;
        } else {
            return (this.queue.length - this.head) + this.tail + 1;
        }
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.tail == this.head && this.tail == -1;
    }

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise
     */
    private boolean isFull() {
        return !this.isEmpty() && (this.tail + 1) % this.queue.length == this.head;
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return this.queue[head];
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be added
     * @throws IllegalStateException if the queue is full
     */
    @Override
    public void enqueue(T element) {
        if (this.isFull()) {
            throw new IllegalStateException("Queue is full");
        } else if (this.isEmpty()) {
            this.head = this.tail = 0;  // When the queue is empty, reset both head and tail to 0.
        } else {
            // Move the tail to the next position, wrapping around if necessary.
            this.tail = (this.tail + 1) % this.queue.length;
        }
        this.queue[this.tail] = element;  // Place the element at the tail position.
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws IllegalStateException if the queue is empty
     */
    @Override
    public T dequeue() {
        if (this.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        T temp = this.queue[head];

        // Clear the element at the head position.
        this.queue[this.head] = null;
        if (this.head == this.tail) {
            // If the queue becomes empty, reset both head and tail to -1.
            this.tail = this.head = -1;
        } else {
            // Move the head to the next position, wrapping around if necessary.
            this.head = (this.head + 1) % this.queue.length;
        }
        return temp;
    }

    /**
     * Returns an iterator over the elements in this queue.
     *
     * @return an iterator over the elements in this queue
     */
    @Override
    public Iterator<T> iterator() {
        return new CircularArrayQueueIterator();
    }

    /**
     * CircularArrayQueueIterator provides an iterator for the CircularArrayQueue.
     * It allows traversal of the queue in a FIFO manner.
     */
    class CircularArrayQueueIterator implements Iterator<T> {
        private int currentIndex = head;
        private int elementsVisited = 0;
        private final int totalElements = size();

        @Override
        public boolean hasNext() {
            return elementsVisited < totalElements;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T element = queue[currentIndex];
            currentIndex = (currentIndex + 1) % queue.length;
            elementsVisited++;
            return element;
        }
    }
}
