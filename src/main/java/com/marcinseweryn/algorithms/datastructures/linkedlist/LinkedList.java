package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class represent <strong>Doubly Linked List</strong> implementation
 * Implementing this interface allows an object to be the target of
 * the for each loop
 *
 * @see Iterable
 * @see Iterator
 */
public class LinkedList<T> implements Iterable<T> {
    private Node<T> head = null;
    private Node<T> tail = null;
    private static final String EMPTY_LIST = "list is empty";
    private int size = 0;


    /**
     * Return {@code true} if this list does not contain any elements.
     *
     * @return {@code true} if this list does not contain any elements.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return size of the list
     *
     * @return size of the list
     */
    public int size() {
        return this.size;
    }

    /**
     * Insert the specified element at the specified index in the list. If any
     * element occupied specified index then shifts the element currently at
     * that position.
     *
     * @param index   - specifies where the element should be placed
     * @param element - element to be inserted
     * @throws IndexOutOfBoundsException - if the index is out of range
     *                                   (index < 0 || index > size)
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Not able to insert an " +
                                                        "element(" + element +
                                                        ") on the index(" + index + ")\n" + "Accept only (index " +
                                                        ">= 0 && index <= " +
                                                        "size(" + size + ")");
        }
        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            Node<T> node = new Node<>(element, temp, temp.next);
            temp.next.prev = node;
            temp.next = node;
            size++;
        }
    }

    /**
     * Insert the element at the beginning of the list
     *
     * @param element - insert first element to the list
     */
    public void addFirst(T element) {
        Node<T> node = new Node<>(element, null, head);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            head.prev = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    /**
     * Insert as the last element into the list
     *
     * @param element to insert as last
     */
    public void addLast(T element) {

        // In case of empty List tail = null
        Node<T> node = new Node<>(element, tail, null);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Insert as the last element into the list
     *
     * @param element to insert as last
     */
    public void add(T element) {
        addLast(element);
    }

    /**
     * Return the initial element from the list and do not remove it
     *
     * @return the first element from the list
     * @throws NoSuchElementException if list is empty
     */
    public T peekFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST);
        }
        return head.value;
    }

    /**
     * Return the last element from the list and do not remove it
     *
     * @return the last element from the list
     * @throws NoSuchElementException if list is empty
     */
    public T peekLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST);
        }
        return tail.value;
    }

    /**
     * Return the first element from the list and remove it
     *
     * @return the first element from the list
     * @throws NoSuchElementException if list is empty
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST);
        } else {
            T temp = head.value;
            head = head.next;

            // In case size == 1
            if (head != null) {
                head.prev = null;
            }
            size--;
            return temp;
        }
    }

    /**
     * Return the last element from the list and remove it
     *
     * @return the last element from the list
     * @throws NoSuchElementException if list is empty
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST);
        } else {
            T temp = tail.value;

            // In case size == 1
            if (tail.prev != null) {
                tail.prev.next = null;
            }
            tail = tail.prev;
            size--;
            return temp;
        }
    }


    /**
     * Remove the first occurrence of the specified object if
     * present in the list, otherwise do nothing
     *
     * @param element to remove
     * @return true if successfully removed element from the list
     * or false if element does not exist in the list
     */
    public boolean remove(T element) {
        int i = 0;
        for (Node<T> node = head; node != null; node = node.next) {
            if (node.value.equals(element)) {
                remove(i);
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Remove and return the element that's located on given index
     * if present in the list, otherwise do nothing
     *
     * @param index represents the location of an object
     * @return element at the specified index from the list
     * @throws NoSuchElementException    if list is empty
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index > size)
     */
    public T remove(int index) {
        if (isEmpty()) {
            throw new NoSuchElementException(EMPTY_LIST);
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Not able to remove an " +
                                                        "element on the " +
                                                        "index(" + index + ")" +
                                                        "\n" + "Accept only " +
                                                        "(index >= 0 && " +
                                                        "index < size)");
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            T result = temp.next.value;
            temp.next.next.prev = temp;
            temp.next = temp.next.next;
            size--;
            return result;
        }
    }

    /**
     * Removes every element from the list. After {@code this.clear()}
     * list will be empty
     */
    public void clear() {
        for (Node<T> x = head; x != null; x = x.next) {
            x.prev = null;
            x.value = null;
        }
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Return {@code true} if the list contains a specified element.
     *
     * @param element whose existence is being tested
     * @return {@code true} if the list contains the element
     */
    public boolean contains(T element) {
        for (Node<T> x = head; x != null; x = x.next) {
            if (x.value.equals(element)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Return the String that will represent elements that are stored inside
     * of the list
     *
     * @return String representation of the linked list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        int i = 0;
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (i < size - 1) {
                sb.append(",");
            }
            i++;
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Return an iterator over a collection.
     *
     * @return Iterator object
     * @see Iterator
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            Node<T> previous = null;

            @Override
            public boolean hasNext() {
                if (current != null) {
                    return current.next != null;
                }
                return false;
            }

            @Override
            public T next() {
                if (current.value == null) {
                    throw new NoSuchElementException();
                }
                T val = current.value;
                previous = current;
                current = current.next;
                return val;
            }

        };
    }


    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        public Node(T value, Node<T> prev, Node<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

}
