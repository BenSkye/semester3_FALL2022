/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import data.Graph;
import util.Menu;

/**
 *
 * @author Administrator
 */
public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        int choice;
        Menu menu = new Menu("ASSIGNMENT ABOUT GRAPH");
        menu.addNewOption("1-Print and Traversals");
        menu.addNewOption("2-Compute Degree");
        menu.addNewOption("3-Alogrithm");
        menu.addNewOption("4-Save to file");
        menu.addNewOption("5-Exit");

        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    g.printAndTraversal();
                    break;
                case 2:
                    g.caculateDegree();

                    break;
                case 3:
                    g.executeAlogrithm();
                    
                    break;
                case 4:
                    if (g.saveToFile("data_Graph.txt")) {
                        System.out.println("Save success");
                    } else {
                        System.out.println("Save fail");
                    }

                    break;
                case 5:
                    System.exit(0);

            }
        } while (choice != 5);
    }

}
