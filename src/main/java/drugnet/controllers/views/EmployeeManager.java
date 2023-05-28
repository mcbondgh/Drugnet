package drugnet.controllers.views;

import com.jfoenix.controls.JFXButton;
import drugnet.alerts.UserAlerts;
import drugnet.alerts.UserNotification;
import drugnet.controllers.HomePage;
import drugnet.encryption.DefaultPassword;
import drugnet.encryption.EncryptionDecryption;
import drugnet.fetchedData.EmployeesData;
import drugnet.models.HumanResourceModel;
import drugnet.multistages.MultiStages;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeManager extends HumanResourceModel implements Initializable {
    UserAlerts ALERTS;
    UserNotification NOTIFICATION = new UserNotification();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillGenderSelector();
        fillUserRoleSelector();
        fillEmployeeTypeSelector();
        fillIdTypeSelector();
        saveEmployeeButtonClicked();
        employeeIdLabel.setText(generateEmpId());
    }

    /*******************************************************************************************************************
     **************************** FXML NODE EJECTIONS.
     ******************************************************************************************************************/
    @FXML private JFXButton addEmployeeButton, saveEmployeeButton;
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
    @NotNull
    private String generateEmpId() {
        String emp_id = "";
        int count = countEmployees() + 1;
        if (count <= 9) {
            emp_id = "emp-00000" + count;
        } else if (count < 100) {
            emp_id = "emp-0000" + count;
        } else if(count < 1000) {
            emp_id = "emp-000" + count;
        } else if (count < 10000) {
            emp_id = "emp-00" + count;
        } else  emp_id = "emp-0" + count;
        return emp_id;
    }
    private void resetFields() {
        employeeIdLabel.setText(generateEmpId());
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
        saveEmployeeButton.setDisable(isFullnameEmpty() || isMobileNumberEmpty() || isEmailFieldEmpty() || isDigitalFieldEmpty() || isGenderEmpty() || isQualificationEmpty()
                || isIdNumberEmpty() || isIdTypeEmpty() || isEmployeeTypeEmpty() || isSalaryEmpty() || isDateJoinedEmpty());
    }



    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION.
     ******************************************************************************************************************/
    @FXML void saveEmployeeButtonClicked() {
        try {
            saveEmployeeButton.setOnAction(actionEvent -> {
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
                String userRole = userRoleSelector.getValue();

                int activeUserId = getUserIdByUsername(HomePage.staticActiveUser);
                String defaultPassword = DefaultPassword.defaultPassword;
                String encryptedPassword = EncryptionDecryption.passwordEncryptor(defaultPassword);

                if (isUserRoleChecked()) {
                    saveEmployeeButton.setDisable(isUserRoleEmpty());
                    ALERTS = new UserAlerts("SAVE EMPLOYEE", "DO YOU WISH TO ADD '" + fullname + "' TO YOUR LIST OF EMPLOYEES AND SET AS A NEW USER TOO?", "please confirm your action to add else CANCEL to abort.");
                    if (ALERTS.confirmationAlert()) {
                        int flag = saveEmployee(emp_id, fullname, mobileNumber, email, digitalAddress, gender, qualification, idType, idNumber, employeeType, salary, joinedDate, comments, activeUserId );
                        flag += saveUser(emp_id, userRole, email, encryptedPassword, activeUserId);
                        if (flag == 2) {
                            NOTIFICATION.successNotification("SUCCESSFUL OPERATION", "Employee And User Account Successfully Created.");
                            resetFields();
                            userRoleSelector.setValue(null);
                            userRoleCheckBox.setSelected(false);
                            userRoleSelector.setDisable(true);
                        }
                    }
                } else {
                    ALERTS = new UserAlerts("SAVE EMPLOYEE", "DO YOU WISH TO ADD '" + fullname + "' TO YOUR LIST OF EMPLOYEES", "please confirm your action to add else CANCEL to abort.");
                    if (ALERTS.confirmationAlert()) {
                        int flag = saveEmployee(emp_id, fullname, mobileNumber, email, digitalAddress, gender, qualification, idType, idNumber, employeeType, salary, joinedDate, comments, activeUserId );
                        if (flag > 0) {
                            NOTIFICATION.successNotification("EMPLOYEE ADDED", "Successfully add new employee to list.");
                            resetFields();
                        }
                    }
                }
            });
        }catch (Exception ignored) {}

    }// end of method.


    @FXML void userRoleButtonSelected() {
        if (isUserRoleChecked()) {
            userRoleSelector.setDisable(false);
        } else {
            userRoleSelector.setDisable(true);
        }
    }


}//End of class...
