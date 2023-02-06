<%-- 
    Document   : foodList
    Created on : 30-10-2022
    Author     : hd
--%>

<%@page import="fall22.pe.user.UserDTO" %>
<%@page import="fall22.pe.food.FoodDTO" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food List Page</title>
        <link rel="stylesheet" href="mycss.css" />
    </head>
    <body>
        <!--your code here-->
        <c:set var="acc" value="${sessionScope.acc}" ></c:set>
        <c:choose>
            <c:when test="${acc != null}">
                <h2>Welcome ${acc.getFullName()}</h2>
                <form action="MainController" method="post">
                    <table>
                        <tr>
                            <td>
                                <input type="text" name="search" required value="${param.search == null ? '' : param.search}"> 
                            </td>
                            <td>
                                <input type="submit" name="action" value="search">
                            </td>
                        </tr>
                    </table>
                </form>
                <font style="color: red">${error == null ? '' : error} </font>
                <font style="color: green">${msg == null ? '' : msg} </font>
                <c:set var="foodList" value="${requestScope.foodList}"></c:set>
                <c:if test="${foodList != null && !foodList.isEmpty()}">
                    <table class="food">
                        <tr style="margin: 3px">
                            <th>NO</th>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Cooking Time</th>
                            <th>Status</th>
                            <th>ACtion</th>
                        </tr>
                        <c:forEach var="index" begin="1" end="${foodList.size()}" step="1">
                            <c:set var="food" value="${foodList.get(index-1)}"></c:set>
                            <c:if test="${food.getStatus() == 1}">
                                <form action="MainController" method="post">
                                    <tr style="border: 1px solid;">
                                        <td>${index}</td>
                                        <td>${food.getId()}</td>
                                        <td>${food.getName()}</td>
                                        <td>${food.getDescription()}</td>
                                        <td>${food.getPrice()}</td>
                                        <td>${food.getCookingTime()}</td>
                                        <td>
                                            available
                                        </td>
                                        <td>
                                            <input type="text" hidden name="search" value="${param.search}" />
                                            <input type="text" hidden name="id" value="${food.getId()}"/>
                                            <input type="submit" value="delete" name="action"/>
                                        </td>
                                    </tr>
                                </form>
                            </c:if>    
                        </c:forEach>
                    </table>

                </c:if>
                <br />
                <form action="MainController" method="POST">
                    <input type="submit" name="action" value="logout">
                </form>
            </c:when>
            <c:otherwise>
                <font style="color: red;"> Forbidden access to this page, click <a href='./'>here</a> to go back</font>
            </c:otherwise>
        </c:choose>

    </body>
</html>
