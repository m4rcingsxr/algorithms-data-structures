package com.marcinseweryn.algorithms.datastructures.stack;

import java.util.Iterator;

public interface Stack<T> extends Iterable<T> {

    int size();

    boolean isEmpty();

    T pop();

    void push(T elem);

    T peek();

    @Override
    public Iterator<T> iterator();

}
