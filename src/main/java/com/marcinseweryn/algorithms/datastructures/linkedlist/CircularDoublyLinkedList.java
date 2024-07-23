package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;

public class CircularDoublyLinkedList<T> implements LinkedList<T> {

    protected Node<T> head;
    protected Node<T> tail;
    private int size;

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T peekFirst() {
        if (this.isEmpty()) return null;
        return this.head.element;
    }

    @Override
    public T peekLast() {
        if (this.isEmpty()) return null;
        return this.tail.element;
    }

    @Override
    public void add(T element) {
        this.add(this.size(), element);
    }

    @Override
    public void addLast(T element) {
        this.add(0, element);
    }

    @Override
    public void addFirst(T element) {
        this.add(this.size(), element);
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = new Node<>(element);
        if (this.isEmpty()) {
            this.head = this.tail = node;
            this.head.next = this.tail;
            this.head.previous = this.tail;
        } else {
            if (index == 0) {
                node.next = this.head;
                node.previous = this.tail;
                this.head.previous = node;
                this.head = node;
                this.tail.next = this.head;
            } else if (index == this.size()) {
                node.previous = this.tail;
                node.next = this.head;
                this.head.previous = node;
                this.tail.next = node;
                this.tail = node;
            } else {
                Node<T> preceding = findPrecedingElement(index);
                node.previous = preceding;
                node.next = preceding.next;
                preceding.next.previous = node;
                preceding.next = node;
            }
        }
        this.size++;

    }

    private Node<T> findPrecedingElement(int index) {
        Node<T> current = this.head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public boolean remove(T element) {
        int index;
        if ((index = indexOf(element)) != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) return null;
        return this.remove(0);
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) return null;
        return this.remove(this.size() - 1);
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp;
        if (index == 0) {
            temp = this.head.element;
            if(this.size() == 1) {
               this.head = this.tail = null;
            } else {
                this.tail.next = this.head.next;
                this.head = this.head.next;
                this.head.previous = this.tail;
            }
        } else if (index == this.size() - 1) {
            temp = this.tail.element;
            this.tail = this.tail.previous;
            this.tail.next = this.head;
            this.head.previous = this.tail;
        } else {
            Node<T> preceding = findPrecedingElement(index);
            temp = preceding.next.element;
            preceding.next = preceding.next.next;
            preceding.next.previous = preceding;
        }
        this.size--;
        return temp;
    }

    @Override
    public boolean contains(T element) {
        return this.indexOf(element) != -1;
    }

    @Override
    public int indexOf(T element) {
        if (this.isEmpty()) return -1;  // Return -1 if the list is empty

        Node<T> current = this.head;

        for (int i = 0; i < this.size(); i++) {
            if ((current.element != null && current.element.equals(element)) ||
                    (current.element == null && element == null)) {
                return i;
            }
            current = current.next;
        }

        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this.head);
    }
}
