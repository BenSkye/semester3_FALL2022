/**
 * Class for DoctorList
 */
package list;

import data.Doctor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.Menu;

import util.Mytools;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class DoctorList extends ArrayList<Doctor> {

    public static final String SEPARATOR = ",";
    public static final String DOCTOR_FORMAT = "D\\d{2}";
    public static final String DEPARTMENT_FORMAT = "A\\d{2}";

    public DoctorList() {
        super();
    }

    /**
     * Function loadFromFile Pass object data line by line to list
     *
     * @param filename
     * @return
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
                        String doctorID = stk.nextToken().trim().toUpperCase();
                        String name = stk.nextToken().trim().toUpperCase();
                        String sex = stk.nextToken().trim().toUpperCase();
                        String address = stk.nextToken().trim().toUpperCase();
                        String departmentID = stk.nextToken().trim().toUpperCase();
                        String createDate = stk.nextToken().trim().toUpperCase();
                        String lastUpdateDate = stk.nextToken().trim().toUpperCase();

                        if (doctorID.matches(DOCTOR_FORMAT) == false
                                || doctorID.length() == 0 || doctorID.isEmpty()
                                || name.length() == 0 || name.isEmpty()
                                || sex.length() == 0 || sex.isEmpty()
                                || address.length() == 0 || address.isEmpty()
                                || departmentID.matches(DEPARTMENT_FORMAT) == false
                                || departmentID.length() == 0 || departmentID.isEmpty()
                                || Mytools.isTrueDate(createDate) == false
                                || Mytools.isTrueDate(lastUpdateDate) == false) {
                            return false;
                        } else {
                            this.add(new Doctor(doctorID, name, sex, address, departmentID, createDate, lastUpdateDate));

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
                    pw.println(x.getDoctorID() + SEPARATOR + x.getName() + SEPARATOR
                            + x.getSex() + SEPARATOR + x.getAddress() + SEPARATOR
                            + x.getDepartmentID() + SEPARATOR + x.getCreateDate()
                            + SEPARATOR + x.getLastUpdateDate());
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
            if (this.get(i).getDoctorID().equals(ID)) {
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
            if (this.get(i).getDoctorID().equals(ID)) {
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
        inputID = Mytools.getID("Enter the ID to search (The format of id is DXX [X stands for a digit]): ",
                "The ID is required", DOCTOR_FORMAT);
        int index = this.searchID(inputID);
        if (index < 0) {
            System.out.println("No doctor has this ID");
        } else {
            System.out.println("+--------------+-------------------------+--------------+--------------+---------------+----------------+------------------+");
            System.out.println("| Doctor ID    |          Name           |     Sex      |    Address   | Department ID |   Create Date  | Last Update Date |");
            System.out.println("+--------------+-------------------------+--------------+----------- --+---------------+----------------+------------------+");
            this.get(index).showInfo();

        }
    }

    /**
     * Add doctor check if department id exists or not, then allow import
     *
     * @throws ParseException
     */
    public void addDoctor() throws ParseException {

        String doctorID;
        String name;
        String sex;
        String address;
        String departmentID;
        String createDate;
        String lastUpdateDate;
        int pos;

        DepartmentList departList = new DepartmentList();
        try {
            departList.loadFromFile("departmentFile.txt");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(DoctorList.class.getName()).log(Level.SEVERE, null, ex);

        }
        System.out.println("This is list of Doctor.");
        this.listDoctor();
        do {
            doctorID = Mytools.getID("Enter Doctor ID (DXX)(X stands for a digit): ",
                    "The format of id is DXX (X stands for a digit)", DOCTOR_FORMAT);
            pos = searchID(doctorID);
            if (pos >= 0) {
                System.out.println("The Department ID already exists. " + "Input another one!");

            }
        } while (pos > 0);

        name = Mytools.getString("Enter Doctor name: ",
                "The Doctor name is required!");

        address = Mytools.getString("Enter Doctor address: ", "The Doctor address is required!");
        do {
            departmentID = Mytools.getID("Enter Department ID (AXX)(X stands for a digit): ",
                    "Department ID is required", DEPARTMENT_FORMAT);
            if (departList.searchingID(departmentID) == false) {
                System.out.println("There is no department with this ID");
            }
        } while (departList.searchingID(departmentID) == false);

        do {
            createDate = Mytools.getDate("Enter the create date"
                    + " (The format date is dd/MM/YYYY): ",
                    "Invalid date. Please enter again!");

        } while (Mytools.isTrueDate(createDate) == false
                || Mytools.isValidCreateDate(Mytools.getNowDate(), createDate) == false);
        do {
            lastUpdateDate = Mytools.getDate("Enter the last update date (The format date is dd/MM/YYYY): ",
                    "Invalid date. Please enter again!");
        } while (Mytools.isTrueDate(createDate) == false
                || Mytools.isValidLastUpdate(createDate, lastUpdateDate) == false);
        do {
            sex = Mytools.getString("Sex (The format is M/F) ?", "Male (M) or FeMale(F) ?");
            if (sex.equalsIgnoreCase("f") || sex.equalsIgnoreCase("female")) {
                sex = "FEMALE";
            } else if (sex.equalsIgnoreCase("m") || sex.equalsIgnoreCase("male")) {
                sex = "MALE";
            }
        } while (!(sex.equalsIgnoreCase("f") || sex.equalsIgnoreCase("female")
                || sex.equalsIgnoreCase("m") || sex.equalsIgnoreCase("male")));

        Doctor newDoc = new Doctor(doctorID, name, sex, address, departmentID, createDate, lastUpdateDate);
        this.add(newDoc);
        System.out.println("A doctor profile is added sucessfully");

    }

    /**
     * Update doctor Only update
     * name,sex,address,departmentID,createDate,lastUpdateDate
     */
    public void updateDoctor() {
        int position;
        System.out.println("This is list of Doctor.Which ID do you want to update?");
        this.listDoctor();
        position = searchID(Mytools.getID("Enter Doctor ID (DXX)(X stands for a digit: ",
                "The Doctor ID is required", "D\\d{2}"));

        if (position < 0) {
            System.out.println("Not found!");
        } else {
            String name;
            String sex;
            String address;
            String departmentID;
            String createDate;
            String lastUpdateDate;
            Menu menu = new Menu("Update");
            menu.addNewOption("1-Update doctor's name");
            menu.addNewOption("2-Update doctor's sex");
            menu.addNewOption("3-Update doctor's address");
            menu.addNewOption("4-Update doctor's DeparmentID");
            menu.addNewOption("5-Update doctors createDate");
            menu.addNewOption("6-Update doctors lastUpdateDate");
            menu.addNewOption("7-Exit");
            int choice;
            do {
                menu.printMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:
                        System.out.println("The current doctor name is "
                                + this.get(position).getName());
                        name = Mytools.getString("Enter new Doctor name: ",
                                "The new name is required!");
                        this.get(position).setName(name);
                        System.out.println("The Doctor info is updated successfully!");
                        this.listDoctor();

                        break;
                    case 2:
                        System.out.println("The current sex is "
                                + this.get(position).getSex());

                        do {
                            sex = Mytools.getString("Sex (The format is M/F) ?", "Male (M) or FeMale(F) ?");
                            if (sex.equalsIgnoreCase("f") || sex.equalsIgnoreCase("female")) {
                                sex = "FEMALE";
                                this.get(position).setSex(sex);

                            } else if (sex.equalsIgnoreCase("m") || sex.equalsIgnoreCase("male")) {
                                sex = "MALE";
                                this.get(position).setSex(sex);

                            }
                        } while (!(sex.equalsIgnoreCase("f") || sex.equalsIgnoreCase("female")
                                || sex.equalsIgnoreCase("m") || sex.equalsIgnoreCase("male")));
                        System.out.println("The Doctor info is updated successfully!");
                        this.listDoctor();

                        break;
                    case 3:
                        System.out.println("The current address is "
                                + this.get(position).getAddress());
                        address = Mytools.getString("Enter new address: ", "The new address is required!");
                        this.get(position).setAddress(address);
                        System.out.println("The Doctor info is updated successfully!");
                        this.listDoctor();

                        break;
                    case 4:
                        System.out.println("The current departmentID is "
                                + this.get(position).getAddress());
                        departmentID = Mytools.getString("Enter new department ID: ",
                                "The new department ID is required!");
                        this.get(position).setDepartmentID(departmentID);
                        System.out.println("The Doctor info is updated successfully!");
                        this.listDoctor();

                        break;
                    case 5:
                        do {
                            System.out.println("The current create date is "
                                    + this.get(position).getCreateDate());
                            createDate = Mytools.getDate("Enter the create date (The format date is dd/MM/YYYY): ",
                                    "Invalid date. Please enter again!");

                            this.get(position).setCreateDate(createDate);

                        } while (Mytools.isTrueDate(createDate) == false
                                || Mytools.isValidCreateDate(Mytools.getNowDate(), createDate) == false);
                        System.out.println("The Doctor info is updated successfully!");
                        this.listDoctor();

                        break;
                    case 6:
                        System.out.println("The current last update date is "
                                + this.get(position).getLastUpdateDate());
                        do {
                            lastUpdateDate = Mytools.getDate("Enter the last update date (The format date is dd/MM/YYYY): ",
                                    "Invalid date. Please enter again!");
                            this.get(position).setLastUpdateDate(lastUpdateDate);
                            createDate = this.get(position).getCreateDate();
                        } while (Mytools.isTrueDate(lastUpdateDate) == false
                                || Mytools.isValidLastUpdate(createDate, lastUpdateDate) == false);
                        System.out.println("The Doctor info is updated successfully!");
                        this.listDoctor();

                        break;
                    case 7:
                        break;

                }
            } while (choice != 7);

        }
    }

    /**
     * remove doctor following id
     */
    public void removeDoctor() {
        String id;
        int pos;
        System.out.println("This is list of Doctor.Which ID do you want to remove?");
        this.listDoctor();
        id = Mytools.getID("Input doctor id (DXX)(X stands for a digit: ",
                "Doctor ID is required!", DOCTOR_FORMAT);
        pos = searchID(id);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {

            System.out.println("This is Doctor you want to remove ?");
            this.get(pos).showInfo();
            String quit = Mytools.getString("Do you want to quit (Y/N) ?",
                    "Yes (Y) or No(N) ?");

            if (quit.equalsIgnoreCase("y")) {
                this.remove(pos);
                System.out.println("The selected Doctor is removed successfully!");
                System.out.println("The Doctor info is updated successfully!");
                this.listDoctor();

            } else {
                System.out.println("The selected Doctor isn't removed !");

            }

            System.out.println("The selected doctor is removed successfully!");
        }
    }

    /**
     * show the list of doctor
     */
    public void listDoctor() {
        if (this.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+--------------+-------------------------+--------------+--------------+---------------+----------------+------------------+");
            System.out.println("| Doctor ID    |          Name           |     Sex      |    Address   | Department ID |   Create Date  | Last Update Date |");
            System.out.println("+--------------+-------------------------+--------------+----------- --+---------------+----------------+------------------+");
            if (this.isEmpty()) {
                System.out.println("The list is empty!");
            }
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfo();
            }
        }
    }

    public void function() throws ParseException {
        int choiceM2;
        Menu menuM2 = new Menu("Doctor's Menu ");
        menuM2.addNewOption("1-Add Doctor");
        menuM2.addNewOption("2-Remove Doctor");
        menuM2.addNewOption("3-Update Doctor");
        menuM2.addNewOption("4-Search Doctor");
        menuM2.addNewOption("5-Show Doctor List");
        menuM2.addNewOption("6-Exit");
        do {
            menuM2.printMenu();
            choiceM2 = menuM2.getChoice();
            switch (choiceM2) {

                case 1:
                    this.addDoctor();
                    this.saveToFile("doctorFile.txt");
                    break;
                case 2:
                    this.removeDoctor();
                    this.saveToFile("doctorFile.txt");

                    break;
                case 3:
                    this.updateDoctor();
                    this.saveToFile("doctorFile.txt");

                    break;
                case 4:
                    this.search();

                    break;
                case 5:
                    this.listDoctor();

                    break;
                case 6:

                    break;
            }
        } while (choiceM2 >= 1 && choiceM2 < 6);
    }
}
