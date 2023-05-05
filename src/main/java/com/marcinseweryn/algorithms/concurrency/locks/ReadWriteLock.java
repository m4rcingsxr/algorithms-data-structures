package com.marcinseweryn.algorithms.concurrency.locks;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of a reentrant ReadWriteLock which supports Write to Read
 * Reentrance and Read to
 * Write Reentrance.
 * <p>
 * Assumptions:
 * Write requests are more important than read requests - if reads are what
 * happens most often, and we did not up-prioritize writes,
 * starvation could occur. Threads requesting write access would be blocked
 * until all readers had unlocked the ReadWriteLock. If new threads were
 * constantly
 * granted read access, the thread waiting for write access would remain
 * blocked indefinitely, resulting in starvation. Therefore,
 * a thread can only be granted read access if no thread has currently locked
 * the ReadWriteLock for writing, or requested it locked for writing.
 * <p>
 * Read Access - if no threads are writing, and no threads have requested
 * write access.
 * Write Access - if no threads are reading or writing.
 * <p>
 * A thread is granted read reentrance if it can get read access (no writers
 * or write requests), or if it already has read access
 * (regardless of write requests).
 * <p>
 * This class solves the problem of reentrance lockout.
 * <p>
 * We use notifyAll instead of notify because threads may be waiting for read
 * access or write access. If a thread awakened by notify()
 * was a read access thread, it would be put back to waiting because there
 * are threads waiting for write access. However, none of the
 * threads awaiting write access are awakened, so nothing more happens. No
 * threads gain neither read nor write access. By calling notifyAll(),
 * all waiting threads are awakened and check if they can get the desired
 * access.
 * <p>
 * Calling notifyAll() also has another advantage. If multiple threads are
 * waiting for read access and none for write access, and
 * unlockWrite() is called, all threads waiting for read access are granted
 * read access at once - not one by one.
 */
public class ReadWriteLock {

    private final Map<Thread, Integer> readingThreads = new HashMap<>();
    private int numWriters;
    private int numWriteRequests;
    private Thread writingThread;

    /**
     * Acquires read access to the lock. This method will block until it can
     * get read access.
     * If a thread already has read access, it will be granted reentrance.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        Integer numReads = readingThreads.computeIfPresent(
                callingThread,
                (k, v) -> v + 1
        );
        if (numReads == null) {
            readingThreads.put(callingThread, 1);
        }
    }

    /**
     * Releases read access from the lock.
     */
    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        Integer numReads = readingThreads.get(callingThread);
        if (numReads == null) throw new IllegalMonitorStateException();
        if (numReads == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.compute(callingThread, (k, v) -> v - 1);
        }
        notifyAll();
    }

    /**
     * Acquires write access to the lock. This method will block until it can
     * get write access.
     *
     * @throws InterruptedException if the thread is interrupted while waiting
     */
    public synchronized void lockWrite() throws InterruptedException {
        // A write request is made
        numWriteRequests++;

        Thread callingThread = Thread.currentThread();
        // Wait until the read-write lock can grant write access to the
        // calling thread
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }

        // Once the calling thread is granted write access, decrement the
        // write request count
        numWriteRequests--;
        // Increment the number of active writers
        numWriters++;

        // Set the thread that holds the write lock to be the calling thread
        writingThread = callingThread;
    }

    /**
     * Unlocks the write lock. If no threads hold the write lock after this
     * method
     * call, it will notify all waiting threads.
     */
    public synchronized void unlockWrite() {
        // Decrement the number of active writers
        numWriters--;

        // If no thread holds the write lock, set the writing thread to null
        if (numWriters == 0) {
            writingThread = null;
        }

        // Notify all waiting threads to check if they can acquire the lock
        notifyAll();
    }

    /**
     * Determines if a thread can be granted read access based on the current
     * state of the read-write lock.
     *
     * @param callingThread The thread requesting read access
     * @return True if the calling thread can be granted read access, false
     * otherwise
     */
    private boolean canGrantReadAccess(Thread callingThread) {
        // If there is an active writer, the calling thread cannot be granted
        // read access
        if (numWriters > 0) {
            return false;
        }

        // If the calling thread already has read access, it is granted read
        // access again
        if (readingThreads.containsKey(callingThread)) {
            return true;
        }

        // If there is a write request, the calling thread cannot be granted
        // read access
        if (numWriteRequests > 0) {
            return false;
        }

        // If none of the above conditions are met, the calling thread can be
        // granted read access
        return true;
    }

    /**
     * Determines if a thread can be granted write access based on the
     * current state of the read-write lock.
     *
     * @param callingThread The thread requesting write access
     * @return True if the calling thread can be granted write access, false
     * otherwise
     */
    private boolean canGrantWriteAccess(Thread callingThread) {
        // If there are active readers, the calling thread cannot be granted
        // write access
        if (readingThreads.size() > 0) {
            return false;
        }

        // If there is an active writer and it is not the calling thread, the
        // calling thread cannot be granted write access
        if (writingThread != null && writingThread != callingThread) {
            return false;
        }

        // If none of the above conditions are met, the calling thread can be
        // granted write access
        return true;
    }

}