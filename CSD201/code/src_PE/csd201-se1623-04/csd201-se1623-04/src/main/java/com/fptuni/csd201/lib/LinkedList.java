package com.fptuni.csd201.lib;

/**
 *
 * @author HUYNH VAN PHU - SE160877
 */
public class LinkedList {

    Node head, tail;

    public LinkedList() {
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
        Node node = new Node(x);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
    }

    //2. add a node with value x at the tail of a list
    public void addToTail(int x) {
        Node node = new Node(x);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    //3. add a node with value x after the node p
    public void addAfter(Node p, int x) {
        Node node = new Node(x);
        node.setNext(p.getNext());
        p.setNext(node);
        if (p == tail) {
            tail = node;
        }
    }

    //4. traverse from head to tail and display info of all nodes in the list 
    public void traverse() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getInfo() + " ");
            current = current.getNext();
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
        }
        return x;
    }

    //6. delete the tail and return its info
    public int deleteFromTail() {
        int x = tail.getInfo();
        if (head == tail) {
            head = tail = null;
        } else {
            Node current = head;
            while (current != null && current.getNext() != tail) {
                current = current.getNext();
            }
            tail = current;
            tail.setNext(null);
        }
        return x;
    }

    //7. delete the node after the node p and return its info
    public int deleteAfter(Node p) {
        if (p == tail) {
            return 0;
        }
        if (p.getNext() == tail) {
            tail = p;
        }
        int x = p.getNext().getInfo();
        p.setNext(p.getNext().getNext());
        return x;
    }

    //8. delele the first node whose info is equal to x
    public void dele(int x) {
        Node current = head, before = null;
        while (current != null && current.getInfo() != x) {
            before = current;
            current = current.getNext();
        }
        if (current == null) { //linklist rong hoac ko tim thay x trong linked list
            return;
        }
        if (before == null) { //gia tri head.info == x
            head = head.getNext();
            if (head == null) { //linklist chi co 1 node
                tail = null;
            }
            return;
        }
        before.setNext(current.getNext());
        if (before.getNext() == null) {  //tail.info == x
            tail = before;
        }
    }

    //9. search and return the reference to the first node having info x
    public Node search(int x) {
        Node current = head;
        while (current != null && current.getInfo() != x) {
            current = current.getNext();
        }
        return current;
    }

    //10. count and return number of nodes in the list
    public int count() {
        Node current = head;
        int numOfNode = 0;
        while (current != null) {
            numOfNode++;
            current = current.getNext();
        }
        return numOfNode;
    }

    //11. delete an i-th node on the list. Besure that such a node exists
    public void deleI(int i) {
        Node current = head, before = null;
        if (current == null) {
            return;
        }
        if (i < 1 || i > count()) {
            return;
        }
        int x = 1;
        while (current != null && x < i) {
            x++;
            before = current;
            current = current.getNext();
        }
        if (before == null) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            return;
        }
        before.setNext(current.getNext());
        if (before.getNext() == null) {
            tail = before;
        }
    }

    //12.  sort the list by ascending order of info
    public void sort() {
        Node i = head;
        Node j = null;
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
    public void remove(Node p) {
        if (p == null) {
            return;
        }
        Node current = head, before = null;
        while (current != null && current != p) {
            before = current;
            current = current.getNext();
        }
        if (current == null) {
            return;
        }
        if (before == null) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            return;
        }
        before.setNext(current.getNext());
        if (before.getNext() == null) {
            tail = before;
        }
        p.setNext(null);
    }

    //14. create and return array containing info of all nodes in the list
    public int[] toArray() {
        int[] arr = new int[count()];
        int pos = 0;
        Node current = head;
        while (current != null) {
            arr[pos] = current.getInfo();
            pos++;
            current = current.getNext();
        }
        return arr;
    }

    //15. Merge two ordered singly linked lists of integers into one ordered list
    public void mergeOrder(LinkedList list1, LinkedList list2) {
        Node currentA = list1.head;
        Node currentB = list2.head;
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
    public void addBefore(Node p, int x) {
        Node node = new Node(x, null);
        Node current = head, before = null;
        while (current != null && current != p) {
            before = current;
            current = current.getNext();
        }
        if (current == null) {
            return;
        }
        if (current == head) {
            node.setNext(p);
            head = node;
            return;
        }
        node.setNext(p);
        before.setNext(node);
    }

    //17. Attach a singly linked list to the end of another singly linked list
    public void attach(LinkedList list) {
        this.tail.setNext(list.head);
        this.tail = list.tail;
    }

    //18. find and return the maximum value in the list
    public int max() {
        Node current = head;
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
        Node current = head;
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
        Node current = head;
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
        Node current = head;
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
        Node current = head;
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
        Node current = head, before = null, after = null;
        tail = head;
        while (current != null) {
            after = current.getNext();
            current.setNext(before);
            before = current;
            current = after;
        }
        head = before;
    }

    //25. Check whether two singly linked list have the same contents
    public boolean checkTwoLinkedList(LinkedList list) {
        Node currentA = this.head;
        Node currentB = list.head;
        while (currentA != null || currentB != null) {
            if (currentA == null || currentB == null) {
                return false;
            }
            if (currentA.getInfo() != currentB.getInfo()) {
                return false;
            }
            currentA = currentA.getNext();
            currentB = currentB.getNext();
        }
        return true;
    }

    //Ham dung de xac dinh head tail
    public void findHeadTail() {
        System.out.println("head: " + head.getInfo());
        System.out.println("tail: " + tail.getInfo());
    }
}
