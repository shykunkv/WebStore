<%--
  Created by IntelliJ IDEA.
  User: Kostya
  Date: 21.11.2015
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registeration</title>
</head>
<body>

<form action="/register" method="POST">
    Name: <input type="text" name="login">
    <br />
    Email address: <input type="text" name="mail">
    <br />
    Password: <input type="password" name="password" />
    <br />
    <input type="submit" value="GO" />
</form>

</body>
</html>
