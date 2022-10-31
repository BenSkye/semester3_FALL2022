
package util;

import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public final class Mytools {

    private Mytools() {
    }

    public static final String DATE_FORMAT = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";

    private static final Scanner sc = new Scanner(System.in);

    //hàm nhập vào số nguyên đích thực
    //- mọi sự nhập cà chớn bị chửi, ví dụ nhập chuỗi thay vì nhập số
    //- chống trôi lệnh, tức là ko để lại rác Enter hay kí tự nào đó
    //trong buffer cho thằng sau hốt 
    //- có thể kiểm tra số nguyên trong 1 range/khoảng cho trước
    //- có câu thông báo động, tùy ngữ cảnh
    /**
     * getAnInteger is a function enter true integer Any incorrect input is
     * notified via errorMsg and forced to re-enter until correct . Example of
     * entering a string instead of a number
     *
     * @param inputMsg: Enter true integer
     * @param errorMsg: Enter the error message when the function does not run
     * as expected
     *
     */
    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * getAnInteger is a function enter true integer in ranger Any incorrect
     * input is notified via errorMsg and forced to re-enter until correct .
     * Example of entering a string instead of a number
     *
     * @param inputMsg: Enter true integer in range lowerBound and upper Bound
     * @param errorMsg: Enter the error message when the function does not run
     * as expected
     * @param lowerBound: Enter true integer for lowerBound
     * @param upperBound:Enter true integer for upperBound
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
        //nếu đầu vào lower > upper thì đổi chỗ
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

    /**
     * Function to write String
     *
     * Enter a string date,month,year following this format: dd/mm/yyyy using
     * Regular Expression.Block case such as blank
     *
     * format: format of get date dd/mm/yyyy
     *
     * @param inputMsg:enter message line
     * @param errorMsg:enter error line
     *
     * @return string that the user entered is valid
     */
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
     * Function getDate Function to write Date. Block all cases such as blank,
     * incorrect input
     *
     * format:Forcing user to enter until valid
     *
     * @param inputMsg:enter message line
     * @param errorMsg:enter error line
     *
     * @return id that the user entered is valid
     */
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

    /**
     * Function:isTrueDate Function to determine if the string of date and time
     * entered by the user is valid or not
     *
     * @param msg:user-entered character string
     * @return True or False
     */
    public static boolean isTrueDate(String msg) {
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

    /**
     * Function:isValidCreateDate .The purpose is to check whether the newly
     * entered createDate is valid or not. Eligibility Criteria: createDate is
     * the date from today until later
     *
     * @param msg: today
     * @param mss: enter createDate
     * @return true or false
     */
    public static boolean isValidCreateDate(String msg, String mss) {
        StringTokenizer stk = new StringTokenizer(msg, "[:,/]");
        int d = Integer.parseInt(stk.nextToken().trim());
        int m = Integer.parseInt(stk.nextToken().trim());
        int y = Integer.parseInt(stk.nextToken().trim());

        StringTokenizer stk1 = new StringTokenizer(mss, "[:,/]");

        int d1 = Integer.parseInt(stk1.nextToken().trim());
        int m1 = Integer.parseInt(stk1.nextToken().trim());
        int y1 = Integer.parseInt(stk1.nextToken().trim());

        if (y1 < y) {
            return false;
        } else if (y1 == y) {
            if (m1 < m) {
                return false;
            } else if (y1 == y && m1 == m) {
                if (d1 < d) {
                    return false;
                } else if (y1 == y && m1 == m && d1 == d) {
                    return true;
                }
            }
        }
        return true;
    }

    /**
     * Function isValidLastUpdate function to check update date. The purpose is
     * to check whether the updated date entered is valid or not. Eligibility
     * Criteria: The update date is the date from the date of creation until
     * later
     *
     * @param msg:enter createDate
     * @param mss:enter UpdateDate
     * @return True or False
     */
    public static boolean isValidLastUpdate(String msg, String mss) {
        StringTokenizer stk = new StringTokenizer(msg, "[:,/]");
        int d = Integer.parseInt(stk.nextToken().trim());
        int m = Integer.parseInt(stk.nextToken().trim());
        int y = Integer.parseInt(stk.nextToken().trim());

        StringTokenizer stk1 = new StringTokenizer(mss, "[:,/]");

        int d1 = Integer.parseInt(stk1.nextToken().trim());
        int m1 = Integer.parseInt(stk1.nextToken().trim());
        int y1 = Integer.parseInt(stk1.nextToken().trim());
        if (y1 < y) {
            return false;
        } else if (y1 == y) {
            if (m1 < m) {
                return false;
            } else if (y1 == y && m1 == m) {
                if (d1 < d) {
                    return false;
                } else if (y1 == y && m1 == m && d1 == d) {
                    return true;
                }
            }
        }
        return true;

    }

    /**
     * Function getNowDate Purpose: function to get the date and year at the
     * time of import
     *
     * @return the date and year at the time of import
     */
    public static String getNowDate() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        String date = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);

        return date;
    }

  

}
