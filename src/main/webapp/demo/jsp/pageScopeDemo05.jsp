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
    <title>pageContext属性范围的进一步补充</title>
</head>
<body>
<%
    //使用request对象获取属性
    String refName = (String) request.getAttribute("name");
    Date refDate = (Date) request.getAttribute("date");
    //也可以使用pageContext对象获取属性，只要在获取时指明对象的属性范围即可
    String refName2 = (String) pageContext.getAttribute("name", PageContext.REQUEST_SCOPE);
    Date refDate2 = (Date) pageContext.getAttribute("date", PageContext.REQUEST_SCOPE);
%>
使用request对象获取属性：
<h1>姓名：<%=refName%>
</h1>
<h1>日期：<%=refDate%>
</h1>
使用pageContext对象获取属性：
<h1>姓名：<%=refName2%>
</h1>
<h1>日期：<%=refDate2%>
</h1>

<%--在pageScopeDemo04.jsp使用的是pageContext对象调用setAttribute()方法设置的属性范围是request的属性范围，因此在调用此方法时，
把一个int类型的scope范围常量REQUEST_SCOPE传递了进来，这个REQUEST_SCOPE属性范围常量的作用就是告诉pageContext对象现在要设置
的属性范围是request的属性范围，所以pageScopeDemo05.jsp这个页面中可以直接使用request.getAttribute();
方法获取在pageScopeDemo04.jsp设置的属性。--%>
</body>
</html>