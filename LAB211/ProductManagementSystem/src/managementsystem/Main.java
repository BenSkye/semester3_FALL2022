package managementsystem;

import controller.Controller;
import java.io.FileNotFoundException;

/**
 *
 * @author LAPTOP_HONGQUAN5
 *
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Controller.getInstance().init();
        Controller.getInstance().showMenu();
    }
}
