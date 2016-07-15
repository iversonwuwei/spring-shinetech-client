<%--
  Created by IntelliJ IDEA.
  User: walden
  Date: 16/7/13
  Time: 下午3:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success</title>
</head>
<body><br><br>
<center>
    系统登录成功<br><br><br>${user.user.user_name}，欢迎光临！
</center>
<div>
    <a href="/user/login">Log Out!</a>
</div>
</body>
</html>
