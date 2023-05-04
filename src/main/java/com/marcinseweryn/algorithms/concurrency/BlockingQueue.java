package com.marcinseweryn.algorithms.concurrency;

import java.util.ArrayDeque;
import java.util.Queue;


/**
 * A BlockingQueue is a queue that blocks when trying to dequeue from an
 * empty queue or enqueue to a full queue.
 *
 * @param <T> The type of elements in the queue
 */
public class BlockingQueue<T> {
    private final Queue<T> queue = new ArrayDeque<>();
    private final int capacity;

    /**
     * Constructs a new BlockingQueue with the specified capacity.
     *
     * @param capacity the maximum capacity of the queue
     */
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Inserts the specified element at the tail of this queue, blocking
     * until space is available if the queue is full.
     *
     * @param element the element to add
     * @throws InterruptedException if the current thread is interrupted
     * while waiting
     */
    public synchronized void enqueue(T element) throws InterruptedException {
        while (this.queue.size() == this.capacity) {
            wait();
        }
        this.queue.add(element);

        // Notify dequeue that elements are available
        if (this.queue.size() == 1) {
            notifyAll();
        }
    }

    /**
     * Retrieves and removes the head of this queue, blocking until an
     * element is available if the queue is empty.
     *
     * @return the head of the queue
     * @throws InterruptedException if the current thread is interrupted
     * while waiting
     */
    public synchronized T dequeue() throws InterruptedException {
        while (this.queue.isEmpty()) {
            wait();
        }

        // Notify enqueue that it can insert new element
        if (this.queue.size() == this.capacity) {
            notifyAll();
        }

        return this.queue.remove();
    }
}
