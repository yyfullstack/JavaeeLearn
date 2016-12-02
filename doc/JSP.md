###什么是JSP
    JSP全称是Java Server Pages，它和servle技术一样，都是SUN公司定义的一种用于开发动态web资源的技术。
    JSP这门技术的最大的特点在于，写jsp就像在写html，但它相比html而言，html只能为用户提供静态数据，
    而Jsp技术允许在页面中嵌套java代码，为用户提供动态数据

###JSP原理
####2.1 Web服务器是如何调用并执行一个jsp页面的？
    浏览器向服务器发请求，不管访问的是什么资源，其实都是在访问Servlet，所以当访问一个jsp页面时，其实也是在访问一个Servlet，
    服务器在执行jsp的时候，首先把jsp翻译成一个Servlet，所以我们访问jsp时，其实不是在访问jsp，
    而是在访问jsp翻译过后的那个Servlet，

    当我们通过浏览器访问index.jsp时，服务器首先将index.jsp翻译成一个index_jsp.class，
    在Tomcat服务器的work\Catalina\localhost\项目名\org\apache\jsp目录下可以看到index_jsp.class的源代码文件index_jsp.java，
    index_jsp.java的代码如下
    index_jsp这个类是继承 org.apache.jasper.runtime.HttpJspBase这个类的
    HttpJspBase类是继承HttpServlet的，所以HttpJspBase类是一个Servlet，
    而index_jsp又是继承HttpJspBase类的，所以index_jsp类也是一个Servlet，所以当浏览器访问服务器上的index.jsp页面时，
    其实就是在访问index_jsp这个Servlet，index_jsp这个Servlet使用_jspService这个方法处理请求

####2.2 Jsp页面中的html排版标签是如何被发送到客户端的？
    在jsp中编写的java代码和html代码都会被翻译到_jspService方法中去，在jsp中编写的java代码会原封不动地翻译成java代码，
    如<%out.print("Hello Jsp");%>直接翻译成out.print("Hello Jsp");，而HTML代码则会翻译成使用out.write("<html标签>\r\n");
    的形式输出到浏览器。在jsp页面中编写的html排版标签都是以out.write("<html标签>\r\n");的形式输出到浏览器，
    浏览器拿到html代码后才能够解析执行html代码。

####2.3、Jsp页面中的java代码服务器是如何执行的？
    在jsp中编写的java代码会被翻译到_jspService方法中去，当执行_jspService方法处理请求时，就会执行在jsp编写的java代码了，
    所以Jsp页面中的java代码服务器是通过调用_jspService方法处理请求时执行的。
####2.4 Web服务器在调用jsp时，会给jsp提供一些什么java对象
    查看_jspService方法可以看到，Web服务器在调用jsp时，会给Jsp提供如下的8个java对象
    PageContext pageContext;
    HttpSession session;
    ServletContext application;
    ServletConfig config;
    JspWriter out;
    Object page = this;
    HttpServletRequest request,
    HttpServletResponse response
####2.5 Jsp最佳实践
    不管是JSP还是Servlet，虽然都可以用于开发动态web资源。但由于这2门技术各自的特点，在长期的软件实践中，
    人们逐渐把servlet作为web应用中的控制器组件来使用，而把JSP技术作为数据显示模板来使用。其原因为，
    程序的数据通常要美化后再输出：让jsp既用java代码产生动态数据，又做美化会导致页面难以维护。让servlet既产生数据，
    又在里面嵌套html代码美化数据，同样也会导致程序可读性差，难以维护。因此最好的办法就是根据这两门技术的特点，
    让它们各自负责各的，servlet只负责响应请求产生数据，并把数据通过转发技术带给jsp，数据的显示jsp来做。

####2.6 Tomcat服务器的执行流程
    第一次执行：
    客户端通过电脑连接服务器，因为是请求是动态的，所以所有的请求交给WEB容器来处理
    在容器中找到需要执行的*.jsp文件
    之后*.jsp文件通过转换变为*.java文件
    *.java文件经过编译后，形成*.class文件
    最终服务器要执行形成的*.class文件

    第二次执行：
    因为已经存在了*.class文件，所以不在需要转换和编译的过程

    修改后执行：
    1.源文件已经被修改过了，所以需要重新转换，重新编译。

###JSP模版元素
    　JSP页面中的HTML内容称之为JSP模版元素。
    　JSP模版元素定义了网页的基本骨架，即定义了页面的结构和外观。
###JSP表达式
    JSP脚本表达式（expression）用于将程序数据输出到客户端
    语法：<%= 变量或表达式 %>
    JSP引擎在翻译脚本表达式时，会将程序数据转成字符串，然后在相应位置用out.print(…) 将数据输给客户端。
    JSP脚本表达式中的变量或表达式后面不能有分号（;）。
###JSP脚本片断
    JSP脚本片断(scriptlet)用于在JSP页面中编写多行Java代码。语法：
　　<%
　　　　多行java代码
　　%>
    在<% %>中可以定义变量、编写语句，不能定义方法。
    JSP脚本片断中只能出现java代码，不能出现其它模板元素， JSP引擎在翻译JSP页面中，
    会将JSP脚本片断中的Java代码将被原封不动地放到Servlet的_jspService方法中。
    JSP脚本片断中的Java代码必须严格遵循Java语法，例如，每执行语句后面必须用分号（;）结束。
    在一个JSP页面中可以有多个脚本片断，在两个或多个脚本片断之间可以嵌入文本、HTML标记和其他JSP元素。

    多个脚本片断中的代码可以相互访问，犹如将所有的代码放在一对<%%>之中的情况。如：out.println(x);
    单个脚本片断中的Java语句可以是不完整的，但是，多个脚本片断组合后的结果必须是完整的Java语句

###JSP声明
    JSP页面中编写的所有代码，默认会翻译到servlet的service方法中， 而Jsp声明中的java代码被翻译到_jspService方法的外面。语法：
　　<%！
　　　　java代码
　　%>
    所以，JSP声明可用于定义JSP页面转换成的Servlet程序的静态代码块、成员变量和方法 。
    多个静态代码块、变量和函数可以定义在一个JSP声明中，也可以分别单独定义在多个JSP声明中。
    JSP隐式对象的作用范围仅限于Servlet的_jspService方法，所以在JSP声明中不能使用这些隐式对象。

###JSP注释
    注释有两大类：
    显式注释：直接使用HTML风格的注释：<!- - 注释内容- ->
    <!--这个注释可以看见-->

    而JAVA注释和JSP注释在浏览器中查看源文件时是看不到注释的内容的
    隐式注释：直接使用JAVA的注释：//、/*……*/
    JSP自己的注释：<%- - 注释内容- -%>

###JSP指令简介
    JSP指令（directive）是为JSP引擎而设计的，它们并不直接产生任何可见输出，而只是告诉引擎如何处理JSP页面中的其余部分。
    在JSP 2.0规范中共定义了三个指令：
    page指令
    Include指令
    taglib指令
    JSP指令的基本语法格式：<%@ 指令 属性名="值" %>

####3.1  Page指令
    page指令用于定义JSP页面的各种属性，无论page指令出现在JSP页面中的什么地方，它作用的都是整个JSP页面，
    为了保持程序的可读性和遵循良好的编程习惯，page指令最好是放在整个JSP页面的起始位置
    page指令的完整语法:
    <%@ page
        [ language="java" ]
        [ extends="package.class" ]
        [ import="{package.class | package.*}, ..." ]
        [ session="true | false" ]
        [ buffer="none | 8kb | sizekb" ]
        [ autoFlush="true | false" ]
        [ isThreadSafe="true | false" ]
        [ info="text" ]
        [ errorPage="relative_url" ]
        [ isErrorPage="true | false" ]
        [ contentType="mimeType [ ;charset=characterSet ]" | "text/html ; charset=ISO-8859-1" ]
        [ pageEncoding="characterSet | ISO-8859-1" ]
        [ isELIgnored="true | false" ]
    %>

#####1  Page指令
    在Jsp页面中，Jsp引擎会自动导入下面的包
    java.lang.*
    javax.servlet.*
    javax.servlet.jsp.*
    javax.servlet.http.*
    可以在一条page指令的import属性中引入多个类或包，其中的每个包或类之间使用逗号(,)分隔
    可以改写为使用多条page指令的import属性来分别引入各个包或类
    <%@ page import="java.util.Date"%>
    <%@ page import="java.io.*" %>
    <%@ page import="java.sql.*" %>
#####2 page指令的errorPage属性
    errorPage属性的设置值必须使用相对路径，如果以“/”开头，表示相对于当前Web应用程序的根目录(注意不是站点根目录)，否则，表示相对于当前页面
    可以在web.xml文件中使用<error-page>元素为整个Web应用程序设置错误处理页面。
    <error-page>元素有3个子元素，<error-code>、<exception-type>、<location>
    <error-code>子元素指定错误的状态码，例如：<error-code>404</error-code>
    <exception-type>子元素指定异常类的完全限定名，例如：<exception-type>java.lang.ArithmeticException</exception-type>
    <location>子元素指定以“/”开头的错误处理页面的路径，例如：<location>/ErrorPage/404Error.jsp</location>
    如果设置了某个JSP页面的errorPage属性，那么在web.xml文件中设置的错误处理将不对该页面起作用。
#####3 使用errorPage属性指明出错后跳转的错误页面
    page指令的errorPage属性指明了出错后跳转到"/ErrorPage/error.jsp"，
#####4 在web.xml中使用<error-page>标签为整个web应用设置错误处理页面
    <!-- 针对404错误的处理页面 -->
       <error-page>
           <error-code>404</error-code>
           <location>/ErrorPage/404Error.jsp</location>
       </error-page>

#####5 关于在web.xml中使用<error-page>标签为整个web应用设置错误处理页面在IE下无法跳转的解决办法
    这里需要注意的是，如果错误页面比较小，那么当访问服务器上不存在的web资源或者访问服务器出错时在IE浏览器下是无法跳转到错误页面的，
    显示的是ie自己的错误页面，而在火狐和google浏览器下(其他浏览器没有测试过)是不存在注意的问题的。

    经过测试，当定制的错误页面的size=617bytes时，在IE8下已经可以跳转到定制的错误页面了，其他版本的IE浏览器没有经过测试，
    不过为了保险起见，定制的错误页面的size最好超过1024bytes

#####6 使用page指令的的isErrorPage属性显式声明页面为错误页面
    如果某一个jsp页面是作为系统的错误处理页面，那么建议将page指令的isErrorPage属性(默认为false)设置为"true"来
    显式声明这个Jsp页面是一个错误处理页面。

    将error.jsp页面显式声明为错误处理页面后，有什么好处呢，好处就是Jsp引擎在将jsp页面翻译成Servlet的时候，在Servlet的 _jspService方法
    中会声明一个exception对象，然后将运行jsp出错的异常信息存储到exception对象中

    由于Servlet的_jspService方法中声明了exception对象，那么就可以在error.jsp页面中使用exception对象，这样就可以在Jsp页面中拿到出错的异常信息了

    如果没有设置isErrorPage="true"，那么在jsp页面中是无法使用exception对象的，因为在Servlet的_jspService方法中不会声明一个exception对象，

    Jsp有9大内置对象，而一般情况下exception对象在Jsp页面中是获取不到的，只有设置page指令的isErrorPage属性为"true"来
    显式声明Jsp页面是一个错误处理页面之后才能够在Jsp页面中使用exception对象

####3.2  include指令
    在JSP中对于包含有两种语句形式：
    @include指令
    <jsp:include>指令

#####1 @include指令
    @include可以包含任意的文件，当然，只是把文件的内容包含进来。
    include指令用于引入其它JSP页面，如果使用include指令引入了其它JSP页面，那么JSP引擎将把这两个JSP翻译成一个servlet。
    所以include指令引入通常也称之为静态引入。
     语法：<%@ include file="relativeURL"%>，其中的file属性用于指定被引入文件的路径。路径以“/”开头，表示代表当前web应用。
    include指令细节注意问题：
1. 被引入的文件必须遵循JSP语法。
2. 被引入的文件可以使用任意的扩展名，即使其扩展名是html，JSP引擎也会按照处理jsp页面的方式处理它里面的内容，
为了见明知意，JSP规范建议使用.jspf（JSP fragments(片段)）作为静态引入文件的扩展名。
3. 由于使用include指令将会涉及到2个JSP页面，并会把2个JSP翻译成一个servlet，所以这2个JSP页面的指令不能冲突（除了pageEncoding和导包除外）。

#####2 总结@include指令
     使用@include可以包含任意的内容，文件的后缀是什么都无所谓。这种把别的文件内容包含到自身页面的@include语句就叫作静态包含，
     作用只是把别的页面内容包含进来，属于静态包含。

#####3 jsp:include指令
    jsp:include指令为动态包含，如果被包含的页面是JSP，则先处理之后再将结果包含，而如果包含的是非*.jsp文件，
    则只是把文件内容静态包含进来，功能与@include类似。

###JSP运行原理
    每个JSP 页面在第一次被访问时，WEB容器都会把请求交给JSP引擎（即一个Java程序）去处理。JSP引擎先将JSP翻译成一个_jspServlet(实质上也是一个servlet) ，然后按照servlet的调用方式进行调用。
    由于JSP第一次访问时会翻译成servlet，所以第一次访问通常会比较慢，但第二次访问，JSP引擎如果发现JSP没有变化，就不再翻译，
    而是直接调用，所以程序的执行效率不会受到影响。
    JSP引擎在调用JSP对应的_jspServlet时，会传递或创建9个与web开发相关的对象供_jspServlet使用。JSP技术的设计者为便于开发人员
    在编写JSP页面时获得这些web对象的引用，特意定义了9个相应的变量，开发人员在JSP页面中通过这些变量就可以快速获得这9大对象的引用。

###认识九个内置对象
    NO.	内置对象	类型
    1	pageContext	javax.servlet.jsp.PageContext
    2	request	javax.servlet.http.HttpServletRequest
    3	response	javax.servlet.http.HttpServletResponse
    4	session	javax.servlet.http.HttpSession
    5	application	javax.servlet.ServletContext
    6	config	javax.servlet.ServletConfig
    7	out	javax.servlet.jsp.JspWriter
    8	page	java.lang.Object
    9	exception	java.lang.Throwable

###内置对象使用说明
 1. page对象
    page对象表示当前一个JSP页面，可以理解为一个对象本身，即：把一个JSP当作一个对象来看待。page对象在开发中几乎不用，了解一下即可
 2. out对象
    out对象用于向客户端发送文本数据。
    out对象是通过调用pageContext对象的getOut方法返回的，其作用和用法与ServletResponse.getWriter方法返回的PrintWriter对象非常相似。
    JSP页面中的out对象的类型为JspWriter，JspWriter相当于一种带缓存功能的PrintWriter，设置JSP页面的page指令的buffer属性可以调整它的缓存大小，
    甚至关闭它的缓存。
    只有向out对象中写入了内容，且满足如下任何一个条件时，out对象才去调用ServletResponse.getWriter方法，
    并通过该方法返回的PrintWriter对象将out对象的缓冲区中的内容真正写入到Servlet引擎提供的缓冲区中：

    设置page指令的buffer属性关闭了out对象的缓存功能
    out对象的缓冲区已满
    整个JSP页面结束

 3. pageContext对象
    pageContext对象是JSP技术中最重要的一个对象，它代表JSP页面的运行环境，这个对象不仅封装了对其它8大隐式对象的引用，
    它自身还是一个域对象(容器)，可以用来保存数据。并且，这个对象还封装了web开发中经常涉及到的一些常用操作，
    例如引入和跳转其它资源、检索其它域对象中的属性等。

 4. 通过pageContext获得其他对象
    getException方法返回exception隐式对象
    getPage方法返回page隐式对象
    getRequest方法返回request隐式对象
    getResponse方法返回response隐式对象
    getServletConfig方法返回config隐式对象
    getServletContext方法返回application隐式对象
    getSession方法返回session隐式对象
    getOut方法返回out隐式对象

 5. pageContext封装其它8大内置对象的意义
    如果在编程过程中，把pageContext对象传递给一个普通java对象，那么这个java对象将可以获取8大隐式对象，
    此时这个java对象就可以和浏览器交互了，此时这个java对象就成为了一个动态web资源了，
    这就是pageContext封装其它8大内置对象的意义，把pageContext传递给谁，谁就能成为一个动态web资源，
    那么什么情况下需要把pageContext传递给另外一个java类呢，什么情况下需要使用这种技术呢，在比较正规的开发中，
    jsp页面是不允许出现java代码的，如果jsp页面出现了java代码，那么就应该想办法把java代码移除掉，
    我们可以开发一个自定义标签来移除jsp页面上的java代码，首先围绕自定义标签写一个java类，
    jsp引擎在执行自定义标签的时候就会调用围绕自定义标签写的那个java类，在调用java类的时候就会把pageContext对象
    传递给这个java类，由于pageContext对象封装了对其它8大隐式对象的引用，因此在这个java类中就可以使用jsp页面中
    的8大隐式对象(request，response，config，application，exception，Session，page，out)了，
    pageContext对象在jsp自定义标签开发中特别重要。

 6. pageContext作为域对象
    pageContext对象可以作为容器来使用，因此可以将一些数据存储在pageContext对象中。
    pageContext对象的常用方法：
    1 public void setAttribute(java.lang.String name,java.lang.Object value)
    2 public java.lang.Object getAttribute(java.lang.String name)
    3 public void removeAttribute(java.lang.String name)
    4 public java.lang.Object findAttribute(java.lang.String name)
    重点介绍一下findAttribute方法，这个方法是用来查找各个域中的属性的，查看这个方法的API可以看到关于这个方法的描述：
    当要查找某个属性时，findAttribute方法按照查找顺序"page→request→session→application"在这四个对象中去查找，
    只要找到了就返回属性值，如果四个对象都没有找到要查找的属性，则返回一个null。

    EL表达式语句在执行时，会调用pageContext.findAttribute方法，用标识符为关键字，分别从page、request、 session、application
    四个域中查找相应的对象，找到则返回相应对象，找不到则返回”” （注意，不是null，而是空字符串）。

    pageContext对象中封装了访问其它域的方法
    1 public java.lang.Object getAttribute(java.lang.String name,int scope)
    2 public void setAttribute(java.lang.String name, java.lang.Object value,int scope)
    3 public void removeAttribute(java.lang.String name,int scope)
    代表各个域的常量
    1 PageContext.APPLICATION_SCOPE
    2 PageContext.SESSION_SCOPE
    3 PageContext.REQUEST_SCOPE
    4 PageContext.PAGE_SCOPE

 7. PageContext引入和跳转到其他资源
    PageContext类中定义了一个forward方法(用来跳转页面)和两个include方法(用来引入页面)来分别简化和替代RequestDispatcher.forward方法和include方法。
    方法接收的资源如果以“/”开头， “/”代表当前web应用。

    这种写法是用来简化和替代pageContext.getRequest().getRequestDispatcher("/pageContextDemo05.jsp").forward(request, response);
    这种写法的。在实际开发中，使用pageContext.forward(relativeUrlPath)方法跳转页面用得不多，主要是因为要在Jsp页面中嵌套java代码，
    所以这种做法简单了解一下即可，在开发中，要想从一个Jsp页面采用服务器端跳转的方式跳转到另一个Jsp页面，
    那么一般会使用<jsp:forward>标签，<jsp:forward>标签用于把请求转发给另外一个资源。
    使用pageContext的include方法引入页面这种做法也很少用，一般都使用jsp:include标签引入资源，因此这种做法了解一下即可

###JSP属性范围
   JSP中提供了四种属性范围，四种属性范围分别指以下四种：
   当前页：一个属性只能在一个页面中取得，跳转到其他页面无法取得
   一次服务器请求：一个页面中设置的属性，只要经过了服务器跳转，则跳转之后的页面可以继续取得。
   一次会话：一个用户设置的内容，只要是与此用户相关的页面都可以访问（一个会话表示一个人，
   这个人设置的东西只要这个人不走，就依然有效）
   上下文中：在整个服务器上设置的属性，所有人都可以访问

###属性的操作方法
    既然JSP中提供了四种属性范围，则四种属性范围中都将包含以下的属性操作方法。
1. public void setAttribute(String name,Object value) 设置属性
2. public object getAttribute(String name)取得属性
3. public void removeAttribute(String name) 删除属性

单词Attribute的意思是“属性”，setAttribute(String name,Object value)从单词的组合来看就可以知道是这个方法的是设置属性，
设置属性的名字和属性的值，名字(name)为String类型，值(value)为Object类型，由于值为Object类型，这表示可以设置任意类型的数据作为值，
因为所有的类都是从Object类型继承而来。因此设置属性值的时候可以是任意类型的数据。getAttribute(String name)方法是根据属性的名字取得属性，
removeAttribute(String name)方法是根据属性的名字删除属性。

###JSP四种属性范围的具体介绍
#### 1. page属性范围（pageContext）
    page属性范围相对好理解一些：在一个页面设置的属性，跳转到其他页面就无法访问了。但是在使用page属性范围的时候必须注意的是，
    虽然习惯上将页面范围的属性称为page范围，但是实际上操作的时候是使用pageContext内置对象完成的。
    pageContext从字面上的定义，可以发现，是表示一个页面(page)的上下文(Context)，可以表示一个页面中的所有内容。
    从操作流程图来看，在第一个页面设置的属性经过服务器端跳转到第二个页面以后，在第二个页面是无法取得在第一个页面中设置的属性的，
    就好比现在坐着的桌子上有一支笔，但一旦离开了这张桌子，坐到别的桌子上时，笔就没有了。

    使用了服务器端跳转，但是发现内容并不能取得，证明page范围的属性只能在本页中取得，跳转到其他页面之中不能取得。如果现在希望跳转到其他页面之中，
    依然可以取得，则可以扩大属性范围，使用request属性范围即可。
####2. request属性范围
    request属性范围表示在一次服务器跳转中有效，只要是服务器跳转，则设置的request属性可以一直传递下去。
    此时使用了超链接跳转，一旦跳转之后，地址栏改变，所以此种跳转也可以称为客户端跳转。
    requestScopeDemo04.jsp页面显示的结果是null。这说明了在requestScopeDemo01.jsp这个页面设置的属性经过超链接这种
    客户端跳转到别的页面时别的页面是无法取得requestScopeDemo01.jsp中设置的属性的。
####3. session属性范围
    session设置的属性不管如何跳转，都可以取得的。当然，session只针对一个用户
    跳转(服务器跳转/客户端跳转)到其他页面之后，其他的页面依然可以取得第一个页面上设置的属性。
    这说明了即使是采用客户端跳转，在别的页面依然可以取得第一个页面中设置的session属性。但是，如果，此时新开了一个浏览器，
    则sessionScopeDemo03.jsp肯定无法取得sessionScopeDemo01.jsp中设置的session对象的属性，因为session只是保留了一个人的信息。
####4. application属性范围
    因为application属性范围是在服务器上设置的一个属性，所以一旦设置之后任何用户都可以浏览到此属性。
    开启多个浏览器窗口，运行applicationScopeDemo02.jsp时，都可以显示出上图所示的结果，因为属性范围设置在了服务器中，
    所以只要是连接到此服务器的任意用户都可以取得此属性，当然，如果服务器关闭的话，则此属性肯定消失。
    注意：如果在服务器上设置了过多的application属性，则会影响到服务器的性能。

####5. 关于pageContext属性范围的进一步补充
    PageContext类继承了JspContext类，所以在PageContext类中实现了抽象的setAttribute方法：
    public abstract void setAttribute(String name,Object value,int scope)
    这个setAttribute()方法如果不写后面的int类型的scope参数，则此参数默认为PAGE_SCOPE，则此时setAttribute()方法设置的就是page属性范围，
    如果传递过来的int类型参数scope为REQUEST_SCOPE，则此时setAttribute()方法设置的就是request属性范围，同理，
    传递的scope参数为SESSION_SCOPE和APPLICATION_SCOPE时，则表示setAttribute()方法设置的就是session属性范围和application属性范围。
###jsp四种属性范围的使用场合
   1. request：如果客户向服务器发请求，产生的数据，用户看完就没用了，像这样的数据就存在request域,
   像新闻数据，属于用户看完就没用的。
   2. session：如果客户向服务器发请求，产生的数据，用户用完了等一会儿还有用，
   像这样的数据就存在session域中，像购物数据，用户需要看到自己购物信息，并且等一会儿，还要用这个购物数据结帐。
   3. application(servletContext)：如果客户向服务器发请求，产生的数据，用户用完了，还要给其它用户用，
   像这样的数据就存在application(servletContext)域中，像聊天数据

###JSP标签介绍
    JSP标签也称之为Jsp Action(JSP动作)元素，它用于在Jsp页面中提供业务逻辑功能，避免在JSP页面中直接编写java代码，
    造成jsp页面难以维护

###JSP常用标签
   jsp的常用标签有以下三个
   <jsp:include>标签
   <jsp:forward>标签
   <jsp:param>标签

1. <jsp:include>标签
   <jsp:include>标签用于把另外一个资源的输出内容插入进当前JSP页面的输出内容之中，这种在JSP页面执行时的引入方式称之为动态引入。
   语法：
   <jsp:include page="relativeURL | <%=expression%>" flush="true|false" />
   page属性用于指定被引入资源的相对路径，它也可以通过执行一个表达式来获得。
   flush属性指定在插入其他资源的输出内容时，是否先将当前JSP页面的已输出的内容刷新到客户端。
2. <jsp:include>标签与include指令的区别
   <jsp:include>标签是动态引入， <jsp:include>标签涉及到的2个JSP页面会被翻译成2个servlet，这2个servlet的内容在执行时进行合并。
   而include指令是静态引入，涉及到的2个JSP页面会被翻译成一个servlet，其内容是在源文件级别进行合并。
3. *.jspf扩展名文件在jsp:include、@include和c:import中的区别
   JSP规范建议使用.jspf（JSP fragments）作为静态引入文件的扩展名。今天无意中发现，把一个JSP文件命名为jspf扩展名，
   然后include到另一个jsp文件中的，发现只有用"@include"指令的时候，jspf文件的内容才会被解析并执行其中的jsp指令和tag，
   而使用"jsp:include"和JSTL的"c:import"都没有用，jspf文件被当作纯文本文件处理了。

   解决办法一：修改web.xml文件，添加对扩展名为*.jspf文件的映射
   解决办法二：修改Tomcat服务器的web.xml文件，添加对扩展名为*.jspf文件的映射
        名字为jsp的那个Servlet只支持*.jsp和*.jspx两种扩展名，因此可以在这个地方添加多一个<url-pattern>*.jspf</url-pattern>

4. <jsp:forward>标签
   <jsp:forward>标签用于把请求转发给另外一个资源。
   语法：<jsp:forward page="relativeURL | <%=expression%>" />
   page属性用于指定请求转发到的资源的相对路径，它也可以通过执行一个表达式来获得。
   从页面的显示效果来看，页面已经完成了跳转，但地址栏没有变化，地址栏中显示的地址还是forwarddemo01.jsp，
   但页面显示的内容却是forwardemo02.jsp中的内容。因为此跳转属于服务器端跳转。只要是服务器端跳转，则地址栏永远没有变化。
5. <jsp:param>标签
   当使用<jsp:include>和<jsp:forward>标签引入或将请求转发给其它资源时，可以使用<jsp:param>标签向这个资源传递参数。
　　语法1：
   <jsp:include page="relativeURL | <%=expression%>">
       <jsp:param name="parameterName" value="parameterValue|<%= expression %>" />
   </jsp:include>
　　语法2：
   <jsp:forward page="relativeURL | <%=expression%>">
       <jsp:param name="parameterName" value="parameterValue|<%= expression %>" />
   </jsp:include>
   <jsp:param>标签的name属性用于指定参数名，value属性用于指定参数值。在<jsp:include>和<jsp:forward>标签中可以
   使用多个<jsp:param>标签来传递多个参数。