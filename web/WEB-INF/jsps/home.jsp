<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-11
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Home page</title>
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
                <c:out value="${offer.user.name}"></c:out>
            </td>
            <td>
                <c:out value="${offer.user.email}"></c:out>
            </td>
            <td>
                <c:out value="${offer.text}"></c:out>
            </td>
        </tr>
    </c:forEach>
</table>

<c:choose>
    <c:when test="${hasOffer == true}">
        <p><a href="${pageContext.request.contextPath}/createoffer">Edit or delete your offer</a></p>
    </c:when>
    <c:otherwise>
        <p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a></p>
    </c:otherwise>
</c:choose>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <p><a href="<c:url value='/admin'/>">For admins</a></p>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    <p><a href="<c:url value='/j_spring_security_logout'/>">Log out</a></p>
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <p><a href="<c:url value='/login'/>">Log in</a></p>
</sec:authorize>

</body>
</html>
