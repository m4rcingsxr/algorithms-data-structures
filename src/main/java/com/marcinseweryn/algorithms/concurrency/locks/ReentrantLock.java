package com.marcinseweryn.algorithms.concurrency.locks;

/**
 * A reentrant mutual exclusion lock implementation. Allows a thread to
 * acquire the same lock multiple times without
 * deadlocking.
 */
public class ReentrantLock {

    /**
     * Indicates if the lock is currently held by any thread.
     */
    private boolean isLocked;

    /**
     * The thread that currently holds the lock, or {@code null} if the lock
     * is not held by any thread.
     */
    private Thread lockedBy;

    /**
     * The number of times the lock has been acquired by the current holder.
     */
    private int lockCount;

    /**
     * Acquires the lock. If the lock is not available, the calling thread
     * will wait until it is.
     *
     * @throws InterruptedException if the thread is interrupted while
     * waiting for the lock
     */
    public synchronized void lock() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (isLocked && lockedBy != callingThread) {
            wait();
        }
        isLocked = true;
        lockCount++;
        lockedBy = callingThread;
    }

    /**
     * Releases the lock. If the current thread holds the lock multiple
     * times, it must call unlock the same number of
     * times before the lock is released for other threads to acquire.
     *
     * @throws IllegalMonitorStateException if the current thread does not
     * hold the lock
     */
    public synchronized void unlock() {
        if (Thread.currentThread() == this.lockedBy) {
            lockCount--;

            if (lockCount == 0) {
                lockedBy = null;
                isLocked = false;
                notifyAll();
            }
        } else {
            throw new IllegalMonitorStateException(
                    Thread.currentThread().getName() + " does not hold the lock"
            );
        }
    }
}