package ru.job4j.it;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.next();
                i.add(value);
                break;
            } else {
                i.next();
            }
        }
    }

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        Objects.requireNonNull(filter);
        ListIterator i = list.listIterator();
        List<T> arrayList = new ArrayList<>();
        while (i.hasNext()) {
            T temp = (T) i.next();
            if (filter.test(temp)) {
                arrayList.add(temp);
                i.remove();
            }
        }
        return arrayList;
    }

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        Objects.requireNonNull(filter);
        ListIterator i = list.listIterator();
        List<T> arrayList = new ArrayList<>();
        while (i.hasNext()) {
            T temp = (T) i.next();
            if (filter.test(temp)) {
                arrayList.add(temp);
                i.set(value);
            }
        }
        return arrayList;
    }

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        ListIterator i = list.listIterator();
        Iterator elementsIterator = elements.iterator();
        List<T> arrayList = new ArrayList<>();
        while (i.hasNext()) {
            T temp = (T) i.next();
            while (elementsIterator.hasNext()) {
                if (temp.equals(elementsIterator.next())) {
                    arrayList.add(temp);
                    i.remove();
                }
            }
        }
        return arrayList;
    }
}
