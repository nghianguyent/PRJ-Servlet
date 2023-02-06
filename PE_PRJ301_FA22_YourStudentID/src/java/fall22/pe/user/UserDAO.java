/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fall22.pe.user;

import fall22.pe.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author Hoadnt
 */
public class UserDAO {
//    your code here
    public static UserDTO getUser(String userID, String password) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select * from tblUsers where userID = ? and password = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, userID);
                stm.setString(2, password);
                ResultSet rs = stm.executeQuery();
                
                while (rs.next()) {
                    return new UserDTO(rs.getString("userID"), rs.getString("fullName"), rs.getString("roleID"), password);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cn != null) {
                try {
                    cn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }            
        }        
        return null;
    }   
    
}
