package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedListContainer<T> implements Iterable<T> {

    private Node first;
    private Node last;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, modCount);
        Node node = first;
        for (int i = 0; i < index; i++) {
            node = node.lastNode;
        }
        return node.t;
    }

    public void add(T element) {
        modCount++;
        if (first == null) {
            first = new Node(null, element, last);
        } else if (last == null) {
            last = new Node(first, element, null);
            first.lastNode = last;
        } else {
            Node node = new Node(last, element, null);
            last.lastNode = node;
            last = node;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node node = first;
            final int expectedMod = modCount;

            @Override
            public boolean hasNext() {
                if (expectedMod != modCount) {
                    throw new ConcurrentModificationException();
                }
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T element = node.t;
                node = node.lastNode;
                return element;
            }
        };
    }

    class Node {
        T t;
        Node firstNode;
        Node lastNode;

        public Node(Node firstNode, T t, Node lastNode) {
            this.t = t;
            this.firstNode = firstNode;
            this.lastNode = lastNode;
        }
    }
}
