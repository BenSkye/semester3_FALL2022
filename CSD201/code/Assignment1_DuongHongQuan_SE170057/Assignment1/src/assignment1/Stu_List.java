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
public class Stu_List {

    Node n_head;
    Node n_tail;

    Stu_List() {
        n_head = n_tail = null;
    }

    public Stu_List(Node n_head, Node n_tail) {
        this.n_head = n_head;
        this.n_tail = n_tail;
    }

    public boolean isEmpty() {
        return (n_head == null);
    }

    public boolean isExisting(float n) {
        if (this.isEmpty()) {
            return false;
        }
        Node curr = n_head;
        while (curr != null) {

            if (curr.gpa_score == n) {
                return true;
            }
            curr = curr.p_Next;
        }

        return false;
    }

    public void clear() {
        n_head = n_tail = null;
    }

    public void insertHead(Node insHead) {
        if (this.isEmpty()) {
            n_head = n_tail = insHead;
        } else {
            insHead.p_Next = n_head;
            n_head = insHead;
            System.out.println("Succesful insertion");
        }
    }

    public void insertTail(Node insTail) {
        if (this.isEmpty()) {
            n_head = n_tail = insTail;
        } else {
            n_tail.p_Next = insTail;
            n_tail = insTail;
            System.out.println("Succesful insertion");

        }
    }

    public void deleteHead() {
        if (this.isEmpty()) {
            return;
        }
        if (this.n_head == null) {
            return;
        }
        n_head = n_head.p_Next;
        System.out.println("Succesful deletion");

    }

    public void deleteTail() {
        if (this.isEmpty()) {
            return;
        }

        Node curr = n_head;
        Node prev = null;
        while (curr.p_Next != null) {
            prev = curr;
            curr = curr.p_Next;
        }
        prev.p_Next = null;
        n_tail = prev;
        System.out.println("Succesful deletion");

    }

    public void deleteNode(float delScore) {
        if (this.isEmpty()) {
            return;
        }
        Node curr = n_head, prev = null;
        while (curr != null && curr.gpa_score == delScore) {
            n_head = curr.p_Next; 
            curr = n_head; 
        }

        while (curr != null) {
            while (curr != null && curr.gpa_score != delScore) {
                prev = curr;
                curr = curr.p_Next;
            }
            if (curr == null) {
                return;
            }
            prev.p_Next = curr.p_Next;
            curr = prev.p_Next;
        }

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

    public void function() {
        Menu menu = new Menu("Student List");
        menu.addNewOption("1-Add Head");
        menu.addNewOption("2-Add Tail");
        menu.addNewOption("3-Delete Head");
        menu.addNewOption("4-Delete Tail");
        menu.addNewOption("5-Delete a node with specific value");
        menu.addNewOption("6-Check a gpa exist");
        menu.addNewOption("7-Clear student list");
        menu.addNewOption("8-Show student list");
        menu.addNewOption("9-Exit");
        int choice;
        boolean cont = true;

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    float headValue = (float) Utils.getADouble("Enter a head: ", "A head is required");
                    this.insertHead(new Node(headValue));
                    break;

                case 2:
                    float tailValue = (float) Utils.getADouble("Enter a tail: ", "A tail is required");
                    this.insertTail(new Node(tailValue));
                    break;

                case 3:

                    this.deleteHead();
                    break;

                case 4:
                    this.deleteTail();
                    break;
                case 5:
                    int input = Utils.getAnInteger("Enter a node you want reomve: ", "A node is required");
                    this.deleteNode(input);
                    break;
                case 6:
                    int input1 = Utils.getAnInteger("Enter a gpa you want check a gpa exist: ", "A gpa is required");
                    this.isExisting(input1);
                    break;
                case 7:
                    this.clear();
                    break;
                case 8:
                    this.show();
                    break;
                case 9:
                    break;

            }
        } while (choice < 9 && choice >= 1);

    }
}
