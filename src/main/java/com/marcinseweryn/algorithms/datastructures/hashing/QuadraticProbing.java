package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The QuadraticProbing class represents a hash table implemented using quadratic probing.
 * This hash table allows for inserting, searching, and deleting key-value pairs.
 * It uses a hash function that leverages the hashCode() method of the key objects
 * to compute the bucket index. When the load factor of the hash table exceeds 0.75,
 * the hash table is rehashed to double its original size.
 *
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
public class QuadraticProbing<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 16;  // Initial size of the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;  // Threshold for resizing
    private static final int C1 = 1;  // Quadratic probing constant c1
    private static final int C2 = 3;  // Quadratic probing constant c2
    private int size;  // Number of key-value pairs in the hash table
    private int capacity;  // Current capacity of the hash table
    private Entry<K, V>[] hashTable;  // Array to store key-value pairs

    /**
     * Constructs an empty hash table with an initial capacity of 16.
     */
    public QuadraticProbing() {
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
            resize();
        }

        int index = hash(key);
        int i = 0;

        // Use quadratic probing to resolve collisions
        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                hashTable[index].value = value;  // Update the value if key already exists
                return;
            }
            i++;
            index = (hash(key) + C1 * i + C2 * i * i) & (capacity - 1);
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
        for (Entry<K, V> entry : oldTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
    }

    /**
     * Computes the hash index for a given key using a bit-mixing algorithm.
     * The hash code is computed using the key's hashCode() method.
     * The high bits are mixed into the low bits to ensure a more uniform distribution.
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

        int index = hash(key);
        int i = 0;

        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                return hashTable[index].value;  // Return the value if key is found
            }
            i++;
            index = (hash(key) + C1 * i + C2 * i * i) & (capacity - 1);
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

        int index = hash(key);
        int i = 0;

        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                V oldValue = hashTable[index].value;
                hashTable[index] = null;
                size--;

                // Rehash all keys in the same cluster to avoid breaking the probing chain
                i++;
                index = (hash(key) + C1 * i + C2 * i * i) % capacity;
                while (hashTable[index] != null) {
                    Entry<K, V> entryToRehash = hashTable[index];
                    hashTable[index] = null;
                    size--;
                    put(entryToRehash.key, entryToRehash.value);  // Reinsert the rehashed entry
                    i++;
                    index = (hash(key) + C1 * i + C2 * i * i) & (capacity - 1);
                }

                return oldValue;  // Return the value associated with the removed key
            }
            i++;
            index = (hash(key) + C1 * i + C2 * i * i) & (capacity - 1);
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
