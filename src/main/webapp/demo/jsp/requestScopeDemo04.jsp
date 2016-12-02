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
    <title>request属性范围</title>
</head>
<body>
<%
    //取得requestScopedemo01.jsp设置的属性
    //取得设置的属性
    String refName = (String) request.getAttribute("name");
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
    Date refDate = (Date) request.getAttribute("date");

    //此时使用了超链接跳转，一旦跳转之后，地址栏改变，所以此种跳转也可以称为客户端跳转
    //requestScopeDemo04.jsp页面显示的结果是null。这说明了在requestScopeDemo01.jsp这个页面设置的属性经过超链接
    //这种客户端跳转到别的页面时别的页面是无法取得requestScopeDemo01.jsp中设置的属性的。
%>
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>
</body>
</html>