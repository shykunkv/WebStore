<%@ page import="ents.User" %><%--
  Created by IntelliJ IDEA.
  User: Kostya
  Date: 21.11.2015
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% User u = (User) session.getAttribute("user"); %>

<html>
<head>
    <title>Hello</title>
</head>
<body>
    <h2>Hello <%= u.getLogin() %></h2>
</body>
</html>
