package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= (capacity * LOAD_FACTOR)) {
            expand();
        }
        int i = indexFor(hash(Objects.hashCode(key)));
        boolean rsl = table[i] == null;
        if (rsl) {
            table[i] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode ^ (hashCode >>> 16));
    }

    private int indexFor(int hash) {
        return (table.length - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> t : oldTable) {
            if (t != null) {
                table[indexFor(hash(Objects.hashCode(t.key)))] = t;
            }
        }
    }

    @Override
    public V get(K key) {
        int i = indexFor(hash(Objects.hashCode(key)));
        return (table[i] != null
                && Objects.hashCode(table[i].key) == Objects.hashCode(key)
                && Objects.equals(table[i].key, key)) ? table[i].value : null;
    }

    @Override
    public boolean remove(K key) {
        int i = indexFor(hash(Objects.hashCode(key)));
        boolean rsl = false;
        if (table[i] != null && Objects.hashCode(table[i].key) == Objects.hashCode(key)
                && Objects.equals(table[i].key, key)) {
            table[i] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            int i = 0;
            int expectedModCount = modCount;
            int size = count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return i < size;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[i] == null) {
                    i++;
                    size++;
                }
                return table[i++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}