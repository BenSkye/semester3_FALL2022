/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import Tools.Menu;
import Tools.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Menu m00 = new Menu("Assignment");
        m00.addNewOption("1-Student list");
        m00.addNewOption("2-Stack");
        m00.addNewOption("3-Queue");
        m00.addNewOption("4-Exit");
        Stu_List sList = new Stu_List();
        Stack stackList = new Stack();
        Queue queueList = new Queue();
        int choice;
        boolean cont = true;
        do {
            m00.printMenu();
            choice = m00.getChoice();
            switch (choice) {
                case 1:
                    sList.function();
                    break;
                case 2:
                    stackList.function();
                    break;
                case 3:
                    queueList.function();
                    break;
                case 4:
                    String quit = Utils.getString("Do you want to quit (Y/N) ?", "Yes (Y) or No(N) ?");
                    if (quit.equalsIgnoreCase("y")) {
                        cont = false;
                        System.out.println("Good bye !");

                    }
                    break;
            }
        } while (cont);
    }
}
