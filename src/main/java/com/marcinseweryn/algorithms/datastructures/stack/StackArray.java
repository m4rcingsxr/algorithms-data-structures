package com.marcinseweryn.algorithms.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Class represent array implementation of the Stack
 *
 * @author Marcin Seweryn
 * @version 1.0
 */
public class StackArray<T> {

    private Object[] arr;
    private int size;
    private int top = 0;

    /**
     * Constructs an empty stack with an initial capacity.
     * Stack's allowed to store 10 elements.
     */
    public StackArray() {
        size = 10;
        arr = new Object[size];
    }

    /**
     * Constructs an empty stack with a specified capacity.
     * Stack's allowed to store initialSize number of elements.
     *
     * @param initialSize - determines the initial capacity of stack
     */
    public StackArray(int initialSize) {
        size = initialSize;
        arr = new Object[size];
    }

    /**
     * Return the number of elements in this Stack
     *
     * @return the number of elements in this Stack
     */
    public int size() {
        return top;
    }

    /**
     * Return {@code true} if this Stack contains no elements
     *
     * @return {@code true} if this Stack contains no elements
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /**
     * Remove and return last inserted element of this stack
     *
     * @return last inserted element of this stack
     * @throws EmptyStackException if stack contains no element
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T result = (T) arr[--top];

        // Prevent memory leaks
        arr[top] = null;
        return result;
    }

    /**
     * Insert specified element at the end of the Stack
     *
     * @param element to be inserted at the end of the Stack
     */
    public void push(T element) {
        if (isFull()) {
            increaseSize();
        }
        arr[top++] = element;
    }


    private boolean isFull() {
        return top == size;
    }

    private void increaseSize() {
        size *= 2;
        arr = Arrays.copyOf(arr, size);
    }

    /**
     * Return last inserted element to this Stack
     *
     * @return last inserted element to this Stack
     * @throws EmptyStackException if stack contains no element
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        return (T) arr[top - 1];
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            sb.append(arr[i]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
