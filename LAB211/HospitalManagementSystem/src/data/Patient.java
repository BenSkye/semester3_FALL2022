/**
 * Class for Patient
 */
package data;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Patient {

    private String patientID;
    private String name;
    private int age;
    private String address;
    
//CONSTRUCTOR

    public Patient() {
    }

    public Patient(String patientID, String name, int age, String address) {
        this.patientID = patientID;
        this.name = name;
        this.age = age;
        this.address = address;
    }
//GETTER AND SETTER
    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//TOSTRING
    @Override
    public String toString() {
        return patientID + ", " + name + ", " + age + ", " + address;
    }
//SHOW INFO THEO FORMAT
    public void showInfo() {
        System.out.printf("|%-15s|%-25s|%-13s|%-13s|\n", patientID, name, age, address);

    }
}
