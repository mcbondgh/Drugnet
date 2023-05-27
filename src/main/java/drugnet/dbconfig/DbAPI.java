package drugnet.dbconfig;


import java.sql.*;

public class DbAPI {
    protected PreparedStatement prepare;
    protected Statement statement;
    protected ResultSet resultSet;

    protected Connection DB_CONNECT() throws SQLException {
        String URL = "jdbc:mysql://127.0.0.1:3306/drugnet";
        String DB_USERNAME = "drugnet";
        String DB_PASSWORD = "drugnet1234";
        return DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
    }


//    public static void main(String[] args) {
//        DbAPI api  = new DbAPI();
//        System.out.println("Connecting to server...");
//        try {
//            if (api.DB_CONNECT() != null) {
//                System.out.println("Successfully connected to server....");
//            } else {
//                System.out.println("failed to connect to server");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}//end of class
