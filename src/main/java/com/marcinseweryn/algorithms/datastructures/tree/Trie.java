package com.marcinseweryn.algorithms.datastructures.tree;

import java.util.HashMap;

/**
 * A Trie (prefix tree) data structure implementation.
 * Tries are used for storing a dynamic set of strings where keys are usually strings.
 */
public class Trie {

    private final TrieNode root;

    /**
     * Initializes a new Trie with an empty root node.
     */
    public Trie() {
        this.root = new TrieNode();
    }

    /**
     * Inserts a word into the Trie.
     *
     * @param word the word to be inserted into the Trie
     */
    public void insert(String word) {
        TrieNode current = this.root;
        // Iterate through each character of the word
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            TrieNode node = current.children.get(character);

            // If the character is not present, add it to the Trie
            if (node == null) {
                node = new TrieNode();
                current.children.put(character, node);
            }
            current = node; // Move to the next node in the path
        }
        current.endOfString = true; // Mark the end of the word
    }

    /**
     * Searches for a word in the Trie.
     *
     * @param word the word to search for
     * @return true if the word exists in the Trie, false otherwise
     */
    public boolean search(String word) {
        TrieNode current = this.root;
        // Iterate through each character of the word
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);
            TrieNode next = current.children.get(character);
            if (next == null) return false; // Character not found, word does not exist
            current = next; // Move to the next node
        }
        return current.endOfString; // Check if the end of the word is reached
    }

    /**
     * Deletes a word from the Trie.
     *
     * @param word the word to be deleted
     * @return true if the word was deleted, false if the word was not found
     */
    public boolean delete(String word) {
        // Check if the word exists in the Trie
        if(this.search(word)) {
            // Start the recursive deletion process
            this.delete(this.root, word, 0);
            return true;
        }
        return false;
    }

    /**
     * Recursively deletes a word from the Trie.
     *
     * @param parent the current node in the Trie
     * @param word the word to be deleted
     * @param index the current index in the word being processed
     * @return true if the current node should be deleted, false otherwise
     */
    private boolean delete(TrieNode parent, String word, int index) {
        char character = word.charAt(index);
        TrieNode current = parent.children.get(character);

        // If the current node has more than one child, we cannot delete it yet
        if (current.children.size() > 1) {
            // Recur to delete the next character in the word
            this.delete(current, word, index + 1);
            return false; // Do not delete this node
        }

        // If the end of the word is reached
        if (index == word.length() - 1) {
            // If the current node is not a leaf, just unset the endOfString flag
            if(!current.children.isEmpty()) {
                current.endOfString = false;
                return false; // Do not delete this node
            } else {
                // If it is a leaf node, remove it from the parent
                parent.children.remove(character);
                return true; // Node can be deleted
            }
        }

        // If the current node marks the end of another word, do not delete it
        if(current.endOfString) {
            this.delete(current, word, index + 1); // check if further node can be removed
            return false; // Do not delete this node
        }

        // Recur to delete the next character
        boolean deleteNode = this.delete(current, word, index + 1);

        // If the child node was deleted, check if the current node can be deleted
        if(deleteNode) {
            parent.children.remove(character);
        }

        return deleteNode; // Return whether this node should be deleted
    }

    /**
     * Represents a node in the Trie.
     */
    static class TrieNode {

        private final HashMap<Character, TrieNode> children;
        private boolean endOfString = false;

        /**
         * Initializes a new TrieNode with an empty map of children.
         */
        TrieNode() {
            this.children = new HashMap<>();
        }

    }

}
