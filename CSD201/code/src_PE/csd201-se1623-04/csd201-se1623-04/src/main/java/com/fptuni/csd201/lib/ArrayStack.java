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
public class ArrayStack implements Stack {

    private int array[];
    private int top;
    private int max;

    public ArrayStack() {
        this(50);
    }

    public ArrayStack(int max) {
        this.array = new int[max];
        this.max = max;
        this.top = -1;
    }
    
    protected boolean grow() {
        int max1 = max + max / 2;
        int[] array1 = new int[max1];
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
    
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == max - 1;
    }

    @Override
    public void push(int x) {
        if (isFull() && !grow()) {
            return;
        }
        top++;
        array[top] = x;
    }

    @Override
    public int pop() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        int result = array[top];
        top--;
        return result;
    }

    @Override
    public void traverse() {
        for (int i = 0; i <= top; i++) {
            System.out.print(array[i] + " ");
        }
    }

    @Override
    public void clear() {
        top = -1;
    }

    @Override
    public int top() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    public void decToBin(int k) {
        ArrayStack s = new ArrayStack();
        System.out.print(k + " in binary system is: ");
        while (k > 0) {
            s.push(new Integer(k % 2));
            k = k / 2;
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop());
        }
        System.out.println();
    }

}
