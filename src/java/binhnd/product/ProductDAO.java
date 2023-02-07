/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.product;

import binhnd.registration.RegistrationDTO;
import binhnd.utils.DBHelpers;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class ProductDAO implements Serializable{
    private ProductList productsList;

    public ProductList getProductsList() throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. connect DB
            con = DBHelpers.makeConnection();
            if (con != null) {
                //2. CRUD
                //2.1 Create SQL String
                String sql = "Select sku, name, description, quantity, price, status "
                        + "from product";
                //2.2 create Statement Object to set SQL
                stm = con.prepareStatement(sql);
                //2.3 Excute Query -> Result Set
                rs = stm.executeQuery();
                //2.4 Process Result Set
                while (rs.next()) {
                    String sku = rs.getString("sku");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    int quantity = rs.getInt("quantity");
                    float price = rs.getFloat("price");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(sku, name, description, quantity, price, status);
                    if (this.productsList == null) {
                        this.productsList = new ProductList();
                    }//end account list has been existed
                    this.productsList.add(dto);
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
        return productsList;
    }
    
}
