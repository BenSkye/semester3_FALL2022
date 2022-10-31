/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import managementsystem.Login;
import controller.Controller;
import util.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ViewServices {

    public static final ViewServices instance = new ViewServices();

    public static ViewServices getInstance() {
        return instance;
    }

    public ViewServices() {

    }

    public void viewOfProduct() {
        ViewOfProduct.getInstance().printAllProduct();
    }

    public void viewOfProductByCondition() {
        ViewOfProduct.getInstance().printAllProductByCondition();
    }

    public void viewOfUser() {
        ViewOfUser.getInstance().printAllUser();
    }

    /**
     * Function to show menu of product
     */
    public void menuOfProduct() {

        int choiceP1;

        Menu menuP = new Menu("PRODUCT MANAGEMENT SYSTEM");
        menuP.addNewOption("1-Add product");
        menuP.addNewOption("2-Remove product");
        menuP.addNewOption("3-Update product");
        menuP.addNewOption("4-Search product by product name");
        menuP.addNewOption("5-Show product list");
        menuP.addNewOption("6-Check exist product");
        menuP.addNewOption("7-Save product list to file");
        menuP.addNewOption("8-Exit");

        do {

            menuP.printMenu();

            choiceP1 = menuP.getChoice();
            switch (choiceP1) {
                case 1:
                    Controller.getInstance().addProduct();
                    Controller.getInstance().saveProductFile();

                    break;
                case 2:
                    Controller.getInstance().removeProduct();
                    Controller.getInstance().saveProductFile();

                    break;
                case 3:
                    Controller.getInstance().updateProduct();
                    Controller.getInstance().saveProductFile();

                    break;
                case 4:
                    Controller.getInstance().filterByNameProduct();
                    break;
                case 5:
                    Controller.getInstance().printAllProductByCondition();
                    break;
                case 6:
                    Controller.getInstance().checkProductExist();

                    break;
                case 7:
                    Controller.getInstance().saveProductFile();
                    break;

                case 8:
                    break;

            }

        } while (choiceP1 != 8);
    }

    /**
     * Function to show menu of user
     *
     */
    public void menuOfUser() {
        int choiceP1;
        Menu menuP = new Menu("USER MANAGEMENT SYSTEM");
        menuP.addNewOption("1-Add user");
        menuP.addNewOption("2-Remove user");
        menuP.addNewOption("3-Change password");
        menuP.addNewOption("4-Filter by name");
        menuP.addNewOption("5-Show user list");
        menuP.addNewOption("6-Save user list to file");
        menuP.addNewOption("7-Exit");
        do {

            menuP.printMenu();
            choiceP1 = menuP.getChoice();
            switch (choiceP1) {
                case 1:
                    Controller.getInstance().addUser();
                    Controller.getInstance().saveUserFile();

                    break;
                case 2:
                    Controller.getInstance().removeUser();
                    Controller.getInstance().saveUserFile();

                    break;
                case 3:
                    Controller.getInstance().changePassword();
                    Controller.getInstance().saveUserFile();

                    break;
                case 4:
                    Controller.getInstance().filterByNameUser();
                    break;
                case 5:
                    Controller.getInstance().printAllUser();
                    break;

                case 6:
                    Controller.getInstance().saveUserFile();
                    break;

                case 7:
                    break;

            }

        } while (choiceP1 != 7);
    }

    public void menuForAdmin() {
        int choicead;

        Menu menuad = new Menu("PRODUCT MANAGEMENT SYSTEM");
        menuad.addNewOption("1-User management");
        menuad.addNewOption("2-Product management");
        menuad.addNewOption("3-Quit");
        do {
            menuad.printMenu();
            choicead = menuad.getChoice();
            switch (choicead) {
                case 1:
                    menuOfUser();
                    break;
                case 2:
                    menuOfProduct();
                    break;
                case 3:
                    if (Utils.isQuit()) {
                        System.out.println("Good bye !");
                        System.exit(0);
                    }
                    break;

            }
        } while (true);
    }

    public void menuForUser() {
        int choicep;

        Menu menuP = new Menu("PRODUCT MANAGEMENT SYSTEM");
        menuP.addNewOption("1-Add product");
        menuP.addNewOption("2-Remove product");
        menuP.addNewOption("3-Update product");
        menuP.addNewOption("4-Search product by product name");
        menuP.addNewOption("5-Show product list");
        menuP.addNewOption("6-Check exist product");
        menuP.addNewOption("7-Save product list to file");
        menuP.addNewOption("8-Exit");
        do {
            menuP.printMenu();
            choicep = menuP.getChoice();
            switch (choicep) {
                case 1:
                    Controller.getInstance().addProduct();
                    Controller.getInstance().saveProductFile();

                    break;
                case 2:
                    Controller.getInstance().removeProduct();
                    Controller.getInstance().saveProductFile();

                    break;
                case 3:
                    Controller.getInstance().updateProduct();
                    Controller.getInstance().saveProductFile();

                    break;
                case 4:
                    Controller.getInstance().filterByNameProduct();
                    break;
                case 5:
                    Controller.getInstance().printAllProduct();
                    break;
                case 6:
                    Controller.getInstance().checkProductExist();

                    break;
                case 7:
                    Controller.getInstance().saveProductFile();
                    break;

                case 8:
                    if (Utils.isQuit()) {
                        System.out.println("Good bye!");
                        System.exit(0);
                    }
                    break;

            }
        } while (true);

    }

    public void menuMain() {
        int choice, choice1;
        boolean cont = true;
        Menu main = new Menu("PRODUCT MANAGEMENT SYSTEM");
        main.addNewOption("1-For staff");
        main.addNewOption("2-For customer");
        main.addNewOption("3-Exit");

        Menu submenu = new Menu("PRODUCT MANAGEMENT SYSTEM");
        submenu.addNewOption("1-Print all product");
        submenu.addNewOption("2-Filter by name of product");
        submenu.addNewOption("3-Exit");

        do {
            main.printMenu();
            choice = main.getChoice();
            switch (choice) {
                case 1:
                    Login login = Login.getInstance();

                    if (login.getCurrentUser().isADMIN()) {
                        menuForAdmin();

                    } else if (login.getCurrentUser().isUser()) {
                        menuForUser();

                    }

                    break;
                case 2:
                    do {
                        submenu.printMenu();
                        choice1 = submenu.getChoice();
                        switch (choice1) {
                            case 1:
                                Controller.getInstance().printAllProductByCondition();
                                break;
                            case 2:
                                Controller.getInstance().filterByNameProduct();
                                break;
                            case 3:
                                break;
                        }
                    } while (choice1 != 3);
                    break;
                case 3:
                    if (Utils.isQuit()) {
                        cont = false;
                        System.out.println("Good bye !");
                    }

                    break;
            }
        } while (cont);

    }

}
