<%-- 
    Document   : OrderDetail
    Created on : Oct 11, 2022, 5:31:43 PM
    Author     : Nghia Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="sampe.dao.OrderDAO"%>
<%@page import="sample.dto.OrderDetail"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String email = (String) session.getAttribute("email");
            String name = (String) session.getAttribute("name");
            if (name == null) {
        %>
        <p>
            <font color="red">you must login to veiw personal page</font>
        </p>
        <%
        } else {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <h2>Welcome back <%= name%></h2>
        <h3>
            <font color="green">
            <a href="MainController?action=logout">Logout</a>
            </font>
        </h3>
        <a href="MainController?action=viewOrder">View All Orders</a>
        <section>
            <c:set var="list" value="${requestScope.orderList}" ></c:set>
            <c:set var="status" value="${requestScope.status}" ></c:set>
            <c:set var="msg" value="${requestScope.msg}"></c:set>
            <c:choose>
                <c:when test="${msg.isEmpty() && list != null}">
                    <c:set var="money" value="0" />
                    <c:forEach var="detail" items="${list}">
                        <c:set var="money" value="${money + detail.getPrice() * detail.getQuantity()}" />
                        <table class="order">
                            <tr>
                                <td>Order ID</td>
                                <td>Plant ID</td>
                                <td>Plant Name</td>
                                <td>Image</td>
                                <td>Price</td>
                                <td>Quantity</td>
                            </tr>
                            <tr>
                                <td>${detail.getOrderID()}</td>
                                <td>${detail.getPlantID()}</td>
                                <td>${ detail.getPlantName()}</td>
                                <td><img src='${ detail.getImgPath()}' class="plantimg"> </td>
                                <td>${ detail.getPrice() * detail.getQuantity()}</td>
                                <td>${ detail.getQuantity()}</td>
                            </tr>
                        </table>
                    </c:forEach>
                    <h3> Total money: ${ money }</h3> 
                    <%
                        String action = "orderAgain";
                        if (Integer.parseInt(request.getParameter("status")) == 1) {
                            action = "cancelOrder";
                        }
                    %>
                    <a href="MainController?action=<%= action%>&orderId=${list.get(0).getOrderID()}"><%= action.matches("orderAgain") ? "Order Again" : "Cancel Order"%></a>
                
                </c:when>
                <c:otherwise>
                    ${msg}
                </c:otherwise>
            </c:choose>


        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%            }
        %>
    </body>
</html>
