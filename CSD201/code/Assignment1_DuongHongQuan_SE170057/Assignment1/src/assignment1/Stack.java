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
public class Stack {

    Node n_head;

    public Stack() {
        n_head = null;
    }

    public Stack(Node n_head) {
        this.n_head = n_head;
    }

    public boolean isEmpty() {
        return (n_head == null);
    }

    public void clear() {
        n_head = null;
    }

    public void push(float score) {
        if (isEmpty()) {
            System.out.println("The stack is empty");
        } else {
            n_head = new Node(score, n_head);
            System.out.println("Succesful pushion");
        }
    }

    public float pop() {
        if (isEmpty()) {
            System.out.println("The stack is empty");
        }

        float x = n_head.gpa_score;
        n_head = n_head.p_Next;
        System.out.println("Succesful pop");
        return x;
    }

    public float top() {
        if (isEmpty()) {
            System.out.println("The stack is empty");

        }
        System.out.println(n_head.gpa_score);
        return n_head.gpa_score;
    }

    public void show() {
        if (this.isEmpty()) {
            System.out.println("The stack is empty");
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
        menu.addNewOption("1-Push");
        menu.addNewOption("2-Pop");
        menu.addNewOption("3-Top");
        menu.addNewOption("4-Clear");
        menu.addNewOption("5-Show");
        menu.addNewOption("6-Exit");
        int choice;
        boolean cont = true;
        Stack sList = new Stack();

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    float input = (float) Utils.getADouble("Enter a head: ", "A head is required");
                    this.push(input);
                    break;

                case 2:

                    this.pop();
                    break;

                case 3:
                    this.top();
                    break;

                case 4:
                    this.clear();
                    break;
                case 5:
                    this.show();
                    break;
                case 6:
                    break;

            }
        } while (choice < 6 && choice >= 1);

    }
}
