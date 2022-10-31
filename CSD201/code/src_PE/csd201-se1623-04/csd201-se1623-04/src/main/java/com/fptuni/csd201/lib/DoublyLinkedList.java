/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

/**
 *
 * @author HUYNH VAN PHU - SE160877
 */
public class DoublyLinkedList {

    NodeDoubly head, tail;

    public DoublyLinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        head = tail = null;
    }

    //1. add a node with value x at the head of a list
    public void addToHead(int x) {
        NodeDoubly node = new NodeDoubly(x);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head.setPrev(node);
            head = node;
        }
    }

    //2. add a node with value x at the tail of a list
    public void addToTail(int x) {
        NodeDoubly node = new NodeDoubly(x);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
    }

    //3. add a node with value x after the node p
    public void addAfter(NodeDoubly p, int x) {
        NodeDoubly node = new NodeDoubly(x);
        node.setNext(p.getNext());
        node.setPrev(p);
        if (p.getNext() == null) {
            tail = node;
        } else {
            p.getNext().setPrev(node);
        }
        p.setNext(node);
    }

    //4. traverse from head to tail and display info of all nodes in the list 
    public void traverse() {
        NodeDoubly current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    //Ham traverse nguoc de kiem tra tinh dung dan cua doubly linked list
    public void traverseFromTail() {
        NodeDoubly current = tail;
        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getPrev();
        }
        System.out.println();
    }

    //5. delete the head and reuturn its info
    public int deleteFromHead() {
        int x = head.getInfo();
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        return x;
    }

    //6. delete the tail and return its info
    public int deleteFromTail() {
        int x = tail.getInfo();
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        return x;
    }

    //7. delete the node after the node p and return its info
    public int deleteAfter(NodeDoubly p) {
        if (p == tail) {
            return 0;
        }
        if (p.getNext() == tail) {
            tail = p;
        }
        int x = p.getNext().getInfo();
        p.getNext().getNext().setPrev(p);
        p.setNext(p.getNext().getNext());
        return x;
    }

    //8. delele the first node whose info is equal to x
    public void dele(int x) {
        NodeDoubly current = head;
        while (current != null && current.getInfo() != x) {
            current = current.getNext();
        }
        if (current == null) { //linklist rong hoac ko tim thay x trong linked list
            return;
        }
        if (current == head) { //gia tri head.info == x
            deleteFromHead();
            return;
        }
        if (current == tail) { //gia tri tail.info == x
            deleteFromTail();
            return;
        }
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
    }

    //9. search and return the reference to the first node having info x
    public NodeDoubly search(int x) {
        NodeDoubly current = head;
        while (current != null && current.getInfo() != x) {
            current = current.getNext();
        }
        return current;
    }

    //10. count and return number of nodes in the list
    public int count() {
        NodeDoubly current = head;
        int x = 0;
        while (current != null) {
            x++;
            current = current.getNext();
        }
        return x;
    }

    //11. delete an i-th node on the list. Besure that such a node exists
    public void deleI(int i) {
        NodeDoubly current = head;
        if (current == null) {
            return;
        }
        if (i < 1 || i > count()) {
            return;
        }
        int x = 1;
        while (x < i) {
            x++;
            current = current.getNext();
        }
        if (current == head) { //gia tri head.info == x
            deleteFromHead();
            return;
        }
        if (current == tail) {
            deleteFromTail();
            return;
        }
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
    }

    //12.  sort the list by ascending order of info
    public void sort() {
        NodeDoubly i = head;
        NodeDoubly j = null;
        int tmp;
        while (i != null) {
            j = i.getNext();
            while (j != null) {
                if (j.getInfo() < i.getInfo()) {
                    tmp = i.getInfo();
                    i.setInfo(j.getInfo());
                    j.setInfo(tmp);
                }
                j = j.getNext();
            }
            i = i.getNext();
        }
    }

    //13. delete node p if it exists in the list
    public void remove(NodeDoubly p) {
        if (p == null) {
            return;
        }
        NodeDoubly current = head;
        while (current != null && current != p) {
            current = current.getNext();
        }
        if (current == null) {
            return;
        }
        if (current == head) { 
            deleteFromHead();
            return;
        }
        if (current == tail) {
            deleteFromTail();
            return;
        }
        current.getNext().setPrev(current.getPrev());
        current.getPrev().setNext(current.getNext());
    }

    //14. create and return array containing info of all nodes in the list
    public int[] toArray() {
        int[] arr = new int[count()];
        int pos = 0;
        NodeDoubly current = head;
        while (current != null) {
            arr[pos] = current.getInfo();
            pos++;
            current = current.getNext();
        }
        return arr;
    }

    //15. Merge two ordered singly linked lists of integers into one ordered list
    public void mergeOrder(DoublyLinkedList list1, DoublyLinkedList list2) {
        NodeDoubly currentA = list1.head;
        NodeDoubly currentB = list2.head;
        if (!list1.sorted()) {
            list1.sort();
        }
        if (!list2.sorted()) {
            list2.sort();
        }
        clear();
        while (currentA != null || currentB != null) {
            if (currentA == null) {
                addToTail(currentB.getInfo());
                currentB = currentB.getNext();
                continue;
            }
            if (currentB == null) {
                addToTail(currentA.getInfo());
                currentA = currentA.getNext();
                continue;
            }
            if (currentA.getInfo() <= currentB.getInfo()) {
                addToTail(currentA.getInfo());
                currentA = currentA.getNext();
            } else {
                addToTail(currentB.getInfo());
                currentB = currentB.getNext();
            }
        }
    }

    //16. add a node with value x before the node p
    public void addBefore(NodeDoubly p, int x) {
        NodeDoubly node = new NodeDoubly(x);
        node.setNext(p);
        node.setPrev(p.getPrev());
        if (p.getPrev() == null) {
            head = node;
        } else {
            p.getPrev().setNext(node);
        }
        p.setPrev(node);
    }

    //17. Attach a singly linked list to the end of another singly linked list
    public void attach(DoublyLinkedList list) {
        this.tail.setNext(list.head);
        list.head.setPrev(this.tail);
        this.tail = list.tail;
    }

    //18. find and return the maximum value in the list
    public int max() {
        NodeDoubly current = head;
        if (current == null) {
            return 0;
        }
        int x = current.getInfo();
        current = current.getNext();
        while (current != null) {
            if (current.getInfo() > x) {
                x = current.getInfo();
            }
            current = current.getNext();
        }
        return x;
    }

    //19. find and return the minimum value in the list
    public int min() {
        NodeDoubly current = head;
        if (current == null) {
            return 0;
        }
        int x = current.getInfo();
        current = current.getNext();
        while (current != null) {
            if (current.getInfo() < x) {
                x = current.getInfo();
            }
            current = current.getNext();
        }
        return x;
    }

    //20. return the sum of all values in the list
    public int sum() {
        NodeDoubly current = head;
        int x = 0;
        while (current != null) {
            x += current.getInfo();
            current = current.getNext();
        }
        return x;
    }

    //21. return the average of all values in the list
    public int avg() {
        int average = sum() / count();
        return average;
    }

    //22. check and return true if the list is sorted, return false if the list is not sorted
    public boolean sorted() {
        NodeDoubly current = head;
        while (current != null && current.getNext() != null) {
            if (current.getInfo() >= current.getNext().getInfo()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    //23. insert node with value x into sorted list so that the new list is sorted
    public void insert(int x) {
        if (isEmpty()) {
            addToHead(x);
            return;
        }
        if (!sorted()) {
            sort();
        }
        if (x <= head.getInfo()) {
            addToHead(x);
            return;
        }
        if (x >= tail.getInfo()) {
            addToTail(x);
            return;
        }
        NodeDoubly current = head;
        while (current != null && current.getNext() != null) {
            if (current.getInfo() <= x && current.getNext().getInfo() >= x) {
                addAfter(current, x);
                return;
            }
            current = current.getNext();
        }
    }

    //24. Reverse a singly linked list using only one pass through the list
    public void reverse() {
        NodeDoubly current = head, before = null, after = null;
        tail = head;
        while (current != null) {
            after = current.getNext();
            current.setNext(before);
            current.setPrev(after);
            before = current;
            current = after;
        }
        head = before;
    }

    //25. Check whether two singly linked list have the same contents
    public boolean checkDoublyLinkedList(DoublyLinkedList list) {
        NodeDoubly currentA = this.head;
        NodeDoubly currentB = list.head;
        if (this.count() != list.count()) {
            return false;
        }
        while (currentA != null && currentB != null) {
            if (currentA.getInfo() != currentB.getInfo()) {
                return false;
            }
            currentA = currentA.getNext();
            currentB = currentB.getNext();
        }
        return true;
    }
}
