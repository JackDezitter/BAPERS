/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pers.common;

import pers.beans.CustomerInfoBean;
import pers.beans.StaffInfoBean;
import pers.beans.BookingInfoBean;
import pers.dbutils.DBUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alia
 */
public class CommonUtils {
    
    //Validation for profile details
    public List<String> validateProfileDetails(Object profileDetails){
        List<String> errors = new ArrayList<>();
        //If any fields are empty then give the following errors
        if(profileDetails==null){
            errors.add("Invalid Data.");
        }else{
            if(profileDetails instanceof CustomerInfoBean){
                CustomerInfoBean custProfile =  (CustomerInfoBean)profileDetails;
                //First name field must not be empty
                if(custProfile.getFirstName()==null || custProfile.getFirstName().trim().isEmpty()){
                    errors.add("First Name can't be empty");
                }
                //Last name field must not be empty
                if(custProfile.getSurname()==null || custProfile.getSurname().trim().isEmpty()){
                    errors.add("Last Name can't be empty");
                }
                //Phone number field must not be empty
                if(custProfile.getPhoneNumber()==null || custProfile.getPhoneNumber().trim().isEmpty()){
                    errors.add("Phone can't be empty");
                }
                //Email field must not be empty
                if(custProfile.getEmail()==null || custProfile.getEmail().trim().isEmpty()){
                    errors.add("Email can't be empty");
                }
                //Address field must not be empty
                if(custProfile.getAddress()==null || custProfile.getAddress().trim().isEmpty()){
                    errors.add("Address can't be empty");
                }
           }
            if(profileDetails instanceof StaffInfoBean){
                StaffInfoBean member =  (StaffInfoBean)profileDetails;
                //First name field must not be empty
                if(member.getFirstName()==null || member.getFirstName().trim().isEmpty()){
                    errors.add("First Name can't be empty");
                }
                //Last name field must not be empty
                if(member.getLastName()==null || member.getLastName().trim().isEmpty()){
                    errors.add("Last Name can't be empty");
                }
                //Phone number field must not be empty
                if(member.getPhone()==null || member.getPhone().trim().isEmpty()){
                    errors.add("Phone can't be empty");
                }
                //Email field must not be empty
                if(member.getEmail()==null || member.getEmail().trim().isEmpty()){
                    errors.add("Email can't be empty");
                }
                //Address field must not be empty
                if(member.getAddress()==null || member.getAddress().trim().isEmpty()){
                    errors.add("Address can't be empty");
                }
           }
        }
        return errors;
    }
    
    //Validation of booking data
    public List<String> validateBookingData(BookingInfoBean booking){
        List<String> errors = new ArrayList<>();
        if(booking==null){
            errors.add("Invalid Booking Data.");
        }else{
            //Customer Number field must not be empty
            if(booking.getCustomerNumber()==null || booking.getCustomerNumber().trim().isEmpty()){
                errors.add("Customer Number can't be empty");
            }else if(!isValidCustomerNumber(booking.getCustomerNumber())){
                errors.add("Invalid Customer Number");
            }
            if(booking.getJobCode()==null || booking.getJobCode().trim().isEmpty()){
                errors.add("Job Code can't be empty");
            }
            if(booking.getDeadline()==null || booking.getDeadline().trim().isEmpty()){
                errors.add("Deadline can't be empty");
            }else{
                try{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    sdf.parse(booking.getDeadline());
                }catch(Exception ex){
                    errors.add("Invalid Date format. Please use (DD-MM-YYYY)");
                }
            }
        }
        return errors;
    }
    
    //Validation of members data
    public List<String> validateMemberData(StaffInfoBean member){
        List<String> errors = new ArrayList<>();
        if(member==null){
            errors.add("Invalid Member Data.");
        }else{
            //First name field must not be empty
            if(member.getFirstName()==null || member.getFirstName().trim().isEmpty()){
                errors.add("FirstName can't be empty");
            }
            //Last name field must not be empty
            if(member.getLastName()==null || member.getLastName().trim().isEmpty()){
                errors.add("LastName can't be empty");
            }
            //Email field must not be empty
            if(member.getEmail()==null || member.getEmail().trim().isEmpty()){
                errors.add("Email can't be empty");
                //Same email cannot be used
            }else if(isEmailRegistered(member.getEmail())){
                errors.add("email already registered.");
            }
            //Phone number field must not be empty
            if(member.getPhone()==null || member.getPhone().trim().isEmpty()){
                errors.add("Phone can't be empty");
            }
        }
        //shows all the errors
        return errors;
    }
    //Validation of customers data
    public List<String> validateCustomerData(CustomerInfoBean customer){
        List<String> errors = new ArrayList<>();
        if(customer==null){
            errors.add("Invalid Customer Data.");
        }else{
            
            //First name field must not be empty
            if(customer.getFirstName()==null || customer.getFirstName().trim().isEmpty()){
                errors.add("FirstName can't be empty");
            }
            //Last name field must not be empty
            if(customer.getSurname()==null || customer.getSurname().trim().isEmpty()){
                errors.add("LastName can't be empty");
            }
            //Email field must not be empty
            if(customer.getEmail()==null || customer.getEmail().trim().isEmpty()){
                errors.add("Email can't be empty");
            }
            //cannot use an already registered email
            else if(isEmailRegistered(customer.getEmail())){
                errors.add("email already registered.");
            }
        }
        return errors;
    }
    //Makes sure that the username entered is available in the database
    public boolean isUsernameAvailable(String username){
        ResultSet rs = new DBUtilities().executeQuery("select user_id from user_login_details where user_name=?", new String[]{username});
        try {
            if(!rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    //Checks if the email address entered is registered in the system for the customers and the staff
    public boolean isEmailRegistered(String email){
        ResultSet rs = new DBUtilities().executeQuery("select customerID from customer where email=?", new String[]{email});
        try {
            if(!rs.next()){
                rs = new DBUtilities().executeQuery("select StaffID from Staff where email=?", new String[]{email});
                if(!rs.next()){
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    //Checks the database if the customer number is valid
    public boolean isValidCustomerNumber(String customerNumber){
        ResultSet rs = new DBUtilities().executeQuery("select user_id from customers where CUSTOMER_NUMBER=?", new String[]{customerNumber});
        try {
            if(!rs.next()){
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }
    public boolean isRecordAccessible(long bookingId, long user_id){
        ResultSet rs = new DBUtilities().executeQuery("select booking_id from bookings where booking_id=? and CUSTOMER_ID=(select customer_id from customers where user_id=?)", new String[]{String.valueOf(bookingId), String.valueOf(user_id)});
        try {
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
