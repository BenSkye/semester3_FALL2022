/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import java.util.NoSuchElementException;

/**
 *
 * @author HUYNH VAN PHU - SE160877
 */
public class QueueString {

    private String[] array;
    private int max;
    private int first;
    private int last;

    public QueueString() {
        this(10);
    }

    public QueueString(int max) {
        this.max = max;
        this.first = -1;
        this.last = -1;
        this.array = new String[max];
    }

    public boolean grow() {
        int i, j;
        int max1 = max + max / 2;
        String[] array1 = new String[max1];
        if (array1 == null) {
            return (false);
        }
        if (last >= first) {
            for (i = first; i <= last; i++) {
                array1[i - first] = array[i];
            }
        } else {
            for (i = first; i < max; i++) {
                array1[i - first] = array[i];
            }
            i = max - first;
            for (j = 0; j <= last; j++) {
                array1[i + j] = array[j];
            }
        }
        array = array1;
        first = 0;
        last = max - 1;
        max = max1;
        return (true);
    }

    public boolean isFull() {
        return ((first == 0 && last == max - 1) || first == last + 1);
    }

    public boolean isEmpty() {
        return (first == -1);
    }

    public void clear() {
        first = last = -1;
    }

    public void enqueue(String x) throws NoSuchElementException {
        if (isFull() && !grow()) {
            throw new NoSuchElementException();
        }
        if (last == max - 1 || last == -1) {
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

    public String dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        String x = array[first];
        if (first == last) { // only one element
            first = -1;
            last = -1;
        } else if (first == max - 1) {
            first = 0;
        } else {
            first++;
        }
        return x;
    }

    public String first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (array[first]);
    }

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
            for (i = first; i < max; i++) {
                System.out.print(array[i] + " ");
            }
            i = max - first;
            for (j = 0; j <= last; j++) {
                System.out.print(array[j] + " ");
            }
        }
        System.out.println();
    }
}
