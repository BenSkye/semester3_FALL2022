/**
 * Class for account
 */
package data;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import list.AccountList;

import util.Mytools;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public final class Account {

    private String accName; //ID
    private String pwd; //password
    private String role;

    //constructor
    /**
     *
     */
    public Account() {
        checkAcc();
    }

    public Account(String accName, String pwd, String role) {
        this.accName = accName;
        this.pwd = pwd;
        this.role = role;
    }

    //Getter
    public String getAccName() {
        return accName;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return accName + pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * Function check () Log in to the account that has been granted before and
     * check if the account you just entered is in the list of previous levels
     * or not.If not enter until correct
     *
     *
     */
    public void checkAcc() {
        AccountList accList = new AccountList();

        try {
            accList.loadFromFile("account.txt");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);

        }
        System.out.println("Welcome to Hospital Management System!");

        do {
            do {
                accName = Mytools.getString("Enter your acc name: ", "Acc name is required");
                if (accList.searchingName(this) == false) {
                    System.out.println("Account name does not exist");
                }
            } while (accList.searchingName(this) == false);
            do {
                pwd = Mytools.getString("Enter your password: ", "Password is required");
                if (accList.searchingPwd(this) == false) {
                    System.out.println("Wrong password!");
                }
            } while (accList.searchingPwd(this) == false);
            do {
                role = Mytools.getString("Enter your role: ", "Role is required");
                if (accList.searchingRole(this) == false) {
                    System.out.println("Role does not exist");
                }
            } while (accList.searchingRole(this) == false);

            if (accList.checkAcc(this) == false) {
                System.out.println("Account does not exist!");
            }
//                accName = Mytools.getString("Enter your acc name: ", "Acc name is required");
//                pwd = Mytools.getString("Enter your password: ", "Password is required");
//                role = Mytools.getString("Enter your role: ", "Role is required");

        } while (accList.checkAcc(this) == false);

    }

    public void showInfo() {
        System.out.printf("|%-15s|%-25s|%-14s|\n", accName, pwd, role);

    }

}
