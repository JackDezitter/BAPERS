/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.dbutils;

import pers.beans.StaffInfoBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alia
 */
public class StaffDBOperations {
    //private attribute
    private final DBUtilities dbUtilities=new DBUtilities();

    //method which gets the staff details by user id  from the db
    public StaffInfoBean getStaffMemberDetails(long staffID){
        StaffInfoBean result=null;
        //sql query which retrieves the data from the database
        ResultSet resultSet = dbUtilities.executeQuery("SELECT StaffID, Name, Surname, Email, PhoneNumber, Address, Role, FROM Staff where StaffID=?",new String[]{String.valueOf(staffID)});
        try {
            if(resultSet.next()){
                result = new StaffInfoBean();
                result.setStaffID(resultSet.getInt("StaffID"));
                result.setFirstName(resultSet.getString("Name"));
                result.setLastName(resultSet.getString("Surname"));
                result.setEmail(resultSet.getString("Email"));
                result.setPhone(resultSet.getString("PhoneNumber"));
                result.setUserRole(resultSet.getString("Role"));
                result.setAddress(resultSet.getString("Address"));
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }

    //This method gets a list of all the customers from the db
    public List<StaffInfoBean> getStaffList(String whereClause, List<String> paramsList){
        List<StaffInfoBean> result=new ArrayList<>();
        String[] params = new String[paramsList.size()];
        for(int inx=0; inx<paramsList.size();inx++){
            params[inx]=paramsList.get(inx);
        }
        ResultSet resultSet = dbUtilities.executeQuery("SELECT StaffID,"
                + " Name, Surname, Email, PhoneNumber, Address, Role "
                + " FROM staff "+whereClause,params);

        try {
            while(resultSet.next()){
                StaffInfoBean staff = new StaffInfoBean();
                staff.setMemberId(resultSet.getInt("StaffID"));
                staff.setFirstName(resultSet.getString("Name"));
                staff.setLastName(resultSet.getString("Surname"));
                staff.setEmail(resultSet.getString("email"));
                staff.setPhone(resultSet.getString("phoneNumber"));
                staff.setUserRole(resultSet.getString("Role"));
                staff.setAddress(resultSet.getString("address"));
                result.add(staff);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }
    //method which saves the updated the customer details and also updates the db
    @SuppressWarnings("unchecked")
    public long saveStaffMember(StaffInfoBean member, int userID){
        long result=-1;
        //inserting user
        List<Object[]> resultList;
        if(member.getStaffID()==null || member.getStaffID()<=0){
            resultList = (List<Object[]>) dbUtilities.insertOrUpdateRecord("INSERT INTO Staff (Name, Surname, Address, Email, Password, Role, PhoneNumber) VALUES(?,?,?,?,?,?,?)",
                    new String[]{"STAFF_ID"},
                    member.getFirstName(), member.getLastName(), member.getAddress(),
                    member.getEmail(), member.getPassword(), member.getUserRole(), member.getPhone()).get("GENERATED_KEYS");

            if(resultList==null || resultList.isEmpty()){
                dbUtilities.rollbackChanges();
                return result;
            }
            dbUtilities.commitChanges();
            result = Integer.valueOf(String.valueOf(resultList.get(0)[0]));

        }else{
            long rows = (int)dbUtilities.insertOrUpdateRecord("UPDATE Staff SET Name=?, Surname=?, Email=?, PhoneNumber=?, Address=?, Role = ?"
                            + "  WHERE StaffID=? ",
                    new String[]{},
                    member.getFirstName(),member.getLastName(), member.getEmail(),
                    member.getPhone(),member.getAddress(), String.valueOf(member.getStaffID())).get("ROWS");
            if(rows<=0){
                dbUtilities.rollbackChanges();
                return result;
            }
            result=member.getStaffID();
            dbUtilities.commitChanges();
        }
        return result;
    }
    //method which deletes staff member from the db
    public long deleteMember(long staffID){
        long result=-1;
        ResultSet user = dbUtilities.executeQuery("select StaffID from Staff where StaffID=?", new String[]{String.valueOf(staffID)});
        try {
            if(user.next()){
                //deleting customer
                long rows = (int)dbUtilities.insertOrUpdateRecord("delete from Staff where StaffID=?", new String[]{}, String.valueOf(staffID)).get("ROWS");
                if(rows>0){
                    dbUtilities.commitChanges();
                    return rows;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
