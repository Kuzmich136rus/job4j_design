package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
       if (count == LOAD_FACTOR * capacity) {
           capacity *= 2;
           expand();
       }
       boolean rsl = false;
       int index = indexFor(hash(key));
       if (table[index] == null) {
           table[index] = new MapEntry<>(key, value);
           count++;
           modCount++;
           rsl = true;
       }
       return rsl;
    }

    private int hash(K key) {
        return (key == null) ? 0 : key.hashCode() ^ (key.hashCode() >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] beforeExpand = table;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> entry : beforeExpand) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
        beforeExpand = null;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = indexFor(hash(key));
        K searchKey = table[index] != null ? table[index].key : null;
            if (Objects.hashCode(searchKey) == Objects.hashCode(key) && Objects.equals(key, searchKey)) {
                rsl = table[index].value;
            }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = indexFor(hash(key));
        K searchKey = table[index] != null ? table[index].key : null;
            if (Objects.hashCode(searchKey) == Objects.hashCode(key) && Objects.equals(key, searchKey)) {
                table[index] = null;
                rsl = true;
                count--;
                modCount++;
            }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int counter = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (counter < capacity && table[counter] == null) {
                    counter++;
                }
                return counter < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[counter++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}