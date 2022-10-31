/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import controller.Controller;
import dto.UserDAO;
import model.UserModel;
import java.util.ArrayList;
import java.util.List;
import managementsystem.Login;

import util.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class UserServices extends ObjectServices<UserModel> implements UserDAO {

    private static final UserServices instance = new UserServices();

    public static UserServices getInstance() {
        return instance;
    }

    public UserServices(String filePath) {
        super(filePath);
    }

    public UserServices() {

    }

    /**
     * Function to add a user to the list
     *
     */
    @Override
    public void addUser() {
        if (Login.getInstance().getCurrentUser().isADMIN()) {
            String accName;
            String pwd;
            Controller.getInstance().printAllUser();
            accName = Utils.getID("Enter your acc name(UXXX(X is digit): ", "Acc name is required", UserModel.USER_PATTERN);
            pwd = Utils.getString("Enter your password: ", "Password is required");
            UserModel user = new UserModel(accName, pwd);
            this.add(user);

            System.out.println("A user is created sucessfully");
             Controller.getInstance().printAllUser();

        } else {
            System.out.println("You don't have permission");

        }

    }

    /**
     * Function removeUser() to remove user
     */
    @Override
    public void removeUser() {
        if (Login.getInstance().getCurrentUser().isADMIN()) {
            String accName;
            int pos;
            System.out.println("This is list of user."
                    + " Which ID do you want to remove?");
             Controller.getInstance().printAllUser();

            accName = Utils.getID("Enter your acc name(UXXX(X is digit)): ", "Acc name is required", UserModel.USER_PATTERN);

            pos = searchName(accName);
            System.out.println("------------------------------------");
            if (pos == -1) {
                System.out.println("Not found!!!");
            } else {

                System.out.println("This is account you want to remove ?");
                this.get(pos).show();
                String quit = Utils.getString("Do you want to quit (Y/N) ?",
                        "Yes (Y) or No(N) ?");

                if (quit.equalsIgnoreCase("y")) {
                    this.remove(pos);
                    System.out.println("The selected Account is removed successfully!");
                     Controller.getInstance().printAllUser();

                } else {
                    System.out.println("The selected Account isn't removed !");

                }

            }
        } else {
            System.out.println("You don't have permission");

        }

    }

    public int searchName(String accName) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getAccName().equalsIgnoreCase(accName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Function searchingName Function that searches by Name and returns true
     * and false when found
     *
     * @param acc
     *
     * @return true/false
     */
    public boolean searchingName(UserModel acc) {
        for (UserModel o : this) {
            if (acc.getAccName().equalsIgnoreCase(o.getAccName()) == true) {
                return true;
            }

        }
        return false;
    }

    /**
     * Function searchingPwd Function that searches by password and returns true
     * and false when found
     *
     * @param acc
     *
     * @return true/false
     */
    public boolean searchingPwd(UserModel acc) {
        for (UserModel o : this) {
            if (acc.getPwd().equalsIgnoreCase(o.getPwd()) == true) {
                return true;
            }

        }
        return false;
    }

    /**
     * Function changePassword() Function to changePassword when doctor forgot
     * password
     */
    @Override
    public void changePassword() {
        if (Login.getInstance().getCurrentUser().isADMIN()) {
            int position;
            System.out.println("This is list of user."
                    + " Which ID do you want to change password?");
             Controller.getInstance().printAllUser();
            position = searchName(Utils.getString("Enter your acc name(AXX or UXXX(X is digit)): ", "Acc name is required"));
            if (position < 0) {
                System.out.println("Not found!");
            } else {
                String pwd;

                System.out.println("The current password is " + this.get(position).getPwd());

                pwd = Utils.getString("Enter your password: ", "Password is required");

                this.get(position).setPwd(pwd);
                System.out.println("A password is changed sucessfully");

            }
        } else {
            System.out.println("You don't have permission");

        }

    }

    /**
     * function to convert string to an object
     *
     * @param stringObject
     * @return object
     */
    @Override
    protected UserModel parseString(String stringObject) {
        UserModel obj = new UserModel();
        obj.parseString(stringObject);
        return obj;
    }

    /**
     * function filterByName() that searches for a product by the name entered
     * from the keyboard
     */
    @Override
    public void filterByName() {
        if (Login.getInstance().getCurrentUser().isADMIN()) {
            String productName;
            productName = Utils.getString("Please enter product name(UXXX(X is digit): ", "Product Name is required!");
            showFilter(productName);
        } else {
            System.out.println("You don't have permission");

        }

    }

   

    /**
     * Function checkAcc() to check Is the account you just entered in the
     * allowed list?
     *
     * @param acc
     * @return true or false
     */
    public boolean isValid(UserModel acc) {
        for (UserModel o : this) {
            if (acc.getAccName().equalsIgnoreCase(o.getAccName()) && acc.getPwd().equalsIgnoreCase(o.getPwd())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void showFilter(String name) {
        List<UserModel> filterList = filter(name);
        if (filterList.isEmpty()) {
            System.out.println("No user has this name");

        } else {
            System.out.println("+-------------------------+-------------------------+");
            System.out.println("|       Acc name          |         Password        |");
            System.out.println("+-------------------------+-------------------------+");
            for (UserModel pro : filterList) {
                pro.show();
            }
            System.out.println("+-------------------------+-------------------------+");

        }

    }

    @Override
    public List<UserModel> filter(String name) {
        List<UserModel> filterList = new ArrayList<>();
        try {
            for (UserModel pro : this) {
                if (pro.getAccName().contains(name)) {
                    filterList.add(pro);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No product has this name");
        }
        return filterList;
    }

  

}
