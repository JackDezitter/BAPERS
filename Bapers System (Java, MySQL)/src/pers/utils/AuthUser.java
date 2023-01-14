/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.utils;

import pers.dbutils.DBUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alia
 */
public class AuthUser {

    public String role = "None";
    public int staffID = 0;
    // this function is called to verify or authentication users
    public boolean auth(String email, String password) {
        boolean authSuccess = false;
        // check if the data entered in all text boxes
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            return authSuccess;
        } else {
            // run query to check if the exist
            ResultSet result = new DBUtilities().executeQuery("SELECT StaffID, Email, Password,"
                    + "Role From staff WHERE email=? and Password=?", new String[]{email, password});

            try {
                if (result.next()) {
                    staffID = result.getInt(1);
                    role = result.getString(4);
                    System.out.println(role);
                    authSuccess = true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AuthUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return authSuccess;
    }
    public String getRole() {
        return role;
    }
    public int getStaffID(){return staffID;}

}
