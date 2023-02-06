<%-- 
    Document   : OrderDetail
    Created on : Oct 11, 2022, 5:31:43 PM
    Author     : Nghia Nguyen
--%>

<%@page import="com.sun.javafx.image.impl.IntArgb"%>
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
            <font color="red">you must login to veiw this page</font>
        </p>
        <%
        } else {
        %>
        <c:import url="header_loginedAdmin.jsp"></c:import>
            <a href="MainController?action=filter order">View All Orders</a>
            <section>
            <%
                ArrayList<OrderDetail> list = (ArrayList<OrderDetail>) request.getAttribute("orderList");
                String msg = (String) request.getAttribute("msg");
                if (msg.isEmpty() && list != null) {
                    int money = 0;
                    for (OrderDetail detail : list) {
                        money += (detail.getPrice() * detail.getQuantity());
            %>
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
                    <td><%= detail.getOrderID()%></td>
                    <td><%= detail.getPlantID()%></td>
                    <td><%= detail.getPlantName()%></td>
                    <td><img src='<%= detail.getImgPath()%>' class="plantimg"> </td>
                    <td><%= detail.getPrice()%></td>
                    <td><%= detail.getQuantity()%></td>
                </tr>
            </table>
            <%                }
            %>
            <h3> Total money: <%= money%> </h3> 
            <c:if test="${param.status == 1}">
                <a href="MainController?action=acceptOrder&orderId=<%= list.get(0).getOrderID() %>">Accept order</a>
                <br />
                <a href="MainController?action=cancelOrder&orderId=<%= list.get(0).getOrderID() %>">Cancel order</a>
            </c:if>
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%            }
            }
        %>
    </body>
</html>
