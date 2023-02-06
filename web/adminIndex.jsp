<%-- 
    Document   : adminIndex
    Created on : Oct 25, 2022, 9:43:34 PM
    Author     : Nghia Nguyen
--%>

<%@page import="sampe.dao.AccountDAO"%>
<%@page import="sample.dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" />
    </head>
    <body>
        <%
            String email = (String) session.getAttribute("email");
            String name = (String) session.getAttribute("name");
            Account acc = (Account) session.getAttribute("acc");

            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                acc = AccountDAO.getAccount(token);
                for (Cookie cookie : c) {
                    if (cookie.getName().equals("selector")) {
                        token = cookie.getValue();
                        if (acc != null && acc.getRole() == 1) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                if (acc != null && acc.getRole() == 1) {
                    login = true;
                }
            }
            if (login) {
        %>
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <section>
                <img src="images/background.jpg" width="100%" height="100%"/>
            </section>
        <%
            } else {
            response.sendRedirect("errorpage.html");
            }
        %>
    </body>
</html>
