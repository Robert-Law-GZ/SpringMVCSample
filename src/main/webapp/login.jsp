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
    <input id="a" name="username" type="text">
    <input id="b" name="password" type="password">
    <button type="submit">确认</button>
</form:form>
</body>
</html>
