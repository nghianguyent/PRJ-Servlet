<%-- 
    Document   : login
    Created on : 30-10-2022
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>Login information</h1>
        <font style="color: red;"> ${requestScope.msg == null ? '' : requestScope.msg}</font>
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>UserID</td>
                    <td><input type="text" name="userID"> </td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"> </td>
                </tr>
                <tr>
                    <td><input type="submit" value="login" name="action"> </td>
                </tr>
            </table>
        </form>
    </body>
    
</html>

