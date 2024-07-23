package com.marcinseweryn.algorithms.datastructures.linkedlist;

public class Node<T> {

    protected T element;
    protected Node<T> previous;
    protected Node<T> next;

    Node(T element, Node<T> previous, Node<T> next) {
        this.element = element;
        this.previous = previous;
        this.next = next;
    }

    Node(T element) {
        this.element = element;
    }
}
