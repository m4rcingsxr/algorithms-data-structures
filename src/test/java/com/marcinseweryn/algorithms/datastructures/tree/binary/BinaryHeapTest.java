package com.marcinseweryn.algorithms.datastructures.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {
    BinaryHeap<Integer> bh;

    @BeforeEach
    void beforeEach() {
        bh = new BinaryHeap<>(10);
    }

    @Test
    void emptyBinaryHeapTest() {
        Executable actual = () -> bh.extractHead(BinaryHeap.MIN_HEAP);
        assertAll(
                () -> assertThrows(IllegalStateException.class, actual),
                () -> assertEquals(0, bh.size()),
                () -> assertTrue(bh.isEmpty())
        );
    }

    @Test
    void testMinHeapProperty() {
        Integer[] numbers = {3, 2, 6, 5, 8, 7, 4, 1, 0, 9};

        for (Integer number : numbers) {
            bh.add(number, BinaryHeap.MIN_HEAP);
        }

        for (int i = 0; i < 10; i++) {
            assertEquals(i, bh.extractHead(BinaryHeap.MIN_HEAP));
        }

    }

    @Test
    void testMaxHeapProperty() {
        Integer[] numbers = {3, 2, 6, 5, 8, 7, 4, 1, 0, 9};

        for (Integer number : numbers) {
            bh.add(number, BinaryHeap.MAX_HEAP);
        }

        for (int i = 9; i >= 0; i--) {
            assertEquals(i, bh.extractHead(BinaryHeap.MAX_HEAP));
        }
    }

}