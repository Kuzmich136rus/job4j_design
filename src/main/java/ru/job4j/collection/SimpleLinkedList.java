package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    private int modCount;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> newNode = new Node<>(value, null);
        if (first == null) {
            first = newNode;
            last = newNode;
            size++;
            modCount++;
            return;
        }
        Node<E> tail = first;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> current = first;
        int count = 0;
        while (count != index) {
            current = current.next;
            count++;
        }
        return current.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> cursor = first;
            int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> itemReturn = cursor;
                cursor = cursor.next;
                return itemReturn.item;
            }
        };
    }
}
