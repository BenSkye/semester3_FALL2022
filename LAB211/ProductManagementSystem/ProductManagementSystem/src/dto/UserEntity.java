/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public class UserEntity {

    private String accName; //ID

    private String pwd; //password

    public UserEntity(String accName, String pwd) {
        this.accName = accName;
        this.pwd = pwd;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "accName=" + accName + ", pwd=" + pwd + '}';
    }

}
