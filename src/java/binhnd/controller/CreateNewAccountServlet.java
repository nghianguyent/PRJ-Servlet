/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhnd.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import binhnd.registration.RegistrationCreateError;
import binhnd.registration.RegistrationDAO;
import binhnd.registration.RegistrationDTO;

/**
 *
 * @author tklin
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createNewAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        request.setCharacterEncoding("UTF-8");
        
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullname = request.getParameter("txtFullname");
        boolean errorFound = false;
        RegistrationCreateError errors = new RegistrationCreateError();
        String url = ERROR_PAGE;
        try {
            //1. Check all user errors
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                errorFound = true;
                errors.setUsernameLengthError("Username is  required input from 6 to 20 characters");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                errorFound = true;
                errors.setPasswordLengthError("Password is  required input from 6 to 30 characters");
            } else if (!confirm.trim().equals(password.trim())) {
                errorFound = true;
                errors.setConfirmNotMatched("Confirm must be matched password");
            }
            if (fullname.trim().length() < 2 || fullname.trim().length() > 50) {
                errorFound = true;
                errors.setFullNameLengthError("Full name is  required input from 2 to 50 characters");
            }
            if (errorFound) {
                //1.2 Cache store
                request.setAttribute("CREATE_ERRORS", errors);
                //1.3 show errors
            } else {
                //2. Insert to DB
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, errorFound);
                boolean result = dao.createAccount(dto);
                //3. Check all system errors
                if (result) {
                    url = LOGIN_PAGE;
                }
            }

            //4. Transfer specified page
        } catch (SQLException ex) {
            String errMsg = ex.getMessage();
            log("Create New Account SQL " + errMsg);
            if (errMsg.contains("duplicate")) {
                errors.setUsernameIsExisted(username + " is existed");
                request.setAttribute("CREATE_ERRORS", errors);
            }
        } catch (NamingException ex) {
            log("Create New Account Naming " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
//tìm ra được keyword của error message ở log để bắt lỗi 
