<%-- 
    Document   : order
    Created on : Nov 2, 2022, 6:29:51 PM
    Author     : duybi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order</title>
    </head>
    <body>
        <h1>You ordered</h1>
        <c:set var="order" value="${sessionScope.ORDER}">
            
        </c:set>
        <p>
            Order Id: ${order.id}
        </p>
         <p>
            Date Buy: ${order.dateBuy}
        </p>
         <p>
            Total: ${order.total}
        </p>
        <a href="DispatchController?btAction=Shopping">Go shopping</a>
        <a href="userCart.jsp">View cart</a>
    </body>
</html>
