package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private T[] array;
    private int size;
    private int point;

    public SimpleArray(int size) {
        this.size = size;
        array = (T[]) new Object[size];
    }

    public void add(T model) {
        if (point < size) {
            array[point++] = model;
            return;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    public void set(int index, T model) {
        array[Objects.checkIndex(index, point)] = model;
    }

    public void remove(int index) {
        int remIndex = Objects.checkIndex(index, point);
        System.arraycopy(array, remIndex + 1,
                array, remIndex, point - remIndex - 1);
        point--;
    }

    public T get(int index) {
        return array[Objects.checkIndex(index, point)];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int iPoint = 0;

            @Override
            public boolean hasNext() {
                return iPoint < point;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[iPoint++];
            }
        };
    }
}