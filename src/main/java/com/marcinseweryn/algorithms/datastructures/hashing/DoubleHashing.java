package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.ArrayList;
import java.util.List;

/**
 * DoubleHashing is a class that represents a hash table using double hashing
 * collision resolution method. It provides methods for inserting values into
 * the hash table, rehashing the keys when the load factor reaches a certain
 * threshold, and generating hash codes using the modASCIIHashFunction and
 * secondHashFunction. It also provides a toString() method for printing out
 * the contents of the hash table.
 */
public class DoubleHashing {

    // The hash table represented as an array of Strings.
    String[] hashTable;

    // The number of cells occupied in the hash table.
    int numOfOccupiedCells;

    /**
     * Constructs a new DoubleHashing object with the specified size.
     *
     * @param size the size of the hash table to be created.
     */
    public DoubleHashing(int size) {
        hashTable = new String[size];
        numOfOccupiedCells = 0;
    }

    /**
     * Calculates the hash value for a given word using the mod ASCII hash
     * function.
     *
     * @param word        the word to calculate the hash value for.
     * @param arrayLength the length of the hash table array.
     * @return the hash value for the given word.
     */
    public int modASCIIHashFunction(String word, int arrayLength) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i);
        }
        return sum % arrayLength;
    }

    /**
     * Rehashes the keys in the hash table with a new string to be inserted.
     *
     * @param newStringToBeInserted the new string to be inserted into the
     *                              hash table.
     */
    public void rehashKeys(String newStringToBeInserted) {
        numOfOccupiedCells = 0;
        List<String> temp = new ArrayList<>();
        for (String s : hashTable) {
            if (s != null) {
                temp.add(s);
            }
        }
        temp.add(newStringToBeInserted);
        hashTable = new String[hashTable.length * 2];
        for (String s : temp) {
            insert(s);
        }

    }

    /**
     * Calculates the load factor of the hash table.
     *
     * @return the load factor of the hash table.
     */
    private double getLoadFactor() {
        return numOfOccupiedCells * 1.0 / hashTable.length;
    }

    /**
     * Inserts a new string into the hash table.
     *
     * @param value the new string to be inserted into the hash table.
     */
    public void insert(String value) {
        double loadFactor = getLoadFactor();
        if (loadFactor >= 0.75) {
            rehashKeys(value);
        } else {
            int x = modASCIIHashFunction(value, hashTable.length);
            int y = secondHashFunction(value, hashTable.length);
            for (int i = 0; i < hashTable.length; i++) {
                int newIndex = (x + i * y) % hashTable.length;
                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = value;
                    break;
                }
            }
        }
        numOfOccupiedCells++;
    }

    /**
     * Calculates the hash value for a given string using the second hash
     * function.
     *
     * @param s           the string to calculate the hash value for.
     * @param arrayLength the length of the hash table array.
     * @return the hash value for the given string.
     */
    private int secondHashFunction(String s, int arrayLength) {
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i);
        }
        while (sum > hashTable.length) {
            sum = addAllDigits(sum);
        }
        return sum % arrayLength;
    }


    private int addAllDigits(int sum) {
        int value = 0;
        while (sum > 0) {
            value = sum % 10;
            sum = sum / 10;
        }
        return value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String s : hashTable) {
            if (s != null) {
                sb.append(s).append(", ").append(index++).append("\n");
            }
        }
        return sb.toString();
    }
}
