/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Utils {

    public static final String SEPARATOR = ",";
    private static final String IGNORE_CASE_PATTERN = "(?i)";
    public static final String DATE_FORMAT = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
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

    /**
     * Function:isTrueDate Function to determine if the string of date and time
     * entered by the user is valid or not
     *
     * @param msg:user-entered character string
     * @return True or False
     */
    public static boolean validateDate(String msg) {
        StringTokenizer stk = new StringTokenizer(msg, "[:,/]");
        int d = Integer.parseInt(stk.nextToken().trim());
        int m = Integer.parseInt(stk.nextToken().trim());
        int y = Integer.parseInt(stk.nextToken().trim());

        if (y >= 1900 && y <= 9999) {
            //check moth
            if (m >= 1 && m <= 12) {
                //check days
                if ((d >= 1 && d <= 31) && (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12)) {
                    return true;
                } else if ((d >= 1 && d <= 30) && (m == 4 || m == 6 || m == 9 || m == 11)) {
                    return true;
                } else if ((d >= 1 && d <= 28) && (m == 2)) {
                    return true;
                } else if (d == 29 && m == 2 && (y % 400 == 0 || (y % 4 == 0 && y % 100 != 0))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {

            return false;
        }
    }

    public static String getDate(String inputMsg, String errorMsg) {
        String id;
        String format = DATE_FORMAT;
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
}
