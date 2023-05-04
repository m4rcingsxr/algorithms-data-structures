package com.marcinseweryn.algorithms.concurrency.locks;

/**
 * A simple Lock class that provides a lock and unlock mechanism.
 * <p>
 * This class allows threads to acquire and release a lock. Only one thread
 * can hold the lock at a time. If a thread tries to acquire the lock while
 * it is already held by another thread, the thread will be blocked until the
 * lock is released.
 */
public class Lock {
    private boolean isLocked;
    private Thread currentThread;

    /**
     * Acquires the lock. If the lock is already held by another thread, this
     * method will block until the lock is released.
     *
     * @throws InterruptedException if the thread is interrupted while
     *                              waiting for the lock.
     */
    public synchronized void lock() throws InterruptedException {

        // 1st always false, wait until the lock is released
        while (isLocked) {
            this.wait();
        }
        isLocked = true;
        currentThread = Thread.currentThread();
    }


    /**
     * Releases the lock.
     *
     * @throws IllegalMonitorStateException if the calling thread does not
     * hold the lock.
     */
    public synchronized void unlock() {
        if (this.currentThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    Thread.currentThread().getName() + " has not acquired " +
                            "this lock"
            );
        }
        isLocked = false;
        currentThread = null;
        this.notifyAll();
    }
}
