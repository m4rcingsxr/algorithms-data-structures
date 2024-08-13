package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.queue.LinkedListQueue;
import com.marcinseweryn.algorithms.datastructures.queue.Queue;
import com.marcinseweryn.algorithms.datastructures.tree.BinaryNode;
import com.marcinseweryn.algorithms.datastructures.tree.BinaryTree;

import java.util.Iterator;

/**
 * A class representing a binary tree implemented using linked nodes. The tree
 * stores elements in level order.
 *
 * @param <T> the type of elements held in this tree, must be comparable
 */
public class BinaryTreeLinkedList<T extends Comparable<T>> implements BinaryTree<T> {

    private BinaryNode<T> root;
    private int size;

    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree has no elements, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Returns the number of elements in the tree.
     *
     * @return the size of the tree
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Adds an element to the tree in level order. Duplicate elements are not allowed.
     *
     * @param element the element to add
     * @return true if the element was added, false if it already exists in the tree
     */
    @Override
    public boolean add(T element) {
        if (this.contains(element)) return false;

        BinaryNode<T> node = new BinaryNode<>(element);
        if (this.isEmpty()) {
            this.root = node;
        } else {
            Queue<BinaryNode<T>> queue = new LinkedListQueue<>();
            queue.enqueue(this.root);

            // Perform level-order traversal until we find an empty spot
            while (!queue.isEmpty()) {
                BinaryNode<T> current = queue.dequeue();

                // Check left child
                if (current.left == null) {
                    current.left = node;
                    break;
                }

                // Check right child
                if (current.right == null) {
                    current.right = node;
                    break;
                }

                // Continue level-order traversal
                queue.enqueue(current.left);
                queue.enqueue(current.right);
            }
        }
        size++;
        return true;
    }

    /**
     * Removes the element from the tree if it exists.
     *
     * @param element the element to remove
     * @return true if the element was found and removed, false otherwise
     */
    @Override
    public boolean remove(T element) {
        if (this.isEmpty()) return false;

        Queue<BinaryNode<T>> queue = new LinkedListQueue<>();
        queue.enqueue(this.root);
        BinaryNode<T> targetNode = null;
        BinaryNode<T> current;

        // Perform level-order traversal to find the node to remove
        while (!queue.isEmpty()) {
            current = queue.dequeue();
            if (current.element.equals(element)) {
                targetNode = current;
                break;
            }
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }

        // If the element is not found, return false
        if (targetNode == null) return false;

        // Replace the target node's element with the deepest node's element and remove the deepest node
        BinaryNode<T> deepestNode = deleteDeepestNode();
        if (deepestNode != null) {
            targetNode.element = deepestNode.element;
            if(this.size > 0) this.size--; // ensure clear() was not called
            return true;
        }
        return false;
    }

    /**
     * Deletes the deepest node in the tree and returns it.
     *
     * @return the deepest node that was deleted, or null if the tree was empty
     */
    private BinaryNode<T> deleteDeepestNode() {
        Queue<BinaryNode<T>> queue = new LinkedListQueue<>();
        queue.enqueue(this.root);

        BinaryNode<T> previous = null;
        BinaryNode<T> temp = null;
        BinaryNode<T> current = null;

        while(!queue.isEmpty()) {
            previous = current;
            current = queue.dequeue();

            if(current.left == null) { // leaf
                if(previous == null) { // only root
                    temp = this.root;
                    this.clear();
                    return temp;
                }
                temp = previous.right;
                previous.right = null;
                return temp;
            } else if(current.right == null) { // 1 child
                temp = current.left;
                current.left = null;
                return temp;
            }

            queue.enqueue(current.left);
            queue.enqueue(current.right);
        }

        return null;
    }

    /**
     * Checks if the tree contains the specified element.
     *
     * @param element the element to check for
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        if (this.isEmpty()) return false;

        Queue<BinaryNode<T>> queue = new LinkedListQueue<>();
        queue.enqueue(this.root);

        // Perform level-order traversal to find the element
        while (!queue.isEmpty()) {
            BinaryNode<T> current = queue.dequeue();
            if (current.element.equals(element)) {
                return true;
            }
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }
        }
        return false;
    }


    /**
     * Clears the tree by removing all elements.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public Iterator<T> levelOrderIterator() {
        return BinaryTreeIteratorFactory.levelOrderIterator(this.root);
    }

    @Override
    public Iterator<T> inOrderIterator() {
        return BinaryTreeIteratorFactory.inOrderIterator(this.root);
    }

    @Override
    public Iterator<T> postOrderIterator() {
        return BinaryTreeIteratorFactory.postOrderIterator(this.root);
    }

    @Override
    public Iterator<T> preOrderIterator() {
        return BinaryTreeIteratorFactory.preOrderIterator(this.root);
    }
}

