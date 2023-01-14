package pers.dbutils;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentDBOperations {
    private static Connection connection = DBConnection.getConnection();

    public static float CalculatePaymentPrice(String jobIDs, String customerEmail) throws SQLException {
        float jobPrice = 0;
        List<String> jobIDList = null;
        if (jobIDs != null) {
            jobIDList = Arrays.asList(jobIDs.split(","));
        }
        String query;
        float totalPrice = 0;
        //Initialises the variable that stores total cost of the jobs
        if (jobIDList != null) {
            for (int i = 0; i < jobIDList.size(); i++) {
                System.out.println("Job Found");
                //Assigns task ID number to the task stored at position i
                int jobIDNumber = Integer.parseInt(jobIDList.get(i));
                //Retrieves price of each task from task database
                query = "SELECT Price FROM job WHERE JobID = ? AND PaidStatus = 'Unpaid'";
                PreparedStatement jobPriceSelect = connection.prepareStatement(query);
                jobPriceSelect.setInt(1, jobIDNumber);
                ResultSet set = jobPriceSelect.executeQuery();
                set.next();
                jobPrice = set.getFloat(1);
                //Adds job price to total price of Jobs
                totalPrice += jobPrice;
            }
        }
        else if (customerEmail != ""){

            int customerID = CustomerIDSearch(customerEmail);
            System.out.println(customerID);
            query = "SELECT CustomerTypeID, DiscountTypeID FROM Customer WHERE CustomerID = ?";
            PreparedStatement customerTypeSelect = connection.prepareStatement(query);
            customerTypeSelect.setInt(1, customerID);
            ResultSet customerTypeSet = customerTypeSelect.executeQuery();
            customerTypeSet.next();
            System.out.println("'" + customerTypeSet.getString(1) + "'");
            if (customerTypeSet.getString(1).equals("Valued")){
                System.out.println("Valued Customer");
                query = "SELECT Price FROM job WHERE CustomerID = ? AND PaidStatus = 'Unpaid' AND (DateCreated between ? and ?)";
                PreparedStatement valuedJob = connection.prepareStatement(query);
                valuedJob.setInt(1, customerID);
                valuedJob.setDate(2, Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(11)));
                valuedJob.setDate(3, Date.valueOf(LocalDate.now().withDayOfMonth(10)));
                ResultSet valuedJobSet = valuedJob.executeQuery();

                while (valuedJobSet.next()) {
                    jobPrice = valuedJobSet.getFloat(1);
                    System.out.println("valuedJob");
                    //Adds job price to total price of Jobs
                    totalPrice += jobPrice;
                }
                if (customerTypeSet.getInt(2) == 3){
                    System.out.println("Hello");
                    query = "SELECT * FROM flexbands WHERE CustomerID = ?";
                    PreparedStatement getDiscountDetails = connection.prepareStatement(query);
                    getDiscountDetails.setInt(1, customerID);
                    ResultSet discountDetailSet = getDiscountDetails.executeQuery();
                    discountDetailSet.next();
                    System.out.println("Band EXTRACT");
                    int band = 3;
                    if (totalPrice <= discountDetailSet.getFloat(3)){
                        band  = 2;
                    }
                    if (totalPrice <= discountDetailSet.getFloat(2)){
                        band = 1;
                    }
                    System.out.println(band);
                    System.out.println(discountDetailSet.getFloat(band + 3));
                    System.out.println(totalPrice);
                    System.out.println(totalPrice - (totalPrice * (discountDetailSet.getFloat(band + 3)/100)));
                    totalPrice = totalPrice - (totalPrice * (discountDetailSet.getFloat(band + 3)/100));
                }
            }
            else {
                query = "SELECT Price, DateCreated FROM job WHERE CustomerID = ? AND PaidStatus = 'Unpaid'";
                PreparedStatement jobPriceSelect = connection.prepareStatement(query);
                jobPriceSelect.setInt(1, customerID);
                ResultSet jobPriceSet = jobPriceSelect.executeQuery();
                while (jobPriceSet.next()) {
                    jobPrice = jobPriceSet.getFloat(1);
                    //Adds job price to total price of Jobs
                    totalPrice += jobPrice;
                }
            }
        }
        return totalPrice;

    }

    public static List<String> PayJobs(String jobIDs, String customerEmail, String paymentType) throws SQLException {
        String query;
        int customerID = CustomerIDSearch(customerEmail);
        List<String> jobIDList = new ArrayList<>();
        ResultSet jobMatch = null;

        query = "SELECT CustomerTypeID, DiscountTypeID FROM Customer WHERE CustomerID = ?";
        PreparedStatement customerTypeSelect = connection.prepareStatement(query);
        customerTypeSelect.setInt(1, customerID);
        ResultSet customerTypeSet = customerTypeSelect.executeQuery();
        customerTypeSet.next();

        if (jobIDs != null) {
            jobIDList = Arrays.asList(jobIDs.split(","));
        }

        else{
            if(customerTypeSet.getString(1).equals("Valued")){
                query = "SELECT JobID FROM job WHERE CustomerID = ? AND PaidStatus = 'Unpaid' AND DateCreated between ? and ?";
                PreparedStatement valuedJob = connection.prepareStatement(query);
                valuedJob.setInt(1, customerID);
                valuedJob.setDate(2, Date.valueOf(LocalDate.now().minusMonths(1).withDayOfMonth(11)));
                valuedJob.setDate(3, Date.valueOf(LocalDate.now().withDayOfMonth(10)));
                jobMatch = valuedJob.executeQuery();
            }
            else {
                query = "SELECT JobID FROM job WHERE CustomerID = ? AND PaidStatus = 'Unpaid'";
                PreparedStatement searchJob = connection.prepareStatement(query);
                searchJob.setInt(1, customerID);
                jobMatch = searchJob.executeQuery();
            }
            ResultSet jobMatch2 = jobMatch;
            while(jobMatch2.next()){
                String jobID = String.valueOf(jobMatch2.getInt(1));
                System.out.println(String.valueOf(jobMatch2.getInt(1)));
                jobIDList.add(jobID);
            }
        }

        for (int i = 0; i < jobIDList.size() ; i ++){
            //Assigns task ID number to the task stored at position i
            int jobIDNumber = Integer.parseInt(jobIDList.get(i));
            //Retrieves price of each task from task database
            query = "UPDATE job SET PaidStatus = 'Paid' WHERE JobID = ? AND PaidStatus = 'Unpaid'";
            PreparedStatement jobPaidUpdate = connection.prepareStatement(query);
            jobPaidUpdate.setInt(1, jobIDNumber);
            jobPaidUpdate.executeUpdate();

            query = "INSERT into typeOfPayment (JobID, PaymentType)" + " values (?,?)";
            PreparedStatement insertPaymentType = connection.prepareStatement(query);
            insertPaymentType.setInt(1, jobIDNumber);
            insertPaymentType.setString(2, paymentType);
            insertPaymentType.execute();
        }
        return jobIDList;
    }

    public static void PayCardJob(List<String> jobMatch, String customerEmail, String cardNumber, String cardType, String CVV, String expiry) throws SQLException {
        String query;
        List<String> jobIDList = jobMatch;
        System.out.println(jobIDList.size());
        int jobIDNumber;
        expiry = "29/" + expiry;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expiryDate = LocalDate.parse(expiry, formatter);
        if (customerEmail == null) {
            System.out.println("Must Have Valid Customer Email");
        }
        else{
            int customerID = CustomerIDSearch(customerEmail);

            for (int i = 0; i < jobIDList.size(); i++) {
                jobIDNumber = Integer.parseInt(jobIDList.get(i));
                System.out.println(jobIDNumber);
                System.out.println(cardNumber.substring(12,cardNumber.length()));
                query = "INSERT INTO cardDetails(CardNumber, ExpiryDate, SecurityCode, CustomerID, JobID, CardType) values(?,?,?,?,?,?)";
                PreparedStatement insertCardDetails = connection.prepareStatement(query);
                insertCardDetails.setString(1, cardNumber.substring(12,cardNumber.length()));
                insertCardDetails.setDate(2, Date.valueOf(expiryDate));
                insertCardDetails.setString(3, CVV);
                insertCardDetails.setInt(4, customerID);
                insertCardDetails.setInt(5, jobIDNumber);
                insertCardDetails.setString(6, cardType);
                insertCardDetails.execute();
                System.out.println("Hey");

            }
            System.out.println("Card Details Recorded");
        }

    }
    public static int CustomerIDSearch(String email) throws SQLException {
        int customerID = 0;
        String query;
        System.out.println(email);
        query = "SELECT CustomerID FROM Customer WHERE Email = ?";
        PreparedStatement customerIDSelect = connection.prepareStatement(query);
        customerIDSelect.setString(1, email);
        ResultSet set = customerIDSelect.executeQuery();
        if (set.next()) {
            customerID = set.getInt(1);
        }
        return customerID;
    }

    public static boolean CalculateOverduePayment(int jobID) throws SQLException {
        boolean overdue = false;
        try {
            String query;
            query = "SELECT PayByDate, PaidStatus FROM job WHERE jobID = ?";
            PreparedStatement calculateOverdue = connection.prepareStatement(query);
            calculateOverdue.setInt(1, jobID);
            ResultSet set = calculateOverdue.executeQuery();
            set.next();
            LocalDate payByDate;
            if(set.getDate(1) != null) {
                payByDate = set.getDate(1).toLocalDate();
            }
            else{
                payByDate = LocalDate.now();
            }
            String paidStatus = set.getString(2);
            if (LocalDateTime.now().isAfter(payByDate.atStartOfDay()) && !paidStatus.equals("Paid")){
                overdue = true;
            }

            //connection.close();

        }
        catch (Exception e){
            System.err.println("Got an exception, Calculate Overdue Payment!");
            System.err.println(e.getMessage());
        }
        return overdue;
    }

}
