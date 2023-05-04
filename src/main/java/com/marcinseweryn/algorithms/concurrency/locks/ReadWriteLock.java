package com.marcinseweryn.algorithms.concurrency.locks;

import java.util.HashMap;
import java.util.Map;

/**
 * Reentrant readwrite lock implementation, Write to Read Reentrance, Read to
 * Write Reentrance
 * Assumptions:
 * Write request are more important than read-requests. - if reads are what
 * happens most often, and we did not up-prioritize writes, starvation could
 * occur.Threads requesting write access would be blocked until all readers
 * had unlocked the ReadWriteLock. If new threads were constantly granted
 * read access the thread waiting for write access would remain blocked
 * indefinately, resulting in starvation. Therefore a thread can only be
 * granted read access if no thread has currently locked the ReadWriteLock
 * for writing, or requested it locked for writing.
 * <p>
 * Read Access   	If no threads are writing, and no threads have requested
 * write access.
 * Write Access   	If no threads are reading or writing.
 * <p>
 * A thread is granted read reentrance if it can get read access (no writers
 * or write requests), or if it already has read access (regardless of write
 * requests).
 * <p>
 * Solve:
 * Reentrance Lockout
 * <p>
 * <p>
 * Why notifyAll instead of Notify:
 * Inside the ReadWriteLock there are threads waiting for read access, and
 * threads waiting for write access. If a thread awakened by notify() was a
 * read access thread, it would be put back to waiting because there are
 * threads waiting for write access. However, none of the threads awaiting
 * write access are awakened, so nothing more happens. No threads gain
 * neither read nor write access. By calling noftifyAll() all waiting threads
 * are awakened and check if they can get the desired access.
 * <p>
 * Calling notifyAll() also has another advantage. If multiple threads are
 * waiting for read access and none for write access, and unlockWrite() is
 * called, all threads waiting for read access are granted read access at
 * once - not one by one.
 */
public class ReadWriteLock {

    private final Map<Thread, Integer> readingThreads = new HashMap<>();
    private int writers;
    private int writeRequests;
    private Thread writingThread;

    public synchronized void lockRead() throws InterruptedException {
        Thread callingThread = Thread.currentThread();
        while (!canGrantReadAccess(callingThread)) {
            wait();
        }
        Integer x = readingThreads.computeIfPresent(
                callingThread,
                (k, v) -> v + 1
        );
        if (x == null) {
            readingThreads.put(callingThread, 1);
        }
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        Integer reads = readingThreads.get(callingThread);
        if(reads == null) throw new IllegalMonitorStateException();
        if(reads == 1) {
            readingThreads.remove(callingThread);
        } else {
            readingThreads.compute(callingThread, (k, v) -> v - 1);
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {

        // responsible for blocking read threads
        writeRequests++;
        Thread callingThread = Thread.currentThread();
        while (!canGrantWriteAccess(callingThread)) {
            wait();
        }
        writeRequests--;
        writers++;
        writingThread = callingThread;
    }

    public synchronized void unlockWrite() {
        writers--;
        if(writers == 0) {
            writingThread = null;
        }
        notifyAll();
    }

    // Helper methods
    private boolean canGrantReadAccess(Thread callingThread) {
        if (writers > 0) return false;

        //  if the calling thread already has read access this takes
        //  precedence over any writeRequests, reentrant
        if (isReader(callingThread)) return true;
        if (writeRequests > 0) return false;

        // No write requests, already not added into map
        return true;
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if(hasReaders()) return false;
        if(writingThread == null) return true;

        // Reentrant
        return isWriter(callingThread);
    }

    private boolean hasReaders() {
        return readingThreads.size() > 0;
    }

    private boolean isWriter(Thread callingThread) {
        return writingThread == callingThread;
    }

    private boolean isReader(Thread callingThread) {
        return readingThreads.containsKey(callingThread);
    }

}
