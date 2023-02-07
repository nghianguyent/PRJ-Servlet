/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.orderdetail;

import binhnd.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author duybi
 */
public class OrderDetailDAO implements Serializable {

    public boolean checkoutItems(OrderDetailDTO dto) throws SQLException, NamingException, ClassNotFoundException {

        Connection con = null;
        PreparedStatement stm = null;
        Boolean result = false;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Insert Into OrderDetail ("
                        + "sku, order_id, quantity, price, total"
                        + ") "
                        + "Values "
                        + "(?, ?, ?, ?, ?)";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getSku());
                stm.setInt(2, dto.getOrderId());
                stm.setInt(3, dto.getQuantity());
                stm.setFloat(4, dto.getPrice());
                stm.setFloat(5, dto.getTotal());

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

    public int getQuantitySoldProduct(String sku) throws SQLException, NamingException, ClassNotFoundException {
       
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "select sku, SUM(quantity) as quantity "
                        + "from OrderDetail "
                        + "where sku = ? "
                        + "group by sku";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setString(1, sku);
                //2.3 Excute Update -> Result Set
                rs = stm.executeQuery();
                if (rs.next()) {
                    quantity = rs.getInt("quantity");
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
        return quantity;
    }
}
