<%-- 
    Document   : index
    Created on : Sep 30, 2022, 11:25:48 AM
    Author     : Nghia Nguyen
--%>

<%@page import="sample.dto.Plant"%>
<%@page import="java.util.ArrayList"%>
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
            <c:set var="list" value="${requestScope.list}" />
            <c:set var="msg" value="${requestScope.msg}"/>
            <c:choose >
                <c:when test="${list!= null && !list.isEmpty()}">
                    <c:forEach var="plant" items="${list}">
                        <table class="product"> 
                            <tr>
                                <td>
                                    <img src="${plant.imgpath}" class="plantimg">
                                    <br>
                                    Product ID: ${plant.id}
                                    <br>
                                    Product name ${plant.name}
                                    <br>
                                    Price: ${plant.price}
                                    <br>
                                    Category: ${plant.status == 0 ? "Out of stock" : "Available"}
                                    <br>
                                    <a href="MainController?action=addtocart&pid=${plant.id}"> add to cart </a> 
                                </td>
                            </tr>
                        </table>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <h3>${msg == null ? "" : msg}</h3>
                </c:otherwise>
            </c:choose>

        </section>
        <footer>    
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
