package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;
    private int modCount = 0;

    @Override
    public void add(E value) {
        Node<E> oldNode = last;
        Node<E> newNode = new Node<>(oldNode, value, null);
        last = newNode;
        if (oldNode == null) {
            first = newNode;
        } else {
            oldNode.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> getNode = first;
        for (int i = 0; i < index; i++) {
            getNode = getNode.next;
        }
        return getNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private int point = 0;
            private Node<E> returnedNode = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                point++;
                E value = returnedNode.item;
                returnedNode = returnedNode.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
