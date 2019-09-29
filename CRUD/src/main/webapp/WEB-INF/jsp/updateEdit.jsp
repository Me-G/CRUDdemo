<%-- 
    Document   : updateEdit
    Created on : 2019年8月15日, 下午4:19:13
    Author     : ME
--%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UpdateEdit Page</title>
    </head>
    <body>
        <h1>修改页面</h1>
        <form action="../updateWebsite" method="post" modelAttribute="updateWeb">   
            <input type="hidden" name="_method" value="put"/>
            <input type="hidden" path="id" name="id" value="${editWeb.id}"/>
            Name: <input path="name" name="name" value="${editWeb.name}"/><br/>
            Value: <input path="value" name="value" value="${editWeb.value}"/><br/>
            Remark: <input path="remark" name="remark" value="${editWeb.remark}"/><br/>
            <input type="submit" value="修改"/>
        </form>  
    </body>
</html>
