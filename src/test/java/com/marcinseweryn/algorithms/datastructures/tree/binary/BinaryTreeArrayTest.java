package com.marcinseweryn.algorithms.datastructures.tree.binary;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeArrayTest {

    @Test
    void givenNewBinaryTree_whenCreated_thenShouldBeEmpty() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenSingleElement_whenAdded_thenShouldBePresentInTree() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        assertEquals(1, tree.size());
        assertTrue(tree.contains(10));
    }

    @Test
    void givenDuplicateElement_whenAdded_thenShouldNotAdd() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        boolean result = tree.add(10);
        assertFalse(result);
        assertEquals(1, tree.size());
    }

    @Test
    void givenTreeWithElements_whenRemovedLeaf_thenShouldDecreaseSize() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.add(20);
        tree.add(30);
        assertTrue(tree.remove(30));
        assertEquals(2, tree.size());
    }

    @Test
    void givenTreeWithElements_whenRemovedElement_thenShouldReplaceWithLastElement() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);

        assertTrue(tree.remove(20));
        assertEquals(4, tree.size());

        Iterator<Integer> iterator = tree.levelOrderIterator();
        assertEquals(10, iterator.next());
        assertEquals(50, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(40, iterator.next());
    }

    @Test
    void givenNonexistentElement_whenRemoved_thenShouldReturnFalse() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.add(20);
        assertFalse(tree.remove(30));
        assertEquals(2, tree.size());
    }

    @Test
    void givenTree_whenContainsCalled_thenShouldFindCorrectElement() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.add(20);
        assertTrue(tree.contains(10));
        assertFalse(tree.contains(30));
    }

    @Test
    void givenTree_whenCleared_thenShouldBeEmpty() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.add(20);
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenTreeWithOneElement_whenRemoved_thenTreeShouldBeEmpty() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.remove(10);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenTreeWithElements_whenIteratedInOrder_thenShouldReturnElementsInOrder() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        tree.add(10);
        tree.add(20);
        tree.add(30);
        tree.add(40);
        tree.add(50);
        tree.add(60);
        tree.add(70);

        Iterator<Integer> inOrder = tree.inOrderIterator();
        assertTrue(inOrder.hasNext());
        assertEquals(40, inOrder.next());
        assertEquals(20, inOrder.next());
        assertEquals(50, inOrder.next());
        assertEquals(10, inOrder.next());
        assertEquals(60, inOrder.next());
        assertEquals(30, inOrder.next());
        assertEquals(70, inOrder.next());
        assertFalse(inOrder.hasNext());

        Iterator<Integer> levelOrder = tree.levelOrderIterator();
        assertTrue(levelOrder.hasNext());
        assertEquals(10, levelOrder.next());
        assertEquals(20, levelOrder.next());
        assertEquals(30, levelOrder.next());
        assertEquals(40, levelOrder.next());
        assertEquals(50, levelOrder.next());
        assertEquals(60, levelOrder.next());
        assertEquals(70, levelOrder.next());
        assertFalse(levelOrder.hasNext());

        Iterator<Integer> preOrder = tree.preOrderIterator();
        assertTrue(preOrder.hasNext());
        assertEquals(10, preOrder.next());
        assertEquals(20, preOrder.next());
        assertEquals(40, preOrder.next());
        assertEquals(50, preOrder.next());
        assertEquals(30, preOrder.next());
        assertEquals(60, preOrder.next());
        assertEquals(70, preOrder.next());
        assertFalse(preOrder.hasNext());

        Iterator<Integer> postOrder = tree.postOrderIterator();
        assertTrue(postOrder.hasNext());
        assertEquals(40, postOrder.next());
        assertEquals(50, postOrder.next());
        assertEquals(20, postOrder.next());
        assertEquals(60, postOrder.next());
        assertEquals(70, postOrder.next());
        assertEquals(30, postOrder.next());
        assertEquals(10, postOrder.next());
        assertFalse(postOrder.hasNext());
    }

    @Test
    void givenEmptyTree_whenRemoveCalled_thenShouldReturnFalse() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        assertFalse(tree.remove(10));
    }

    @Test
    void givenEmptyTree_whenContainsCalled_thenShouldReturnFalse() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        assertFalse(tree.contains(10));
    }

    @Test
    void givenLargeNumberOfElements_whenAdded_thenShouldHandleCorrectly() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        for (int i = 0; i < 10000; i++) {
            tree.add(i);
        }
        assertEquals(10000, tree.size());
        assertTrue(tree.contains(0));
        assertTrue(tree.contains(9999));

        Iterator<Integer> iterator = tree.levelOrderIterator();
        for (int i = 0; i < 10000; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenLargeNumberOfElements_whenRemoved_thenShouldHandleCorrectly() {
        BinaryTreeArray<Integer> tree = new BinaryTreeArray<>(10);
        for (int i = 0; i < 10000; i++) {
            tree.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            tree.remove(i);
        }
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }
}
