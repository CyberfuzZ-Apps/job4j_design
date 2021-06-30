package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] container;
    private int modCount = 0;
    private int size = 0;

    public SimpleArray() {
        container = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public SimpleArray(int capacity) {
        container = (T[]) new Object[capacity];
    }

    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    public void add(T model) {
        if (size == container.length) {
            grow();
        }
        container[size++] = model;
        modCount++;
    }

    private void grow() {
        int oldCapacity = container.length;
        if (oldCapacity > 0) {
            int newCapacity = oldCapacity + oldCapacity / 2;
            container = Arrays.copyOf(container, newCapacity);
        } else {
            container = (T[]) new Object[DEFAULT_CAPACITY];
        }
    }

    public int length() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
