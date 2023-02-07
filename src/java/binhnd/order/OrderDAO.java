/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.order;

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
public class OrderDAO implements Serializable{
    public int checkoutItems(OrderDTO dto) throws SQLException, NamingException, ClassNotFoundException {
       
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int orderId = -1;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Insert Into [Order] ("
                        + "dateBuy, total"
                        + ") "
                        + "output inserted.id, inserted.dateBuy, inserted.total "
                        + "Values "
                        + "(?, ?)";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                stm.setDate(1, dto.getDateBuy());
                stm.setFloat(2, dto.getTotal());
                //2.3 Excute Update -> Result Set
                rs = stm.executeQuery();
                if (rs.next()) {
                    orderId = rs.getInt("id");
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
        return orderId;
    }
}
