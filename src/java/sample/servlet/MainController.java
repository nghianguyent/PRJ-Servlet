/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nghia Nguyen
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            String url = "index.jsp";
            if (action != null) {
                switch (action) {
                    case "login":
                        url = "LoginServlet";
                        break;
                    case "register":
                        url = "RegisterServlet";
                        break;
                    case "OrderDetail":
                        url = "OrderDetailServlet";
                        break;
                    case "logout":
                        url = "LogoutServlet";
                        break;
                    case "search":
                        url = "SearchServlet";
                        break;
                    case "cancelOrder":
                        url = "CancelOrderServlet";
                        break;
                    case "viewOrder":
                        url = "FilterOrderServlet";
                        break;
                    case "orderAgain":
                        url = "OrderAgainServlet";
                        break;
                    case "updateProfile":
                        url = "UpdateProfileServlet";
                        break;
                    case "addtocart":
                        url = "AddToCartServlet";
                        break;
                    case "viewCart":
                        url = "viewCart.jsp";
                        break;
                    case "deleteCart":
                        url = "DeleteCartServlet";
                        break;
                    case "updateCart":
                        url = "UpdateCartServlet";
                        break;
                    case "saveOrder":
                        url = "SaveShoppingCartServlet";
                        break;
                    case "productDetail":
                        url = "ProductDetailServelet";
                        break;
                    case "filter order":
                        url = "FilterOrderServlet";
                        break;
                    case "manageAccount":
                        url = "ManageAccountServlet";
                        break;
                    case "updateStatusAccount":
                        url = "UpdateStatusAccountServlet";
                        break;
                    case "searchAccount":
                        url = "SearchAccountServlet";
                        break;
                    case "managePlant":
                        url = "ManagePlantServlet";
                        break;
                    case "updateStatusPlant":
                        url = "UpdateStatusPlant";
                        break;
                    case "manageCategories":
                        url = "MangeCategoriesServlet";
                        break;
                    case "manageOrders":
                        url = "ManageOrdersServlet";
                        break;
                    case "acceptOrder":
                        url = "AcceptOrderServlet";
                        break;
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
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
