package com.marcinseweryn.algorithms.datastructures.priorityqueue;

import java.util.*;

public class PriorityQueue<T extends Comparable<T>> {

    // Represent elements inside the heap
    private List<T> heap;

    // List to track the elements inside the heap
    // Get element index in O(1), if element occur
    // few times it does not matter which index we get
    private Map<T, TreeSet<Integer>> map = new HashMap<>();

    // Default initialization with heapList size = 1;
    public PriorityQueue() {
        this(1);
    }

    // Create PQ object with initial size
    public PriorityQueue(int sz) {
        heap = new ArrayList<>(sz);
    }

    // Construct pq object with specified array with elements
    public PriorityQueue(T[] elements) {
        int heapSize = elements.length;
        heap = new ArrayList<>(heapSize);

        for (int i = 0; i < heapSize; i++) {
            mapPut(elements[i], i);
            heap.add(elements[i]);
        }

        // Heapify - starting half way through the heap O(N)
        for (int i = Math.max(0, (heap.size() / 2) - 1); i >= 0; i--) {
            heapifyTopToBottom(i);
        }
    }

    // Construction O(Nlog(N))
    public PriorityQueue(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) {
            add(elem);
        }
    }

    /**
     * Checks whether the heap is empty or not.
     *
     * @return boolean value indicating whether the heap is empty or not.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Clears all elements from the heap.
     */
    public void clear() {
        heap.clear();
        map.clear();
    }

    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap.
     */
    public int size() {
        return heap.size();
    }

    /**
     * Returns the value of the element with the lowest priority in the
     * priority queue. If the priority queue is empty, null is returned.
     *
     * @return the value of the element with the lowest priority in the
     * priority queue. If the priority queue is empty, null is returned.
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    /**
     * Removes and returns the element with the lowest priority in the
     * priority queue.
     *
     * @return the element with the lowest priority in the priority queue.
     */

    public T poll() {
        return removeAt(0);
    }

    /**
     * Checks whether the specified element is present in the priority queue
     * or not.
     *
     * @param elem the element to be searched.
     * @return true if the element is present in the priority queue,
     * otherwise false.
     */
    public boolean contains(T elem) {
        if (elem == null) return false;
        return map.containsKey(elem);

        /*
            Linear scan to check O(N)
            for i = 0 i < heapSize i ++ :
                if heap.get(i).equals(elem):
                    return true
            return false
         */
    }

    /**
     * Inserts an element in the priority queue.
     *
     * @param elem the element to be inserted.
     * @throws IllegalArgumentException if the specified element is null.
     */
    public void add(T elem) {
        if (elem == null) throw new IllegalArgumentException();

        // resize only if we reach capacity
        heap.add(elem);
        int indexOfLastElem = size() - 1;

        mapPut(elem, indexOfLastElem);
        heapifyBottomToTop(indexOfLastElem);
    }

    private void heapifyBottomToTop(int k) {

        // Grab the index of the next parent node (0 based array)
        int parent = (k - 1) / 2;

        // Keep heapify while we have not reached the root
        // and while we're less than our parent.
        while (k > 0 && less(k, parent)) {

            // Exchange k with the parent
            swap(parent, k);
            k = parent;

            // Grab the index of the next parent
            // node WRT to k
            parent = (k - 1) / 2;

        }
    }

    // Tests if the value of node i <= node j
    // This method assumes i & j are valid indices, O(1)
    // Heapify from bottom to top O(log(N))
    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    // Top down node sink, O(log(N))
    private void heapifyTopToBottom(int k) {
        int heapSize = size();

        while (true) {
            int left = 2 * k + 1;
            int right = 2 * k + 2;
            int smallest = left;

            // Find which is smaller left or right
            // If right is smaller set smallest to be right
            if (right < heapSize && less(right, left)) {
                smallest = right;
            }

            // Stop if we're outside the bounds of the tree
            // or stop early if we cannot sink k anymore
            if (left >= heapSize || less(k, smallest)) break;

            // Move down the tree following the smallest node
            swap(smallest, k);
            k = smallest;
        }
    }

    public boolean remove(T element) {

        if (element == null) return false;

        // Linear remove via search, O(n)
        // for(int i = 0; i < heapSize; i ++) {
        //      if(elements.equals(heap.get(i)) {
        //          removeAt(i);
        //          return true;
        //      }
        // }

        // Logarithmic removal with map, O(log(N))
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;
    }

    // Removes a node at particular index, O(log(n))
    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElem);

        // Obliterate the value & Update map
        heap.remove(indexOfLastElem); // O(1)
        mapRemove(removed_data, indexOfLastElem); // O(1)

        // Removed last element
        if (i == indexOfLastElem) return removed_data;

        T elem = heap.get(i);

        // Try sinking element
        heapifyTopToBottom(i);

        // If heapifyTopToBottom does not change position
        if (heap.get(i).equals(elem)) {
            heapifyBottomToTop(i);
        }

        return removed_data;
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index); // TreeSets take O(log(N)) remove time
        if (set.isEmpty()) map.remove(value);
    }

    // Extract an index position for the given value
    // Note: if a value exists multiple times in the heap the highest
    // index is returned (this has arbitrarily been chosen)
    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) return set.last();
        return null;
    }

    private void swap(int i, int j) {
        T i_elem = heap.get(i);
        T j_elem = heap.get(j);

        heap.set(i, j_elem);
        heap.set(j, i_elem);

        mapSwap(i_elem, j_elem, i, j);
    }

    /**
     * Recursively checks if the heap is a min heap. This method is just for
     * testing purposes to make sure the heap invariant
     * is still being maintained. It starts checking at the root with index k.
     *
     * @param k The index to start checking at.
     * @return true if the heap is a min heap starting at index k, false
     * otherwise.
     */
    public boolean isMinHeap(int k) {

        // If we are outside the bound of the heap return true
        int heapSize = size();
        if (k >= heapSize) return true;

        int left = 2 * k + 1;
        int right = 2 * k + 2;

        // Make sure that the current node k  ise less than
        // both of its children left, and right if they exist
        // return false otherwise to indicate an invalid heap
        if (left < heapSize && !less(k, left)) return false;
        if (right < heapSize && !less(k, right)) return false;

        // Recurse on both children to make sure they're also valid heaps
        return isMinHeap(left) && isMinHeap(right);
    }

    /**
     * Swaps the positions of two values in the heap and updates the value's
     * positions in the map. The values are identified
     * by their keys. The positions of the values in the heap are identified
     * by their indices. The indices are tracked in the
     * map.
     *
     * @param val1      The first value to swap.
     * @param val2      The second value to swap.
     * @param val1Index The index of the first value in the heap.
     * @param val2Index The index of the second value in the heap.
     */
    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {
        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);

    }

    /**
     * Adds the given value and index to the internal map of the priority queue.
     * If the value does not already exist in the map, a new TreeSet is
     * created and the index is added to it.
     * If the value already exists in the map, the index is added to the
     * existing TreeSet.
     *
     * @param value The value to be added to the map.
     * @param index The index corresponding to the value to be added to the map.
     */
    private void mapPut(T value, int index) {
        TreeSet<Integer> set = map.get(value);

        // New value being inseerted in map
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            map.put(value, set);
        } else {
            set.add(index);
        }
    }

    @Override
    public String toString() {
        return heap.toString();
    }

}
