package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class represent an implementation of a HashTable that's
 * using separate chaining for collision resolution. To proper
 * work of this hashtable key object must override equals and
 * hashcode. Collision occurs if keys are unequal and hashFunction
 * generate the same hashValue that represent index of associative
 * array
 *
 * @param <K> key
 * @param <V> value
 * @author Marcin Seweryn
 * @version 1.0
 * @see Object#equals(Object)
 * @see Object#hashCode()
 */
public class SeparateChaining<K, V>
        implements Iterable<SeparateChaining.Entry<K, V>> {

    /**
     * Compile time constant represent default load factor
     */
    private static final double LOAD_FACTOR = 0.80;

    /**
     * Compile time constant represent default capacity
     * of HashTable
     */
    private static final int DEFAULT_CAPACITY = 5;
    private LinkedList<Entry<K, V>>[] hashTable;
    private final double loadFactor;
    private int capacity;
    private int size;
    private int maxElementToResize;

    /**
     * Constructs a new, empty hashtable with the specified initial capacity
     * and the specified load factor.
     *
     * @param loadFactor decide when to resize HashTable
     * @param capacity   of the HashTable
     * @throws IllegalArgumentException for wrong arguments of loadFactor
     *                                  and capacity
     */
    public SeparateChaining(double loadFactor, int capacity) {
        if (loadFactor <= 0 || Double.isInfinite(loadFactor) || Double.isNaN(
                loadFactor)) {
            throw new IllegalArgumentException("loadFactor(" + loadFactor +
                                                       ") - illegal argument");
        } else if (capacity < 0) {
            throw new IllegalArgumentException("capacity(" + capacity + ") - " +
                                                       "illegal argument");
        }
        this.size = 0;
        this.loadFactor = loadFactor;
        this.capacity = capacity;
        this.maxElementToResize = (int) (loadFactor * capacity);
        this.hashTable = new LinkedList[capacity];
    }

    /**
     * Constructs a new, empty hashtable with default initial
     * capacity and default load factor
     */
    public SeparateChaining() {
        this(LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    /**
     * Maps specified key to specified value in this HashTable.
     * Key and Value cannot be null. If some value already
     * occurs for specified keys then values will be replaced
     *
     * @param key   - Entry key
     * @param value - Entry value
     * @return The previous value of the specified key in HashTable
     * or NULL if entry with this key does not exist
     */
    public V put(K key, V value) {
        return insert(key, value);
    }

    private V insert(K key, V value) {
        if (value == null || key == null) {
            throw new NullPointerException("Key & value != null");
        }

        Entry<K, V> entry = new Entry<>(key, value);
        int hash = key.hashCode();

        // 2's complement representation (positive/negative int) &
        // 0x7FFFFFFF(max integer(2^31-1) - most significant bit == 0 (sign bit)
        // in case of positive integers) with bitwise operator we make sure
        // that index is always positive [0, cap). Always in capacity with %
        int index = (hash & 0x7FFFFFFF) % hashTable.length;

        return insertEntry(entry, index);
    }

    private V insertEntry(Entry<K, V> entry, int index) {
        LinkedList<Entry<K, V>> bucket = hashTable[index]; // bucket might
        // reference to null
        K key = entry.key;

        // Make sure that if we invoke getEntry then null will
        // be returned <=> if LL does not contains element
        if (bucket == null) {
            hashTable[index] = new LinkedList<>();
            // Reinitialize bucket cuz might reference to null
            bucket = hashTable[index];
            assert bucket != null;
        }
        Entry<K, V> current = getEntry(index, key);

        // Entry does not exist in LL
        if (current == null) {
            bucket.add(entry);
            size++;
            if (size > maxElementToResize) {
                resizeTable();
            }

            // indicate that entry with this key does not exist
            return null;
        } else {
            V old = current.value;
            current.value = entry.value;
            return old;
        }
    }

    /**
     * Remove entry with specified key. Return value of
     * the key pair if entry does exist. Return null if
     * entry does not exist
     *
     * @param key - Entry key
     * @return value pair of key if entry exist or null
     * if entry with specified key does not exist
     * @throws NullPointerException if provided key is null
     */
    public V remove(K key) {
        // hash table do not accept null keys
        if (key == null) {
            throw new NullPointerException();
        }
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % hashTable.length;
        return removeEntry(index, key);
    }

    private V removeEntry(int index, K key) {
        Entry<K, V> entryToRemove = getEntry(index, key);
        if (entryToRemove != null) {
            LinkedList<Entry<K, V>> current = hashTable[index];
            current.remove(entryToRemove);
            size--;
            return entryToRemove.value;
        } else {
            return null;
        }
    }

    private void resizeTable() {
        this.capacity *= 2;
        this.maxElementToResize = (int) (this.loadFactor * capacity);

        LinkedList<Entry<K, V>>[] temp = hashTable;
        hashTable = Arrays.copyOf(hashTable, capacity);

        for (LinkedList<Entry<K, V>> entries : temp) {
            if (entries != null) {
                for (Entry<K, V> entry : entries) {
                    int hash = entry.key.hashCode();
                    int index = (hash & 0x7FFFFFFF) % hashTable.length;
                    LinkedList<Entry<K, V>> bucket = hashTable[index];
                    if (bucket == null) {
                        hashTable[index] = new LinkedList<>();

                        // Earlier might be null
                        bucket = hashTable[index];
                    }
                    bucket.add(entry);
                }
            }
        }
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % hashTable.length;
        Entry<K, V> entry = getEntry(index, key);
        return entry != null ? entry.value : null;
    }

    // Return null if LL is not created or key does not exist
    private Entry<K, V> getEntry(int index, K key) {
        LinkedList<Entry<K, V>> bucket = hashTable[index];
        if (bucket == null) {
            return null;
        }
        for (Entry<K, V> entry : hashTable[index]) {
            if (key.equals(entry.key)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Return {@code true} if specified key is mapped, or
     * {@code false} if this map contains no mapping for the key
     *
     * @param key the key to check if associated value pair exist
     * @return {@code true} if specified key is mapped, or
     * {@code false} if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("Key cannot be null");
        }
        return keyMapExist(key);
    }

    private boolean keyMapExist(K key) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % hashTable.length;
        return getEntry(index, key) != null;
    }


    /**
     * Return {@code true} if this HashTable is empty
     *
     * @return {@code true} if this HashTable is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return an iterator over hashtable pairs
     *
     * @return an iterator over hashtable pairs
     */
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return getEntryList().iterator();
    }

    /**
     * Return String representation of this HashTable
     *
     * @return String representation of this HashTable
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<K, V>> tabIterator = iterator();
        sb.append("[");
        while (tabIterator.hasNext()) {
            sb.append(tabIterator.next() + ",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    private LinkedList<Entry<K, V>> getEntryList() {
        LinkedList<Entry<K, V>> list = new LinkedList<>();
        for (LinkedList<Entry<K, V>> entries : hashTable) {
            if (entries != null) {
                for (Entry<K, V> entry : entries) {
                    list.add(entry);
                }
            }
        }
        return list;
    }


    protected static class Entry<K, V> {
        private final K key;
        private V value;

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" + key + "," + value + "}";
        }
    }

}
