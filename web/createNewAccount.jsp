
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Account</title>
    </head>
    <body>
        <h1>Create New Account</h1>
        <form action="DispatchController" method="POST">
            <c:set var="errors" value="${requestScope.CREATE_ERRORS}"/>
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /> (6-20 chars) <br/>
            <c:if test="${not empty errors.usernameLengthError}">
                <font color="red">
                ${errors.usernameLengthError}
                </font><br/>
            </c:if>
            <c:if test="${not empty errors.usernameIsExisted}">
                <font color="red">
                ${errors.usernameIsExisted}
                </font><br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="" />(6-30 chars)<br/>
            <c:if test="${not empty errors.passwordLengthError}">
                <font color="red">
                ${errors.passwordLengthError}
                </font><br/>
            </c:if>
            Confirm <input type="password" name="txtConfirm" value="" /><br/>
            <c:if test="${not empty errors.confirmNotMatched}">
                <font color="red">
                ${errors.confirmNotMatched}
                </font><br/>
            </c:if>
            Full name <input type="text" name="txtFullname" value="${param.txtFullname}" />(2-50 chars)<br/>
            <c:if test="${not empty errors.fullNameLengthError}">
                <font color="red">
                ${errors.fullNameLengthError}
                </font><br/>
            </c:if>
            <input type="submit" value="Create New Account" name="btAction" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
