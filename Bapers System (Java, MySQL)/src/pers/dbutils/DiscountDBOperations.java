package pers.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DiscountDBOperations {
    private static Connection connection = DBConnection.getConnection();
    public static void CreateVariableDiscount(int customerID, int taskID, float discountAmount) throws SQLException {
        try{
            String query = "INSERT into variable (CustomerID, TaskID, DiscountPercentage)" + " values (?,?,?)";

            PreparedStatement addVariableDiscount = connection.prepareStatement(query);
            addVariableDiscount.setInt(1, customerID);
            addVariableDiscount.setInt(2, taskID);
            addVariableDiscount.setFloat(3,discountAmount/100);
            addVariableDiscount.execute();
            System.out.println("Variable Discount Added" );

        }
        catch (Exception e) {
            System.err.println("Got an exception, Variable Discount Added");
            System.err.println(e.getMessage());
        }
    }
    public static void DeleteVariableDiscount(int customerID) throws SQLException {
        try{
            String query = "DELETE FROM Variable WHERE customerID = ?" + " values (?)";

            PreparedStatement deleteVariableDiscount = connection.prepareStatement(query);
            deleteVariableDiscount.setInt(1, customerID);
            deleteVariableDiscount.execute();
            System.out.println("Variable Discount Removed" );

        }
        catch (Exception e) {
            System.err.println("Got an exception, Variable Discount Removed");
            System.err.println(e.getMessage());
        }
    }
    public static void CreateFixedDiscount(int customerID, float discountAmount) throws SQLException {
        try{
            String query = "INSERT into Fixed (CustomerID, FixedRate)" + " values (?,?)";

            PreparedStatement addFixedDiscount = connection.prepareStatement(query);
            addFixedDiscount.setInt(1, customerID);
            addFixedDiscount.setFloat(2, discountAmount/100);
            addFixedDiscount.execute();
            System.out.println("Fixed Discount Added" );
        }

        catch (Exception e) {
            System.err.println("Got an exception, Fixed Discount Added");
            System.err.println(e.getMessage());
        }
    }
    public static void DeleteFixedDiscount(int customerID) throws SQLException {
        try{
            String query = "DELETE FROM Fixed WHERE customerID = ?" + " values (?)";

            PreparedStatement deleteFixedDiscount = connection.prepareStatement(query);
            deleteFixedDiscount.setInt(1, customerID);
            deleteFixedDiscount.execute();
            System.out.println("Fixed Discount Removed" );

        }
        catch (Exception e) {
            System.err.println("Got an exception, Fixed Discount Removed");
            System.err.println(e.getMessage());
        }
    }
    public static void CreateFlexibleDiscount(int customerID, float band1, float band2, float band1Discount, float band2Discount, float band3Discount) throws SQLException {
        try{
            String query = "INSERT into FlexBands (CustomerID, Band1, Band2, Band1Discount, Band2Discount, Band3Discount)" + " values (?,?,?,?,?,?)";

            PreparedStatement addFlexibleDiscount = connection.prepareStatement(query);
            addFlexibleDiscount.setInt(1, customerID);
            addFlexibleDiscount.setFloat(2, band1);
            addFlexibleDiscount.setFloat(3, band2);
            addFlexibleDiscount.setFloat(4, band1Discount);
            addFlexibleDiscount.setFloat(5, band2Discount);
            addFlexibleDiscount.setFloat(6, band3Discount);
            addFlexibleDiscount.execute();
            System.out.println("Flexible Discount Added" );
        }

        catch (Exception e) {
            System.err.println("Got an exception, Flexible Discount Added");
            System.err.println(e.getMessage());
        }
    }
    public static void DeleteFlexibleDiscount(int customerID) throws SQLException {
        try{
            String query = "DELETE FROM flexbands WHERE customerID = ?" + " values (?)";

            PreparedStatement deleteFixedDiscount = connection.prepareStatement(query);
            deleteFixedDiscount.setInt(1, customerID);
            deleteFixedDiscount.execute();
            System.out.println("Flexible Discount Removed" );

        }
        catch (Exception e) {
            System.err.println("Got an exception, Flexible Discount Removed");
            System.err.println(e.getMessage());
        }
    }
}
