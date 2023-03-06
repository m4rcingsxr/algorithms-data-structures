package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.TraversalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AVLTest {
    static AVL<Integer> avl;
    static TestBinaryNode root;

    @BeforeEach
    void beforeEach() {
        avl = new AVL<>();
        root = null;
    }

    @Test
    void testAddRemoveNullShouldReturnFalse() {
        assertFalse(avl.add(null));
        assertFalse(avl.remove(null));
    }

    @Test
     void testRightRightCondition() {
        //      9                              10
        //        10            ->         9       20
        //           20
        avl.add(9);
        avl.add(10);
        avl.add(20);

        // Imitate expected state
        root = TestBinaryNode.add(root, 10);
        root = TestBinaryNode.add(root, 20);
        root = TestBinaryNode.add(root, 9);

        assertTrue(validateTraversal());

        //         11  - unbalanced RL                      15     - Balanced
        //       10  15                        ->       11      17
        //         14  17                             10  14      18
        //               18
        avl = new AVL<>();
        root = null;
        avl.add(11);
        avl.add(10);
        avl.add(15);
        avl.add(14);
        avl.add(17);
        avl.add(18);
        root = TestBinaryNode.add(root, 15);
        root = TestBinaryNode.add(root, 11);
        root = TestBinaryNode.add(root, 17);
        root = TestBinaryNode.add(root, 10);
        root = TestBinaryNode.add(root, 14);
        root = TestBinaryNode.add(root, 18);

        assertTrue(validateTraversal());
    }

    @Test
     void testLeftLeftCondition() {
        //      9                              8
        //    8                 ->          5     9
        //  5
        avl.add(9);
        avl.add(8);
        avl.add(5);

        // Imitate expected state
        root = TestBinaryNode.add(root, 8);
        root = TestBinaryNode.add(root, 5);
        root = TestBinaryNode.add(root, 9);

        assertTrue(validateTraversal());

        //          8    -- unbalanced LL                5
        //       5     9                              4      8
        //     4  6                          ->     3      6   9
        //    3
        avl.add(6);
        avl.add(4);
        avl.add(3);
        root = null;
        root = TestBinaryNode.add(root, 5);
        root = TestBinaryNode.add(root, 4);
        root = TestBinaryNode.add(root, 8);
        root = TestBinaryNode.add(root, 3);
        root = TestBinaryNode.add(root, 6);
        root = TestBinaryNode.add(root, 9);

        assertTrue(validateTraversal());
    }

    @Test
     void testLeftRightCondition() {
        //      10             10             9
        //    8          ->   8       ->   8     10
        //      9            9
        avl.add(10);
        avl.add(8);
        avl.add(9);

        // Imitate expected state
        root = TestBinaryNode.add(root, 9);
        root = TestBinaryNode.add(root, 8);
        root = TestBinaryNode.add(root, 10);

        assertTrue(validateTraversal());
    }

    @Test
     void testAddRightLeftCondition() {
        //      10             10                11
        //        15     ->      11        ->  10  15
        //      11                 15
        avl.add(10);
        avl.add(15);
        avl.add(11);

        // Imitate expected state
        root = TestBinaryNode.add(root, 11);
        root = TestBinaryNode.add(root, 10);
        root = TestBinaryNode.add(root, 15);
        assertTrue(validateTraversal());

        //         11  - unbalanced RL                      15     - Balanced
        //       10  15                        ->       11      17
        //         14  17                             10  14  16
        //           16
        avl.add(17);
        avl.add(14);
        avl.add(16);
        root = null;
        root = TestBinaryNode.add(root, 15);
        root = TestBinaryNode.add(root, 11);
        root = TestBinaryNode.add(root, 17);
        root = TestBinaryNode.add(root, 10);
        root = TestBinaryNode.add(root, 14);
        root = TestBinaryNode.add(root, 16);
        assertTrue(validateTraversal());
    }

    @Test
     void testRemoveRightLeftCondition() {
        //      10             10                11
        //    9   15     ->      11        ->  10  15
        //      11                 15
        avl.add(10);
        avl.add(9);
        avl.add(15);
        avl.add(11);
        assertTrue(validateBalance(avl.getRoot()));
        assertTrue(avl.remove(9));
        assertTrue(validateBalance(avl.getRoot()));

        // Imitate expected state - balanced
        root = TestBinaryNode.add(root, 11);
        root = TestBinaryNode.add(root, 10);
        root = TestBinaryNode.add(root, 15);
        assertTrue(validateTraversal());

        //         11  - unbalanced RL                      15     - Balanced
        //       10  15                        ->       11      17
        //     9    14  17                             10  14  16
        //            16
        avl.add(17);
        avl.add(9);
        avl.add(14);
        avl.add(16);

        assertTrue(avl.remove(9));
        assertTrue(validateBalance(avl.getRoot()));

        root = null;
        root = TestBinaryNode.add(root, 15);
        root = TestBinaryNode.add(root, 11);
        root = TestBinaryNode.add(root, 17);
        root = TestBinaryNode.add(root, 10);
        root = TestBinaryNode.add(root, 14);
        root = TestBinaryNode.add(root, 16);
        assertTrue(validateTraversal());
    }

    @Test
    void testInsertionBalanceRoot() {
        for (int i = 0; i < 100_000; i++) {
            avl.add(new Random().nextInt(100));
            assertTrue(validateBalance(avl.getRoot()));
        }
    }

    @Test
    void testDeletionBalanceRoot() {
        for (int i = 0; i < 100_000; i++) {
            avl.add(new Random().nextInt(100));
            assertTrue(validateBalance(avl.getRoot()));
        }

        for (int i = 0; i < 100_000; i++) {
            avl.remove(new Random().nextInt(100));
            assertTrue(validateBalance(avl.getRoot()));
        }
    }


    static boolean validateBalance(AVL.BinaryNode<Integer> node) {
        if(node != null && node.getLeft() != null && node.getRight() != null) {
            return node.getLeft().getHeight() - node.getRight().getHeight() <= 1 &&
                    node.getLeft().getHeight() - node.getRight().getHeight() >= -1;
        }
        return true;
    }

    static boolean validateTraversal() {

        assert root != null;
        // inOrder test
        TestBinaryNode.inOrder(root);
        Iterator<AVL.BinaryNode<Integer>> avlInorder = avl.traverseIterator(TraversalType.INORDER);
        Iterator<TestBinaryNode> inOrder = TestBinaryNode.getList().iterator();
        for (int i = 0; i < avl.size(); i++) {
            int actual = avlInorder.next().getVal();
            int expected = inOrder.next().value;
            if (actual != expected) {
                return false;
            }
        }

        // levelOrder
        TestBinaryNode.levelOrder(root);
        Iterator<TestBinaryNode> levelOrder = TestBinaryNode.getList().iterator();
        Iterator<AVL.BinaryNode<Integer>> avlLevelOrder = avl.traverseIterator(TraversalType.LEVELORDER);
        for (int i = 0; i < avl.size(); i++) {
            int actual = avlLevelOrder.next().getVal();
            int expected = levelOrder.next().value;

            if (actual != expected) {
                return false;
            }
        }

        // preOrder
        TestBinaryNode.preOrder(root);
        Iterator<TestBinaryNode> preOrder = TestBinaryNode.getList().iterator();
        Iterator<AVL.BinaryNode<Integer>> avlPreorder = avl.traverseIterator(TraversalType.PREORDER);

        for (int i = 0; i < avl.size(); i++) {
            int actual = avlPreorder.next().getVal();
            int expected = preOrder.next().value;
            if (actual != expected) {
                return false;
            }
        }

        // postOrder
        TestBinaryNode.postOrder(root);
        Iterator<TestBinaryNode> postOrder = TestBinaryNode.getList().iterator();
        Iterator<AVL.BinaryNode<Integer>> avlPostorder = avl.traverseIterator(TraversalType.POSTORDER);
        for (int i = 0; i < avl.size(); i++) {
            int actual = avlPostorder.next().getVal();
            int expected = postOrder.next().value;

            if (actual != expected) {
                return false;
            }
        }

        return true;
    }

    static class TestBinaryNode {
        Integer value;
        TestBinaryNode left;
        TestBinaryNode right;
        private static List<TestBinaryNode> list = new ArrayList<>();

         TestBinaryNode(Integer value, TestBinaryNode left, TestBinaryNode right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        static List<TestBinaryNode> getList() {
            List<TestBinaryNode> temp = new ArrayList<>();
            for (TestBinaryNode testBinaryNode : list) {
                temp.add(testBinaryNode);
            }
            list = new ArrayList<>();
            return temp;
        }

         static TestBinaryNode add(TestBinaryNode node, int value) {
            if (node == null) {
                node = new TestBinaryNode(value, null, null);
            } else if (value < node.value) {
                node.left = add(node.left, value);
            } else {
                node.right = add(node.right, value);
            }
            return node;
        }

        static void inOrder(TestBinaryNode node) {
            if (node == null) {
                return;
            }
            inOrder(node.left);
            list.add(node);
            inOrder(node.right);
        }

        static void postOrder(TestBinaryNode node) {
            if (node == null) {
                return;
            }
            postOrder(node.left);
            postOrder(node.right);
            list.add(node);
        }

        static void levelOrder(TestBinaryNode node) {
            Queue<TestBinaryNode> queue = new ArrayDeque<>();
            queue.add(node);
            while (!queue.isEmpty()) {
                TestBinaryNode current = queue.remove();
                list.add(current);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        static void preOrder(TestBinaryNode node) {
            if (node == null) {
                return;
            }
            list.add(node);
            preOrder(node.left);
            preOrder(node.right);
        }

        static int height(TestBinaryNode node) {
            if (node == null) {
                return 0;
            }
            return 1 + Math.max(height(node.left), height(node.right));
        }
    }
}