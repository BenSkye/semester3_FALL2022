/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import managementsystem.Login;
import controller.Controller;
import java.util.Collections;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ViewOfUser {

    public static final ViewOfUser instance = new ViewOfUser();

    public static ViewOfUser getInstance() {
        return instance;
    }

    public ViewOfUser() {

    }

    /**
     * function that prints all user in the list
     *
     */
    public void printAllUser() {
        if (Login.getInstance().getCurrentUser().isADMIN()) {
            if (Controller.getInstance().getUserList().isEmpty()) {
                System.out.println("The list is empty!");
            } else {

                if (Controller.getInstance().getUserList().isEmpty()) {
                    System.out.println("The list is empty!");
                }
                System.out.println("+-------------------------+-------------------------+");
                System.out.println("|        User name        |        Password         |");
                System.out.println("+-------------------------+-------------------------+");
                Collections.sort(Controller.getInstance().getUserList());
                for (int i = 0; i < Controller.getInstance().getUserList().size(); i++) {
                    Controller.getInstance().getUserList().get(i).show();
                }
                System.out.println("+-------------------------+-------------------------+");

            }
        } else {
            System.out.println("You don't have permission");

        }

    }
}
