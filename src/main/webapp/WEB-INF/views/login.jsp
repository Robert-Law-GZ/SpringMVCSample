<%--
  Created by IntelliJ IDEA.
  User: robert
  Date: 2018/4/26
  Time: 下午2:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form action="/login" method="post" commandName="employee">
    <table>
        <tr>
            <td>用户名：</td>
            <td><form:input path="username"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><form:input path="password"/></td>
        </tr>
    </table>

    <button type="submit">确认</button>
</form:form>
</body>
</html>
