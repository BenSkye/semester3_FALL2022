/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Scanner;
import controller.Controller;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Utils {

    public static final String SEPARATOR = ",";
    private static final String IGNORE_CASE_PATTERN = "(?i)";

    private static final Scanner sc = new Scanner(System.in);

    private Utils() {
    }

    /**
     * getAnInteger is a function enter true integer Any incorrect input is
     * notified via errorMsg and forced to re-enter until correct .Example of
     * entering a string instead of a number
     *
     * @param inputMsg: Enter true integer
     * @param errorMsg: Enter the error message when the function does not run
     * as expected
     * @return
     *
     */
    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * getAnInteger is a function enter true integer in ranger Any incorrect
     * input is notified via errorMsg and forced to re-enter until correct
     * .Example of entering a string instead of a number
     *
     * @param inputMsg: Enter true integer in range lowerBound and upper Bound
     * @param errorMsg: Enter the error message when the function does not run
     * as expected
     * @param lowerBound: Enter true integer for lowerBound
     * @param upperBound:Enter true integer for upperBound
     * @return
     *
     */
    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * getAnInteger is a function enter true double Any incorrect input is
     * notified via errorMsg and forced to re-enter until correct . Example of
     * entering a string instead of a number
     *
     * @param inputMsg:Enter true double
     * @param errorMsg: Enter the error message when the function does not run
     * as expected
     *
     * @return
     */
    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;

        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * Function to write ID. Block all cases such as blank, incorrect input
     * format.Forcing user to enter until valid
     *
     * @param inputMsg:enter message line
     * @param errorMsg:enter error line
     * @param format: format of get id Example: DXX is D\\d{2}
     * @return id that the user entered is valid
     */
    public static String getID(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    /**
     * Function that takes a string of characters and checks if it is valid for
     * the given format
     *
     * @param str
     * @param regex
     * @param ignoreCase
     * @return
     */
    public static boolean validateString(String str, String regex, boolean ignoreCase) {
        if (str != null && regex != null) {
            if (ignoreCase) {
                regex = Utils.IGNORE_CASE_PATTERN + regex;
            }
            return str.matches(regex);
        }
        return false;
    }

    /**
     * function that takes a integer number and checks if it is valid for the
     * given interval
     *
     * @param inputMsg
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean validateInteger(double inputMsg, int lowerBound, int upperBound) {

        return inputMsg <= upperBound && inputMsg >= lowerBound && (Math.ceil(inputMsg) == Math.floor(inputMsg));
    }

    /**
     * function that takes a float number and checks if it is valid for the
     * given interval
     *
     * @param inputMsg
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public static boolean validateFloat(double inputMsg, double lowerBound, double upperBound) {
        String regex = "[+-]?([0-9]*[.])?[0-9]+";

        return inputMsg <= upperBound && inputMsg >= lowerBound && Double.toString(inputMsg).matches(regex);

    }

    /**
     * function that takes a string and checks available or not
     *
     * @param status
     * @return
     */
    public static boolean validateStatus(String status) {
        if (status.equalsIgnoreCase("available") || status.equalsIgnoreCase("not available")) {
            return true;
        }
        return false;
    }

    public static boolean validateID(String id) {
        int pos = Controller.getInstance().getProductList().searchID(id);
        if (pos >= 0) {
            return false;
        }
        return true;
    }

    public static boolean validateName(String name) {
        int pos = Controller.getInstance().getProductList().searchName(name);
        if (pos >= 0) {
            return false;
        }
        return true;
    }

    public static boolean isContinuing() {
        String isContinue;
        do {
            isContinue = Utils.getString("Do you want to continue (Y/N) ?", "Yes (Y) or No(N) ?");
        } while (!isContinue.equalsIgnoreCase("y") && !isContinue.equalsIgnoreCase("n"));
        if (isContinue.equalsIgnoreCase("y")) {
            return true;
        }
        return false;
    }

    public static boolean isQuit() {
        String isQuit;
        do {
            isQuit = Utils.getString("Do you want to quit (Y/N) ?", "Yes (Y) or No(N) ?");
        } while (!isQuit.equalsIgnoreCase("y") && !isQuit.equalsIgnoreCase("n"));
        if (isQuit.equalsIgnoreCase("y")) {
            return true;
        }
        return false;
    }

}
