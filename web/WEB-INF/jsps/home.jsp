<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-11
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home page</title>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/offers">Show current offers</a></p>
<p><a href="${pageContext.request.contextPath}/createoffer">Add a new offer</a></p>

<p><a href="<c:url value='/j_spring_security_logout'/>">Log out</a></p>
</body>
</html>
