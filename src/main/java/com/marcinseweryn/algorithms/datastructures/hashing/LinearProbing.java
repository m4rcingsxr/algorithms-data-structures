package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.Arrays;

public class LinearProbing {
    String[] hashTable;
    int usedCellNumber;

    LinearProbing(int size) {
        hashTable = new String[size];
        usedCellNumber = 0;
    }

    private double getLoadFactor() {
        return usedCellNumber * 1.0 / hashTable.length;
    }

    private int modASCIIHashFunction(String word, int length) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i);
        }
        return sum % length;
    }

    private void rehashKeys(String word) {
        usedCellNumber = 0;
        String[] temp = Arrays.copyOf(hashTable, hashTable.length * 2);
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                int index = modASCIIHashFunction(hashTable[i], temp.length);
                temp[index] = hashTable[i];
            }
        }
        hashTable = temp;
        insert(word);
    }

    public void insert(String word) {
        double loadFactor = getLoadFactor();
        if (loadFactor >= 0.75) {
            rehashKeys(word);
        } else {
            int index = modASCIIHashFunction(word, hashTable.length);
            for (int i = index; i < index + hashTable.length; i++) {
                int newIndex = i % hashTable.length;
                if (hashTable[newIndex] == null) {
                    hashTable[newIndex] = word;
                    break;
                }
            }
        }
        usedCellNumber++;
    }

    public boolean search(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        for (int i = index; i < index + hashTable.length; i++) {

            // because size is index + hashTable.length, if our index is 15,
            // size is 15 and we want to insert new value then we need to get into index 1 so must use mode
            int newIndex = i % hashTable.length;
            if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != null) {
                sb.append("[").append(i).append(",").append(hashTable[i]).append("]");
            }
        }
        return sb.toString();
    }

    /**
     * Return {@code true} if value was successfully removed
     * or {@code false} if element does not exist in the table
     *
     * @param word element to remove
     * @return {@code true} if value was successfully removed
     */
    public boolean delete(String word) {
        int index = modASCIIHashFunction(word, hashTable.length);
        for (int i = index; i < index + hashTable.length; i++) {
            int newIndex = i % hashTable.length;
            if (hashTable[newIndex] != null && hashTable[newIndex].equals(word)) {
                hashTable[newIndex] = null;
                return true;
            }
        }
        return false;
    }
}




