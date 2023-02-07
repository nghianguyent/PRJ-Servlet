/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import binhnd.utils.DBHelpers;

/**
 *
 * @author tklin
 */
public class RegistrationDAO implements Serializable {

    public RegistrationDTO checkLogin(String username, String password) throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Select username, lastname"
                        + "\nFrom Registration"
                        + "\nWhere username = ? And password = ?";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //2.3 Excute Query -> Result Set
                rs = stm.executeQuery();
                //2.4 Process Result Set
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    result = new RegistrationDTO(username, password, fullName, false);

                }
            }//end if connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList; //tự phát sinh code khi viết đúng quy luật
    }

        public void searchLastname(String searchValue) throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Select username, password, lastname, isAdmin "
                        + "from Registration "
                        + "Where lastname like ?";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //2.3 Excute Query -> Result Set
                rs = stm.executeQuery();
                //2.4 Process Result Set
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    RegistrationDTO dto = new RegistrationDTO(username, password, fullName, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }//end account list has been existed
                    this.accountList.add(dto);
                }//end traverse result set
            }//end if connection is available
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

    }

    public boolean deleteAccount(String username) throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        Boolean result = false;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Delete From Registration "
                        + "Where username = ?";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                //2.3 Excute Update -> Result Set
                int effectRow = stm.executeUpdate();
                //2.4 Process Result Set
                if (effectRow > 0) {
                    result = true;
                }

            }//end if connection is available
        } finally {

            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public boolean updateAccount(String username, String password, boolean isAdmin) throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "Where username=?";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                //2.3 Excute Query -> Result Set
                stm.setString(1, password);
                stm.setBoolean(2, isAdmin);
                stm.setString(3, username);
                int effectedRows = stm.executeUpdate();
                if (effectedRows > 0) {
                    result = true;
                }
            
                //2.4 Process Result Set
             
            }//end if connection is available
        } finally {
          
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
    
    public boolean createAccount(RegistrationDTO dto) throws SQLException, NamingException, ClassNotFoundException {
        //không nên truyền quá nhiều tham số, chỉ truyền 1 cái chung là dto
        Connection con = null;
        PreparedStatement stm = null;
        Boolean result = false;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Insert Into Registration ( "
                        + "username, password, lastname, isAdmin "
                        + ") "
                        + "Values "
                        + "(?, ?, ?, ?)";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                //2.3 Excute Update -> Result Set
                int effectRow = stm.executeUpdate();
                //2.4 Process Result Set
                if (effectRow > 0) {
                    result = true;
                }

            }//end if connection is available
        } catch (Exception e) {

        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

}
//Log: Nullable
//Recovery Pending in SQL Server
//Fix: 