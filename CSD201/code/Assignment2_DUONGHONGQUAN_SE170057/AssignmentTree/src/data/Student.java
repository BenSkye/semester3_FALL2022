/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import util.Utils;

/**
 *
 * @author Administrator
 */
public final class Student {

    private int studentCode;
    private String name;
    private String gender;
    private double gpa_score;

    public Student(int studentCode, String name, String gender, double gpa_score) {
        this.studentCode = studentCode;
        setName(name);
        setGender(gender);
        setGpa_score(gpa_score);
    }

    public Student() {
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {

        this.studentCode = studentCode;
    }

    public String getName() {
        return name;
    }

    public final void setName(String name) {
        if (Utils.validateString(name)) {
            this.name = name;

        }
    }

    public String getGender() {
        return gender;
    }

    public final void setGender(String gender) {
        if (Utils.validateGender(gender)) {
            this.gender = gender;

        }
    }

    public double getGpa_score() {
        return gpa_score;
    }

    public final void setGpa_score(double gpa_score) {
        if (Utils.validateGpa(gpa_score)) {
            this.gpa_score = gpa_score;

        }
    }

    @Override
    public String toString() {
        return studentCode + " " + name + " " + gender + " " + gpa_score;
    }

}
