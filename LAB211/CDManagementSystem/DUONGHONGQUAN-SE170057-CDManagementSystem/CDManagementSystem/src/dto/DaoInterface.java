/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dto;

import java.util.ArrayList;
import model.IObject;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public interface DaoInterface<E extends IObject> {


    boolean load();

    boolean save();

    ArrayList<E> getDataList();

}
