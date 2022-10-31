/**
 * Class for DepartmentList
 */
package list;

import data.Department;
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
public class DepartmentList extends ArrayList<Department> {

    public static final String SEPARATOR = ",";
    public static final String DEPARTMENT_FORMAT = "A\\d{2}";

    public DepartmentList() {
        super();
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
                        String departmentID = stk.nextToken().trim().toUpperCase();
                        String name = stk.nextToken().trim().toUpperCase();
                        String createDate = stk.nextToken().trim().toUpperCase();
                        String lastUpdateDate = stk.nextToken().trim().toUpperCase();
                        if (departmentID.matches(DEPARTMENT_FORMAT) == false
                                || departmentID.length() == 0 || departmentID.isEmpty()
                                || name.isEmpty() || name.length() == 0
                                || Mytools.isTrueDate(createDate) == false
                                || (Mytools.isTrueDate(lastUpdateDate) == false)) {
                            return false;
                        } else {
                            this.add(new Department(departmentID, name, createDate, lastUpdateDate));
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
                    pw.println(x.getDepartmentID() + SEPARATOR + x.getName() + SEPARATOR + x.getCreateDate() + SEPARATOR + x.getLastUpdateDate());
                });
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }

    /**
     * Function deleteFile function used to delete files
     *
     * @param filename: name of file
     * @return true or false
     */
    public boolean deleteFile(String filename) {
        if (this.isEmpty()) {
            return false;
        }
        try {
            File myObj = new File(filename);
            if (myObj.delete()) {
                System.out.println("Deleted the file: " + myObj.getName());
            } else {
                System.out.println("Failed to delete the file.");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return true;
    }

    /**
     * Function searchID function that searches by id and returns the position
     * of the id in the list
     *
     * @param ID
     * @return i: location
     */
    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getDepartmentID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Function searchingID Function that searches by id and returns true and
     * false when found
     *
     * @param ID
     * @return true/false
     */
    public boolean searchingID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getDepartmentID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function search function that searches by id and returns object
     */
    public void search() {
        String inputID;
        inputID = Mytools.getID("Enter Department ID (AXX)(X stands for a digit): ",
                "The Department ID is required", DEPARTMENT_FORMAT);
        int index = this.searchID(inputID);
        if (index < 0) {
            System.out.println("No Department has this ID");
        } else {
            System.out.println("+---------------+-------------------------+--------------+----------------+");
            System.out.println("| Department ID |          Name           | Create Date  |Last Update Date|");
            System.out.println("+---------------+-------------------------+--------------+----------------+");
            this.get(index).showInfo();
        }
    }

    /**
     * Function addDepartment
     *
     */
    public void addDepartment() {

        String departmentID;
        String name;
        String createDate;
        String lastUpdateDate;
        int pos;
        System.out.println("This is a list of Department!");
        this.listDepartments();
        do {
            departmentID = Mytools.getID("Enter Department ID (AXX)(X stands for a digit): ",
                    "The Department ID is required", DEPARTMENT_FORMAT);
            pos = searchID(departmentID);
            if (pos >= 0) {
                System.out.println("The Department ID already exists. "
                        + "Input another one!");
            }
        } while (pos > 0);

        name = Mytools.getString("Enter Department name: ",
                "The Department name is required!");

        do {
            createDate = Mytools.getDate("Enter create date "
                    + "(The format date is dd/MM/YYYY): ",
                    "Invalid date. Please enter again!");
        } while (Mytools.isTrueDate(createDate) == false
                || Mytools.isValidCreateDate(Mytools.getNowDate(), createDate) == false);

        do {
            lastUpdateDate = Mytools.getDate("Enter last update date (The format date is dd/MM/YYYY): ",
                    "Invalid date. Please enter again!");
        } while (Mytools.isTrueDate(lastUpdateDate) == false
                || Mytools.isValidLastUpdate(createDate, lastUpdateDate) == false);
        Department newdepartment = new Department(departmentID, name, createDate, lastUpdateDate);
        this.add(newdepartment);
        System.out.println("A department profile is added sucessfully");

    }

    /**
     * Function: updateDepartment only update name,createDate,lastUpdateDate
     *
     */
    public void updateDepartment() {
        int position;
        System.out.println("This is list of department. "
                + "Which ID do you want to update?");
        this.listDepartments();
        position = searchID(Mytools.getID("Enter Department ID (AXX)(X stands for a digit): ",
                "The Department ID is required", DEPARTMENT_FORMAT));
        System.out.println("------------------------------------");

        if (position < 0) {
            System.out.println("Not found!");
        } else {
            String name;
            String createDate;
            String lastUpdateDate;
            Menu menu = new Menu("Update");
            menu.addNewOption("1-The Name");
            menu.addNewOption("2-The create date");
            menu.addNewOption("3-The current last update date");
            menu.addNewOption("4-Exit");
            int choice;
            do {
                menu.printMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:

                        System.out.println("The current department name is "
                                + this.get(position).getName());
                        name = Mytools.getString("Enter the new name: ",
                                "The new name is required!");
                        this.get(position).setName(name);
                        System.out.println("The department info is updated successfully!");
                        this.listDepartments();

                        break;
                    case 2:
                        do {
                            System.out.println("The current create date is "
                                    + this.get(position).getCreateDate());
                            createDate = Mytools.getDate("Enter the new create "
                                    + "date (The format date is dd/MM/YYYY): ",
                                    "Invalid date. Please enter again!");

                            this.get(position).setCreateDate(createDate);

                        } while (Mytools.isTrueDate(createDate) == false
                                || Mytools.isValidCreateDate(Mytools.getNowDate(), createDate) == false);
                        System.out.println("The department info is updated successfully!");
                        this.listDepartments();

                        break;
                    case 3:
                        do {
                            System.out.println("The current last update date is "
                                    + this.get(position).getLastUpdateDate());
                            lastUpdateDate = Mytools.getDate("Enter the new update date (The format date is dd/MM/YYYY): ",
                                    "Invalid date. Please enter again!");

                            this.get(position).setLastUpdateDate(lastUpdateDate);
                            createDate = this.get(position).getCreateDate();
                        } while (Mytools.isTrueDate(lastUpdateDate) == false
                                || Mytools.isValidLastUpdate(createDate, lastUpdateDate) == false);
                        System.out.println("The department info is updated successfully!");
                        this.listDepartments();

                        break;
                    case 4:
                        break;

                }
            } while (choice != 4);

        }
    }

    /**
     * remove department following id
     *
     */
    public void removeDepartment() {
        String id;
        int pos;
        System.out.println("This is list of department."
                + " Which ID do you want to remove?");
        this.listDepartments();
        id = Mytools.getID("Input Department ID (AXX)(X stands for a digit): ",
                "Department id is required!", DEPARTMENT_FORMAT);
        pos = searchID(id);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {

            System.out.println("This is Department you want to remove ?");
            this.get(pos).showInfo();
            String quit = Mytools.getString("Do you want to quit (Y/N) ?",
                    "Yes (Y) or No(N) ?");

            if (quit.equalsIgnoreCase("y")) {
                this.remove(pos);
                System.out.println("The selected Department is removed successfully!");
                this.listDepartments();

            } else {
                System.out.println("The selected Department isn't removed !");

            }

        }
    }

    /**
     * show the list of department
     */
    public void listDepartments() {
        if (this.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+---------------+-------------------------+--------------+----------------+");
            System.out.println("| Department ID |          Name           | Create Date  |Last Update Date|");
            System.out.println("+---------------+-------------------------+--------------+----------------+");
            if (this.isEmpty()) {
                System.out.println("The list is empty!");
            }
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfo();
            }
        }
    }

    public void function() {
        int choiceM1;
        Menu menuM1 = new Menu("Department's Menu ");
        menuM1.addNewOption("1-Add Department");
        menuM1.addNewOption("2-Remove Department");
        menuM1.addNewOption("3-Update Department");
        menuM1.addNewOption("4-Search Department");
        menuM1.addNewOption("5-Show Department List");
        menuM1.addNewOption("6-Exit");
        do {
            menuM1.printMenu();
            choiceM1 = menuM1.getChoice();
            switch (choiceM1) {
                case 1:
                    this.addDepartment();
                    this.saveToFile("departmentFile.txt");
                    break;
                case 2:
                    this.removeDepartment();
                    this.saveToFile("departmentFile.txt");
                    break;
                case 3:
                    this.updateDepartment();
                    this.saveToFile("departmentFile.txt");

                    break;
                case 4:
                    this.search();
                    break;
                case 5:
                    this.listDepartments();
                    break;
                case 6:
                    break;
            }

        } while (choiceM1 >= 1 && choiceM1 < 6);

    }
}
