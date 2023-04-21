package com.marcinseweryn.algorithms.datastructures.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * Class represent implementation of the Trie.
 */
public class Trie {
    private final Node root;

    /**
     * Create empty Trie
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Insert specified String to the Trie
     *
     * @param val element to be inserted
     */
    public void insert(String val) {
        Node current = root;
        for (int i = 0; i < val.length(); i++) {
            char character = val.charAt(i);

            Node link = current.children.get(character);
            if (link == null) {
                link = new Node();
                current.children.put(character, link);
            }
            current = link;
        }

        current.endOfString = true;
    }

    /**
     * Return {@code true} if element exist in the Trie
     *
     * @param word element to search in the Trie
     * @return {@code true} if element exist in the Trie
     */
    public boolean search(String word) {
        Node current = root;
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            Node next = current.children.get(character);
            if (next == null) return false;
            current = next;
        }
        return current.endOfString;
    }

    private boolean delete(Node parent, String val, int index) {
        char character = val.charAt(index);
        Node current = parent.children.get(character);
        boolean deleteNode;
        if (current.children.size() > 1) {
            delete(current, val, index + 1);
            return false;
        }
        if (index == val.length() - 1) {
            if (current.children.size() >= 1) {
                current.endOfString = false;
                return false;
            } else {
                parent.children.remove(character);
                return true;
            }
        }

        if (current.endOfString) {
            delete(current, val, index + 1);
            return false;
        }
        deleteNode = delete(current, val, index + 1);
        if (deleteNode) {
            parent.children.remove(character);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Return {@code true} if element was successfully removed
     * from the Trie
     *
     * @param val element to be removed
     * @return {@code true} if element was successfully removed
     */
    public boolean delete(String val) {
        if (search(val)) {
            delete(root, val, 0);
            return true;
        } else {
            return false;
        }
    }

    private static class Node {
        private boolean endOfString = false;
        private final Map<Character, Node> children = new HashMap<>();

    }
}
