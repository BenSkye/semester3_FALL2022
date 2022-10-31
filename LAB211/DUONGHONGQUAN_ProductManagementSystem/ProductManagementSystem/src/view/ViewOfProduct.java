/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.Controller;
import java.util.Collections;
import java.util.Comparator;
import model.ProductModel;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ViewOfProduct {

    public static final ViewOfProduct instance = new ViewOfProduct();

    public static ViewOfProduct getInstance() {
        return instance;
    }

    public ViewOfProduct() {

    }

    public void printAllProduct() {

        if (Controller.getInstance().getProductList().isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
            System.out.println("| Product ID    |      Product  Name      |   Unit Price  |    Quantity    |     Status     |");
            System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
           Comparator IDBalance = (Comparator<ProductModel>) (ProductModel o1, ProductModel o2) -> o1.getId().compareToIgnoreCase(o2.getId());
           Collections.sort(Controller.getInstance().getProductList(), IDBalance);
            for (int i = 0; i < Controller.getInstance().getProductList().size(); i++) {
                Controller.getInstance().getProductList().get(i).show();
            }
            System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");

        }

    }

    public void printAllProductByCondition() {

        if (Controller.getInstance().getProductList().isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+-------------------------+---------------+----------------+----------------+");
            System.out.println("|      Product  Name      |   Unit Price  |    Quantity    |     Status     |");
            System.out.println("+-------------------------+---------------+----------------+----------------+");

            Collections.sort(Controller.getInstance().getProductList());
//            Collections.sort(Controller.getInstance().getProductList());

            for (int i = 0; i < Controller.getInstance().getProductList().size(); i++) {
                Controller.getInstance().getProductList().get(i).showWithCondition();
            }
            System.out.println("+-------------------------+---------------+----------------+----------------+");

        }

    }

}
