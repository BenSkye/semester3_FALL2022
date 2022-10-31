/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Node {

    float gpa_score;
    Node p_Next;

    public Node() {
    }

    public Node(float x) {
        this(x, null);
    }

    public Node(float x, Node p) {
        gpa_score = x;
        p_Next = p;
    }

}
