<%-- 
    Document   : ManageOrders
    Created on : Nov 1, 2022, 1:10:42 PM
    Author     : Nghia Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp" ></c:import>
            <section>
            <c:set var="status" value="${ param.status == null ? 0 : Integer.parseInt(param.status)}" />
            <c:if test="${list!= null && !list.isEmpty()}" >
                <c:forEach var="ord" items="${requestScope.list}">
                    <c:if test="${status == ord.status || status == 0}" >
                        <table class="order">
                            <tr>
                                <td>Order ID</td>
                                <td>Order Date</td>
                                <td>Ship Date</td>
                                <td>Order's status</td>
                                <td>Account Id </td>
                                <td>action</td>
                            </tr>
                            <tr>
                                <td>
                                    ${ord.orderID}
                                </td>
                                <td>
                                    ${ord.orderDate}
                                </td>
                                <td>
                                    ${ord.shipDate ==  null ? "null" : ord.shipDate }
                                </td>
                                <td>
                                    ${ord.status}
                                </td>
                                <td>
                                    ${ord.accID}
                                </td>
                                <td>
                                    ${statusArr[ord.status]}
                                    <br/> 
                                    <a href="MainController?action=OrderDetail&status=${ord.status}&orderid=${ord.orderID}">Detail</a> 
                                </td>
                            </tr>
                        </table>
                    </c:if>
                </c:forEach>
            </c:if>
        </section>
    </body>
</html>
