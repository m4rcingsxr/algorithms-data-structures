package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;

/**
 * A generic singly linked list implementation that supports basic operations such as adding, removing,
 * and searching for elements. This list allows for efficient insertion and removal at the beginning
 * and end of the list, but is less efficient for middle insertions and deletions.
 *
 * @param <T> the type of elements held in this SinglyLinkedList
 */
public class SinglyLinkedList<T> implements LinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Returns the number of elements in the linked list.
     *
     * @return the number of elements in the linked list
     */
    public int size() {
        return this.size;
    }

    /**
     * Checks if the linked list is empty.
     *
     * @return true if the linked list contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Retrieves the first element of the linked list without removing it.
     *
     * @return the first element of the linked list, or null if the list is empty
     */
    public T peekFirst() {
        if (this.isEmpty()) return null;
        return this.head.element;
    }

    /**
     * Retrieves the last element of the linked list without removing it.
     *
     * @return the last element of the linked list, or null if the list is empty
     */
    public T peekLast() {
        if (this.isEmpty()) return null;
        return this.tail.element;
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param element the element to be added to the list
     */
    public void add(T element) {
        this.addLast(element);
    }

    /**
     * Adds an element to the end of the linked list.
     *
     * @param element the element to be added to the list
     */
    public void addLast(T element) {
        this.add(this.size(), element);
    }

    /**
     * Adds an element to the beginning of the linked list.
     *
     * @param element the element to be added to the list
     */
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
    public void add(int index, T element) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> node = new Node<>(element);

        // If the list is empty, initialize head and tail
        if (this.isEmpty()) {
            this.head = this.tail = node;
            this.size = 1;
        } else {
            // Insert at the beginning
            if (index == 0) {
                node.next = this.head;
                this.head = node;
            }
            // Insert at the end
            else if (index == this.size()) {
                this.tail.next = node;
                this.tail = node;
            }
            // Insert in the middle
            else {
                Node<T> current = findPrecedingElement(index);
                node.next = current.next;
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
     * @return the first element of the linked list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public T removeFirst() {
        return this.remove(0);
    }

    /**
     * Removes and returns the last element from the linked list.
     *
     * @return the last element of the linked list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public T removeLast() {
        return this.remove(this.size() - 1);
    }

    /**
     * Removes and returns the element at the specified index from the linked list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    public T remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp;
        // Remove the first element
        if (index == 0) {
            temp = this.head.element;
            if (this.size() == 1) {
                this.head = this.tail = null;
            } else {
                this.head = this.head.next;
            }
        }
        // Remove the last element
        else if (index == this.size() - 1) {
            temp = this.tail.element;
            this.tail = this.findPrecedingElement(index);
            this.tail.next = null;
        }
        // Remove an element from the middle
        else {
            Node<T> preceding = this.findPrecedingElement(index);
            temp = preceding.next.element;
            preceding.next = preceding.next.next;
        }

        this.size--;
        return temp;
    }

    /**
     * Finds the node that precedes the element at the specified index.
     *
     * @param index the index of the target element
     * @return the node preceding the target element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     */
    private Node<T> findPrecedingElement(int index) {
        Node<T> current = this.head;
        // Traverse the list to find the preceding node
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Checks if the linked list contains the specified element.
     *
     * @param element the element to be checked for presence in the list
     * @return true if the list contains the specified element, false otherwise
     */
    public boolean contains(T element) {
        return this.indexOf(element) != -1;
    }

    /**
     * Returns the index of the first occurrence of the specified element in the linked list.
     *
     * @param element the element to search for
     * @return the index of the first occurrence of the specified element, or -1 if the element is not found
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

    /**
     * Returns an iterator over the elements in this linked list in proper sequence.
     *
     * @return an iterator over the elements in this linked list
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this.head);
    }

}
