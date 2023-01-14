package pers.common;

import pers.beans.AvailableJobsInfoBean;
import pers.beans.UserMenuAccessBean;
import pers.dbutils.DBUtilities;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pers.beans.DiscountType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Alia
 */
public class ApplicationConstants {
    public static final String DB_PRODUCT_MSSQL="Microsoft SQL Server";
    public static final String DB_PRODUCT_MYSQL="mysql";
    
    //Email address for sending customers confirmation
    public static final String EMAIL_SENDER="xyz@gmail.com";
    public static final String EMAIL_PASSWORD="xyzabc";
    
    public static final String BAP_ADMIN_CODE="BAP-ADMIN";
    public static final String BAP_STAFF_CODE="BAP-STAFF";
    
  //Hash map including all the destinations
//    public static final Map<String, AvailableJobsInfoBean> JOBS=new HashMap<>();
//    static{
//        try {
//            ResultSet rs = new DBUtilities().executeQuery("Select JOB_CODE, JOB_TITLE, JOB_COST from AVAILABLE_JOBS", new String[]{});
//            while(rs.next()){
//                JOBS.put(rs.getString("JOB_CODE"),new AvailableJobsInfoBean(rs.getString("JOB_CODE"),
//                        rs.getString("JOB_TITLE"), rs.getInt("JOB_COST")));
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    public static final Map<Long, DiscountType> DISCOUNT_TYPES = new HashMap<>();
    static{
        try {
            ResultSet rs = new DBUtilities().executeQuery("Select DiscountTypeID, DiscountType from discounttype", new String[]{});
            while(rs.next()){
                DiscountType dt = new DiscountType(rs.getString("DiscountType"), rs.getLong("DiscountTypeID"));
                DISCOUNT_TYPES.put(dt.getDiscountTypeId(),dt);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static final Map<String, UserMenuAccessBean> groupAccessBean = new HashMap<>();
    static {
    	UserMenuAccessBean adminAccess = new UserMenuAccessBean(true, true, true, true, true, true);
    	UserMenuAccessBean staffAccess = new UserMenuAccessBean(true, false, true, true, false, true);
    	
    	groupAccessBean.put(BAP_ADMIN_CODE, adminAccess);
    	groupAccessBean.put(BAP_STAFF_CODE, staffAccess);
    }
    
    
    
}