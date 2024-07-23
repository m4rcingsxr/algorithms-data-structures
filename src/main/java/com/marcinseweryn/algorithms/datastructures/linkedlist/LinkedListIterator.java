package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedListIterator<T> implements Iterator<T> {

    private Node<T> current;
    private final Node<T> head;
    private boolean started;

    public LinkedListIterator(Node<T> start) {
        this.head = start;
        this.current = start;
        this.started = false;
    }

    @Override
    public boolean hasNext() {
        return current != null && (!started || current != head);
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T temp = current.element;
        current = current.next;
        started = true;
        return temp;
    }

}
