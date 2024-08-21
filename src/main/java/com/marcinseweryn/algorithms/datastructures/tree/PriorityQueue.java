package com.marcinseweryn.algorithms.datastructures.tree;

public interface PriorityQueue<T extends Comparable<T>> extends BinaryTree<T> {

    T peek();

    T poll();

}
