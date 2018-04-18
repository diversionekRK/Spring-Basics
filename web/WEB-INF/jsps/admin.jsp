<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-18
  Time: 23:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin only</title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<h2>For admins only!</h2>

<table class="formtable">
    <tr>
        <td>Username</td>
        <td>Email</td>
        <td>Authority</td>
        <td>Enabled</td>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.authority}</td>
            <td>${user.enabled}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
