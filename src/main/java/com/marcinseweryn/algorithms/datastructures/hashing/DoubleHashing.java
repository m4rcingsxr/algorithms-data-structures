package com.marcinseweryn.algorithms.datastructures.hashing;

import java.util.ArrayList;
import java.util.List;

public class DoubleHashing {
    String[] hashTable;
    int numOfOccupiedCells;

    public DoubleHashing(int size) {
        hashTable = new String[size];
        numOfOccupiedCells = 0;
    }

    public int modASCIIHashFunction(String word, int arrayLength) {
        int sum = 0;
        for (int i = 0; i < word.length(); i++) {
            sum += word.charAt(i);
        }
        return sum % arrayLength;
    }

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

    private double getLoadFactor() {
        return numOfOccupiedCells * 1.0 / hashTable.length;
    }

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
