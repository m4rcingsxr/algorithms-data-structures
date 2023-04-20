package com.marcinseweryn.algorithms.datastructures.tree.binary;

import java.util.Arrays;

/**
 * Class represent an implementation of the BinaryHeap (Priority Queue)
 * Any data that's implement comparable interface is allowed.
 *
 * @author Marcin Seweryn
 * @version 1.0
 * @see Comparable
 */
public class BinaryHeap<T extends Comparable<T>> {
    private Object[] arr;
    private int size = 0;

    /**
     * Compile time constant to specify MAX
     * Binary Heap
     */
    public static final String MAX_HEAP = "MAX_HEAP";

    /**
     * Compile time constant to specify MIN
     * Binary Heap
     */
    public static final String MIN_HEAP = "MIN_HEAP";

    /**
     * Create empty Binary Heap with initial capacity
     * to avoid frequently executing of costly resizing
     * method
     *
     * @param capacity initial capacity of Binary Heap
     */
    public BinaryHeap(int capacity) {
        arr = new Object[capacity];
    }

    public int size() {
        return size;
    }

    /**
     * Return {@code true} if this Binary Heap is empty
     *
     * @return {@code true} if this Binary Heap is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Insert specified element to the BinaryHeap
     *
     * @param val to be inserted
     */
    public void add(T val, String heapType) {
        if (size + 1 == arr.length) {
            resize();
        }
        arr[++size] = val;
        heapifyBottomToTop(size, heapType);
    }

    @SuppressWarnings("unchecked")
    private void heapifyBottomToTop(int index, String heapType) {
        int parent = index / 2;

        //Base case
        if (index <= 1) {
            return;
        }
        if (heapType.equals(BinaryHeap.MIN_HEAP) && ((T) arr[index]).compareTo(
                (T) arr[parent]) < 0) {
            T temp = (T) arr[index];
            arr[index] = arr[parent];
            arr[parent] = temp;
            heapifyBottomToTop(parent, BinaryHeap.MIN_HEAP);
        } else if (heapType.equals(
                BinaryHeap.MAX_HEAP) && ((T) arr[index]).compareTo(
                (T) arr[parent]) > 0) {
            T temp = (T) arr[index];
            arr[index] = arr[parent];
            arr[parent] = temp;
            heapifyBottomToTop(parent, BinaryHeap.MAX_HEAP);
        }
    }

    private void resize() {
        arr = Arrays.copyOf(arr, arr.length * 2);
    }

    /**
     * Return min or max of the Binary Heap
     *
     * @param heapType
     * @return head of the Binary Heap (min or max)
     */
    @SuppressWarnings("unchecked")
    public T extractHead(String heapType) {
        if (isEmpty()) {
            throw new IllegalStateException("Binary Heap is empty");
        }
        T temp = (T) arr[1];
        arr[1] = arr[size];
        arr[size] = null;
        size--;
        heapifyTopToBottom(1, heapType);
        return temp;
    }

    @SuppressWarnings("unchecked")
    private void heapifyTopToBottom(int index, String heapType) {
        int left = index * 2;
        int right = index * 2 + 1;
        if (left > size) {
            return;
        }
        int children = index;
        if (heapType.equals(BinaryHeap.MIN_HEAP)) {

            // 1 children case
            if (size == left) {
                children = left;

                // 2 children case
            } else {
                children = ((T) (arr[left])).compareTo((T) (arr[right])) < 0 ?
                        left : right;
            }
            if (((T) arr[index]).compareTo((T) arr[children]) > 0) {
                T temp = (T) arr[index];
                arr[index] = arr[children];
                arr[children] = temp;
            }
        } else if (heapType.equals(BinaryHeap.MAX_HEAP)) {

            // 1 children case
            if (size == left) {
                children = left;

                // 2 children case
            } else {
                children = ((T) arr[left]).compareTo((T) arr[right]) > 0 ?
                        left : right;
            }
            if (((T) arr[index]).compareTo((T) arr[children]) < 0) {
                T temp = (T) arr[index];
                arr[index] = arr[children];
                arr[children] = temp;
            }
        }
        heapifyTopToBottom(children, heapType);
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }
}
