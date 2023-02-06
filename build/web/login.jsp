<%-- 
    Document   : login
    Created on : Sep 30, 2022, 11:28:27 AM
    Author     : Nghia Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
            <form action="MainController" method="POST">
                <font style="color: red;">${requestScope.WARNING == null ? "" : requestScope.WARNING} </font>
                <table>
                    <tr>
                        <td>
                            Email:
                        </td>
                        <td>
                            <input type="text" name="email">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Password:
                        </td>
                        <td>
                            <input type="password" name="password">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="checkbox" value="saveLogin" name="saveLogin"> Stay loggin
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="login" name="action">
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
