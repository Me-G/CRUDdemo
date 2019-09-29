<%-- 
    Document   : login
    Created on : 2019年9月8日, 下午1:18:20
    Author     : ME
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>登陆页面</h1>
        <br/><br/>
        <form:form action="tableView" method="POST">
            用户名:<input type="text" name="username"/><br/>
            密码:<input type="password" name="password"/><br/>
            <input type="submit" value="登录"/>
        </form:form>
    </body>
</html>




















