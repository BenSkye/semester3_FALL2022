package com.fptuni.csd201.lib.ObjectStackQueue;

import java.util.NoSuchElementException;

public class ObjectQueue {

    private Object[] array;
    private int first, last;
    private int size;

    public ObjectQueue() {
        this(10);
    }

    public ObjectQueue(int size) {
        this.size = size;
        array = new Object[size];
        first = -1;
        last = -1;
    }

    public boolean grow() {
        int i, j;
        int size1 = size + size / 2;
        Object[] array1 = new Object[size1];
        if (array1 == null) {
            return (false);
        }
        if (last >= first) {
            for (i = first; i <= last; i++) {
                array1[i - first] = array[i];
            }
        } else {
            for (i = first; i < size; i++) {
                array1[i - first] = array[i];
            }
            i = size - first;
            for (j = 0; j <= last; j++) {
                array1[i + j] = array[j];
            }
        }
        array = array1;
        first = 0;
        last = size - 1;
        size = size1;
        return (true);
    }

    public boolean isEmpty() {
        return (first == -1);
    }

    public boolean isFull() {
        return ((first == 0 && last == size - 1) || first == last + 1);
    }

    public void clear() {
        first = -1;
        last = -1;
    }

    public void enqueue(Object obj) throws NoSuchElementException {
        if (isFull() && !grow()) {
            throw new NoSuchElementException();
        }
        if (last == size - 1 || last == -1) {
            array[0] = obj;
            last = 0;
            if (first == -1) {
                first = 0;
            }
        } else {
            last++;
            array[last] = obj;
        }
    }

    public Object dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Object x = array[first];
        if (first == last) {
            first = -1;
            last = -1;
        } else if (first == size - 1) {
            first = 0;
        } else {
            first++;
        }
        return x;
    }

    public Object first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[first];

    }

    public void traverse() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (first <= last) {
            for (int i = first; i <= last; i++) {
                System.out.println(array[i].toString());
            }
            return;
        }
        if (first > last) {
            for (int i = first; i <= size - 1; i++) {
                System.out.println(array[i].toString());
            }
            for (int i = 0; i <= last; i++) {
                System.out.println(array[i].toString());
            }
            return;
        }
    }
}
