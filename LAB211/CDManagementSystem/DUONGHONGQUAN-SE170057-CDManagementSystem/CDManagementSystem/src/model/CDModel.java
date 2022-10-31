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
 * @author Administrator
 */
public final class CDModel implements IObject {

    private static final int ATTRIBUTE_COUNT = 6;

    public static final String ID_FORMAT = "C\\d{3}";

    private String CollectionName;
    private String CDType;
    private String title;
    private double unitPrice;
    private String ID;
    private String publishingYear;

    public CDModel(String CollectionName, String CDType, String title, double unitPrice, String ID, String publishingYear) {
        setCollectionName(CollectionName);
        setCDType(CDType);
        setTitle(title);
        setUnitPrice(unitPrice);
        setID(ID);
        setPublishingYear(publishingYear);
    }

    public CDModel() {
    }

    public String getCollectionName() {
        return CollectionName;
    }

    public final void setCollectionName(String CollectionName) {
        if (validateCollectionName(CollectionName)) {
            this.CollectionName = CollectionName;

        }
    }

    public String getCDType() {
        return CDType;
    }

    public final void setCDType(String CDType) {
        if (Utils.validateType(CDType)) {
            this.CDType = CDType;

        }
    }

    public String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        if (Utils.validateString(title)) {
            this.title = title;

        }
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public final void setUnitPrice(double unitPrice) {
//        if (Utils.validateDouble(unitPrice)) {
            this.unitPrice = unitPrice;

//        }
    }

    public String getID() {
        return ID;
    }

    public final void setID(String ID) {
        if (Utils.validateID(ID) && ID.matches(ID_FORMAT)) {
            this.ID = ID;

        }
    }

    public String getPublishingYear() {
        return publishingYear;
    }

    public final void setPublishingYear(String publishingYear) {
        if (Utils.validateDate(publishingYear)) {
            this.publishingYear = publishingYear;

        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.CollectionName);
        sb.append(Utils.SEPARATOR);
        sb.append(this.CDType);
        sb.append(Utils.SEPARATOR);
        sb.append(this.title);
        sb.append(Utils.SEPARATOR);
        sb.append(this.unitPrice);
        sb.append(Utils.SEPARATOR);
        sb.append(this.ID);
        sb.append(Utils.SEPARATOR);
        sb.append(this.publishingYear);

        return sb.toString();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void show() {
        System.out.printf("|%-25s|%-15s|%-25s|%-16.1f|%-16s|%-16s|\n", CollectionName, CDType, title, unitPrice, ID, publishingYear);
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
        return CDModel.ATTRIBUTE_COUNT;
    }

    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setCollectionName(attributes[idx++].trim());
            setCDType(attributes[idx++].trim());
            setTitle(attributes[idx++].trim());
            try {
                setUnitPrice(Double.parseDouble(attributes[idx++].trim()));

            } catch (NumberFormatException ex) {
                Logger.getLogger(CDModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            setID(attributes[idx++].trim());
            setPublishingYear(attributes[idx++].trim());

        }
        return idx;
    }

    private boolean validateCollectionName(String name) {

        if (name.equalsIgnoreCase("Game") || name.equalsIgnoreCase("Movie") || name.equalsIgnoreCase("Music")) {
            return true;
        }
        return false;
    }
}
