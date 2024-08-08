package com.marcinseweryn.algorithms.datastructures.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A stack implementation using an array as the underlying data structure.
 * The stack follows a Last-In-First-Out (LIFO) principle, where elements are added and removed
 * from the top of the stack.
 *
 * @param <T> the type of elements held in this stack
 */
public class StackArray<T> implements Stack<T>{

    private T[] stack; // The array to hold stack elements
    private int top;   // The index of the top element in the stack

    /**
     * Constructs a stack with an initial size. If a large number of elements are expected,
     * it's advisable to provide a larger initial size to avoid frequent resizing.
     *
     * @param initialSize the initial capacity of the stack
     */
    public StackArray(int initialSize) {
        this.stack = (T[]) new Object[initialSize]; // Initialize the stack array
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return this.top;
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T temp = this.stack[--top]; // Decrement top and retrieve the top element
        this.stack[top] = null;     // Nullify the slot to avoid memory leaks
        return temp;
    }

    /**
     * Pushes an element onto the top of the stack. Resizes the stack if it's full.
     *
     * @param elem the element to be added to the stack
     */
    @Override
    public void push(T elem) {
        if(this.isFull()) { // Check if the stack is full
            this.resize();  // Resize the stack array if needed
        }
        this.stack[this.top++] = elem; // Add the element to the top and increment the top index
    }

    /**
     * Checks if the stack is full.
     *
     * @return true if the stack is full, false otherwise
     */
    private boolean isFull() {
        return this.stack.length == this.top;
    }

    /**
     * Resizes the stack array to double its current capacity.
     */
    private void resize() {
        this.stack = Arrays.copyOf(stack, this.stack.length * 2);
    }

    /**
     * Retrieves, but does not remove, the element at the top of the stack.
     *
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.stack[this.top - 1]; // Return the top element without modifying the stack
    }

    /**
     * Returns an iterator over the elements in this stack in LIFO order.
     *
     * @return an iterator over the elements in this stack
     */
    @Override
    public Iterator<T> iterator() {
        return new StackIterator(); // Return a new StackIterator instance
    }

    /**
     * An iterator class for the StackArray that iterates over the stack elements
     * from the bottom to the top.
     */
    class StackIterator implements Iterator<T> {

        private int currentIndex; // The index of the next element to be returned by the iterator

        /**
         * Checks if there are more elements to iterate over.
         *
         * @return true if there are more elements, false otherwise
         */
        @Override
        public boolean hasNext() {
            return this.currentIndex < top; // Compare currentIndex with the top index
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if there are no more elements to return
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T temp = stack[currentIndex]; // Retrieve the next element
            currentIndex++;               // Increment currentIndex
            return temp;
        }
    }
}
