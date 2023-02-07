/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.controller;

import binhnd.cart.CartObject;
import binhnd.orderdetail.OrderDetailDAO;
import binhnd.product.ProductDAO;
import binhnd.product.ProductDTO;
import binhnd.product.ProductList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author duybi
 */
@WebServlet(name = "ViewShopServlet", urlPatterns = {"/ViewShopServlet"})
public class ViewShopServlet extends HttpServlet {

    private final String LOGIN_PAGE = "login.html";
    private final String SHOPPING_PAGE = "shopping.jsp";

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
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            ProductDAO dao = new ProductDAO();
            HttpSession session = request.getSession();
            ProductList results = dao.getProductsList();
            if (results != null) {
                url = SHOPPING_PAGE;
                
                CartObject cart = (CartObject) session.getAttribute("CART");
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                for (ProductDTO product: results) {
                     results.updateRemainProduct(product.getSku(), orderDetailDAO.getQuantitySoldProduct(product.getSku()));
                }
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    items.forEach((key, value) -> {
                        results.updateRemainProduct(key, value);

                    });
                }

                session.setAttribute("PRODUCTS", results);
            }
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("View Shop SQL " + errMsg);
        } catch (NamingException ex) {
            log("View Shop Naming " + ex.getMessage());
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
