package com.marcinseweryn.algorithms.datastructures.linkedlist;

public class CircularSinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void createCSLL(int value) {
        Node node = new Node();
        node.value = value;
        head = node;
        tail = node;
        size = 1;
    }

    public int size() {
        return this.size;
    }

    public int get(int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            return -1;
        } else {
            return temp.value;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int index, int value) {
        Node node = new Node();
        node.value = value;
        if (isEmpty()) {
            createCSLL(value);
            return;
        } else if (index == 0) {
            node.next = head;
            tail.next = node;
            head = node;
        } else if (index >= size) {
            node.next = head;
            tail.next = node;
            tail = node;
        } else {
            Node tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            node.next = tempNode.next;
            tempNode.next = node;
        }
        size++;
    }

    public boolean remove(int index) {
        if (isEmpty()) {
            assert size == 0 : "size is not 0";
            return false;
        } else if (size == 1) {
            head = null;
            tail = null;
        } else if (index == 0) {
            head = head.next;
            tail.next = head;
        } else if (index >= size) {
            Node tempNode = head;
            for (int i = 0; i < size - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = head;
            tail = tempNode;
        } else {
            Node tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next = tempNode.next.next;
        }
        size--;
        return true;
    }

    public boolean search(int value) {
        Node tempNode = head;
        for (int i = 0; i < size - 1; i++) {
            if (tempNode.value == value) {
                return true;
            }
            tempNode = tempNode.next;
        }
        return false;
    }

    public void deleteCSLL() {
        head = null;
        tail = null;
        size = 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node tempNode = head;
        for (int i = 0; i < size; i++) {
            sb.append(tempNode.value);
            if (i < size - 1) {
                sb.append("->");
            }
            tempNode = tempNode.next;
        }
        return sb.toString();
    }

    private static class Node {
        private Node next;
        private int value;
    }

}
