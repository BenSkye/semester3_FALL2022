/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import data.BSTree;
import util.Menu;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        BSTree tree = new BSTree();
        tree.loadFromFile("data_AVL.txt");

        int choice;
        Menu menu = new Menu("ASSGINMENT AVL TREE");
        menu.addNewOption("1-Insert student");
        menu.addNewOption("2-Search student");
        menu.addNewOption("3-Delete by merging student");
        menu.addNewOption("4-Delete by copy student");
        menu.addNewOption("5-Show student list");
        menu.addNewOption("6-Save to file");
        menu.addNewOption("7-Exit");

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {

                case 1:
                    tree.insertStudent();

                    break;
                case 2:
                    tree.searchStudent();
                    break;
                case 3:
                    tree.deleteStudentByMerging();
                    break;
                case 4:
                    tree.deleteStudentByCopy();
                    break;
                case 5:
                    tree.showStudentList();
                    break;
                case 6:
                    if (tree.saveToFile()) {
                        System.out.println("Save success!");
                    } else {
                        System.out.println("Save fail");
                    }

                    break;

                case 7:
                    System.out.println("Good bye");
                    break;
            }
        } while (choice != 7);
    }
}
