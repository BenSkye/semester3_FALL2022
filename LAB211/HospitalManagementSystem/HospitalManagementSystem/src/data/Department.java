/**
 * Class for Department
 */
package data;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Department {

    private String departmentID;
    private String name;
    private String createDate;
    private String lastUpdateDate;

    public Department() {
    }
//CONSTRUCTOR

    public Department(String departmentID, String name, String createDate, String lastUpdateDate) {
        this.departmentID = departmentID;
        this.name = name;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }
//GETTER AND SETTER

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
//TOSTRING

    @Override
    public String toString() {
        return departmentID + ", " + name + ", " + createDate + ", " + lastUpdateDate;
    }
//SHOW INFO THEO FORMAT

    public void showInfo() {
        System.out.printf("|%-15s|%-25s|%-14s|%-16s|\n", departmentID, name, createDate, lastUpdateDate);

    }

}
