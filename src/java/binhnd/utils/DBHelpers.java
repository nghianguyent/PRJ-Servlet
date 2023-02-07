/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

/**
 *
 * @author tklin
 */
public class DBHelpers implements Serializable{

    public static Connection makeConnection()
            throws /*ClassNotFoundException*/ NamingException, SQLException, ClassNotFoundException {
//        //1. Find Server Context -> JNDI (Java name directory interface)
//        Context serverContext = new InitialContext();
//        //2. Find Container Context
//        Context tomcatContext = (Context) serverContext.lookup("java:comp/env");
//        //3. Get DS
//        DataSource ds = (DataSource) tomcatContext.lookup("DS007");
//        //4. Open Connection
//        Connection con = ds.getConnection();
//
//        return con;

        //1. Load driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2. Create connect string url
        String url = "jdbc:sqlserver://localhost:1433;databaseName=MVCSE1603;instanceName=SQLEXPRESS";
        //3. Connect
        Connection con = DriverManager.getConnection(url, "sa", "12345");
        return con;
    }
    public static Properties getSiteMaps(String siteMapFile, ServletContext context) throws IOException{
        if(siteMapFile == null){
            return null;
        }
        if(siteMapFile.trim().isEmpty()){     
            return null;
        }
        Properties result = new Properties();
        InputStream is = null;
        try {
            is = context.getResourceAsStream(siteMapFile);
            result.load(is);
            return result;
        } finally {
            if(is!=null){
                is.close();
            }
        }
        
        
    }
}
