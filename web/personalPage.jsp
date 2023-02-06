<%-- 
    Document   : personalPage.
    Created on : Sep 30, 2022, 11:58:35 AM
    Author     : Nghia Nguyen
--%>

<%@page import="sampe.dao.AccountDAO"%>
<%@page import="sample.dto.Account"%>
<%@page import="sample.dto.Order"%>
<%@page import="sampe.dao.OrderDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            Cookie[] c = request.getCookies();
            boolean login = false;
            if (name == null) {
                String token = "";
                for (Cookie cookie : c) {
                    if (cookie.getName().equals("selector")) {
                        token = cookie.getValue();
                        Account acc = AccountDAO.getAccount(token);
                        if (acc != null) {
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;
                        }
                    }
                }
            } else {
                login = true;
            }
            if (login) {
        %>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>
        <h2>Welcome back ${name}</h2>
        <h3>
            <font color="green">
            <a href="MainController?action=logout">Logout</a>
            </font>
        </h3>
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
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }
        %>
    </body>
</html>
