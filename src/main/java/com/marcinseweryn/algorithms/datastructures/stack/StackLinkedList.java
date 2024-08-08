package com.marcinseweryn.algorithms.datastructures.stack;

import com.marcinseweryn.algorithms.datastructures.linkedlist.DoublyLinkedList;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * A stack implementation using a doubly linked list as the underlying data structure.
 * The stack follows a Last-In-First-Out (LIFO) principle, where elements are added and removed
 * from the top of the stack.
 *
 * @param <T> the type of elements held in this stack
 */
public class StackLinkedList<T> implements Stack<T> {

    private final DoublyLinkedList<T> linkedList;

    /**
     * Constructs an empty stack.
     */
    public StackLinkedList() {
        this.linkedList = new DoublyLinkedList<>();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    @Override
    public int size() {
        return this.linkedList.size();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.linkedList.isEmpty();
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
        return this.linkedList.removeLast();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element the element to be added to the stack
     */
    @Override
    public void push(T element) {
        this.linkedList.add(element);
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
        return this.linkedList.peekLast();
    }

    /**
     * Returns an iterator over the elements in this stack in LIFO order.
     *
     * @return an iterator over the elements in this stack
     */
    @Override
    public Iterator<T> iterator() {
        return this.linkedList.iterator();
    }
}
