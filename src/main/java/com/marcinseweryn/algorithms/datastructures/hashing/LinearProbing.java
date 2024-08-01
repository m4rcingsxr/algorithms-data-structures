package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The LinearProbing class represents a hash table implemented using linear probing.
 * This hash table allows for inserting, searching, and deleting key-value pairs.
 * It uses a hash function that leverages the hashCode() method of the key objects
 * to compute the bucket index. When the load factor of the hash table exceeds 0.75,
 * the hash table is rehashed to double its original size.
 *
 * This class also includes methods for calculating the load factor, checking the
 * size and emptiness of the table, and converting the hash table to a string representation.
 *
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
public class LinearProbing<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 16;  // Initial size of the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;  // Threshold for resizing
    private int size;  // Number of key-value pairs in the hash table
    private int capacity;  // Current capacity of the hash table
    private Entry<K, V>[] hashTable;  // Array to store key-value pairs

    /**
     * Constructs an empty hash table with an initial capacity of 16.
     */
    public LinearProbing() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.hashTable = new Entry[this.capacity];
    }

    /**
     * Inserts a key-value pair into the hash table.
     * If the key already exists, the value is updated.
     * Resizes the table if the load factor exceeds the threshold.
     *
     * @param key   the key to insert
     * @param value the value associated with the key
     * @throws IllegalArgumentException if the key is null
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        // Resize the table if the load factor exceeds the threshold
        if (size >= capacity * LOAD_FACTOR_THRESHOLD) {
            this.resize();
        }

        int index = this.hash(key);  // Compute the hash index for the key
        while (this.hashTable[index] != null) {
            if (this.hashTable[index].key.equals(key)) {
                this.hashTable[index].value = value;  // Update the value if key already exists
                return;
            }
            index = (index + 1) & (capacity - 1);  // Linear probing to find the next available slot
        }
        hashTable[index] = new Entry<>(key, value);  // Insert the new key-value pair
        size++;
    }

    /**
     * Resizes the hash table to double its current capacity.
     * Rehashes all existing key-value pairs to the new table.
     */
    private void resize() {
        this.capacity *= 2;
        Entry<K, V>[] oldTable = this.hashTable;
        this.hashTable = new Entry[this.capacity];
        this.size = 0;

        // Rehash all non-null entries from the old table into the new table
        Arrays.stream(oldTable).filter(Objects::nonNull).forEach(e -> this.put(e.key, e.value));
    }

    /**
     * Computes the hash index for a given key using a bit-mixing algorithm.
     * The hash code is computed using the key's hashCode() method.
     * The high bits are mixed into the low bits to ensure a more uniform distribution.
     *
     * Hash function explanation:
     * - Computes the hash code of the key.
     * - Right shifts the hash code by 16 bits (moving high bits to lower positions).
     * - XORs the original hash code with its shifted version to mix bits.
     * - Applies a bitwise AND with (capacity - 1) to ensure the index is within bounds.
     *
     * @param key the key to hash
     * @return the computed hash index
     */
    private int hash(K key) {
        int h = key.hashCode();  // Get the hash code from the key
        // Mix the hash bits and use bitwise AND to keep index within the capacity
        return (h ^ (h >>> 16)) & (capacity - 1);
    }

    /**
     * Retrieves the value associated with the specified key from the hash table.
     * If the key does not exist, returns null.
     *
     * @param key the key whose associated value is to be returned
     * @return the value associated with the key, or null if the key is not found
     */
    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);  // Compute the hash index for the key
        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                return hashTable[index].value;  // Return the value if key is found
            }
            index = (index + 1) & (capacity - 1);  // Continue probing
        }

        return null;  // Return null if the key is not found
    }

    /**
     * Removes the key-value pair associated with the specified key from the hash table.
     * If the key does not exist, returns null.
     *
     * @param key the key to be removed
     * @return the value that was associated with the key, or null if the key was not found
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }

        int index = hash(key);  // Compute the hash index for the key
        while (this.hashTable[index] != null) {
            if (this.hashTable[index].key.equals(key)) {
                V oldValue = this.hashTable[index].value;
                this.hashTable[index] = null;
                this.size--;

                // Rehash all keys in the same cluster to avoid breaking the probing chain
                index = (index + 1) & (this.capacity - 1);
                while (this.hashTable[index] != null) {
                    Entry<K, V> entryToRehash = this.hashTable[index];
                    this.hashTable[index] = null;
                    this.size--;
                    put(entryToRehash.key, entryToRehash.value);  // Reinsert the rehashed entry
                    index = (index + 1) & (this.capacity - 1);  // Continue probing for rehashing
                }

                return oldValue;  // Return the value associated with the removed key
            }
            index = (index + 1) & (this.capacity - 1);  // Continue probing
        }

        return null;  // Return null if the key was not found
    }

    /**
     * Returns the number of key-value pairs in the hash table.
     *
     * @return the number of key-value pairs in the hash table
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if the hash table contains no key-value pairs.
     *
     * @return true if the hash table is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<Entry<K, V>> {
        private int currentIndex = 0;  // Current index in the hash table array
        private int entriesCounted = 0; // Number of entries counted so far

        public HashTableIterator() {
            // Move to the first non-null entry in the hash table
            moveToNextNonNullEntry();
        }

        @Override
        public boolean hasNext() {
            return entriesCounted < size;
        }

        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the hash table");
            }

            Entry<K, V> entry = hashTable[currentIndex++];
            entriesCounted++;
            moveToNextNonNullEntry();
            return entry;
        }

        private void moveToNextNonNullEntry() {
            while (currentIndex < capacity && hashTable[currentIndex] == null) {
                currentIndex++;
            }
        }
    }
}
