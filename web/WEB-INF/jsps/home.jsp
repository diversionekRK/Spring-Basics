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
</head>
<body>
<p><a href="${pageContext.request.contextPath}/offers">Show current offers</a></p>
<p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a></p>

<sec:authorize access="hasRole('admin')">
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
