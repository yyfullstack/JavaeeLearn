<%--
  Created by IntelliJ IDEA.
  User: yong
  Date: 2016/10/26 0026
  Time: 9:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>application属性范围</title>
</head>
<body>
<%
    ////取得applicationScopedemo01.jsp设置的属性
    //取得设置的属性
    String refName = (String) application.getAttribute("name");
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
    Date refDate = (Date) application.getAttribute("date");
%>
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>
<%--　开启多个浏览器窗口，运行applicationScopeDemo02.jsp时，都可以显示出上图所示的结果，因为属性范围设置在了服务器中，
所以只要是连接到此服务器的任意用户都可以取得此属性，当然，如果服务器关闭的话，则此属性肯定消失。
　注意：如果在服务器上设置了过多的application属性，则会影响到服务器的性能。--%>
</body>
</html>