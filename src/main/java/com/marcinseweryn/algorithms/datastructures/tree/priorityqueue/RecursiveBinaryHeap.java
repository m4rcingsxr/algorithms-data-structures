package com.marcinseweryn.algorithms.datastructures.tree.priorityqueue;

import com.marcinseweryn.algorithms.datastructures.tree.PriorityQueue;
import com.marcinseweryn.algorithms.datastructures.tree.binary.BinaryTreeIteratorFactory;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/**
 * A priority queue implemented as a recursive binary heap.
 * The heap supports typical operations such as insert (add), remove, peek, poll,
 * and supports heapifying both from bottom to top and top to bottom.
 * The heap is maintained using a comparator for custom ordering of elements.
 *
 * <p>
 * To create a Min-Heap, you should use the natural ordering comparator:
 * {@code Comparator.naturalOrder()}.
 *
 * To create a Max-Heap, you should use the reverse ordering comparator:
 * {@code Comparator.reverseOrder()}.
 * </p>
 *
 * @param <T> the type of elements held in this priority queue
 */
public class RecursiveBinaryHeap<T extends Comparable<T>> implements PriorityQueue<T> {

    private T[] heap;
    private int size;
    private final Comparator<? super T> comparator;
    private final int initialCapacity;

    /**
     * Constructs an empty binary heap with the specified initial capacity and comparator.
     *
     * @param initialCapacity the initial capacity of the binary heap
     * @param comparator the comparator used to order the elements in this heap
     */
    public RecursiveBinaryHeap(int initialCapacity, Comparator<T> comparator) {
        this.initialCapacity = initialCapacity;
        this.heap = (T[]) new Comparable[initialCapacity];
        this.comparator = comparator;
    }

    /**
     * Retrieves, but does not remove, the root element (highest priority) of the heap.
     *
     * @return the root element of the heap, or null if the heap is empty
     */
    @Override
    public T peek() {
        if(this.isEmpty()) return null;
        return this.heap[0];
    }

    /**
     * Retrieves and removes the root element (highest priority) of the heap.
     * Replaces the root with the last element in the heap, then re-heapifies from top to bottom.
     *
     * @return the root element of the heap, or null if the heap is empty
     */
    @Override
    public T poll() {
        if(this.isEmpty()) return null;
        T temp = this.heap[0]; // Store the root element to return it later
        this.heap[0] = this.heap[this.size - 1]; // Replace root with the last element
        this.heap[this.size - 1] = null; // Remove the last element
        this.size--; // Decrement size
        this.heapifyTopToBottom(0); // Re-heapify starting from the root
        return temp; // Return the removed root element
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise
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
     * Adds a new element to the heap. If the element already exists, it will not be added.
     * After adding, re-heapifies the heap from bottom to top.
     * If the heap is full, it resizes the underlying array.
     *
     * @param element the element to add to the heap
     * @return true if the element was added, false if it already exists in the heap
     */
    @Override
    public boolean add(T element) {
        if (this.contains(element)) return false; // Do not add if element already exists
        if (this.size == this.heap.length) {
            this.resize(); // Resize the heap if it is full
        }
        this.heap[this.size++] = element; // Add the element at the end of the heap
        this.heapifyBottomToTop(this.size - 1); // Re-heapify starting from the new element
        return true; // Return true indicating the element was added
    }

    /**
     * Removes a specific element from the heap. If the element does not exist, it will not be removed.
     * Replaces the element with the last element in the heap, then re-heapifies from top to bottom.
     * If needed, re-heapifies from bottom to top.
     *
     * @param element the element to remove from the heap
     * @return true if the element was removed, false if it does not exist in the heap
     */
    @Override
    public boolean remove(T element) {
        if (!this.contains(element)) return false; // Return false if the element does not exist
        int index = this.indexOf(element); // Find the index of the element

        T temp = this.heap[this.size - 1]; // Get the last element
        this.heap[this.size - 1] = null; // Remove the last element
        this.heap[index] = temp; // Replace the element to be removed with the last element
        this.size--; // Decrement size
        this.heapifyTopToBottom(index); // Re-heapify starting from the replaced position
        if(this.heap[index].equals(temp)) this.heapifyBottomToTop(index); // Re-heapify bottom-to-top if needed

        return true; // Return true indicating the element was removed
    }

    /**
     * Checks if a specific element exists in the heap.
     *
     * @param element the element to check for existence in the heap
     * @return true if the element exists, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return this.indexOf(element) != -1;
    }

    /**
     * Resizes the underlying array to accommodate more elements when the heap is full.
     * Doubles the current capacity of the heap.
     */
    private void resize() {
        this.heap = Arrays.copyOf(this.heap, this.heap.length * 2);
    }

    /**
     * Re-heapifies the heap from bottom to top, ensuring the heap property is maintained.
     *
     * @param index the starting index from which to begin heapifying upwards
     */
    private void heapifyBottomToTop(int index) {
        int parent = (index - 1) / 2;
        if (index <= 0) return; // Base case: reached the root of the heap

        T currentNode = this.heap[index]; // Get the current node
        T parentNode = this.heap[parent]; // Get the parent node
        if (comparator.compare(currentNode, parentNode) < 0) { // Compare current node with parent
            this.heap[index] = parentNode; // Swap the current node with the parent
            this.heap[parent] = currentNode;
            this.heapifyBottomToTop(parent); // Recur on the parent node
        }
    }

    /**
     * Re-heapifies the heap from top to bottom, ensuring the heap property is maintained.
     *
     * @param index the starting index from which to begin heapifying downwards
     */
    private void heapifyTopToBottom(int index) {
        int left = index * 2 + 1; // Calculate the left child index
        int right = index * 2 + 2; // Calculate the right child index
        if (left >= this.size) return; // Base case: reached a leaf node

        int selected = left; // Assume the left child is the smaller child

        // Check if the right child exists and is smaller than the left child
        if (right < size) {
            selected = comparator.compare(this.heap[left], this.heap[right]) < 0 ? left : right;
        }

        // If the selected child is smaller than the current node, swap them
        if (comparator.compare(this.heap[index], this.heap[selected]) > 0) {
            T temp = this.heap[index]; // Swap current node with the selected child
            this.heap[index] = this.heap[selected];
            this.heap[selected] = temp;
            this.heapifyTopToBottom(selected); // Recur on the selected child
        }
    }

    /**
     * Returns the index of a specific element in the heap.
     *
     * @param element the element whose index is to be found
     * @return the index of the element, or -1 if the element does not exist in the heap
     */
    private int indexOf(T element) {
        for (int i = 0; i < this.size(); i++) {
            if(this.heap[i].equals(element)) return i; // Return the index if the element is found
        }
        return -1; // Return -1 if the element is not found
    }

    /**
     * Removes all elements from the heap, effectively clearing the heap.
     */
    @Override
    public void clear() {
        this.heap = (T[]) new Comparable[initialCapacity];
        this.size = 0;
    }

    /**
     * Returns an iterator for iterating over the elements of the heap in level-order.
     *
     * @return an iterator over the elements in level-order
     */
    @Override
    public Iterator<T> levelOrderIterator() {
        return BinaryTreeIteratorFactory.levelOrderIterator(this.heap);
    }

    /**
     * Returns an iterator for iterating over the elements of the heap in in-order.
     *
     * @return an iterator over the elements in in-order
     */
    @Override
    public Iterator<T> inOrderIterator() {
        return BinaryTreeIteratorFactory.inOrderIterator(this.heap);
    }

    /**
     * Returns an iterator for iterating over the elements of the heap in post-order.
     *
     * @return an iterator over the elements in post-order
     */
    @Override
    public Iterator<T> postOrderIterator() {
        return BinaryTreeIteratorFactory.postOrderIterator(this.heap);
    }

    /**
     * Returns an iterator for iterating over the elements of the heap in pre-order.
     *
     * @return an iterator over the elements in pre-order
     */
    @Override
    public Iterator<T> preOrderIterator() {
        return BinaryTreeIteratorFactory.preOrderIterator(this.heap);
    }
}
