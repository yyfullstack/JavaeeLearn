<%@page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="yc" uri="/yctag" %>
<html>
<head>
    <title>foreach标签测试</title>
</head>
<%
    //list集合
    List<String> listData = new ArrayList<String>();
    listData.add("jack");
    listData.add("lily");
    listData.add("tom");

    //对象数组
    Integer intObjArr[] = new Integer[]{1, 2, 3};

    //基本数据类型数组
    int intArr[] = new int[]{4, 5, 6};

    //map集合
    Map<String, String> mapData = new HashMap<String, String>();
    mapData.put("a", "aaaaaa");
    mapData.put("b", "bbbbbb");
    mapData.put("c", "cccccc");
    mapData.put("d", "dddddd");


    //将集合存储到pageContext对象中
    pageContext.setAttribute("listData", listData);
    pageContext.setAttribute("intObjArr", intObjArr);
    pageContext.setAttribute("intArr", intArr);
    pageContext.setAttribute("mapData", mapData);

%>
<body>
<%--迭代存储在pageContext对象中的list集合 --%>
<yc:foreach2 items="${listData}" var="str">
    ${str}<br/>
</yc:foreach2>
<hr/>
<%--迭代存储在pageContext对象中的数组 --%>
<yc:foreach2 items="${intObjArr}" var="num">
    ${num}<br/>
</yc:foreach2>
<hr/>
<%--迭代存储在pageContext对象中的数组 --%>
<yc:foreach2 items="${intArr}" var="num">
    ${num}<br/>
</yc:foreach2>
<hr/>
<%--迭代存储在pageContext对象中的map集合 --%>
<yc:foreach2 items="${mapData}" var="me">
    ${me}<br/>
</yc:foreach2>
</body>
</html>
