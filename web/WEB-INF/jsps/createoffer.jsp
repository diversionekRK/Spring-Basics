<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-16
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s-form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create offer</title>
    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.request.contextPath}/static/scripts/jquery.js"></script>

    <script>
        function onDeleteClick() {
            var doDelete = confirm("Are you sure you want to delete your offer?");

            if(doDelete == false)
                event.preventDefault();
        }

        function onReady() {
            $("#delete").click(onDeleteClick)
        }

        $(document).ready(onReady);
    </script>

</head>
<body>

<s-form:form action="${pageContext.request.contextPath}/docreate" method="post" commandName="offer">
    <s-form:hidden path="id"></s-form:hidden>
    <table class="formtable">
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
            <td><input class="control" value="Save advert" type="submit"/></td>
        </tr>
        <c:if test="${offer.id != 0}">
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Delete advert" type="submit" name="delete" id="delete"/></td>
        </tr>
        </c:if>
    </table>
</s-form:form>


</body>
</html>
