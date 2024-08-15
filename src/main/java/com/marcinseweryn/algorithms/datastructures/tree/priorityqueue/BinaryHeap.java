package com.marcinseweryn.algorithms.datastructures.tree.priorityqueue;

import com.marcinseweryn.algorithms.datastructures.tree.PriorityQueue;
import com.marcinseweryn.algorithms.datastructures.tree.binary.BinaryTreeIteratorFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A generic binary heap implementation that can function as either a min-heap or a max-heap,
 * depending on the comparator provided. This heap is implemented as a priority queue,
 * supporting efficient insertions and deletions.
 *
 * @param <T> The type of elements held in this heap, which must be comparable.
 */
public class BinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {

    private T[] heap; // The array representation of the binary heap.
    private int size; // The current number of elements in the heap.
    private final int initialCapacity; // The initial capacity of the heap.
    private final Comparator<T> comparator; // Comparator to define heap order (min or max).

    /**
     * Constructs a binary heap with a specified initial size, assuming a natural order (min-heap).
     *
     * @param initialCapacity The initial capacity of the heap.
     */
    private BinaryHeap(int initialCapacity) {
        this(initialCapacity, Comparator.naturalOrder()); // Use natural order for min-heap
    }

    /**
     * Constructs a binary heap with a specified initial size and a custom comparator.
     *
     * @param initialCapacity The initial capacity of the heap.
     * @param comparator      The comparator that defines the heap order.
     */
    private BinaryHeap(int initialCapacity, Comparator<T> comparator) {
        this.heap = (T[]) new Comparable[initialCapacity];
        this.initialCapacity = initialCapacity;
        this.comparator = comparator;
    }

    /**
     * Factory method to create a min-heap.
     *
     * @param initialCapacity The initial capacity of the heap.
     * @param <T>             The type of elements held in the heap.
     * @return A new instance of a min-heap.
     */
    public static <T extends Comparable<T>> BinaryHeap<T> createMinHeap(int initialCapacity) {
        return new BinaryHeap<>(initialCapacity);
    }

    /**
     * Factory method to create a max-heap.
     *
     * @param initialCapacity The initial capacity of the heap.
     * @param <T>             The type of elements held in the heap.
     * @return A new instance of a max-heap.
     */
    public static <T extends Comparable<T>> BinaryHeap<T> createMaxHeap(int initialCapacity) {
        return new BinaryHeap<>(initialCapacity, Comparator.<T>reverseOrder());
    }

    /**
     * Retrieves the element at the top of the heap without removing it.
     *
     * @return The element at the top of the heap, or null if the heap is empty.
     */
    @Override
    public T peek() {
        if (this.isEmpty()) return null; // Return null if heap is empty
        return this.heap[0]; // Root element of the heap
    }

    /**
     * Retrieves and removes the element at the top of the heap.
     *
     * @return The element at the top of the heap, or null if the heap is empty.
     */
    @Override
    public T poll() {
        if (this.isEmpty()) return null; // Return null if heap is empty
        T result = this.heap[0]; // Store the root element
        this.removeAt(0); // Remove the root element
        return result;
    }

    /**
     * Checks if the heap is empty.
     *
     * @return True if the heap is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0; // Heap is empty if size is 0
    }

    /**
     * Returns the number of elements in the heap.
     *
     * @return The size of the heap.
     */
    @Override
    public int size() {
        return this.size; // Return the number of elements in the heap
    }

    /**
     * Adds a new element to the heap.
     *
     * @param element The element to be added.
     * @return True if the element was added successfully, false if it was already present.
     */
    @Override
    public boolean add(T element) {
        if (element == null || this.contains(element)) return false; // Return false if element is null or already present
        if (this.isFull()) this.resize(); // Resize the heap if it's full
        this.heap[this.size] = element; // Place the new element at the end of the heap
        this.heapifyBottomToTop(this.size); // Restore heap property by bubbling up
        this.size++; // Increment the size
        return true;
    }

    /**
     * Checks if the heap is full.
     *
     * @return True if the heap is full, false otherwise.
     */
    private boolean isFull() {
        return this.size == this.heap.length; // Heap is full if size equals array length
    }

    /**
     * Doubles the size of the heap's underlying array.
     */
    private void resize() {
        this.heap = Arrays.copyOf(this.heap, this.heap.length * 2); // Double the size of the heap array
    }

    /**
     * Removes a specific element from the heap.
     *
     * @param element The element to be removed.
     * @return True if the element was removed successfully, false if it was not found.
     */
    @Override
    public boolean remove(T element) {
        int index = this.indexOf(element); // Find the index of the element
        if (index == -1) return false; // Return false if element is not found
        this.removeAt(index); // Remove the element at the found index
        return true;
    }

    /**
     * Removes a node at a particular index.
     *
     * @param index The index of the element to remove.
     * @return The removed element.
     */
    private T removeAt(int index) {
        T removedElement = this.heap[index]; // Store the element to be removed
        this.heap[index] = this.heap[--this.size]; // Replace it with the last element in the heap
        T temp = this.heap[this.size];
        this.heap[this.size] = null; // Nullify the last element's position
        if(index == this.size) return removedElement; // check if the last element was removed

        this.heapifyTopToBottom(index); // Restore heap property by sinking down
        if (temp.equals(this.heap[index])) {
            this.heapifyBottomToTop(index); // Ensure heap property in case of changes during sinking
        }
        return removedElement; // Return the removed element
    }

    /**
     * Restores the heap property by bubbling up the element at the specified index.
     *
     * @param index The index of the element to be bubbled up.
     */
    private void heapifyBottomToTop(int index) {
        int parentIndex = (index - 1) / 2;
        // Continue bubbling up until the heap property is restored
        while (index > 0 && this.less(index, parentIndex)) {
            this.swap(index, parentIndex); // Swap the element with its parent
            index = parentIndex; // Move up to the parent's index
            parentIndex = (index - 1) / 2; // Update the parent's index
        }
    }

    /**
     * Restores the heap property by sinking down the element at the specified index.
     *
     * @param index The index of the element to be sunk down.
     */
    private void heapifyTopToBottom(int index) {
        while (true) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int smallestChild = leftChild;

            // Determine the smallest child to compare with the current node
            if (rightChild < this.size && this.less(rightChild, leftChild)) {
                smallestChild = rightChild;
            }

            // If the smallest child is out of bounds or the current node is smaller, break the loop
            if (leftChild >= this.size || this.less(index, smallestChild)) break;

            this.swap(index, smallestChild); // Swap with the smaller child
            index = smallestChild; // Move down the tree
        }
    }

    /**
     * Compares two elements in the heap based on the comparator.
     *
     * @param i The index of the first element.
     * @param j The index of the second element.
     * @return True if the element at index i is less than the element at index j.
     */
    private boolean less(int i, int j) {
        return this.comparator.compare(heap[i], heap[j]) < 0; // Compare using the comparator
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param i The index of the first element.
     * @param j The index of the second element.
     */
    private void swap(int i, int j) {
        T temp = this.heap[i]; // Temporarily store the first element
        this.heap[i] = this.heap[j]; // Assign the second element to the first position
        this.heap[j] = temp; // Assign the first element to the second position
    }

    /**
     * Checks if the heap contains a specific element.
     *
     * @param element The element to be checked.
     * @return True if the element is found, false otherwise.
     */
    @Override
    public boolean contains(T element) {
        return this.indexOf(element) != -1; // Return true if the element's index is found
    }

    /**
     * Finds the index of a specific element in the heap.
     *
     * @param element The element to be searched for.
     * @return The index of the element, or -1 if it is not found.
     */
    private int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (this.heap[i].equals(element)) return i; // Return the index if found
        }
        return -1; // Return -1 if not found
    }

    /**
     * Clears the heap, removing all elements.
     */
    @Override
    public void clear() {
        this.heap = (T[]) new Comparable[this.initialCapacity]; // Reset the heap array to its initial capacity
        this.size = 0; // Reset the size
    }

    @Override
    public Iterator<T> levelOrderIterator() {
        return BinaryTreeIteratorFactory.levelOrderIterator(Arrays.copyOf(heap, size));
    }

    @Override
    public Iterator<T> inOrderIterator() {
        return BinaryTreeIteratorFactory.inOrderIterator(Arrays.copyOf(heap, size));
    }

    @Override
    public Iterator<T> postOrderIterator() {
        return BinaryTreeIteratorFactory.postOrderIterator(Arrays.copyOf(heap, size));
    }

    @Override
    public Iterator<T> preOrderIterator() {
        return BinaryTreeIteratorFactory.preOrderIterator(Arrays.copyOf(heap, size));
    }
}
