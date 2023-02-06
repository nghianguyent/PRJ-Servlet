/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import sampe.dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sample.utils.Regex;

/**
 *
 * @author Nghia Nguyen
 */
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected HttpServletRequest reuseField(HttpServletRequest request, String email, String phone, String fullname) {
        request.setAttribute("email", email);
        request.setAttribute("fullname", fullname);
        request.setAttribute("phone", phone);
        return request;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String phone = request.getParameter("phone");
            // check email 
            if (!email.matches(Regex.REGEX_EMAIL)) {
                reuseField(request, email, phone, fullname);
                request.setAttribute("error", "The email is invalid");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            } // check name
            // check phone
            if (phone.matches(Regex.REGEX_PHONE)) {
                reuseField(request, email, phone, fullname);
                request.setAttribute("error", "The phone is invalid");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            if (AccountDAO.getAccountByEmail(email) != null) {
                reuseField(request, "", phone, fullname);
                request.setAttribute("error", "The email is already used");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }
            int status = 1;
            int role = 0;
            if (AccountDAO.insertAccount(email, password, fullname, phone, status, role)) {
                request.setAttribute("email_newAccount", email);
                request.getRequestDispatcher("sendOTP").forward(request, response);
            } else {
                response.sendError(500, "something when wrong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(500, "something when wrong");

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
