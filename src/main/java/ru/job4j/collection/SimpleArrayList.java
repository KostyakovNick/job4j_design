package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[size] = value;
        size++;
    }

    @Override
    public T set(int index, T newValue) {
        T rsl = container[Objects.checkIndex(index, size)];
        container[Objects.checkIndex(index, size)] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        T rsl = container[Objects.checkIndex(index, size)];
        System.arraycopy(
                container,
                index + 1,
                container, index,
                container.length - index - 1
        );
        size--;
        container[size] = null;
        modCount++;
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            int i = 0;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[i++];
            }
        };
    }
}