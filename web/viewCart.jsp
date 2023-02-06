<%-- 
    Document   : viewCart.jsp
    Created on : Oct 17, 2022, 7:06:44 AM
    Author     : Nghia Nguyen
--%>

<%@page import="sampe.dao.PlantDAO"%>
<%@page import="sample.dto.Plant"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <c:set var="name" value="${sessionScope.name}" />
            <c:if test="${name != null}" >
                <h3>Welcome ${name}</h3>
                <h3><a href="MainController?action=logout">Logout</a></h3>
                <h3><a href="MainController?action=viewOrder">Personal page</a></h3>
            </c:if>

            <font style="color: red;"><%=request.getAttribute("WARNING") == null ? "" : request.getAttribute("WARNING")%> </font>
            <br />
            <table width="100%" class="producttable">

                <c:set var="totalMoney" value="0" />
                <c:set var="cart" value="${sessionScope.cart}" />
                <c:choose >
                    <c:when test="${cart != null && !cart.isEmpty()}" >
                        <tr>
                            <td>Product ID</td>
                            <td>Quantity</td>
                            <td>price</td>
                            <td>image</td>
                            <td>action</td>
                        </tr>
                        <c:forEach var="pid" items="${cart.keySet()}">
                            <c:set var="quantity" value="${cart.get(pid)}" />
                            <c:set var="plant" value ="${PlantDAO.getPlant(pid)}" />
                            <c:set var="totalMoney" value="${totalMoney + quantity * plant.getPrice()}" />
                            <form action="MainController" method ="post">
                                <tr>
                                    <td>
                                        <input type="hidden" value="${pid}" name="pid">
                                        <a href="MainController?action=productDetail&pid=${pid}">${pid}</a>
                                    </td>
                                    <td>
                                        <input type="number" value="${quantity}" name="quantity">

                                    </td>
                                    <td>
                                        <input type="number" value="${plant.getPrice() * quantity}" name="price" disabled>
                                    </td>
                                    <td>
                                        <img src="${plant.getImgpath()}" width="100" height="100" />
                                    </td>
                                    <td>
                                        <input type="submit" name="action" value="deleteCart">
                                        <input type="submit" name="action" value="updateCart">
                                    </td>
                                </tr>   
                            </form>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            your cart is empty
                        </tr>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td>
                        Total money: ${totalMoney}
                    </td>
                </tr>
                <tr>
                    <td>
                        Order Date: <%= (new Date()).toString()%>
                    </td>
                </tr>
                <tr>
                    <td>
                        Ship Date: N/A
                    </td>
                </tr>
            </table>
            <form action="MainController" method="POST">
                <input type="submit" value="saveOrder" name="action" class="submitorder" />
            </form>
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
