/**
 * Class for Doctor
 */
package data;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Doctor {

    private String doctorID;
    private String name;
    private String sex;
    private String address;
    private String departmentID;
    private String createDate;
    private String lastUpdateDate;
//CONSTRUCTOR

    public Doctor(String doctorID, String name, String sex, String address, String departmentID, String createDate, String lastUpdateDate) {
        this.doctorID = doctorID;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.departmentID = departmentID;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
    }

    public Doctor() {
    }
//GETTER AND SETTER

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public String setSex(String sex) {
        this.sex = sex;
        return sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
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
        return doctorID + ", " + name + ", " + sex + ", " + address + ", " + departmentID + ", " + createDate + ", " + lastUpdateDate;
    }
//SHOW INFO THEO FORMAT

    public void showInfo() {
        System.out.printf("|%-14s|%-25s|%-14s|%-14s|%-15s|%-16s|%-18s|\n", doctorID, name, sex, address, departmentID, createDate, lastUpdateDate);

    }
}
