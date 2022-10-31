/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Administrator
 */
public class CDEntity {

    private String CollectionName;
    private String CDType;
    private String title;
    private double unitPrice;
    private String ID;
    private String publishingYear;

    public CDEntity(String CollectionName, String CDType, String title, double unitPrice, String ID, String publishingYear) {
        this.CollectionName = CollectionName;
        this.CDType = CDType;
        this.title = title;
        this.unitPrice = unitPrice;
        this.ID = ID;
        this.publishingYear = publishingYear;
    }

    public String getCollectionName() {
        return CollectionName;
    }

    public void setCollectionName(String CollectionName) {
        this.CollectionName = CollectionName;
    }

    public String getCDType() {
        return CDType;
    }

    public void setCDType(String CDType) {
        this.CDType = CDType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(String publishingYear) {
        this.publishingYear = publishingYear;
    }

    @Override
    public String toString() {
        return "CDEntity{" + "CollectionName=" + CollectionName + ", CDType=" + CDType + ", title=" + title + ", unitPrice=" + unitPrice + ", ID=" + ID + ", publishingYear=" + publishingYear + '}';
    }
    
}
