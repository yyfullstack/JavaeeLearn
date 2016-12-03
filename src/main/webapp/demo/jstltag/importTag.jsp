<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>JSTL: -- import标签实例</title>
</head>
<body>
<h4><c:out value="import实例"/></h4>
<hr/>
<h4><c:out value="绝对路径引用的实例"/></h4>
<%--使用绝对路径导入百度首页，
    导入时使用<c:catch></c:catch>捕获异常。
 --%>
<c:catch var="error1">
    <c:import url="http://wwww.baidu.com" charEncoding="utf-8"/>
</c:catch>
${error1}
<hr/>


<h4>
    <c:out value="相对路径引用本应用中的文件"/>
</h4>
<%--使用相对路径导入同一文件夹下的“JSTL的import标签使用说明”文件，
    接收的字符编码格式使用charEncoding设置为utf-8。 --%>
<c:catch var="error2">
    <c:import url="JSTL的import标签使用说明" charEncoding="utf-8"/>
</c:catch>
${error2}


<c:catch var="errora">
    <c:import url="outTag.jsp" charEncoding="utf-8"/>
</c:catch>
${errora}
<h4><c:out value="使用字符串输出相对路径引用的实例，并保存在session范围内"/></h4>
<%--导入“JSTL的import标签使用说明”，
    使用var定义的变量接收要导入的文件，并存储在session中，
    如果在其他页面同样也要导入该文件，只须使用<c:out>输出“JSTL的import标签使用说明”的值即可。
--%>
<c:catch var="error3">
    <c:import
            var="myurl"
            url="JSTL的import标签使用说明"
            scope="session"
            charEncoding="utf-8"/>
    <c:out value="${myurl}"></c:out>
    <hr/>
    <c:out value="${myurl}"/>
</c:catch>
${error3}

<c:catch var="error4">
    <c:import
            var="indexpage"
            url="/index.jsp"
            scope="session"
            charEncoding="utf-8"/>
    <c:out value="${indexpage}"></c:out>
    <hr/>
    <c:out value="${indexpage}"/>
</c:catch>
${error4}

<c:catch var="error5">
    <c:import url="/test.jsp"
        context="/demo"/>
    <hr/>
</c:catch>
${error5}
</body>
</html>
