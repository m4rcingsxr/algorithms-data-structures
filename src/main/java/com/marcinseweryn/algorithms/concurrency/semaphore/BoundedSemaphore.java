package com.marcinseweryn.algorithms.concurrency.semaphore;

/**
 * A bounded semaphore is a type of semaphore that is bounded to a certain
 * limit.
 * It allows multiple threads to access a resource concurrently, up to a
 * certain limit.
 */
public class BoundedSemaphore {
    private int signals = 0;
    private int upperBound = 0;

    /**
     * Constructs a new BoundedSemaphore with the specified upper bound.
     *
     * @param upperBound the maximum number of permits that can be acquired
     *                   by the semaphore
     */
    public BoundedSemaphore(int upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * Acquires a permit from this semaphore, blocking until one is available.
     *
     * @throws InterruptedException if the current thread is interrupted
     *                              while waiting
     */
    public synchronized void acquire() throws InterruptedException {
        while (this.signals == upperBound) {
            wait();
        }
        this.signals++;
        this.notify();
    }

    /**
     * Releases a permit, allowing one waiting thread to acquire it.
     *
     * @throws InterruptedException if the current thread is interrupted
     *                              while waiting
     */
    public synchronized void release() throws InterruptedException {
        while (this.signals == 0) {
            wait();
        }
        this.signals--;
        this.notify();
    }
}