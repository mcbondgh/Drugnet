package drugnet.models;

import drugnet.dbconfig.DbAPI;
import drugnet.fetchedData.SmsApiData;
import drugnet.tableViewData.BrandsTableData;
import drugnet.tableViewData.SuppliersTableData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainModel extends DbAPI {

    protected int getUserIdByUsername(String username) {
        int userId = 1;

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
        }catch (SQLException ignored) {
        }


        return data;
    }


}//end of class...
