<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-16
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Offers</title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
<table class="offerstable">
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td>Text</td>
    </tr>
    <c:forEach var="offer" items="${offers}">
        <tr>
            <td>
                <c:out value="${offer.name}"></c:out>
            </td>
            <td>
                <c:out value="${offer.email}"></c:out>
            </td>
            <td>
                <c:out value="${offer.text}"></c:out>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
