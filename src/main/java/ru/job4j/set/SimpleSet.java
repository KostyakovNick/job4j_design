package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean rsl = false;
        if (set.size() == 0 || !contains(value)) {
            set.add(value);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean contains(T value) {
        return value == null && iterator().next() == null
                || iterator().next().equals(value);
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}