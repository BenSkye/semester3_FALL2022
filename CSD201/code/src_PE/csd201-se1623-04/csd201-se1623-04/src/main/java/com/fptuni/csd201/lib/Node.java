package com.fptuni.csd201.lib;

import com.fptuni.csd201.object.Person;

/**
 *
 * @author huynh van phu - SE160877
 */
public class Node {

    private int info;
    private Node next;

    public Node() {
    }

    public Node(int info, Node next) {
        this.info = info;
        this.next = next;
    }
    
    public Node(int info) {
        this(info, null);
    }
    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
    
}

