/**
 * Class for Examination
 */
package data;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class Examination {

    private String examinationID;
    private String doctorID;
    private String patientID;
    private String result;
    private String date;
//CONSTRUCTOR

    public Examination() {
    }

    public Examination(String examinationID, String doctorID, String patientID, String result, String date) {
        this.examinationID = examinationID;
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.result = result;
        this.date = date;
    }
//GETTER AND SETTER

    public String getExaminationID() {
        return examinationID;
    }

    public void setExaminationID(String examinationID) {
        this.examinationID = examinationID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
//TOSTRING

    @Override
    public String toString() {
        return examinationID + ", " + doctorID + ", " + patientID + ", " + result + ", " + date;
    }
//SHOW INFO THEO FORMAT

    public void showInfo() {
        System.out.printf("|%-16s|%-15s|%-14s|%-13s|%-13s|\n", examinationID, doctorID, patientID, result, date);

    }
}
