package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.BinaryNode;
import com.marcinseweryn.algorithms.datastructures.tree.OrderedBinaryTree;

import java.util.Iterator;

/**
 * AVL Tree implementation.
 * An AVL tree is a self-balancing binary search tree where the height of two child subtrees of any node differ by at most one.
 * If at any time during insertion or deletion, the height difference becomes greater than one, the tree is rebalanced through rotations.
 *
 * @param <T> the type of elements maintained by this AVL tree, which must be comparable
 */
public class AVL<T extends Comparable<T>> implements OrderedBinaryTree<T> {

    protected BinaryNode<T> root;
    private int size;

    /**
     * Retrieves the minimum element in the AVL tree.
     *
     * @return the minimum element, or {@code null} if the tree is empty
     */
    @Override
    public T min() {
        // If the tree is empty, return null
        if (this.root == null) return null;
        // Find the minimum element starting from the root
        return this.min(this.root).element;
    }

    /**
     * Helper method to find the minimum node starting from a given node.
     *
     * @param node the starting node
     * @return the node containing the minimum element
     */
    private BinaryNode<T> min(BinaryNode<T> node) {
        // Traverse the left subtree until the leftmost node is found
        if (node.left == null) {
            return node;
        }
        return this.min(node.left);
    }

    /**
     * Retrieves the maximum element in the AVL tree.
     *
     * @return the maximum element, or {@code null} if the tree is empty
     */
    @Override
    public T max() {
        // If the tree is empty, return null
        if (this.root == null) return null;
        // Find the maximum element starting from the root
        return this.max(this.root);
    }

    /**
     * Helper method to find the maximum element starting from a given node.
     *
     * @param node the starting node
     * @return the maximum element
     */
    private T max(BinaryNode<T> node) {
        // Traverse the right subtree until the rightmost node is found
        if (node.right == null) {
            return node.element;
        }
        return this.max(node.right);
    }

    /**
     * Checks if the AVL tree is empty.
     *
     * @return {@code true} if the tree is empty, {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        // The tree is empty if the size is 0
        return this.size == 0;
    }

    /**
     * Returns the number of elements in the AVL tree.
     *
     * @return the size of the AVL tree
     */
    @Override
    public int size() {
        // Return the number of elements in the tree
        return this.size;
    }

    /**
     * Adds an element to the AVL tree.
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
        this.size++;  // Increment the size of the tree
        return true;
    }

    /**
     * Helper method to add an element to the AVL tree starting from a given node.
     *
     * @param node the current node in the AVL tree
     * @param element the element to add
     * @return the node after insertion and balancing
     */
    private BinaryNode<T> add(BinaryNode<T> node, T element) {
        // If the current node is null, we've found the insertion point
        if (node == null) {
            node = new BinaryNode<>(element);  // Create a new node with the element
            node.height = 1;  // Initialize the node height
            return node;  // Return the newly created node
        } else if (element.compareTo(node.element) < 0) {
            // If the element is smaller than the current node, insert into the left subtree
            node.left = this.add(node.left, element);
        } else {
            // If the element is greater than or equal to the current node, insert into the right subtree
            node.right = this.add(node.right, element);
        }

        // Update the height of the current node
        node.height = this.height(node);
        // Calculate the balance factor of the current node
        int balance = this.balance(node);

        // Rebalance the tree if necessary
        if (balance > 1 && element.compareTo(node.left.element) < 0) {
            // Left Left Case
            return this.rotateRight(node);
        } else if (balance > 1 && element.compareTo(node.left.element) > 0) {
            // Left Right Case
            node.left = this.rotateLeft(node.left);
            return this.rotateRight(node);
        } else if (balance < -1 && element.compareTo(node.right.element) > 0) {
            // Right Right Case
            return this.rotateLeft(node);
        } else if (balance < -1 && element.compareTo(node.right.element) < 0) {
            // Right Left Case
            node.right = this.rotateRight(node.right);
            return this.rotateLeft(node);
        }

        // Return the (potentially updated) current node
        return node;
    }

    /**
     * Removes an element from the AVL tree.
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
     * Helper method to remove an element from the AVL tree starting from a given node.
     *
     * @param node the current node in the AVL tree
     * @param element the element to remove
     * @return the node after removal and balancing
     */
    private BinaryNode<T> remove(BinaryNode<T> node, T element) {
        // If the current node is null, the element is not found in the tree
        if (node == null) {
            return null;
        } else if (element.compareTo(node.element) < 0) {
            // If the element is smaller, continue searching in the left subtree
            node.left = this.remove(node.left, element);
        } else if (element.compareTo(node.element) > 0) {
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

        // Update the height of the current node
        node.height = this.height(node);
        // Calculate the balance factor of the current node
        int balance = this.balance(node);

        // Rebalance the tree if necessary
        if (balance > 1 && this.balance(node.left) >= 0) {
            // Left Left Case (perfect left tree - LL condition)
            return this.rotateRight(node);
        } else if (balance > 1 && this.balance(node.left) < 0) {
            // Left Right Case
            node.left = this.rotateLeft(node.left);
            return this.rotateRight(node);
        } else if (balance < -1 && this.balance(node.right) <= 0) {
            // Right Right Case (perfect right tree - RR condition)
            return this.rotateLeft(node);
        } else if (balance < -1 && this.balance(node.right) > 0) {
            // Right Left Case
            node.right = this.rotateRight(node.right);
            return this.rotateLeft(node);
        }

        // Return the (potentially updated) current node
        return node;
    }

    /**
     * Calculates the balance factor of a node.
     *
     * @param node the node to calculate the balance factor for
     * @return the balance factor (height of left subtree - height of right subtree)
     */
    private int balance(BinaryNode<T> node) {
        // The balance factor is the height difference between the left and right subtrees
        return this.height(node.left) - this.height(node.right);
    }

    /**
     * Calculates the height of a node.
     *
     * @param node the node to calculate the height for
     * @return the height of the node
     */
    private int height(BinaryNode<T> node) {
        // Base case: the height of a null node is 0
        if (node == null) {
            return 0;
        }

        // Height is 1 + the maximum height of the left and right subtrees
        return 1 + Math.max(this.height(node.left), this.height(node.right));
    }

    /**
     * Performs a right rotation around an unbalanced node.
     *
     * @param unbalancedNode the node that is unbalanced
     * @return the new root of the subtree after the rotation
     */
    private BinaryNode<T> rotateRight(BinaryNode<T> unbalancedNode) {
        // Perform the right rotation
        BinaryNode<T> newRoot = unbalancedNode.left;
        unbalancedNode.left = newRoot.right;
        newRoot.right = unbalancedNode;

        // Update the heights of the nodes involved in the rotation
        unbalancedNode.height = this.height(unbalancedNode);
        newRoot.height = this.height(newRoot);

        // Return the new root of the subtree
        return newRoot;
    }

    /**
     * Performs a left rotation around an unbalanced node.
     *
     * @param unbalancedNode the node that is unbalanced
     * @return the new root of the subtree after the rotation
     */
    private BinaryNode<T> rotateLeft(BinaryNode<T> unbalancedNode) {
        // Perform the left rotation
        BinaryNode<T> newRoot = unbalancedNode.right;
        unbalancedNode.right = newRoot.left;
        newRoot.left = unbalancedNode;

        // Update the heights of the nodes involved in the rotation
        unbalancedNode.height = this.height(unbalancedNode);
        newRoot.height = this.height(newRoot);

        // Return the new root of the subtree
        return newRoot;
    }

    /**
     * Checks if the AVL tree contains a given element.
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
     * Helper method to check if the AVL tree contains a given element starting from a node.
     *
     * @param node the current node in the AVL tree
     * @param element the element to check for
     * @return {@code true} if the element is found, {@code false} otherwise
     */
    private boolean contains(BinaryNode<T> node, T element) {
        // If the current node is null, the element is not in the tree
        if (node == null) return false;

        // Compare the element with the current node's element
        if (element.compareTo(node.element) < 0) {
            // If the element is smaller, search in the left subtree
            return this.contains(node.left, element);
        } else if (element.compareTo(node.element) > 0) {
            // If the element is greater, search in the right subtree
            return this.contains(node.right, element);
        }
        // The element is equal to the current node's element
        return true;
    }

    /**
     * Clears the AVL tree, removing all elements.
     */
    @Override
    public void clear() {
        // Set the root to null, effectively clearing the tree
        this.root = null;
        // Reset the size to 0
        this.size = 0;
    }

    /**
     * Returns an iterator that performs a level-order traversal of the AVL tree.
     *
     * @return an iterator for level-order traversal
     */
    @Override
    public Iterator<T> levelOrderIterator() {
        // Use the factory to create a level-order iterator
        return BinaryTreeIteratorFactory.levelOrderIterator(this.root);
    }

    /**
     * Returns an iterator that performs an in-order traversal of the AVL tree.
     *
     * @return an iterator for in-order traversal
     */
    @Override
    public Iterator<T> inOrderIterator() {
        // Use the factory to create an in-order iterator
        return BinaryTreeIteratorFactory.inOrderIterator(this.root);
    }

    /**
     * Returns an iterator that performs a post-order traversal of the AVL tree.
     *
     * @return an iterator for post-order traversal
     */
    @Override
    public Iterator<T> postOrderIterator() {
        // Use the factory to create a post-order iterator
        return BinaryTreeIteratorFactory.postOrderIterator(this.root);
    }

    /**
     * Returns an iterator that performs a pre-order traversal of the AVL tree.
     *
     * @return an iterator for pre-order traversal
     */
    @Override
    public Iterator<T> preOrderIterator() {
        // Use the factory to create a pre-order iterator
        return BinaryTreeIteratorFactory.preOrderIterator(this.root);
    }
}
