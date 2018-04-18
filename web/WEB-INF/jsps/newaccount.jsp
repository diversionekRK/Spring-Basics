<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-18
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s-form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New account</title>

    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<s-form:form method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">

    <table class="formtable">
        <tr>
            <td class="label">Username:</td>
            <td>
                <s-form:input class="control" path="username" name="username" type="text"/>
                <br/>
                <s-form:errors path="username" cssClass="error"></s-form:errors>
            </td>
        </tr>
        <tr>
            <td class="label">Email:</td>
            <td><s-form:input class="control" path="email" name="email" type="text"/>
                <br/>
                <s-form:errors path="email" cssClass="error"></s-form:errors>
            </td>
        </tr>
        <tr>
            <td class="label">Password:</td>
            <td>
                <s-form:input class="control" path="password" name="password" type="text"/>
                <br/>
                <s-form:errors path="password" cssClass="error"></s-form:errors>
            </td>
        </tr>
        <tr>
            <td class="label">Confirm Password:</td>
            <td><input class="control" name="confirmpass" type="text"/><br/></td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Create account" type="submit"/></td>
        </tr>
    </table>

</s-form:form>

</body>
</html>
