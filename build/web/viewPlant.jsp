<%-- 
    Document   : ProductDetail
    Created on : Oct 21, 2022, 5:45:41 PM
    Author     : Nghia Nguyen
--%>

<%@page import="sample.dto.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>  
        <section>
            <jsp:useBean id="plant" class="sample.dto.Plant" scope="request" />
            <table class="producttable">
                <tr>
                    <td>Product ID</td>
                    <td>Name</td>
                    <td>price</td>
                    <td>Description</td>
                    <td>image</td>
                    <td>Category</td>
                    <td>Status</td>
                </tr>
                <tr>
                    <td>${plant.id}</td>
                    <td>${plant.name}</td>
                    <td>${plant.price}</td>
                    <td>${plant.description}</td>
                    <td><img src="${plant.imgpath}" width="100" height="100"></td>
                    <td>${plant.catename}</td>
                    <td>${plant.status}</td>
                </tr>
            </table>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
