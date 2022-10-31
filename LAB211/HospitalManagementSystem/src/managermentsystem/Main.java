package managermentsystem;

import data.Account;
import list.AccountList;
import list.DepartmentList;
import list.DoctorList;
import list.ExaminationList;
import list.PatientList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.Menu;
import util.Mytools;

/**
 * ^
 *
 * @author LAPTOP_HONGQUAN
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException {
        int choiceM00, choiceM0;
        int choiceD0;
        boolean cont = true;

        /**
         * This is menu for manager.
         */
        Menu menuM00 = new Menu("Hospital Managerment System");
        menuM00.addNewOption("1-For Staff");
        menuM00.addNewOption("2-For Patient");
        menuM00.addNewOption("3-Exit");

        Menu menuM0 = new Menu("Manager's Menu ");
        menuM0.addNewOption("1-Account Management");
        menuM0.addNewOption("2-Department Management");
        menuM0.addNewOption("3-Doctor Management");
        menuM0.addNewOption("4-Patient Management");
        menuM0.addNewOption("5-Examination Result Management");
        menuM0.addNewOption("6-Exit");

        Menu menuD0 = new Menu("Doctor's Menu ");
        menuD0.addNewOption("1-Patient");
        menuD0.addNewOption("2-Examination");
        menuD0.addNewOption("3-Exit");

        DepartmentList departList = new DepartmentList();
        DoctorList doctorList = new DoctorList();
        PatientList patientList = new PatientList();
        ExaminationList examList = new ExaminationList();
        AccountList accList = new AccountList();
        try {
            accList.loadFromFile("account.txt");
            departList.loadFromFile("departmentFile.txt");
            doctorList.loadFromFile("doctorFile.txt");
            patientList.loadFromFile("patientFile.txt");
            examList.loadFromFile("examinationFile.txt");
            System.out.println("---------------------------------------------");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        //CHECK ACC NAME & ROLE OF MANAGER
        do {
            menuM00.printMenu();
            choiceM00 = menuM00.getChoice();
            switch (choiceM00) {
                case 1:
                    Account acc = new Account();
                    if (acc.getRole().equalsIgnoreCase("ADMIN")) {
                        do {

                            menuM0.printMenu();
                            choiceM0 = menuM0.getChoice();
                            switch (choiceM0) {
                                case 1:
                                    accList.function();
                                    break;
                                case 2:
                                    departList.function();
                                    break;
                                case 3:
                                    doctorList.function();
                                    break;
                                case 4:
                                    patientList.function();
                                    break;
                                case 5:
                                    examList.fuction();
                                    break;
                                case 6:
                                    String quit = Mytools.getString("Do you want to quit (Y/N) ?", "Yes (Y) or No(N) ?");
                                    if (quit.equalsIgnoreCase("y")) {
                                        cont = false;
                                        System.out.println("Good bye !");
                                    }
                                    break;
                            }

                        } while (cont);

                        //CHECK ACC NAME & ROLE OF DOCTOR
                    } else if (acc.getRole().equalsIgnoreCase("doctor")) {
                        do {

                            menuD0.printMenu();
                            choiceD0 = menuD0.getChoice();
                            switch (choiceD0) {
                                case 1:
                                    patientList.function();
                                    break;
                                case 2:
                                    examList.fuction();
                                    break;
                                case 3:
                                    String quit = Mytools.getString("Do you want to quit (Y/N) ?", "Yes (Y) or No(N) ?");
                                    if (quit.equalsIgnoreCase("y")) {
                                        cont = false;
                                        System.out.println("Good bye !");
                                    }
                                    break;

                            }
                        } while (cont);
                    } else {
                        System.out.println("Account does not exist!");
                    }

                    break;
                case 2:
                    examList.search2();
                    break;
                case 3:
                    String quit = Mytools.getString("Do you want to quit (Y/N) ?", "Yes (Y) or No(N) ?");
                    if (quit.equalsIgnoreCase("y")) {
                        cont = false;
                        System.out.println("Good bye !");

                    }
            }

        } while (cont);
    }

}
