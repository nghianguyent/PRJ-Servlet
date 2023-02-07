<%-- 
    Document   : confirm
    Created on : Nov 2, 2022, 2:50:37 PM
    Author     : duybi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirm to Checkout</title>
    </head>
    <body>
        <h1>Your order</h1>
        <form action="DispatchController">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <c:set var="order" value="${sessionScope.ORDERS}"/>
                
                <tbody>
                    <c:forEach var="dto" items="${order}" varStatus="counter">
                        <tr>

                        <td>${counter.count}</td>
                        <td>
                            ${dto.sku}
                        </td>
                        <td>${dto.price}</td>
                        <td>${dto.quantity}</td>
                        <td>${dto.total}</td>
                       
                    </tr>
                </c:forEach>
                </tbody>
            </table>
                <h2>
                    Total: ${sessionScope.TOTAL_ORDER}
                </h2>
            <input type="submit" value="Checkout" name="btAction" />

        </form>
    </body>
</html>
