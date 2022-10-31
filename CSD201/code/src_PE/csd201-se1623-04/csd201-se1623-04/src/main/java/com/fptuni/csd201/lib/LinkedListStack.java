/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import com.fptuni.csd201.ds.Stack;
import java.util.EmptyStackException;

/**
 *
 * @author DUNGHUYNH
 */
public class LinkedListStack implements Stack {

    private Node head;

    public LinkedListStack() {
        head = null;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public void push(int x) {
        head = new Node(x, head);
    }

    @Override
    public int pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int x = head.getInfo();
        head = head.getNext();
        return (x);
    }

    @Override
    public int top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
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
