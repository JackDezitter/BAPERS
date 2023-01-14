/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.dbutils;

import pers.beans.BookingInfoBean;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alia
 */
public class BookingsDBOperations {
    //Private Attribute
    private final DBUtilities dbUtilities=new DBUtilities();

    //Method which gets all the trip details from the database using sql query
    public BookingInfoBean getJobDetails(long bookingId){
        BookingInfoBean booking=null;
        ResultSet resultSet = dbUtilities.executeQuery("SELECT (SELECT CUSTOMER_NUMBER FROM CUSTOMERS WHERE CUSTOMER_ID=bk.CustomerID) as CUSTOMER_NUMBER," +
                " JobID, CustomerID, PaidStatus, DATE_FORMAT(bk.Deadline, '%d-%m-%Y') Deadline, "+
                " Price, SpecialInstructions, avj.JOB_TITLE as jobTitle,avj.JOB_CODE as jobCode FROM JOB bk"+
                " left join available_jobs avj on avj.AV_JOB_ID=bk.taskId "+
                " where JobID=?",new String[]{String.valueOf(bookingId)});
        try {
            if(resultSet!=null && resultSet.next()){
                booking = new BookingInfoBean();
                booking.setJobId(resultSet.getLong("JobID"));
                booking.setCustomerId(resultSet.getLong("CustomerID"));
                booking.setDeadline(resultSet.getString("Deadline"));
                booking.setJobCode(resultSet.getString("jobCode"));
                booking.setJobTitle(resultSet.getString("jobTitle"));
                booking.setPrice(resultSet.getDouble("Price"));
                booking.setStatus(getCurrentTask(booking.getJobId()));
                booking.setSpecialInstructions(resultSet.getString("SpecialInstructions"));
                booking.setCustomerNumber(resultSet.getString("CUSTOMER_NUMBER"));
                booking.setPaidStatus(resultSet.getInt("PaidStatus")==1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return booking;
    }
    //get a list of all the bookings
    public List<BookingInfoBean> getJobBookingsList(String whereClause, List<String> paramsList){
        List<BookingInfoBean> result=new ArrayList<>();
        String[] params = new String[paramsList.size()];
        for(int inx=0; inx<paramsList.size();inx++){
            params[inx]=paramsList.get(inx);
        }
        ResultSet resultSet = dbUtilities.executeQuery("SELECT (SELECT CUSTOMER_NUMBER FROM CUSTOMERS WHERE CUSTOMER_ID=bk.CustomerID) as CUSTOMER_NUMBER,"
                + " JobID, CustomerID,avj.JOB_TITLE as jobTitle, DATE_FORMAT(bk.Deadline, '%d-%m-%Y') Deadline, PaidStatus FROM JOB bk"
                + " left join available_jobs avj on avj.AV_JOB_ID=bk.taskId "+whereClause, params);

        try {
            while(resultSet.next()){
                BookingInfoBean booking = new BookingInfoBean();
                booking.setJobId(resultSet.getLong("JobID"));
                booking.setCustomerId(resultSet.getLong("CustomerID"));
                booking.setDeadline(resultSet.getString("Deadline"));
                booking.setJobTitle(resultSet.getString("jobTitle"));
                booking.setStatus(getCurrentTask(booking.getJobId()));
                booking.setCustomerNumber(resultSet.getString("CUSTOMER_NUMBER"));
                booking.setPaidStatus(resultSet.getInt("PaidStatus")==1);
                result.add(booking);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    @SuppressWarnings("unchecked")
    public long saveBooking(BookingInfoBean job, int userID){
        long result=-1;
        List<Object[]> resultList;
        if(job.getJobId()==null || job.getJobId()<=0){

            resultList = (List<Object[]>)dbUtilities.insertOrUpdateRecord("INSERT INTO JOB(CustomerID, Deadline, Price, "
                            + " taskId, SpecialInstructions, DateCreated, CREATED_BY)"
                            + " VALUES((SELECT CUSTOMER_ID FROM CUSTOMERS WHERE CUSTOMER_NUMBER=?), STR_TO_DATE(?,'%d-%m-%Y'),"
                            + " (SELECT JOB_COST from AVAILABLE_JOBS where JOB_CODE=?),(SELECT AV_JOB_ID from AVAILABLE_JOBS where JOB_CODE=?), ?, sysdate(),?)",
                    new String[]{"JobID"},
                    job.getCustomerNumber(), job.getDeadline(), job.getJobCode(), job.getJobCode(), job.getSpecialInstructions(),
                    String.valueOf(userID)
            ).get("GENERATED_KEYS");
            if(resultList==null || resultList.isEmpty()){
                dbUtilities.rollbackChanges();
                return result;
            }
            final String bookingId = String.valueOf(resultList.get(0)[0]);

            dbUtilities.commitChanges();
            result = Integer.valueOf(bookingId);
        }else{
            long rows = (int)dbUtilities.insertOrUpdateRecord("UPDATE JOB SET "
                            + " CustomerID=(SELECT CUSTOMER_ID FROM CUSTOMERS WHERE CUSTOMER_NUMBER = ?),"
                            + " Deadline = STR_TO_DATE(?,'%d-%m-%Y'),taskId = (SELECT AV_JOB_ID from AVAILABLE_JOBS where JOB_CODE=?),"
                            + " Price = (SELECT JOB_COST from AVAILABLE_JOBS where JOB_CODE = ?),"
                            + " SpecialInstructions=? "
                            + " WHERE JobID=? ",
                    new String[]{},
                    job.getCustomerNumber(), job.getDeadline(), job.getJobCode(), job.getSpecialInstructions(),
                    String.valueOf(job.getJobId())).get("ROWS");
            if(rows<=0){
                dbUtilities.rollbackChanges();
                return result;
            }
            result=job.getJobId();
            dbUtilities.commitChanges();
        }
        return result;
    }
    public long updateBooking(String requestType, long bookingId, long location, long userId){
        long result=-1;
        if(null == requestType){
            return result;
        }else switch (requestType) {
            case "updateLocation":
                requestType="UPDATE_LOCATION";
                break;
            default:
                return result;
        }
        try {
            ResultSet resultSet = dbUtilities.executeQuery("select jt.jobId, t.Location from job_tasks jt"
                    + " left join task t on t.TaskID=jt.TaskID where jt.jobId=?", new String[]{String.valueOf(bookingId)});

            if(resultSet!=null && resultSet.next()) {
                result = (int)dbUtilities.insertOrUpdateRecord("update job_tasks set jobId=?, taskId=?,"
                                + " staffWorker=?, startTime = sysdate() ",
                        new String[] {},
                        String.valueOf(bookingId), String.valueOf(location), String.valueOf(userId)
                ).get("ROWS");

                dbUtilities.commitChanges();
            }else {
                result = (int)dbUtilities.insertOrUpdateRecord("INSERT into job_tasks (jobId, taskId, staffWorker, startTime)"
                                + " values(?,?,?,sysdate())",
                        new String[] {},
                        String.valueOf(bookingId), String.valueOf(location), String.valueOf(userId)
                ).get("ROWS");

                dbUtilities.commitChanges();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            dbUtilities.rollbackChanges();
        }
        return result;
    }

    public Map<Long, String> getTasksList() {
        Map<Long, String> tasksList = new HashMap<>();
        try {
            ResultSet resultSet = dbUtilities.executeQuery("select t.TaskId, t.Location from task t",
                    new String[]{});

            if(resultSet!=null ) {
                while(resultSet.next()) {
                    tasksList.put(resultSet.getLong("taskId"), resultSet.getString("Location"));
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return tasksList;
    }

    public String getCurrentTask(Long bookingId) {
        try {
            ResultSet resultSet = dbUtilities.executeQuery("select t.Location from job_tasks jt"
                    + " left join task t on t.TaskID=jt.TaskID where jobId=?", new String[]{String.valueOf(bookingId)});

            if(resultSet!=null && resultSet.next()) {
                return resultSet.getString("Location");
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return "N/A";
    }
}