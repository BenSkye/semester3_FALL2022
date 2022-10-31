package controller;

import service.ProductServices;
import service.UserServices;
import java.io.FileNotFoundException;
import view.ViewServices;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Controller {

    public static final Controller instance = new Controller();

    public static Controller getInstance() {
        return instance;
    }
    private final String productFilePath;
    private final String userFilePath;
    private final ProductServices productServices;
    private final UserServices userServices;
    private final ViewServices viewservices;

    /**
     * Constructor
     */
    private Controller() {
        productFilePath = "Product.dat";
        userFilePath = "User.dat";
        userServices = new UserServices(userFilePath);
        productServices = new ProductServices(productFilePath);
        viewservices = new ViewServices();
    }

    /**
     * function to load the product file into the list
     */
    private void loadProduct() {
        productServices.load();
    }

    /**
     * function to load the user file into the list
     */
    private void loadUser() {
        userServices.load();
    }

    public void init() throws FileNotFoundException {
        loadProduct();
        loadUser();

    }

    public ProductServices getProductList() {
        return productServices;
    }

    public UserServices getUserList() {
        return userServices;
    }

    public void addUser() {
        this.userServices.addUser();
    }

    public void removeUser() {
        this.userServices.removeUser();
    }

    public void changePassword() {
        this.userServices.changePassword();

    }

    public void filterByNameUser() {
        this.userServices.filterByName();
    }

    public void printAllUser() {
        this.viewservices.viewOfUser();
    }

    public void saveUserFile() {
        if (this.userServices.save()) {
            System.out.println("Save success");
        } else {
            System.out.println("Save fail!");
        }
    }

    public void addProduct() {
        this.productServices.addProduct();
    }

    public void removeProduct() {
        this.productServices.removeProduct();
    }

    public void updateProduct() {
        this.productServices.updateProduct();
    }

    public void filterByNameProduct() {
        this.productServices.filterByName();
    }

    public void checkProductExist() {
        this.productServices.checkProductExist();
    }

    public void printAllProduct() {
        this.viewservices.viewOfProduct();
    }
     public void printAllProductByCondition() {
        this.viewservices.viewOfProductByCondition();
    }

    public void saveProductFile() {
        if (this.productServices.save()) {
            System.out.println("Save success");
        } else {
            System.out.println("Save fail!");
        }
    }

    public void showMenu() {
        this.viewservices.menuMain();
    }
}
