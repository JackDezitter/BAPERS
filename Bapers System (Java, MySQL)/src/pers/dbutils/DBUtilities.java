/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alia
 */
public class DBUtilities {
    Connection conn;
    //database connection
    public DBUtilities(){
        conn = DBConnection.getConnection();
    }
    public ResultSet executeQuery(String query, String[] params){
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for(int inx=1;inx<=params.length;inx++){
                ps.setString(inx, params[inx-1]);
            }
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.print(query);
            ex.printStackTrace();
        }
        return null;
    }
    //method for inserting or updating records in the database
    public Map<String, Object> insertOrUpdateRecord(String query,String[] returnParams, String...params){
        Map<String, Object> returnMap=new HashMap<>();
        List<Object[]> generatedKeys;
        try {
            PreparedStatement ps = conn.prepareStatement(query, returnParams);
            for(int inx=1;inx<=params.length;inx++){
                ps.setString(inx, params[inx-1]);
            }
            int rows = ps.executeUpdate();
            returnMap.put("ROWS", rows);
            if(rows>0){
                generatedKeys=new ArrayList<>();
                if(returnParams!=null && returnParams.length>0) {
                    ResultSet resultSet = ps.getGeneratedKeys();
                    while(resultSet.next()){
                        Object obj[]=new Object[returnParams.length];
                        for(int inx=0;inx<returnParams.length;inx++)
                            obj[inx] = resultSet.getString(inx+1);
                        generatedKeys.add(obj);
                    }
                }
                returnMap.put("GENERATED_KEYS", generatedKeys);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnMap;
    }
    public void commitChanges(){
        try {
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void rollbackChanges(){
        try {
            conn.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DBUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
