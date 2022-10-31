package com.fptuni.csd201.lib;

import java.lang.reflect.Array;

public class CircularLinkedList {

    private Node head, tail;

    public CircularLinkedList() {
        head = null;
        tail = null;

    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("The List is Empty!");
            return;
        }
        if (head.getNext() == head) {
            System.out.print("The List is: " + head.getInfo());
            return;
        }
        System.out.print("The List is: ");
        Node current = head;
        while (current != tail) {
            System.out.print(current.getInfo() + " ");
            current = current.getNext();
        }
        System.out.print(tail.getInfo());
    }

    public boolean isContain(int i) {
        if (isEmpty() == true) {
            return false;
        }
        if (head.getNext() == head) {
            if (head.getInfo() == i) {
                return true;
            } else {
                return false;
            }
        }
        Node current = head;
        while (current != tail) {
            if (current.getInfo() == i) {
                return true;
            }
            current = current.getNext();
        }
        if (tail.getInfo() == i) {
            return true;
        }
        return false;
    }

    public boolean isContain(Node p) {
        if (isEmpty() == true) {
            return false;
        }
        if (head.getNext() == head) {
            if (head == p) {
                return true;
            } else {
                return false;
            }
        }
        Node current = head;
        while (current != tail) {
            if (current == p) {
                return true;
            }
            current = current.getNext();
        }
        if (tail == p) {
            return true;
        }
        return false;
    }

    public int size() {
        int dem = 1;
        if (isEmpty()) {
            return 0;
        }
        if (head.getNext() == head) {
            return 1;
        }
        Node current = head;
        while (current != tail) {
            dem = dem + 1;
            current = current.getNext();
        }
        return dem;
    }

    public Node NodeBefore(Node p) {
        return NodeLocate(NodePosition(p) - 1);

    }

    public Node NodeLocate(int i) {
        int dem = 1;
        if (i < 1 && i > size()) {
            return null;
        }
        Node current = head;
        do {
            if (dem == i) {
                return current;
            }
            dem++;
            current = current.getNext();
        } while (current != tail);
        return tail;
    }

    public Integer NodePosition(Node p) {
        for (int i = 1; i <= size(); i++) {
            if (NodeLocate(i) == p) {
                return i;
            }
        }
        return null;

    }

    public Node gethead() {
        return head;
    }

    public Node gettail() {
        return tail;
    }

    // 1. Add A Node to Head
    public void addToHead(int x) {
        Node free = new Node(x);
        if (isEmpty() == true) {
            head = free;
            head.setNext(head);
            tail = head;
            return;
        }
        if (head.getNext() == head) {
            tail = head;
            head = free;
            head.setNext(tail);
            tail.setNext(head);
            return;
        }

        Node Temp = head;
        head = free;
        head.setNext(Temp);
        tail.setNext(head);

    }

    // 2. Add a Node to Tail
    public void addToTail(int x) {
        Node free = new Node(x);
        if (isEmpty() == true) {
            head = free;
            head.setNext(head);
            tail = head;
            return;
        }
        if (head.getNext() == head) {
            tail = free;
            head.setNext(tail);
            tail.setNext(head);
            return;
        }

        Node Temp = tail;
        tail = free;
        Temp.setNext(tail);
        tail.setNext(head);
    }

    // 3. Add a node with value x after the node p.
    public void addAfter(Node p, int x) {
        Node free = new Node(x);
        if (isContain(p) == false || p == null) {
            System.out.println("We can't find this Node in the List.");
            return;
        }
        if (isEmpty()) {
            System.out.println("We can't find this Node in the List.");
            return;
        }
        if (head.getNext() == head) {
            if (p != head) {
                System.out.println("We can't find this Node in the List");
                return;
            } else {
                tail = free;
                head.setNext(tail);
                tail.setNext(head);
                return;
            }
        }
        if (p == head) {
            free.setNext(head.getNext());
            head.setNext(free);
            return;
        }
        if (p == tail) {
            Node temp = tail;
            tail = free;
            temp.setNext(tail);
            tail.setNext(head);
            return;
        }
        free.setNext(p.getNext());
        p.setNext(free);
    }

    // 4. Traverse from head to tail and dislay info of all nodes in the list.
    public void traverse() {
        display();
    }

    // 5. Delete the head and return its info.
    public Integer deleteFromHead() {
        if (isEmpty()) {
            return null;
        }
        if (head.getNext() == head) {
            int temp = head.getInfo();
            head = null;
            return temp;
        }
        int temp = head.getInfo();
        Node Temp = head.getNext();
        tail.setNext(Temp);
        head = Temp;
        return temp;
    }

    // 6. Delete the tail and return its info.
    public Integer deleteFromTail() {
        if (isEmpty()) {
            return null;
        }
        if (head.getNext() == head) {
            int temp = head.getInfo();
            head = null;
            return temp;
        }
        int temp = tail.getInfo();
        Node Temp = NodeBefore(tail);
        Temp.setNext(head);
        tail = Temp;
        return temp;
    }

    // 7. Delete the node after the node p and return its info.
    public Integer deleteAter(Node p) {
        if (isContain(p) == false) {
            return null;
        }
        if (isEmpty()) {
            return null;
        }
        if (head.getNext() == head) {
            int i = head.getInfo();
            head = null;
            return i;
        }
        if (p == NodeBefore(tail)) {
            int temp = tail.getInfo();
            deleteFromTail();
            return temp;
        }
        if (p == tail) {
            int temp = head.getInfo();
            deleteFromHead();
            return temp;
        }
        int temp = p.getNext().getInfo();
        Node Temp = p.getNext().getNext();
        p.setNext(Temp);
        return temp;
    }

    // 8 Delele the first node whose info is equal to x
    public void dele(int x) {
        if (isContain(x) == false) {
            System.out.println("There is no Node's Info = " + x + ".");
            return;
        }
        if (isEmpty()) {
            System.out.println("There is no Node's Info = " + x + ".");
            return;
        }
        int temp = 0;
        for (int i = 1; i <= size(); i++) {
            if (x == NodeLocate(i).getInfo()) {
                temp = i;
                break;
            }
        }

        Node k = NodeLocate(temp);
        if (k == head) {
            deleteFromHead();
            return;
        }
        if (k == tail) {
            deleteFromTail();
            return;
        }
        Node Temp = k.getNext();
        Node Temp2 = NodeBefore(k);
        Temp2.setNext(Temp);
    }

    // 9 .Search and return the reference to the first node having info x.
    public Node search(int x) {
        if (isEmpty() == true) {
            return null;
        }
        for (int i = 1; i <= size(); i++) {
            if (NodeLocate(i).getInfo() == x) {
                return NodeLocate(i);
            }
        }
        return null;

    }

    // 10. Count and return number of nodes in the list.
    public int count() {
        return size();
    }

    // 11. Delete an i-th node on the list. Besure that such a node exists
    public void deleter(int i) {
        if (i > size() || i < 1) {
            System.out.print("Over bound!");
            return;
        }
        if (i == 1) {
            deleteFromHead();
            return;
        }
        if (i == size()) {
            deleteFromTail();
            return;
        }
        Node temp1 = NodeLocate(i - 1);
        Node temp2 = NodeLocate(i + 1);
        temp1.setNext(temp2);
    }

    // 12. Sort the list by ascending order of info
    public void sort() {
        if (isEmpty()) {
            System.out.println("The List is Empty!");
            return;
        }
        int[] ar = new int[size()];
        for (int i = 0; i < size(); i++) {
            ar[i] = 0;
        }

        for (int i = 1; i <= size(); i++) {
            ar[i - 1] = NodeLocate(i).getInfo();
        }
        for (int i = 0; i < size(); i++) {
            for (int j = i; j < size(); j++) {
                if (ar[i] > ar[j]) {
                    int temp = ar[j];
                    ar[j] = ar[i];
                    ar[i] = temp;
                }
            }
        }
        for (int i = 1; i <= size(); i++) {
            NodeLocate(i).setInfo(ar[i - 1]);
        }

    }

    // 13. Remove a Node
    public void remove(Node p) {
        if (isEmpty() == true) {
            System.out.println("The List is Empty!");
            return;
        }
        if (isContain(p) == false) {
            System.out.println("There is no Node p in the List.");
            return;
        }
        for (int i = 1; i <= size(); i++) {
            if (NodeLocate(i) == p) {
                deleter(i);
            }
        }

    }

    // 14. Create and return array containing info of all nodes in the list.
    public int[] toArray() {
        if (isEmpty()) {
            System.out.println("The List is Empty!");
            return null;
        }
        int[] arr = new int[size()];
        for (int i = 1; i <= size(); i++) {
            arr[i - 1] = NodeLocate(i).getInfo();
        }
        return arr;
    }

    // 15. Merge two ordered singly linked lists of integers into one ordered list.
    public CircularLinkedList merge(CircularLinkedList X, CircularLinkedList Y) {
        if (X.head == null && X.tail == null) {
            return Y;
        }
        if (Y.head == null && Y.tail == null) {
            return X;
        }
        Node temp1 = Y.tail;
        Node temp2 = X.tail;
        temp2.setNext(Y.head);
        temp1.setNext(X.head);
        X.tail = temp1;

        return X;

    }

    // 16. Add a node with value x before the node p
    public void addBefore(Node p, int x) {
        if (isEmpty() == true) {
            System.out.println("The List is Empty!");
            return;
        }
        if (isContain(p) == false) {
            System.out.println("There is no Node p in the List.");
            return;
        }
        Node free = new Node(x);
        if (p == head) {
            addToHead(x);
            return;
        }
        for (int i = 2; i <= size(); i++) {
            if (NodeLocate(i) == p) {
                free.setNext(NodeLocate(i));
                NodeLocate(i - 1).setNext(free);
                return;
            }
        }
    }

    // 17. Attach a circular linked list to the end of another circular linked list.
    public void attach(CircularLinkedList X) {
        merge(this, X);

    }

    // 18. Find and return the maximum value in the list.
    public Integer max() {
        if (isEmpty()) {
            return null;
        }
        toArray();
        int max = toArray()[0];
        if (toArray().length == 1) {
            return toArray()[0];
        }
        for (int i = 0; i < toArray().length - 1; i++) {
            if (toArray()[i] < toArray()[i + 1]) {
                max = toArray()[i + 1];
            }

        }
        return max;
    }

    // 19. Find and return the minimun value in the list.
    public Integer min() {
        if (isEmpty()) {
            return null;
        }
        toArray();
        int min = toArray()[0];
        if (toArray().length == 1) {
            return toArray()[0];
        }
        for (int i = 0; i < toArray().length - 1; i++) {
            if (toArray()[i] > toArray()[i + 1]) {
                min = toArray()[i + 1];
            }

        }
        return min;
    }

    // 20. Find and return the Sum.
    public Integer sum() {
        if (isEmpty()) {
            return null;
        }
        toArray();
        int sum = 0;

        for (int i = 0; i < toArray().length; i++) {
            sum = sum + toArray()[i];
        }

        return sum;
    }

    // 21. Find and return the Average.
    public Float average() {
        if (isEmpty()) {
            return null;
        }
        toArray();

        return (float) sum() / (float) size();

    }

    // 22. Check and return true if the list is sorted, return false if the list is
    // not sorted.
    public boolean sorted() {
        if (isEmpty()) {

            return true;
        }
        int[] ar = new int[size()];
        for (int i = 0; i < size(); i++) {
            ar[i] = 0;
        }
        for (int i = 1; i <= size(); i++) {
            ar[i - 1] = NodeLocate(i).getInfo();
        }
        for (int i = 0; i < size(); i++) {
            for (int j = i; j < size(); j++) {
                if (ar[i] > ar[j]) {
                    return false;
                }
            }
        }
        return true;

    }

    // 23. Insert node with value x into sorted list so that the new list is sorted.
    public void insert(int x) {
        addToHead(x);
        sort();
    }

    // 24. Reverse a singly linked list using only one pass through the list.
    public void reverse() {
        if (isEmpty()) {
            System.out.println("The List is Empty!");
            return;
        }
        int[] arr = new int[size()];
        for (int i = 1; i <= size(); i++) {
            arr[i - 1] = NodeLocate(i).getInfo();
        }
        if (arr.length == 1) {
            return;
        }
        for (int i = 0; i <= (arr.length - 1) / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }

        for (int i = 1; i <= size(); i++) {
            NodeLocate(i).setInfo(arr[i - 1]);
        }

    }

    // 25. Check whether two circular linked list have the same contents.
    public boolean check(CircularLinkedList X, CircularLinkedList Y) {
        if (X.isEmpty() == true && Y.isEmpty() == true) {
            return true;
        }
        if (X.isEmpty() != Y.isEmpty()) {
            return false;
        }
        if (X.size() != Y.size()) {
            return false;
        }
        for (int i = 1; i <= X.size(); i++) {
            if (X.NodeLocate(i).getInfo() != Y.NodeLocate(i).getInfo()) {
                return false;
            }

        }
        return true;

    }
}