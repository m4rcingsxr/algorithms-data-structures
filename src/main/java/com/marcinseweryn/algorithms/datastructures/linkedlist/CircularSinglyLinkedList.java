
package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;

public class CircularSinglyLinkedList<T> implements LinkedList<T> {

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
        if(this.isEmpty()) return null;
        return this.head.element;
    }

    @Override
    public T peekLast() {
        if(this.isEmpty()) return null;
        return this.tail.element;
    }

    @Override
    public void add(T element) {
        this.addLast(element);
    }

    @Override
    public void addLast(T element) {
        this.add(this.size(), element);
    }

    @Override
    public void addFirst(T element) {
        this.add(0, element);
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> node = new Node<>(element);
        if(this.isEmpty()) {
            this.tail = this.head = node;
            this.tail.next = this.head.previous = node;
        } else {
            if(index == 0) {
                node.next = this.head;
                tail.next = node;
                this.head = node;
            } else if (index == this.size()) {
                this.tail.next = node;
                node.next = this.head;
                this.tail = node;
            } else {
                Node<T> current = this.findPrecedingElement(index);
                node.next = current.next;
                current.next = node;
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
        if((index = this.indexOf(element)) != -1) {
            this.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public T removeFirst() {
        return this.remove(0);
    }

    @Override
    public T removeLast() {
        return this.remove(this.size() - 1);
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }

        T temp;
        if (index == 0) {
            temp = this.head.element;
            if(this.size() == 1) {
                this.tail = this.head = null;
            } else {
                this.head = this.head.next;
                this.tail.next = this.head;
            }
        } else if(index == this.size() - 1) {
            Node<T> preceding = this.findPrecedingElement(index);
            temp = this.tail.element;
            preceding.next = this.head;
            this.tail = preceding;
        } else {
            Node<T> preceding = this.findPrecedingElement(index);
            temp = preceding.next.element;
            preceding.next = preceding.next.next;
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

        return -1;  // Element not found
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this.head);
    }
}
