package drugnet.controllers.views;

import com.jfoenix.controls.JFXButton;
import drugnet.alerts.UserAlerts;
import drugnet.alerts.UserNotification;
import drugnet.fetchedData.EmployeesData;
import drugnet.models.HumanResourceModel;
import drugnet.multistages.MultiStages;
import io.github.palexdev.materialfx.controls.legacy.MFXLegacyTableView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ConcurrentModificationException;
import java.util.ResourceBundle;

public class ViewEmployees  extends HumanResourceModel implements Initializable {
    UserAlerts ALERTS;
    UserNotification NOTIFICATION = new UserNotification();

    /**
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateEmployeesTable();
    }


    @FXML private JFXButton addEmployeeButton, updateStatusButton;


    /*******************************************************************************************************************
     **************************** EMPLOYEES TABLE VIEW COLUMNS.
     ******************************************************************************************************************/
    @FXML
    private MFXLegacyTableView<EmployeesData> employeesTable;
    @FXML private TableColumn<EmployeesData, String> employeeIdColumn;
    @FXML private TableColumn<EmployeesData, String> fullnameColumn;
    @FXML private TableColumn<EmployeesData, String> employeeMobileNumberColumn;
    @FXML private TableColumn<EmployeesData, String> employeeEmailAddressColumn;
    @FXML private TableColumn<EmployeesData, String> employmentTypeColumn;
    @FXML private TableColumn<EmployeesData, Double> employeeSalaryColumn;
    @FXML private TableColumn<EmployeesData, Label> statusColumn;
    @FXML private TableColumn<EmployeesData, CheckBox> actionColumn;


    /*******************************************************************************************************************
     **************************** IMPLEMENTATION OF OTHER METHODS.
     ******************************************************************************************************************/
    void populateEmployeesTable() {
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        employeeMobileNumberColumn.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        employeeEmailAddressColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        employmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("employeeType"));
        employeeSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
        employeesTable.setItems(getAllEmployees());
    }
    void refreshEmployeesTable() {
        employeesTable.getItems().clear();
        populateEmployeesTable();
    }

    /*******************************************************************************************************************
     **************************** ACTION EVENT METHODS IMPLEMENTATION...
     ******************************************************************************************************************/
    @FXML void addEmployeeButtonOnAction() throws IOException {
        MultiStages.addEmployee();
    }
    @FXML void updateEmployeeStatusButtonClicked() throws ConcurrentModificationException {
        ALERTS = new UserAlerts("UPDATE EMPLOYEE ACTIVE STATUS.", "THIS PROCESS WILL SET ALL CHECKED BOXES AS ACTIVE AND UNCHECKED AS INACTIVE EMPLOYEES", "please do you wish to save this process? Please confirm to execute else CANCEL to abort.");
        if (ALERTS.confirmationAlert()) {
            byte statusValue;
            for (EmployeesData item : employeesTable.getItems()) {
                if (item.getCheckBox().isSelected()) {
                    statusValue = 1;
                } else {
                   statusValue = 0;
                }
                updateEmployeeStatus(statusValue, item.getEmp_id());
            }
            refreshEmployeesTable();
        }
    }
    @FXML void employeeSelected(MouseEvent e) throws IOException {
        if (e.getClickCount() == 2) {
            String empId =  employeesTable.getSelectionModel().getSelectedItem().getEmp_id();
            for (EmployeesData item : getAllEmployees()) {
                if (item.getEmp_id().equals(empId)) {
                    UpdateEmployee.staticEmployeeID = item.getEmp_id();
                    UpdateEmployee.staticFullname = item.getFullname();
                    UpdateEmployee.staticMobileNumber = item.getMobileNumber();
                    UpdateEmployee.staticEmailAddress = item.getEmailAddress();
                    UpdateEmployee.staticDigitalAddress = item.getDigitalAddress();
                    UpdateEmployee.staticGender = item.getGender();
                    UpdateEmployee.staticQualification = item.getQualification();
                    UpdateEmployee.staticIdType = item.getIdType();
                    UpdateEmployee.staticIdNumber = item.getIdNumber();
                    UpdateEmployee.staticEmployedAs = item.getEmployeeType();
                    UpdateEmployee.staticSalary = item.getSalary();
                    UpdateEmployee.staticDateJoined = item.getJoinedDate();
                    UpdateEmployee.staticNote = item.getNotes();
                    break;
                }
            }
            MultiStages.updateEmployeeStage();
        }
    }


    }// end of class...
