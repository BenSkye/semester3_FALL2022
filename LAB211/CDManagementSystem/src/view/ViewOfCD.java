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
public class ViewOfCD {

    public static final ViewOfCD instance = new ViewOfCD();

    public static ViewOfCD getInstance() {
        return instance;
    }

    public ViewOfCD() {

    }

    public void printAllCD() {
        System.out.println("There is/are " + Controller.getInstance().getCDList().countCD() + " in catalog");
        System.out.println("+-------------------------+---------------+-------------------------+----------------+----------------+----------------+");
        System.out.println("|    Collection name      |    CD Type    |          Title          |   Unit Price   |       ID       |  Publish year  |");
        System.out.println("+-------------------------+---------------+-------------------------+----------------+----------------+----------------+");
        for (int i = 0; i < Controller.getInstance().getCDList().size(); i++) {
            Controller.getInstance().getCDList().get(i).show();
        }
        System.out.println("+-------------------------+---------------+-------------------------+----------------+----------------+----------------+");

    }
}
