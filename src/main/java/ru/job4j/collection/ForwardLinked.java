package ru.job4j.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public T addFirst(T value) {
        head = new Node<>(value, head);
        return head.value;
    }

    public T deleteFirst() {
        Node<T> f = head;
        if (f == null) {
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    private T unlinkFirst(Node<T> f) {
        T element = f.value;
        Node<T> next = f.next;
        f.value = null;
        f.next = null;
        head = next;
        return element;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}