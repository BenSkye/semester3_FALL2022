package model;

import managementsystem.Login;
import util.Utils;

/**
 *
 * @author LAPTOP_HONGQUAN
 */
public final class UserModel implements IObject,Comparable<UserModel> {

    private static final int ATTRIBUTE_COUNT = 2;
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String PREFIX_ADMIN = "A";
    public static final String PREFIX_USER = "U";

    public static final String ADMIN_PATTERN = PREFIX_ADMIN + "\\d{3}";
    public static final String USER_PATTERN = PREFIX_USER + "\\d{3}";

    private String accName; //ID
    private String pwd; //password

    //constructor
    /**
     *
     */
    public UserModel() {

    }

    public UserModel(String accName, String pwd) {
        setAccName(accName);
        setPwd(pwd);

    }

    /**
     * Getter and Setter
     *
     * @return
     */
    public String getAccName() {
        return accName;
    }

    public final void setAccName(String accName) {
        if (!accName.isEmpty()) {
            this.accName = accName;

        }
    }

    public String getPwd() {
        return pwd;
    }

    public final void setPwd(String pwd) {
        if (!pwd.isEmpty()) {
            this.pwd = pwd;
        }
    }

    @Override
    public String getId() {
        return accName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.accName);
        sb.append(Utils.SEPARATOR);
        sb.append(this.pwd);
        

        return sb.toString();
    }

    /**
     * function to convert string to an object
     *
     * @param stringObject
     * @return setAttribute
     */
    public int parseString(String stringObject) {
        if (stringObject != null) {
            return setAttribute(stringObject.split(Utils.SEPARATOR));
        }
        return 0;
    }

    protected int getAttributeCount() {
        return UserModel.ATTRIBUTE_COUNT;
    }

    public int setAttribute(String[] attributes) {
        int idx = 0;
        if (attributes != null && attributes.length >= getAttributeCount()) {
            setAccName(attributes[idx++].trim());
            setPwd(attributes[idx++].trim());
          
        }
        return idx;
    }

    /**
     * Show the user
     */
    @Override
    public void show() {
        System.out.printf("|%-25s|%-25s|\n", accName, pwd);

    }

    public boolean isADMIN() {
        if (Login.getInstance().getCurrentUser().getAccName().substring(0,1).equalsIgnoreCase(PREFIX_ADMIN)) {
            return true;
        }
        return false;
    }

    public boolean isUser() {
        if (Login.getInstance().getCurrentUser().getAccName().substring(0,1).equalsIgnoreCase(PREFIX_USER)) {
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(UserModel o) {
      return this.getAccName().compareToIgnoreCase(o.getAccName());
    }

}
