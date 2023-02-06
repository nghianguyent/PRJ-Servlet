<%-- 
    Document   : header_loginedUser
    Created on : Sep 30, 2022, 11:58:19 AM
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
        <header>
            <ul>
                <li><a href=""><img src="images/logo.jpg" id="logo"></a></li>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="profile.jsp">Change profile</a></li>
                <li><a href="MainController?action=viewOrder&status=2">Completed orders</a></li>
                <li><a href="MainController?action=viewOrder&status=3">Canceled orders</a></li>
                <li><a href="MainController?action=viewOrder&status=1">Processing orders</a></li>
                <li>
                    <form action="MainController" method="post">
                        From
                        <input type="date" value="${requestScope.from}" name="from">
                        To
                        <input type="date" value="${requestScope.to}%>" name="to">
                        <input type="submit" value="filter order" name="action">
                    </form>
                </li>
            </ul>
        </header>
    </body>
</html>
