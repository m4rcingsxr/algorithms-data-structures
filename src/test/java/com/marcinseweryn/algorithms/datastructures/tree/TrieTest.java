package com.marcinseweryn.algorithms.datastructures.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieTest {
    Trie trie;

    @BeforeEach
    void beforeEach() {
        trie = new Trie();
    }

    @Test
    void searchTest() {
        trie.insert("");
        assert (trie.search(""));
        trie.insert("bbbbbbbbb");
        assert (trie.search("bbbbbbbbb"));
        trie.insert("bbbbbbbbb");
        assert (trie.search("bbbbbbbbb"));

        trie.insert("aaaaaaaap");
        assertFalse(trie.search("aaaaaaaa"));
        assertTrue(trie.search("aaaaaaaap"));
        trie.insert("aaaaaaaa");
        assertTrue(trie.search("aaaaaaaa"));

        trie.insert("AD");
        trie.insert("AE");
        trie.insert("AH");
        trie.insert("AH");
        trie.insert("AC2");
        trie.insert("B");
        trie.insert("B");
        assertTrue(trie.search("AD"));
        assertTrue(trie.search("AE"));
        assertTrue(trie.search("AH"));
        assertTrue(trie.search("AC2"));
        assertTrue(trie.search("B"));

        assertFalse(trie.search("Ad"));
        assertFalse(trie.search("aD"));
        assertFalse(trie.search("ADd"));
        assertFalse(trie.search("b"));
        assertFalse(trie.search("Bb"));

    }

    @Test
    void testInsert() {
        trie.insert("ADca");
        trie.insert("AEfd");
        trie.insert("AHfdd");
        trie.insert("AHfddd");
        trie.insert("AC2a");
        trie.insert("B");
        trie.insert("B");
        trie.insert("");

        assertTrue(trie.search("ADca"));
        assertTrue(trie.search("AEfd"));
        assertTrue(trie.search("AHfdd"));
        assertTrue(trie.search("AHfddd"));
        assertTrue(trie.search("AC2a"));
        assertTrue(trie.search("B"));
        assertTrue(trie.search(""));
    }

    @Test
    void testDelete() {
        trie.insert("ADca");
        trie.insert("AEfd");
        trie.insert("AHfdd");
        trie.insert("AHfddd");
        trie.insert("AC2a");
        trie.insert("B");
        trie.insert("B");
        trie.insert("");

        trie.delete("ADca");
        trie.delete("AEfd");
        trie.delete("AHfdd");
        trie.delete("AHfddd");
        trie.delete("AC2a");
        trie.delete("B");
        trie.delete("B");

        assertFalse(trie.search("ADca"));
        assertFalse(trie.search("AEfd"));
        assertFalse(trie.search("AHfdd"));
        assertFalse(trie.search("AHfddd"));
        assertFalse(trie.search("AC2a"));
        assertFalse(trie.search("B"));
    }

}