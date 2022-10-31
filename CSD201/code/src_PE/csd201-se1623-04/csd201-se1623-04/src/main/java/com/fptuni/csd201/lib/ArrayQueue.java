/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import com.fptuni.csd201.ds.Queue;
import java.util.NoSuchElementException;

/**
 *
 * @author DUNGHUYNH
 */
public class ArrayQueue implements Queue {

    private int size;
    private int[] array;
    private int first;
    private int last;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int size) {
        this.size = size;
        this.first = -1;
        this.last = -1;
        this.array = new int[size];
    }

    public boolean grow() {
        int i, j;
        int size1 = size + size / 2;
        int[] array1 = new int[size1];
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

    @Override
    public boolean isEmpty() {
        return (first == -1);
    }

    public boolean isFull() {
        return ((first == 0 && last == size - 1) || first == last + 1);
    }

    @Override
    public void clear() {
        first = last = -1;
    }

    @Override
    public void enqueue(int x) throws NoSuchElementException {
        if (isFull() && !grow()) {
            throw new NoSuchElementException();
        }
        if (last == size - 1 || last == -1) {
            array[0] = x;
            last = 0;
            if (first == -1) {
                first = 0;
            }
        } else {
            last++;
            array[last] = x;
        }
    }

    @Override
    public int dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int x = array[first];
        if (first == last) { // only one element
            first = -1;
            last = -1;
        } else if (first == size - 1) {
            first = 0;
        } else {
            first++;
        }
        return x;
    }

    @Override
    public int first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return array[first];
    }

    @Override
    public void traverse() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int i,j;
        if (last >= first) {
            for (i = first; i <= last; i++) {
                System.out.print(array[i] + " ");
            }
        } else {
            for (i = first; i < size; i++) {
                System.out.print(array[i] + " ");
            }
            i = size - first;
            for (j = 0; j <= last; j++) {
                System.out.print(array[j] + " ");
            }
        }
        System.out.println();
    }

    public void realToBin(double k) {
        ArrayQueue q = new ArrayQueue();
        System.out.print(k + " in binary system is: 0.");
        while (k > 0) {
            k *= 2;
            if (k>=1) {
                q.enqueue(1);
                k -= 1;
            } else {
                q.enqueue(0);
            }
        }
        while (!q.isEmpty()) {
            System.out.print(q.dequeue());
        }
        System.out.println();
    }
}
