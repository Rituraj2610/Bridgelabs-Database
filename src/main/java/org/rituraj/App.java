package org.rituraj;

import java.sql.*;
import java.util.Enumeration;

public class App 
{
    public static void main( String[] args )  {

        // UC1: Loading the driver and setting up the connection
        String url = "jdbc:mysql://localhost:3306/payroll_service";
        String user = "root";
        String pswd = "root";
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
        }catch (ClassNotFoundException e){
            throw new IllegalStateException("Driver not found");
        }

            Enumeration<Driver> drivers =  DriverManager.getDrivers();
            if(drivers.hasMoreElements()){
                System.out.println(drivers.nextElement());
            }


        try{
            con = DriverManager.getConnection(url, user, pswd);
            System.out.println("Connection success");
            }catch(SQLException e){
            e.printStackTrace();
        }


    }
}
