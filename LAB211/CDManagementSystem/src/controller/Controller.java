/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.FileNotFoundException;
import services.CDServices;
import view.ViewServices;

/**
 *
 * @author Administrator
 */
public class Controller {

    public static final Controller instance = new Controller();

    public static Controller getInstance() {
        return instance;
    }
    private final String cdFilePath;
    private final CDServices services;

    private final ViewServices viewservices;

    /**
     * Constructor
     */
    private Controller() {
        cdFilePath = "CD.dat";

        services = CDServices.getInstance();

        viewservices = new ViewServices();
    }

    /**
     * function to load the product file into the list
     */
    private void loadCD() {
        services.load();
    }

    public void init() throws FileNotFoundException {
        loadCD();

    }

    public CDServices getCDList() {
        return services;
    }

    public void addCD() {
        this.viewservices.viewOfCD();
        this.services.addCD();
    }

    public void removeCD() {
        this.viewservices.viewOfCD();
        this.services.removeCD();
    }

    public void updateCD() {
        this.viewservices.viewOfCD();
        this.services.updateCD();
    }

    public void filterByTitle() {
        this.services.filterByTitle();
    }

    public void printAllCD() {
        this.viewservices.viewOfCD();
    }

    public void saveUserFile() {
        if (this.services.save()) {
            System.out.println("Save success");
        } else {
            System.out.println("Save fail!");
        }
    }

    public void showMenu() {
        this.viewservices.menuOfCD();
    }
}
