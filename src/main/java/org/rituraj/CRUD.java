package org.rituraj;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    public void createTable(Connection con) throws SQLException {
        String sql = "CREATE TABLE employee_payroll (id INT AUTO_INCREMENT, name VARCHAR(50), salary DOUBLE(10, 2), joining_date DATE, PRIMARY KEY (id))";
        Statement st = con.createStatement();
        int i = st.executeUpdate(sql);
        System.out.println("Table created successfully!");
    }

    public void readData(Connection con) throws SQLException {
        List<Employee> list = new ArrayList<>();
        String sql = "SELECT * FROM employee_payroll";
        Statement st = con.createStatement();
        ResultSet set =  st.executeQuery(sql);
        while(set.next()){
            Employee employee = new Employee(set.getInt(1), set.getString(2), set.getDouble(3), set.getDate(4));
            list.add(employee);
        }

        for(Employee e : list){
            System.out.println(e);
        }

    }

    public void insertData(Connection con) throws SQLException {
        String sql = "INSERT INTO employee_payroll (name, salary, joining_date) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);

        // First record
        preparedStatement.setString(1, "Alice Johnson");
        preparedStatement.setDouble(2, 75000.00);
        preparedStatement.setDate(3, Date.valueOf("2021-03-15"));
        preparedStatement.addBatch();

        // Second record
        preparedStatement.setString(1, "Bob Smith");
        preparedStatement.setDouble(2, 56000.50);
        preparedStatement.setDate(3, Date.valueOf("2020-07-01"));
        preparedStatement.addBatch();

        // Third record
        preparedStatement.setString(1, "Charlie Brown");
        preparedStatement.setDouble(2, 98000.00);
        preparedStatement.setDate(3, Date.valueOf("2023-01-12"));
        preparedStatement.addBatch();

        // Fourth record
        preparedStatement.setString(1, "Diana Rose");
        preparedStatement.setDouble(2, 63000.75);
        preparedStatement.setDate(3, Date.valueOf("2019-11-25"));
        preparedStatement.addBatch();

        int[] results = preparedStatement.executeBatch();
        System.out.println("Rows inserted: " + results.length);
    }

    public void updateSalary(Connection con, Employee e) throws SQLException {
        //used prepared statement
        String sql = "UPDATE employee_payroll SET salary=30000.00 WHERE name=?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, e.getName());

        int i = preparedStatement.executeUpdate();
        if(i > 0){
            System.out.println("Updated successfully!");
        }
    }

    public void getEmployeeInDateRange(Connection con) throws SQLException {
        String query = "SELECT * FROM employee_payroll WHERE joining_date BETWEEN ? AND ?";

        PreparedStatement st = con.prepareStatement(query);
        st.setDate(1, Date.valueOf("2020-01-01"));
        st.setDate(2, Date.valueOf("2024-01-01"));

        ResultSet set = st.executeQuery();
        while(set.next()){
            System.out.println("Id: " + set.getInt(1) + " Name: " + set.getString(2) + " Salary: " + set.getDouble(3) + " Joining Date: " + set.getDate(4));
        }
    }
}
