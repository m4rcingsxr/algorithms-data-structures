package com.marcinseweryn.algorithms.datastructures.tree.binary;


import com.marcinseweryn.algorithms.datastructures.tree.TraversalType;

import java.util.*;

/**
 * Class contains an implementation of a Binary Search Tree.
 * Any data that's implement comparable interface is allowed.
 * BST include method such as inserting, removing, containment
 * checks and traversal method that's returning String representation
 * of chosen Traversal Type. Distinct keys and no duplicates.
 *
 * @author Marcin Seweryn
 * @version 1.0
 * @see com.marcinseweryn.algorithms.datastructures.tree.TraversalType
 * @see Comparable
 */
public class BST<T extends Comparable<T>> {
    private BinaryNode<T> root;
    private int size = 0;

    /**
     * Return {@code true} if BST is empty
     *
     * @return {@code true} if BST is empty
     */
    public boolean isEmpty() {
        return size == 0 && root == null;
    }

    /**
     * Return {@code true} if element was successfully inserted
     *
     * @param value - specified to be inserted
     * @return {@code true} if element was successfully inserted
     */
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        } else {
            root = add(root, value);
            return true;
        }
    }

    private BinaryNode<T> add(BinaryNode<T> node, T value) {
        if (node == null) {
            node = new BinaryNode<>(value);
            node.height = 1;
            size++;
            return node;

            // (value - node.left.value) < 0 <=> node.left.value > value
        } else if (value.compareTo(node.value) < 0) {
            node.left = add(node.left, value);
        } else {
            node.right = add(node.right, value);
        }

        node.height = height(node);
        // Most of the time it remains the same
        return node;
    }

    /**
     * Return {@code true} if element was successfully deleted
     *
     * @param value - specified to be delete
     * @return {@code true} if element was successfully deleted
     */
    public boolean remove(T value) {
        if (contains(value)) {
            root = remove(root, value);
            size--;
            return true;
        }
        return false;
    }

    private BinaryNode<T> remove(BinaryNode<T> node, T value) {
        if (node == null) {
            return null;
        }
        int compare = value.compareTo(node.value);

        // Find value
        if (compare < 0) {
            node.left = remove(node.left, value);
        } else if (compare > 0) {
            node.right = remove(node.right, value);
        } else {

            // Case 1: 2 Children
            if (node.left != null && node.right != null) {

                // find inorder successor of right subtree for node
                // or predecessor(largest value in left sub tree)
                BinaryNode<T> successor = minNode(node.right);
                node.value = successor.value;

                // Remove inorder successor of removed node from right subtree
                node.right = remove(node.right, successor.value);

                // Case 2: 1 Children
            } else if (node.left != null) {
                node = node.left;
            } else if (node.right != null) {
                node = node.right;

                // Case 3: Leaf
            } else {

                // Null will be assigned as left or right child of parent
                return null;
            }
        }
        node.height = height(node);
        return node;
    }

    /**
     * Return minimum value in BST.
     *
     * @return minimum value in BST
     */
    public T min() {
        return minNode(root).value;
    }

    private BinaryNode<T> minNode(BinaryNode<T> node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    /**
     * Return maximum value in BST.
     *
     * @return maximum value in BST
     */
    public T max() {
        return maxNode(root).value;
    }

    private BinaryNode<T> maxNode(BinaryNode<T> node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }


    private int height(BinaryNode<T> node) {

        // (Both)Leaf
        if (node == null) {
            return 0;
        }

        // Recursively find height for both subtrees of max()
        return 1 + Math.max(height(node.right), height(node.left));
    }

    /**
     * Return {@code true} if element exist in the BST
     *
     * @param value to check if contains in the BST
     * @return {@code true} if element exist in the BST
     */
    public boolean contains(T value) {
        return contains(root, value);
    }

    private boolean contains(BinaryNode<T> node, T value) {
        if (node == null) {
            return false;
        }
        int compare = value.compareTo(node.value);
        if (compare < 0) {
            return contains(node.left, value);
        } else if (compare > 0) {
            return contains(node.right, value);
        }
        return node.value == value;
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

    public Iterator<BinaryNode<T>> traverseIterator(TraversalType type) {
        List<BinaryNode<T>> list = new ArrayList<>();
        switch (type) {
            case PREORDER -> {
                preOrderIterator(root, list);
                return iter(list);
            }
            case INORDER -> {
                inOrderIterator(root, list);
                return iter(list);
            }
            case POSTORDER -> {
                postOrderIterator(root, list);
                return iter(list);
            }
            case LEVELORDER -> {
                return levelOrderIterator(root, list);
            }
            default -> {
                return null;
            }
        }
    }


    private Iterator<BinaryNode<T>> iter(List<BinaryNode<T>> list) {

        final int nodeCount = size;
        final Iterator<BinaryNode<T>> iterator = list.iterator();

        return new Iterator<BinaryNode<T>>() {
            @Override
            public boolean hasNext() {
                if (nodeCount != size) {
                    throw new ConcurrentModificationException();
                }
                return iterator.hasNext();
            }

            @Override
            public BinaryNode<T> next() {
                return iterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }

    private void postOrderIterator(BinaryNode<T> node,
                                   List<BinaryNode<T>> list) {
        if (node == null) {
            return;
        }
        postOrderIterator(node.left, list);
        postOrderIterator(node.right, list);
        list.add(node);
    }


    private void inOrderIterator(BinaryNode<T> node, List<BinaryNode<T>> list) {
        if (node == null) {
            return;
        }
        inOrderIterator(node.left, list);
        list.add(node);
        inOrderIterator(node.right, list);
    }

    private void preOrderIterator(BinaryNode<T> node,
                                  List<BinaryNode<T>> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        preOrderIterator(node.left, list);
        preOrderIterator(node.right, list);
    }

    private Iterator<BinaryNode<T>> levelOrderIterator(BinaryNode<T> node,
                                                       List<BinaryNode<T>> list) {
        Queue<BinaryNode<T>> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            BinaryNode<T> current = queue.remove();
            list.add(current);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }

        final int nodeCount = size;
        final Iterator<BinaryNode<T>> iterator = list.iterator();

        return new Iterator<BinaryNode<T>>() {
            @Override
            public boolean hasNext() {
                if (nodeCount != size) {
                    throw new ConcurrentModificationException();
                }
                return iterator.hasNext();
            }

            @Override
            public BinaryNode<T> next() {
                return iterator.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }


    public void clear() {
        root = null;
    }

    public int size() {
        return this.size;
    }

    static class BinaryNode<E> {
        private BinaryNode<E> left;
        private BinaryNode<E> right;
        private E value;
        private int height;

        public E getValue() {
            return value;
        }

        public int getHeight() {
            return height;
        }

        public BinaryNode(E value) {
            this.value = value;
        }
    }

}

