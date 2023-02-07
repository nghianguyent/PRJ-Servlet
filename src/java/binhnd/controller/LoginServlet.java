/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import binhnd.registration.RegistrationDAO;
import binhnd.registration.RegistrationDTO;
import binhnd.utils.MyApplicationConstants;

/**
 *
 * @author tklin
 */
public class LoginServlet extends HttpServlet {

//    private final String SEARCH_PAGE = "searchPage";
//    private final String INVALID_PAGE = "invalidPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");

//        String url = INVALID_PAGE;
//        String url = siteMaps.getProperty(MyApplicationConstants.LoginController.INVALID_PAGE);
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        try {

            //1. gọi model/dao
            //1.1 new object
            RegistrationDAO dao = new RegistrationDAO();
            //1.2 call method of obj
            RegistrationDTO result = dao.checkLogin(username, password);
            if (result != null) {
//                url = siteMaps.getProperty(MyApplicationConstants.LoginController.SEARCH_PAGE);;
                Cookie cookie = new Cookie(username, password);
                cookie.setMaxAge(60 * 3);
                response.addCookie(cookie);
                HttpSession session = request.getSession(); //cần tạo mới để add thì getsession true
                session.setAttribute("USER", result);
                System.out.println(session.getAttribute("USER"));
            }

        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("Log in SQL " + errMsg);
        } catch (NamingException ex) {
            ex.printStackTrace();
            log("Log in Naming " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
//            RequestDispatcher rs = request.getRequestDispatcher(url);
//            rs.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
