package com.marcinseweryn.algorithms.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.IntStream;

/**
 * A simple thread pool implementation using a blocking queue to store tasks
 * and a fixed number of worker threads to execute the tasks.
 */
public class ThreadPool {

    private static class WorkerThread implements Runnable {
        private Thread thread;
        private BlockingQueue<Runnable> taskQueue;
        private boolean isStopped;

        public WorkerThread(BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
        }

        /**
         * Runs the worker thread, taking tasks from the task queue and
         * executing them until the thread is stopped.
         */
        @Override
        public void run() {
            this.thread = Thread.currentThread();
            while (!isStopped) {
                try {
                    taskQueue.take().run();
                } catch (InterruptedException e) {
                    System.out.println(
                            Thread.currentThread().getName() + " interrupted");
                }
            }
        }

        /**
         * Stops the worker thread by setting the isStopped flag and
         * interrupting the thread.
         */
        public synchronized void stop() {
            isStopped = true;

            // This makes sure that a thread blocked in a wait() call inside
            // the taskQueue.take() call breaks out of the wait() call,
            // and leaves the dequeue() method call with an InterruptedException
            this.thread.interrupt();
        }

        /**
         * Returns true if the worker thread is stopped, false otherwise.
         */
        public synchronized boolean isStopped() {
            return isStopped;
        }
    }

    private BlockingQueue<Runnable> taskQueue;
    private List<WorkerThread> workerThreads = new ArrayList<>();
    private boolean isStopped = false;

    /**
     * Creates a new thread pool with the specified number of worker threads
     * and maximum task queue size.
     *
     * @param numThreads   the number of worker threads to create
     * @param maxQueueSize the maximum number of tasks that can be stored in
     *                     the task queue
     */
    public ThreadPool(int numThreads, int maxQueueSize) {
        taskQueue = new ArrayBlockingQueue<>(maxQueueSize);

        IntStream.range(0, numThreads)
                .mapToObj(i -> new WorkerThread(taskQueue))
                .forEach(workerThreads::add);

        workerThreads.forEach(e -> new Thread(e).start());
    }

    /**
     * Adds a task to the task queue for execution by a worker thread.
     *
     * @param task the task to execute
     * @throws IllegalStateException if the thread pool has been stopped
     */
    public synchronized void execute(Runnable task) {
        if (this.isStopped) throw
                new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.offer(task);
    }

    /**
     * Stops the thread pool by stopping all worker threads and clearing the
     * task queue.
     */
    public synchronized void stop() {
        while (!this.taskQueue.isEmpty()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isStopped = true;
        workerThreads.forEach(WorkerThread::stop);
    }
}

