<%--
  Created by IntelliJ IDEA.
  User: walden
  Date: 16/7/7
  Time: 上午10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>heool</title>
</head>
<body>
<center>
    <span class="STYLE3">用户登录</span>
</center>
<form method="get" action="/user/registry">
    <center>${error.error}</center>
    <table align="center">
        <tr>
            <td height="23"><span class="STYLE3">输入用户名：</span></td>
            <td height="23"><input name="user_name" type="text"></td>
        </tr>
        <tr>
            <td height="23"><span class="STYLE3">输入密码：</span></td>
            <td height="23"><input name="user_password" type="password"></td>
        </tr>
        <tr>
            <td height="23" colspan="2" align="center">
                <input type="submit" value="登录">
                <input type="reset" value="重置">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
