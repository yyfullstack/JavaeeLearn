###EL表达式简介
　　EL 全名为Expression Language。EL主要作用：
　　1、获取数据
　　　　EL表达式主要用于替换JSP页面中的脚本表达式，以从各种类型的web域 中检索java对象、获取数据。(某个web域 中的对象，
        访问javabean的属性、访问list集合、访问map集合、访问数组)
　　2、执行运算
　　　　利用EL表达式可以在JSP页面中执行一些基本的关系运算、逻辑运算和算术运算，以在JSP页面中完成一些简单的逻辑运算。${user==null}
　　3、获取web开发常用对象
　　　　EL 表达式定义了一些隐式对象，利用这些隐式对象，web开发人员可以很轻松获得对web常用对象的引用，从而获得这些对象中的数据。
　　4、调用Java方法
　　　　EL表达式允许用户开发自定义EL函数，以在JSP页面中通过EL表达式调用Java类的方法。
####1.1、获取数据
　　使用EL表达式获取数据语法："${标识符}"
　　EL表达式语句在执行时，会调用pageContext.findAttribute方法，用标识符为关键字，分别从page、request、session、application四个域中查找相应的对象，找到则返回相应对象，找不到则返回”” （注意，不是null，而是空字符串）。

　　EL表达式可以很轻松获取JavaBean的属性，或获取数组、Collection、Map类型集合的数据

####1.2、执行运算
    语法：${运算表达式}，EL表达式支持如下运算符：
   1、关系运算符
   2、逻辑运算符：
   3、empty运算符：检查对象是否为null(空)
   4、二元表达式：${user!=null?user.name :""}
   5、[ ] 和 . 号运算符

####1.3、获得web开发常用对象
    EL表达式语言中定义了11个隐含对象，使用这些隐含对象可以很方便地获取web开发中的一些常见对象，并读取这些对象的数据。
    语法：${隐式对象名称}：获得对象的引用

    1	pageContext	对应于JSP页面中的pageContext对象（注意：取的是pageContext对象。）
    2	pageScope	代表page域中用于保存属性的Map对象
    3	requestScope	代表request域中用于保存属性的Map对象
    4	sessionScope	代表session域中用于保存属性的Map对象
    5	applicationScope	代表application域中用于保存属性的Map对象
    6	param	表示一个保存了所有请求参数的Map对象
    7	paramValues	表示一个保存了所有请求参数的Map对象，它对于某个请求参数，返回的是一个string[]
    8	header	表示一个保存了所有http请求头字段的Map对象，注意：如果头里面有“-” ，例Accept-Encoding，则要header[“Accept-Encoding”]
    9	headerValues	表示一个保存了所有http请求头字段的Map对象，它对于某个请求参数，返回的是一个string[]数组。注意：
        如果头里面有“-” ，例Accept-Encoding，则要headerValues[“Accept-Encoding”]
    10	cookie	表示一个保存了所有cookie的Map对象
    11	initParam	表示一个保存了所有web应用初始化参数的map对象

    注意:
    　　测试header和headerValues时，如果头里面有“-” ，例Accept-Encoding，则要header["Accept-Encoding"]、headerValues["Accept-Encoding"]
    　　测试cookie时，例${cookie.key}取的是cookie对象，如访问cookie的名称和值，须${cookie.key.name}或${cookie.key.value}
####1.4、使用EL调用Java方法
    　　EL表达式语法允许开发人员开发自定义函数，以调用Java类的方法。语法：${prefix：method(params)}
    　　在EL表达式中调用的只能是Java类的静态方法，这个Java类的静态方法需要在TLD文件中描述，才可以被EL表达式调用。
    　　EL自定义函数用于扩展EL表达式的功能，可以让EL表达式完成普通Java程序代码所能完成的功能。

####1.5、EL Function开发步骤
    　　一般来说， EL自定义函数开发与应用包括以下三个步骤：
    　　1、编写一个Java类的静态方法
    　　2、编写标签库描述符（tld）文件，在tld文件中描述自定义函数。
    　　3、在JSP页面中导入和使用自定义函数
####1.6、开发EL Function注意事项
    　　编写完标签库描述文件后，需要将它放置到<web应用>\WEB-INF目录中或WEB-INF目录下的除了classes和lib目录之外的任意子目录中。
    　　TLD文件中的<uri> 元素用指定该TLD文件的URI，在JSP文件中需要通过这个URI来引入该标签库描述文件。
    　　<function>元素用于描述一个EL自定义函数，其中：
    　　<name>子元素用于指定EL自定义函数的名称。
    　　<function-class>子元素用于指定完整的Java类名，
    　　<function-signature>子元素用于指定Java类中的静态方法的签名，方法签名必须指明方法的返回值类型及各个参数的类型，
    各个参数之间用逗号分隔。
####1.7、EL注意事项
    　　EL表达式是JSP 2.0规范中的一门技术 。因此，若想正确解析EL表达式，需使用支持Servlet2.4/JSP2.0技术的WEB服务器。
    注意：有些Tomcat服务器如不能使用EL表达式
        （1）升级成tomcat6
        （2）在JSP中加入<%@ page isELIgnored="false" %>

###EL函数库介绍
   　　由于在JSP页面中显示数据时，经常需要对显示的字符串进行处理，SUN公司针对于一些常见处理定义了一套EL函数库供开发者使用。
   　　这些EL函数在JSTL开发包中进行描述，因此在JSP页面中使用SUN公司的EL函数库，需要导入JSTL开发包，并在页面中导入EL函数库，如下所示：