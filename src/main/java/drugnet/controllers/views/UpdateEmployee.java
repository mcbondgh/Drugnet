package drugnet.controllers.views;

import com.jfoenix.controls.JFXButton;
import drugnet.alerts.UserAlerts;
import drugnet.alerts.UserNotification;
import drugnet.controllers.HomePage;
import drugnet.encryption.DefaultPassword;
import drugnet.encryption.EncryptionDecryption;
import drugnet.models.HumanResourceModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateEmployee extends HumanResourceModel implements Initializable {
    UserAlerts ALERTS;
    UserNotification NOTIFICATION = new UserNotification();


    static String staticEmployeeID, staticFullname, staticMobileNumber, staticEmailAddress, staticDigitalAddress, staticGender;
    static String staticQualification, staticIdType, staticIdNumber, staticEmployedAs, staticNote;
    static LocalDate staticDateJoined;
    static Double staticSalary;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGenderSelector();
        fillUserRoleSelector();
        fillEmployeeTypeSelector();
        fillIdTypeSelector();
        updateEmployeeButtonClicked();
        setStaticFieldsForEmployeeUpdate();
    }

    /*******************************************************************************************************************
     **************************** FXML NODE EJECTIONS.
     ******************************************************************************************************************/
    @FXML
    private JFXButton updateEmployeeButton;
    @FXML private TextField fullnameField, mobileNumberField, digitalAddressField, emailAddressField;
    @FXML private TextField idNumberField, qualificationField, salaryField;
    @FXML private TextArea commentsArea;
    @FXML private ComboBox<String> genderSelector, employeeTypeSelector, idTypeSelector, userRoleSelector;
    @FXML private Label employeeIdLabel;
    @FXML private CheckBox userRoleCheckBox;
    @FXML private DatePicker joinedDatePicker;



    /*******************************************************************************************************************
     **************************** TRUE OR FALSE STATEMENTS....
     ******************************************************************************************************************/
    boolean isUserRoleChecked() {return userRoleCheckBox.isSelected();}
    boolean isFullnameEmpty() {return fullnameField.getText().isEmpty();}
    boolean isMobileNumberEmpty() {return mobileNumberField.getText().isEmpty();}
    boolean isEmailFieldEmpty(){return emailAddressField.getText().isEmpty();}
    boolean isDigitalFieldEmpty() {return digitalAddressField.getText().isEmpty();}
    boolean isGenderEmpty() {return genderSelector.getValue() ==  null;}
    boolean isQualificationEmpty() {return qualificationField.getText().isEmpty();}
    boolean isIdTypeEmpty() {return idTypeSelector.getValue() == null;}
    boolean isIdNumberEmpty() {return idNumberField.getText().isEmpty();}
    boolean isEmployeeTypeEmpty() {return employeeTypeSelector.getValue() == null;}
    boolean isSalaryEmpty() {return salaryField.getText().isEmpty();}
    boolean isDateJoinedEmpty() {return joinedDatePicker.getValue() == null;}
    boolean isUserRoleEmpty() {return userRoleSelector.getValue() == null;}



    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF OTHER METHODS.
     ******************************************************************************************************************/
    void setStaticFieldsForEmployeeUpdate() {
        employeeIdLabel.setText(staticEmployeeID);
        fullnameField.setText(staticFullname);
        mobileNumberField.setText(staticMobileNumber);
        emailAddressField.setText(staticEmailAddress);
        digitalAddressField.setText(staticDigitalAddress);
        genderSelector.setValue(staticGender);
        qualificationField.setText(staticQualification);
        idNumberField.setText(staticIdNumber);
        idTypeSelector.setValue(staticIdType);
        employeeTypeSelector.setValue(staticEmployedAs);
        salaryField.setText(String.valueOf(staticSalary));
        joinedDatePicker.setValue(staticDateJoined);
        commentsArea.setText(staticNote);
    }
    public void fillIdTypeSelector() {
        String[] items = {"National Id", "Passport", "Voter Id", "Driving License", "NHIS", "SSNIT CARD"};
        for (String item : items) {
            idTypeSelector.getItems().add(item);
        }
    }
    public void fillGenderSelector() {
        String [] gender = {"Female", "Male", "Other"};
        for (String item: gender) {
            genderSelector.getItems().add(item);
        }
    }
    public void fillEmployeeTypeSelector() {
        String [] employeeType = {"Pharmacist", "Security", "Cleaner", "Auditor", "Sales Personnel"};
        for (String item : employeeType) {
            employeeTypeSelector.getItems().add(item);
        }
    }
    public void fillUserRoleSelector() {
        String [] userRoles = {"Admin", "Cashier", "Customize", "Manager", "Pharmacist"};
        for (String item : userRoles) {
            userRoleSelector.getItems().add(item);
        }
    }

    private void resetFields() {
        fullnameField.clear();
        mobileNumberField.clear();
        digitalAddressField.clear();
        emailAddressField.clear();
        genderSelector.setValue(null);
        qualificationField.clear();
        idTypeSelector.setValue(null);
        idNumberField.clear();
        employeeTypeSelector.setValue(null);
        salaryField.clear();
        joinedDatePicker.setValue(null);
        commentsArea.clear();
    }


    /*******************************************************************************************************************
     **************************** INPUT VALIDATIONS
     ******************************************************************************************************************/
    @FXML void validateMobileNumberInput(KeyEvent e) {
        if (!(e.getCode().isDigitKey() || e.getCode().isArrowKey() || e.getCode().equals(KeyCode.BACK_SPACE))) {
            mobileNumberField.deletePreviousChar();
            mobileNumberField.deleteNextChar();
        }if (mobileNumberField.getText().length() > 10) {
            mobileNumberField.deletePreviousChar();
            mobileNumberField.deleteNextChar();
        }
    }
    @FXML void validateSalaryInput(KeyEvent e) {
        if (!(e.getCode().isDigitKey() || e.getCode().isArrowKey() || e.getCode().equals(KeyCode.BACK_SPACE) || e.getCode().equals(KeyCode.PERIOD))) {
            salaryField.deletePreviousChar();
            salaryField.deleteNextChar();
        }
    }
    @FXML void disableButtonOnFieldEmpty() {
        updateEmployeeButton.setDisable(isFullnameEmpty() || isMobileNumberEmpty() || isEmailFieldEmpty() || isDigitalFieldEmpty() || isGenderEmpty() || isQualificationEmpty()
                || isIdNumberEmpty() || isIdTypeEmpty() || isEmployeeTypeEmpty() || isSalaryEmpty() || isDateJoinedEmpty());
    }



    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION.
     ******************************************************************************************************************/
    @FXML void updateEmployeeButtonClicked() {
        try {
            updateEmployeeButton.setOnAction(actionEvent -> {
                String emp_id = employeeIdLabel.getText();
                String fullname = fullnameField.getText();
                String mobileNumber = mobileNumberField.getText();
                String email = emailAddressField.getText();
                String digitalAddress = digitalAddressField.getText();
                String gender = genderSelector.getValue();
                String qualification = qualificationField.getText();
                String idType = idTypeSelector.getValue();
                String idNumber = idNumberField.getText();
                String employeeType = employeeTypeSelector.getValue();
                Double salary = Double.valueOf(salaryField.getText());
                LocalDate joinedDate = joinedDatePicker.getValue();
                String comments = commentsArea.getText();

                int activeUserId = getUserIdByUsername(HomePage.staticActiveUser);
                ALERTS = new UserAlerts("UPDATE EMPLOYEE", "DO YOU WISH TO UPDATE EMPLOYEE WITH NAME '" + fullname + "' AND ID " + emp_id + "?", "please confirm your action to add else CANCEL to abort.");
                    if (ALERTS.confirmationAlert()) {
                        int flag = updateEmployeeData(emp_id, fullname, mobileNumber, email, digitalAddress, gender, qualification, idType, idNumber, employeeType, salary, joinedDate, comments, activeUserId);
                        if (flag > 0) {
                            updateEmployeeButton.getScene().getWindow().hide();
                            NOTIFICATION.successNotification("UPDATE SUCCESSFUL", "Selected Employee Data Successfully Update. Refresh View To See Changes");
                        }
                    }
            });
        }catch (Exception ignored) {}

    }// end of method.
}//end of class...
