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
            container = grow(container);
        }
        container[size] = value;
        size++;
        modCount++;
    }

    private T[] grow(T[] container) {
        return container.length != 0
                ? Arrays.copyOf(container, container.length * 2)
                : Arrays.copyOf(container, 10);
    }

    @Override
    public T set(int index, T newValue) {
        index = Objects.checkIndex(index, size);
        T rsl = container[index];
        container[Objects.checkIndex(index, size)] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        index = Objects.checkIndex(index, size);
        T rsl = container[index];
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
        index = Objects.checkIndex(index, size);
        return container[index];
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
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return i < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[i++];
            }
        };
    }
}