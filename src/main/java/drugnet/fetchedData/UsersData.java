package drugnet.fetchedData;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.sql.Timestamp;

public class UsersData {
    int id;
    String emp_id;
    String username;
    String password;
    String userRole;
    Label activeStatus;
    byte isAdmin;
    String addedBy;
    Timestamp dateAdded;
    CheckBox checkBox;

    public UsersData(int id, String emp_id, String username, String password, String userRole, Label activeStatus, byte isAdmin, String addedBy, Timestamp dateAdded, CheckBox checkBox) {
        this.id = id;
        this.emp_id = emp_id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.activeStatus = activeStatus;
        this.isAdmin = isAdmin;
        this.addedBy = addedBy;
        this.dateAdded = dateAdded;
        this.checkBox = checkBox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Label getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Label activeStatus) {
        this.activeStatus = activeStatus;
    }

    public byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }
}//end of class
