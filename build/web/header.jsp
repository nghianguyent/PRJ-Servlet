<%-- 
    Document   : header
    Created on : Sep 30, 2022, 11:22:22 AM
    Author     : Nghia Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" >
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp"><img src="images/logo.jpg" id="logo"></a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="registration.jsp">Register</a></li>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="MainController?action=viewCart">View Cart</a></li>
                <li>
                    <form action="MainController" method="post" class="formsearch">
                        <input type="text" name="txtsearch" value="${param.txtsearch}">
                        <select name="searchby">
                            <option value="byname">By name</option>
                            <option value="bycate">By categories</option>
                        </select>
                        <input type="submit" value="search" name="action">
                    </form>
                </li>
            </ul>
        </nav>
    </body>
</html>
