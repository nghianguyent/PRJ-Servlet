<%-- 
    Document   : Profile
    Created on : Oct 12, 2022, 3:20:23 PM
    Author     : Nghia Nguyen
--%>

<%@page import="sample.dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <%@include file="header_loginedUser.jsp" %>
        </header>

        <section>
            <jsp:useBean class="sample.dto.Account" id="acc" scope="session" />
            <%
                String email = (String) session.getAttribute("email");
                String name = (String) session.getAttribute("name");
                String msg = request.getParameter("msg");
                if (name == null) {
            %>

            <p>
                <font color="red">you must login to veiw personal page</font>
            </p>
            <%
            } else {
            %>
            <h2>Welcome back ${acc.fullname}</h2>
            <h3>
                <font color="green">
                <a href="MainController?action=logout">Logout</a>
                </font>
            </h3>
            <h2>Change your profile</h2>
            <h2 style="color: green">
                <%= msg == null ? "" : msg%>
            </h2>
            <form action="MainController" method="post">
                <table>
                    <tr>
                        <td>Account ID:</td>
                        <td> 
                            <input type="number" value="${acc.acacid}" name="accId" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td>Full name:</td>
                        <td> 
                            <input type="text" value="${acc.fullname}" name="name">
                        </td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td> 
                            <input type="text" value="${acc.email}" name="email" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td>Phone Number:</td>
                        <td> 
                            <input type="text" value="${acc.phone}" name="phone">
                        </td>
                    </tr>
                    <tr>
                        <td>Status:</td>
                        <td> 
                            <input type="number" value="${acc.status}" name="status" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td> 
                            <input type="number" value="${acc.role}" name="role" disabled="true">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="updateProfile" name="action">
                        </td>
                    </tr>
                </table>
            </form>
        </section>

        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <%
            }
        %>
    </body>
</html>
