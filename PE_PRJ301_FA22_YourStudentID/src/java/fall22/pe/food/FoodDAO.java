/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates/. Created on : 30-10-2022
 * and open the template in the editor.
 */
package fall22.pe.food;

import fall22.pe.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hd
 */
public class FoodDAO {

//   your code here
    public static ArrayList<FoodDTO> findByName(String name) {
        ArrayList<FoodDTO> result = new ArrayList<>();
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "select * from tblFoods where name like ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + name + "%");
                ResultSet rs = stm.executeQuery();

                while (rs.next()) {
                    result.add(new FoodDTO(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getFloat("price"), rs.getInt("cookingTime"), rs.getInt("status")));
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
        return result;
    }
    
    public static boolean deleteFood(String id) {
        Connection cn = null;
        try {
            cn = DBUtils.getConnection();
            if (cn != null) {
                String sql = "UPDATE tblFoods SET status = 0 WHERE id = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, id);
                return stm.executeUpdate() > 0;
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
        return false;
    }
}
