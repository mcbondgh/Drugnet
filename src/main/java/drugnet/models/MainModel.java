package drugnet.models;

import drugnet.dbconfig.DbAPI;
import drugnet.fetchedData.EmployeesData;
import drugnet.fetchedData.SmsApiData;
import drugnet.fetchedData.UsersData;
import drugnet.tableViewData.BrandsTableData;
import drugnet.tableViewData.SuppliersTableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainModel extends DbAPI {
    protected int getUserIdByUsername(String username) {
        int userId = 0;
        try {
            String select = "SELECT user_id FROM users WHERE(username = '"+ username +"')";
            prepare = DB_CONNECT().prepareStatement(select);
            resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt(1);
            }
        }catch (SQLException ignored){}
        return userId;
    }
    protected List<Object> getStoreInfo() {
        List<Object> data = new ArrayList<>();
        try {
            String select = "SELECT * FROM storeinformation;";
            prepare = DB_CONNECT().prepareStatement(select);
            resultSet = prepare.executeQuery();
            while (resultSet.next()) {
                data.add(resultSet.getString(1)); // store_name; 0
                data.add(resultSet.getString(2));//store_address; 1
                data.add(resultSet.getString(3));//store_number 2
                data.add(resultSet.getString(4));//store_email 3
                data.add(resultSet.getString(5));//store_logo 4
                data.add(resultSet.getString(6));//manager_name 5
                data.add(resultSet.getString(7));//manager_email 6
                data.add(resultSet.getString(8));//manager_number 7
                data.add(resultSet.getInt(9));//userId; 8
                data.add(resultSet.getString(10));//date_created 9
            }
            prepare.close();
            resultSet.close();
            DB_CONNECT().close();
        }catch (SQLException igored) {}

        return data;
    }
    protected ObservableList<SuppliersTableData> getSuppliesData() {
        ObservableList<SuppliersTableData> data = FXCollections.observableArrayList();
        try {
            String select = "SELECT * FROM suppliers";
            prepare = DB_CONNECT().prepareStatement(select);
            resultSet = prepare.executeQuery();
            while(resultSet.next()) {
                int supplierId = resultSet.getInt(1);
                String supplierName = resultSet.getString(2);
                String mobileNumber = resultSet.getString(3);
                String address = resultSet.getString(4);
                String location = resultSet.getString(5);
                String comments = resultSet.getString(6);
                Timestamp date_created = resultSet.getTimestamp(7);
                data.add(new SuppliersTableData(supplierId, supplierName, mobileNumber, location, address, comments, date_created ));
            }
            prepare.close();
            resultSet.close();
            DB_CONNECT().close();
        }catch (SQLException e) {e.printStackTrace();}

        return data;
    }
    protected ObservableList<BrandsTableData> getBrandsData() {
        ObservableList<BrandsTableData> data = FXCollections.observableArrayList();
        try {
            String select = "SELECT * FROM brands";
            prepare = DB_CONNECT().prepareStatement(select);
            resultSet = prepare.executeQuery();
            while(resultSet.next()) {
                int brandId = resultSet.getInt(1);
                String brandName = resultSet.getString(2);
                String manufacturerName = resultSet.getString(3);
                Timestamp date_created = resultSet.getTimestamp(4);
                data.add(new BrandsTableData(brandId, brandName, manufacturerName, date_created));
            }
            prepare.close();
            resultSet.close();
            DB_CONNECT().close();
        }catch (SQLException e) {e.printStackTrace();}
        return data;
    }
    protected List<Object> getSmsApi() {
        List<Object> data = new ArrayList<>();
        try {
            prepare = DB_CONNECT().prepareStatement("SELECT * FROM sms_api");
            resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("api_id");// index 0 -> api_id
                String api_key = resultSet.getString("api_key");// index 1 -> api_key
                String sender_id= resultSet.getString("sender_id"); // index 2 -> sender_id;
                Timestamp timestamp = resultSet.getTimestamp("date_created"); // index 3 -> date_created;
                data.add(0, id);
                data.add(1, api_key);
                data.add(2, sender_id);
                data.add(3, timestamp);
            }
            prepare.close();
            resultSet.close();
            DB_CONNECT().close();
        }catch (SQLException ignored) {}
        return data;
    }
    public int countEmployees() {
        int counter = 0;
        try {
            prepare = DB_CONNECT().prepareStatement("SELECT COUNT(*) FROM employees;");
            resultSet = prepare.executeQuery();
            if (resultSet.next()) {
                counter = resultSet.getInt(1);
            }
        }catch (SQLException ignored) {}
        return counter;
    }

    protected ObservableList<EmployeesData> getAllEmployees() {
        ObservableList<EmployeesData> data = FXCollections.observableArrayList();
        try {
            String select = "SELECT `id`, e.`emp_id`, `fullname`, `emp_mobile_number`, `emp_email`, `emp_digital_address`, " +
                    "`emp_gender`, `emp_qualification`, `emp_idtype`, `emp_idnumber`, `employed_as`, `emp_salary`, " +
                    "`date_joined`, `emp_notes`, e.`is_active`, `date_modified` FROM employees AS e;";
            prepare = DB_CONNECT().prepareStatement(select);
            resultSet = prepare.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String emp_id = resultSet.getString("e.emp_id");
                String fullname = resultSet.getString("fullname");
                String mobileNumber = resultSet.getString("emp_mobile_number");
                String email = resultSet.getString("emp_email");
                String digitalAddress = resultSet.getString("emp_digital_address");
                String gender = resultSet.getNString("emp_gender");
                String qualification = resultSet.getString("emp_qualification");
                String idType = resultSet.getString("emp_idtype");
                String idNumber = resultSet.getNString("emp_idnumber");
                String employmentType = resultSet.getString("employed_as");
                Double salary = resultSet.getDouble("emp_salary");
                LocalDate joinedDate = resultSet.getDate("date_joined").toLocalDate();
                String note = resultSet.getString("emp_notes");
                byte is_active = resultSet.getByte("e.is_active");
                Timestamp dateUpdated = resultSet.getTimestamp("date_modified");

                CheckBox checkBox = new CheckBox();
                Label status = new Label("active");
                switch (is_active) {
                    case 0 -> {
                        status.setText("inactive");
                        status.setStyle("-fx-font-family: poppins; -fx-padding: 5px; -fx-width:30px; -fx-background-radius: 5px; -fx-text-fill:#ff0000; -fx-background-color: #ffe2e2");
                    }
                    case 1 -> {
                        checkBox.setSelected(true);
                        status.setStyle("-fx-font-family: poppins; -fx-padding: 5px; -fx-width:30px; -fx-background-radius: 5px; -fx-text-fill:#059f40; -fx-background-color:#e3ffee");
                    }
                }//end of switch

                data.add(new EmployeesData(id, emp_id,fullname, mobileNumber, digitalAddress, email, gender, qualification, joinedDate, idType, idNumber, employmentType, salary, note, status, dateUpdated, checkBox));
            }
            prepare.close();
            resultSet.close();
            DB_CONNECT().close();
        }catch (SQLException ignored) {}
        return data;
    }

    public ObservableList<UsersData> getAllUsers() {
        ObservableList<UsersData> users = FXCollections.observableArrayList();
        try {
            String select = "SELECT t1.user_id, t1.emp_id, t1.role_name, t1.username, t1.password, t1.is_active, t1.is_admin, t2.username, t1.date_added from users as t1\n" +
                    "JOIN users as t2 ON t1.user_id = t2.user_id;";
            prepare = DB_CONNECT().prepareStatement(select);
            resultSet = prepare.executeQuery();
            while(resultSet.next()) {
                int user_id = resultSet.getInt("t1.user_id");
                String emp_id = resultSet.getString("t1.emp_id");
                String role = resultSet.getString("t1.role_name");
                String username = resultSet.getString("t1.username");
                String password = resultSet.getString("t1.password");
                byte isActive = resultSet.getByte("t1.is_active");
                byte isAdmin = resultSet.getByte("t1.is_admin");
                String addedBy = resultSet.getString("t2.username");
                Timestamp dateAdded = resultSet.getTimestamp("t1.date_added");
                Label activeStatus = new Label("active");
                CheckBox checkBox = new CheckBox();
                switch(isActive) {
                    case 0 -> {
                        activeStatus.setText("inactive");
                        activeStatus.setStyle("-fx-font-family: poppins; -fx-padding: 5px; -fx-width:30px; -fx-background-radius: 5px; -fx-text-fill:#ff0000; -fx-background-color: #ffe2e2");
                    }
                    case 1 -> {
                        checkBox.setSelected(true);
                        activeStatus.setStyle("-fx-font-family: poppins; -fx-padding: 5px; -fx-width:30px; -fx-background-radius: 5px; -fx-text-fill:#059f40; -fx-background-color:#e3ffee");
                    }
                }
                users.add(new UsersData(user_id, emp_id, username, password, role, activeStatus, isAdmin, addedBy, dateAdded, checkBox));
            }
            prepare.close();
            resultSet.close();
            DB_CONNECT().close();
        }catch (SQLException e) {e.printStackTrace();}
        return users;
    }

}//end of class...
