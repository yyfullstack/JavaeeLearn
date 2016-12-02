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
    //此时设置的属性只能够在本页中取得
    pageContext.setAttribute("name", "孤傲苍狼");  //设置属性
    pageContext.setAttribute("date", new Date()); //设置属性
    //注意：这里设置的两个属性的名字分别为name和date，这两个是字符串类型的数据，但对应的属性值MLDN和new Date这个两个值却不是字符串类型，而是两个Object类型的数据。
%>
<%
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