<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%--<%@page isELIgnored="false"%>--%>
<%!
    int i = 10;
%>
<h1>includeDemo2.jsp中i的值为：<%=i %></h1>


<h1>
    <jsp:include page="jsp_fragments/include_part.jsp"/>

    <%--发现结果已经可以正确地显示，而且不会互相影响，这是因为使用jsp:include属于动态包含，动态包含就是指先将各个页面分别处理，
    处理完之后再将处理后的结果包含进来。
    不管是<jsp:include>标签，还是include指令，它们都会把两个JSP页面内容合并输出，所以这两个页面不要出现重复的HTML全局架构标签，
    否则输出给客户端的内容将会是一个格式混乱的HTML文档--%>
</h1>