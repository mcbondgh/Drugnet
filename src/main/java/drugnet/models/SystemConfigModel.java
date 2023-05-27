package drugnet.models;

import java.sql.SQLException;

public class SystemConfigModel extends MainModel{
    protected int updateStoreInfo(String storeName, String storeAddress, String storeMobileNumber , String storeEmail, String logoName, String managerName, String managerEmail, String managerNumber, int addedBy) {
        int flag = 0;
        try {
            String insertQuery = "UPDATE storeinformation SET store_name = ?, store_address = ?, store_mobile_number = ?, store_email = ?, store_logo = ?, manager_name = ?," +
                    "manager_email = ?, manager_number = ?, updated_by = ?, date_created = DEFAULT";
            prepare = DB_CONNECT().prepareStatement(insertQuery);
            prepare.setString(1, storeName);
            prepare.setString(2, storeAddress);
            prepare.setString(3, storeMobileNumber);
            prepare.setString(4, storeEmail);
            prepare.setString(5, logoName);
            prepare.setString(6, managerName);
            prepare.setString(7, managerEmail);
            prepare.setString(8, managerNumber);
            prepare.setInt(9, addedBy);
            flag = prepare.executeUpdate();
            prepare.close();
            DB_CONNECT().close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    protected int saveSupplier(String supplierName, String mobileNumber, String address, String location, String note) {
        int flag = 0;
        try {
            String insertQuery = "INSERT INTO suppliers VALUES(DEFAULT, ?, ?, ?, ?,  ?, DEFAULT);";
            prepare = DB_CONNECT().prepareStatement(insertQuery);
            prepare.setString(1, supplierName);
            prepare.setString(2, mobileNumber);
            prepare.setString(3, address);
            prepare.setString(4, location);
            prepare.setString(5, note);
            flag = prepare.executeUpdate();
            prepare.close();
            DB_CONNECT().close();
        }catch (SQLException e){e.printStackTrace();}
        return flag;
    }
    protected int saveBrand(String brandName, String manufacturerName) {
        int flag = 0;
        try {
            String insertQuery = "INSERT INTO brands VALUES(DEFAULT, ?, ?, DEFAULT);";
            prepare = DB_CONNECT().prepareStatement(insertQuery);
            prepare.setString(1, brandName);
            prepare.setString(2, manufacturerName);
            flag = prepare.executeUpdate();
            prepare.close();
            DB_CONNECT().close();
        }catch (SQLException e){e.printStackTrace();}
        return flag;
    }
    protected int updateSupplierInfo(int supplierId, String supplierName, String supplierNumber, String supplierAddress, String supplierLocation, String comments) {
        int flag = 0;
        try {
            String update = "UPDATE suppliers SET supplier_name = ?, supplier_contact = ?, supplier_address = ?, supplier_location = ?, supplier_note = ?, date_created = DEFAULT WHERE(supplier_id = ?);";
            prepare = DB_CONNECT().prepareStatement(update);
            prepare.setString(1, supplierName);
            prepare.setString(2, supplierNumber);
            prepare.setString(3, supplierAddress);
            prepare.setString(4, supplierLocation);
            prepare.setString(5, comments);
            prepare.setInt(6, supplierId);
            flag = prepare.executeUpdate();
            prepare.close();
            DB_CONNECT().close();
        }catch (SQLException e) {e.printStackTrace();}

        return flag;
    }
    protected int updateBrandInfo(int brand_id, String brand_name, String manufacturer_name)  {
        int flag = 0;
            try {
                String update = "UPDATE brands SET brand_name = ?, manufacturer_name = ?, date_created = DEFAULT WHERE(brand_id = ?);";
                prepare = DB_CONNECT().prepareStatement(update);
                prepare.setString(1, brand_name);
                prepare.setString(2, manufacturer_name);
                prepare.setInt(3, brand_id);
                flag = prepare.executeUpdate();
                prepare.close();
                DB_CONNECT().close();
            }catch (SQLException e) {e.printStackTrace();}
        return flag;
    }

    protected int updateSmsApi(String api_key, String sender_id) {
        int flag = 0;
        try {
            String update = "UPDATE sms_api SET api_key = '" + api_key +"' , sender_id = '"+ sender_id +"', date_created = DEFAULT WHERE api_id = 1";
            prepare = DB_CONNECT().prepareStatement(update);
            flag = prepare.executeUpdate();
        }catch (SQLException ignored){}
        return flag;
    }






}//END OF CLASS...
