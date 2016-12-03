<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: --表达式控制标签“out”标签的使用</title>
</head>
<body>
<h3><c:out value="下面的代码演示了c:out的使用，以及在不同属性值状态下的结果。"/></h3>
<ul>
    <%--（1）直接输出了一个字符串。 --%>
    <li>（1） <c:out value="JSTL的out标签的使用"/></li>
    <li>（2） <c:out value="<a href='http://www.cnblogs.com/'>点击链接到博客园</a>"/></li>
    <%--escapeXml="false"表示value值中的html标签不进行转义，而是直接输出 --%>
    <li>（3） <c:out value="<a href='http://www.cnblogs.com/'>点击链接到博客园</a>" escapeXml="false"/></li>
    <%--(4）字符串中有转义字符，但在默认情况下没有转换。 --%>
    <li>（4） <c:out value="&lt未使用字符转义&gt"/></li>
    <%--（5）使用了转义字符&lt和&gt分别转换成<和>符号。 --%>
    <li>（5） <c:out value="&lt使用字符转义&gt" escapeXml="false"/></li>
    <%--（6）设定了默认值，从EL表达式${null}得到空值，所以直接输出设定的默认值。 --%>
    <li>（6）<c:out value="${null}">使用了默认值</c:out></li>
    <%--（7）未设定默认值，输出结果为空。 --%>
    <li>（7）<c:out value="${null}"></c:out></li>
    <%--（8）设定了默认值，从EL表达式${null}得到空值，所以直接输出设定的默认值。 --%>
    <li>（8）<c:out value="${null}" default="默认值"/></li>
    <%--（9）未设定默认值，输出结果为空。 --%>
    <li>（9）<c:out value="${null}"/></li>

</ul>
</body>
</html>
