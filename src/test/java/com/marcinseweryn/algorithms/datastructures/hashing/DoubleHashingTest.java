package com.marcinseweryn.algorithms.datastructures.hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoubleHashingTest {

    private DoubleHashing<String, Integer> stringTable;
    private DoubleHashing<CollidingKey, Integer> collidingTable;

    @BeforeEach
    void setUp() {
        stringTable = new DoubleHashing<>();
        collidingTable = new DoubleHashing<>();
    }

    @Test
    void givenEmptyHashTable_whenPutCalled_thenElementShouldBeInserted() {
        stringTable.put("apple", 1);
        assertEquals(1, stringTable.size());
        assertEquals(1, stringTable.get("apple"));
    }

    @Test
    void givenHashTableWithElement_whenGetCalled_thenShouldReturnCorrectValue() {
        stringTable.put("banana", 2);
        Integer value = stringTable.get("banana");
        assertNotNull(value);
        assertEquals(2, value);
    }

    @Test
    void givenHashTableWithElement_whenGetCalledWithNonExistentKey_thenShouldReturnNull() {
        stringTable.put("banana", 2);
        assertNull(stringTable.get("apple"));
    }

    @Test
    void givenHashTable_whenPutCalledWithExistingKey_thenValueShouldBeUpdated() {
        stringTable.put("cherry", 3);
        stringTable.put("cherry", 33);
        assertEquals(1, stringTable.size());
        assertEquals(33, stringTable.get("cherry"));
    }

    @Test
    void givenHashTableWithMultipleElements_whenRemoveCalled_thenShouldRemoveAndReturnValue() {
        stringTable.put("one", 1);
        stringTable.put("two", 2);
        stringTable.put("three", 3);

        assertEquals(1, stringTable.remove("one"));
        assertNull(stringTable.get("one"));
        assertEquals(2, stringTable.size());
    }

    @Test
    void givenHashTableWithMultipleElements_whenRemoveCalledWithNonExistentKey_thenShouldReturnNull() {
        stringTable.put("one", 1);
        assertNull(stringTable.remove("two"));
    }

    @Test
    void givenEmptyHashTable_whenSizeCalled_thenShouldReturnZero() {
        assertEquals(0, stringTable.size());
    }

    @Test
    void givenEmptyHashTable_whenIsEmptyCalled_thenShouldReturnTrue() {
        assertTrue(stringTable.isEmpty());
    }

    @Test
    void givenHashTable_whenIsEmptyCalled_thenShouldReturnFalse() {
        stringTable.put("one", 1);
        assertFalse(stringTable.isEmpty());
    }

    @Test
    void givenHashTable_whenResizeTriggered_thenElementsShouldBeRehashedCorrectly() {
        for (int i = 0; i < 20; i++) {
            stringTable.put("key" + i, i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i, stringTable.get("key" + i));
        }
        assertEquals(20, stringTable.size());
    }

    @Test
    void givenNullKey_whenPutCalled_thenShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> stringTable.put(null, 1));
    }

    @Test
    void givenNullKey_whenGetCalled_thenShouldReturnNull() {
        assertNull(stringTable.get(null));
    }

    @Test
    void givenNullKey_whenRemoveCalled_thenShouldReturnNull() {
        assertNull(stringTable.remove(null));
    }

    @Test
    void givenCollidingKeys_whenInserted_thenShouldHandleCollisionsCorrectly() {
        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);

        collidingTable.put(key1, 10);
        collidingTable.put(key2, 20);
        collidingTable.put(key3, 30);

        assertEquals(10, collidingTable.get(key1), "Failed to retrieve value for key1 after insertion");
        assertEquals(20, collidingTable.get(key2), "Failed to retrieve value for key2 after insertion");
        assertEquals(30, collidingTable.get(key3), "Failed to retrieve value for key3 after insertion");

        assertEquals(3, collidingTable.size(), "Hash table size should be 3 after inserting 3 colliding elements");


        assertNotNull(collidingTable.get(key1), "Key1 should be retrievable");
        assertNotNull(collidingTable.get(key2), "Key2 should be retrievable");
        assertNotNull(collidingTable.get(key3), "Key3 should be retrievable");
    }

}
