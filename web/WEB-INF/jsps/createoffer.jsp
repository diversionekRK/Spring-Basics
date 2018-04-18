<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-16
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s-form"%>
<html>
<head>
    <title>Create offer</title>

    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

<s-form:form action="${pageContext.request.contextPath}/docreate" method="post" commandName="offer">
    <table class="formtable">
        <tr>
            <td class="label">Name: </td>
            <td>
                <s-form:input class="control" name="name" type="text" path="name"/>
                <br/>
                <s-form:errors path="name" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td class="label">Email: </td>
            <td>
                <s-form:input class="control" name="email" type="email" path="email"/>
                <br/>
                <s-form:errors path="email" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td class="label">Your offer: </td>
            <td>
                <s-form:textarea class="control" name="text" rows="10" cols="10" path="text"></s-form:textarea>
                <br/>
                <s-form:errors path="text" cssClass="error"/>
            </td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Create advert" type="submit"/></td>
        </tr>
    </table>
</s-form:form>


</body>
</html>
