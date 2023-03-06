package com.marcinseweryn.algorithms.graphs.list;

import java.util.ArrayList;
import java.util.List;

public class GraphNode<E> {
    private final E element;

    private final List<GraphNode<E>> neighbors;

    public E getElement() {
        return element;
    }

    public List<GraphNode<E>> getNeighbors() {
        return this.neighbors;
    }

    public GraphNode(E element) {
        this.element = element;
        this.neighbors = new ArrayList<>();
    }
}