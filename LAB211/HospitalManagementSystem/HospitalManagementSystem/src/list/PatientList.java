/**
 * Class for PatientList
 */
package list;

import data.Patient;
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
public class PatientList extends ArrayList<Patient> {

    public static final String SEPARATOR = ",";
    public static final String PATIENT_FORMAT = "P\\d{2}";

    public PatientList() {
        super();
    }

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
                        String patientID = stk.nextToken().trim().toUpperCase();
                        String name = stk.nextToken().trim().toUpperCase();
                        Integer age = Integer.parseInt(stk.nextToken().trim());
                        String address = stk.nextToken().trim().toUpperCase();
                        if (patientID.matches(PATIENT_FORMAT) == false
                                || patientID.length() == 0 || patientID.isEmpty()
                                || name.length() == 0 || name.isEmpty()
                                || age < 1 || age > 100 || address.length() == 0
                                || address.isEmpty()) {
                            return false;
                        } else {
                            this.add(new Patient(patientID, name, age, address));

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

    public boolean saveToFile(String filename) {
        if (this.isEmpty()) {
            System.out.println("Empty list");
            return false;
        }
        try {
            File f = new File(filename);
            try ( FileWriter fw = new FileWriter(f);  PrintWriter pw = new PrintWriter(fw)) {
                this.forEach((x) -> {
                    pw.println(x.getPatientID() + SEPARATOR + x.getName() + SEPARATOR + x.getAge() + SEPARATOR + x.getAddress());
                });
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return true;
    }

    public boolean deleteFile(String filename) {
        if (this.isEmpty()) {
            return false;
        }
        try {
            File file = new File(filename);
            if (file.delete()) {
                System.out.println("Deleted the file: " + file.getName());
            } else {
                System.out.println("Failed to delete the file.");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return true;
    }

    public int searchPatientID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getPatientID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    public boolean searchingID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getPatientID().equals(ID)) {
                return true;
            }
        }
        return false;
    }

    public void search() {
        String inputID;

        inputID = Mytools.getID("Enter the ID to search (PXX)(X stands for a digit): ",
                "The Patient ID is required!", PATIENT_FORMAT);

        int index = this.searchPatientID(inputID);
        if (index < 0) {
            System.out.println("No patient has this ID");
        } else {
            System.out.println("+---------------+-------------------------+-------------+-------------+");
            System.out.println("|  Patient ID   |          Name           |     Age     |   Address   |");
            System.out.println("+---------------+-------------------------+-------------+-------------+");
            this.get(index).showInfo();

        }
    }

    public void addPatient() {

        String patientID;
        String name;
        int pos;
        int age;
        String address;
        System.out.println("This is list of Patient");
        this.listPatient();
        do {
            patientID = Mytools.getID("Enter the Patient ID (PXX)(X stands for a digit): ",
                    "The Patient ID is required!", PATIENT_FORMAT);
            pos = searchPatientID(patientID);
            if (pos >= 0) {
                System.out.println("The patient id already exists. "
                        + "Input another one!");

            }
        } while (this.searchPatientID(patientID) != -1);

        name = Mytools.getString("Enter Patient name: ",
                "The Patient name is required!");

        age = Mytools.getAnInteger("Enter Patient age: ",
                "The Patient age is required!", 100, 1);

        address = Mytools.getString("Enter address: ",
                "The address is required!");

        Patient newPat = new Patient(patientID, name, age, address);

        this.add(newPat);
        System.out.println("A patient profile is added sucessfully");

    }

    public void updatePatient() {
        int position;
        System.out.println("This is list of Patient ."
                + "Which ID do you want to update?");
        this.listPatient();
        position = searchPatientID(Mytools.getID("Enter Patient ID (PXX)(X stands for a digit): ",
                "The Patient ID is required!", PATIENT_FORMAT));

        if (position < 0) {
            System.out.println("Not found!");
        } else {

            String name;
            int age;
            String address;
            Menu menu = new Menu("Update");
            menu.addNewOption("1-Update Patient's name");
            menu.addNewOption("2-Update Patient's age");
            menu.addNewOption("3-Update Patient's address");
            menu.addNewOption("4-Exit");
            int choice;
            do {
                menu.printMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:
                        System.out.println("The current patient name is "
                                + this.get(position).getName());

                        name = Mytools.getString("Enter new name: ",
                                "The new name is required!");

                        this.get(position).setName(name);
                        System.out.println("The Patient info is updated successfully!");
                        this.listPatient();

                        break;
                    case 2:
                        System.out.println("The current age is "
                                + this.get(position).getAge());

                        age = Mytools.getAnInteger("Enter Patient age: ",
                                "The Patient age is required!", 110, 1);
                        this.get(position).setAge(age);
                        System.out.println("The Patient info is updated successfully!");
                        this.listPatient();

                        break;

                    case 3:
                        System.out.println("The current address is "
                                + this.get(position).getAddress());

                        address = Mytools.getString("Enter new address: ",
                                "The new address is required!");

                        this.get(position).setAddress(address);
                        System.out.println("The Patient info is updated successfully!");
                        this.listPatient();

                        break;
                    case 4:
                        break;

                }

            } while (choice != 4);
        }
    }

    public void removePatient() {
        String id;
        int pos;
        System.out.println("This is list of Patient .Which ID do you want to remove?");
        this.listPatient();
        id = Mytools.getID("Input Patient ID (PXX)(X stands for a digit):",
                "The Patient ID is required! ", "P\\d{2}");
        pos = searchPatientID(id);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {

            System.out.println("This is Patient you want to remove ?");
            this.get(pos).showInfo();
            String quit = Mytools.getString("Do you want to quit (Y/N) ?",
                    "Yes (Y) or No(N) ?");

            if (quit.equalsIgnoreCase("y")) {
                this.remove(pos);
                System.out.println("The selected Patient is removed successfully!");
                this.listPatient();

            } else {
                System.out.println("The selected Patient isn't removed !");

            }

        }
    }

    public void listPatient() {
        if (this.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+---------------+-------------------------+-------------+-------------+");
            System.out.println("|  Patient ID   |          Name           |     Age     |   Address   |");
            System.out.println("+---------------+-------------------------+-------------+-------------+");
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfo();
            }
        }

    }

    public void function() {
        int choiceM3;
        Menu menuM3 = new Menu("Patient's Menu ");
        menuM3.addNewOption("1-Add Patient");
        menuM3.addNewOption("2-Remove Patient");
        menuM3.addNewOption("3-Update Patient");
        menuM3.addNewOption("4-Search Patient");
        menuM3.addNewOption("5-Show Patient List");
        menuM3.addNewOption("6-Exit");
        do {
            menuM3.printMenu();
            choiceM3 = menuM3.getChoice();
            switch (choiceM3) {
                case 1:
                    this.addPatient();
                    this.saveToFile("patientFile.txt");
                    break;
                case 2:
                    this.removePatient();
                    this.saveToFile("patientFile.txt");

                    break;
                case 3:
                    this.updatePatient();
                    this.saveToFile("patientFile.txt");
                    break;
                case 4:
                    this.search();
                    break;
                case 5:
                    this.listPatient();
                    break;
                case 6:

                    break;
            }
        } while (choiceM3 >= 1 && choiceM3 < 6);

    }
}
