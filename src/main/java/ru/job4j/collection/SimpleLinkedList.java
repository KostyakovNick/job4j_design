package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private Node<E> fstNode;
    private Node<E> lstNode;
    private int size;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E value, Node<E> next) {
            this.item = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void add(E value) {
        size++;
        modCount++;
        if (fstNode == null) {
            fstNode = new Node<E>(null, value, null);
            lstNode = fstNode;
        } else {
            Node<E> prevNode = lstNode;
            lstNode = new Node<E>(prevNode, value, null);
            prevNode.next = lstNode;
            if (prevNode.prev == null) {
                fstNode.next = lstNode;
            }
        }
    }

    @Override
    public E get(int index) {
        index = Objects.checkIndex(index, size);
        Node<E> target = fstNode;
        for (int i = 0; i < index; i++) {
            target = target.next;
        }
        return target.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = fstNode;
            int index;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.item;
                node = node.next;
                index++;
                return rsl;
            }
        };
    }
}