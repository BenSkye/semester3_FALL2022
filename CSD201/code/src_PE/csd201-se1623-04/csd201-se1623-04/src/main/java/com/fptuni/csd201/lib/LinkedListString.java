/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

/**
 *
 * @author vu doan minh quang SE160767
 */
public class LinkedListString {

    NodeString head, tail;

    public LinkedListString() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        head = tail = null;
    }

    //1. add a node with value x at the head of a list
    public void addToHead(String x) {
        NodeString node = new NodeString(x);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    //2. add a node with value x at the tail of a list
    public void addToTail(String x) {
        NodeString node = new NodeString(x);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    //3. add a node with value x after the node p
    public void addAfter(NodeString p, String x) {
        NodeString node = new NodeString(x);
        node.setNext(p.getNext());
        p.setNext(node);
        if (p == tail) {
            tail = node;
        }
    }

    //4. traverse from head to tail and display info of all nodes in the list 
    public void traverse() {
        NodeString current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    //5. delete the head and reuturn its info
    public String deleteFromHead() {
        String x = head.getInfo();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
        }
        return x;
    }

    //6. delete the tail and return its info
    public String deleteFromTail() {
        String x = tail.getInfo();
        if (head == tail) {
            head = tail = null;
        } else {
            NodeString current = head;
            while (current != null && current.getNext() != tail) {
                current = current.getNext();
            }
            tail = current;
            tail.setNext(null);
        }
        return x;
    }

    //7. delete the node after the node p and return its info
    public String deleteAfter(NodeString p) {
        if (p == tail) {
            return null;
        }
        if (p.getNext() == tail) {
            tail = p;
        }
        String x = p.getNext().getInfo();
        p.setNext(p.getNext().getNext());
        return x;
    }

    //8. delele the first node whose info is equal to x
    public void dele(String x) {
        NodeString current = head, before = null;
        while (current != null && (current.getInfo() == null ? x != null : !current.getInfo().equals(x))) {
            before = current;
            current = current.getNext();
        }
        if (current == null) {
            return; 
        }
        if (before == null) { //gia tri head.info == x
            head = head.getNext();
            if (head == null) {
                tail = null; //linklist chi co 1 node
            }
            return;
        }
        before.setNext(current.getNext());
        if (before.getNext() == null) {
            tail = before;
        }
    }

    //9. search and return the reference to the first node having info x
    public NodeString search(String x) {
        NodeString current = head;
        while (current != null && (current.getInfo() == null ? x != null : !current.getInfo().equals(x))) {
            current = current.getNext();
        }
        return current;
    }

    //10. count and return number of nodes in the list
    public int count() {
        NodeString current = head;
        int x = 0;
        while (current != null) {
            x++;
            current = current.getNext();
        }
        return x;
    }

}
