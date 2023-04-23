package com.marcinseweryn.algorithms.datastructures.priorityqueue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class represents an implementation of an indexed min binary heap
 * priority
 * queue, which can handle arbitrary keys with comparable values. To use
 * arbitrary
 * keys (e.g. strings or objects), the keys must first be mapped to the integer
 * domain [0, N), where N is the number of keys. The resulting index values
 * (ki) can
 * then be used with this indexed priority queue. The code uses 'ki' to
 * denote the
 * index value associated with a key k in the domain [0, N).
 * <p>
 * This implementation is zero-based.
 *
 * @param <T> the type of the values in the priority queue
 */
public class IndexedPriorityQueue<T extends Comparable<T>> {
    private int currentSize;
    private final int capacity;

    //The degree of a heap refers to the number of children that each node
    // can have. In a binary heap, the degree is 2 because each node has at
    // most 2 children. In this implementation, the degree can be set by the
    // user when the IndexedPriorityQueue object is created.
    private final int nodeDegree;

    // Contain left children of every node index
    private final int[] childrenIndices;
    private final int[] parentIndices;

    // The Position Map (pm) maps Key Indexes (ki) to where the position of that
    // key is represented in the priority queue in the domain [0, sz).
    private final int[] positionMap;

    // Inverse map stores the indexes of the keys in the range
    // [0, size) which make up the priority queue.
    // im and pm are inverses of each other, so: pm[im[i]] = im[pm[i]] = i
    //  very usefully in pq operation because we operate on real
    //  indexes and we by using them we can get key indexes
    private final int[] inversePositionMap;

    // Values associated with the keys. Note that
    // this arrays is indexed by the key indexes ('ki')
    private final Object[] elements;

    /**
     * Creates a new indexed priority queue with the specified degree and
     * capacity.
     *
     * @param degree   the degree of the heap (i.e. the number of children
     *                 each node can have)
     * @param capacity the maximum number of elements that can be stored in
     *                 the priority queue
     * @throws IllegalArgumentException if capacity is less than or equal to
     *                                  zero
     */
    public IndexedPriorityQueue(int degree, int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity <= 0");
        this.nodeDegree = Math.max(2, degree);
        this.capacity = Math.max(degree + 1, capacity);

        inversePositionMap = new int[capacity];
        positionMap = new int[capacity];
        childrenIndices = new int[capacity];
        parentIndices = new int[capacity];
        elements = new Object[capacity];

        for (int i = 0; i < capacity; i++) {
            parentIndices[i] = (i - 1) / degree;
            childrenIndices[i] = i * degree + 1;
            positionMap[i] = inversePositionMap[i] = -1;
        }
    }

    /**
     * Returns the number of elements in the priority queue.
     *
     * @return the number of elements in the priority queue
     */
    public int size() {
        return this.currentSize;
    }


    /**
     * Returns whether the priority queue is empty.
     *
     * @return true if the priority queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    /**
     * Returns whether the priority queue contains an element with the
     * specified key index.
     *
     * @param elementIndex the key index to search for
     * @return true if the priority queue contains an element with the
     * specified key index, false otherwise
     * @throws IndexOutOfBoundsException if the key index is not in the valid
     *                                   range [0, capacity)
     */
    public boolean contains(int elementIndex) {
        keyInBoundsOrThrow(elementIndex);
        return positionMap[elementIndex] != -1;
    }

    /**
     * Returns the index of the minimum element in the priority queue.
     *
     * @return the index of the minimum element in the priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    public int peekMinElementIndex() {
        isNotEmptyOrThrow();

        // return min KeyIndex for 0 position of node
        return inversePositionMap[0];
    }

    /**
     * Removes and returns the index of the minimum element in the priority
     * queue.
     *
     * @return the index of the minimum element in the priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    public int pollMinElementIndex() {
        int minElementIndex = peekMinElementIndex();
        delete(minElementIndex);
        return minElementIndex;
    }

    /**
     * Returns the minimum value in the priority queue.
     *
     * @return the minimum value in the priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    @SuppressWarnings("unchecked")
    public T peekMinValue() {
        isNotEmptyOrThrow();
        return (T) elements[inversePositionMap[0]];
    }

    /**
     * Removes and returns the minimum value in the priority queue.
     *
     * @return the minimum value in the priority queue
     * @throws NoSuchElementException if the priority queue is empty
     */
    public T pollMinValue() {
        T minValue = peekMinValue();
        delete(peekMinElementIndex());
        return minValue;
    }

    /**
     * Inserts a new element with the specified key index and value into the
     * priority queue.
     *
     * @param elementIndex the key index associated with the element to insert
     * @param value        the value of the element to insert
     * @throws IllegalArgumentException  if the key index already exists in
     *                                   the priority queue
     *                                   or if the value is null
     * @throws IndexOutOfBoundsException if the key index is not in the valid
     *                                   range [0, capacity)
     */
    public void insert(int elementIndex, T value) {
        if (contains(elementIndex)) throw new IllegalArgumentException(
                String.format("index %d already exists", elementIndex));
        valueNotNullOrThrow(value);
        positionMap[elementIndex] = currentSize;
        inversePositionMap[currentSize] = elementIndex;
        elements[elementIndex] = value;
        heapifyBottomToTop(currentSize++); // swim
    }

    @SuppressWarnings("unchecked")
    public T valueOf(int elementIndex) {
        keyExistsOrThrow(elementIndex);
        return (T) elements[elementIndex];
    }

    /**
     * Removes the key-value pair associated with the specified key index from
     * the priority queue.
     *
     * @param elementIndex the key index of the key-value pair to be removed
     * @throws IndexOutOfBoundsException if the key index is not in the valid
     *                                   range [0, capacity)
     */
    public T delete(int elementIndex) {
        keyExistsOrThrow(elementIndex);
        final int i = positionMap[elementIndex];
        swap(i, --currentSize);
        heapifyTopToBottom(i);
        heapifyBottomToTop(i);
        T value = (T) elements[elementIndex];
        elements[elementIndex] = null;
        positionMap[elementIndex] = -1;
        inversePositionMap[currentSize] = -1;
        return value;
    }

    /**
     * Updates the element at the specified index in the heap with the
     * specified value.
     *
     * @param elementIndex the index of the element to be updated
     * @param value        the new value of the element
     * @return the old value of the element before it was updated
     * @throws IllegalArgumentException if the specified element index is out
     *                                  of bounds or if the specified
     *                                  value is null
     */
    public T update(int elementIndex, T value) {
        keyExistsAndValueNotNullOrThrow(elementIndex, value);
        final int i = positionMap[elementIndex];
        T oldValue = (T) elements[elementIndex];
        elements[elementIndex] = value;
        heapifyTopToBottom(i);
        heapifyBottomToTop(i);
        return oldValue;
    }

    /**
     * Changes the value associated with the specified key index to the
     * specified value.
     *
     * @param elementIndex the key index whose value should be changed
     * @param value        the new value associated with the specified key index
     * @throws IndexOutOfBoundsException if the key index is not in the valid
     *                                   range [0, capacity)
     */
    public void decrease(int elementIndex, T value) {
        keyExistsAndValueNotNullOrThrow(elementIndex, value);
        if (less(value, elements[elementIndex])) {
            elements[elementIndex] = value;
            heapifyBottomToTop(positionMap[elementIndex]);
        }
    }

    /**
     * Increases the value associated with the specified key index, if the
     * new value is greater than the old one.
     *
     * @param elementIndex the index associated with the value to be increased
     * @param value        the new value to be compared with the old one
     * @throws NoSuchElementException   if the key index does not exist in
     *                                  the priority queue
     * @throws IllegalArgumentException if the value is null
     */
    public void increase(int elementIndex, T value) {
        keyExistsAndValueNotNullOrThrow(elementIndex, value);
        if (less(elements[elementIndex], value)) {
            elements[elementIndex] = value;
            heapifyTopToBottom(positionMap[elementIndex]);
        }
    }

    /**
     * Heapifies the binary heap from the top (root) to the bottom.
     *
     * @param i the index of the node to start the heapification from
     */
    private void heapifyTopToBottom(int i) {
        for (int j = minChild(i); j != -1; ) {
            swap(i, j);
            i = j;
            j = minChild(i);
        }
    }

    /**
     * Returns the index of the smallest child node of the specified node, or
     * -1 if the node has no child.
     *
     * @param i the index of the node to find the smallest child for
     * @return the index of the smallest child node, or -1 if the node has no
     * child
     */
    private int minChild(int i) {
        int index = -1;
        int from = childrenIndices[i];
        int to = Math.min(currentSize, from + nodeDegree);
        for (int j = from; j < to; j++) {
            if (less(j, i)) index = i = j;
        }
        return index;
    }

    /**
     * Heapifies the binary heap from the bottom to the top.
     *
     * @param i the index of the node to start the heapification from
     */
    private void heapifyBottomToTop(int i) {
        while (less(i, parentIndices[i])) {
            swap(i, parentIndices[i]);
            i = parentIndices[i];
        }
    }

    /**
     * Swaps the positions of two nodes in the binary heap.
     *
     * @param i the index of the first node to be swapped
     * @param j the index of the second node to be swapped
     */
    private void swap(int i, int j) {
        positionMap[inversePositionMap[j]] = i;
        positionMap[inversePositionMap[i]] = j;
        int tmp = inversePositionMap[i];
        inversePositionMap[i] = inversePositionMap[j];
        inversePositionMap[j] = tmp;
    }

    /**
     * Compares the values of two nodes in the binary heap.
     *
     * @param i the index of the first node
     * @param j the index of the second node
     * @return true if the value of the first node is less than the value of
     * the second node, false otherwise
     */
    private boolean less(int i, int j) {
        return ((Comparable<? super T>) elements[inversePositionMap[i]]).compareTo(
                (T) elements[inversePositionMap[j]]) < 0;
    }

    /**
     * Compares two objects.
     *
     * @param obj1 the first object to be compared
     * @param obj2 the second object to be compared
     * @return true if the first object is less than the second object, false
     * otherwise
     */
    private boolean less(Object obj1, Object obj2) {
        return ((Comparable<? super T>) obj1).compareTo((T) obj2) < 0;
    }

    @Override
    public String toString() {
        List<T> lst = new ArrayList<>(currentSize);
        for (int i = 0; i < currentSize; i++)
            lst.add((T) elements[inversePositionMap[i]]);
        return lst.toString();
    }

    // helper methods
    private void keyExistsAndValueNotNullOrThrow(int ki, Object value) {
        keyExistsOrThrow(ki);
        valueNotNullOrThrow(value);
    }

    private void keyExistsOrThrow(int ki) {
        if (!contains(ki))
            throw new NoSuchElementException("Index does not exists");
    }

    private void valueNotNullOrThrow(Object value) {
        if (value == null)
            throw new IllegalArgumentException("Value cannot be null");
    }

    private void keyInBoundsOrThrow(int ki) {
        if (ki < 0 || ki >= capacity)
            throw new IllegalArgumentException(
                    "Key index out of bounds; received: " + ki);
    }

    private void isNotEmptyOrThrow() {
        if (isEmpty())
            throw new NoSuchElementException("Priority queue is empty");
    }

    public boolean isMinHeap() {
        return isMinHeap(0);
    }

    private boolean isMinHeap(int i) {
        int from = childrenIndices[i], to = Math.min(currentSize,
                                                     from + nodeDegree
        );
        for (int j = from; j < to; j++) {
            if (!less(i, j)) return false;
            if (!isMinHeap(j)) return false;
        }
        return true;
    }

}
