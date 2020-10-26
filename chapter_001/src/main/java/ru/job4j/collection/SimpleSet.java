package ru.job4j.collection;

import java.util.Iterator;

public class SimpleSet<T> implements Iterable<T> {

    private SimpleArray<T> array;

    public void add(T model) {
        for (T element : array) {
            if (element.equals(model)) {
                return;
            }
        }
        array.add(model);
    }

    @Override
    public Iterator<T> iterator() {
        return array.iterator();
    }
}
