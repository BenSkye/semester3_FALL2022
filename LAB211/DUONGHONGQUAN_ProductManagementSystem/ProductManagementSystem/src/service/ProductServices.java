/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.ProductDAO;
import controller.Controller;
import model.ProductModel;
import java.util.ArrayList;
import java.util.List;
import managementsystem.Login;
import view.Menu;
import util.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class ProductServices extends ObjectServices<ProductModel> implements ProductDAO {

    private static final ProductServices instance = new ProductServices();

    public static ProductServices getInstance() {
        return instance;
    }

    public ProductServices(String filePath) {
        super(filePath);
    }

    public ProductServices() {

    }

    /**
     * function to convert string to an object
     *
     * @param stringObject
     * @return object
     */
    @Override
    protected ProductModel parseString(String stringObject) {
        ProductModel obj = new ProductModel();
        obj.parseString(stringObject);
        return obj;
    }

    /**
     * Function to add a product to the list
     */
    @Override
    public void addProduct() {
        if (Login.getInstance().getCurrentUser().isADMIN() || Login.getInstance().getCurrentUser().isUser()) {
            Controller.getInstance().printAllProduct();
            System.out.println("Input product ...");
            String productID;
            String productName;
            String status;
            double unitPrice;
            int quantity;

            boolean cont = true;
            int choicest;
            Controller pm = Controller.getInstance();
            do {
                productID = Utils.getID("Please enter the id with the pattern("
                        + ProductModel.ID_FORMAT + "): ", "ID is required!", ProductModel.ID_PATTERN).toUpperCase();
                if (!Utils.validateID(productID)) {
                    System.out.println("The ID is duplicated! Please insert a new ID!");
                }
            } while (!Utils.validateID(productID));

            do {
                productName = Utils.getID("Please enter product name("
                        + ProductModel.NAME_FORMAT + "): ", "Product Name is required!", ProductModel.NAME_PATTERN).toUpperCase();
                int pos = searchName(productName);
                if (!Utils.validateName(productName)) {
                    System.out.println("The name is duplicated! Please insert a new name!");
                }
            } while (!Utils.validateName(productName));

            unitPrice = Utils.getADouble("Please enter price: ", "Price is required", 0, 10000);

            quantity = Utils.getAnInteger("Please enter quantity: ", "Quantity is required!", 0, 1000);

            Menu setStatus = new Menu("Status update");
            setStatus.addNewOption("1-Available");
            setStatus.addNewOption("2-Not available");
            do {
                setStatus.printMenu();
                choicest = setStatus.getChoice();
                switch (choicest) {
                    case 1:
                        status = "AVAILABLE";
                        cont = false;
                        ProductModel obj = new ProductModel(productID, productName, unitPrice, quantity, status);
                        this.add(obj);

                        break;
                    case 2:
                        status = "NOT AVAILABLE";
                        cont = false;
                        ProductModel obj1 = new ProductModel(productID, productName, unitPrice, quantity, status);
                        this.add(obj1);

                        break;
                }
            } while (cont);
            System.out.println("A product is added sucessfully");
            Controller.getInstance().printAllProduct();
            if (Utils.isContinuing()) {
                addProduct();
            }

        } else {
            System.out.println("You don't have permission!");
        }

    }

    /**
     * function to modify the information of a previous product
     */
    @Override
    public void updateProduct() {
        if (Login.getInstance().getCurrentUser().isADMIN() || Login.getInstance().getCurrentUser().isUser()) {
            int position;
            System.out.println("This is list of product. "
                    + "Which ID do you want to update?");
            Controller.getInstance().printAllProduct();
            position = searchID(Utils.getID("Please enter the product id with the pattern(" + ProductModel.ID_FORMAT + "): ", "ID is required!", ProductModel.ID_PATTERN));
            System.out.println("------------------------------------");

            if (position < 0) {
                System.out.println("Not found!");
            } else {
                String productName;
                double unitPrice;
                int quantity;
                String status;
                Menu menu = new Menu("Update product");
                menu.addNewOption("1-The product name");
                menu.addNewOption("2-The unit price");
                menu.addNewOption("3-The quantity");
                menu.addNewOption("4-The status");
                menu.addNewOption("5-Exit");
                int choice;
                do {
                    menu.printMenu();
                    choice = menu.getChoice();
                    switch (choice) {
                        case 1:

                            System.out.println("The current product name is "
                                    + this.get(position).getProductName());
                            productName = Utils.getID("Please enter new product name with the pattern ( " + ProductModel.NAME_FORMAT + "): ",
                                    "Product name is required!", ProductModel.NAME_PATTERN).toUpperCase();

                            this.get(position).setProductName(productName);
                            System.out.println("The product is updated successfully!");
                            Controller.getInstance().printAllProduct();

                            break;
                        case 2:

                            System.out.println("The current unit price is "
                                    + this.get(position).getUnitPrice());
                            unitPrice = Utils.getADouble("Please enter new price: ",
                                    "Price is required", 0, 10000);
                            this.get(position).setUnitPrice(unitPrice);
                            System.out.println("The product is updated successfully!");
                            Controller.getInstance().printAllProduct();

                            break;
                        case 3:

                            System.out.println("The current quantity is "
                                    + this.get(position).getQuantity());
                            quantity = Utils.getAnInteger("Please enter new quantity:", "Quantity is required!", 0, 1000);

                            this.get(position).setQuantity(quantity);

                            System.out.println("The product is updated successfully!");
                            Controller.getInstance().printAllProduct();

                            break;
                        case 4:
                            boolean cont = true;
                            int choicest;
                            System.out.println("The current status is "
                                    + this.get(position).getStatus());
                            Menu setStatus = new Menu("Status update");
                            setStatus.addNewOption("1-Available");
                            setStatus.addNewOption("2-Not available");
                            do {
                                setStatus.printMenu();
                                choicest = setStatus.getChoice();
                                switch (choicest) {
                                    case 1:
                                        status = "AVAILABLE";
                                        this.get(position).setStatus(status);
                                        cont = false;
                                        break;
                                    case 2:
                                        status = "NOT AVAILABLE";
                                        this.get(position).setStatus(status);
                                        cont = false;
                                        break;
                                }
                            } while (cont);
                            System.out.println("The product is updated successfully!");
                            Controller.getInstance().printAllProduct();
                            break;
                        case 5:
                            break;
                    }

                } while (choice != 5);

            }
            if (Utils.isContinuing()) {
                updateProduct();
            }
        } else {
            System.out.println("You don't have permission");
        }

    }

    /**
     * The function returns the position value of a product in a list
     *
     * @param ID
     * @return position
     */
    public int searchID(String ID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductID().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Function removeProduct() to remove product
     */
    @Override
    public void removeProduct() {
        if (Login.getInstance().getCurrentUser().isADMIN() || Login.getInstance().getCurrentUser().isUser()) {
            String id;
            int pos;
            System.out.println("This is list of product."
                    + " Which ID do you want to remove?");
            Controller.getInstance().printAllProduct();
            id = Utils.getID("Please enter the product id with the pattern(" + ProductModel.ID_FORMAT + "): ", "ID is required!", ProductModel.ID_PATTERN);

            pos = searchID(id);

            System.out.println("------------------------------------");
            if (pos == -1) {
                System.out.println("Not found!!!");
            } else {

                System.out.println("This is product you want to remove ?");
                this.get(pos).show();
                String removep;
                do {                    
                      removep = Utils.getString("Do you want to remove (Y/N) ?",
                        "Yes (Y) or No(N) ?");
                } while (!removep.equalsIgnoreCase("y") && !removep.equalsIgnoreCase("n"));
              
                if (removep.equalsIgnoreCase("y")) {
                    this.remove(pos);
                    System.out.println("The selected product is removed successfully!");
                    Controller.getInstance().printAllProduct();
                } else {
                    System.out.println("The selected product isn't removed !");
                }
            }
            if (Utils.isContinuing()) {
                removeProduct();
            }
        } else {
            System.out.println("You don't have permission");
        }

    }

    public int searchName(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductName().contains(name)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void showFilter(String name) {
        List<ProductModel> filterList = filter(name);
        if (filterList.isEmpty()) {
            System.out.println("No product has this name");

        } else {
            System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
            System.out.println("| Product ID    |      Product  Name      |   Unit Price  |    Quantity    |     Status     |");
            System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
            for (ProductModel pro : filterList) {
                pro.show();
            }
            System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");

        }

    }

    @Override
    public List<ProductModel> filter(String name) {
        List<ProductModel> filterList = new ArrayList<>();
        try {
            for (ProductModel pro : this) {
                if (pro.getProductName().contains(name)) {
                    filterList.add(pro);
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No product has this name");
        }
        return filterList;
    }

    /**
     * function filterByName() that searches for a product by the name entered
     * from the keyboard
     */
    @Override
    public void filterByName() {
        String productName;
        productName = Utils.getString("Please enter product name: ", "Product name is required!");
        showFilter(productName);
        if (Utils.isContinuing()) {
            showFilter(productName);

        }
    }

    /**
     * function to check a product by name exists in the list or not
     *
     * @param name
     * @return true/false
     */
    public boolean isExist(String name) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProductName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * function to check a product by name exists in the list or not
     *
     */
    @Override
    public void checkProductExist() {
        if (Login.getInstance().getCurrentUser().isADMIN() || Login.getInstance().getCurrentUser().isUser()) {
            String productName;
            productName = Utils.getID("Please enter product name with the pattern ( " + ProductModel.NAME_FORMAT + "): ", "Product name is required!", ProductModel.NAME_PATTERN);
            int index = this.searchName(productName);
            if (isExist(productName) || index > 0) {
                System.out.println("The product is existed!");
                System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
                System.out.println("| Product ID    |      Product  Name      |   Unit Price  |    Quantity    |     Status     |");
                System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
                this.get(index).show();
                System.out.println("+---------------+-------------------------+---------------+----------------+----------------+");
            } else {
                System.out.println("The product doesn't exist!");

            }
            if (Utils.isContinuing()) {
                checkProductExist();

            }
        } else {
            System.out.println("You don't have permission");

        }

    }

}
