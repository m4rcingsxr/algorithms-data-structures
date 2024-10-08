package com.marcinseweryn.algorithms.datastructures.linkedlist;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CircularDoublyLinkedListTest {

    @Test
    void givenNewLinkedList_whenCreated_thenShouldBeEmpty() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void givenSingleElement_whenAdded_thenShouldPeekFirstAndLastCorrectly() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        assertEquals(10, list.peekFirst());
        assertEquals(10, list.peekLast());

        // Ensure circular property for a single element
        assertSame(list.head, list.tail);
        assertSame(list.head, list.head.next);
        assertSame(list.head, list.head.previous);
    }

    @Test
    void givenSingleElement_whenRemoved_thenShouldBeEmpty() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        list.removeFirst();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    void givenEmptyList_whenRemoveFirstCalled_thenShouldReturnNull() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertNull(list.removeFirst());
    }

    @Test
    void givenEmptyList_whenRemoveLastCalled_thenShouldReturnNull() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertNull(list.removeLast());
    }

    @Test
    void givenEmptyList_whenAddFirstCalled_thenShouldAddElementAtStart() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.addFirst(10);
        assertEquals(10, list.peekFirst());

        // Ensure circular property for a single element
        assertSame(list.head, list.tail);
        assertSame(list.head, list.head.next);
        assertSame(list.head, list.head.previous);
    }

    @Test
    void givenEmptyList_whenAddLastCalled_thenShouldAddElementAtEnd() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.addLast(10);
        assertEquals(10, list.peekLast());

        // Ensure circular property for a single element
        assertSame(list.head, list.tail);
        assertSame(list.head, list.head.next);
        assertSame(list.head, list.head.previous);
    }

    static Stream<Arguments> givenInitialArrayAndElement_whenAddCalled_thenShouldAddElementAtCorrectIndex() {
        return Stream.of(
                Arguments.of(new Integer[]{}, 0, 10, new Integer[]{10}),
                Arguments.of(new Integer[]{10}, 1, 20, new Integer[]{10, 20}),
                Arguments.of(new Integer[]{10, 20}, 1, 15, new Integer[]{10, 15, 20}),
                Arguments.of(new Integer[]{10, 20}, 0, 5, new Integer[]{5, 10, 20})
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenInitialArrayAndElement_whenAddCalled_thenShouldAddElementAtCorrectIndex(
            Integer[] initialArray, int index, Integer element, Integer[] expected) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();

        for (Integer i : initialArray) {
            list.add(i);
        }

        list.add(index, element);
        assertArrayEquals(expected, toArray(list));

        // Ensure circular property and correct connections
        assertSame(list.tail.next, list.head);
        assertSame(list.head.previous, list.tail);
        checkConnections(list);
    }

    private void checkConnections(CircularDoublyLinkedList<Integer> list) {
        Node<Integer> current = list.head;
        for (int i = 0; i < list.size(); i++) {
            assertSame(current, current.next.previous);
            assertSame(current, current.previous.next);
            current = current.next;
        }
    }

    private Integer[] toArray(CircularDoublyLinkedList<Integer> list) {
        Integer[] array = new Integer[list.size()];
        int i = 0;
        for (Integer elem : list) {
            array[i++] = elem;
        }
        return array;
    }

    @Test
    void givenInvalidIndex_whenAddCalled_thenShouldThrowException() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));
    }

    static Stream<Arguments> givenInitialArrayAndIndex_whenRemoveCalled_thenShouldRemoveCorrectElement() {
        return Stream.of(
                Arguments.of(new Integer[]{10, 20, 30}, 0, 10, new Integer[]{20, 30}),
                Arguments.of(new Integer[]{10, 20, 30}, 1, 20, new Integer[]{10, 30}),
                Arguments.of(new Integer[]{10, 20, 30}, 2, 30, new Integer[]{10, 20})
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenInitialArrayAndIndex_whenRemoveCalled_thenShouldRemoveCorrectElement(
            Integer[] initialArray, int index, Integer expectedRemovedElement,
            Integer[] expectedList) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();

        for (Integer i : initialArray) {
            list.add(i);
        }

        Integer removedElement = list.remove(index);
        assertEquals(expectedRemovedElement, removedElement);
        assertArrayEquals(expectedList, toArray(list));

        // Ensure circular property and correct connections
        if (!list.isEmpty()) {
            assertSame(list.tail.next, list.head);
            assertSame(list.head.previous, list.tail);
            checkConnections(list);
        }
    }

    @Test
    void givenNonEmptyList_whenRemoveElementNotPresent_thenShouldReturnFalse() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        list.add(20);
        assertFalse(list.remove(Integer.valueOf(30)));
    }

    @Test
    void givenNonEmptyList_whenRemoveElementPresent_thenShouldReturnTrueAndRemoveElement() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        list.add(20);
        assertTrue(list.remove(Integer.valueOf(20)));
        assertFalse(list.contains(20));

        // Ensure circular property and correct connections
        assertSame(list.tail.next, list.head);
        assertSame(list.head.previous, list.tail);
        checkConnections(list);
    }

    @Test
    void givenNonEmptyList_whenPeekFirst_thenShouldReturnFirstElement() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        list.add(20);
        assertEquals(10, list.peekFirst());
    }

    @Test
    void givenNonEmptyList_whenPeekLast_thenShouldReturnLastElement() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        list.add(20);
        assertEquals(20, list.peekLast());
    }

    @Test
    void givenEmptyList_whenPeekFirstOrLast_thenShouldReturnNull() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertNull(list.peekFirst());
        assertNull(list.peekLast());
    }

    static Stream<Arguments> givenInitialArrayAndElement_whenSearchCalled_thenShouldReturnCorrectResults() {
        return Stream.of(
                Arguments.of(new Integer[]{10, 20}, 10, 0, true),
                Arguments.of(new Integer[]{10, 20}, 20, 1, true),
                Arguments.of(new Integer[]{10, 20}, 30, -1, false)
        );
    }

    @ParameterizedTest
    @MethodSource
    void givenInitialArrayAndElement_whenSearchCalled_thenShouldReturnCorrectResults(
            Integer[] initialArray, Integer element, int expectedIndex, boolean expectedContains) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();

        for (Integer i : initialArray) {
            list.add(i);
        }

        assertEquals(expectedIndex, list.indexOf(element));
        assertEquals(expectedContains, list.contains(element));
    }

    @Test
    void givenEmptyList_whenSearchElement_thenShouldReturnMinusOne() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertEquals(-1, list.indexOf(10));
    }

    @Test
    void givenNonEmptyList_whenCheckElementExists_thenShouldReturnTrue() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        assertTrue(list.contains(10));
    }

    @Test
    void givenNonEmptyList_whenCheckElementDoesNotExist_thenShouldReturnFalse() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        assertFalse(list.contains(20));
    }

    @Test
    void givenEmptyList_whenCheckAnyElement_thenShouldReturnFalse() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        assertFalse(list.contains(10));
    }

    @Test
    void givenNonEmptyList_whenIterate_thenShouldTraverseInCorrectOrder() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        list.add(20);
        list.add(30);

        Iterator<Integer> iterator = list.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(10, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(30, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenNonEmptyList_whenExhaustIterator_thenShouldThrowException() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(10);
        Iterator<Integer> iterator = list.iterator();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void givenEmptyList_whenIterate_thenHasNextShouldReturnFalse() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void givenEmptyList_whenNextCalled_thenShouldThrowException() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        Iterator<Integer> iterator = list.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void givenNullElement_whenAddedToList_thenShouldBeHandledCorrectly() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(null);
        assertTrue(list.contains(null));
    }

    @Test
    void givenNullElementInList_whenRemoved_thenShouldRemoveCorrectly() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(null);
        assertTrue(list.remove(null));
        assertFalse(list.contains(null));
    }

    @Test
    void givenLargeNumberOfElements_whenAdded_thenShouldHandleCorrectly() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        assertEquals(10000, list.size());
        assertEquals(0, list.peekFirst());
        assertEquals(9999, list.peekLast());

        // Ensure circular property and correct connections
        assertSame(list.tail.next, list.head);
        assertSame(list.head.previous, list.tail);
        checkConnections(list);
    }

    @Test
    void givenLargeNumberOfElements_whenRemoved_thenShouldHandleCorrectly() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(i);
        }
        for (int i = 0; i < 10000; i++) {
            list.remove(Integer.valueOf(i));
        }
        assertTrue(list.isEmpty());
    }
}
