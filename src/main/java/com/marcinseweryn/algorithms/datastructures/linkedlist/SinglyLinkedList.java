package com.marcinseweryn.algorithms.datastructures.linkedlist;

public class SinglyLinkedList {
    private Node head;
    private Node tail;
    private int size;

    public void createSLL(int value) {
        Node newNode = new Node();
        newNode.value = value;
        head = newNode;
        tail = newNode;
        size = 0;
    }

    public void add(int index, int value) {
        Node newNode = new Node();
        newNode.value = value;
        if (head == null) {
            createSLL(value);
        } else if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else if (index >= size) {
            tail.next = newNode;
            tail = newNode;
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next = newNode;
        }
        size++;
    }

    public boolean search(int value) {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.value == value) return true;
            temp = temp.next;  // i == size - 1: temp = tail.next(null)
        }
        return false;
    }

    public boolean remove(int index) {
        if (isEmpty()) {
            return false;
        } else if (size == 0) {
            removeLL();
        } else if (index == 0) {
            head = head.next;
        } else if (index >= size) {
            Node temp = head;
            for (int i = 0; i < size - 1; i++) {
                temp = temp.next;
            }
            temp.next = null;
            tail = temp;
        } else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        size--;
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

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return this.size;
    }

    public void removeLL() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.value);
            if (cur != tail) {
                sb.append("->");
            }
        }
        return sb.toString();
    }

    private static class Node {
        private Node next;
        private int value;
    }
}

