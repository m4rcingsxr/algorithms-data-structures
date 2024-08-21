package com.marcinseweryn.algorithms.datastructures.tree;

import java.util.Iterator;

// level order iterator
public interface BinaryTree <T> {

    boolean isEmpty();

    int size();

    boolean add(T element);

    boolean remove(T element);

    boolean contains(T element);

    void clear();

    Iterator<T> levelOrderIterator();

    Iterator<T> inOrderIterator();

    Iterator<T> postOrderIterator();

    Iterator<T> preOrderIterator();

}
