package com.marcinseweryn.algorithms.datastructures.hashing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinearProbingTest {

    @Test
    void givenEmptyHashTable_whenPutCalled_thenElementShouldBeInserted() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("apple", 1);
        assertEquals(1, hashTable.size());
        assertEquals(1, hashTable.get("apple"));
    }

    @Test
    void givenHashTableWithElement_whenGetCalled_thenShouldReturnCorrectValue() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("banana", 2);
        Integer value = hashTable.get("banana");
        assertNotNull(value);
        assertEquals(2, value);
    }

    @Test
    void givenHashTableWithElement_whenGetCalledWithNonExistentKey_thenShouldReturnNull() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("banana", 2);
        assertNull(hashTable.get("apple"));
    }

    @Test
    void givenHashTable_whenPutCalledWithExistingKey_thenValueShouldBeUpdated() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("cherry", 3);
        hashTable.put("cherry", 33);
        assertEquals(1, hashTable.size());
        assertEquals(33, hashTable.get("cherry"));
    }

    @Test
    void givenHashTableWithMultipleElements_whenRemoveCalled_thenShouldRemoveAndReturnValue() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        assertEquals(1, hashTable.remove("one"));
        assertNull(hashTable.get("one"));
        assertEquals(2, hashTable.size());
    }

    @Test
    void givenHashTableWithMultipleElements_whenRemoveCalledWithNonExistentKey_thenShouldReturnNull() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("one", 1);
        assertNull(hashTable.remove("two"));
    }

    @Test
    void givenEmptyHashTable_whenSizeCalled_thenShouldReturnZero() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        assertEquals(0, hashTable.size());
    }

    @Test
    void givenEmptyHashTable_whenIsEmptyCalled_thenShouldReturnTrue() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        assertTrue(hashTable.isEmpty());
    }

    @Test
    void givenHashTable_whenIsEmptyCalled_thenShouldReturnFalse() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("one", 1);
        assertFalse(hashTable.isEmpty());
    }

    @Test
    void givenHashTable_whenResizeTriggered_thenElementsShouldBeRehashedCorrectly() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        for (int i = 0; i < 20; i++) {
            hashTable.put("key" + i, i);
        }
        for (int i = 0; i < 20; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }
        assertEquals(20, hashTable.size());
    }

    @Test
    void givenHashTable_whenRemoveCausesRehash_thenAllElementsShouldBeAccessible() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);
        hashTable.put("four", 4);


        hashTable.remove("two");


        assertEquals(1, hashTable.get("one"));
        assertEquals(3, hashTable.get("three"));
        assertEquals(4, hashTable.get("four"));
        assertEquals(3, hashTable.size());
    }

    @Test
    void givenNullKey_whenPutCalled_thenShouldThrowException() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        assertThrows(IllegalArgumentException.class, () -> hashTable.put(null, 1));
    }

    @Test
    void givenNullKey_whenGetCalled_thenShouldReturnNull() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        assertNull(hashTable.get(null));
    }

    @Test
    void givenNullKey_whenRemoveCalled_thenShouldReturnNull() {
        LinearProbing<String, Integer> hashTable = new LinearProbing<>();
        assertNull(hashTable.remove(null));
    }

    @Test
    void givenCollidingKeys_whenInserted_thenShouldHandleCollisionsCorrectly() {
        LinearProbing<CollidingKey, Integer> hashTable = new LinearProbing<>();

        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);

        hashTable.put(key1, 10);
        hashTable.put(key2, 20);
        hashTable.put(key3, 30);

        assertEquals(10, hashTable.get(key1));
        assertEquals(20, hashTable.get(key2));
        assertEquals(30, hashTable.get(key3));

        assertEquals(3, hashTable.size());
    }

    @Test
    void givenCollidingKeys_whenRemoved_thenShouldMaintainChainIntegrity() {
        LinearProbing<CollidingKey, Integer> hashTable = new LinearProbing<>();

        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);

        hashTable.put(key1, 10);
        hashTable.put(key2, 20);
        hashTable.put(key3, 30);

        hashTable.remove(key2);

        assertEquals(10, hashTable.get(key1));
        assertNull(hashTable.get(key2));
        assertEquals(30, hashTable.get(key3));

        assertEquals(2, hashTable.size());
    }

    @Test
    void givenCollidingKeys_whenRemoved_thenShouldNotHaveNullGapsInChain() {
        LinearProbing<CollidingKey, Integer> hashTable = new LinearProbing<>();

        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);

        hashTable.put(key1, 10);
        hashTable.put(key2, 20);
        hashTable.put(key3, 30);

        hashTable.remove(key1);

        assertEquals(20, hashTable.get(key2));
        assertEquals(30, hashTable.get(key3));

        hashTable.remove(key3);

        assertEquals(1, hashTable.size());
        assertEquals(20, hashTable.get(key2));
        assertNull(hashTable.get(key3));
    }

    @Test
    void givenCollidingKeys_whenTableRehashes_thenShouldRehashWithoutNullsInChain() {
        LinearProbing<CollidingKey, Integer> hashTable = new LinearProbing<>();

        CollidingKey key1 = new CollidingKey("key1", 1);
        CollidingKey key2 = new CollidingKey("key2", 1);
        CollidingKey key3 = new CollidingKey("key3", 1);
        CollidingKey key4 = new CollidingKey("key4", 1);

        hashTable.put(key1, 10);
        hashTable.put(key2, 20);
        hashTable.put(key3, 30);
        hashTable.put(key4, 40);

        for (int i = 5; i <= 13; i++) {
            hashTable.put(new CollidingKey("key" + i, 1), i * 10);
        }

        assertEquals(10, hashTable.get(key1));
        assertEquals(20, hashTable.get(key2));
        assertEquals(30, hashTable.get(key3));
        assertEquals(40, hashTable.get(key4));
    }

}
