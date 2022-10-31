/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;

/**
 *
 * @author Administrator
 */
public class ViewServices {

    public static final ViewServices instance = new ViewServices();

    public static ViewServices getInstance() {
        return instance;
    }

    public ViewServices() {

    }

    public void viewOfCD() {
        ViewOfCD.getInstance().printAllCD();
    }

    public void menuOfCD() {

        int choiceP1;

        Menu menuP = new Menu("CD MANAGEMENT SYSTEM");
        menuP.addNewOption("1-Add CD");
        menuP.addNewOption("2-Remove CD");
        menuP.addNewOption("3-Update CD");
        menuP.addNewOption("4-Search CD by CD title");
        menuP.addNewOption("5-Show CD list");
        menuP.addNewOption("6-Save product list to file");
        menuP.addNewOption("7-Exit");

        do {

            menuP.printMenu();

            choiceP1 = menuP.getChoice();
            switch (choiceP1) {
                case 1:
                    Controller.getInstance().addCD();
                    Controller.getInstance().saveUserFile();

                    break;
                case 2:
                    Controller.getInstance().removeCD();
                    Controller.getInstance().saveUserFile();

                    break;
                case 3:
                    Controller.getInstance().updateCD();
                    Controller.getInstance().saveUserFile();

                    break;
                case 4:
                    Controller.getInstance().filterByTitle();
                    break;
                case 5:
                    Controller.getInstance().printAllCD();
                    break;
                case 6:
                    Controller.getInstance().saveUserFile();

                    break;
              
                case 7:
                    break;

            }

        } while (choiceP1 != 7);
    }
}
