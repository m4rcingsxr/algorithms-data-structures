package com.marcinseweryn.algorithms.datastructures.queue;

import java.util.Iterator;

public interface Queue<T> extends Iterable<T> {

    int size();

    boolean isEmpty();

    T peek();

    void enqueue(T element);

    T dequeue();

    @Override
    Iterator<T> iterator();
}
