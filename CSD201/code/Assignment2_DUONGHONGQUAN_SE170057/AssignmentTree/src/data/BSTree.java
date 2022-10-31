/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import util.Menu;
import util.Utils;

/**
 *
 * @author Administrator
 */
public final class BSTree {

    Node root;

    public BSTree() {
        root = null;

    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean loadFromFile(String filename) throws FileNotFoundException {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return false;
            }
            try ( FileReader fr = new FileReader(f)) {
                try ( BufferedReader br = new BufferedReader(fr)) {
                    String info;
                    while ((info = br.readLine()) != null) {
                        StringTokenizer stk = new StringTokenizer(info, " ");
                        Integer studentCode = Integer.parseInt(stk.nextToken().trim());
                        String name = stk.nextToken().trim().toUpperCase();
                        String gender = stk.nextToken().trim().toUpperCase();
                        Double gpa_score = Double.parseDouble(stk.nextToken().trim());
                        insert(new Student(studentCode, name, gender, gpa_score));
                    }
                    br.close();
                }
                fr.close();
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
        return true;
    }

    public boolean saveToFile() {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return false;
        }
        try {
            deleteFile();
            BFTForSaveFile(root);
        } catch (IOException e) {
            System.out.println(e);
        }
        return true;
    }

    private void BFTForSaveFile(Node p) throws IOException {
        if (p == null) {
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            visitForSave(q);
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
        }
    }

    private void visitForSave(Node p) throws IOException {
        String filename = "data_AVL.txt";
        if (p == null) {
            return;
        }

        try {
            try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                if (!Utils.validateStringText(p.info.toString())) {
                    writer.append(p.info.toString());
                    writer.append("\n");

                }

            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    private void deleteFile() {
        String filename = "data_AVL.txt";
        File f = new File(filename);
        f.delete();
    }

    public void visit(Node p) {
        if (p == null) {
            return;
        }
        System.out.println(p.info.toString() + "  ");
    }

//return a Node which has key = given key in the tree p
    private Node search(Node p, int key) {
        if (p == null) {
            System.out.println("There was no student with a Student Code entered! . ");
            return null;
        }
        if (p.info.getStudentCode() == key) {
            System.out.println("This is the student you want to search");
            return p;
        } else if (p.info.getStudentCode() > key) {
            return search(p.left, key);
        } else {
            return search(p.right, key);
        }
    }

    private void insert(Student x) {
        Node p = new Node(x);
        Node f = null, q = root;
        while (q != null) {
            if (q.info.getStudentCode() == x.getStudentCode()) {
                System.out.println("Key cannot be duplicated...");
                return;
            }
            if (q.info.getStudentCode() < x.getStudentCode()) {
                f = q;
                q = q.right;
            } else {
                f = q;
                q = q.left;
            }
        }
        if (f == null) {
            root = p;
        } else if (p.info.getStudentCode() > f.info.getStudentCode()) {
            f.right = p;
        } else {
            f.left = p;
        }
    }

    //preorder a tree
    private void preOrder(Node p) {
        if (p == null) {
            return;
        }
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }

    //postorder a tree
    private void postOrder(Node p) {
        if (p == null) {
            return;
        }
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }

    //inorder a tree
    private void inOrder(Node p) {
        if (p == null) {
            return;
        }
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }

    //bft
    private void BFT(Node p) {
        if (p == null) {
            return;
        }
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while (!m.isEmpty()) {
            Node q = (Node) m.dequeue();
            visit(q);
            if (q.left != null) {
                m.enqueue(q.left);
            }
            if (q.right != null) {
                m.enqueue(q.right);
            }
        }
    }

    //height of tree
   private int height(Node p) {
        if (p == null) {
            return 0;
        } else {
            int lDepth = height(p.left);//compute the depth of each subtree
            int rDepth = height(p.right);
            if (lDepth > rDepth) {
                return (lDepth + 1);//use the larger one
            } else {
                return (rDepth + 1);
            }
        }
    }

    private void deleteByCopy(int x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }
        //find f is father of p
        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.info.getStudentCode() > p.info.getStudentCode()) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
        //1.p has no child
        if (p.left == null && p.right == null) {
            if (f == null) {
                System.out.println("Delete by copy successfully!");
                root = null;
            } else if (f.left == p) {
                System.out.println("Delete by copy successfully!");

                f.left = null;
            } else {
                System.out.println("Delete by copy successfully!");

                f.right = null;
            }
        } //2.p has left child only
        else if (p.left != null && p.right == null) {
            if (f == null) {
                System.out.println("Delete by copy successfully!");

                root = p.left;
            } else if (f.left == p) {
                System.out.println("Delete by copy successfully!");

                f.left = p.left;
            } else {
                System.out.println("Delete by copy successfully!");

                f.right = p.left;
            }
        } //3.p has right child only
        else if (p.left == null && p.right != null) {
            if (f == null) {
                System.out.println("Delete by copy successfully!");

                root = p.right;
            } else if (f.left == p) {
                System.out.println("Delete by copy successfully!");

                f.left = p.right;
            } else {
                System.out.println("Delete by copy successfully!");

                f.right = p.right;
            }
        } //4.p has both child
        else if (p.left != null && p.right != null) {

            q = p.left;
            f = null;
            while (q.right != null) {
                f = q;
                q = q.right;
            }
            p.info = q.info;

            if (f == null) {
                System.out.println("Delete by copy successfully!");

                p.left = q.left;
            } else {
                System.out.println("Delete by copy successfully!");

                f.right = q.left;
            }
        }
    }

    private void deleteByMerging(int x) {
        Node p = search(root, x);
        if (p == null) {
            System.out.println("Key " + x + " does not exists, deletion failed");
            return;
        }

        Node f = null, q = root;
        while (q != p) {
            f = q;
            if (q.info.getStudentCode() > p.info.getStudentCode()) {
                q = q.left;
            } else {
                q = q.right;
            }
        }
        //1.p has no child
        if (p.left == null && p.right == null) {
            if (f == null) {
                System.out.println("Delete by merging successfully!");

                root = null;
            } else if (f.left == p) {
                System.out.println("Delete by merging successfully!");

                f.left = null;
            } else {
                System.out.println("Delete by merging successfully!");

                f.right = null;
            }
        } //2.p has left child only
        else if (p.left != null && p.right == null) {
            if (f == null) {
                System.out.println("Delete by merging successfully!");

                root = p.left;
            } else if (f.left == p) {
                System.out.println("Delete by merging successfully!");

                f.left = p.left;
            } else {
                System.out.println("Delete by merging successfully!");

                f.right = p.left;
            }
        } //3.p has right child only
        else if (p.left == null && p.right != null) {
            if (f == null) {
                System.out.println("Delete by merging successfully!");

                root = p.right;
            } else if (f.left == p) {
                System.out.println("Delete by merging successfully!");

                f.left = p.right;
            } else {
                System.out.println("Delete by merging successfully!");

                f.right = p.right;
            }
        } //4.p has both child
        else if (p.left != null && p.right != null) {

            q = p.left;
            Node t = null;
            while (q.right != null) {
                t = q;
                q = q.right;
            }
            Node rp = p.right;
            q.right = rp;
            if (f == null) {
                System.out.println("Delete by merging successfully!");

                root = p.left;
            } else if (f.left == p) {
                System.out.println("Delete by merging successfully!");

                f.left = p.left;
            } else {
                System.out.println("Delete by merging successfully!");

                f.right = p.left;
            }
        }
    }

    private void buildArray(ArrayList a, Node p) {
        if (p == null) {
            return;
        }
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }

    private void balance(ArrayList a, int f, int l) {
        if (f > l) {
            return;
        }
        int m = (f + l) / 2;
        Node p = (Node) a.get(m);
        insert(p.info);
        balance(a, f, m - 1);
        balance(a, m + 1, l);
    }

    public void balance(Node p) {
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int l = a.size(), f = 0;
        //create a new tree and insert all items from a to the BST
        BSTree t = new BSTree();
        t.balance(a, f, l - 1);
        root = t.root;
    }

    //---------------------
    public void insertStudent() {
        balance(root);
        String gender;
        String name;
        double gpa_score;
        int studentCode = Utils.getAnInteger("Please input a student code: ", "Student code is required!");
        do {
            name = Utils.getString("Input the name of student: ", "The name is required!");

        } while (!Utils.validateString(name));
        do {
            gender = Utils.getString("Input gender: ", "Gender is required!");

        } while (!Utils.validateGender(gender));
        do {
            gpa_score = Utils.getADouble("Input the gpa score: ", "The gpa score is required!");

        } while (!Utils.validateGpa(gpa_score));
        Student x = new Student(studentCode, name, gender, gpa_score);
        insert(x);

        saveToFile();
        balance(root);
        if (Utils.isContinuing()) {
            insertStudent();
        }
    }

    public void searchStudent() {

        int studentCode = Utils.getAnInteger("Please input a student code: ", "Student code is required!");
        Node x = search(root, studentCode);
        visit(x);
        if (Utils.isContinuing()) {
            searchStudent();
        }
    }

    public void deleteStudentByMerging() {

        int studentCode = Utils.getAnInteger("Please input a student code: ", "Student code is required!");
//        Node x = search(root, studentCode);
        deleteByMerging(studentCode);

        saveToFile();
        balance(root);
        if (Utils.isContinuing()) {
            deleteStudentByMerging();
        }

    }

    public void deleteStudentByCopy() {

        int studentCode = Utils.getAnInteger("Please input a student code: ", "Student code is required!");
//        Node x = search(root, studentCode);
        deleteByCopy(studentCode);
        saveToFile();
        balance(root);
        if (Utils.isContinuing()) {
            deleteStudentByCopy();
        }

    }

    public void showStudentList() throws IOException {
        Menu submenu = new Menu("Show student with");
        submenu.addNewOption("1-PreOrder");
        submenu.addNewOption("2-InOrder");
        submenu.addNewOption("3-PostOrder");
        submenu.addNewOption("4-Exit");

        int choice;
        do {
            submenu.printMenu();
            choice = submenu.getChoice();
            switch (choice) {
                case 1:
                    preOrder(root);
                    break;
                case 2:
                    inOrder(root);
                    break;
                case 3:
                    postOrder(root);
                    break;
            }
        } while (choice != 4);
    }
}
