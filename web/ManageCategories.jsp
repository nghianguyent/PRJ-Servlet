<%-- 
    Document   : ManageCategories
    Created on : Oct 26, 2022, 7:23:42 PM
    Author     : Nghia Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp" ></c:import>
            <table class="order">
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                </tr>
            <c:forEach var="category" items="${requestScope.categoryList}">
                <tr>
                    <td>
                        <c:out value="${category.getId()}" />
                    </td>
                    <td>
                        <c:out value="${category.getName()}" />
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
