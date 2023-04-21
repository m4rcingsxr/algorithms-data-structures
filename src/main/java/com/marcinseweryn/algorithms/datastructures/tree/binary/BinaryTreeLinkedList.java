package com.marcinseweryn.algorithms.datastructures.tree.binary;


import com.marcinseweryn.algorithms.datastructures.tree.TraversalType;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Class represent Linked List implementation of Binary Tree
 */
public class BinaryTreeLinkedList<T> {
    BinaryNode<T> root;

    /**
     * Construct empty Binary Tree
     */
    public BinaryTreeLinkedList() {
        // Default constructor
    }


    /**
     * Return {@code true} if BT contains no elements
     *
     * @return {@code true} if BT contains no elements
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert specified element into Binary Tree.
     *
     * @param value to be inserted to BT
     */
    public void add(T value) {
        if (isEmpty()) {
            root = new BinaryNode<>(value, null, null);
        } else {
            BinaryNode<T> newNode = new BinaryNode<>(value, null, null);
            Queue<BinaryNode<T>> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                BinaryNode<T> current = queue.remove();
                if (current.left == null) {
                    current.left = newNode;
                    break;
                } else if (current.right == null) {
                    current.right = newNode;
                    break;
                } else {
                    queue.add(current.left);
                    queue.add(current.right);
                }
            }
        }
    }

    /**
     * Removes specified element from the BT
     *
     * @param value to be removed from BT
     */
    public void remove(T value) {
        if (isEmpty()) {
            throw new IllegalStateException("BT is empty");
        }
        Queue<BinaryNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        BinaryNode<T> current;
        do {
            current = queue.remove();
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        } while (!queue.isEmpty() && current.value != value);

        if (current.value != value) {
            throw new NoSuchElementException("Value does not exist in BT");
        } else {
            BinaryNode<T> deepestNode = deleteDeepestNode();
            if (deepestNode != null) {
                current.value = deleteDeepestNode().value;
            }
        }
    }


    private BinaryNode<T> deleteDeepestNode() {
        if (root.left == null) {
            clear();
        } else {
            Queue<BinaryNode<T>> queue = new ArrayDeque<>();
            queue.add(root);
            BinaryNode<T> current = null;
            BinaryNode<T> previous;
            BinaryNode<T> temp;
            while (!queue.isEmpty()) {
                previous = current;
                current = queue.remove();
                if (current.left == null) {
                    if (previous == null) {
                        temp = current;
                        clear();
                        return temp;
                    }
                    temp = current;
                    previous.right = null;
                    return temp;
                } else if (current.right == null) {
                    temp = current.left;
                    current.left = null;
                    return temp;
                }
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        return root;
    }

    /**
     * Return {@code true} if BT contains specified element
     *
     * @param element to check if it exists in BT
     * @return {@code true} if BT contains specified element
     */
    public boolean contains(T element) {
        Queue<BinaryNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        BinaryNode<T> current;
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.value.equals(element)) {
                return true;
            }
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return false;
    }

    /**
     * Return chosen type of traversal representation
     * as String
     *
     * @return traversal representation as String
     */
    public String traversal(TraversalType type) {
        StringBuilder sb = new StringBuilder("[");
        switch (type) {

            // Java 14
            case PREORDER -> preOrder(root, sb);
            case INORDER -> inOrder(root, sb);
            case POSTORDER -> postOrder(root, sb);
            case LEVELORDER -> levelOrder(sb);
            default -> throw new IllegalArgumentException("No case");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    private void levelOrder(StringBuilder sb) {
        Queue<BinaryNode<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<T> current = queue.remove();
            sb.append(current.value + ",");
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    private void preOrder(BinaryNode<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.value + ",");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }

    private void inOrder(BinaryNode<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrder(node.left, sb);
        sb.append(node.value + ",");
        inOrder(node.right, sb);
    }

    private void postOrder(BinaryNode<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postOrder(node.left, sb);
        postOrder(node.right, sb);
        sb.append(node.value + ",");
    }


    /**
     * Remove all elements from the BT
     */
    public void clear() {
        root = null;
    }


    private static class BinaryNode<E> {
        private BinaryNode<E> left;
        private BinaryNode<E> right;
        private E value;

        public BinaryNode(E value, BinaryNode<E> left, BinaryNode<E> right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
