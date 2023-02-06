<%-- 
    Document   : header_loginedAdmin
    Created on : Oct 25, 2022, 9:53:48 PM
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
        <header>
            <ul>
                <li><a href=""><img src="images/logo.jpg" id="logo"></a></li>
                <li><a href="MainController?action=manageAccount">Manage Accounts</a></li>
                <li><a href="MainController?action=manageCategories">Manage Categories</a></li>
                <li><a href="MainController?action=managePlant">Manage Plants</a></li>
                <li><a href="MainController?action=manageOrders">Manage Order</a></li>
                <li>Welcome ${sessionScope.name} | <a href="MainController?action=logout">logout</a></li>
            </ul>
        </header>
    </body>
</html>
