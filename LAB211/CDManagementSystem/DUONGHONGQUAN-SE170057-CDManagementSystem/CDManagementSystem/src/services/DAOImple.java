/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import model.CDModel;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class DAOImple extends ObjectServices<CDModel> {

    private static final DAOImple instance = new DAOImple();

    public static DAOImple getInstance() {
        return instance;
    }

    public DAOImple() {
        super("CD.dat");
    }

    public void init() {
        CDServices.getInstance().setDao(this);
    }

    @Override
    protected CDModel parseString(String stringObject) {
        CDModel obj = new CDModel();
        obj.parseString(stringObject);
        return obj;
    }

}
