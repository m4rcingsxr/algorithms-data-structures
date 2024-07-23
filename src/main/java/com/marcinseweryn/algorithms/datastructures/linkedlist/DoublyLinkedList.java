package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;

/**
 * A generic doubly linked list implementation that supports basic operations such as adding,
 * removing, and searching for elements.
 *
 * @param <T> the type of elements held in this LinkedList
 */
public class DoublyLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list contains no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the number of elements in the linked list
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Retrieves the first element of the linked list without removing it.
     *
     * @return the first element of the linked list, or null if the list is empty
     */
    @Override
    public T peekFirst() {
        if (this.size() == 0) return null;
        return this.head.element;
    }

    /**
     * Retrieves the last element of the linked list without removing it.
     *
     * @return the last element of the linked list, or null if the list is empty
     */
    @Override
    public T peekLast() {
        if (this.size() == 0) return null;
        return this.tail.element;
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param element the element to be added to the list
     */
    @Override
    public void add(T element) {
        this.addLast(element);
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param element the element to be added to the list
     */
    @Override
    public void addLast(T element) {
        this.add(this.size(), element);
    }

    /**
     * Adds an element to the beginning of the linked list.
     *
     * @param element the element to be added to the list
     */
    @Override
    public void addFirst(T element) {
        this.add(0, element);
    }

    /**
     * Adds an element at the specified index in the linked list.
     *
     * @param index   the position at which the element is to be inserted
     * @param element the element to be inserted
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = new Node<>(element);
        // If the list is empty, initialize head and tail
        if (this.isEmpty()) {
            this.head = node;
            this.tail = node;
            this.size = 1;
        } else {
            // Insert at the beginning
            if (index == 0) {
                node.next = this.head;
                this.head.previous = node;
                this.head = node;
            }
            // Insert at the end
            else if (index == this.size()) {
                this.tail.next = node;
                node.previous = tail;
                this.tail = node;
            }
            // Insert in the middle
            else {
                Node<T> current = this.head;
                for (int i = 0; i < index - 1; i++) {
                    current = current.next;
                }

                node.previous = current;
                node.next = current.next;
                current.next.previous = node;
                current.next = node;
            }
            this.size++;
        }
    }

    /**
     * Removes the first occurrence of the specified element from the linked list.
     *
     * @param element the element to be removed
     * @return true if the list contained the specified element, false otherwise
     */
    @Override
    public boolean remove(T element) {
        int index;
        if ((index = this.indexOf(element)) != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    /**
     * Removes and returns the first element from the linked list.
     *
     * @return the first element of the linked list, or null if the list is empty
     * @throws IndexOutOfBoundsException if the list is empty
     */
    @Override
    public T removeFirst() {
        return remove(0);
    }

    /**
     * Removes and returns the last element from the linked list.
     *
     * @return the last element of the linked list, or null if the list is empty
     * @throws IndexOutOfBoundsException if the list is empty
     */
    @Override
    public T removeLast() {
        return remove(this.size() - 1);
    }

    /**
     * Removes and returns the element at the specified index from the linked list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp;
        // Remove the first element
        if (index == 0) {
            temp = this.head.element;
            if (this.size() == 1) {
                this.tail = this.head = null;
                this.size = 0;
                return temp;
            } else {
                this.head = this.head.next;
                this.head.previous = null;
            }
        }
        // Remove the last element
        else if (index == this.size() - 1) {
            temp = this.tail.element;
            this.tail = this.tail.previous;
            this.tail.next = null;
        }
        // Remove an element from the middle
        else {
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            temp = current.next.element;
            current.next = current.next.next;
            current.next.previous = current;
        }

        size--;
        return temp;
    }

    /**
     * Checks if the linked list contains the specified element.
     *
     * @param element the element to be checked for presence in the list
     * @return true if the list contains the specified element, false otherwise
     */
    @Override
    public boolean contains(T element) {
        return this.indexOf(element) != -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the linked list.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element, or -1 if the element
     * is not found
     */
    @Override
    public int indexOf(T element) {
        Node<T> current = this.head;
        int i = 0;
        // Traverse the list to find the element
        while (current != null) {
            if ((current.element != null && current.element.equals(element)) ||
                    (current.element == null && element == null)) {
                break;
            }
            current = current.next;
            i++;
        }

        return current != null ? i : -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this.head);
    }

}