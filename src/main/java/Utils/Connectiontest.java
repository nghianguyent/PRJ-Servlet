///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Utils;
//
//import java.sql.*;
//
///**
// *
// * @author trong
// */
//public class Connectiontest {
//
//        public static void main(String[] args) {
//
//                Connection conn = null;
//                try {
//                        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//                        conn
//                                = DriverManager.getConnection("jdbc:mysql://localhost:3308/demo_java", "root", "nghia14302");
//                        // Do something with the Connection
//
//                } catch (Exception ex) {
//                        // handle any errors
//                        System.out.println("SQLException: " + ex.getMessage());
//                        System.out.println("SQLState: " + ex.getSQLState());
//                        System.out.println("VendorError: " + ex.getErrorCode());
//                }
//        }
//}
