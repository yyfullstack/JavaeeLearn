<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<head>
    <title>html form 表单</title>
    <style type="text/css">
        div {
            min-height: 30px;
        }

        label {
            width: 100px;
            display: inline-block;
        }

        textarea {
            height: 200px;
            width: 400px;
            overflow: auto;
            word-break: break-all;
        }
    </style>
</head>
<body>
<h2>表单</h2>

<fieldset>
    <legend>form表单</legend>
    <form action="/request/RequestDemo4" method="post">
        <div>
            <label for="userid">
                编号
            </label>
            <input type="text" name="userid" id="userid">
        </div>
        <div>
            <label for="username">
                用户名
            </label>
            <input type="text" name="username" id="username">
        </div>
        <div>
            <label for="userpass">
                密码
            </label>
            <input type="password" name="userpass" id="userpass">
        </div>
        <div>
            <label>
                性别
            </label>
            <input type="radio" name="sex" checked="checked" value="男">男
            <input type="radio" name="sex" value="女">女
        </div>
        <div>
            <label for="dept">
                部分
            </label>
            <select id="dept" name="dept" style="width: 150px;">
                <option value="技术部">技术部</option>
                <option value="销售部">销售部</option>
                <option value="财务部">财务部</option>
            </select>
        </div>
        <div>
            <label>
                兴趣
            </label>
            <input type="checkbox" name="inst" checked="checked" value="唱歌">唱歌
            <input type="checkbox" name="inst" value="游泳">游泳
            <input type="checkbox" name="inst" value="跳舞">跳舞
            <input type="checkbox" name="inst" value="编程">编程
            <input type="checkbox" name="inst" value="上网">上网
        </div>
        <div>
            <label for="note">
                说明
            </label>
            <textarea name="note" id="note">
            </textarea>
        </div>
        <div style="padding:10px 100px;">
            <input type="hidden" name="hiddenField" value="hiddenValue">
            <input type="submit" value="提交">
            <input type="reset" value="重置">
        </div>
    </form>
</fieldset>
</body>
</html>
