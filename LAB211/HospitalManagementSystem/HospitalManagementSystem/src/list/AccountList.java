/**
 * Class for AccountList
 */
package list;

import data.Account;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import ui.Menu;
import util.Mytools;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class AccountList extends ArrayList<Account> {

    public static final String SEPARATOR = ",";
    public static final String DOCTOR_FORMAT = "D\\d{2}";

    public AccountList() {

    }

    /**
     * Function loadFromFile function to get data from file and pass it to array
     * list
     *
     * @param filename: Name of file
     * @return true or false
     * @throws FileNotFoundException
     */
    public boolean loadFromFile(String filename) throws FileNotFoundException {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return false;
            }
            try ( FileReader fr = new FileReader(f)) {
                try ( BufferedReader br = new BufferedReader(fr)) {
                    String info;
                    while ((info = br.readLine()) != null) {
                        StringTokenizer stk = new StringTokenizer(info, SEPARATOR);
                        String accName = stk.nextToken().trim().toUpperCase();
                        String pwd = stk.nextToken().trim().toUpperCase();
                        String role = stk.nextToken().trim().toUpperCase();
                        if (accName.length() == 0 || accName.isEmpty()
                                || pwd.length() == 0 || pwd.isEmpty()
                                || role.length() == 0 || role.isEmpty()) {
                            return false;
                        } else {
                            this.add(new Account(accName, pwd, role));
                        }
                    }
                    br.close();
                }
                fr.close();
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }

        return true;
    }

    /**
     * Function saveToFile function get data from array list then get each
     * object line by line and save to file line by line
     *
     * @param filename: Name of file
     * @return true or false
     */
    public boolean saveToFile(String filename) {
        if (this.isEmpty()) {
            return false;
        }
        try {
            File f = new File(filename);
            try ( FileWriter fw = new FileWriter(f);  PrintWriter pw = new PrintWriter(fw)) {
                this.forEach((x) -> {
                    pw.println(x.getAccName() + SEPARATOR + x.getPwd() + SEPARATOR + x.getRole());
                });
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }

    public int searchName(String accName) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getAccName().equals(accName)) {
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
    public boolean searchingName(Account acc) {
        for (Account o : this) {
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
    public boolean searchingPwd(Account acc) {
        for (Account o : this) {
            if (acc.getPwd().equalsIgnoreCase(o.getPwd()) == true) {
                return true;
            }

        }
        return false;
    }

    /**
     * Function searchingRole Function that searches by Role and returns true
     * and false when found
     *
     * @param acc
     * @return true/false
     */
    public boolean searchingRole(Account acc) {
        for (Account o : this) {
            if (acc.getRole().equalsIgnoreCase(o.getRole()) == true) {
                return true;
            }

        }
        return false;
    }

    /**
     * Function addAccount() Function to add an account to log in to the system
     */
    public void addAccount() {
        String accName;
        String pwd;
        String role;
        accName = Mytools.getID("Enter your acc name: ", "Acc name is required", DOCTOR_FORMAT);
        pwd = Mytools.getString("Enter your password: ", "Password is required");
        role = Mytools.getString("Enter your role: ", "Role is required");
        Account acc = new Account(accName, pwd, role);
        this.add(acc);
        System.out.println("A account is created sucessfully");
    }

    /**
     * Function changePassword() Function to changePassword when doctor forgot
     * password
     */
    public void changePassword() {
        int position;

        position = searchName(Mytools.getID("Enter your acc name: ", "Acc name is required", DOCTOR_FORMAT));
        if (position < 0) {
            System.out.println("Not found!");
        } else {
            String pwd;

            System.out.println("The current password is " + this.get(position).getPwd());

            pwd = Mytools.getString("Enter your password: ", "Password is required");

            this.get(position).setPwd(pwd);
            System.out.println("A password is changed sucessfully");

        }
    }

    /**
     * Function checkAcc() to check Is the account you just entered in the
     * allowed list?
     *
     * @param acc
     * @return true or false
     */
    public boolean checkAcc(Account acc) {
        for (Account o : this) {
            if (acc.getAccName().equalsIgnoreCase(o.getAccName()) == true && acc.getPwd().equalsIgnoreCase(o.getPwd()) == true && acc.getRole().equalsIgnoreCase(o.getRole()) == true) {
                return true;
            }

        }
        return false;
    }

    /**
     * Function removeAccount() Function to removeAccount
     */
    public void removeAccount() {
        String accName;
        int pos;
        System.out.println("This is list of account."
                + " Which ID do you want to remove?");
        this.listAccount();
        accName = Mytools.getID("Enter Acc Name (DXX)(X stands for a digit: ",
                "The acc name is required", DOCTOR_FORMAT);

        pos = searchName(accName);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {

            System.out.println("This is account you want to remove ?");
            this.get(pos).showInfo();
            String quit = Mytools.getString("Do you want to quit (Y/N) ?",
                    "Yes (Y) or No(N) ?");

            if (quit.equalsIgnoreCase("y")) {
                this.remove(pos);
                System.out.println("The selected Account is removed successfully!");
                this.listAccount();

            } else {
                System.out.println("The selected Account isn't removed !");

            }

        }
    }

    /**
     * Show the list account
     */
    public void listAccount() {
        if (this.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+---------------+-------------------------+--------------+");
            System.out.println("| Acc name      |         Password        |    Role      |");
            System.out.println("+---------------+-------------------------+--------------+");
            if (this.isEmpty()) {
                System.out.println("The list is empty!");
            }
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfo();
            }
        }
    }

    public void function() {
        int choice;
        Menu menu = new Menu("Account Management");
        menu.addNewOption("1-Create Account");
        menu.addNewOption("2-Remove Account");
        menu.addNewOption("3-Changed Password");
        menu.addNewOption("4-Show List Account");
        menu.addNewOption("5-Exit");
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    this.addAccount();
                    this.saveToFile("account.txt");
                    break;
                case 2:
                    this.removeAccount();
                    this.saveToFile("account.txt");
                    break;
                case 3:
                    this.changePassword();
                    this.saveToFile("account.txt");
                    break;
                case 4:
                    this.listAccount();
                    break;
                case 5:
                    break;
            }
        } while (choice >= 1 && choice < 5);

    }

}
