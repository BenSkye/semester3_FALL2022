package com.fptuni.csd201.lib.ObjectStackQueue;

import java.util.EmptyStackException;

public class ObjectStack {

    private Object[] array;
    private int top;
    private int size;

    public ObjectStack(int size) {
        this.size = size;
        array = new Object[size];

        top = -1;
    }

    public void updatesize(int size1) {

        Object[] array1 = new Object[size1];
        for (int i = 0; i < size1; i++) {
            if (i < size) {

                array1[i] = array[i];

            }
        }
        size = size1;
        array = array1;
    }

    public boolean isEmpty() {
        if (top == -1) {
            return true;
        }
        return false;
    }

    public boolean isFull() {
        if (top == size - 1) {
            return true;
        }
        return false;
    }

    public void clear() {
        top = -1;
    }

    public void push(Object t) {
        if (isFull() == true) {
            System.out.println("The Stack is Full.");
            return;
        }
        top++;
        array[top] = t;
    }

    public Object pop() throws EmptyStackException {
        if (isEmpty() == true) {
            throw new EmptyStackException();
        }
        Object temp = array[top];
        top--;
        return temp;
    }

    public Object top() throws EmptyStackException {
        if (isEmpty() == true) {
            throw new EmptyStackException();
        }
        return array[top];
    }

    public void traverse() {
        if (isEmpty()) {
            System.out.println("The Stack is Empty.");
            return;
        }
        for (int i = 0; i <= top; i++) {
            System.out.println(array[i].toString());
        }

    }
}
