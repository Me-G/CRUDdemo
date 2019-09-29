<%-- 
    Document   : operation
    Created on : 2019年9月7日, 下午4:33:33
    Author     : ME
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>操作页面</title>
    </head>
    <body>
        <h1>操作页面</h1>
        <table width="100%" border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Value</th>
                <th>Remark</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${allwebsites}" var="website">
                <tr>
                <td>${website.id}</td>
                <td>${website.name}</td>
                <td>${website.value}</td>
                <td>${website.remark}</td>
                <td><a href="updateEdit/${website.id}">编辑</a></td>
                <td><form:form action="deleteWebsite/${website.id}" method="post">
                    <input type="hidden" name="_method" value="delete"/>
                            <input type="submit" value="删除"/>
                    </form:form>
                </td>
                </tr>
            </c:forEach>
          
        </table>
        
        <br/>
            <form:form action="insertWebsite" method="post" name="insertWeb">
                Name: <input path="name"name="name"/><br/>
                Value: <input path="value" name="value"/><br/>
                Remark: <input path="remark" name="remark"/><br/>
                <input type="submit" value="添加"/>
            </form:form>
            <br/>
    </body>
</html>
