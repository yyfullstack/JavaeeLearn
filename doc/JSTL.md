###JSTL标签库介绍　　
    JSTL标签库的使用是为弥补html标签的不足，规范自定义标签的使用而诞生的。使用JSLT标签的目的就是不希望在jsp页面中出现java逻辑代码

###JSTL标签库的分类
   核心标签(用得最多)
   国际化标签(I18N格式化标签)
   数据库标签(SQL标签，很少使用)
   XML标签(几乎不用)
   JSTL函数(EL函数)

###核心标签库使用说明
   JSTL的核心标签库标签共13个，使用这些标签能够完成JSP页面的基本功能，减少编码工作。

　　从功能上可以分为4类：表达式控制标签、流程控制标签、循环标签、URL操作标签。
　　　　（1）表达式控制标签：out标签、set标签、remove标签、catch标签。
　　　　（2）流程控制标签：if标签、choose标签、when标签、otherwise标签。
　　　　（3）循环标签：forEach标签、forTokens标签。
　　　　（4）URL操作标签：import标签、url标签、redirect标签、param标签。
　　在JSP页面引入核心标签库的代码为：<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

####3.1、表达式控制标签——out标签使用总结
 1、<c:out>标签的功能
 　　<c:out>标签主要是用来输出数据对象（字符串、表达式）的内容或结果。
 　　在使用Java脚本输出时常使用的方式为： <% out.println(“字符串”)%> 或者 <%=表达式%> ，在web开发中，为了避免暴露逻辑代码
 会尽量减少页面中的Java脚本，使用<c:out>标签就可以实现以上功能。
     <c:out value=”字符串”>
     <c:out value=”EL表达式”>
 　　JSTL的使用是和EL表达式分不开的，EL表达式虽然可以直接将结果返回给页面，但有时得到的结果为空，<c:out>有特定的结果处理功能，
 EL的单独使用会降低程序的易读性，建议把EL的结果输入放入<c:out>标签中。
2、<c:out>标签的语法
　　<c:out>标签的使用有两种语法格式：
　　　　【语法1】：<c:out value=”要显示的数据对象” [escapeXml=”true|false”] [default=”默认值”]/>
　　　　【语法2】：<c:out value=”要显示的数据对象” [escapeXml=”true|false”]>默认值</c:out>
　　这两种方式没有本质的区别，只是格式上的差别。[escapeXml=”true|false”] [default=”默认值”]这些使用[]属性表示是不是必须的。
3、<c:out>标签的属性
    value： 支持EL表达式， 属性类型为Object， 指定要输出的内容
    escapeXml： 支持EL表达式， 属性类型为Boolean， 指定是否将<,>，&等特殊字符进行HTML编码转换后再进行输出，默认为true
    default： 支持EL表达式， 属性类型为Object， 指定如果value属性的值为null时，所输出的默认值
####3.2、表达式控制标签——set标签使用总结
 1、<c:set>标签的功能
 　　<c:set>标签用于把某一个对象存在指定的域范围内，或者将某一个对象存储到Map或者JavaBean对象中。
 2、<c:set>标签的语法
 　　<c:set>标签的编写共有4种语法格式。
 　　语法1：存值，把一个值放在指定的域范围内。
 　　　　<c:set value=”值1” var=”name1” [scope=”page|request|session|application”]/>
 　　　　含义：把一个变量名为name1值为“值1”的变量存储在指定的scope范围内。
 　　语法2：
 　　　　<c:set var=”name2” [scope=”page|request|session|application”]>
 　　　　　　值2
 　　　　</c:set>
 　　　　含义：把一个变量名为name2，值为值2的变量存储在指定的scope范围内。
 　　语法3：
 　　　　<c:set value=”值3” target=”JavaBean对象” property=”属性名”/>
 　　　　含义：把一个值为“值3”赋值给指定的JavaBean的属性名。相当与setter()方法。
 　　语法4：
 　　　　<c:set target=”JavaBean对象” property=”属性名”>
 　　　　　　值4
 　　　　</c:set>
 　　　　含义：把一个值4赋值给指定的JavaBean的属性名。
 　　从功能上分语法1和语法2、语法3和语法4的效果是一样的，只是把value值放置的位置不同，至于使用那个根据个人的喜爱，
    语法1和语法2是向scope范围内存储一个值，语法3和语法4是给指定的JavaBean赋值。
 3、<c:set>标签的属性
    value： 支持EL表达式， 属性类型为Object， 指定要属性值
    var： 不支持EL表达式， 属性类型为String， 指定要设置的web域属性的名称
    scope:不支持EL表达式， 属性类型为String, 指定属性所在的web域
    target： 支持EL表达式， 属性类型为Object， 指定要属性的对象，这个对象必须是javabean对象或java.util.Map对象
    property： 支持EL表达式， 属性类型为String， 指定要设置当前要为对象设置的属性名称

####3.3、表达式控制标签——remove标签使用总结

1、<c:remove>标签的功能
　　<c:remove>标签主要用来从指定的JSP范围内移除指定的变量。
2、<c:remove>标签的语法
　　<c:remove var=”变量名” [scope=”page|request|session|application”]/>
　　其中var属性是必须的，scope可以以省略。

####3.4、表达式控制标签——catch标签使用总结
1、<c:catch>标签的功能
　　<c:catch>标签用于捕获嵌套在标签体中的内容抛出的异常。
2、<c:catch>标签的语法
　　其语法格式如下：<c:catch [var="varName"]>容易产生异常的代码</c:catch>
　　var属性用于标识<c:catch>标签捕获的异常对象，它将保存在page这个Web域中。

####3.5、流程控制标签——if标签使用总结
1、<c:if>标签的功能
　　<c:if>标签和程序中的if语句作用相同，用来实现条件控制。
2、<c:if>标签的语法
　　【语法1】：没有标签体内容(body)
　　　　<c:if test="testCondition" var="varName" [scope="{page|request|session|application}"]/>
　　【语法2】：有标签体内容
　　　　<c:if test="testCondition" [var="varName"] [scope="{page|request|session|application}"]>
    　　　　　　标签体内容
　　　　</c:if>
　　【参数说明】：
　　　　（1）test属性用于存放判断的条件，一般使用EL表达式来编写。
　　　　（2）var属性用来存放判断的结果，类型为true或false。
　　　　（3）scopes属性用来指定var属性存放的范围。
3、<c:if>标签的属性
    test： 支持EL表达式， 属性类型为boolean， 决定是否处理标签体中的内容的条件表达式
    var： 不支持EL表达式， 属性类型为String， 指定要将test属性的执行结果保存到某个web域中的某个属性的名称的名称
    scope:不支持EL表达式， 属性类型为String, 指定将test属性的执行结果保存到哪个web域中
####3.6、流程控制标签——choose标签、when标签、otherwise标签配合使用讲解
1、<c:choose>、<c:when>和<c:otherwise>标签的功能
　 <c:choose>、<c:when>和<c:otherwise>这3个标签通常情况下是一起使用的，<c:choose>标签作为<c:when>和<c:otherwise>标签的父标签来使用。
　　使用<c:choose>，<c:when>和<c:otherwise>三个标签，可以构造类似 “if-else if-else” 的复杂条件判断结构。
2、语法
　<c:choose>
         <c:when test="条件1">
　　　　　　//业务逻辑1
         <c:when>
　　　<c:when test="条件2">
　　　　　　//业务逻辑2
         <c:when>
　　　<c:when test="条件n">
　　　　　　//业务逻辑n
         <c:when>
         <c:otherwise>
　　　　　　//业务逻辑
　　　</c:otherwise>
　</c:choose>
####3.7、循环标签——forEach标签使用总结
1、<c:forEach>标签的功能
　　该标签根据循环条件遍历集合（Collection）中的元素。
2、<c:forEach>标签的语法

  　<c:forEach
      　　var=”name”
      　　items=”Collection”
      　　varStatus=”StatusName”
      　　begin=”begin”
      　　end=”end”
      　　step=”step”>
      本体内容
  </c:forEach>
  【参数解析】：
  　　（1）var设定变量名用于存储从集合中取出元素。
  　　（2）items指定要遍历的集合。
  　　（3）varStatus设定变量名，该变量用于存放集合中元素的信息。
  　　（4）begin、end用于指定遍历的起始位置和终止位置（可选）。
  　　（5）step指定循环的步长。

  3、<c:forEach>标签属性
  循环标签属性说明
  属性名称	是否支持EL表达式	属性类型	是否必须	默认值
  var	NO	String	是	无
  items	YES	Arrays，Collection，Iterator，Enumeration，Map，String []args，是	无
  begin	YES	int	否	0
  end	YES	int	否	集合中最后一个元素
  step	YES	int	否	1
  varStatus	NO	String	否	无

  其中varStatus有4个状态属性，如下表所示：
  varStatus的4个状态
  属性名	类型	说明
  index	int	当前循环的索引值
  count	int	循环的次数
  frist	boolean	是否为第一个位置
  last	boolean	是否为最后一个位置

####3.8、循环标签——forTokens标签使用总结
1、<c:forTokens>标签的功能
　　该标签用于浏览字符串，并根据指定的字符将字符串截取。
2、<c:forTokens>标签的语法
语法：
<c:forTokens items=”strigOfTokens”
            delims=”delimiters”
            [var=”name”
            begin=”begin”
            end=”end”
            step=”len”
            varStatus=”statusName”] >
    本体内容
</c:forTokens>
【参数说明】
　　（1）items指定被迭代的字符串。
　　（2）delims指定使用的分隔符。
　　（3）var指定用来存放遍历到的成员。
　　（4）begin指定遍历的开始位置（int型从取值0开始）。
　　（5）end指定遍历结束的位置（int型，默认集合中最后一个元素）。
　　（6）step遍历的步长（大于0的整型）。
　　（7）varStatus存放遍历到的成员的状态信息

####3.9、URL操作标签——import标签使用讲解
1、<c:import>标签的功能
　 该标签可以把其他静态或动态文件包含到本JSP页面，与<jsp:include>的区别为：<jsp:include>只能包含同一个web应用中的文件。
而<c:import>可以包含其他web应用中的文件，甚至是网络上的资源。
2、<c:import>标签的语法
【语法1】：
<c:import
    url=”url”
    [context=”context”]
    [value=”value”]
    [scope=”page|request|session|application”]
    [charEncoding=”encoding”]/>

【语法2】：
<c:import
    url=”url”
    varReader=”name”
    [context=”context”]
    [charEncoding=”encoding”]/>

【参数说明】：
（1）URL为资源的路径，当引用的资源不存在时系统会抛出异常，因此该语句应该放在<c:catch></c:catch>语句块中捕获。
（2）引用资源有两种方式：绝对路径和相对路径。
    使用绝对路径的示例如下：<c:import url=”http://www.baidu.com”>
    使用相对路径的示例如下：<c:import url=”aa.txt”>，aa.txt放在同一文件目录。
（3）如果以“/”开头表示应用的根目录下。例如：tomcat应用程序的根目录文件夹为webapps。导入webapps下的文件bb.txt的编写方式为：<c:import url=”/bb.txt”>
    如果访问webapps管理文件夹中其他web应用就要用context属性。
（4）context属性用于在访问其他web应用的文件时，指定根目录。例如，访问root下的index.jsp的实现代码为：<c:import url=”/index.jsp” context=”/root”>
    等同于webapps/root/index.jsp
（5）var、scope、charEncoding、varReader是可选属性。

####3.10、URL操作标签——url标签使用总结
1、<c:url>标签的功能
　　<c:url>标签用于在JSP页面中构造一个URL地址，其主要目的是实现URL重写。
2、<c:url>标签的语法
【语法1】：指定一个url不做修改，可以选择把该url存储在JSP不同的范围中。
<c:url
    value=”value”
    [var=”name”]
    [scope=”page|request|session|application”]
    [context=”context”]/>
【语法2】：配合 <c:param>标签给url加上指定参数及参数值，可以选择以name存储该url。
<c:url
    value=”value”
    [var=”name”]
    [scope=”page|request|session|application”]
    [context=”context”]>
    　　<c:param name=”参数名” value=”值”>
</c:url>

3、<c:url>标签的主要属性
    value： 支持EL表达式， 属性类型为String， 指定要构造的url
    var： 不支持EL表达式， 属性类型为String， 指定要将构造出的url结果保存到某个web域中的某个属性的名称的名称
    scope:不支持EL表达式， 属性类型为String, 指定将构造出的url结果保存到哪个web域中

####3.11、URL操作标签——redirect标签使用总结
1、<c:redirect>标签的功能
　　该标签用来实现请求的重定向。同时可以配合使用<c:param>标签在url中加入指定的参数。
2、<c:redirect>标签的语法
【语法1】：
<c:redirect url=”url” [context=”context”]/>
【语法2】：
<c:redirect url=”url”[context=”context”]>
    <c:param name=”name1” value=”value1”>
</c:redirect>
【参数说明】：
（1）url指定重定向页面的地址，可以是一个string类型的绝对地址或相对地址。
（2）context用于导入其他web应用中的页面。

3、<c:url>标签的主要属性
    url： 支持EL表达式， 属性类型为String， 指定要转发或重定向到的目标资源的url地址
    content： 支持EL表达式， 属性类型为String， 当要使用相对路径重定向到同一个服务器下的其他web应用程序中的资源是，
                            content属性指向其他web应用程序的名称

####3.12、<c:param>标签使用总结
   在JSP页面进行URL的相关操作时，经常要在URL地址后面附加一些参数。<c:param>标签可以嵌套在<c:import>、<c:url>或<c:redirect>标签内，
   为这些标签所使用的URL地址附加参数。　　<c:param>标签在为一个URL地址附加参数时，将自动对参数值进行URL编码，例如，
   如果传递的参数值为“中国”， 则将其转换为“%d6%d0%b9%fa”后再附加到URL地址后面，这也就是使用<c:param>标签的最大好处。

示例1：与<c:url>标签嵌套使用
<c:url value="http://www.baidu.com" var="paramUrl">
    <c:param name="userName" value="孤傲苍狼"/>
   <c:param name="pwd">123456</c:param>
</c:url>
<a href="${paramUrl}">百度首页(带参数)</a>

示例2：与<c:redirect>标签嵌套使用
<c:redirect url="http://www.baidu.com">
    <%--在重定向时使用<c:param>标签为URL添加了两个参数：uname=GACL和password=123 --%>
    <c:param name="uname">GACL</c:param>
    <c:param name="password">123</c:param>
</c:redirect>