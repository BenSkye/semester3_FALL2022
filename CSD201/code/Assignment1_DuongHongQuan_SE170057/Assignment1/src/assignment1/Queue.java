/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import Tools.Menu;
import Tools.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Queue {

    Node n_head;
    Node n_tail;

    public Queue() {
        n_head = n_tail = null;

    }

    public Queue(Node n_head, Node n_tail) {
        this.n_head = n_head;
        this.n_tail = n_tail;
    }

    public boolean isEmpty() {
        return n_head == null;
    }

    public void clear() {
        n_head = null;
        System.out.println("Clear succesful");
    }

    public float front() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return n_tail.gpa_score;
    }

    public float dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }

        float gpa = n_tail.gpa_score;

        if (isEmpty()) {
            throw new Exception();
        } else {
            Node curr = n_head;
            Node prev = null;
            while (curr.p_Next != null) {
                prev = curr;
                curr = curr.p_Next;
            }
            prev.p_Next = null;
            n_tail = prev;
        }
        System.out.println("The gpa is " + gpa);
        return gpa;
    }

    public void enqueue(float x) throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }

        Node inshead = new Node(x);
        if (isEmpty()) {
            n_head = n_tail = inshead;
        } else {
            inshead.p_Next = n_head;
            n_head = inshead;
            System.out.println("Enqueue succesful");
        }

    }

    public int countElement() {
        int count = 0;
        Node curr = n_head;
        if (this.isEmpty()) {
            return count = 0;
        }
        while (curr != null) {
            count++;
            curr = curr.p_Next;
        }
        System.out.println("The queue has/have" + " " + count + " " + "element");
        return count;
    }

    public void show() {
        if (this.isEmpty()) {
            System.out.println("The list is empty");
        }
        Node curr = n_head;
        while (curr != null) {
            System.out.print(curr.gpa_score + " ");
            curr = curr.p_Next;
        }
        System.out.println();

    }

    public void function() throws Exception {
        Menu menu = new Menu("Stack");
        menu.addNewOption("1-Enqueue");
        menu.addNewOption("2-Dequeue");
        menu.addNewOption("3-Front");
        menu.addNewOption("4-Clear");
        menu.addNewOption("5-Count element(s) in Queue");
        menu.addNewOption("6-Show");
        menu.addNewOption("7-Exit");
        int choice;
        boolean cont = true;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    float input = (float) Utils.getADouble("Enter a gpa score: ", "a gpa score is required");
                    this.enqueue(input);
                    break;

                case 2:
                    this.dequeue();
                    break;

                case 3:

                    this.front();
                    break;

                case 4:
                    this.clear();
                    break;
                case 5:
                    this.countElement();
                    break;
                case 6:
                    this.show();
                    break;
                case 7:
                    break;

            }
        } while (choice < 7 && choice >= 1);

    }
}
