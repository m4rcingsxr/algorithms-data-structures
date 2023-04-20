package com.marcinseweryn.algorithms.datastructures.tree.binary;

import com.marcinseweryn.algorithms.datastructures.tree.TraversalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class BSTTest {
    static BST<Integer> bst;
    static TestBinaryNode root;

    @BeforeEach
    void beforeEach() {
        root = null;
        bst = new BST<>();
    }

    @Test
    void isEmpty() {
        assertTrue(bst.isEmpty());
        bst.add(1);
        root = TestBinaryNode.add(root, 1);
        assertFalse(bst.isEmpty());
        assertTrue(BSTTest.validateTraversal());
    }

    @RepeatedTest(20)
    void testSize() {
        int expected = new Random().nextInt(1000);
        int expectedSize = 0;
        for (int i = 0; i < expected; i++) {
            int val = new Random().nextInt(1000);
            if (bst.add(val)) {
                expectedSize++;
            }
            root = TestBinaryNode.add(root, val);
        }
        int actual = bst.size();
        System.out.println("Expected: " + expected + "\nActual: " + actual);
        assertEquals(expectedSize, actual);
    }

    @Test
    void testHeight() {
        //1        9
        //2      7  10
        //3    6  8   11
        //4  3
        bst.add(9);
        root = TestBinaryNode.add(root, 9);
        bst.add(7);
        root = TestBinaryNode.add(root, 7);
        bst.add(10);
        root = TestBinaryNode.add(root, 10);
        bst.add(11);
        root = TestBinaryNode.add(root, 11);
        bst.add(6);
        root = TestBinaryNode.add(root, 6);
        bst.add(8);
        root = TestBinaryNode.add(root, 8);
        bst.add(3);
        root = TestBinaryNode.add(root, 3);

        Iterator<BST.BinaryNode<Integer>> bstIterator =
                bst.traverseIterator(TraversalType.LEVELORDER);
        TestBinaryNode.levelOrder(root);
        Iterator<TestBinaryNode> testIterator = TestBinaryNode.list.iterator();

        for (int i = 0; i < bst.size(); i++) {
            int actual = bstIterator.next().getHeight();
            int expected = TestBinaryNode.height(testIterator.next());

            assertEquals(expected, actual);
        }
    }


    @Test
    void testTraversal() {
        root = null;
        bst = new BST<>();
        bst.add(3);
        bst.add(2);
        bst.add(4);
        bst.add(1);
        bst.add(0);
        root = TestBinaryNode.add(root, 3);
        root = TestBinaryNode.add(root, 2);
        root = TestBinaryNode.add(root, 4);
        root = TestBinaryNode.add(root, 1);
        root = TestBinaryNode.add(root, 0);
        TestBinaryNode.inOrder(root);
        Iterator<TestBinaryNode> iterator = TestBinaryNode.getList().iterator();
        Iterator<BST.BinaryNode<Integer>> it =
                bst.traverseIterator(TraversalType.INORDER);
        while (iterator.hasNext()) {
            System.out.println(iterator.next().value);
        }
        System.out.println();
        while (it.hasNext()) {
            System.out.println(it.next().getValue());
        }
        assertTrue(validateTraversal());
    }

    @Test
    void testAdd() {
        //1        9
        //2      7  10
        //3    6  8   11
        //4  3
        bst.add(9);
        bst.add(7);
        bst.add(10);
        bst.add(8);
        bst.add(6);
        bst.add(11);
        bst.add(3);
        int[] expected = {9, 7, 10, 6, 8, 11, 3};
        Iterator<BST.BinaryNode<Integer>> iterator =
                bst.traverseIterator(TraversalType.LEVELORDER);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(iterator.next().getValue(), expected[i]);
        }
    }

    @Test
    void testRemove() {
        bst.add(4);
        assertAll(
                () -> assertEquals(1, bst.size()),
                () -> assertFalse(bst.remove(2)),
                () -> assertEquals(1, bst.size())
        );
        bst.add(5);
        assert bst.contains(5);
        bst.add(7);
        assertAll(
                () -> assertFalse(bst.add(5)),
                () -> assertEquals(3, bst.size()),
                () -> assertTrue(bst.remove(5)),
                () -> assertEquals(2, bst.size())
        );
    }

    @RepeatedTest(200)
    void testMin() {
        Random random = new Random();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 1000; i++) {
            int randomInt = random.nextInt(10_000);
            if (randomInt < min) {
                min = randomInt;
            }
            bst.add(randomInt);
        }

        int expected = min;
        int actual = bst.min();
        assertEquals(expected, actual);
    }

    @RepeatedTest(200)
    void testMax() {
        Random random = new Random();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < 1000; i++) {
            int randomInt = random.nextInt(10_000);
            if (randomInt > max) {
                max = randomInt;
            }
            bst.add(randomInt);
        }

        int expected = max;
        int actual = bst.max();
        assertEquals(expected, actual);
    }

    @Test
    void testContains() {
        //1        9
        //2      7  10
        //3    6  8   11
        //4  3
        bst.add(9);
        bst.add(7);
        bst.add(10);
        bst.add(8);
        bst.add(6);
        bst.add(11);
        bst.add(3);
        assertAll(
                () -> assertTrue(bst.contains(9)),
                () -> assertTrue(bst.contains(7)),
                () -> assertTrue(bst.contains(10)),
                () -> assertTrue(bst.contains(11)),
                () -> assertTrue(bst.contains(3)),
                () -> assertTrue(bst.contains(8)),
                () -> assertFalse(bst.contains(1000)),
                () -> assertFalse(bst.contains(43)),
                () -> assertFalse(bst.contains(0))
        );
    }


    static boolean validateTraversal() {

        assert root != null;
        // inOrder test
        System.out.println("\ninOrder");
        TestBinaryNode.inOrder(root);
        Iterator<BST.BinaryNode<Integer>> bstInorder =
                bst.traverseIterator(TraversalType.INORDER);
        Iterator<TestBinaryNode> inOrder = TestBinaryNode.getList().iterator();
        for (int i = 0; i < bst.size(); i++) {
            int actual = bstInorder.next().getValue();
            int expected = inOrder.next().value;
            System.out.println("actual: " + actual + "\nexpected: " + expected);
            if (actual != expected) {
                return false;
            }
        }

        // levelOrder
        TestBinaryNode.levelOrder(root);
        Iterator<TestBinaryNode> levelOrder =
                TestBinaryNode.getList().iterator();
        Iterator<BST.BinaryNode<Integer>> bstLevelOrder =
                bst.traverseIterator(TraversalType.LEVELORDER);
        System.out.println("\nlevelOrder");
        for (int i = 0; i < bst.size(); i++) {
            int actual = bstLevelOrder.next().getValue();
            int expected = levelOrder.next().value;
            System.out.println("actual: " + actual + "\nexpected: " + expected);

            if (actual != expected) {
                return false;
            }
        }

        // preOrder
        System.out.println("\npreOrder");
        TestBinaryNode.preOrder(root);
        Iterator<TestBinaryNode> preOrder = TestBinaryNode.getList().iterator();
        Iterator<BST.BinaryNode<Integer>> bstPreorder =
                bst.traverseIterator(TraversalType.PREORDER);

        for (int i = 0; i < bst.size(); i++) {
            int actual = bstPreorder.next().getValue();
            int expected = preOrder.next().value;
            System.out.println("actual: " + actual + "\nexpected: " + expected);
            if (actual != expected) {
                return false;
            }
        }

        // postOrder
        System.out.println("\npostOrder\n");
        TestBinaryNode.postOrder(root);
        Iterator<TestBinaryNode> postOrder =
                TestBinaryNode.getList().iterator();
        Iterator<BST.BinaryNode<Integer>> bstPostorder =
                bst.traverseIterator(TraversalType.POSTORDER);
        for (int i = 0; i < bst.size(); i++) {
            int actual = bstPostorder.next().getValue();
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

        public TestBinaryNode(Integer value, TestBinaryNode left,
                              TestBinaryNode right) {
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

        public static TestBinaryNode add(TestBinaryNode node, int value) {
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

    static class ToIntegerArrayConverter
            extends TypedArgumentConverter<String, Integer[]> {
        protected ToIntegerArrayConverter() {
            super(String.class, Integer[].class);
        }

        @Override
        protected Integer[] convert(String source)
                throws ArgumentConversionException {
            if (source.isEmpty()) {
                return new Integer[0];
            }
            return Arrays.stream(source.split(",")).map(
                    Integer::valueOf).toArray(Integer[]::new);
        }
    }
}