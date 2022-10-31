/**
 * Class for Menu
 */
package ui;

import java.util.ArrayList;
import util.Mytools;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList();
    //contains options/menu

    //initialize name of app., name of menu
    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    /**
     * Add an option to the list, start building the menu/ program selection
     *
     * @param newOption
     */
    public void addNewOption(String newOption) {
        //TODO: cần kiểm tra coi option đưa vào có trùng với option
        //sẵn có hay ko?
        //nếu ko trùng, add vào danh sách lựa chọn
        optionList.add(newOption);
    }

    /**
     * print a list of options for the user to choose the required feature use
     */
    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("---------------------------------------------");
        System.out.println("Welcome to " + menuTitle);
        System.out.println("---------------------------------------------");

        for (String x : optionList) {
            System.out.println(x);
        }

    }

    /**
     * have a new menu with options. The function returns the number selected by
     * the user corresponds to the function the user wants the app to perform
     *
     * @return choice;
     */
    public int getChoice() {
        int maxOption = optionList.size();
        //lựa chọn lớn nhất là số thứ tự ứng với số mục chọn
        String inputMsg = "Choose [1.." + maxOption + "]: ";
        String errorMsg = "You are required to choose the option 1.." + maxOption;
        return Mytools.getAnInteger(inputMsg, errorMsg, 1, maxOption);
        //print the input: Choose[1..8]: , assuming there are 8 choices in
        //menu
    }
}
