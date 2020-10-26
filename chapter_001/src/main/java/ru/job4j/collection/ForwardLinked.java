package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> last;
    private int length = 0;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        length--;
        head = head.next;
    }

    public T deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        length--;
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        Node<T> prevLast = head;
        while (prevLast != tail) {
            if (prevLast.next == tail) {
                break;
            }
            prevLast = prevLast.next;
        }
        prevLast.next = null;
        if (prevLast == tail) {
            head = null;
        }
        return tail.value;
    }

    public void revert() {
        Node<T> first = head;
        Node<T> second = first.next;
        Node<T> third = second.next;

        Node<T> next = third;
        Node<T> prev = second;

        first.next = null;
        second.next = first;

        while (next != null) {
            Node<T> temp = next.next;
            next.next = prev;
            prev = next;
            next = temp;
        }
        head = prev;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
