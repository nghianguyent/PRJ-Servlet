<%-- 
    Document   : ManageAccounts
    Created on : Oct 25, 2022, 10:18:52 PM
    Author     : Nghia Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" />
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp" />
        <form action="MainController" method="post">
            <input type="text" name="search" value="${requestScope.search == null ? "" : requestScope.search}"/>
            <input type="submit" value="searchAccount" name="action" />
        </form>
        <h1></h1>
        <table class="order">
            <tr>
                <th>Id</th>
                <th>Email</th>
                <th>Full name</th>
                <th>Status</th>
                <th>Phone</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            <c:forEach var="acc" items="${requestScope.accountList}">
                <tr>
                    <td><c:out value="${acc.getAcacid()}"></c:out></td>
                    <td><c:out value="${acc.getEmail()}" ></c:out></td>
                    <td><c:out value="${acc.getFullname()}" ></c:out></td>
                        <td>
                        <c:choose>
                            <c:when test="${acc.getStatus() eq 1}">
                                Active
                            </c:when>
                            <c:otherwise>inActive</c:otherwise>
                        </c:choose>
                    </td>
                    <td><c:out value="${acc.getPhone()}"></c:out></td>
                        <td>
                        <c:choose>
                            <c:when test="${acc.getRole() eq 1}">
                                Admin
                            </c:when>
                            <c:otherwise>User</c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${acc.getRole() eq 0}">
                            <c:url var="mylink" value="MainController">
                                <c:param name="email" value="${acc.getEmail()}"></c:param>
                                <c:param name="status" value="${acc.getStatus()}"></c:param>
                                <c:param name="action" value="updateStatusAccount"></c:param>
                            </c:url>
                            <a href="${mylink}">Block/Unblock</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
