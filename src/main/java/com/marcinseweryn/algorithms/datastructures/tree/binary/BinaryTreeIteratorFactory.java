package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.queue.LinkedListQueue;
import com.marcinseweryn.algorithms.datastructures.queue.Queue;
import com.marcinseweryn.algorithms.datastructures.stack.Stack;
import com.marcinseweryn.algorithms.datastructures.stack.StackLinkedList;
import com.marcinseweryn.algorithms.datastructures.tree.BinaryNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A factory class that provides various iterators for binary trees. These iterators
 * include level-order (BFS), in-order (DFS), pre-order (DFS), and post-order (DFS) traversals.
 * The iterators are designed to work with both linked-list based binary trees and array-based binary trees.
 */
public class BinaryTreeIteratorFactory {

    private BinaryTreeIteratorFactory() {}

    /**
     * Returns an iterator for level-order traversal (Breadth-First Search) for a linked-list based binary tree.
     *
     * @param root the root node of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for level-order traversal
     */
    public static <T> Iterator<T> levelOrderIterator(BinaryNode<T> root) {
        return new LevelOrderLinkedListIterator<>(root);
    }

    /**
     * Returns an iterator for in-order traversal (Depth-First Search) for a linked-list based binary tree.
     *
     * @param root the root node of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for in-order traversal
     */
    public static <T> Iterator<T> inOrderIterator(BinaryNode<T> root) {
        return new InOrderLinkedListIterator<>(root);
    }

    /**
     * Returns an iterator for post-order traversal (Depth-First Search) for a linked-list based binary tree.
     *
     * @param root the root node of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for post-order traversal
     */
    public static <T> Iterator<T> postOrderIterator(BinaryNode<T> root) {
        return new PostOrderLinkedListIterator<>(root);
    }

    /**
     * Returns an iterator for pre-order traversal (Depth-First Search) for a linked-list based binary tree.
     *
     * @param root the root node of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for pre-order traversal
     */
    public static <T> Iterator<T> preOrderIterator(BinaryNode<T> root) {
        return new PreOrderLinkedListIterator<>(root);
    }

    /**
     * Returns an iterator for level-order traversal (Breadth-First Search) for an array-based binary tree.
     *
     * @param tree the array representation of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for level-order traversal
     */
    public static <T> Iterator<T> levelOrderIterator(T[] tree) {
        return new LevelOrderArrayIterator<>(tree);
    }

    /**
     * Returns an iterator for in-order traversal (Depth-First Search) for an array-based binary tree.
     *
     * @param tree the array representation of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for in-order traversal
     */
    public static <T> Iterator<T> inOrderIterator(T[] tree) {
        return new InOrderArrayIterator<>(tree);
    }

    /**
     * Returns an iterator for post-order traversal (Depth-First Search) for an array-based binary tree.
     *
     * @param tree the array representation of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for post-order traversal
     */
    public static <T> Iterator<T> postOrderIterator(T[] tree) {
        return new PostOrderArrayIterator<>(tree);
    }

    /**
     * Returns an iterator for pre-order traversal (Depth-First Search) for an array-based binary tree.
     *
     * @param tree the array representation of the binary tree
     * @param <T>  the type of elements held in the tree
     * @return an iterator for pre-order traversal
     */
    public static <T> Iterator<T> preOrderIterator(T[] tree) {
        return new PreOrderArrayIterator<>(tree);
    }

    /**
     * Iterator for level-order traversal (BFS) of a linked-list based binary tree.
     */
    static class LevelOrderLinkedListIterator<T> implements Iterator<T> {

        private final Queue<BinaryNode<T>> queue;

        LevelOrderLinkedListIterator(BinaryNode<T> root) {
            this.queue = new LinkedListQueue<>();
            if (root != null) {
                this.queue.enqueue(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }

            // Dequeue the next node and enqueue its children
            BinaryNode<T> current = queue.dequeue();
            if (current.left != null) {
                queue.enqueue(current.left);
            }
            if (current.right != null) {
                queue.enqueue(current.right);
            }

            return current.element;
        }
    }

    /**
     * Iterator for level-order traversal (BFS) of an array-based binary tree.
     */
    static class LevelOrderArrayIterator<T> implements Iterator<T> {

        private final T[] tree;
        private int index;

        LevelOrderArrayIterator(T[] tree) {
            this.tree = tree;
        }

        @Override
        public boolean hasNext() {
            // Ensure the current index is within bounds and points to a non-null element
            return index < tree.length && tree[index] != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }

            // Return the current element and increment the index
            return tree[index++];
        }
    }

    /**
     * Iterator for in-order traversal (DFS) of a linked-list based binary tree.
     */
    static class InOrderLinkedListIterator<T> implements Iterator<T> {

        private final Stack<BinaryNode<T>> stack;

        InOrderLinkedListIterator(BinaryNode<T> root) {
            this.stack = new StackLinkedList<>();
            pushLeftElements(root);  // Start by pushing all leftmost nodes from the root
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }

            // Pop the top node from the stack and handle its right subtree
            BinaryNode<T> current = this.stack.pop();
            T result = current.element;

            // If the node has a right child, push all its leftmost nodes
            if (current.right != null) {
                pushLeftElements(current.right);
            }

            return result;
        }

        /**
         * Helper method to push all leftmost nodes of a subtree to the stack.
         *
         * @param node the starting node of the subtree
         */
        private void pushLeftElements(BinaryNode<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }
    }

    /**
     * Iterator for in-order traversal (DFS) of an array-based binary tree.
     */
    static class InOrderArrayIterator<T> implements Iterator<T> {

        private final Stack<Integer> stack;
        private final T[] tree;

        InOrderArrayIterator(T[] tree) {
            this.stack = new StackLinkedList<>();
            this.tree = tree;
            pushLeftElements(0);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }

            Integer current = this.stack.pop();
            int right = current * 2 + 2;

            // Check if the right child index is within the bounds and if the right child exists
            if (right < tree.length && tree[right] != null) {
                pushLeftElements(right);
            }

            return tree[current];
        }

        /**
         * Helper method to push all leftmost nodes of a subtree to the stack.
         *
         * @param index the starting index of the subtree in the array
         */
        private void pushLeftElements(int index) {
            // Traverse down the left subtree and push nodes onto the stack
            while (index < tree.length && tree[index] != null) {
                this.stack.push(index);
                index = index * 2 + 1; // Move to the left child
            }
        }
    }

    /**
     * Iterator for pre-order traversal (DFS) of a linked-list based binary tree.
     */
    static class PreOrderLinkedListIterator<T> implements Iterator<T> {

        private final Stack<BinaryNode<T>> stack;

        PreOrderLinkedListIterator(BinaryNode<T> root) {
            this.stack = new StackLinkedList<>();
            if (root != null) {
                this.stack.push(root);
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }

            // Pop the top node from the stack
            BinaryNode<T> current = this.stack.pop();
            T result = current.element;

            // Push right child first so that left child is processed first
            if (current.right != null) {
                this.stack.push(current.right);
            }
            if (current.left != null) {
                this.stack.push(current.left);
            }

            return result;
        }
    }

    /**
     * Iterator for pre-order traversal (DFS) of an array-based binary tree.
     */
    static class PreOrderArrayIterator<T> implements Iterator<T> {

        private final Stack<Integer> stack;
        private final T[] tree;

        PreOrderArrayIterator(T[] tree) {
            this.stack = new StackLinkedList<>();
            this.tree = tree;
            if (tree.length > 0 && tree[0] != null) {
                this.stack.push(0);  // Start with the root element (index 0)
            }
        }

        @Override
        public boolean hasNext() {
            return !this.stack.isEmpty();
        }

        @Override
        public T next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }

            // Pop the current node index from the stack
            Integer currentIndex = this.stack.pop();
            T result = tree[currentIndex];

            // Calculate the right and left child indices
            int rightIndex = currentIndex * 2 + 2;
            int leftIndex = currentIndex * 2 + 1;

            // Push right child first so that left child is processed first
            if (rightIndex < tree.length && tree[rightIndex] != null) {
                this.stack.push(rightIndex);
            }
            if (leftIndex < tree.length && tree[leftIndex] != null) {
                this.stack.push(leftIndex);
            }

            return result;
        }
    }

    /**
     * Iterator for post-order traversal (DFS) of a linked-list based binary tree.
     */
    static class PostOrderLinkedListIterator<T> implements Iterator<T> {

        private final Stack<BinaryNode<T>> stack1;

        PostOrderLinkedListIterator(BinaryNode<T> root) {
            this.stack1 = new StackLinkedList<>();
            Stack<BinaryNode<T>> stack2 = new StackLinkedList<>();
            if (root != null) {
                stack2.push(root);

                // Traverse the tree in reverse order (right -> left -> root) and push nodes to stack1
                while (!stack2.isEmpty()) {
                    BinaryNode<T> current = stack2.pop();
                    stack1.push(current);  // Push the current node to stack1

                    // Push left child first so that right child is processed first
                    if (current.left != null) {
                        stack2.push(current.left);
                    }
                    if (current.right != null) {
                        stack2.push(current.right);
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack1.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }
            return stack1.pop().element;
        }
    }

    /**
     * Iterator for post-order traversal (DFS) of an array-based binary tree.
     */
    static class PostOrderArrayIterator<T> implements Iterator<T> {

        private final Stack<Integer> stack1;
        private final T[] tree;

        PostOrderArrayIterator(T[] tree) {
            this.tree = tree;
            this.stack1 = new StackLinkedList<>();
            Stack<Integer> stack2 = new StackLinkedList<>();
            if (tree.length > 0 && tree[0] != null) {
                stack2.push(0);  // Start with the root element (index 0)

                // Traverse the tree in reverse order (right -> left -> root)
                while (!stack2.isEmpty()) {
                    Integer currentIndex = stack2.pop();
                    stack1.push(currentIndex);  // Push the current node index to stack1

                    // Calculate the left and right child indices
                    int leftIndex = currentIndex * 2 + 1;
                    int rightIndex = currentIndex * 2 + 2;

                    // Push left child first so that right child is processed first
                    if (leftIndex < tree.length && tree[leftIndex] != null) {
                        stack2.push(leftIndex);
                    }
                    if (rightIndex < tree.length && tree[rightIndex] != null) {
                        stack2.push(rightIndex);
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !stack1.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements to iterate.");
            }
            return this.tree[stack1.pop()];
        }
    }
}
