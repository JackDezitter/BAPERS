package pers.dbutils;

import MyPackage.Home;
import pers.beans.Job;
import pers.beans.Task;
import pers.utils.AuthUser;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TaskAndJobDBOperations {

    private static Connection connection = DBConnection.getConnection();


    //Inserts instance of Job into the database
    public static int CreateJob(String urgency, int customerID, LocalDateTime deadline, String specialInstructions, LocalDateTime dateCreated) throws SQLException {
        LocalDateTime payByDate = null;
        try {
            String query;
            query = "SELECT CustomerTypeID FROM Customer WHERE CustomerID =?";
            PreparedStatement getCustomerType = connection.prepareStatement(query);
            getCustomerType.setInt(1, customerID);
            ResultSet customerType = getCustomerType.executeQuery();
            customerType.next();
            System.out.println(deadline);
            if(customerType.getString(1).equals("Valued")){
                payByDate = LocalDateTime.now().plusMonths(1).withDayOfMonth(10);
            }
            else if(customerType.getString(1).equals("Normal")){
                payByDate = deadline;
            }

            query = "INSERT into job (Urgency, CustomerID, Deadline, SpecialInstructions, DateCreated, PayByDate)" + " values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //If urgency field is not left blank
            if(urgency.isBlank() == false) preparedStatement.setString(1, urgency);
                //If no urgency specified set to normal
            else preparedStatement.setString(1, "Normal");
            //Inputs customerID
            preparedStatement.setInt(2, customerID);
            //Inputs deadline date

            System.out.println(deadline);
            preparedStatement.setTimestamp(3, Timestamp.valueOf(deadline));

            //If special instructions field is not left blank
            if(specialInstructions.isBlank() == false) preparedStatement.setString(4, specialInstructions);
                //If field left blank input None
            else preparedStatement.setString(4, "None");
            //Inputs date created
            preparedStatement.setTimestamp(5, Timestamp.valueOf(dateCreated));
            preparedStatement.setTimestamp(6, Timestamp.valueOf(payByDate));
            preparedStatement.execute();

            //Gets the Primary key of the entry and assigns it to jobID
            ResultSet set = preparedStatement.getGeneratedKeys();
            set.next();
            int jobID = set.getInt(1);

            System.out.println("beans.Job Added");
            //Returns the beans.Job ID
            return jobID;

        }
        //If error with adding job to database
        catch (Exception e){
            System.err.println("Got an exception, Create beans.Job!");
            System.err.println(e.getMessage());
        }
        return 0;
    }

    //Adds tasks to jobs in jobtasks database
    //Takes the job ID and the list of tasks assigned to job as parameters
    public static void AddJobTasks(int jobID, String urgency,int customerID , List<String> tasks) throws SQLException {
        try {
            String query;
            //Initialises the variable that stores total cost of the job
            float totalPrice = 0;
            float discount = 0;
            ResultSet taskDiscountSet = null;

            //Gets Customer Discount Type
            query = "SELECT DiscountTypeID FROM Customer WHERE CustomerID = ?";
            PreparedStatement discountTypeSearch = connection.prepareStatement(query);
            discountTypeSearch.setInt(1, customerID);
            ResultSet set = discountTypeSearch.executeQuery();
            set.next();
            int discountTypeID = set.getInt(1);
            //Fixed
            if (discountTypeID == 1){
                query = "SELECT FixedRate FROM Fixed WHERE CustomerID = ?";
                PreparedStatement discountSearch = connection.prepareStatement(query);
                discountSearch.setInt(1, customerID);
                ResultSet discountSearchSet = discountSearch.executeQuery();
                discountSearchSet.next();
                discount = discountSearchSet.getFloat(1);
            }

            //For each task in the list of tasks
            for (int i = 0; i < tasks.size() ; i ++){
                //Assigns task ID number to the task stored at position i
                int taskIDNumber = Integer.parseInt(tasks.get(i));
                //Retrieves price of each task from task database
                query = "SELECT Price FROM task WHERE taskID = ?";
                PreparedStatement priceSelect = connection.prepareStatement(query);
                priceSelect.setInt(1, taskIDNumber);
                ResultSet priceSet = priceSelect.executeQuery();
                priceSet.next();
                float taskPrice = priceSet.getFloat(1) ;
                //Adds task price to total price of beans.Job

                if(discountTypeID == 2) {
                    query = "SELECT DiscountPercentage FROM variable WHERE taskID = ? AND CustomerID = ?";
                    PreparedStatement taskDiscountSelect = connection.prepareStatement(query);
                    taskDiscountSelect.setInt(1, taskIDNumber);
                    taskDiscountSelect.setInt(2, customerID);
                    taskDiscountSet = taskDiscountSelect.executeQuery();

                    if (taskDiscountSet.next() == true) {

                        taskPrice = taskPrice - (taskPrice * taskDiscountSet.getFloat(1));
                    }
                }
                totalPrice += taskPrice;

                //Adds the jobs, tasks and price of the task to the database jobtasks
                query = "INSERT into jobtasks(JobID, TaskID, Price)" + " values (?,?,?)";
                PreparedStatement insertJobTask = connection.prepareStatement(query);
                insertJobTask.setInt(1, jobID);
                insertJobTask.setInt(2, taskIDNumber);
                insertJobTask.setFloat(3, taskPrice);
                insertJobTask.execute();

            }
            if (urgency.toLowerCase() == "very urgent"){
                totalPrice = totalPrice*2;
            }
            if (discountTypeID == 1) {
                totalPrice = totalPrice - (totalPrice*discount);
            }
            //Updates the price column of the job
            query = "UPDATE job SET Price = ? WHERE JobID = ?";
            PreparedStatement insertJobPrice = connection.prepareStatement(query);
            insertJobPrice.setFloat(1, totalPrice);
            insertJobPrice.setInt(2, jobID);
            insertJobPrice.execute();

            //connection.close();

        }
        //If error with queries
        catch (Exception e){
            System.err.println("Got an exception, Add beans.Job Tasks!");
            System.err.println(e.getMessage());
        }

    }
    public static ResultSet SearchJob(int jobID, int customerID, String urgency, LocalDate deadline, String specialInstruction, int numberTasksLeft, LocalDate dateCreated, String paidStatus, LocalDate payByDate) throws SQLException {
        String query;
        try {
            //Retrieves price of each task from task database
            query = "SELECT * FROM job WHERE JobID = ? OR CustomerID = ?  OR Urgency = ?  OR Deadline = ? OR SpecialInstructions = ? OR DateCreated = ? OR PaidStatus = ? OR PayByDate = ? OR NumberOfTasksRemaining = ?";
            PreparedStatement jobSearch = connection.prepareStatement(query);
            jobSearch.setInt(1, jobID);
            jobSearch.setInt(2, customerID);
            jobSearch.setString(3, urgency);
            if(deadline ==null){
                jobSearch.setDate(4, null);
            }
            else {jobSearch.setDate(4, Date.valueOf(deadline));}
            jobSearch.setString(5, specialInstruction);
            if(dateCreated == null){
                jobSearch.setDate(6, null);
            }
            else {
                jobSearch.setDate(6, Date.valueOf(dateCreated));
            }
            jobSearch.setString(7, paidStatus);
            if(payByDate == null) {
                jobSearch.setDate(8, null);
            }
            else {
                jobSearch.setDate(8, Date.valueOf(payByDate));
            }
            jobSearch.setInt(9, numberTasksLeft);

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
    //Counts number of jobs remaining for a task
    public static void CountJobTasks(int jobID) throws SQLException {
        try {
            String query;
            //Counts number of taskIDs where JobID is the same and the task is not complete
            query = "SELECT COUNT(TaskID) FROM jobtasks WHERE JobID = ? and Status != 'Complete'";
            PreparedStatement countJobTasks = connection.prepareStatement(query);
            countJobTasks.setInt(1, jobID);
            ResultSet set = countJobTasks.executeQuery();
            set.next();
            //Assigns result to taskCount
            int taskCount = set.getInt(1) ;

            //Updates Number of tasks remaining column for selected beans.Job to the taskCount
            query = "UPDATE job SET NumberOfTasksRemaining = ? WHERE JobID = ?";
            PreparedStatement insertTasksRemaining = connection.prepareStatement(query);
            insertTasksRemaining.setInt(1, taskCount);
            insertTasksRemaining.setInt(2, jobID);
            insertTasksRemaining.execute();
            //connection.close();

            //If the task counter is zero mark the job as complete
            if(taskCount == 0){
                System.out.println("beans.Job Complete");
                query = "UPDATE job SET JobStatus = 'Complete' WHERE JobID = ?";
                PreparedStatement setJobStatus = connection.prepareStatement(query);
                setJobStatus.setInt(1, jobID);
                setJobStatus.executeUpdate();
            }

        }
        //If exception report error on section
        catch (Exception e){
            System.err.println("Got an exception, Count beans.Job Tasks!");
            System.err.println(e.getMessage());
        }
        CalculateEstimatedTime(jobID);
    }

    public static void CalculateEstimatedTime(int jobID) throws SQLException {
        try {
            String query;

            query = "SELECT TaskID FROM jobtasks WHERE jobID = ? AND Status != 'Complete'";
            PreparedStatement getTasks = connection.prepareStatement(query);
            getTasks.setInt(1, jobID);
            ResultSet set = getTasks.executeQuery();
            LocalTime estimatedTimeRemaining = LocalTime.of(0,0,0);
            while (set.next()){
                query = "SELECT Duration FROM task WHERE TaskID = ?";
                PreparedStatement EstimatedTimeCount = connection.prepareStatement(query);
                EstimatedTimeCount.setInt(1, set.getInt(1));
                ResultSet times = EstimatedTimeCount.executeQuery();
                times.next();

                LocalTime time = times.getObject(1, LocalTime.class);
                estimatedTimeRemaining = estimatedTimeRemaining.plusHours(time.getHour()).plusMinutes(time.getMinute()).plusSeconds(time.getSecond());

            }
            query = "UPDATE job SET EstimatedTimeRemaining = ? WHERE JobID = ?";
            PreparedStatement updateTimeRemaining = connection.prepareStatement(query);

            updateTimeRemaining.setTime(1, Time.valueOf(estimatedTimeRemaining));
            updateTimeRemaining.setInt(2, jobID);
            updateTimeRemaining.execute();

            //connection.close();

        }
        catch (Exception e){
            System.err.println("Got an exception, Estimated Time!");
            System.err.println(e.getMessage());
        }

    }
    //This method gets a list of all the customers from the db
    public List<Job> getJobList(String whereClause, List<String> paramsList){
        List<Job> result=new ArrayList<>();
        String[] params = new String[paramsList.size()];
        for(int inx=0; inx<paramsList.size();inx++){
            params[inx]=paramsList.get(inx);
        }
        DBUtilities dbUtilities = new DBUtilities();
        ResultSet resultSet = dbUtilities.executeQuery("SELECT JobID,"
                + " Urgency, CustomerID, Deadline, SpecialInstructions, NumberOfTasksRemaining, DateCreated, PaidStatus, PayByDate, EstimatedTimeRemaining, JobStatus"
                + " FROM job "+whereClause,params);

        try {
            while(resultSet.next()){
                Job job = new Job();
                job.setJobID(resultSet.getInt("JobID"));
                job.setUrgency(resultSet.getString("Urgency"));
                job.setCustomerID(resultSet.getInt("CustomerID"));
                job.setDeadline(resultSet.getTimestamp("Deadline").toLocalDateTime());
                job.setSpecialInstruction(resultSet.getString("SpecialInstructions"));
                job.setNumberOfTasksRemaining(resultSet.getInt("NumberOfTasksRemaining"));
                job.setDateCreated(resultSet.getTimestamp("DateCreated"));
                job.setPaidStatus(resultSet.getString("PaidStatus"));
                job.setPayByDate(resultSet.getTimestamp("PayByDate"));
                job.setEstimatedTimeRemaining(resultSet.getTime("EstimatedTimeRemaining").toLocalTime());
                job.setJobStatus(resultSet.getString("JobStatus"));

                result.add(job);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }

    public List<Job> getJobList(ResultSet resultSet){
        List<Job> result=new ArrayList<>();
        try {
            while(resultSet.next()){
                Job job = new Job();
                job.setJobID(resultSet.getInt("JobID"));
                job.setUrgency(resultSet.getString("Urgency"));
                job.setCustomerID(resultSet.getInt("CustomerID"));
                job.setDeadline(resultSet.getTimestamp("Deadline").toLocalDateTime());
                job.setSpecialInstruction(resultSet.getString("SpecialInstructions"));
                job.setNumberOfTasksRemaining(resultSet.getInt("NumberOfTasksRemaining"));
                job.setDateCreated(resultSet.getTimestamp("DateCreated"));
                job.setPaidStatus(resultSet.getString("PaidStatus"));
                job.setPayByDate(resultSet.getTimestamp("PayByDate"));
                job.setEstimatedTimeRemaining(resultSet.getTime("EstimatedTimeRemaining").toLocalTime());
                job.setJobStatus(resultSet.getString("JobStatus"));

                result.add(job);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }

    //This method gets a list of all the customers from the db
    public List<Task> getTaskList(String whereClause, List<String> paramsList){
        List<Task> result=new ArrayList<>();
        String[] params = new String[paramsList.size()];
        for(int inx=0; inx<paramsList.size();inx++){
            params[inx]=paramsList.get(inx);
        }
        DBUtilities dbUtilities = new DBUtilities();
        ResultSet resultSet = dbUtilities.executeQuery("SELECT * FROM task "+whereClause,params);

        try {
            while(resultSet.next()){
                Task task = new Task();
                task.setTaskID(resultSet.getInt(1));
                task.setLocation(resultSet.getString(2));
                task.setDescription(resultSet.getString(3));
                task.setPrice(resultSet.getFloat(4));
                task.setDuration(resultSet.getTime(5).toLocalTime());
                result.add(task);
            }
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
        return result;
    }
    public static void CreateTask(String location, String description, float price,LocalTime duration) throws SQLException {
        try {
            String query = "INSERT into task (Location, Description, Price , Duration)" + " values (?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, location);
            preparedStatement.setString(2, description);
            preparedStatement.setFloat(3,price);
            preparedStatement.setTime(4, Time.valueOf(duration));
            preparedStatement.execute();
            System.out.println("beans.Task Added" );

        }
        catch (Exception e) {
            System.err.println("Got an exception, Create beans.Task!");
            System.err.println(e.getMessage());
        }
    }

    public static boolean StartTask(int jobID, int taskID, int staffID) throws SQLException {
        boolean overdue = false;
        try {
            boolean affectedRows;
            String query;
            query = "UPDATE jobtasks SET Status = 'In Progress', StartTime = ?, StaffWorker = ?  WHERE JobID = ? AND TaskID = ? AND Status = 'Pending'";
            PreparedStatement startTask = connection.prepareStatement(query);
            startTask.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            startTask.setInt(2,staffID);
            startTask.setInt(3, jobID);
            startTask.setInt(4, taskID);
            affectedRows = startTask.executeUpdate() > 0;
            if (affectedRows == false){
                System.out.println("Could not perform Update, Please check the task is pending or if the job has this task");
            }
            overdue = CalculateOverdue(jobID);
            //connection.close();

        }
        catch (Exception e){
            System.err.println("Got an exception, Start beans.Task!");
            System.err.println(e.getMessage());
        }
        return overdue;
    }

    public static boolean CompleteTask(int jobID, int taskID) throws SQLException {
        boolean overdue = false;
        try {
            boolean affectedRows;
            String query;
            int staffID = Home.getStaffID();

            query = "UPDATE jobtasks SET Status = 'Complete', EndTime = ?, StaffWorker = ? WHERE JobID = ? AND TaskID = ? AND Status = 'In Progress'";

            PreparedStatement completeTask = connection.prepareStatement(query);

            completeTask.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));

            completeTask.setInt(2, staffID);
            completeTask.setInt(3, jobID);
            completeTask.setInt(4, taskID);
            affectedRows = completeTask.executeUpdate() > 0;
            if (affectedRows == false){
                System.out.println("Could not perform Update, Please check the task is in progress or if the job has this task");
            }
            CountJobTasks(jobID);
            overdue = CalculateOverdue(jobID);
            //connection.close();

        }
        catch (Exception e){
            System.err.println("Got an exception, Complete beans.Task!");
            System.err.println(e.getMessage());
        }
        return overdue;
    }

    public static boolean CalculateOverdue(int jobID) throws SQLException {
        boolean overdue = false;
        try {
            String query;

            query = "SELECT Deadline, EstimatedTimeRemaining FROM job WHERE jobID = ?";
            PreparedStatement calculateOverdue = connection.prepareStatement(query);
            calculateOverdue.setInt(1, jobID);
            ResultSet set = calculateOverdue.executeQuery();
            set.next();
            LocalDateTime deadline = set.getObject(1, LocalDateTime.class);
            LocalTime timeRemaining = set.getObject(2, LocalTime.class);

            LocalDateTime estimatedCompletion = LocalDateTime.now().plusHours(timeRemaining.getHour()).plusMinutes(timeRemaining.getSecond());
            if (estimatedCompletion.isAfter(deadline)){
                System.out.println("Job Number: " + jobID + " will over run");
                overdue = true;
            }

            //connection.close();

        }
        catch (Exception e){
            System.err.println("Got an exception, Calculate Overdue!");
            System.err.println(e.getMessage());
        }
        return overdue;
    }
}
