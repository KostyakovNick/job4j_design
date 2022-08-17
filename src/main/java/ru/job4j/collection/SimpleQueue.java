package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out1 = new SimpleStack<>();
    private int size;

    public T poll() {
        if (out1.isEmpty()) {
            if (in.isEmpty()) {
                throw new NoSuchElementException();
            }
            for (int i = 0; i < size; i++) {
                out1.push(in.pop());
            }
        }
        size--;
        return out1.pop();
    }

    public void push(T value) {
        in.push(value);
        size++;
    }
}