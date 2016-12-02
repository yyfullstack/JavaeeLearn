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
    <title>page属性范围（pageContext）</title>
</head>
<body>
<%
    //使用了服务器端跳转，但是发现内容并不能取得，证明page范围的属性只能在本页中取得，跳转到其他页面之中不能取得。
    // 如果现在希望跳转到其他页面之中，依然可以取得，则可以扩大属性范围，使用request属性范围即可。
    //取得设置的属性
    String refName = (String) pageContext.getAttribute("name");
    //由于取得的值为Object类型，因此必须使用String强制向下转型，转换成String类型
    Date refDate = (Date) pageContext.getAttribute("date");
%>
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>
</body>
</html>