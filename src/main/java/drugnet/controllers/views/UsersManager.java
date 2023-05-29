package drugnet.controllers.views;

import com.jfoenix.controls.JFXButton;
import drugnet.alerts.UserAlerts;
import drugnet.alerts.UserNotification;
import drugnet.controllers.HomePage;
import drugnet.controllers.Reports;
import drugnet.fetchedData.EmployeesData;
import drugnet.fetchedData.UsersData;
import drugnet.models.HumanResourceModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.security.cert.TrustAnchor;
import java.util.Objects;
import java.util.ResourceBundle;

public class UsersManager extends HumanResourceModel implements Initializable {
    
    UserAlerts ALERTS;
    UserNotification NOTIFICATION = new UserNotification();
    int activeUserId = getUserIdByUsername(HomePage.staticActiveUser);


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillEmployeeSelector();
        fillUserRoleSelector();
        populateUsersTable();

    }


    @FXML
    JFXButton addUserButton, updateUserStatus;
    @FXML private TextField usernameField;
    @FXML private ComboBox<String> userRoleSelector, employeeSelector;
    @FXML
    MFXPasswordField passwordField, confirmPasswordField;
    @FXML
    Label passwordIndicator, validUserIndicator;


     /*******************************************************************************************************************
     **************************** EMPLOYEES TABLE VIEW COLUMNS.
     ******************************************************************************************************************/
    @FXML
    private MFXLegacyTableView<UsersData> usersTable;
    @FXML private TableColumn<UsersData, String> userIdColumn;
    @FXML private TableColumn<UsersData, String> usernameColumn;
    @FXML private TableColumn<UsersData, String> userRoleColumn;
    @FXML private TableColumn<UsersData, String> userStatusColumn;
    @FXML private TableColumn<UsersData, CheckBox> userActionColumn;

    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF TRUE OR FALSE STATEMENTS.
     ******************************************************************************************************************/
    boolean isEmployeeSelectorEmpty() {return employeeSelector.getValue() == null;}
    boolean isUsernameEmpty() {return usernameField.getText().isEmpty();}
    boolean isUserRoleSelectorEmpty() {return userRoleSelector.getValue() == null;}
    boolean isPasswordEmpty() {return passwordField.getText().isEmpty();}
    boolean isConfirmPasswordEmpty() {return confirmPasswordField.getText().isEmpty();}
    boolean passwordMatchResult() {
        return passwordField.getText().equals(confirmPasswordField.getText());
    }


    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF OTHER METHODS.
     ******************************************************************************************************************/
    void fillEmployeeSelector() {
        for (EmployeesData item : getAllEmployees()) {
            if (item.getStatus().getText().equals("active")) {
                employeeSelector.getItems().add(item.getFullname());
            }
            for (UsersData users : getAllUsers()) {
                if (users.getEmp_id().equals(item.getEmp_id())) {
                    employeeSelector.getItems().remove(item.getFullname());
                }
            }
        }
    }
    public void fillUserRoleSelector() {
        String [] userRoles = {"Admin", "Cashier", "Customize", "Manager", "Pharmacist"};
        for (String item : userRoles) {
            userRoleSelector.getItems().add(item);
        }
    }

    @FXML void validatePasswordFields() {
        passwordIndicator.setVisible(!passwordMatchResult());
        addUserButton.setDisable(!passwordMatchResult());
    }
    @FXML void populateUsersTable() {
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        userStatusColumn.setCellValueFactory(new PropertyValueFactory<>("activeStatus"));
        userActionColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        usersTable.setItems(getAllUsers());
    }
    void refreshUsersTable() {
        usersTable.getItems().clear();
        populateUsersTable();
    }

    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION...
     ******************************************************************************************************************/
    @FXML void addUserButtonClicked() {
        if (addUserButton.getText().equals("🔄 Update")) {

        }else {

        }
    }
    @FXML void updateStatusButtonClicked() {
        for (UsersData item : usersTable.getItems()) {
            byte statusValue = 0;
            String emp_id = item.getEmp_id();
            if (item.getCheckBox().isSelected()) {
                statusValue = 1;
            }
            updateUserStatus(statusValue, emp_id);
        }
        refreshUsersTable();
    }
    @FXML void checkForAllEmptyFields() {
        addUserButton.setDisable(isEmployeeSelectorEmpty() || isUsernameEmpty() || isUserRoleSelectorEmpty() || isPasswordEmpty() || isConfirmPasswordEmpty());
    }
    @FXML void selectedEmployeeOnAction() {
       String selectedValue = employeeSelector.getValue();
       for (EmployeesData items : getAllEmployees()) {
        if (items.getFullname().equals(selectedValue) ) {
            usernameField.setText(items.getEmailAddress());
            break;
        }
       }
    }

    @FXML void getSelectedTableItem() {
        if (!usersTable.getSelectionModel().isEmpty()) {
            String username = usersTable.getSelectionModel().getSelectedItem().getUsername();
            String userRole = usersTable.getSelectionModel().getSelectedItem().getUserRole();
            String emp_id = usersTable.getSelectionModel().getSelectedItem().getEmp_id();
            usernameField.setText(username);
            userRoleSelector.setValue(userRole);
            employeeSelector.setValue(emp_id);
            employeeSelector.setDisable(true);
            addUserButton.setText("🔄 Update");
        }
    }
}//end of class
