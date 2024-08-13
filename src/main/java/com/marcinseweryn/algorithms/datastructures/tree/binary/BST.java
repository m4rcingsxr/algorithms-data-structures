package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.BinaryNode;
import com.marcinseweryn.algorithms.datastructures.tree.OrderedBinaryTree;

import java.util.Iterator;

/**
 * A binary search tree (BST) implementation that stores elements in an ordered fashion.
 * Elements must implement the {@code Comparable} interface to ensure proper ordering.
 *
 * @param <T> the type of elements maintained by this BST, which must be comparable
 */
public class BST<T extends Comparable<T>> implements OrderedBinaryTree<T> {

    private BinaryNode<T> root;  // The root node of the BST
    private int size;            // Number of elements in the BST

    /**
     * Retrieves the minimum element in the BST.
     *
     * @return the minimum element in the BST, or {@code null} if the tree is empty
     */
    @Override
    public T min() {
        // If the tree is empty, return null
        if (this.root == null) {
            return null;
        }
        // Delegate to the helper method to find the minimum element
        return this.min(this.root).element;
    }

    /**
     * Helper method to find the minimum node starting from a given node.
     *
     * @param node the node from which to find the minimum element
     * @return the node containing the minimum element
     */
    private BinaryNode<T> min(BinaryNode<T> node) {
        // If there is no left child, this node is the minimum
        if (node.left == null) {
            return node;
        }
        // Recursively call min on the left child to find the minimum element
        return this.min(node.left);
    }

    /**
     * Retrieves the maximum element in the BST.
     *
     * @return the maximum element in the BST, or {@code null} if the tree is empty
     */
    @Override
    public T max() {
        // If the tree is empty, return null
        if (this.root == null) {
            return null;
        }
        // Delegate to the helper method to find the maximum element
        return this.max(this.root).element;
    }

    /**
     * Helper method to find the maximum node starting from a given node.
     *
     * @param node the node from which to find the maximum element
     * @return the node containing the maximum element
     */
    private BinaryNode<T> max(BinaryNode<T> node) {
        // If there is no right child, this node is the maximum
        if (node.right == null) {
            return node;
        }
        // Recursively call max on the right child to find the maximum element
        return this.max(node.right);
    }

    /**
     * Checks if the BST is empty.
     *
     * @return {@code true} if the tree is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        // The tree is empty if the size is 0
        return this.size == 0;
    }

    /**
     * Returns the number of elements in the BST.
     *
     * @return the size of the BST
     */
    @Override
    public int size() {
        // Return the number of elements in the tree
        return this.size;
    }

    /**
     * Adds an element to the BST.
     *
     * @param element the element to add
     * @return {@code true} if the element was added successfully, {@code false} if the element already exists
     */
    @Override
    public boolean add(T element) {
        // If the element already exists, do not add it again
        if (this.contains(element)) return false;
        // Insert the element into the tree starting from the root
        this.root = this.add(this.root, element);
        return true;
    }

    /**
     * Helper method to add an element to the BST starting from a given node.
     *
     * @param node the current node in the BST
     * @param element the element to add
     * @return the node after insertion
     */
    public BinaryNode<T> add(BinaryNode<T> node, T element) {
        // If the current node is null, we've found the insertion point
        if (node == null) {
            node = new BinaryNode<>(element);  // Create a new node with the element
            size++;  // Increment the size of the tree
            return node;  // Return the newly created node
        } else if (node.element.compareTo(element) > 0) {
            // If the element is smaller than the current node, insert into the left subtree
            node.left = this.add(node.left, element);
        } else {
            // If the element is greater than or equal to the current node, insert into the right subtree
            node.right = this.add(node.right, element);
        }
        // Return the (potentially updated) current node
        return node;
    }

    /**
     * Removes an element from the BST.
     *
     * @param element the element to remove
     * @return {@code true} if the element was removed successfully, {@code false} if the element was not found
     */
    @Override
    public boolean remove(T element) {
        // If the element does not exist in the tree, return false
        if (!this.contains(element)) return false;
        // Remove the element from the tree starting from the root
        this.root = this.remove(this.root, element);
        this.size--;  // Decrement the size of the tree
        return true;
    }

    /**
     * Helper method to remove an element from the BST starting from a given node.
     *
     * @param node the current node in the BST
     * @param element the element to remove
     * @return the node after removal
     */
    private BinaryNode<T> remove(BinaryNode<T> node, T element) {
        // If the current node is null, we've reached the end without finding the element
        if (node == null) {
            return null;
        }

        // Traverse the tree to find the node to remove
        if (node.element.compareTo(element) > 0) {
            // If the element is smaller, continue searching in the left subtree
            node.left = this.remove(node.left, element);
        } else if (node.element.compareTo(element) < 0) {
            // If the element is greater, continue searching in the right subtree
            node.right = this.remove(node.right, element);
        } else {
            // Found the node to remove

            // Case 1: The node has two children
            if (node.left != null && node.right != null) {
                // Find the minimum element in the right subtree (successor)
                BinaryNode<T> successor = this.min(node.right);
                // Replace the current node's element with the successor's element
                node.element = successor.element;
                // Recursively remove the successor from the right subtree
                node.right = this.remove(node.right, successor.element);
            } else if (node.left != null) {
                // Case 2: The node has only a left child
                return node.left;  // Replace the node with its left child
            } else if (node.right != null) {
                // Case 3: The node has only a right child
                return node.right;  // Replace the node with its right child
            } else {
                // Case 4: The node has no children
                return null;  // Remove the node by returning null
            }
        }

        // Return the (potentially updated) current node
        return node;
    }

    /**
     * Checks if the BST contains a given element.
     *
     * @param element the element to check for
     * @return {@code true} if the element is found, {@code false} otherwise
     */
    @Override
    public boolean contains(T element) {
        // Start the search from the root of the tree
        return this.contains(this.root, element);
    }

    /**
     * Helper method to check if the BST contains a given element starting from a node.
     *
     * @param node the current node in the BST
     * @param element the element to check for
     * @return {@code true} if the element is found, {@code false} otherwise
     */
    private boolean contains(BinaryNode<T> node, T element) {
        // If the current node is null, the element is not in the tree
        if (node == null) {
            return false;
        }

        // Check if the current node contains the element
        if (node.element.equals(element)) {
            return true;
        }

        // Traverse the tree to continue searching for the element
        if (element.compareTo(node.element) < 0) {
            // If the element is smaller, search in the left subtree
            return this.contains(node.left, element);
        } else {
            // If the element is greater, search in the right subtree
            return this.contains(node.right, element);
        }
    }

    /**
     * Clears the BST, removing all elements.
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
