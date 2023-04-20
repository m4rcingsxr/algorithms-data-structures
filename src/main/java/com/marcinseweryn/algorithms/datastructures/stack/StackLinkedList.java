package com.marcinseweryn.algorithms.datastructures.stack;

import com.marcinseweryn.algorithms.datastructures.linkedlist.LinkedList;

import java.util.EmptyStackException;

/**
 * Class represent Linked List implementation of Stack
 *
 * @author Marcin Seweryn
 * @version 1.0
 */
public class StackLinkedList<T> {
    private final LinkedList<T> list;

    /**
     * Construct an empty stack
     */
    public StackLinkedList() {
        list = new LinkedList<>();
    }

    /**
     * Construct a Stack with initial element
     */
    public StackLinkedList(T element) {
        list = new LinkedList<>();
        list.add(element);
    }

    /**
     * Constructs an empty stack with an initial capacity.
     * Stack's allowed to store 10 elements.
     */
    public int size() {
        return list.size();
    }

    /**
     * Return {@code true} if this Stack contains no elements
     *
     * @return {@code true} if this Stack contains no elements
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Insert specified element at the end of the Stack
     */
    public void push(T element) {
        list.add(element);
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
        return list.removeLast();
    }

    /**
     * Return last inserted element to this Stack
     *
     * @return last inserted element to this Stack
     * @throws EmptyStackException if stack contains no element
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.peekLast();
    }
}

