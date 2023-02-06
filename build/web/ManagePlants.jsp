<%-- 
    Document   : ManagePlants
    Created on : Oct 25, 2022, 10:48:12 PM
    Author     : Nghia Nguyen
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" >
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp" />
        <table class="producttable">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Price</th>
                <th>Image</th>
                <th>Description</th>
                <th>Status</th>
                <th>Categories</th>
                <th>Action</th>
            </tr>
            <c:forEach var="plant" items="${requestScope.plantList}">
                <tr>
                    <td><h3><c:out value="${plant.getId()}"></c:out></h3></td>
                    <td><c:out value="${plant.getName()}" ></c:out></td>
                    <td><c:out value="${plant.getPrice()}" ></c:out></td>
                        <td>
                            <img src="${plant.getImgpath()}" width="100" height="100">
                    </td>
                    <td><c:out value="${plant.getDescription()}"></c:out></td>
                        <td>
                        <c:choose>
                            <c:when test="${plant.getStatus() eq 1}">
                                Available
                            </c:when>
                            <c:otherwise>Unavailable</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${plant.getCatename()}" ></c:out></td>
                        <td>
                        <c:url var="mylink" value="MainController">
                            <c:param name="id" value="${plant.getId()}"></c:param>
                            <c:param name="status" value="${plant.getStatus()}"></c:param>
                            <c:param name="action" value="updateStatusPlant"></c:param>
                        </c:url>
                        <a href="${mylink}">Instock/Outstock</a>
                        </td>
                    </tr>
            </c:forEach>
        </table>
    </body>
</html>
