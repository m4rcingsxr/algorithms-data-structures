package com.marcinseweryn.algorithms.datastructures.hashing;

public class Entry<K, V> {
        final K key;
        V value;

        /**
         * Constructs an entry with a specified key and value.
         *
         * @param key   the key associated with this entry
         * @param value the value associated with this entry
         */
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }