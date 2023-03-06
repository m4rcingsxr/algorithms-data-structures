package com.marcinseweryn.algorithms.datastructures.linkedlist;

public class CircularDoublyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public CircularDoublyLinkedList() {
        size = 0;
    }

    public void createCDLL(int value) {
        Node node = new Node();
        node.value = value;
        head = node;
        tail = node;
        size++;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void add(int index, int value) {
        if (isEmpty()) {
            createCDLL(value);
            return;
        }
        Node node = new Node();
        node.value = value;
        if (index == 0) {
            head.previous = node;
            node.next = head;
            head = node;
            node.previous = tail;
            tail.next = head;
        } else if (index >= size) {
            head.previous = node;
            node.previous = tail;
            node.next = head;
            tail.next = node;
            tail = node;
        } else {
            Node tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next.previous = node;
            node.next = tempNode.next;
            node.previous = tempNode;
            tempNode.next = node;
        }
        size++;
    }

    public boolean remove(int index) {
        if (isEmpty()) {
            return false;
        } else if (size == 1) {
            return deleteCDLL();
        } else if (index == 0) {
            head.next.previous = tail;
            tail.next = head.next;
            head = head.next;
        } else if (index >= size - 1) {
            tail = tail.previous;
            tail.next = head;
            head.previous = tail;
        } else {
            Node tempNode = head;
            for (int i = 0; i < index - 1; i++) {
                tempNode = tempNode.next;
            }
            tempNode.next.next.previous = tempNode;
            tempNode.next = tempNode.next.next;
        }
        size--;
        return true;
    }

    public boolean deleteCDLL() {
        head = null;
        tail = null;
        size = 0;
        return true;
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
        private Node previous;
        private Node next;
        private int value;
    }
}
