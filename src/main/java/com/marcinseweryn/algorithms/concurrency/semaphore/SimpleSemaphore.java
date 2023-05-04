package com.marcinseweryn.algorithms.concurrency.semaphore;

/**
 * SimpleSemaphore is a basic implementation of a semaphore, a
 * synchronization tool used to control access to a shared resource.
 * The semaphore uses the wait-notify mechanism to coordinate the access to
 * the shared resource. This class is designed to avoid missing signals,
 * which can occur when a thread calls the notify method before another
 * thread has called the wait method. To prevent this, the semaphore uses a
 * boolean flag to store the state of the semaphore and ensure that a signal
 * is not missed.
 */
public class SimpleSemaphore {
    private boolean isAvailable;

    /**
     * Causes the current thread to wait until the semaphore is available.
     * If the semaphore is not available, the thread is blocked until it
     * becomes available.
     *
     * @throws InterruptedException if the current thread is interrupted
     * while waiting
     */
    public synchronized void acquire() throws InterruptedException {
        while (!isAvailable) {
            wait();
        }
        isAvailable = false;
    }

    /**
     * Releases the semaphore, making it available for other threads to acquire.
     * Any threads that are waiting for the semaphore to become available
     * will be unblocked.
     */
    public synchronized void release() {
        isAvailable = true;
        notify();
    }
}