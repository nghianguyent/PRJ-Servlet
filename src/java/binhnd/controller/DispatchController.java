/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.controller;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import binhnd.utils.MyApplicationConstants;

/**
 *
 * @author tklin
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {

//    private final String LOGIN_PAGE = "";
//    private final String LOGIN_CONTROLLER = "LoginServlet"; //url-pattern in web.xml
//        private final String LOGIN_CONTROLLER = "loginController"; //url-pattern in web.xml
//    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastnameServlet";
//    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
//    private final String FIRST_REQUEST_CONTROLLER = "FirstRequestServlet";
//    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
//    private final String VIEW_CART_PAGE = "userCart.jsp";
//    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
//    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAPS");
        String button = request.getParameter("btAction");
        String url = siteMaps.getProperty(MyApplicationConstants.DispatchController.LOGIN_PAGE);
        try {
            //User click button did user click?
            if (button == null) {
                //do nothing -> transfer to Login
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.FIRST_REQUEST_CONTROLLER);
            } else if (button.equals("Login")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.LOGIN_CONTROLLER);
            } else if (button.equals("Search")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.SEARCH_LASTNAME_CONTROLLER);
            } else if (button.equals("Delete")) {   
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.DELETE_ACCOUNT_CONTROLLER);
            } 
            else if (button.equals("Update")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.UPDATE_ACCOUNT_CONTROLLER);
            }
            else if (button.equals("Add Book to Your Cart")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.ADD_ITEM_TO_CART_CONTROLLER);
            } else if (button.equals("View Your Cart")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.VIEW_CART_PAGE);
            } else if(button.equals("Remove Selected Items")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.REMOVE_ITEM_FROM_CART_CONTROLLER);
            } else if(button.equals("Create New Account")){
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.CREATE_NEW_ACCOUNT_CONTROLLER);
            }
            else if (button.equals("Checkout Selected Items")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.CONFIRM_CHECK_OUT_CONTROLLER);
            }
            else if (button.equals("Checkout")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.CHECK_OUT_CONTROLLER);
            }
            else if (button.equals("Shopping")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.VIEW_SHOP_CONTROLLER);
            }
            else if (button.equals("Log out")) {
                url = siteMaps.getProperty(MyApplicationConstants.DispatchController.LOG_OUT_CONTROLLER);
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
//1. Tao view va view do chuyen toi dieu phoi
//2. Map chuc nang do vao ben trong dieu phoi va tro den servlet chuc nang
//3. Tao servlet chuc nang
//4. Tao DAO
//5. Ket noi database
