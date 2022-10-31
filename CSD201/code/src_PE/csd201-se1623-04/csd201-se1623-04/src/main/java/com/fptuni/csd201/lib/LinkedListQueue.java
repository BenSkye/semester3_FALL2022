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
public class LinkedListQueue implements Queue {

    private Node head, tail;

    public LinkedListQueue() {
        head = tail = null; 
    }

    @Override
    public void enqueue(int x) {
        if (isEmpty()) {
            head = tail = new Node(x);
        } else {
            tail.setNext(new Node(x));
            tail = tail.getNext();
        }
    }

    @Override
    public int dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        int x = head.getInfo();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        return (x);

    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void clear() {
        head = tail = null; 
    }

    @Override
    public int first() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (head.getInfo());
    }

    @Override
    public void traverse() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getNext();
        }
        System.out.println();
    }
}
