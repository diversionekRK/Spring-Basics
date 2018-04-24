<%--
  Created by IntelliJ IDEA.
  User: Div
  Date: 2018-04-18
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s-form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>New account</title>

    <link href="${pageContext.request.contextPath}/static/css/main.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/static/scripts/jquery.js"></script>

    <script>
        function onLoad() {
            $("#password").keyup(checkPasswordsMatch);
            $("#confirmpass").keyup(checkPasswordsMatch);

            $("#details").submit(canSubmit)
        }

        function canSubmit() {
            var password = $("#password").val();
            var confirmpass = $("#confirmpass").val();

            if(password == confirmpass && password.length > 0) {
                return true;
            }
            else {
                return false;
            }
        }

        function checkPasswordsMatch() {
            var password = $("#password").val();
            var confirmpass = $("#confirmpass").val();

            if(password.length > 3 || confirmpass.length > 3) {
                if(password == confirmpass) {
                    $("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'></fmt:message>");
                    $("#matchpass").addClass("valid");
                    $("#matchpass").removeClass("error");
                }
                else {
                    $("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'></fmt:message>");
                    $("#matchpass").addClass("error");
                    $("#matchpass").removeClass("valid");
                }
            }
        }

        $(document).ready(onLoad);
    </script>
</head>
<body>

<s-form:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">

    <table class="formtable">
        <tr>
            <td class="label">Username:</td>
            <td>
                <s-form:input class="control" path="username" name="username" type="text"/>
                <br/>
                <div class="error"><s-form:errors path="username"></s-form:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Email:</td>
            <td><s-form:input class="control" path="email" name="email" type="text"/>
                <br/>
                <div class="error"><s-form:errors path="email"></s-form:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Password:</td>
            <td>
                <s-form:input class="control" path="password" name="password" type="password" id="password"/>
                <br/>
                <div class="error"><s-form:errors path="password"></s-form:errors></div>
            </td>
        </tr>
        <tr>
            <td class="label">Confirm Password:</td>
            <td>
                <input class="control" name="confirmpass" type="password" id="confirmpass"/>
                <div id="matchpass"></div>
            </td>
        </tr>
        <tr>
            <td class="label"></td>
            <td><input class="control" value="Create account" type="submit"/></td>
        </tr>
    </table>

</s-form:form>

</body>
</html>
