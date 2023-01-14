/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.dbutils;

import pers.common.ApplicationConstants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Alia
 */
public class DBConnection {
    public static Connection connection;

    // function to connect to database
    public static boolean Connect(){
        if (connection == null) {
            //Database URL
            String url = "jdbc:mysql://localhost:3306/database";
            //Database username
            String username = "root";
            //Database Password
            String password = "Team23";

            System.out.println("Connecting database...");

            try {
                //Attempts Connection
                connection = DriverManager.getConnection(url, username, password);
                //If database connection is successful
                System.out.println("Database connected!");
                return true;
            }
            //If database connection fails
            catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            }
        }
        return false;
    }

    public static Connection getConnection() {
        return connection;
    }
}
