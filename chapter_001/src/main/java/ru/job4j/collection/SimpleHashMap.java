package ru.job4j.collection;

import java.util.*;

public class SimpleHashMap<K, V> implements Iterable<K> {

    /**
     * Массив для хранения пары ключ-значение
     */
    private Node<Object, Object>[] table;

    private int modCount = 0;

    /**
     * Ключи
     */
    private Set<K> keys = new HashSet<>();

    public SimpleHashMap(Node<Object, Object>[] table) {
        this.table = table;
    }

    public boolean insert(K key, V value) {
        boolean result = false;
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new Node<>(key, value);
            result = true;
            modCount++;
            keys.add(key);
        }
        return result;
    }

    public V get(K key) {
        return (V) table[getIndex(key)].getValue();
    }

    public boolean delete(K key) {
        boolean result = false;
        int index = getIndex(key);
        if (table[index] != null) {
            table[index] = null;
            result = true;
            modCount++;
            keys.remove(key);
        }
        return result;
    }

    /**
     * Хэширование ключа
     * @param key
     * @return
     */
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Метод нахождения индекса элемента в table
     * @param key
     * @return
     */
    private int getIndex(K key) {
        //Размер table
        int tableSize = 128;
        return (tableSize - 1) & hash(key);
    }

    public int getModCount() {
        return modCount;
    }

    @Override
    public Iterator<K> iterator() {
        return new SimpleHashMapIterator<>(this);
    }

    /**
     * Структура для хранения пары ключ-значение
     * @param <K>
     * @param <V>
     */
    static class Node<K, V> {
        private K key;
        private V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key + ", value=" + value + '}';
        }
    }


    public Set<K> getKeys() {
        return this.keys;
    }

    static class SimpleHashMapIterator<K, V> implements Iterator {

        private SimpleHashMap<K, V> simpleHashMap;
        private int modCount;
        private Iterator<K> keys;

        public SimpleHashMapIterator(SimpleHashMap<K, V> simpleHashMap) {
            this.simpleHashMap = simpleHashMap;
            this.modCount = simpleHashMap.getModCount();
            this.keys = simpleHashMap.getKeys().iterator();
        }

        @Override
        public boolean hasNext() {
            return keys.hasNext();
        }

        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else
            if (this.modCount != simpleHashMap.getModCount()) {
                throw new ConcurrentModificationException();
            }
            return this.simpleHashMap.get(keys.next());
        }
    }
}
