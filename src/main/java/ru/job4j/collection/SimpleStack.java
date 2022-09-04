package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<T>();

    private int size = 0;

    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
        size++;
    }

    public int getSize() {
        return size;
    }
}
