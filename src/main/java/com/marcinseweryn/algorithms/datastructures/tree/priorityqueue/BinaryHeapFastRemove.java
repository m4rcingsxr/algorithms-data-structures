package com.marcinseweryn.algorithms.datastructures.tree.priorityqueue;

import com.marcinseweryn.algorithms.datastructures.tree.PriorityQueue;
import com.marcinseweryn.algorithms.datastructures.tree.binary.BinaryTreeIteratorFactory;

import java.util.*;

/**
 * A priority queue implementation using a binary heap.
 * This class supports efficient element removals by tracking element positions within the heap
 * using a map.
 * The heap can be either a min-heap or max-heap depending on the comparator provided.
 *
 * @param <T> the type of elements held in this collection, which must be comparable
 */
public class BinaryHeapFastRemove<T extends Comparable<T>> implements PriorityQueue<T> {

    /** The array representing the binary heap. */
    private T[] heap;

    /** The number of elements currently in the heap. */
    private int size;

    /** The initial size of the heap array. */
    private final int initialSize;

    /** The comparator used to order the elements in the heap. */
    private final Comparator<T> comparator;

    /** A map to track the positions of each element in the heap for fast removal. */
    private final Map<T, TreeSet<Integer>> map = new HashMap<>();

    /**
     * Constructs a BinaryHeapFastRemove with a natural order comparator.
     *
     * @param initialSize the initial capacity of the heap
     */
    private BinaryHeapFastRemove(int initialSize) {
        this.heap = (T[]) new Comparable[initialSize];
        this.initialSize = initialSize;
        this.comparator = Comparator.naturalOrder();
    }

    /**
     * Constructs a BinaryHeapFastRemove with a custom comparator.
     *
     * @param initialSize the initial capacity of the heap
     * @param comparator the comparator used to order the elements
     */
    private BinaryHeapFastRemove(int initialSize, Comparator<T> comparator) {
        this.heap = (T[]) new Comparable[initialSize];
        this.initialSize = initialSize;
        this.comparator = comparator;
    }

    /**
     * Factory method to create a min-heap.
     *
     * @param initialCapacity The initial capacity of the heap.
     * @param <T>             The type of elements held in the heap.
     * @return A new instance of a min-heap.
     */
    public static <T extends Comparable<T>> BinaryHeapFastRemove<T> createMinHeap(
            int initialCapacity) {
        return new BinaryHeapFastRemove<>(initialCapacity);
    }

    /**
     * Factory method to create a max-heap.
     *
     * @param initialCapacity The initial capacity of the heap.
     * @param <T>             The type of elements held in the heap.
     * @return A new instance of a max-heap.
     */
    public static <T extends Comparable<T>> BinaryHeapFastRemove<T> createMaxHeap(
            int initialCapacity) {
        return new BinaryHeapFastRemove<>(initialCapacity, Comparator.<T>reverseOrder());
    }

    /**
     * Retrieves, but does not remove, the element at the top of the heap (minimum or maximum
     * element).
     *
     * @return the element at the top of the heap, or {@code null} if the heap is empty
     */
    @Override
    public T peek() {
        if (this.isEmpty()) return null; // If heap is empty, return null
        return this.heap[0]; // The root element is always the minimum or maximum
    }

    /**
     * Retrieves and removes the element at the top of the heap (minimum or maximum element).
     *
     * @return the element removed, or {@code null} if the heap is empty
     */
    @Override
    public T poll() {
        if (this.isEmpty()) return null; // If heap is empty, return null
        T result = this.heap[0]; // Store the top element to return later
        this.removeAt(0); // Remove the top element
        return result;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return {@code true} if the heap is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return the number of elements in the heap
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Adds a new element to the heap, maintaining the heap property.
     *
     * @param element the element to be added to the heap
     * @return {@code true} if the element was successfully added, {@code false} if the element
     * is already present
     */
    @Override
    public boolean add(T element) {
        if (this.isFull()) {
            this.resize(); // Double the heap size if full
        }

        this.addIndex(element, this.size); // Track the new element's position

        this.heap[this.size] = element; // Add the new element to the heap
        this.heapifyBottomToTop(this.size); // Restore the heap property by swimming up
        this.size++; // Increase the heap size
        return true;
    }

    /**
     * Removes a specific element from the heap.
     *
     * @param element the element to be removed
     * @return {@code true} if the element was successfully removed, {@code false} if the element
     * was not found
     */
    @Override
    public boolean remove(T element) {
        if (element == null) return false;
        Integer index = this.getIndex(element); // Get the index of the element
        if (index != null) {
            removeAt(index); // Remove the element at the found index
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the heap contains a specific element.
     *
     * @param element the element to check for presence in the heap
     * @return {@code true} if the element is present in the heap, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        if (element == null) return false;
        return this.map.containsKey(element); // Use the map for fast lookup
    }

    /**
     * Removes all elements from the heap, effectively clearing it.
     */
    @Override
    public void clear() {
        this.heap = (T[]) new Comparable[initialSize]; // Reset the heap array
        this.size = 0; // Reset the size
    }

    /**
     * Removes the element at the specified index in the heap.
     * This operation maintains the heap property after the removal.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the heap
     */
    private T removeAt(int index) {
        if (isEmpty()) return null;

        T removedElement = this.heap[index]; // Store the element to be removed
        this.swap(index, --this.size);
        this.heap[this.size] = null;

        this.removeIndex(removedElement, index); // Update the map to remove the element's index

        this.heapifyTopToBottom(index); // Restore the heap property by sinking down
        if (removedElement.equals(this.heap[index])) {
            this.heapifyBottomToTop(index); // Ensure heap property in case of changes during sinking
        }
        return removedElement; // Return the removed element
    }

    /**
     * Checks if the heap is full (i.e., the underlying array is full).
     *
     * @return {@code true} if the heap is full, {@code false} otherwise
     */
    private boolean isFull() {
        return this.size == this.heap.length;
    }

    /**
     * Doubles the size of the heap array to accommodate more elements.
     */
    private void resize() {
        this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
    }

    /**
     * Restores the heap property by "swimming" the element at the specified index up the heap.
     *
     * @param index the index of the element to swim up
     */
    private void heapifyBottomToTop(int index) {
        int parent = (index - 1) / 2;

        while (index > 0 && this.less(index, parent)) {
            this.swap(index, parent); // Swap with parent if out of order
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    /**
     * Restores the heap property by "sinking" the element at the specified index down the heap.
     *
     * @param index the index of the element to sink down
     */
    private void heapifyTopToBottom(int index) {
        while (true) {
            int left = 2 * index + 1; // Calculate the left child index
            int right = 2 * index + 2; // Calculate the right child index

            int smallestChild = left;
            if (right < this.size && this.less(right, smallestChild))
                smallestChild = right; // Determine the smaller child
            if (left >= this.size || this.less(index, smallestChild))
                break; // If in order or at a leaf, stop

            this.swap(index, smallestChild); // Swap with the smallest child
            index = smallestChild; // Move down to the child's position
        }
    }

    /**
     * Compares the elements at two indices in the heap.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     * @return {@code true} if the element at index {@code i} is less than the element at index
     * {@code j}, {@code false} otherwise
     */
    private boolean less(int i, int j) {
        return this.comparator.compare(this.heap[i], this.heap[j]) < 0;
    }

    /**
     * Swaps the elements at two indices in the heap.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swap(int i, int j) {
        T temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;

        this.swapIndexes(i, j); // Update the map with the new positions
    }

    /**
     * Updates the map to reflect the new positions of elements after a swap.
     *
     * @param i the index of the first element
     * @param j the index of the second element
     */
    private void swapIndexes(int i, int j) {
        // backwards because we already swap them
        T elemI = this.heap[j];
        T elemJ = this.heap[i];

        TreeSet<Integer> setI = this.map.get(elemI);
        TreeSet<Integer> setJ = this.map.get(elemJ);

        setI.remove(i); // Ensure the old index is removed
        setI.add(j);    // Add the new index

        setJ.remove(j); // Ensure the old index is removed
        setJ.add(i);    // Add the new index
    }

    /**
     * Removes the specified element's index from the map.
     * If the element no longer has any positions in the heap, it is removed from the map entirely.
     *
     * @param element the element to remove from the map
     * @param index the index to remove from the element's index set
     */
    private void removeIndex(T element, int index) {
        TreeSet<Integer> set = this.map.get(element);
        set.remove(index);
        if (set.isEmpty()) map.remove(element);
    }

    /**
     * Adds an index to the map for the specified element.
     *
     * @param element the element to add to the map
     * @param index the index of the element in the heap
     */
    private void addIndex(T element, int index) {
        TreeSet<Integer> set = this.map.get(element);
        if (set == null) {
            set = new TreeSet<>();
            set.add(index);
            this.map.put(element, set);
        } else {
            set.add(index);
        }
    }

    /**
     * Retrieves the highest index associated with the specified element.
     * This is used to locate the position of an element in the heap for removal.
     *
     * @param element the element to locate
     * @return the highest index of the element, or {@code null} if the element is not in the heap
     */
    private Integer getIndex(T element) {
        TreeSet<Integer> set = map.get(element);
        if (set != null) return set.last();
        return null;
    }

    /**
     * Returns an iterator that traverses the heap in level-order.
     *
     * @return an iterator that traverses the heap in level-order
     */
    @Override
    public Iterator<T> levelOrderIterator() {
        return BinaryTreeIteratorFactory.levelOrderIterator(Arrays.copyOf(heap, size));
    }

    /**
     * Returns an iterator that traverses the heap in in-order.
     *
     * @return an iterator that traverses the heap in in-order
     */
    @Override
    public Iterator<T> inOrderIterator() {
        return BinaryTreeIteratorFactory.inOrderIterator(Arrays.copyOf(heap, size));
    }

    /**
     * Returns an iterator that traverses the heap in post-order.
     *
     * @return an iterator that traverses the heap in post-order
     */
    @Override
    public Iterator<T> postOrderIterator() {
        return BinaryTreeIteratorFactory.postOrderIterator(Arrays.copyOf(heap, size));
    }

    /**
     * Returns an iterator that traverses the heap in pre-order.
     *
     * @return an iterator that traverses the heap in pre-order
     */
    @Override
    public Iterator<T> preOrderIterator() {
        return BinaryTreeIteratorFactory.preOrderIterator(Arrays.copyOf(heap, size));
    }
}
