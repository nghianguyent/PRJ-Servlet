/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sampe.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import sample.dto.Account;
import sample.dto.Category;
import sample.utils.mylib;

/**
 *
 * @author Nghia Nguyen
 */
public class CategoryDAO {

    public static ArrayList<Category> getAll() {
        ArrayList<Category> result = new ArrayList<>();
        try {
            Connection cn = mylib.makeConnection();
            if (cn != null) {
                String sql = "select *\n"
                        + "from dbo.Categories";
                Statement stm = cn.createStatement();
                ResultSet table = stm.executeQuery(sql);

                while (table.next()) {
                    int id = table.getInt("CateID");
                    String name = table.getString("CateName");
                    result.add(new Category(id, name));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
