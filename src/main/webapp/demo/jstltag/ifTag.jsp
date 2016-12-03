<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: --流程控制标签 if标签示例</title>
</head>
<body>
<h4>if标签示例</h4>
<hr>
<form action="${pageContext.request.contextPath}/demo/jstlTag/ifTag.jsp" method="post">
    <input type="text" name="uname" value="${param.uname}">
    <input type="submit" value="登录">
</form>
<%--使用if标签进行判断并把检验后的结果赋给adminchock，存储在默认的page范围中。 --%>
<c:if test="${param.uname=='admin'}" var="adminchock">
    <%--可以把adminchock的属性范围设置为session，这样就可以在其他的页面中得到adminchock的值，
    使用<c:if text=”${adminchock}”><c:if>判断，实现不同的权限。 --%>
    <c:out value="管理员欢迎您！"/>
</c:if>
<%--使用EL表达式得到adminchock的值，如果输入的用户名为admin将显示true。 --%>
${adminchock}
</body>
</html>
