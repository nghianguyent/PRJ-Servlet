/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nghia Nguyen
 */
public class mylib {

    public static Connection makeConnection() throws Exception {
        String IP = "localhost";
        String instanceName = "DESKTOP-4S7PJC8";
        String port = "1433";
        String uid = "sa";
        String pwd = "12345";
        String db = "PlantShop";
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url = "jdbc:sqlserver://" + IP + "\\" + instanceName + ":" + port
                + ";databasename=" + db + ";user=" + uid + ";password=" + pwd;
            Class.forName(driver);
        Connection cn = DriverManager.getConnection(url);
        return cn;
    }
}
