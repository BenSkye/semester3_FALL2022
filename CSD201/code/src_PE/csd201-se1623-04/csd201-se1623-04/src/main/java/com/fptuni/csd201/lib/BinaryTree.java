/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.lib;

import com.fptuni.csd201.lib.ObjectStackQueue.ObjectQueue;

/**
 *
 * @author DUNGHUYNH
 */
public class BinaryTree {

    NodeBT root;

    public BinaryTree() {
        root = null;
    }

    //1. return true if a tree is empty, return false otherwise.
    public boolean isEmpty() {
        return root == null;
    }

    //2. clear a tree.
    public void clear() {
        root = null;
    }

    //3. Search a node having value x. `Return a reference to that node if found, return null otherwise.
    public NodeBT search(NodeBT p, int x) {
        if (p == null) {
            return null;
        }
        if (p.getInfo() == x) {
            return p;
        } else if (p.getInfo() > x) {
            return search(p.getLeft(), x);
        } else {
            return search(p.getRight(), x);
        }
    }

    //4. check if the key x does not exists in a tree then insert new node with value x into the tree.
    public void insert(int x) {
        if (root == null) {
            root = new NodeBT(x);
            return;
        }
        NodeBT f = null, p = root;
        while (p != null) {
            if (p.getInfo() == x) {
                System.out.println("the key " + x + " already exists, no insertion");
                return;
            }
            f = p;
            if (x < p.getInfo()) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        if (x < f.getInfo()) {
            f.setLeft(new NodeBT(x));
        } else {
            f.setRight(new NodeBT(x));
        }
    }

    public void visit(NodeBT p) {
        if (p == null) {
            return;
        }
        System.out.print(p.getInfo() + "  ");
    }

    //5. traverse a tree.
    public void breadth() {
        if (root == null) {
            return;
        }
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        NodeBT p;
        while (!q.isEmpty()) {
            p = (NodeBT) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            visit(p);
        }
    }

    //6. recursive preorder traverse of a tree.
    public void preOrder(NodeBT p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.getLeft());
        preOrder(p.getRight());
    }

    //7. recursive inorder traverse of a tree.
    public void inOrder(NodeBT p) {
        if (p == null) {
            return;
        }
        inOrder(p.getLeft());
        visit(p);
        inOrder(p.getRight());
    }

    //8. recursive postorder traverse of a tree.
    public void postOrder(NodeBT p) {
        if (p == null) {
            return;
        }
        postOrder(p.getLeft());
        postOrder(p.getRight());
        visit(p);
    }

    //9. count and return number of nodes in the tree.
    public int count() {
        if (root == null) {
            return 0;
        }
        int res = 0;
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        NodeBT p;
        while (!q.isEmpty()) {
            p = (NodeBT) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            res++;
        }
        return res;
    }

    //10. delete a node having value x.
    public void delete(int x) {
        NodeBT p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }

        //find f is father of p
        NodeBT f = null, q = root;
        while (q != p) {
            f = q;
            if (q.getInfo() > p.getInfo()) {
                q = q.getLeft();
            } else {
                q = q.getRight();
            }
        }

        //1.p has no child
        if (p.getLeft() == null && p.getRight() == null) {
            if (f == null) {
                root = null; //p is root
            } else if (f.getLeft() == p) {
                f.setLeft(null);
            } else {
                f.setRight(null);
            }
        } //2.p has left child only
        else if (p.getLeft() != null && p.getRight() == null) {
            if (f == null) {
                root = p.getLeft();
            } else if (f.getLeft() == p) {
                f.setLeft(p.getLeft());
            } else {
                f.setRight(p.getLeft());
            }
        } //3.p has right child only
        else if (p.getLeft() == null && p.getRight() != null) {
            if (f == null) {
                root = p.getRight();
            } else if (f.getLeft() == p) {
                f.setLeft(p.getRight());
            } else {
                f.setRight(p.getRight());
            }
        } //4.p has both child
        else if (p.getLeft() != null && p.getRight() != null) {
            //tim q la node lon nhat ben con trai cua p -> q la con phai nhat
            //cua con trai cua p
            q = p.getLeft();
            f = null;
            while (q.getRight() != null) {
                f = q;
                q = q.getRight();
            }
            p.setInfo(q.getInfo());
            //delete q
            if (f == null) {
                p.setLeft(q.getLeft());
            } else {
                f.setRight(q.getLeft());
            }
        }
    }

    //11. find and return the node with minimum value in the tree.
    public NodeBT min() {
        if (root == null) {
            return null;
        }
        NodeBT p = root;
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p;
    }

    //12. find and return the node with maximum value in the tree.
    public NodeBT max() {
        if (root == null) {
            return null;
        }
        NodeBT p = root;
        while (p.getRight() != null) {
            p = p.getRight();
        }
        return p;
    }

    //13. return the sum of all values in the tree.
    public int sum() {
        if (root == null) {
            return 0;
        }
        int sum = 0;
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        NodeBT p;
        while (!q.isEmpty()) {
            p = (NodeBT) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            sum += p.getInfo();
        }
        return sum;
    }

    //14. return the average of all values in the tree.
    public int avg() {
        return sum() / count();
    }

    //15. Write a function that returns the height of a binary tree.
    public int height(NodeBT p) {
        if (p == null) {
            return 0;
        } else {
            int lDepth = height(p.getLeft());//compute the depth of each subtree
            int rDepth = height(p.getRight());
            if (lDepth > rDepth) { //use the larger one
                return (lDepth + 1);
            } else {
                return (rDepth + 1);
            }
        }
    }

    //16. Write a function that returns the cost of the 
    //most expensive path from the root to a leaf node.
    public int treeCost(NodeBT x) {
        if (x == null) {
            return 0;
        } else {
            return x.getInfo() + Math.max(treeCost(x.getLeft()), treeCost(x.getRight()));
        }
    }

    //17. Write a function to determine whether a given binary tree is AVL or not.
    public boolean isBalanced(NodeBT p) {
        if (p == null) {
            return true;
        } else if (isBalanced(p.getLeft()) && isBalanced(p.getRight())
                && Math.abs(height(p.getLeft()) - height(p.getRight())) <= 1) {
            return true;
        }
        return false;
    }

    //18. What value does the following function return when called with each of the binary trees in question 3?
    int mystery(NodeBT x) {
        if (x == null) {
            return 0;
        } else {
            return Math.max(mystery(x.getLeft()), mystery(x.getRight()));
        }
    }

    //19. Write a function to determine whether a given binary tree is a heap.
    //heap min
    public boolean isHeap(NodeBT p, int i, int n) {
        if (p == null) {
            return true;
        }

        if (i >= n) {
            return false;
        }

        if ((p.getLeft() != null && p.getLeft().getInfo() <= p.getInfo())
                || (p.getRight() != null && p.getRight().getInfo() <= p.getInfo())) {
            return false;
        }

        return isHeap(p.getLeft(), 2 * i + 1, n) && isHeap(p.getRight(), 2 * i + 2, n);
    }
}
