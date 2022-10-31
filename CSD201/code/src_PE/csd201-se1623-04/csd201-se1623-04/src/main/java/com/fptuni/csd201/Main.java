package com.fptuni.csd201;

import com.fptuni.csd201.bookservice.BookServer;
import com.fptuni.csd201.curious.Curious;
import com.fptuni.csd201.curious.FindingPath;
import com.fptuni.csd201.lib.ArrayQueue;
import com.fptuni.csd201.lib.ArrayStack;
import com.fptuni.csd201.lib.BinaryTree;
import com.fptuni.csd201.lib.CircularLinkedList;
import com.fptuni.csd201.lib.DoublyLinkedList;
import com.fptuni.csd201.lib.LinkedListString;
import com.fptuni.csd201.utils.Turtle;
import com.fptuni.csd201.lib.Node;
import com.fptuni.csd201.lib.LinkedList;
import com.fptuni.csd201.lib.NodeDoubly;
import com.fptuni.csd201.lib.QueueCharacter;
import com.fptuni.csd201.lib.QueueString;
import com.fptuni.csd201.lib.StackCharacter;
import com.fptuni.csd201.lib.StackString;
import com.fptuni.csd201.marsmessenger.MessageReceiverImpl;
import com.fptuni.csd201.marsmessenger.SpaceShip;
import com.fptuni.csd201.recursion.VonKnockSnowflake;

public class Main {

    public static void testLinkedList() {
        System.out.println("Linked List");

        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        LinkedList list3 = new LinkedList();

        list1.addToTail(10);//10
        list1.addToTail(30);//10 30
        list1.addToHead(60);//60 10 30
        list1.addToHead(40);//40 60 10 30
        list1.addToHead(50);//50 40 60 10 30

        list2.addToTail(2);//2
        list2.addToTail(35);//2 35
        list2.addToHead(70);//70 2 35
        list2.addToHead(30);//30 70 2 35 
        list2.addToHead(20);//20 30 70 2 35 

        list1.traverse();
        list2.traverse();

        // Node p = list1.search(40);
//        list1.reverse();
//        list1.findHeadTail();
//        list1.traverse();
//        list3.mergeOrder(list1, list2);
//        list3.traverse();
//        list3.addBefore(list3.tail, 2);
//        list3.traverse();
//        list3.insert(33);
//        list3.traverse();
//
//        list3.reverse();
//        list3.traverse();
//
        if (list1.checkTwoLinkedList(list2)) {
            System.out.println("same");
        } else {
            System.out.println("not same");
        }
//
//        System.out.println(list1.max() + " " + list1.min());
//        System.out.println(list2.avg());
//        list1.attach(list2);
//        list1.traverse();
    }

    public static void testLinkListString() {
        System.out.println("Linked List String");

        LinkedListString list = new LinkedListString();

        list.addToHead("a");
        list.addToHead("d");
        list.addToHead("e");
        list.addToHead("g");
        list.addToHead("b");
        list.traverse();
        list.dele("b");
        list.traverse();

    }

    public static void testDoublyLinkedList() {
        System.out.println("Doubly Linked List");

        DoublyLinkedList list1 = new DoublyLinkedList();
        DoublyLinkedList list2 = new DoublyLinkedList();
        DoublyLinkedList list3 = new DoublyLinkedList();

        list1.addToTail(10);//10
        list1.addToTail(30);//10 30
        list1.addToHead(60);//60 10 30
        list1.addToHead(40);//40 60 10 30
        list1.addToHead(50);//50 40 60 10 30
        list1.traverse();

        list2.addToTail(2);//2
        list2.addToTail(35);//2 35
        list2.addToHead(70);//70 2 35
        list2.addToHead(30);//30 70 2 35 
        list2.addToHead(20);//20 30 70 2 35 
        list2.traverse();

        NodeDoubly p = list1.search(30);
//        list1.addBefore(p, 20);
//        list1.remove(p);
//        list1.deleI(5);
//        list3.mergeOrder(list1, list2);
//        list3.traverse();
//        list3.traverseFromTail();
//        System.out.println(list1.sum());
//        list1.attach(list2);
//        list1.sort();
//        list1.insert(45);
//        list1.traverse();
//        list1.reverse();
//        list1.traverse();
//        list1.traverseFromTail();

//        if (list1.checkDoublyLinkedList(list2)) {
//            System.out.println("same");
//        } else {
//            System.out.println("not same");
//        }
        list1.traverse();
        list1.reverse();
        list1.traverse();
    }

    public static void testCircularLinkedList() {
        System.out.println("Circular Linked List");
        CircularLinkedList list = new CircularLinkedList();
        CircularLinkedList list2 = new CircularLinkedList();
        Node temp;
        temp = new Node(3);

        list.addToHead(55);
        list.addToHead(445);
        list.addToHead(1242145);
        list2.addToHead(55);
        list2.addToHead(445);
        list2.addToHead(1242145);
        System.out.println(list.check(list, list2));
        //System.out.println(list.NodePosition(list.search(8)));
        System.out.println(list.gettail().getNext().getInfo());
        list.display();
    }

    public static void testStack() {
        System.out.println("Array stack");
        ArrayStack stack = new ArrayStack(4);
        stack.decToBin(10);
        stack.push(10);
        stack.push(40);
        stack.push(30);
        stack.push(60);
        stack.pop();
        stack.push(20);
        System.out.println(stack.top());
        stack.traverse();

    }

    public static void testQueue() {
        System.out.println("Array Queue");
        ArrayQueue queue = new ArrayQueue(4);
        queue.realToBin(0.75);
        queue.enqueue(10);
        queue.enqueue(40);
        queue.enqueue(30);
        queue.enqueue(60);
        queue.dequeue();
        queue.enqueue(20);
        queue.enqueue(50);
        System.out.println(queue.first());
        queue.traverse();
    }

    public static void testStackString() {
        System.out.println("stack of string");
        StackString stack = new StackString(4);
        stack.push("a");
        stack.push("b");
        stack.push("cc");
        stack.push("dd");
        stack.pop();
        stack.push("abc");
        System.out.println(stack.top());
        stack.traverse();
    }

    public static void testQueueString() {
        System.out.println("queue of string");
        QueueString queue = new QueueString(4);
        queue.enqueue("se1");
        queue.enqueue("se2");
        queue.enqueue("se3");
        queue.enqueue("se4");
        queue.dequeue();
        System.out.println(queue.first());
        queue.traverse();
    }

    public static void testStackCharacter() {
        System.out.println("stack of character");
        StackCharacter stack = new StackCharacter(4);
        stack.push('a');
        stack.push('b');
        stack.push('c');
        stack.push('d');
        stack.pop();
        stack.push('e');
        stack.traverse();
    }

    public static void testQueueCharacter() {
        System.out.println("queue of character");
        QueueCharacter queue = new QueueCharacter(4);
        queue.enqueue('a');
        queue.enqueue('b');
        queue.dequeue();
        queue.enqueue('c');
        queue.enqueue('d');
        queue.enqueue('e');
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();

        queue.traverse();
    }

    public static void testVonKnockSnowflake() {
        VonKnockSnowflake s = new VonKnockSnowflake();
        s.draw();

    }

    public static void testMarsMessenger() {
        SpaceShip ss = new SpaceShip();

        for (int i = 1; i <= 10; i++) {

            System.out.println("----TEST " + i);
            MessageReceiverImpl msgrec = new MessageReceiverImpl();

            ss.start(i); // Start test case 1-10
            //ss.print();

            while (!ss.isEmpty()) {
                msgrec.receive(ss.next());

            }

            System.out.println(msgrec.getMessage());
        }

        for (int i = 11; i <= 15; i++) {

            System.out.println("----TEST " + i);
            MessageReceiverImpl msgrec = new MessageReceiverImpl();

            ss.start(i); // Start test case 10-15
            //ss.print();

            while (!ss.isEmpty()) {
                msgrec.receive(ss.next());

            }

            int[] mi = msgrec.getMissingIndex();
            if (mi != null) {
                for (int j = 0; j < mi.length; j++) {
                    System.out.print(mi[j] + " ");
                }
            }

            // Q3. PRINT MISSING PACKAGES, PRINT TRUE IF MISSING PACKAGES ARE CORRECT 
            System.out.println();
            System.out.println(ss.setMissingPackages(msgrec.getMissingIndex()));

            //ss.print();
            //Q4. RESEND THE MISSING PACKAGE AND SHOW COMPLETE MESSAGE
            while (!ss.isEmpty()) {
                msgrec.receive(ss.next());
            }

            System.out.println(msgrec.getMessage());
        }
        for (int i = 16; i <= 20; i++) {

            System.out.println("----TEST " + i);
            MessageReceiverImpl msgrec = new MessageReceiverImpl();

            ss.start(i); // Start test case 1-10
            //ss.print();

            while (!ss.isEmpty()) {
                msgrec.receive(ss.next());

            }

            System.out.println(msgrec.getMessage());
        }
    }

    public static void testMarsExplorer() {
        Curious explorer = new Curious();
        explorer.start(8); // map is the map for testing 1,2,3,4
        explorer.showMap();

        FindingPath fp = new FindingPath();
        fp.setExplorer(explorer);
        //fp.run();
        fp.run1();

    }

    public static void testBinaryTree() {
        System.out.println("Binary tree");
        BinaryTree tree = new BinaryTree();
        tree.insert(10);
        tree.insert(4);
        tree.insert(12);
        tree.insert(2);
        tree.insert(8);
        tree.insert(11);
        tree.insert(19);
        tree.insert(5);
        tree.insert(25);
        tree.breadth();

    }

    public static void testBookServer() {
        BookServer bookServer = new BookServer();
        bookServer.process(7);
    }

    public static void main(String[] args) {

        //testLinkedList();
        //testMarsExplorer();
        //testDoublyLinkedList();
        //testLinkListString();
        //testStack();
        //testQueue();
        //testStackString();
        //testQueueString();
        //testStackCharacter();
        //testQueueCharacter();
        // testMarsExplorer();
        // testMarsMessenger();
        testBookServer();
    }
}
