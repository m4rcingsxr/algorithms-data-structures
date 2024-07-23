package com.marcinseweryn.algorithms.datastructures.linkedlist;

import java.util.Iterator;

public interface LinkedList<T> extends Iterable<T> {
    boolean isEmpty();

    int size();

    T peekFirst();

    T peekLast();

    void add(T element);

    void addLast(T element);

    void addFirst(T element);

    void add(int index, T element);

    boolean remove(T element);

    T removeFirst();

    T removeLast();

    T remove(int index);

    boolean contains(T element);

    int indexOf(T element);

    @Override
    Iterator<T> iterator();
}
