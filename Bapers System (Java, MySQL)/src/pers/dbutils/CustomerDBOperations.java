/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.dbutils;

import pers.beans.CustomerInfoBean;
import pers.beans.DiscountType;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alia
 */
public class CustomerDBOperations {
    //private attribute
    private final DBUtilities dbUtilities=new DBUtilities();

    //Method which gets all the customer details from the database and displays it for the admin
    public CustomerInfoBean getCustomerDetails(long customerId){
        CustomerInfoBean customer=null;
        //sql query which retrieves the data from the database
        ResultSet resultSet = dbUtilities.executeQuery("SELECT CustomerID, Name, Surname, Address, PhoneNumber, Email, CustomerTypeID, DiscountTypeID where CUSTOMER_ID=?",new String[]{String.valueOf(customerId)});
        try {
            if(resultSet.next()){
                customer = new CustomerInfoBean();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("Name"));
                customer.setSurname(resultSet.getString("Surname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phoneNumber"));
                customer.setCustomerTypeID(resultSet.getString("customerTypeID"));
                customer.setDiscountTypeId(resultSet.getLong("discountTypeId"));
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return customer;
    }
    public static ResultSet SearchCustomer(int customerID, String name, String surname, String email, String customerType, String discountType) throws SQLException {
        String query;
        Connection connection = DBConnection.getConnection();
        try {
            if (discountType.equals("1")){
                discountType = "Fixed";
            }
            else if (discountType.equals("2")){
                discountType = "Variable";
            }
            else if (discountType.equals("3")){
                discountType = "Flexible";
            }
            else if (discountType.equals("4")){
                discountType = "None";
            }

            //Retrieves price of each task from task database
            query = "SELECT * FROM Customer WHERE CustomerID = ? OR Name = ? OR Surname = ? OR  Email = ? OR CustomerTypeID = ? OR DiscountTypeID = ? ";
            PreparedStatement jobSearch = connection.prepareStatement(query);
            jobSearch.setInt(1, customerID);
            jobSearch.setString(2, name);
            jobSearch.setString(3, surname);
            jobSearch.setString(4, email );
            jobSearch.setString(5, customerType);
            jobSearch.setString(6, discountType);

            ResultSet set = jobSearch.executeQuery();
            return set;
            //connection.close();
        }
        //If error with queries
        catch (Exception e){
            System.err.println("Got an exception, Search Job!");
            System.err.println(e.getMessage());
            ResultSet set = null;
            return set;
        }

    }
    //This method gets a list of all the customers from the db
    public List<CustomerInfoBean> getCustomersList(ResultSet resultSet){
        List<CustomerInfoBean> result=new ArrayList<>();
        try {
            while(resultSet.next()){
                CustomerInfoBean customer = new CustomerInfoBean();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("Name"));
                customer.setSurname(resultSet.getString("Surname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phoneNumber"));
                customer.setDiscountTypeId(resultSet.getLong("discountTypeId"));
                customer.setCustomerTypeID(resultSet.getString("customerTypeID"));
                result.add(customer);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }
    //This method gets a list of all the customers from the db
    public List<CustomerInfoBean> getCustomersList(String whereClause, List<String> paramsList){
        List<CustomerInfoBean> result=new ArrayList<>();
        String[] params = new String[paramsList.size()];
        for(int inx=0; inx<paramsList.size();inx++){
            params[inx]=paramsList.get(inx);
        }
        ResultSet resultSet = dbUtilities.executeQuery("SELECT CustomerID, Name, Surname, Address, PhoneNumber, Email, CustomerTypeID, DiscountTypeID FROM CUSTOMER "+whereClause, params);

        try {
            while(resultSet.next()){
                CustomerInfoBean customer = new CustomerInfoBean();
                customer.setCustomerID(resultSet.getInt("CustomerID"));
                customer.setFirstName(resultSet.getString("Name"));
                customer.setSurname(resultSet.getString("Surname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setAddress(resultSet.getString("address"));
                customer.setPhoneNumber(resultSet.getString("phoneNumber"));
                customer.setDiscountTypeId(resultSet.getLong("discountTypeId"));
                customer.setCustomerTypeID(resultSet.getString("customerTypeID"));
                result.add(customer);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }
    //method which saves the updated the customer details and also updates the db
    @SuppressWarnings("unchecked")
    public long saveCustomer(CustomerInfoBean customer, int userID){
        long result=-1;
        //inserting user
        List<Object[]> resultList;
        if(customer.getCustomerID()==null || customer.getCustomerID()<=0){
            resultList = (List<Object[]>)dbUtilities.insertOrUpdateRecord("INSERT INTO Customer (Name, Surname, Address, Email) "
                            + "VALUES (?,?,?,?)",
                    new String[]{"CustomerID"},customer.getFirstName(), customer.getSurname(),
                    customer.getAddress(), customer.getEmail()).get("GENERATED_KEYS");

            if(resultList==null || resultList.isEmpty()){
                dbUtilities.rollbackChanges();
                return result;
            }

            dbUtilities.commitChanges();
            result = Integer.valueOf(String.valueOf(resultList.get(0)[0]));
        }else{
            long rows = (int)dbUtilities.insertOrUpdateRecord("UPDATE CUSTOMER SET Name=?, Surname=?, Email=?, ADDRESS=?, PhoneNumber = ? WHERE CUSTOMER_ID=? ",
                    new String[]{},
                    customer.getFirstName(),customer.getSurname(), customer.getEmail(),customer.getAddress(), customer.getPhoneNumber(), String.valueOf(customer.getCustomerID())).get("ROWS");
            if(rows<=0){
                dbUtilities.rollbackChanges();
                return result;
            }
            result=customer.getCustomerID();
            dbUtilities.commitChanges();
        }
        return result;
    }
    //method which deletes customer from the db
    public long deleteCustomer(long customerId){
        long result=-1;
        ResultSet user = dbUtilities.executeQuery("select CustomerID from customer where CustomerID=?", new String[]{String.valueOf(customerId)});
        try {
            if(user.next()){
                //deleting customer
                long rows = (int)dbUtilities.insertOrUpdateRecord("delete from customer where CustomerID=?", new String[]{}, String.valueOf(customerId)).get("ROWS");
                dbUtilities.commitChanges();
                return rows;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDBOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public boolean updateDiscountType(Long customerId, Long discountType) {
        try {
            ResultSet user = dbUtilities.executeQuery("select CustomerID, DiscountTypeID from customer where CustomerID=?", new String[]{String.valueOf(customerId)});
            user.next();
            if(user.getInt(2) != 4){
                //Fixed
                if(user.getInt(2) == 1){
                    dbUtilities.insertOrUpdateRecord("DELETE FROM Fixed WHERE CustomerID = ?",new String[]{}, String.valueOf(customerId)).get("ROWS");
                    dbUtilities.commitChanges();
                }
                //Variable
                else if(user.getInt(2) == 2){
                    dbUtilities.insertOrUpdateRecord("DELETE FROM Variable WHERE CustomerID = ?",new String[]{}, String.valueOf(customerId)).get("ROWS");
                    dbUtilities.commitChanges();
                }
                //Flexible
                else if(user.getInt(2) == 3){
                    dbUtilities.insertOrUpdateRecord("DELETE FROM Flexbands WHERE CustomerID = ?",new String[]{}, String.valueOf(customerId)).get("ROWS");
                    dbUtilities.commitChanges();
                }
            }

            long rows = (int)dbUtilities.insertOrUpdateRecord(
                    "update customer set DiscountTypeID=? where CustomerID = ?",
                    new String[]{}, String.valueOf(discountType), String.valueOf(customerId)).get("ROWS");
            if(rows>0) {
                dbUtilities.commitChanges();

                if (discountType != 4){
                    rows = (int)dbUtilities.insertOrUpdateRecord(
                            "update customer set CustomerTypeID=? where CustomerID = ?",
                            new String[]{}, "Valued", String.valueOf(customerId)).get("ROWS");
                    dbUtilities.commitChanges();
                }
                else {
                    rows = (int)dbUtilities.insertOrUpdateRecord(
                            "update customer set CustomerTypeID=? where CustomerID = ?",
                            new String[]{}, "Normal", String.valueOf(customerId)).get("ROWS");
                    dbUtilities.commitChanges();
                }
                return true;
            }

        }catch(Exception ex) {
            dbUtilities.rollbackChanges();
            ex.printStackTrace();
        }
        return false;
    }
    public boolean updateCustomerType(Long customerId, String customerTypeID) {
        try {
            long rows = (int)dbUtilities.insertOrUpdateRecord(
                    "update customer set CustomerTypeID=? where CustomerID=?",
                    new String[]{}, customerTypeID, String.valueOf(customerId)).get("ROWS");
            if(rows>0) {
                dbUtilities.commitChanges();
                return true;
            }
        }catch(Exception ex) {
            dbUtilities.rollbackChanges();
            ex.printStackTrace();
        }
        return false;
    }
}

