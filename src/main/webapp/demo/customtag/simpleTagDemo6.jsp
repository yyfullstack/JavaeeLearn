<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%--在Jsp页面中使用自定义标签--%>
<%--使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库--%>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>如果标签接收的属性值是一个复合数据类型，该如何给标签的属性赋值</title>
</head>
<body>

<%--
在jsp页面中使用自定义标签，标签有一个date属性 ，是一个复合数据类型
如果标签的属性值是8种基本数据类型，那么在JSP页面在传递字符串时，JSP引擎会自动转换成相应的类型
但如果标签的属性值是复合数据类型，那么JSP引擎是无法自动转换的，
这里将一个字符串赋值给demo6标签的date属性，在运行标签时就会出现如下错误：
Unable to convert string "1988-05-07" to class "java.util.Date" for attribute "date":
Property Editor not registered with the PropertyEditorManager
<yc:simpleTagDemo6 date="1988-05-07"/>
--%>
<%--如果一定要给标签的复合属性赋值，那么可以采用表达式的方式给复合属性赋值，如下所示： --%>

<%
    Date d = new Date();
    request.setAttribute("date", d);
%>
<%--在jsp页面中使用自定义标签 demo6标签--%>
<yc:simpleTagDemo6 date="${date}"/>
<yc:simpleTagDemo6 date="<%=new Date()%>"/>
</body>
</html>
