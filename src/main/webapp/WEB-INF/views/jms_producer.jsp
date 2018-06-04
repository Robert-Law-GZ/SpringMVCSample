<%--
  Created by IntelliJ IDEA.
  User: robert
  Date: 2018/5/29
  Time: 上午10:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JMS-Producer</title>
</head>
<body>

<h1>JMS-Producer!!!</h1>
<form action="onsend" method="post">
    MessageText:<textarea name="message">${time}</textarea>
    <input type="submit" value="Submit">
</form>

<h2><a href="welcome">RETURN HOME</a></h2>

</body>
</html>