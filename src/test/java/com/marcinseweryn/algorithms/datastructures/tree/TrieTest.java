package com.marcinseweryn.algorithms.datastructures.tree;

import com.marcinseweryn.algorithms.datastructures.hashing.LinearProbing;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void givenNewTrie_whenCreated_thenShouldBeEmpty() {
        Trie trie = new Trie();
        assertFalse(trie.search("test"));
    }

    @Test
    void givenEmptyTrie_whenInsertCalled_thenWordShouldBeSearchable() {
        Trie trie = new Trie();
        trie.insert("test");
        assertTrue(trie.search("test"));
    }

    @Test
    void givenTrieWithWord_whenSearchCalledForNonExistentWord_thenShouldReturnFalse() {
        Trie trie = new Trie();
        trie.insert("hello");
        assertFalse(trie.search("world"));
    }

    @Test
    void givenTrieWithWord_whenSearchCalledForExistingWord_thenShouldReturnTrue() {
        Trie trie = new Trie();
        trie.insert("hello");
        assertTrue(trie.search("hello"));
    }

    @Test
    void givenTrieWithWords_whenDeleteCalled_thenShouldRemoveWord() {
        Trie trie = new Trie();
        trie.insert("hello");
        trie.insert("hell");
        trie.insert("heaven");
        trie.delete("hell");
        assertFalse(trie.search("hell"));
        assertTrue(trie.search("hello"));
        assertTrue(trie.search("heaven"));
    }

    @Test
    void givenTrieWithWords_whenDeleteCalledForNonExistentWord_thenShouldReturnFalse() {
        Trie trie = new Trie();
        trie.insert("hello");
        assertFalse(trie.delete("world"));
    }

    @Test
    void givenTrieWithWord_whenDeleteCalled_thenWordShouldNoLongerBeSearchable() {
        Trie trie = new Trie();
        trie.insert("test");
        trie.delete("test");
        assertFalse(trie.search("test"));
    }

    @Test
    void givenTrieWithPrefixWords_whenDeleteCalled_thenShouldOnlyDeleteExactWord() {
        Trie trie = new Trie();
        trie.insert("there");
        trie.insert("their");
        trie.insert("the");
        trie.delete("their");
        assertTrue(trie.search("there"));
        assertFalse(trie.search("their"));
        assertTrue(trie.search("the"));
    }

    @Test
    void givenTrieWithMultipleWords_whenDeleteWordPartiallyContainedInAnother_thenShouldNotDeleteOther() {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.delete("apple");
        assertTrue(trie.search("app"));
        assertFalse(trie.search("apple"));
    }

    @Test
    void givenTrie_whenInsertAndDeleteCalledMultipleTimes_thenTrieShouldBehaveCorrectly() {
        Trie trie = new Trie();
        trie.insert("cat");
        trie.insert("can");
        trie.insert("cap");
        trie.delete("can");
        assertTrue(trie.search("cat"));
        assertFalse(trie.search("can"));
        assertTrue(trie.search("cap"));
        trie.delete("cat");
        assertFalse(trie.search("cat"));
        assertTrue(trie.search("cap"));
    }

    @Test
    void givenTrie_whenInsertSameWordMultipleTimes_thenShouldOnlyDeleteOnce() {
        Trie trie = new Trie();
        trie.insert("duplicate");
        trie.insert("duplicate");
        trie.delete("duplicate");
        assertFalse(trie.search("duplicate"));
    }

    @Test
    void givenTrie_whenDeleteNonexistentWord_thenShouldNotAffectExistingWords() {
        Trie trie = new Trie();
        trie.insert("existent");
        trie.delete("nonexistent");
        assertTrue(trie.search("existent"));
    }

    @Test
    void givenTrie_whenDeleteWordWithSamePrefixAsOtherWord_thenShouldNotDeletePrefix() {
        Trie trie = new Trie();
        trie.insert("prefix");
        trie.insert("pref");
        trie.delete("prefix");
        assertTrue(trie.search("pref"));
        assertFalse(trie.search("prefix"));
    }

    @Test
    void givenTrie_whenInsertCalled_thenTrieShouldContainInsertedWord() {
        Trie trie = new Trie();
        trie.insert("test");
        assertTrue(trie.search("test"));
    }

}
