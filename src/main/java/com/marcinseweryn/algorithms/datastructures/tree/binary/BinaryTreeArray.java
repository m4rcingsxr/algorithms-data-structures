package com.marcinseweryn.algorithms.datastructures.tree.binary;


import com.marcinseweryn.algorithms.datastructures.tree.TraversalType;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Class represent array implementation of Binary Tree
 *
 * @author Marcin Seweryn
 * @version 1.0
 */
public class BinaryTreeArray<T> {
    private Object[] arr;
    int size = 0;


    /**
     * Construct empty Binary Tree object with initial capacity
     *
     * @param capacity maximum numbers of elements that BT is
     *                 able to contain
     */
    public BinaryTreeArray(int capacity) {

        // Eliminate 0 index, easier calculation
        arr = new Object[capacity + 1];

    }

    /**
     * Construct empty Binary Tree
     */
    public BinaryTreeArray() {
        arr = new Object[10];
    }

    /**
     * Return {@code true} if BT contains no elements
     *
     * @return {@code true} if BT contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Insert specified element into Binary Tree.
     *
     * @param value to be inserted to BT
     */
    public void add(T value) {
        if (size == arr.length - 1) {
            resizeArray();
        }

        // Start from index 1
        arr[++size] = value;
    }

    private void resizeArray() {
        int newCapacity = arr.length * 2;
        arr = Arrays.copyOf(arr, newCapacity);
    }

    /**
     * Removes specified element from the BT
     *
     * @param value to be removed from BT
     */
    public void remove(T value) {
        int index;
        if (isEmpty()) {
            throw new IllegalStateException("BT is empty");
        } else if ((index = indexOf(value)) == -1) {
            throw new NoSuchElementException("Element does no exist in BT");
        } else {
            arr[index] = arr[size];
            arr[size] = null;
            size--;
        }
    }

    /**
     * Return {@code true} if BT contains specified element
     *
     * @param value element to check if it exists in BT
     * @return {@code true} if BT contains specified element
     */
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    private int indexOf(T value) {
        for (int i = 1; i <= size; i++) {
            if (arr[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Return chosen type of traversal representation
     * as String
     *
     * @return traversal representation as String
     */
    public String traversal(TraversalType type) {
        StringBuilder sb = new StringBuilder("[");
        switch (type) {

            // Java 14
            case PREORDER -> preOrder(1, sb);
            case INORDER -> inOrder(1, sb);
            case POSTORDER -> postOrder(1, sb);
            case LEVELORDER -> levelOrder(sb);
            default -> throw new IllegalArgumentException("No case");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    private void preOrder(int index, StringBuilder sb) {
        if (index > size) {
            return;
        }
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        sb.append(arr[index] + ",");
        preOrder(leftChild, sb);
        preOrder(rightChild, sb);
    }

    private void inOrder(int index, StringBuilder sb) {
        if (index > size) {
            return;
        }
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        inOrder(leftChild, sb);
        sb.append(arr[index] + ",");
        inOrder(rightChild, sb);
    }

    private void postOrder(int index, StringBuilder sb) {
        if (index > size) {
            return;
        }
        int leftChild = index * 2;
        int rightChild = index * 2 + 1;
        postOrder(leftChild, sb);
        postOrder(rightChild, sb);
        sb.append(arr[index] + ",");
    }

    private void levelOrder(StringBuilder sb) {
        for (int i = 1; i <= size; i++) {
            sb.append(arr[i]);
        }
    }

    /**
     * Remove all elements from the BT
     */
    public void clear() {
        arr = new Object[arr.length];
        size = 0;
    }
}
