package com.fptuni.csd201.lib.StringBinaryTree;

import com.fptuni.csd201.lib.NodeBT;
import com.fptuni.csd201.lib.ObjectStackQueue.ObjectQueue;

public class StringBinaryTree {

    private Node root;

    public StringBinaryTree() {
        root = null;
    }

    public StringBinaryTree(Node p) {
        root = p;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void clear() {
        root = null;
    }

    private Node searchR(Node p, String x) {
        if (p == null) {
            return null;
        }

        if (x.equals(p.getInfo())) {
            return p;
        }

        if (x.compareTo(p.getInfo()) < 0) {

            return searchR(p.getLeft(), x);
        }
        if (x.compareTo(p.getInfo()) > 0) {

            return searchR(p.getRight(), x);
        }

        return null;
    }

    public Node search(String x) {
        return searchR(root, x);
    }

    private void insertR(Node p, String x) {
        if (p != null) {
            if (x.equals(p.getInfo())) {
                System.out.println("The key is duplicated.");
                return;
            }
        }

        if (p == root && root == null) {
            Node add = new Node(x);
            root = add;
            return;
        }

        if (p.getLeft() == null && x.compareTo(p.getInfo()) < 0) {
            Node add = new Node(x);
            p.setLeft(add);
            return;
        }
        if (p.getRight() == null && x.compareTo(p.getInfo()) > 0) {
            Node add = new Node(x);
            p.setRight(add);
            return;
        }
        if (p.getLeft() != null && x.compareTo(p.getInfo()) < 0) {
            p = p.getLeft();
            insertR(p, x);
            return;
        }
        if (p.getRight() != null && x.compareTo(p.getInfo()) > 0) {
            p = p.getRight();
            insertR(p, x);
            return;
        }

    }

    public void insert(String x) {
        insertR(root, x);
    }

    public void visit(Node p) {
        if (p == null) {
            return;
        }
        System.out.print(p.getInfo() + "  ");
    }

    public void breadth() {
        System.out.print("Breadth Traversal:  ");
        ObjectQueue list = new ObjectQueue();
        Node p = root;
        list.enqueue(p);
        while (list.isEmpty() == false) {
            p = (Node) list.dequeue();
            if (p.getLeft() != null) {
                list.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                list.enqueue(p.getRight());
            }
            visit(p);
        }
        System.out.println();
    }

    private void preOrderR(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrderR(p.getLeft());

        preOrderR(p.getRight());
    }

    public void preOrder() {
        System.out.print("Pre-Order Traversal:  ");
        preOrderR(root);
        System.out.println();
    }

    private void inOrderR(Node p) {

        if (p == null) {
            return;
        }

        inOrderR(p.getLeft());
        visit(p);
        inOrderR(p.getRight());
    }

    public void inOrder() {
        System.out.print("In-Order Traversal:  ");
        inOrderR(root);
        System.out.println();
    }

    private void postOrderR(Node p) {

        if (p == null) {
            return;
        }

        inOrderR(p.getLeft());
        inOrderR(p.getRight());
        visit(p);
    }

    public void postOrder() {
        System.out.print("Post-Order Traversal:  ");
        postOrderR(root);
        System.out.println();
    }

    private Node father(Node p) {
        if (p == root) {
            return null;
        }
        Node acc = root;
        Node f = null;
        while (acc != p) {
            f = acc;

            if (acc.getInfo().compareTo(p.getInfo()) < 0) {

                acc = acc.getRight();

            } else {

                acc = acc.getLeft();

            }

        }
        return f;

    }

    private Node leftNode(Node p) {
        if (p.getLeft() == null) {
            return null;
        }
        Node acc = p.getLeft();
        while (acc.getRight() != null) {
            acc = acc.getRight();
        }
        return acc;
    }

    private Node rightNode(Node p) {
        if (p.getRight() == null) {
            return null;
        }
        Node acc = p.getRight();
        while (acc.getLeft() != null) {
            acc = acc.getLeft();
        }
        return acc;
    }

    public void delete(String x) {
        Node key = search(x);
        if (key == null) {
            System.out.println("Key " + x + " doesn't exist in the binary tree.");
            return;
        }

        // Delete Leaf
        if (key.getLeft() == null && key.getRight() == null) {
            if (key == root) {
                root = null;
                return;
            }
            if (key == father(key).getRight()) {
                father(key).setRight(null);
                return;
            }
            if (key == father(key).getLeft()) {
                father(key).setLeft(null);
                return;
            }
        }
        // Delete node with left or right is null
        if (key.getLeft() == null && key.getRight() != null) {
            father(key).setRight(key.getRight());
            return;
        }
        if (key.getRight() == null && key.getLeft() != null) {
            father(key).setLeft(key.getLeft());
            return;
        }
        // Delete node with both left and right are not null
        String temp = leftNode(key).getInfo();
        delete(leftNode(key).getInfo());
        key.setInfo(temp);

    }

    private int heightR(Node p) {
        if (p == null) {
            return -1;
        }
        if (heightR(p.getLeft()) >= heightR(p.getRight())) {
            return heightR(p.getLeft()) + 1;
        } else {
            return heightR(p.getRight()) + 1;
        }

    }

    public int height() {
        return heightR(root);
    }

    private int nodeLevelR(Node p, String x) {
        StringBinaryTree subtree = new StringBinaryTree(p);
        if (subtree.search(x) == null) {
            return -1;
        }
        if (nodeLevelR(p.getLeft(), x) >= nodeLevelR(p.getRight(), x)) {
            return nodeLevelR(p.getLeft(), x) + 1;
        }
        return nodeLevelR(p.getRight(), x) + 1;

    }

    public int nodeLevel(String x) {
        return nodeLevelR(root, x);
    }

    private boolean isAVLTreeR(Node p) {
        if (p == null){return true;}
        if (Math.abs(heightR(p.getLeft()) - heightR(p.getRight())) >= 2) {
            return false;
        } 
    
        return isAVLTreeR(p.getLeft()) && isAVLTreeR(p.getRight());
        
    }
    public boolean isAVLTree(){
        return isAVLTreeR(root);
    }
}
