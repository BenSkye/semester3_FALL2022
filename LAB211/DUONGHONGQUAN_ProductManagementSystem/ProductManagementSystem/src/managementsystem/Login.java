package managementsystem;

import controller.Controller;
import model.UserModel;
import util.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Login {

    public static final Login instance = new Login();

    public static Login getInstance() {
        return instance;
    }
    private final UserModel currentUser;

    public UserModel getCurrentUser() {
        return currentUser;
    }

    public Login() {
        currentUser = login();
    }

    /**
     * Function to login app
     *
     * @return user or null
     */
 

    private UserModel login() {
        UserModel user;

        String name, pass;
        do {
            name = Utils.getString("User name(AXXX or UXXX(X is digit)): ", "User name is required!");
            pass = Utils.getString("Password: ", "Password is required!");
            user = new UserModel(name, pass);
            if (!Controller.getInstance().getUserList().searchingName(user)) {
                System.out.println("The user name doesn't exist!!!");
            }

            if (!Controller.getInstance().getUserList().searchingPwd(user)) {
                System.out.println(" Wrong password!!!");
            }
        } while (!Controller.getInstance().getUserList().isValid(user));
        if (Controller.getInstance().getUserList().isValid(user)) {

            System.out.println("Logged in successfully!!!\n");
        } else {
            System.out.println("Login failed\n");

        }
        return user;

    }
}
