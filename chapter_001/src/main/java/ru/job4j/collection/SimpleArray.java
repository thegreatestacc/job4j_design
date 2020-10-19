package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container = new Object[5];
    private int point = 0;
    private int modCount = 0;

    public T get(int index) {
        Objects.checkIndex(index, point);
        return (T) container[index];
    }

    public void add(T model) {
        modCount++;
        checkLength();
        container[point++] = model;
    }

    private void checkLength() {
        if (point == container.length) {
            this.container = Arrays.copyOf(container, container.length * 2);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int expectedModCount = modCount;
            int itCount = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return itCount < point;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[itCount++];
            }
        };
    }
}
