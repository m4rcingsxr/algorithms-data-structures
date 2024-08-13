package com.marcinseweryn.algorithms.datastructures.tree.binary;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class AVLTest {

    @Test
    void givenNewAVL_whenCreated_thenShouldBeEmpty() {
        AVL<Integer> tree = new AVL<>();
        assertNull(tree.root);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenSingleElement_whenAdded_thenShouldBePresentInTree() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        assertEquals(1, tree.size());
        assertEquals(1, tree.root.height);
        assertTrue(tree.contains(10));
    }

    @Test
    void givenDuplicateElement_whenAdded_thenShouldNotAdd() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        assertFalse(tree.add(10));
        assertEquals(1, tree.size());
    }

    @Test
    void givenTreeWithElements_whenRemovedLeaf_thenShouldDecreaseSize() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        tree.add(30);
        assertTrue(tree.remove(30));
        assertEquals(2, tree.size());
    }

    @Test
    void givenTreeWithElements_whenRemovedElementWithChildren_thenShouldReplaceWithSuccessor() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(5);
        tree.add(15);
        tree.add(12);
        tree.add(20);

        assertTrue(tree.remove(15));
        assertEquals(4, tree.size());

        Iterator<Integer> iterator = tree.inOrderIterator();
        assertEquals(5, iterator.next());
        assertEquals(10, iterator.next());
        assertEquals(12, iterator.next());
        assertEquals(20, iterator.next());
    }

    @Test
    void givenNonexistentElement_whenRemoved_thenShouldReturnFalse() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        assertFalse(tree.remove(30));
        assertEquals(2, tree.size());
    }

    @Test
    void givenTree_whenContainsCalled_thenShouldFindCorrectElement() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        assertTrue(tree.contains(10));
        assertFalse(tree.contains(30));
    }

    @Test
    void givenTree_whenCleared_thenShouldBeEmpty() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        tree.clear();
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenTreeWithOneElement_whenRemoved_thenTreeShouldBeEmpty() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.remove(10);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenTreeWithElements_whenIteratedInOrder_thenShouldReturnElementsInOrder() {
        AVL<Integer> tree = new AVL<>();
        tree.add(40);
        tree.add(20);
        tree.add(60);
        tree.add(10);
        tree.add(30);
        tree.add(50);
        tree.add(70);

        Iterator<Integer> inOrder = tree.inOrderIterator();
        assertTrue(inOrder.hasNext());
        assertEquals(10, inOrder.next());
        assertEquals(20, inOrder.next());
        assertEquals(30, inOrder.next());
        assertEquals(40, inOrder.next());
        assertEquals(50, inOrder.next());
        assertEquals(60, inOrder.next());
        assertEquals(70, inOrder.next());
        assertFalse(inOrder.hasNext());

        Iterator<Integer> levelOrder = tree.levelOrderIterator();
        assertTrue(levelOrder.hasNext());
        assertEquals(40, levelOrder.next());
        assertEquals(20, levelOrder.next());
        assertEquals(60, levelOrder.next());
        assertEquals(10, levelOrder.next());
        assertEquals(30, levelOrder.next());
        assertEquals(50, levelOrder.next());
        assertEquals(70, levelOrder.next());
        assertFalse(levelOrder.hasNext());

        Iterator<Integer> preOrder = tree.preOrderIterator();
        assertTrue(preOrder.hasNext());
        assertEquals(40, preOrder.next());
        assertEquals(20, preOrder.next());
        assertEquals(10, preOrder.next());
        assertEquals(30, preOrder.next());
        assertEquals(60, preOrder.next());
        assertEquals(50, preOrder.next());
        assertEquals(70, preOrder.next());
        assertFalse(preOrder.hasNext());

        Iterator<Integer> postOrder = tree.postOrderIterator();
        assertTrue(postOrder.hasNext());
        assertEquals(10, postOrder.next());
        assertEquals(30, postOrder.next());
        assertEquals(20, postOrder.next());
        assertEquals(50, postOrder.next());
        assertEquals(70, postOrder.next());
        assertEquals(60, postOrder.next());
        assertEquals(40, postOrder.next());
        assertFalse(postOrder.hasNext());
    }

    @Test
    void givenEmptyTree_whenRemoveCalled_thenShouldReturnFalse() {
        AVL<Integer> tree = new AVL<>();
        assertFalse(tree.remove(10));
    }

    @Test
    void givenEmptyTree_whenContainsCalled_thenShouldReturnFalse() {
        AVL<Integer> tree = new AVL<>();
        assertFalse(tree.contains(10));
    }

    @Test
    void givenLargeNumberOfElements_whenAdded_thenShouldHandleCorrectly() {
        AVL<Integer> tree = new AVL<>();
        for (int i = 0; i < 10000; i++) {
            tree.add(i);
        }
        assertEquals(10000, tree.size());
        assertTrue(tree.contains(0));
        assertTrue(tree.contains(9999));

        Iterator<Integer> iterator = tree.inOrderIterator();
        for (int i = 0; i < 10000; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenLargeNumberOfElements_whenRemoved_thenShouldHandleCorrectly() {
        AVL<Integer> tree = new AVL<>();
        for (int i = 0; i < 10000; i++) {
            tree.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            tree.remove(i);
        }
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.size());
    }

    @Test
    void givenElementsThatCauseLLRotation_whenAdded_thenTreeShouldRebalance() {
        AVL<Integer> tree = new AVL<>();
        tree.add(30);
        tree.add(20);
        tree.add(10);  // This should trigger LL rotation

        assertEquals(2, tree.root.height);  // Height should be updated correctly
        assertEquals(20, tree.root.element);  // The root should be 20 after rotation
        assertEquals(10, tree.root.left.element);
        assertEquals(30, tree.root.right.element);
    }

    @Test
    void givenElementsThatCauseRRRotation_whenAdded_thenTreeShouldRebalance() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        tree.add(30);  // This should trigger RR rotation

        assertEquals(2, tree.root.height);  // Height should be updated correctly
        assertEquals(20, tree.root.element);  // The root should be 20 after rotation
        assertEquals(10, tree.root.left.element);
        assertEquals(30, tree.root.right.element);
    }

    @Test
    void givenElementsThatCauseLRRotation_whenAdded_thenTreeShouldRebalance() {
        AVL<Integer> tree = new AVL<>();
        tree.add(30);
        tree.add(10);
        tree.add(20);  // This should trigger LR rotation

        assertEquals(2, tree.root.height);  // Height should be updated correctly
        assertEquals(20, tree.root.element);  // The root should be 20 after rotation
        assertEquals(10, tree.root.left.element);
        assertEquals(30, tree.root.right.element);
    }

    @Test
    void givenElementsThatCauseRLRotation_whenAdded_thenTreeShouldRebalance() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(30);
        tree.add(20);  // This should trigger RL rotation

        assertEquals(2, tree.root.height);  // Height should be updated correctly
        assertEquals(20, tree.root.element);  // The root should be 20 after rotation
        assertEquals(10, tree.root.left.element);
        assertEquals(30, tree.root.right.element);
    }

    @Test
    void givenAVLTree_whenHeightChecked_thenShouldBeCorrectlyUpdated() {
        AVL<Integer> tree = new AVL<>();
        assertNull(tree.root);
        assertTrue(tree.isEmpty());

        tree.add(10);
        assertEquals(1, tree.root.height);

        tree.add(20);
        assertEquals(2, tree.root.height);

        tree.add(30);  // This should cause a rotation, reducing the height to 2
        assertEquals(2, tree.root.height);
    }

    @Test
    void givenAVLTree_whenRebalanced_thenHeightShouldBeUpdatedCorrectly() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        tree.add(30);  // RR rotation should occur

        assertEquals(2, tree.root.height);  // Height should be 2 after rotation
        assertEquals(1, tree.root.left.height);
        assertEquals(1, tree.root.right.height);

        tree.add(40);
        tree.add(50);  // RR rotation should occur

        assertEquals(3, tree.root.height);  // Height should increase to 3
        assertEquals(2, tree.root.right.height);  // The right subtree's height should be 2
    }

    @Test
    void givenAVLTree_whenNodeRemoved_thenShouldRebalanceAndMaintainCorrectHeight() {
        AVL<Integer> tree = new AVL<>();
        tree.add(10);
        tree.add(20);
        tree.add(30);  // RR rotation should occur

        tree.remove(10);  // Remove a leaf node
        assertEquals(2, tree.root.height);  // Height should remain 2
        assertEquals(1, tree.root.right.height);

        tree.remove(30);  // Remove another node causing rotation
        assertEquals(1, tree.root.height);  // Height should decrease to 1
        assertNull(tree.root.left);
        assertNull(tree.root.right);
    }

    @Test
    void givenAVLTree_whenRemovingElementsInComplexPattern_thenShouldRemainBalanced() {
        AVL<Integer> tree = new AVL<>();
        int[] elements = {10, 20, 30, 40, 50, 25};

        for (int el : elements) {
            tree.add(el);
        }

        tree.remove(50);
        tree.remove(40);
        tree.remove(10);

        // The tree should rebalance itself
        assertEquals(2, tree.root.height);  // Height should be updated correctly
        assertTrue(tree.contains(20));
        assertTrue(tree.contains(25));
        assertTrue(tree.contains(30));
    }
}
