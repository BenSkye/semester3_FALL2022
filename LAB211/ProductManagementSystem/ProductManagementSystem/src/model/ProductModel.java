/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.logging.Level;
import java.util.logging.Logger;
import util.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ProductModel implements IObject, Comparable<ProductModel> {

    public static final String NAME_FORMAT = "XXXXX (at least 5 character)";
    public static final String ID_FORMAT = "PXXX (X is digit)";

    public static final String ID_PATTERN = "P\\d{3}";
    public static final String NAME_PATTERN = ".{5,}";

    private static final int ATTRIBUTE_COUNT = 5;

    private String productID;
    private String productName;
    private double unitPrice;
    private int quantity;
    private String status;

    public ProductModel() {
    }

    /**
     * Constructor
     *
     * @param productID
     * @param productName
     * @param unitPrice
     * @param quantity
     * @param status
     */
    public ProductModel(String productID, String productName, double unitPrice, int quantity, String status) {
        setProductID(productID);
        setProductName(productName);
        setUnitPrice(unitPrice);
        setQuantity(quantity);
        setStatus(status);
    }

    /**
     * Getter and Setter
     *
     * @return
     */
    public String getProductID() {
        return productID;
    }

    public final void setProductID(String productID) {
        if (Utils.validateString(productID, ID_PATTERN, true) && Utils.validateID(productID)) {
            this.productID = productID;
        }
    }

    public String getProductName() {
        return productName;
    }

    public final void setProductName(String productName) {
        if (!productName.isEmpty()) {
            this.productName = productName;

        }
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public final void setUnitPrice(double unitPrice) {
        if (Utils.validateFloat(unitPrice, 0, 10000)) {
            this.unitPrice = unitPrice;
        }

    }

    public int getQuantity() {
        return quantity;
    }

    public final void setQuantity(int quantity) {
        if (Utils.validateInteger(quantity, 0, 1000)) {
            this.quantity = quantity;
        }
    }

    public String getStatus() {
        return status;
    }

    public final void setStatus(String status) {
        if (Utils.validateStatus(status) && !status.isEmpty()) {
            this.status = status;

        }
    }

    @Override
    public String getId() {
        return productID;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.productID);
        sb.append(Utils.SEPARATOR);
        sb.append(this.productName);
        sb.append(Utils.SEPARATOR);
        sb.append(this.unitPrice);
        sb.append(Utils.SEPARATOR);
        sb.append(this.quantity);
        sb.append(Utils.SEPARATOR);
        sb.append(this.status);
        return sb.toString();
    }

    /**
     * Show the product
     */
    @Override
    public void show() {
        System.out.printf("|%-15s|%-25s|%-15.1f|%-16d|%-16s|\n", productID, productName, unitPrice, quantity, status);

    }
  public void showWithCondition() {
        System.out.printf("|%-25s|%-15.1f|%-16d|%-16s|\n", productName, unitPrice, quantity, status);

    }

    /**
     * function to convert string to an object
     *
     * @param stringObject
     * @return setAttribute
     */
    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Utils.SEPARATOR));
        }
        return 0;
    }

    protected int getAttributeCount() {
        return ProductModel.ATTRIBUTE_COUNT;
    }

    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setProductID(attributes[idx++].trim());
            setProductName(attributes[idx++].trim());
            try {
                setUnitPrice(Double.parseDouble(attributes[idx++].trim()));
                setQuantity(Integer.parseInt(attributes[idx++].trim()));

            } catch (NumberFormatException ex) {
                Logger.getLogger(ProductModel.class.getName()).log(Level.SEVERE, null, ex);
            }

            setStatus((attributes[idx++].trim()));
        }
        return idx;
    }

    @Override
    public int compareTo(ProductModel product) {
        if (product.getQuantity() != this.getQuantity()) {
            return Integer.compare(product.getQuantity(), this.getQuantity());
        } else {
            return Double.compare(this.getUnitPrice(), product.getUnitPrice());
        }
    }
    

}
