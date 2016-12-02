<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>request接收中文参数乱码问题</title>
    <style type="text/css">
        div {
            min-height: 30px;
        }

        label {
            width: 100px;
            display: inline-block;
        }
    </style>
</head>
<body>
<h2>表单</h2>

<fieldset>
    <legend>form表单</legend>
    <form action="/request/RequestDemo5" method="get">
        <div>
            <label for="username">
                用户名
            </label>
            <input type="text" name="username" id="username">
        </div>
        <div style="padding:10px 100px;">
            <input type="submit" value="提交" id="submit">
        </div>
    </form>
</fieldset>
</body>
</html>
