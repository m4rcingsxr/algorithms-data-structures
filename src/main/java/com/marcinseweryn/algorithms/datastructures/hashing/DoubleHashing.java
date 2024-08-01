package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The DoubleHashing class represents a hash table implemented using double hashing.
 * This hash table allows for inserting, searching, and deleting key-value pairs.
 * It uses two hash functions to compute the bucket index and step size.
 * When the load factor of the hash table exceeds 0.75, the hash table is rehashed to double its original size.
 *
 * @param <K> the type of keys maintained by this hash table
 * @param <V> the type of mapped values
 */
public class DoubleHashing<K, V> implements HashTable<K, V> {

    private static final int INITIAL_CAPACITY = 16;  // Initial size of the hash table
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;  // Threshold for resizing
    private int size;  // Number of key-value pairs in the hash table
    private int capacity;  // Current capacity of the hash table
    private Entry<K, V>[] hashTable;  // Array to store key-value pairs

    /**
     * Constructs an empty hash table with an initial capacity of 16.
     */
    public DoubleHashing() {
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

        int index = hash1(key);
        int stepSize = hash2(key);
        int i = 0;

        // Use double hashing to resolve collisions
        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                hashTable[index].value = value;  // Update the value if key already exists
                return;
            }
            i++;
            index = (hash1(key) + i * stepSize) % capacity;
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
     * Primary hash function using a bit-mixing algorithm.
     * The hash code is computed using the key's hashCode() method.
     *
     * @param key the key to hash
     * @return the computed hash index
     */
    private int hash1(K key) {
        int h = key.hashCode();
        return (h ^ (h >>> 16)) & (capacity - 1);
    }

    /**
     * Secondary hash function to determine the step size in double hashing.
     * Ensures a non-zero step size for probing.
     *
     * @param key the key to hash
     * @return the step size for double hashing
     */
    private int hash2(K key) {
        int h = key.hashCode();
        int prime = largestPrimeLessThan(capacity);
        return prime - (h % prime);
    }

    /**
     * Finds the largest prime number less than a given number.
     *
     * @param n the upper limit
     * @return the largest prime number less than n
     */
    private int largestPrimeLessThan(int n) {
        for (int i = n - 1; i >= 2; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 3;  // Fallback
    }

    /**
     * Checks if a number is prime.
     *
     * @param num the number to check
     * @return true if the number is prime, false otherwise
     */
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
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

        int index = hash1(key);
        int stepSize = hash2(key);
        int i = 0;

        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                return hashTable[index].value;  // Return the value if key is found
            }
            i++;
            index = (hash1(key) + i * stepSize) % capacity;
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

        int index = hash1(key);
        int stepSize = hash2(key);
        int i = 0;

        // Find the key to remove
        while (hashTable[index] != null) {
            if (hashTable[index].key.equals(key)) {
                V oldValue = hashTable[index].value;
                hashTable[index] = null;
                size--;

                // Rehash all keys in the same cluster to avoid breaking the probing chain
                index = (index + 1) % capacity;  // Start rehashing from the next index
                while (hashTable[index] != null) {
                    Entry<K, V> entryToRehash = hashTable[index];
                    hashTable[index] = null;
                    size--;
                    put(entryToRehash.key, entryToRehash.value);  // Reinsert the rehashed entry
                    index = (index + 1) % capacity;  // Continue probing for rehashing
                }

                return oldValue;  // Return the value associated with the removed key
            }
            i++;
            index = (hash1(key) + i * stepSize) % capacity;
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
