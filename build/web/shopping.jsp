<%-- 
    Document   : shopping
    Created on : Oct 29, 2022, 7:43:33 PM
    Author     : duybi
--%>

<%@page import="binhnd.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping</title>
    </head>
    <body>
        <h1>Book Store</h1>

        <form action="DispatchController">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>SKU</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Add to Cart</th>
                    </tr>
                </thead>
                <c:set var="result" value="${sessionScope.PRODUCTS}"/>

                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                    <form action="DispatchController" method="POST">

                        <td>${counter.count}</td>
                        <td>
                            ${dto.sku}
                            <input type="hidden" name="txtSKU" value="${dto.sku}" />
                        </td>

                        <td>${dto.name}</td>
                        <td>${dto.price}</td>
                        <td>${dto.description}</td>
                        <td>${dto.quantity}</td>
                        <td>
                            <input type="submit" name="btAction" value="Add Book to Your Cart"/>
                        </td>
                    </form>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <input type="submit" value="View Your Cart" name="btAction" />

        </form>
    </body>
</html>
