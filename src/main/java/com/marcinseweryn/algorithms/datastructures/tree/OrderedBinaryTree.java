package com.marcinseweryn.algorithms.datastructures.tree;

public interface OrderedBinaryTree<T extends Comparable<T>> extends BinaryTree<T> {

    T min();

    T max();

}
