package com.marcinseweryn.algorithms.datastructures.priorityqueue;


import java.util.*;

public class PriorityQueue<T extends Comparable<T>> {

    private int heapCapacity = 0;
    private int heapSize = 0;

    // Represent elements inside the heap
    private List<T> heap = null;

    // List to track the elements inside the heap
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    public PriorityQueue() {
        this(1);
    }

    public PriorityQueue(int sz) {
        heap = new ArrayList<>(sz);
    }

    public PriorityQueue(T[] elements) {
        heapSize = heapCapacity = elements.length;
        heap = new ArrayList<>(heapCapacity);

        for (int i = 0; i < heapSize; i++) {
            putMap(elements[i], i);
            heap.add(elements[i]);
        }

        for (int i = Math.max(0, (heapSize / 2) - 1); i >= 0; i--) {
            heapify(i);
        }
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void clear() {
        for (int i = 0; i < heapCapacity; i++) {
            heap.set(i, null);
            heapSize = 0;
            map.clear();
        }
    }

    public int size() {
        return heapSize;
    }

    // Returns the value of the element with the lowest priority in this priority
    // queue. If the priority queue is empty null is returned
    public T peek() {
        if(isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    // O(1)
    public boolean contains(T elem) {
        if(elem == null) return false;
        return map.containsKey(elem);

        /*
            Linear scan to check O(N)
            for i = 0 i < heapSize i ++ :
                if heap.get(i).equals(elem):
                    return true
            return false
         */
    }

    public void add(T elem) {
        if(elem == null) throw new IllegalArgumentException();

        if (heapSize < heapCapacity) {
            heap.set(heapSize, elem);
        } else {
            heap.add(elem);
            heapCapacity++;
        }

        putMap(elem, heapSize);
        heapify(heapSize);
        heapSize++;
    }

    private boolean less(int i, int j) {

    }

    private T removeAt(int i) {
    }


    private void heapify(int i) {
    }

    private void putMap(T element, int i) {
        this.map.put(element, new TreeSet<>(i));
    }

}
