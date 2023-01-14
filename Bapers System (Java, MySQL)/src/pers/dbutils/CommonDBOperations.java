/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.dbutils;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import pers.beans.ChangePasswordBean;

/**
 *
 * @author Alia
 */
public class CommonDBOperations {
    //private attribute
    private final DBUtilities dbUtilities=new DBUtilities();
    //Method which allows the change of password and updates the database
    public boolean changePassword(ChangePasswordBean passwordBean, long userId){
        if((userId<=0 || passwordBean!=null) && passwordBean.getNewPassword()!=null && passwordBean.getNewPassword().equals(passwordBean.getConfirmPassword())){
            //sql statement which updates the user login table in the Database
            long rows = (int)dbUtilities.insertOrUpdateRecord("UPDATE USER_LOGIN_DETAILS SET PASSWORD=? WHERE USER_ID=?", new String[]{}, passwordBean.getNewPassword(), String.valueOf(userId)).get("ROWS");
            if(rows<=0){
                dbUtilities.rollbackChanges();
                return false;
            }
            dbUtilities.commitChanges();
            return true;
        }
        return false;
    }
    public Map<Long, String> getCommonParametersList(String listName){
        Map<Long, String> map = new HashMap<>();
        try{
            ResultSet resultSet = dbUtilities.executeQuery("Select clv.LIST_VALUE_ID, clv.VALUE_NAME from common_list_values clv left join common_lists cl on cl.LIST_ID = clv.LIST_ID where cl.LIST_NAME=?",
                    new String[] {listName});
            if(resultSet!=null) {
                while(resultSet.next()) {
                    map.put(resultSet.getLong("LIST_VALUE_ID"), resultSet.getString("VALUE_NAME"));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}