package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.TraversalType;

import java.util.*;

/**
 * Class contains an implementation of the AVL Tree (self-balancing tree).
 * Any data that's implement comparable interface is allowed.
 * AVL include method such as inserting, removing, containment
 * checks and traversal method that's returning String representation
 * of chosen Traversal Type. Distinct keys and no duplicates.
 *
 * @author Marcin Seweryn marcinsewerynn@gmail.com
 * @version 1.0
 * @see com.marcinseweryn.algorithms.datastructures.tree.TraversalType
 * @see Comparable
 */
public class AVL<T extends Comparable<T>> {

    private BinaryNode<T> root;
    private int size;

    /**
     * Create the empty AVL Tree
     */
    public AVL() {
        // Default constructor
    }

    public BinaryNode<T> getRoot() {
        return root;
    }

    /**
     * Return number of elements inside AVL
     *
     * @return number of elements inside
     * AVL tree
     */
    public int size() {
        return this.size;
    }

    /**
     * Insert specified element into the AVL Tree.
     * Return {@code true} if element was successfully
     * inserted.
     *
     * @param val to be inserted
     * @return {@code true} if element was successfully
     * inserted.
     */
    public boolean add(T val) {
        if (!contains(val) && val != null) {
            root = add(root, val);
            size++;
            return true;
        }
        return false;
    }

    private BinaryNode<T> add(BinaryNode<T> node, T val) {
        if (node == null) {
            node = new BinaryNode<>(val, 1);
            return node;
        }
        int compare = val.compareTo(node.val);

        if (compare < 0) {
            node.left = add(node.left, val);
        } else {
            node.right = add(node.right, val);
        }

        // Recursively update the height
        node.height = height(node);

        // Recursively check balance for parent
        int balance = getBalance(node);
        // Check if node is unbalanced
        if (balance > 1 && val.compareTo(node.left.val) < 0) { // val < node.left.val (LL)
            return rotateRight(node);
        } else if (balance > 1 && val.compareTo(node.left.val) > 0) { // val > node.left.val (LR)
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } else if (balance < -1 && val.compareTo(node.right.val) > 0) { // val > node.right.val (RR)
            return rotateLeft(node);
        } else if (balance < -1 && val.compareTo(node.right.val) < 0) { // vall < node.right.val (RL)
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        //Return updated node - no required rotation
        return node;
    }

    private BinaryNode<T> rotateRight(BinaryNode<T> node) {
        assert node != null;
        BinaryNode<T> newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.height = height(newRoot);
        node.height = height(node);
        return newRoot;
    }

    private BinaryNode<T> rotateLeft(BinaryNode<T> node) {
        BinaryNode<T> newRoot = node.right;
        assert newRoot != null;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.height = height(newRoot);
        node.height = height(node);
        return newRoot;
    }


    private int height(BinaryNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private int getBalance(BinaryNode<T> node) {
        if (node != null) {
            return height(node.left) - height(node.right);
        }
        return 0;
    }

    /**
     * Return {@code true} if AVL Tree contains specified
     * element
     *
     * @param val to check if exist in AVL Tree
     * @return {@code true} if AVL Tree contains specified
     * element
     */
    public boolean contains(T val) {
        return contains(root, val);
    }

    private boolean contains(BinaryNode<T> node, T val) {
        if (node == null) {
            return false;
        }
        int compare = val.compareTo(node.val);
        if (compare < 0) {
            return contains(node.left, val);
        } else if (compare > 0) {
            return contains(node.right, val);
        }
        return true;
    }

    /**
     * Remove specified element from the AVL Tree.
     * Return {@code true} if element was successfully
     * removed.
     *
     * @param val to be removed.
     * @return {@code true} if element was successfully
     * removed.
     */
    public boolean remove(T val) {
        if (root != null && contains(val)) {
            root = remove(root, val);
            size--;
            return true;
        } else {
            return false;
        }
    }

    private BinaryNode<T> remove(BinaryNode<T> node, T val) {
        int compare = val.compareTo(node.val);
        if (compare < 0) {
            node.left = remove(node.left, val);
        } else if (compare > 0) {
            node.right = remove(node.right, val);
        } else {

            // Node has 2 children
            if (node.left != null && node.right != null) {
                BinaryNode<T> successor = getSuccessor(node.right);
                node.val = successor.val;
                node.right = remove(node.right, successor.val);
            } else if (node.left != null) { // 1 child
                node = node.left;
            } else if (node.right != null) { // 1 child
                node = node.right;
            } else { // Leaf
                node = null;
                return node;
            }
        }
        node.height = height(node);

        // Tree is rebalanced if needed
        return rebalance(node);
    }

    private BinaryNode<T> rebalance(BinaryNode<T> node) {
        int balance = height(node.left) - height(node.right);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        } else if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } else if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        } else if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        // No need to rotate
        return node;
    }

    /**
     * Return minimum value in AVL Tree.
     *
     * @return minimum value in AVL Tree
     */
    public T min() {
        return minNode(root).val;
    }

    private BinaryNode<T> minNode(BinaryNode<T> node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    /**
     * Return maximum value in AVL Tree.
     *
     * @return maximum value in AVL Tree
     */
    public T max() {
        return maxNode(root).val;
    }

    private BinaryNode<T> maxNode(BinaryNode<T> node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }


    private BinaryNode<T> getSuccessor(BinaryNode<T> node) {
        if (node.left == null) {
            return node;
        }
        return getSuccessor(node.left);
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
            sb.append(current.val + ",");
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
        sb.append(node.val + ",");
        preOrder(node.left, sb);
        preOrder(node.right, sb);
    }

    private void inOrder(BinaryNode<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        inOrder(node.left, sb);
        sb.append(node.val + ",");
        inOrder(node.right, sb);
    }

    private void postOrder(BinaryNode<T> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        postOrder(node.left, sb);
        postOrder(node.right, sb);
        sb.append(node.val + ",");
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

    private Iterator<BinaryNode<T>> levelOrderIterator(BinaryNode<T> node, List<BinaryNode<T>> list) {
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


    private void postOrderIterator(BinaryNode<T> node, List<BinaryNode<T>> list) {
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

    private void preOrderIterator(BinaryNode<T> node, List<BinaryNode<T>> list) {
        if (node == null) {
            return;
        }
        list.add(node);
        preOrderIterator(node.left, list);
        preOrderIterator(node.right, list);
    }


    public void clear() {
        root = null;
    }

    public static class BinaryNode<E> {
        private BinaryNode<E> left;
        private BinaryNode<E> right;
        private E val;
        private int height = 0;

        public BinaryNode<E> getLeft() {
            return left;
        }

        public BinaryNode<E> getRight() {
            return right;
        }

        public E getVal() {
            return val;
        }

        public int getHeight() {
            return height;
        }

        public BinaryNode(E val, int height) {
            this.val = val;
            this.height = height;
        }
    }
}
