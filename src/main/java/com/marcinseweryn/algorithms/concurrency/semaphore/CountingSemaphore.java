package com.marcinseweryn.algorithms.concurrency.semaphore;

/**
 * A counting semaphore is a synchronization primitive that can be used to
 * limit the number of threads accessing a resource or a section of code at
 * the same time. This implementation keeps track of the number of signals
 * acquired and released, and blocks threads that try to acquire a signal
 * when there are no more available.
 */
public class CountingSemaphore {
    private int countSignals = 0;

    /**
     * Acquires a signal from the semaphore. If no signals are available, the
     * calling thread will be blocked until a signal becomes available.
     *
     * @throws InterruptedException if the thread is interrupted while
     *                              waiting for a signal
     */
    public synchronized void acquire() throws InterruptedException {
        while (countSignals == 0) {
            wait();
        }
        countSignals--;
    }

    /**
     * Releases a signal to the semaphore, making it available for other
     * threads to acquire.
     */
    public synchronized void release() {
        countSignals++;
        notify();
    }
}