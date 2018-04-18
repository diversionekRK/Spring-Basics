<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-18
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login Page</title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
</head>
<body onload='document.f.j_username.focus();'>
<h3>Login with Username and Password</h3>

<c:if test="${param.error == true}">
    <p class="error">Invalid username or password. Please try again!</p>
</c:if>

<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
    <table class="formtable">
        <tr>
            <td>User:</td>
            <td><input type='text' name='j_username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='j_password'/></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Login"/></td>
        </tr>
    </table>
</form>

<p><a href="<c:url value="/newaccount"/>">Create new account</a></p>
</body>
</html>
