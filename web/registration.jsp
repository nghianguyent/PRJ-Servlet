<%-- 
    Document   : registration
    Created on : Sep 30, 2022, 11:32:04 AM
    Author     : Nghia Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css">
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>  
        <section>
            <form action="MainController" method="POST" class="form">
                <h1>
                    Register
                </h1>
                <table>
                    <tr>
                        <td>Email:</td>
                        <td>
                            <input type="text" name="email" required="" value="${requestScope.email == null ? "" : requestScope.email}">
                        </td>
                    </tr>
                    <tr>
                        <td>Full name:</td>
                        <td>
                            <input type="text" name="fullname" required="" value="${requestScope.fullname == null ? "" : requestScope.fullname}">
                        </td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td>
                            <input type="password" name="password" required="">
                        </td>
                    </tr>
                    <tr>
                        <td>Phone</td>
                        <td>
                            <input type="text" name="phone" required="" value="${requestScope.phone == null ? "" : requestScope.phone}">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="color: red;">
                            ${requestScope.error == null ? "" : requestScope.error}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="register" name="action">
                        </td>
                    </tr>
                </table>
            </form>
        </section>
        <footer>    
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
