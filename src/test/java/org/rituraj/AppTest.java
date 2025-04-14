package org.rituraj;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest{

    @Test
    public void employee_object_matches_db_row(){
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String pswd = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cn = DriverManager.getConnection(url, user, pswd);

            Employee e = new Employee(5,"Terisa", 30000.00, Date.valueOf("2023-04-14"));

            String query = "SELECT * from employee_payroll WHERE id=5";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            if(rs.next()){
                assertEquals(e.getName(), rs.getString(2));
                assertEquals(e.getId(), rs.getInt(1));
                assertEquals(e.getSalary(), rs.getDouble(3), 0.00);
                assertEquals(e.getDate(), rs.getDate(4));
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
