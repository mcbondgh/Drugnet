package drugnet.controllers;

import com.jfoenix.controls.JFXButton;
import drugnet.fetchedData.EmployeesData;
import drugnet.fetchedData.UsersData;
import drugnet.models.HumanResourceModel;
import drugnet.multistages.MultiStages;
import drugnet.special_methods.SpecialMethods;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HumanResources extends HumanResourceModel implements Initializable {
    SpecialMethods SPECIAL_METHODS = new SpecialMethods();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillEmployeeSelector();
//        try {
//            SPECIAL_METHODS.FlipView("views/employeesview.fxml", borderPane);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    /*******************************************************************************************************************
     **************************** FXML NODE EJECTIONS
     ******************************************************************************************************************/
    @FXML private MFXButton employeesViewButton, userViewButton, userRolesViewButton, userLogsViewButton, payRollViewButton;
    @FXML private BorderPane borderPane;
    @FXML
    JFXButton addUserButton, updateUserStatus;
    @FXML private TextField usernameField;
    @FXML private ComboBox<String> userRoleSelector, employeeSelector;
    @FXML
    MFXPasswordField passwordField, confirmPasswordField;



    /*******************************************************************************************************************
     **************************** EMPLOYEES TABLE VIEW COLUMNS.
     ******************************************************************************************************************/
    @FXML private MFXLegacyTableView<EmployeesData> employeesTable;
    @FXML private TableColumn<EmployeesData, String> employeeIdColumn;
    @FXML private TableColumn<EmployeesData, String> fullnameColumn;
    @FXML private TableColumn<EmployeesData, String> employeeMobileNumberColumn;
    @FXML private TableColumn<EmployeesData, String> employeeEmailAddressColumn;
    @FXML private TableColumn<EmployeesData, String> employmentTypeColumn;
    @FXML private TableColumn<EmployeesData, Double> employeeSalaryColumn;
    @FXML private TableColumn<EmployeesData, Label> statusColumn;
    @FXML private TableColumn<EmployeesData, ComboBox<String>> actionColumn;

    @FXML private MFXLegacyTableView<UsersData> usersTable;
    @FXML private TableColumn<UsersData, String> userIdColumn;
    @FXML private TableColumn<UsersData, String> usernameColumn;
    @FXML private TableColumn<UsersData, String> userRoleColumn;
    @FXML private TableColumn<UsersData, String> userStatusColumn;
    @FXML private TableColumn<UsersData, CheckBox> userActionColumn;


    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF OTHER METHODS.
     ******************************************************************************************************************/
    void fillEmployeeSelector() {
        for(EmployeesData item : getAllEmployees()) {
            if (item.getStatus().equals("active")) {
                employeeSelector.getItems().add(item.getFullname());
            }
        }
    }

    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF TRUE OR FALSE STATEMENTS.
     ******************************************************************************************************************/
    boolean isEmployeeSelectorEmpty() {return employeeSelector.getValue() == null;}
    boolean isUsernameEmpty() {return usernameField.getText().isEmpty();}
    boolean isUserRoleSelectorEmpty() {return userRoleSelector.getValue() == null;}
    boolean isPasswordEmpty() {return passwordField.getText().isEmpty();}
    boolean isConfirmPasswordEmpty() {return confirmPasswordField.getText().isEmpty();}

    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION...
     ******************************************************************************************************************/
    @FXML void employeesViewButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("views/employeesview.fxml", borderPane);
    }
    @FXML void userViewButtonClicked() throws IOException {
        SPECIAL_METHODS.FlipView("views/addusers.fxml", borderPane);
    }
    @FXML void addEmployeeButtonOnAction() throws IOException {
        MultiStages.addEmployee();
    }

    @FXML void addUserButtonClicked() {

    }

    @FXML void updateStatusButtonClicked() {

    }

    @FXML void checkForAllEmptyFields() {
        addUserButton.setDisable(isEmployeeSelectorEmpty() || isUsernameEmpty() || isUserRoleSelectorEmpty() || isPasswordEmpty() || isConfirmPasswordEmpty());
    }


}//end of class
