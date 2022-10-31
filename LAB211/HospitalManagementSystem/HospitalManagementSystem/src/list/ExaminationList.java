/**
 * Class for ExaminationList
 */
package list;

import data.Examination;
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
public class ExaminationList extends ArrayList<Examination> {

    public static final String SEPARATOR = ",";
    public static final String DOCTOR_FORMAT = "D\\d{2}";
    public static final String PATIENT_FORMAT = "P\\d{2}";
    public static final String EXAMINATION_FORMAT = "E\\d{2}";

    public ExaminationList() {
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
                        String examinationID = stk.nextToken().trim().toUpperCase();
                        String doctorID = stk.nextToken().trim().toUpperCase();
                        String patientID = stk.nextToken().trim().toUpperCase();
                        String result = stk.nextToken().trim().toUpperCase();
                        String date = stk.nextToken().trim().toUpperCase();
                        if (examinationID.matches(EXAMINATION_FORMAT) == false
                                || examinationID.length() == 0 || examinationID.isEmpty()
                                || doctorID.matches(DOCTOR_FORMAT) == false
                                || doctorID.length() == 0 || doctorID.isEmpty()
                                || patientID.matches(PATIENT_FORMAT) == false
                                || patientID.length() == 0 || patientID.isEmpty()
                                || result.length() == 0 || result.isEmpty()
                                || Mytools.isTrueDate(date) == false) {
                            return false;
                        } else {
                            this.add(new Examination(examinationID, doctorID, patientID, result, date));
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
                    pw.println(x.getExaminationID() + SEPARATOR + x.getDoctorID()
                            + SEPARATOR + x.getPatientID() + SEPARATOR + x.getResult() + SEPARATOR + x.getDate());
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
            if (this.get(i).getExaminationID().equals(ID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Function search function that searches by id and returns object
     */
    public void search() {
        String inputID;
        inputID = Mytools.getID("Enter the Examination ID to search (EXX)(E stands for a digit): ",
                "The Examination ID is required!", EXAMINATION_FORMAT);
        int index = this.searchID(inputID);
        if (index < 0) {
            System.out.println("No examination has this ID");
        } else {
            System.out.println("+----------------+---------------+--------------+-------------+-------------+");
            System.out.println("| Examination ID |   Doctor ID   |  Patient ID  |   Result    |    Date     |");
            System.out.println("+----------------+---------------+--------------+-------------+-------------+");
            this.get(index).showInfo();

        }
    }

    public void search2() throws FileNotFoundException {
        PatientList patientList = new PatientList();
        patientList.loadFromFile("patientFile.txt");
        String inputID1, inputID2;
        inputID1 = Mytools.getID("Enter the Patient ID to search (PXX)(X stands for a digit): ",
                "The Examination ID is required!", PATIENT_FORMAT);
        inputID2 = Mytools.getID("Enter the Examination ID to search (EXX)(E stands for a digit): ",
                "The Examination ID is required!", EXAMINATION_FORMAT);

        int index = patientList.searchPatientID(inputID1);
        int index2 = this.searchID(inputID2);

        if (inputID1.equalsIgnoreCase(patientList.get(index).getPatientID()) == true && inputID1.equalsIgnoreCase(this.get(index2).getPatientID())) {
            if (index2 < 0) {
                System.out.println("No examination has this ID");
            } else {
                System.out.println("This is the examination result for you!");
                System.out.println("+----------------+---------------+--------------+-------------+-------------+");
                System.out.println("| Examination ID |   Doctor ID   |  Patient ID  |   Result    |    Date     |");
                System.out.println("+----------------+---------------+--------------+-------------+-------------+");
                this.get(index2).showInfo();

            }
        } else {
            System.out.println("No examination has this ID");

        }
    }

    /**
     * Add doctor check if department id and doctor id exists or not, then allow
     * import
     *
     * @throws FileNotFoundException
     *
     */
    public void addExamination() throws FileNotFoundException {

        String examinationID;
        String doctorID;
        String patientID;
        String result;
        String date;
        int pos;

        DoctorList doctorList = new DoctorList();
        PatientList patientList = new PatientList();

        doctorList.loadFromFile("doctorFile.txt");
        patientList.loadFromFile("patientFile.txt");
        System.out.println("This is list of Examination Result");
        this.listExaminationResult();
        do {
            examinationID = Mytools.getID("Enter Examination ID (EXX)(X stands for a digit): ",
                    "The Examination ID is required!", EXAMINATION_FORMAT);
            pos = searchID(examinationID);
            if (pos >= 0) {
                System.out.println("The Examination ID already exists. "
                        + "Input another one!");
            }
        } while (pos > 0);
        do {
            doctorID = Mytools.getID("Enter Doctor ID(DXX)(X stands for a digit): ",
                    "The doctor ID is required!", DOCTOR_FORMAT);
            if (doctorList.searchingID(doctorID) == false) {
                System.out.println("There is no doctor with this ID !");
            }
        } while (doctorList.searchingID(doctorID) == false);
        do {
            patientID = Mytools.getID("Enter Patient ID (PXX)(X stands for a digit): ",
                    "The patient ID is required!", PATIENT_FORMAT);
            if (patientList.searchingID(patientID) == false) {
                System.out.println("There is no patient with this ID !");
            }
        } while (patientList.searchingID(patientID) == false);

        result = Mytools.getString("Enter result: ", "The result is required!");

        do {
            date = Mytools.getDate("Enter the date (The format date is dd/MM/YYYY): ",
                    "Invalid date. Please enter again!");
        } while (Mytools.isTrueDate(date) == false
                || Mytools.isValidCreateDate(Mytools.getNowDate(), date) == false);
        Examination newExam = new Examination(examinationID, doctorID, patientID, result, date);

        this.add(newExam);
        System.out.println("A examination result profile is added sucessfully");

    }

    /**
     * Update doctor Only update result,date
     */
    public void updateExamination() {
        int position;
        System.out.println("This is list of Examination Result.Which ID do you want to update?");
        this.listExaminationResult();
        position = searchID(Mytools.getID("Enter Examination ID (EXX)(X stands for a digit): ",
                "The Examination ID is required!", EXAMINATION_FORMAT));

        if (position < 0) {
            System.out.println("Not found!");
        } else {
            String result;
            String date;
            Menu menu = new Menu("Update");
            menu.addNewOption("1-Update examination result");
            menu.addNewOption("2-Update date");
            menu.addNewOption("3-Exit");
            int choice;
            do {
                menu.printMenu();
                choice = menu.getChoice();
                switch (choice) {
                    case 1:

                        System.out.println("The current result is "
                                + this.get(position).getResult());
                        result = Mytools.getString("Enter the new result: ",
                                "The new result is required!");
                        this.get(position).setResult(result);
                        System.out.println("The Examination result is updated successfully!");
                        this.listExaminationResult();

                        break;
                    case 2:
                        System.out.println("The current last update date is "
                                + this.get(position).getDate());
                        do {
                            date = Mytools.getDate("Enter the date (The format date is dd/MM/YYYY): ",
                                    "Invalid date. Please enter again!");

                            this.get(position).setDate(date);
                        } while (Mytools.isTrueDate(date) == false
                                || Mytools.isValidCreateDate(Mytools.getNowDate(), date) == false);
                        System.out.println("The Examination result is updated successfully!");
                        this.listExaminationResult();

                        break;
                    case 3:
                        break;

                }

            } while (choice != 3);

        }
    }

    /**
     * remove examination result following id
     */
    public void removeExamination() {
        String id;
        int pos;
        System.out.println("This is list of Examination Result.Which ID do you want to remove?");
        this.listExaminationResult();
        id = Mytools.getID("Input Examination result ID (EXX)(X stands for a digit): ",
                "Examination ID is required!", EXAMINATION_FORMAT);
        pos = searchID(id);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {

            System.out.println("This is Examination Result you want to remove ?");
            this.get(pos).showInfo();
            String quit = Mytools.getString("Do you want to quit (Y/N) ?",
                    "Yes (Y) or No(N) ?");

            if (quit.equalsIgnoreCase("y")) {
                this.remove(pos);
                System.out.println("The selected Examination Result is removed successfully!");
                this.listExaminationResult();

            } else {
                System.out.println("The selected Examination Result isn't removed !");

            }

        }
    }

    /**
     * Show the list of doctor
     */
    public void listExaminationResult() {

        if (this.isEmpty()) {
            System.out.println("The list is empty!");
        } else {
            System.out.println("+----------------+---------------+--------------+-------------+-------------+");
            System.out.println("| Examination ID |   Doctor ID   |  Patient ID  |   Result    |    Date     |");
            System.out.println("+----------------+---------------+--------------+-------------+-------------+");
            for (int i = 0; i < this.size(); i++) {
                this.get(i).showInfo();
            }
        }
    }

    public void fuction() throws FileNotFoundException {
        int choiceM4;
        Menu menuM4 = new Menu("Examination's Menu ");
        menuM4.addNewOption("1-Add Examination");
        menuM4.addNewOption("2-Remove Examination");
        menuM4.addNewOption("3-Update Examination");
        menuM4.addNewOption("4-Search Examination");
        menuM4.addNewOption("5-Show Examination List");
        menuM4.addNewOption("6-Record Examination Result");
        menuM4.addNewOption("7-Exit");
        do {
            menuM4.printMenu();
            choiceM4 = menuM4.getChoice();
            switch (choiceM4) {
                case 1:
                    this.addExamination();
                    this.saveToFile("examinationFile.txt");
                    break;
                case 2:
                    this.removeExamination();
                    this.saveToFile("examinationFile.txt");

                    break;
                case 3:
                    this.updateExamination();
                    this.saveToFile("examinationFile.txt");

                    break;
                case 4:
                    this.search();
                    break;
                case 5:
                    this.listExaminationResult();
                    break;
                case 6:
                    this.saveToFile("examinationFile.dat");
                    break;
                case 7:
                    break;
            }
        } while (choiceM4 >= 1 && choiceM4 < 7);

    }
}
