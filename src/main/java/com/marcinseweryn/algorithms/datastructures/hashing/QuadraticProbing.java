package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.ArrayList;

/**
 * A class that implements a hash table using quadratic probing as a collision
 * resolution technique. The hash function used is based on the ASCII values
 * of the characters in the keys. The load factor of the hash table is used
 * to determine when to rehash the keys. This implementation supports insertion,
 * searching, and resizing the hash table.
 */
public class QuadraticProbing {
    String[] hashTable;
    int usedCellNumber;

    public QuadraticProbing(int size) {
        hashTable = new String[size];
        usedCellNumber = 0;
    }

    public int modASCIIHashFunction(String key, int tableLength) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % tableLength;
    }

    public double getLoadFactor() {
        return usedCellNumber * 1.0 / hashTable.length;
    }

    public void rehashKeys(String newStringToBeInserted) {
        usedCellNumber = 0;
        ArrayList<String> data = new ArrayList<>();
        for (String s : hashTable) {
            if (s != null)
                data.add(s);
        }
        data.add(newStringToBeInserted);
        hashTable = new String[hashTable.length * 2];
        for (String s : data) {
            insert(s);
        }
    }

    public void insert(String word) {
        double loadFactor = getLoadFactor();
        if (loadFactor >= 0.75) {
            rehashKeys(word);
        } else {
            int index = modASCIIHashFunction(word, hashTable.length);
            int counter = 0;
            for (int i = index; i < index + hashTable.length; i++) {
                int newIndex = (index + (counter * counter)) % hashTable.length;
                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = word;
                    break;
                }
                counter++;
            }
        }
        usedCellNumber++;
    }

    public boolean isEmpty() {
        return usedCellNumber == 0;
    }

    public boolean search(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        return hashTable[index].equals(word);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                sb.append("[").append(i).append(",").append(
                        hashTable[i]).append("]");
            }
        }
        return sb.toString();
    }
}

