/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fptuni.csd201.bookservice;

import com.fptuni.csd201.lib.ObjectStackQueue.ObjectQueue;

import com.fptuni.csd201.object.Book;

/**
 *
 * @author DUNGHUYNH
 */
public class BookServiceImpl implements BookService {

    private Book root;
    private Book[] list;

    public BookServiceImpl() {
        root = null;
        list = new Book[1000];
    }

    public BookServiceImpl(Book root) {
        this.root = root;
    }

    public Book getRoot() {
        return root;
    }

    public void setRoot(Book root) {
        this.root = root;
    }

    public Book[] getList() {
        return list;
    }

    public void setList(Book[] list) {
        this.list = list;
    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean addBook(Book book) {
        if (root == null) {
            root = book;
            return true;
        }
        Book f = null, p = root;
        while (p != null) {
            if (p.getCode().equals(book.getCode())) {
                return false;
            }
            f = p;
            if (book.getCode().compareTo(p.getCode()) < 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        if (book.getCode().compareTo(f.getCode()) < 0) {
            f.setLeft(book);
        } else {
            f.setRight(book);
        }
        return true;
    }

    @Override
    public void printBook(Book book) {
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        System.out.println(book.toString());
    }

    @Override
    public void showBook(int method) {
        if (method == 1) {
            showBookInOrder(root);
        } else {
            showBookBreadth();
        }
    }

    private void showBookInOrder(Book book) {
        if (book == null) {
            return;
        }
        showBookInOrder(book.getLeft());
        printBook(book);
        showBookInOrder(book.getRight());
    }

    public void showBookBreadth() {
        if (root == null) {
            return;
        }
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        Book p;
        while (!q.isEmpty()) {
            p = (Book) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            printBook(p);
        }
    }

    @Override
    public Book searchBookbyCode(String bookCode) {
        return search(root, bookCode);
    }

    private Book search(Book p, String bookCode) {
        if (p == null) {
            return null;
        }
        if (p.getCode().equals(bookCode)) {
            return p;
        } else if (p.getCode().compareTo(bookCode) > 0) {
            return search(p.getLeft(), bookCode);
        } else {
            return search(p.getRight(), bookCode);
        }
    }

    @Override
    public int countBook() {
        if (root == null) {
            return 0;
        }
        int res = 0;
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        Book p;
        while (!q.isEmpty()) {
            p = (Book) q.dequeue();
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

    private Book father(Book p) {
        if (p == root) {
            return null;
        }
        Book acc = root;
        Book f = null;
        while (acc != p) {
            f = acc;

            if (acc.getCode().compareTo(p.getCode()) < 0) {

                acc = acc.getRight();

            } else {

                acc = acc.getLeft();

            }

        }
        return f;

    }

    private Book leftNode(Book p) {
        if (p.getLeft() == null) {
            return null;
        }
        Book acc = p.getLeft();
        while (acc.getRight() != null) {
            acc = acc.getRight();
        }
        return acc;
    }

    private Book rightNode(Book p) {
        if (p.getRight() == null) {
            return null;
        }
        Book acc = p.getRight();
        while (acc.getLeft() != null) {
            acc = acc.getLeft();
        }
        return acc;
    }

    @Override
    public boolean removeBook(String bookCode) {
        Book p = searchBookbyCode(bookCode);
        if (p == null) {
            System.out.println("This book doesn't appear in the services.");
            return false;
        }
        
        Book f = father(p), q = root;
        
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
            q = p.getLeft();
            f = null;
            while (q.getRight() != null) {
                f = q;
                q = q.getRight();
            }
            
            p.setCode(q.getCode());
            p.setName(q.getName());
            p.setLended(q.getLended());
            p.setQuantity(q.getQuantity());
            p.setPrice(q.getPrice());
            //delete q
            if (f == null) {
                p.setLeft(q.getLeft());
            } else {
                f.setRight(q.getLeft());
            }
        }
        System.out.println("Remove book successfully.");
        return true;
    }

    @Override
    public void printAvailableBook() {
        printAvailableBookInOrder(root);
    }

    private void printAvailableBookInOrder(Book book) {
        if (book == null) {
            return;
        }
        printAvailableBookInOrder(book.getLeft());
        if (book.getLended() < book.getQuantity()) {
            printBook(book);
        }
        printAvailableBookInOrder(book.getRight());
    }

    private int bookLevel(Book p, String code) {
        BookServiceImpl subtree = new BookServiceImpl(p);
        if (subtree.searchBookbyCode(code) == null) {
            return -1;
        }
        if (bookLevel(p.getLeft(), code) >= bookLevel(p.getRight(), code)) {
            return bookLevel(p.getLeft(), code) + 1;
        }
        return bookLevel(p.getRight(), code) + 1;

    }

    private int bookLevel(String x) {
        return bookLevel(root, x);
    }

    public void display() {
        if (root == null) {
            return;
        }
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        Book p;
        while (!q.isEmpty()) {
            p = (Book) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }

            System.out.print("Code: " + p.getCode() + "   Name: " + p.getName() + "  Level: ");
            System.out.print(bookLevel(p.getCode()));
            System.out.print("   Left: ");
            if (p.getLeft() != null) {
                System.out.print(p.getLeft().getCode());
            } else {
                System.out.print("null");
            }
            System.out.print("   Right: ");
            if (p.getRight() != null) {
                System.out.print(p.getRight().getCode());
            } else {
                System.out.print("null");
            }
            System.out.println();

        }

    }

    private int bookPositon(Book book) {
        if (root == null) {
            return -1;
        }
        int res = -1;
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        Book p;
        while (!q.isEmpty()) {
            p = (Book) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            res++;
            if (p == book) {
                return res;
            }
        }
        return -1;
    }

    private Book bookbyPosition(int index) {
        if (root == null) {
            return null;
        }
        int res = -1;
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        Book p;
        while (!q.isEmpty()) {
            p = (Book) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            res++;
            if (res == index) {
                return p;
            }
        }
        return null;
    }

    public Book[] toArray() {

        if (countBook() == 0) {
            return null;
        }
        for (int i = 0; i < countBook(); i++) {
            Book newBook = new Book(bookbyPosition(i).getCode(), bookbyPosition(i).getName(), bookbyPosition(i).getLended(), bookbyPosition(i).getQuantity(), bookbyPosition(i).getPrice());
            list[i] = newBook;

        }
        if (countBook() == 1) {
            return list;
        }
        for (int i = 0; i < countBook() - 1; i++) {
            for (int j = i + 1; j < countBook(); j++) {
                if (list[i].getCode().compareTo(list[j].getCode()) > 0) {
                    Book temp = list[i];
                    list[i] = list[j];
                    list[j] = temp;
                }
            }
        }
        return list;
    }

    private void balancing(int min, int max) {

        if (max <= min) {
            return;
        }
        int midside = (max + min) / 2;
        int leftside = (midside - 1 + min) / 2;
        int rightside = (midside + 1 + max) / 2;
        if (midside != leftside && list[midside].getLeft() == null && list[leftside].getLeft() == null && list[leftside].getRight() == null) {
            list[midside].setLeft(list[leftside]);

        }
        if (midside != rightside && list[midside].getRight() == null && list[rightside].getLeft() == null && list[rightside].getRight() == null) {
            list[midside].setRight(list[rightside]);

        }
        balancing(min, midside - 1);
        balancing(midside + 1, max);
    }

    @Override
    public void balancing() {
        BookService copy = new BookServiceImpl();
        toArray();
        balancing(0, countBook() - 1);
        root = list[(countBook() - 1) / 2];
    }

    @Override
    public Book searchBookbyName(String name) {
        if (root == null) {
            return null;
        }
        ObjectQueue q = new ObjectQueue();
        q.enqueue(root);
        Book p;
        while (!q.isEmpty()) {
            p = (Book) q.dequeue();
            if (p.getLeft() != null) {
                q.enqueue(p.getLeft());
            }
            if (p.getRight() != null) {
                q.enqueue(p.getRight());
            }
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
