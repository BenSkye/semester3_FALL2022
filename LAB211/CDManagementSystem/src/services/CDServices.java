/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import dto.CDDao;
import dto.DaoInterface;
import java.util.ArrayList;
import java.util.List;
import javax.rmi.CORBA.Util;
import model.CDModel;
import util.Utils;
import static util.Utils.getString;
import static util.Utils.validateDate;
import view.Menu;

/**
 *
 * @author Administrator
 */
public class CDServices extends ArrayList<CDModel> implements CDDao {

    DaoInterface dao;

    private static final CDServices instance = new CDServices();

    public static CDServices getInstance() {
        return instance;
    }

    public void setDao(DaoInterface dao) {
        this.dao = dao;
    }

//    private CDServices(String filePath) {
//        super(filePath);
//    }
    private CDServices() {
//        dao = new DAOImple("CD.dat");
    }

    public boolean load() {
        dao.load();
        return addAll(dao.getDataList());
    }
    
     public boolean save() {
         return dao.save();
     }

    public List<CDModel> showfilter(String title) {
        List<CDModel> filterList = filter(title);
        if (filterList.isEmpty()) {
            System.out.println("No CD has this title");

        } else {
            System.out.println("+-------------------------+---------------+-------------------------+----------------+----------------+----------------+");
            System.out.println("|    Collection name      |    CD Type    |          Title          |   Unit Price   |       ID       |  Publish year  |");
            System.out.println("+-------------------------+---------------+-------------------------+----------------+----------------+----------------+");
            for (CDModel cd : filterList) {
                cd.show();
            }
            System.out.println("+-------------------------+---------------+-------------------------+----------------+----------------+----------------+");

        }
        return filterList;
    }

//    @Override
    public List<CDModel> filter(String title) {
        int count = 0;
        List<CDModel> filterList = new ArrayList<>();
        try {
            for (CDModel pro : this) {
                if (pro.getTitle().contains(title)) {
                    filterList.add(pro);
                    count++;
                }
            }
            System.out.println("There is/are " + count + "CD's that match the results you searched for");
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
    public void filterByTitle() {
        String title;

        title = Utils.getString("Please enter title name: ", "Title name is required!");
        showfilter(title);
        if (Utils.isContinuing()) {
            showFilter(title);

        }

    }

    public void showFilter(String name) {
        List<CDModel> filterList = filter(name);
        for (CDModel e : filterList) {
            e.show();
        }
    }
//    @Override
//    protected CDModel parseString(String stringObject) {
//        CDModel obj = new CDModel();
//        obj.parseString(stringObject);
//        return obj;
//    }

    public int countCD() {
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            count++;
        }
        return count;
    }

    public static String getCollectionName() {
        String CollectionName = null;
        Menu collection = new Menu("Please choose collection!");
        collection.addNewOption("1-Game");
        collection.addNewOption("2-Movie");
        collection.addNewOption("3-Music");

        int choiceCollection;

        collection.printMenu();
        choiceCollection = collection.getChoice();
        switch (choiceCollection) {
            case 1:
                CollectionName = "Game";
                break;
            case 2:
                CollectionName = "Movie";
                break;

            case 3:
                CollectionName = "Music";
                break;

        }
        return CollectionName;
    }

    public String getCollectionNameForUpdate() {
        String collectionName = null;
        do {
            collectionName = Utils.getString("Input collection name:", "Collection name is required!");

        } while (!collectionName.isEmpty());

        return collectionName;
    }

    public String getTypeForUpdate() {
        String type = null;
        do {
            type = Utils.getString("Input type:", "Type is required!");
        } while (!type.isEmpty() && !Utils.validateType(type));
        return type;
    }

    public String getType() {
        String Type = null;
        Menu collection = new Menu("Please choose type!");
        collection.addNewOption("1-Music");
        collection.addNewOption("2-Video");

        int choiceCollection;

        collection.printMenu();
        choiceCollection = collection.getChoice();
        switch (choiceCollection) {
            case 1:
                Type = "Audio";
                break;
            case 2:
                Type = "Video";
                break;

        }
        return Type;
    }

    public String getTitleForUpdate() {
        String title;

        title = Utils.getString("Input new title: ", "The title is required!");

        return title;
    }

    public String getUnitPriceForUpdate() {
        String unitPrice;
        unitPrice = Utils.getString("Input new price: ", "Price is required");
        return unitPrice;
    }

    public String getPublishYearForUpdate() {
        String year;
        do {
            year = Utils.getString("Input new year: ", "New year is required!");
        } while (!year.isEmpty() && !validateDate(year));
        return year;
    }

    @Override
    public void addCD() {
        String CollectionName;
        String CDType;
        String title;
        double unitPrice;
        String ID;
        String publishingYear;

        CollectionName = getCollectionName();
        CDType = getType();

        title = Utils.getString("Please input the title: ", "The title is required!");
//        do {
        unitPrice = Utils.getADouble("Please input the unit price: ", "The unit price is required!");
//        } while (!Utils.validateDouble(unitPrice));
        do {
            ID = Utils.getID("Please input the ID: ", "The is is required!");

        } while (!Utils.validateID(ID));
        do {
            publishingYear = Utils.getDate("Please input the publishing year:  ", "The publishing year is required");

        } while (!Utils.validateDate(publishingYear));
        CDModel cdmodel = new CDModel(CollectionName, CDType, title, unitPrice, ID, publishingYear);
        this.add(cdmodel);
        System.out.println("A CD is added sucessfully");
        if (Utils.isContinuing()) {
            addCD();

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
            if (this.get(i).getID().equalsIgnoreCase(ID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void updateCD() {
        String CollectionName;
        String CDType;
        String title;
        Double unitPrice;
        String ID;
        String publishingYear;
        int pos;

        pos = searchID(Utils.getString("Please input the ID you want to update: ", "The is is required!"));
        if (pos < 0) {
            System.out.println("Not found!");
        } else {

            this.get(pos).setCollectionName(getCollectionNameForUpdate());
            this.get(pos).setCDType(getTypeForUpdate());
            title = getTitleForUpdate();
            if (!title.isEmpty()) {
                this.get(pos).setTitle(title);

            }
            unitPrice = Utils.getADouble("Nhap gia", true);
            if (unitPrice != null) {
                this.get(pos).setUnitPrice(unitPrice);

            }
            publishingYear = getPublishYearForUpdate();
            if (!publishingYear.isEmpty() && Utils.validateDate(publishingYear)) {
                this.get(pos).setPublishingYear(publishingYear);

            }

        }
        if (Utils.isContinuing()) {
            updateCD();

        }

    }

    @Override
    public void removeCD() {
        String id;
        int pos;
        pos = searchID(Utils.getID("Please input the ID you want to remove: ", "The is is required!"));

        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {

            System.out.println("This is CD you want to remove ?");
            this.get(pos).show();
            String removep;
            do {
                removep = Utils.getString("Do you want to remove (Y/N) ?",
                        "Yes (Y) or No(N) ?");
            } while (!removep.equalsIgnoreCase("y") && !removep.equalsIgnoreCase("n"));

            if (removep.equalsIgnoreCase("y")) {
                this.remove(pos);
                System.out.println("The selected CD is removed successfully!");

            } else {
                System.out.println("The selected CD isn't removed !");
            }
        }
        if (Utils.isContinuing()) {
            removeCD();

        }
    }

}
