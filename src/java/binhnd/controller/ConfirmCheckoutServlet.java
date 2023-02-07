/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.controller;

import binhnd.cart.CartObject;
import binhnd.orderdetail.OrderDetailDTO;
import binhnd.product.ProductList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@WebServlet(name = "ConfirmCheckoutServlet", urlPatterns = {"/ConfirmCheckoutServlet"})
public class ConfirmCheckoutServlet extends HttpServlet {
    private final String SHOPPING_PAGE = "shopping.jsp";
    private final String CONFIRM_PAGE = "confirm.jsp";
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
         String[] items = request.getParameterValues("chkCheckoutItem");
             HttpSession session = request.getSession(); //luôn luôn p có để ng ta lấy
            //2. Cus takes his/her cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            List<OrderDetailDTO> orderDetailDTOs = new ArrayList<OrderDetailDTO>();
            ProductList productList = (ProductList) session.getAttribute("PRODUCTS");
            for (String item: items) {
                String sku = item;
                int quantity = cart.getItems().get(item);
                float price =  productList.getProduct(item).getPrice();
                float total = price * quantity;
                orderDetailDTOs.add(new OrderDetailDTO(sku, quantity, price, total));
                
            }
            if (!orderDetailDTOs.isEmpty()) {
                url = CONFIRM_PAGE;
                session.setAttribute("ORDERS", orderDetailDTOs);
                float totalOrder = 0;
                for (OrderDetailDTO dto: orderDetailDTOs) {
                    totalOrder += dto.getTotal();
                }
                session.setAttribute("TOTAL_ORDER", totalOrder);
            }
           
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
