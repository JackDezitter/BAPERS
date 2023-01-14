package pers.dbutils;

import pers.beans.IndividualReport;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportsDBOperation {
    private static Connection connection = DBConnection.getConnection();
    //private attribute
    private final DBUtilities dbUtilities=new DBUtilities();
    //This method gets a list of all the customers from the db
    public List<IndividualReport> getIndividualReportList(ResultSet resultSet){
        List<IndividualReport> result=new ArrayList<>();
        try {
            while(resultSet.next()){
                String query;
                int staffID = resultSet.getInt(3);
                query = "SELECT  Name, Surname from staff WHERE StaffID = ?";
                PreparedStatement getNames = connection.prepareStatement(query);
                getNames.setInt(1, staffID);
                ResultSet names = getNames.executeQuery();
                names.next();

                int taskID = resultSet.getInt(2);
                query = "SELECT  Location from task WHERE TaskID = ?";
                PreparedStatement getDepartment = connection.prepareStatement(query);
                getDepartment.setInt(1, taskID);
                ResultSet departmentSet = getDepartment.executeQuery();
                departmentSet.next();

                IndividualReport individualReport = new IndividualReport();
                individualReport.setName(names.getString(1));
                individualReport.setSurname(names.getString(2));
                individualReport.setDepartment(departmentSet.getString(1));
                individualReport.setTaskID(resultSet.getInt(3));
                individualReport.setStartDate(resultSet.getTimestamp(4).toLocalDateTime());
                individualReport.setEndDate(resultSet.getTimestamp(7).toLocalDateTime());
                result.add(individualReport);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }
    //This method gets a list of all the customers from the db
    public List<IndividualReport> getIReportList(String whereClause, List<String> paramsList){
        List<IndividualReport> result=new ArrayList<>();
        String[] params = new String[paramsList.size()];
        for(int inx=0; inx<paramsList.size();inx++){
            params[inx]=paramsList.get(inx);
        }
        ResultSet resultSet = dbUtilities.executeQuery("SELECT * FROM jobTasks "+whereClause, params);

        try {
            while(resultSet.next()){
                String query;
                int staffID = resultSet.getInt(3);
                query = "SELECT  Name, Surname from staff WHERE StaffID = ?";
                PreparedStatement getNames = connection.prepareStatement(query);
                getNames.setInt(1, staffID);
                ResultSet names = getNames.executeQuery();
                names.next();

                int taskID = resultSet.getInt(2);
                query = "SELECT  Location from task WHERE TaskID = ?";
                PreparedStatement getDepartment = connection.prepareStatement(query);
                getDepartment.setInt(1, taskID);
                ResultSet departmentSet = getDepartment.executeQuery();
                departmentSet.next();

                IndividualReport individualReport = new IndividualReport();
                individualReport.setName(names.getString(1));
                individualReport.setSurname(names.getString(2));
                individualReport.setTaskID(resultSet.getInt(3));
                individualReport.setDepartment(departmentSet.getString(1));
                individualReport.setStartDate(resultSet.getTimestamp(4).toLocalDateTime());
                individualReport.setEndDate(resultSet.getTimestamp(7).toLocalDateTime());
                result.add(individualReport);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }

    public static ResultSet SearchJobTasks(LocalDate startDate, LocalDate endDate) throws SQLException {
        String query;
        try {
            //Retrieves price of each task from task database
            query = "SELECT * FROM jobtasks WHERE StartTime >= ?  AND EndTime <= ?";
            PreparedStatement jobTaskSearch = connection.prepareStatement(query);
            jobTaskSearch.setDate(1, Date.valueOf(startDate));
            jobTaskSearch.setDate(2, Date.valueOf(endDate));
            ResultSet set = jobTaskSearch.executeQuery();
            return set;

            //connection.close();
        }
        //If error with queries
        catch (Exception e){
            System.err.println("Got an exception, Search Job task!");
            System.err.println(e.getMessage());
            ResultSet set = null;
            return set;
        }

    }
}
