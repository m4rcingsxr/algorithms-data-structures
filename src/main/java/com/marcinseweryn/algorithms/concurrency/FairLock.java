package com.marcinseweryn.algorithms.concurrency;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * A fair lock implementation that ensures threads acquire the lock in the
 * order they request it.
 */
public class FairLock {

    /**
     * A helper object used to manage threads waiting for the lock.
     */
    private static class WaitObject {
        private boolean isNotified;

        /**
         * Waits until this object is notified.
         *
         * @throws InterruptedException if the thread is interrupted while
         * waiting.
         */
        public synchronized void doWait() throws InterruptedException {
            while (!isNotified) {
                this.wait();
            }
            this.isNotified = false;
        }

        /**
         * Notifies this object.
         */
        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }
    }

    private boolean isLocked;
    private Thread ownerThread;
    private final Queue<WaitObject> waitingThreads = new ArrayDeque<>();

    /**
     * Acquires the lock.
     *
     * @throws InterruptedException if the thread is interrupted while
     * waiting to acquire the lock.
     */
    public void lock() throws InterruptedException {
        WaitObject waitObject = new WaitObject();
        boolean isWaitingThread = true;
        synchronized (this) {
            waitingThreads.add(waitObject);
        }

        while (isWaitingThread) {
            synchronized (this) {
                isWaitingThread =
                        isLocked || waitingThreads.peek() != waitObject;
                if (!isWaitingThread) {
                    isLocked = true;
                    waitingThreads.remove(waitObject);
                    ownerThread = Thread.currentThread();
                    return;
                }
            }
        }

        try {
            waitObject.doWait();
        } catch (InterruptedException e) {
            synchronized (this) {
                waitingThreads.remove(waitObject);
                throw e;
            }
        }
    }

    /**
     * Releases the lock.
     *
     * @throws IllegalMonitorStateException if the current thread does not
     * hold the lock.
     */
    public synchronized void unlock() {
        if (ownerThread != Thread.currentThread()) {
            throw new IllegalMonitorStateException(
                    Thread.currentThread().getName() + " has not acquired " +
                            "this lock"
            );
        }
        isLocked = false;
        ownerThread = null;
        if (!waitingThreads.isEmpty()) {
            waitingThreads.peek().doNotify();
        }
    }
}