/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import sampe.dao.AccountDAO;
import sample.dto.Account;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nghia Nguyen
 */
public class LoginServlet extends HttpServlet {

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String save = request.getParameter("saveLogin");
        Account acc = null;
        try {
            if (email == null || email.equals("") || password == null || password.equals("")) {
                Cookie[] c = request.getCookies();
                String token = "";
                if (c != null) {
                    for (Cookie cookie : c) {
                        if (cookie.getName().equals("selector")) {
                            token = cookie.getValue();
                        }
                    }
                }
                if (!token.equals("")) {
                    request.getRequestDispatcher("FilterOrderServlet").forward(request, response);
                } else {
                    response.sendRedirect("errorpage.html");
                }
            } else {
                acc = AccountDAO.getAccount(email, password);
                if (acc != null) {
                    if (acc.getRole() == 1) {
                        // chuyen qua admin home page
                        HttpSession session = request.getSession(true);
                        session.setAttribute("name", acc.getFullname());
                        session.setAttribute("email", email);
                        session.setAttribute("acc", acc);
                        if (save != null) {
                            String token = "ABC123";
                            Cookie cookie = new Cookie("selector", token);
                            cookie.setMaxAge(60 * 2);
                            response.addCookie(cookie);
                        }
                        response.sendRedirect("adminIndex.jsp");
                    } else {
                        HttpSession session = request.getSession();
                        if (session != null) {
                            session.setAttribute("name", acc.getFullname());
                            session.setAttribute("email", email);
                            session.setAttribute("acc", acc);
                            // create a cookie and attach it to response object
                            if (save != null) {
                                String token = "ABC123";
                                AccountDAO.updateToken(token, email);
                                Cookie cookie = new Cookie("selector", token);
                                response.addCookie(cookie);
                            }
                            request.getRequestDispatcher("FilterOrderServlet").forward(request, response);
                        }
                    }
                } else {
                    response.sendRedirect("errorpage.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
