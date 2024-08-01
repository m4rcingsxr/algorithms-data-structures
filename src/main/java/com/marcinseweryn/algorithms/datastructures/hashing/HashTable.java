package com.marcinseweryn.algorithms.datastructures.hashing;


public interface HashTable<K,V> extends Iterable<Entry<K,V>> {

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    int size();

    boolean isEmpty();

}
