package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The SeparateChaining class represents a hash table implemented using separate chaining.
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
public class SeparateChaining<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 16;  // Initial size of the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;  // Threshold for resizing
    private int size;  // Number of key-value pairs in the hash table
    private int capacity;  // Current capacity of the hash table
    protected LinkedList<Entry<K, V>>[] hashTable;  // Array of linked lists to store key-value pairs

    /**
     * Constructs an empty hash table with an initial capacity of 16.
     */
    public SeparateChaining() {
        this.capacity = INITIAL_CAPACITY;
        this.size = 0;
        this.hashTable = new LinkedList[this.capacity];
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
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }

        // Check if the key already exists and update the value
        for (Entry<K, V> entry : hashTable[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // If key does not exist, add a new entry to the linked list
        hashTable[index].add(new Entry<>(key, value));
        size++;
    }

    /**
     * Resizes the hash table to double its current capacity.
     * Rehashes all existing key-value pairs to the new table.
     */
    private void resize() {
        this.capacity *= 2;
        LinkedList<Entry<K, V>>[] oldTable = this.hashTable;
        this.hashTable = new LinkedList[this.capacity];
        this.size = 0;

        // Rehash all non-null entries from the old table into the new table
        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    this.put(entry.key, entry.value);
                }
            }
        }
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
        LinkedList<Entry<K, V>> bucket = hashTable[index];
        if (bucket != null) {
            for (Entry<K, V> entry : bucket) {
                if (entry.key.equals(key)) {
                    return entry.value;  // Return the value if key is found
                }
            }
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
        LinkedList<Entry<K, V>> bucket = hashTable[index];
        if (bucket != null) {
            Iterator<Entry<K, V>> iterator = bucket.iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (entry.key.equals(key)) {
                    V value = entry.value;
                    iterator.remove();
                    size--;
                    return value;  // Return the value associated with the removed key
                }
            }
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
        private Iterator<Entry<K, V>> currentBucketIterator = null;  // Iterator for the current linked list bucket
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

            Entry<K, V> entry = currentBucketIterator.next();
            entriesCounted++;
            if (!currentBucketIterator.hasNext()) {
                moveToNextNonNullEntry();
            }
            return entry;
        }

        private void moveToNextNonNullEntry() {
            while (currentIndex < capacity && (hashTable[currentIndex] == null || !hashTable[currentIndex].iterator().hasNext())) {
                currentIndex++;
            }
            if (currentIndex < capacity) {
                currentBucketIterator = hashTable[currentIndex].iterator();
            }
        }
    }
}

