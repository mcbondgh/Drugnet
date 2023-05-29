package drugnet.fetchedData;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.sql.Timestamp;
import java.time.LocalDate;

public class EmployeesData {
    int id;
    String emp_id;
    String fullname;
    String mobileNumber;
    String digitalAddress;
    String emailAddress;
    String gender;
    String qualification;
    LocalDate joinedDate;
    String idType;
    String idNumber;
    String employeeType;
    Double salary;
    String notes;
    String addedBy;
    Label status;
    Timestamp dateCreated;
    CheckBox checkBox;

    public EmployeesData(int id, String emp_id, String fullname, String mobileNumber, String digitalAddress, String emailAddress, String gender, String qualification, LocalDate joinedDate, String idType, String idNumber, String employeeType, Double salary, String notes,  Label status, Timestamp dateCreated, CheckBox checkBox) {
        this.id = id;
        this.emp_id = emp_id;
        this.fullname = fullname;
        this.mobileNumber = mobileNumber;
        this.digitalAddress = digitalAddress;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.qualification = qualification;
        this.joinedDate = joinedDate;
        this.idType = idType;
        this.idNumber = idNumber;
        this.employeeType = employeeType;
        this.salary = salary;
        this.notes = notes;
        this.status = status;
        this.dateCreated = dateCreated;
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

    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getDigitalAddress() {
        return digitalAddress;
    }

    public void setDigitalAddress(String digitalAddress) {
        this.digitalAddress = digitalAddress;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(LocalDate joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Label getStatus() {
        return status;
    }

    public void setStatus(Label status) {
        this.status = status;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }
}// end of class
