package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.BinaryTree;

import java.util.Arrays;
import java.util.Iterator;

/**
 * A binary tree implementation using an array as the underlying data structure.
 * The elements are stored in a contiguous block of memory, which may be resized
 * dynamically as more elements are added. The tree supports basic operations such
 * as adding, removing, checking for containment, and iterating over elements.
 *
 * @param <T> the type of elements stored in the tree, which must be comparable.
 */
public class BinaryTreeArray<T extends Comparable<T>> implements BinaryTree<T> {

    private T[] tree;
    private int size;
    private final int initialSize;

    /**
     * Constructs a new BinaryTreeArray with the specified initial size.
     *
     * @param initialSize the initial capacity of the underlying array.
     */
    public BinaryTreeArray(int initialSize) {
        this.initialSize = initialSize;
        this.tree = (T[]) new Comparable[initialSize];
    }

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree contains no elements, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Returns the number of elements currently in the tree.
     *
     * @return the number of elements in the tree.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Adds a new element to the tree. If the element already exists in the tree,
     * it will not be added again.
     *
     * @param element the element to be added.
     * @return true if the element was successfully added, false if it already exists.
     */
    @Override
    public boolean add(T element) {
        // Check if the element already exists in the tree.
        if (contains(element)) {
            return false;
        }

        // Resize the tree array if necessary.
        if (size == tree.length) {
            this.resizeTree();
        }

        // Add the element to the tree and increment the size.
        tree[size++] = element;
        return true;
    }

    /**
     * Doubles the size of the tree array when it becomes full.
     */
    private void resizeTree() {
        this.tree = Arrays.copyOf(tree, tree.length * 2);
    }

    /**
     * Removes the specified element from the tree if it exists.
     *
     * @param element the element to be removed.
     * @return true if the element was successfully removed, false if it does not exist.
     */
    @Override
    public boolean remove(T element) {
        int index;

        // Find the index of the element to remove.
        if ((index = this.indexOf(element)) == -1) {
            return false;
        }

        // Replace the element to be removed with the last element in the tree.
        this.tree[index] = this.tree[this.size - 1];
        this.tree[this.size - 1] = null; // Clear the last element.
        this.size--; // Decrement the size of the tree.

        // If the tree becomes empty, clear it entirely.
        if (this.isEmpty()) {
            this.clear();
        }

        return true;
    }

    /**
     * Finds the index of the specified element in the tree.
     *
     * @param element the element to find.
     * @return the index of the element, or -1 if it is not found.
     */
    private int indexOf(T element) {
        // Iterate through the elements to find the target.
        for (int i = 0; i < this.size; i++) {
            if (this.tree[i] != null && this.tree[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if the tree contains the specified element.
     *
     * @param element the element to check for.
     * @return true if the tree contains the element, false otherwise.
     */
    @Override
    public boolean contains(T element) {
        return this.indexOf(element) != -1;
    }

    /**
     * Clears the tree, removing all elements and resetting its size.
     */
    @Override
    public void clear() {
        this.tree = (T[]) new Comparable[this.initialSize];
        this.size = 0;
    }

    /**
     * Returns an iterator for iterating over the elements in level order.
     *
     * @return an iterator for level-order traversal of the tree.
     */
    @Override
    public Iterator<T> levelOrderIterator() {
        return BinaryTreeIteratorFactory.levelOrderIterator(this.tree);
    }

    /**
     * Returns an iterator for iterating over the elements in in-order.
     *
     * @return an iterator for in-order traversal of the tree.
     */
    @Override
    public Iterator<T> inOrderIterator() {
        return BinaryTreeIteratorFactory.inOrderIterator(this.tree);
    }

    /**
     * Returns an iterator for iterating over the elements in post-order.
     *
     * @return an iterator for post-order traversal of the tree.
     */
    @Override
    public Iterator<T> postOrderIterator() {
        return BinaryTreeIteratorFactory.postOrderIterator(this.tree);
    }

    /**
     * Returns an iterator for iterating over the elements in pre-order.
     *
     * @return an iterator for pre-order traversal of the tree.
     */
    @Override
    public Iterator<T> preOrderIterator() {
        return BinaryTreeIteratorFactory.preOrderIterator(this.tree);
    }
}
