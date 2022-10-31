/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fptuni.csd201.bookservice;

import com.fptuni.csd201.object.Book;

/**
 *
 * @author super
 */
public class test {

    public static void main(String[] args) {
        BookService service = new BookServiceImpl();
        Book c = new Book("C", "Book C", 14, 20, 200000);
        Book d = new Book("D", "Book D", 16, 20, 200000);
        Book a = new Book("A", "Book A", 10, 20, 200000);
        Book b = new Book("B", "Book B", 12, 20, 200000);
        Book e = new Book("E", "Book E", 16, 20, 200000);
        Book f = new Book("F", "Book F", 12, 20, 200000);
        Book g = new Book("G", "Book G", 12, 20, 200000);
        Book h = new Book("H", "Book H", 12, 20, 200000);
        Book k = new Book("K", "Book K", 12, 20, 200000);
        service.addBook(b);
        service.addBook(e);
        service.addBook(f);
        service.addBook(a);
        service.addBook(c);
        service.addBook(d);
        service.addBook(g);
        service.addBook(h);
        service.addBook(k);

        service.removeBook("E");
        System.out.println(service.searchBookbyName("Book E"));

    }
}
