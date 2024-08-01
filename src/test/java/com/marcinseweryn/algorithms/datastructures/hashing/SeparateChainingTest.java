package com.marcinseweryn.algorithms.datastructures.hashing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeparateChainingTest {

    private SeparateChaining<String, Integer> stringTable;
    private SeparateChaining<CollidingKey, Integer> collidingTable;

    @BeforeEach
    void setUp() {
        stringTable = new SeparateChaining<>();
        collidingTable = new SeparateChaining<>();
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
    void givenHashTable_whenRemoveCausesRehash_thenAllElementsShouldBeAccessible() {
        stringTable.put("one", 1);
        stringTable.put("two", 2);
        stringTable.put("three", 3);
        stringTable.put("four", 4);

        stringTable.remove("two");

        assertEquals(1, stringTable.get("one"));
        assertEquals(3, stringTable.get("three"));
        assertEquals(4, stringTable.get("four"));
        assertEquals(3, stringTable.size());
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

        assertEquals(10, collidingTable.get(key1));
        assertEquals(20, collidingTable.get(key2));
        assertEquals(30, collidingTable.get(key3));

        assertEquals(3, collidingTable.size());


        int index = (key1.hashCode() ^ (key1.hashCode() >>> 16)) & (collidingTable.hashTable.length - 1);
        assertEquals(3, collidingTable.hashTable[index].size(), "Linked list size should be 3 due to collision handling");
    }

    @Test
    void givenCollidingKeys_whenRemoved_thenShouldMaintainChainIntegrity() {
        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);

        collidingTable.put(key1, 10);
        collidingTable.put(key2, 20);
        collidingTable.put(key3, 30);

        collidingTable.remove(key2);

        assertEquals(10, collidingTable.get(key1));
        assertNull(collidingTable.get(key2));
        assertEquals(30, collidingTable.get(key3));

        assertEquals(2, collidingTable.size());


        int index = (key1.hashCode() ^ (key1.hashCode() >>> 16)) & (collidingTable.hashTable.length - 1);
        assertEquals(2, collidingTable.hashTable[index].size(), "Linked list size should be 2 after removing one key");
    }

    @Test
    void givenCollidingKeys_whenRemoved_thenShouldNotHaveNullGapsInChain() {
        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);

        collidingTable.put(key1, 10);
        collidingTable.put(key2, 20);
        collidingTable.put(key3, 30);


        collidingTable.remove(key1);


        assertEquals(20, collidingTable.get(key2));
        assertEquals(30, collidingTable.get(key3));


        collidingTable.remove(key3);

        assertEquals(1, collidingTable.size());
        assertEquals(20, collidingTable.get(key2));
        assertNull(collidingTable.get(key3));
    }

}
