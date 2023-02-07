/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.controller;

import binhnd.cart.CartObject;
import binhnd.order.OrderDAO;
import binhnd.order.OrderDTO;
import binhnd.orderdetail.OrderDetailDAO;
import binhnd.orderdetail.OrderDetailDTO;
import binhnd.product.ProductList;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "CheckoutItemServlet", urlPatterns = {"/CheckoutItemServlet"})
public class CheckoutItemServlet extends HttpServlet {

    private final String SHOPPING_PAGE = "shopping.jsp";
    private final String ORDER_PAGE = "order.jsp";
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
        String url = SHOPPING_PAGE;

        try {
            HttpSession session = request.getSession(); //luôn luôn p có để ng ta lấy
            //2. Cus takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            List<OrderDetailDTO> orderItems = (List<OrderDetailDTO>) session.getAttribute("ORDERS");
            float totalOrder = (Float) session.getAttribute("TOTAL_ORDER");
            OrderDAO orderDAO = new OrderDAO();
            java.util.Date date = new java.util.Date();
            OrderDTO orderDTO = new OrderDTO(new Date(date.getTime()), totalOrder);
            int orderId = orderDAO.checkoutItems(orderDTO);
            
            if (orderId != -1) {
                orderDTO.setId(orderId);
                session.setAttribute("ORDER", orderDTO);
                OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                for (OrderDetailDTO orderDetailDTO : orderItems) {
                    orderDetailDTO.setOrderId(orderId);
                    cart.removeItemFromCart(orderDetailDTO.getSku());
                    orderDetailDAO.checkoutItems(orderDetailDTO);
                }
                
                url = ORDER_PAGE;
            }
            //orderDetailDAO.checkoutItems(new OrderDetailDTO())
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("Checkout SQL " + errMsg);
        } catch (NamingException ex) {
            log("Checkout Naming " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            response.sendRedirect(url);
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
