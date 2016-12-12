<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>html过滤器测试</title>
</head>
<body>

<form action="/filter/servletDemo2" method="post">
    留言:
    <textarea rows="8" cols="70" name="message">
    <script type="text/javascript">
    while (true) {
        alert("死循环了，我会不停地弹出了");
    }
    </script>
    <a href="http://www.cnblogs.com">访问博客园</a>
    </textarea>
    <input type="submit" value="发表">
</form>

</body>
</html>
