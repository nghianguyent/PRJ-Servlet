/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Plant;
import sample.utils.mylib;

/**
 *
 * @author Nghia Nguyen
 */
public class PlantDAO {

    public static ArrayList<Plant> getPlants(String keyword, String searchby) throws Exception {
        ArrayList<Plant> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select PID,  PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID = Categories.CateID\n";
                if (searchby.equalsIgnoreCase("byname")) {
                    sql += "where Plants.PName like ?";
                } else {
                    sql += "where Categories.CateName like ?";
                }
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int id = table.getInt("PID");
                    String name = table.getString("PName");
                    int price = table.getInt("price");
                    String imgPath = table.getString("imgPath");
                    String description = table.getString("description");
                    int status = table.getInt("Status");
                    int cateid = table.getInt("CateID");
                    String catename = table.getString("CateName");
                    result.add(new Plant(id, name, price, imgPath, description, status, cateid, catename));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Plant getPlant(String pid) throws Exception {
        ArrayList<Plant> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select PID, PName, price, imgPath, description, status, Plants.CateID as 'CateID', CateName\n"
                        + "from Plants join Categories on Plants.CateID = Categories.CateID where Plants.PID = ?\n";
             
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setString(1, pid);
                ResultSet table = stm.executeQuery();

                while (table.next()) {
                    int id = table.getInt("PID");
                    String name = table.getString("PName");
                    int price = table.getInt("price");
                    String imgPath = table.getString("imgPath");
                    String description = table.getString("description");
                    int status = table.getInt("Status");
                    int cateid = table.getInt("CateID");
                    String catename = table.getString("CateName");
                    result.add(new Plant(id, name, price, imgPath, description, status, cateid, catename));
                }
                if (!result.isEmpty()) {
                    return result.get(0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean udpateStatus(String id, int status) {
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "Update Plants set status = ? where PID = ?";
                PreparedStatement stm = cn.prepareStatement(sql);
                stm.setInt(2, Integer.parseInt(id));
                stm.setInt(1, status);
                return stm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
