package com.fptuni.csd201.lib.StringBinaryTree;

public class Node {
   private String info;  
   private Node left , right;

    public Node(String info) {
        this.info = info;
        left = null;
        right = null;
    }

    public Node() {
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
   
}
