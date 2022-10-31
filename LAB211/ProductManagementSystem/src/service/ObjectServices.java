/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.IObject;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LAPTOP_HONGQUAN
 * @param <E>
 */
public abstract class ObjectServices<E extends IObject> extends ArrayList<E> {

    private String filePath = "Product.dat";

    public String getFilePath() {
        return filePath;
    }

    public final void setFilePath(String filePath) {
        if (filePath != null && !filePath.isEmpty()) {
            this.filePath = filePath;
        }
    }

    public ObjectServices() {
    }

    public ObjectServices(String filePath) {
        setFilePath(filePath);
    }

    /**
     * Function loadFromFile function to get data from file and pass it to array
     * list
     *
     * @return true or false
     */
    public boolean load() {

        try ( Scanner sc = new Scanner(new File(this.filePath))) {
            E obj;
            String data;
            while (sc.hasNextLine()) {
                data = sc.nextLine().trim();
                if (!data.isEmpty()) {
                    obj = parseString(data);
                    if (obj != null) {
                        add(obj);
                    }
                }
            }
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * Function saveToFile function get data from array list then get each
     * object line by line and save to file line by line
     *
     * @return true or false
     */
    public boolean save() {
        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (E e : this) {
                writer.append(e.toString());
                writer.append("\n");
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ObjectServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void showFilter(String name) {
        List<E> filterList = filter(name);
        for (E e : filterList) {
            e.show();
        }
    }


    public abstract List<E> filter(String id);

    protected abstract E parseString(String stringObject);
}
