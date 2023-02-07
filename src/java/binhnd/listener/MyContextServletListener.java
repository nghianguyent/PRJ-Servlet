/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.listener;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import binhnd.utils.DBHelpers;

/**
 * Web application lifecycle listener.
 *
 * @author tklin
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Deploying......................");
        ServletContext context = sce.getServletContext();
        String siteMapsFile = context.getInitParameter("SITE_MAP_FILE_PATH");
        try {
            Properties siteMaps = DBHelpers.getSiteMaps(siteMapsFile, context);
            if (siteMaps != null) {
                context.setAttribute("SITE_MAPS", siteMaps);
            }
        } catch (IOException e) {
            context.log("My Context Listener _ IO" + e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Bye Bye RIP");
    }
}
