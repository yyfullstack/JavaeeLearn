<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.yckjsoft.javaee.el.User" %>
<html>
<head>
    <title>el隐式对象</title>
</head>
<body>
<br/>---------------1、pageContext对象：获取JSP页面中的pageContext对象------------------------<br/>
${pageContext}
<br/>---------------2、pageScope对象：从page域(pageScope)中查找数据------------------------<br/>
<%
    pageContext.setAttribute("name", "孤傲苍狼");  //map
%>
${pageScope.name}

<br/>---------------3、requestScope对象：从request域(requestScope)中获取数据------------------------<br/>
<%
    request.setAttribute("name", "白虎神皇");  //map
%>
${requestScope.name}
<br/>---------------4、sessionScope对象：从session域(sessionScope)中获取数据------------------------<br/>
<%
    session.setAttribute("user", "xdp");  //map
%>
${sessionScope.user}
<br/>---------------5、applicationScope对象：从application域(applicationScope)中获取数据------------------------<br/>
<%
    application.setAttribute("user", "gacl");  //map
%>
${applicationScope.user}

<br/>--------------6、param对象：获得用于保存请求参数map，并从map中获取数据------------------------<br/>
<!-- http://localhost:8080/demo/el/elDemo3.jsp?name=aaa  -->
${param.name}
<!-- 此表达式会经常用在数据回显上 -->
<form action="/demo/el/elDemo3.jsp" method="post">
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="注册">
</form>
<br/>--------------7、paramValues对象：paramValues获得请求参数 //map{"",String[]}------------------------<br/>
<!-- http://localhost:8080/demo/el/elDemo3.jsp?like=aaa&like=bbb -->
${paramValues.like[0]}
${paramValues.like[1]}
<br/>--------------8、header对象：header获得请求头------------------------<br/>
${header.Accept}<br/>
<%--${header.Accept-Encoding} 这样写会报错
     测试headerValues时，如果头里面有“-” ，例Accept-Encoding，则要headerValues[“Accept-Encoding”]
--%>
${header["Accept-Encoding"]}
<br/>--------------9、headerValues对象：headerValues获得请求头的值------------------------<br/>
<%--headerValues表示一个保存了所有http请求头字段的Map对象，它对于某个请求参数，返回的是一个string[]数组
例如：headerValues.Accept返回的是一个string[]数组 ，headerValues.Accept[0]取出数组中的第一个值
--%>
${headerValues.Accept[0]}<br/>
<%--${headerValues.Accept-Encoding} 这样写会报错
     测试headerValues时，如果头里面有“-” ，例Accept-Encoding，则要headerValues[“Accept-Encoding”]
     headerValues["Accept-Encoding"]返回的是一个string[]数组，headerValues["Accept-Encoding"][0]取出数组中的第一个值
--%>
${headerValues["Accept-Encoding"][0]}
<br/>--------------10、cookie对象：cookie对象获取客户机提交的cookie------------------------<br/>
<!-- 从cookie隐式对象中根据名称获取到的是cookie对象,要想获取值，还需要.value -->
${cookie.JSESSIONID.value} //保存所有cookie的map
<br/>--------------11、initParam对象：initParam对象获取在web.xml文件中配置的初始化参数------------------------<br/>
${initParam.url}

</body>
</html>