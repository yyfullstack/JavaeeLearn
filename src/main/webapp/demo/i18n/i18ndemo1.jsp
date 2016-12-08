<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fn" uri="/ELFunction" %>
<html>
<head>
    <title>国际化(i18n)测试</title>
    <%
        System.out.println(request.getLocale().toString());
        //request.getLocale()获取访问用户所在的国家地区
        ResourceBundle myResourcesBundle = ResourceBundle.getBundle("myproperties", request.getLocale());
    %>
</head>
<body>
<form action="" method="post">
    <%=myResourcesBundle.getString("username")%>：<input type="text" name="username"/><br/>
    <%=myResourcesBundle.getString("password")%>：<input type="password" name="password"/><br/>
    <input type="submit" value="<%=myResourcesBundle.getString("submit")%>">
</form>
</body>
</html>