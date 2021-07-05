package ru.job4j.map;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == capacity * LOAD_FACTOR) {
            expand();
        }
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        return true;
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 2);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        table = Arrays.copyOf(table, capacity);
    }

    @Override
    public V get(K key) {
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            return false;
        }
        table[index] = null;
        count--;
        modCount++;
        return true;
    }

    public int size() {
        return count;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {

            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = point; i < table.length; i++) {
                    if (table[i] != null) {
                        return true;
                    }
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[point] == null) {
                    point++;
                }
                return table[point++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
