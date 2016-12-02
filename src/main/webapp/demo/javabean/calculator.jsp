<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--使用calculatorBean--%>
<jsp:useBean id="calcBean" class="com.yckjsoft.javaee.javabean.CalculatorBean"/>
<%--接收用户输入参数--%>
<jsp:setProperty name="calcBean" property="*"/>
<%
    //使用calculatorBean进行计算
    calcBean.calculate();
%>
<html>
<head>
    <title>使用【jsp+javabean开发模式】开发的简单计算器</title>
</head>
<body>
计算结果：<br/>
<jsp:getProperty name="calcBean" property="firstNum"/>
<jsp:getProperty name="calcBean" property="operator"/>
<jsp:getProperty name="calcBean" property="secondNum"/>
=
<jsp:getProperty name="calcBean" property="result"/>

<form method="post" action="/calculator.jsp">
    <table>
        <caption>简单的计算器</caption>
        <tr>
            <td>第一个参数</td>
            <td><input type="text" name="firstNum"></td>
        </tr>
        <tr>
            <td>运算符</td>
            <td>
                <select name="operator">
                    <option value="+">+</option>
                    <option value="-">-</option>
                    <option value="*">*</option>
                    <option value="/">/</option>
                </select>
            </td>
        </tr>
        <tr>
            <td>第二个参数</td>
            <td><input type="text" name="secondNum"></td>
        </tr>
    </table>
    <input type="submit" value="计算"/>
</form>
</body>
</html>
