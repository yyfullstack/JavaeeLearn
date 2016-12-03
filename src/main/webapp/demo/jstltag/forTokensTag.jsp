<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: -- forTokens标签实例</title>
</head>
<body>
<h4><c:out value="forToken实例"/></h4>
<hr/>
<%--提示：分隔符的作用是根据标识，截取字符串。
         如果未设定分隔符或在字符串中没有找到分隔付，将把整个元素作为一个元素截取。
         在实际应用中用于在除去某些符号在页面中显示。 --%>

<c:forTokens var="str" items="北、京、欢、迎、您" delims="、">
    <c:out value="${str}"></c:out><br/>
</c:forTokens>
<br/>
<c:forTokens items="123-4567-8854" delims="-" var="t">
    <c:out value="${t}"></c:out><br/>
</c:forTokens>

<br/>
<c:forTokens items="1*2*3*4*5*6*7"
             delims="*"
             begin="1"
             end="3"
             var="n"
             varStatus="s">
    &nbsp;<c:out value="${n}"/>的四种属性：<br>
    &nbsp;&nbsp;&nbsp;&nbsp;所在位置，即索引：<c:out value="${s.index}"/><br>
    &nbsp;&nbsp;&nbsp;&nbsp;总共已迭代的次数：<c:out value="${s.count}"/><br>
    &nbsp;&nbsp;&nbsp;&nbsp;是否为第一个位置：<c:out value="${s.first}"/><br>
    &nbsp;&nbsp;&nbsp;&nbsp;是否为最后一个位置：<c:out value="${s.last}"/><br>
</c:forTokens>
</body>
</html>
