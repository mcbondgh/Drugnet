package drugnet.controllers.views;

import com.jfoenix.controls.JFXButton;
import drugnet.fetchedData.EmployeesData;
import drugnet.fetchedData.UsersData;
import drugnet.models.HumanResourceModel;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UsersManager extends HumanResourceModel implements Initializable {


    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillEmployeeSelector();
        fillUserRoleSelector();
    }


    @FXML
    JFXButton addUserButton, updateUserStatus;
    @FXML private TextField usernameField;
    @FXML private ComboBox<String> userRoleSelector, employeeSelector;
    @FXML
    MFXPasswordField passwordField, confirmPasswordField;


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


//        try {
//            SPECIAL_METHODS.FlipView("views/employeesview.fxml", borderPane);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF TRUE OR FALSE STATEMENTS.
     ******************************************************************************************************************/
    boolean isEmployeeSelectorEmpty() {return employeeSelector.getValue() == null;}
    boolean isUsernameEmpty() {return usernameField.getText().isEmpty();}
    boolean isUserRoleSelectorEmpty() {return userRoleSelector.getValue() == null;}
    boolean isPasswordEmpty() {return passwordField.getText().isEmpty();}
    boolean isConfirmPasswordEmpty() {return confirmPasswordField.getText().isEmpty();}


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
    public void fillUserRoleSelector() {
        String [] userRoles = {"Admin", "Cashier", "Customize", "Manager", "Pharmacist"};
        for (String item : userRoles) {
            userRoleSelector.getItems().add(item);
        }
    }



    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION...
     ******************************************************************************************************************/
    @FXML void addUserButtonClicked() {

    }

    @FXML void updateStatusButtonClicked() {

    }

    @FXML void checkForAllEmptyFields() {
        addUserButton.setDisable(isEmployeeSelectorEmpty() || isUsernameEmpty() || isUserRoleSelectorEmpty() || isPasswordEmpty() || isConfirmPasswordEmpty());
    }



}
