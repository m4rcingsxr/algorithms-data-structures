package com.marcinseweryn.algorithms.datastructures.tree;

public class BinaryNode<T> {

    public T element;
    public BinaryNode<T> left;
    public BinaryNode<T> right;
    public int height;

    public BinaryNode(T element) {
        this.element = element;
    }
}
