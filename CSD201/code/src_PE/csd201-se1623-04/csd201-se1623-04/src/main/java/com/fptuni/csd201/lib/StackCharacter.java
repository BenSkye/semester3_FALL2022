/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import java.util.EmptyStackException;

/**
 *
 * @author HUYNH VAN PHU - SE160877
 */
public class StackCharacter {
    private char[] array;
    private int top;
    private int max;

    public StackCharacter() {
        this(50);
    }

    public StackCharacter(int max) {
        this.array = new char[max];
        this.top = -1;
        this.max = max;
    }

    public boolean grow() {
        int max1 = max + max / 2;
        char[] array1 = new char[max1];
        if (array1 == null) {
            return false;
        }
        for (int i = 0; i <= top; i++) {
            array1[i] = array[i];
        }
        max = max1;
        array = array1;
        return true;
    }

    public Boolean isEmpty() {
        return top == -1;
    }
    
    public void clear() {
        top = -1;
    }
    
    public Boolean isFull() {
        return top == max - 1;
    }

    public void push(char x) {
        if (isFull() && !grow()) {
            return;
        }
        top++;
        array[top] = x;
    }

    public char pop() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        char result = array[top];
        top--;
        return result;

    }

    public char top() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    public void traverse() {
        for (int i = 0; i <= top; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
