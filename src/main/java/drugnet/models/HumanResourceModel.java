package drugnet.models;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class HumanResourceModel extends MainModel{

    protected int saveEmployee(String emp_id, String fullname, String mobileNumber, String email, String digitalAddress, String gender, String qualification, String idType, String idNumber, String employedAs, Double salary, LocalDate joinedDate, String comments, int added_by) {
        int flag = 0;
        try {
            String insert = "INSERT INTO employees(emp_id, fullname, emp_mobile_number, emp_email, emp_digital_address, emp_gender, emp_qualification, emp_idtype, emp_idnumber,   " +
                    "employed_as, emp_salary, date_joined, emp_notes, emp_added_by) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            prepare = DB_CONNECT().prepareStatement(insert);
            prepare.setString(1, emp_id);
            prepare.setString(2, fullname);
            prepare.setString(3, mobileNumber);
            prepare.setString(4, email);
            prepare.setString(5, digitalAddress);
            prepare.setString(6, gender);
            prepare.setString(7, qualification);
            prepare.setString(8, idType);
            prepare.setString(9, idNumber);
            prepare.setString(10, employedAs);
            prepare.setDouble(11, salary);
            prepare.setDate(12, Date.valueOf(joinedDate));
            prepare.setString(13, comments);
            prepare.setInt(14, added_by);
            flag = prepare.executeUpdate();
        }catch (SQLException ignored) {}
        return flag;
    }
    protected int saveUser(String emp_id, String roleName, String username, String password, int addedBy) {
        int flag = 0;
        try {
            String insert = "INSERT INTO users(emp_id, role_name, username, password, user_added_by) VALUES(?, ?, ?, ?, ?);";
            prepare = DB_CONNECT().prepareStatement(insert);
            prepare.setString(1, emp_id);
            prepare.setString(2, roleName);
            prepare.setString(3, username);
            prepare.setString(4, password);
            prepare.setInt(5, addedBy);
            flag = prepare.executeUpdate();
        }catch (Exception ignored){}

        return flag;
    }


}//end of class...
