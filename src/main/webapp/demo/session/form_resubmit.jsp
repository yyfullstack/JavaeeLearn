<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page import="java.net.URLEncoder" %>
<%@page isELIgnored="false"%>
<html>
<head>
    <title>重复提交的 form 表单</title>
    <style type="text/css">
        div {
            min-height: 30px;
        }

        label {
            width: 100px;
            display: inline-block;
        }


    </style>

    <script>
        //表单是否已经提交标识，默认为false
        var isComitted = false;
        //针对"在网络延迟的情况下让用户有时间点击多次submit按钮导致表单重复提交"这个应用场景，
        // 使用JavaScript是可以解决这个问题的，解决的做法就是"用JavaScript控制Form表单只能提交一次"
        function dosubmit() {
            if(isComitted == false) {
                isComitted = true;
                return true;
            }else {
                return false;
            }
        }
        //除了用这种方式之外，经常见的另一种方式就是表单提交之后，
        //将提交按钮设置为不可用，让用户没有机会点击第二次提交按钮
        function dosubmit2() {
            //获取表单提交按钮
            var btnSubmit = document.getElementById("submit");
            //将表单提交按钮设置为不可用，这样就可以避免用户再次点击提交按钮
            btnSubmit.disabled = "disabled";
            //返回true让表单可以正常提交
            return true;
        }

        function dosubmit2_repeat() {
            var btnSubmit = document.getElementById('submit');
            btnSubmit.disabled = true;
            return true;
        }
    </script>
</head>
<body>
<h2>表单</h2>

<fieldset>
    <legend>form表单</legend>
    <form action="/session/formSubmitServlet" method="post" onsubmit="return dosubmit()">
        <div>
            <label for="username">
                用户名
            </label>
            <%--在form.jsp中使用隐藏域来存储Token(令牌)--%>
            <input type="hidden" name="token" value="${token}"/>
            <input type="text" name="username" id="username">
        </div>
        <div style="padding:10px 100px;">
            <input type="submit" value="提交" id="submit">
        </div>
    </form>
</fieldset>
</body>
</html>
