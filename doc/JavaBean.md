###什么是JavaBean
    JavaBean是一个遵循特定写法的Java类，它通常具有如下特点：
    这个Java类必须具有一个无参的构造函数
    属性必须私有化。
    私有化的属性必须通过public类型的方法暴露给其它程序，并且方法的命名也必须遵守一定的命名规范

    JavaBean在J2EE开发中，通常用于封装数据，对于遵循以上写法的JavaBean组件，其它程序可以通过反射技术实例化JavaBean对象，
    并且通过反射那些遵守命名规范的方法，从而获知JavaBean的属性，进而调用其属性保存数据。

###JavaBean的属性
    JavaBean的属性可以是任意类型，并且一个JavaBean可以有多个属性。每个属性通常都需要具有相应的setter、 getter方法，
    setter方法称为属性修改器，getter方法称为属性访问器。
    属性修改器必须以小写的set前缀开始，后跟属性名，且属性名的第一个字母要改为大写，例如，name属性的修改器名称为setName，
    password属性的修改器名称为setPassword。
    属性访问器通常以小写的get前缀开始，后跟属性名，且属性名的第一个字母也要改为大写，例如，name属性的访问器名称为getName，
    password属性的访问器名称为getPassword。
    一个JavaBean的某个属性也可以只有set方法或get方法，这样的属性通常也称之为只写、只读属性。
###在JSP中使用JavaBean
    JSP技术提供了三个关于JavaBean组件的动作元素，即JSP标签，它们分别为：
   <jsp:useBean>标签：用于在JSP页面中查找或实例化一个JavaBean组件。
   <jsp:setProperty>标签：用于在JSP页面中设置一个JavaBean组件的属性。
   <jsp:getProperty>标签：用于在JSP页面中获取一个JavaBean组件的属性。
1. <jsp:useBean>标签
   <jsp:useBean>标签用于在指定的域范围内查找指定名称的JavaBean对象，如果存在则直接返回该JavaBean对象的引用，
   如果不存在则实例化一个新的JavaBean对象并将它以指定的名称存储到指定的域范围中。
　　常用语法：
   <jsp:useBean id="beanName" class="package.class" scope="page|request|session|application"/>
　　"id"属性用于指定JavaBean实例对象的引用名称和其存储在域范围中的名称。
　　"class"属性用于指定JavaBean的完整类名（即必须带有包名）。
　　"scope"属性用于指定JavaBean实例对象所存储的域范围，其取值只能是page、request、session和application等四个值中的一个，其默认值是page。
2. <jsp:useBean>执行原理
    上面我们在index.jsp中使用<jsp:useBean id="person" class="com.yckjsoft.javaee.javabean.Person" scope="page"/>实例化了一个
    com.yckjsoft.javaee.javabean.Person类的对象，那么这个peson对象是怎么实例化出来的呢？javaBeanDemo01.jsp在执行的过程中首先会翻译成一个servlet，
    因此我们可以通过查看javaBeanDemo01.jsp页面生成的servlet的java代码来查看peson对象的实例化过程
    在javaBeanDemo01.jsp中使用<jsp:useBean id="person" class="com.yckjsoft.javaee.javabean.Person" scope="page"/>来实例化
    person对象的过程实际上是执行了上述的java代码来实例化Person对象。这就是<jsp:useBean>标签的执行原理："首先在指定的域范围内
    查找指定名称的JavaBean对象，如果存在则直接返回该JavaBean对象的引用，如果不存在则实例化一个新的JavaBean对象并将它以指定
    的名称存储到指定的域范围中。
3. 带标签体的<jsp:useBean>标签
    语法：
        <jsp:useBean ...>
            Body
        </jsp:useBean>
    功能：
        Body部分的内容只在<jsp:useBean>标签创建JavaBean的实例对象时才执行。这种做法用得不多，了解一下即可
4. <jsp:setProperty>标签
   <jsp:setProperty>标签用于设置和访问JavaBean对象的属性。
　　语法格式一：
　　　　<jsp:setProperty name="beanName" property="propertyName" value="string字符串"/>
　　语法格式二：
　　　　<jsp:setProperty name="beanName" property="propertyName" value="<%= expression %>" />
　　语法格式三：
　　　　<jsp:setProperty name="beanName" property="propertyName" param="parameterName"/>
　　语法格式四：
　　　　<jsp:setProperty name="beanName" property= "*" />
   　　name属性用于指定JavaBean对象的名称。
   　　property属性用于指定JavaBean实例对象的属性名。
   　　value属性用于指定JavaBean对象的某个属性的值，value的值可以是字符串，也可以是表达式。为字符串时，该值会自动转化为JavaBean属性相应的类型，
   如果value的值是一个表达式，那么该表达式的计算结果必须与所要设置的JavaBean属性的类型一致。
   　　param属性用于将JavaBean实例对象的某个属性值设置为一个请求参数值，该属性值同样会自动转换成要设置的JavaBean属性的类型。
5. <jsp:getProperty>标签
   <jsp:getProperty>标签用于读取JavaBean对象的属性，也就是调用JavaBean对象的getter方法，然后将读取的属性值转换成字符串后插入进输出的响应正文中。
　　语法：
   　　<jsp:getProperty name="beanInstanceName" property="PropertyName" />
　　　　name属性用于指定JavaBean实例对象的名称，其值应与<jsp:useBean>标签的id属性值相同。
　　　　property属性用于指定JavaBean实例对象的属性名。
   　　如果一个JavaBean实例对象的某个属性的值为null，那么，使用<jsp:getProperty>标签输出该属性的结果将是一个内容为“null”的字符串

###JSP+JavaBean开发模式
1. jsp+javabean开发模式架构
    在jsp+javabean架构中，JSP负责控制逻辑、表现逻辑、业务对象（javabean）的调用。
    JSP+JavaBean模式适合开发业务逻辑不太复杂的web应用程序，这种模式下，JavaBean用于封装业务数据，JSP即负责处理用户请求，又显示数据。

###Servlet+JSP+JavaBean开发模式
   在平时的JavaWeb项目开发中，在不使用第三方mvc开发框架的情况下，通常会选择Servlet+JSP+JavaBean开发模式来开发JavaWeb项目，
   Servlet+JSP+JavaBean组合开发就是一种MVC开发模式了，控制器(Controller)采用Servlet、模型(Model)采用JavaBean、视图(View)采用JSP。
   在讲解Servlet+JSP+JavaBean开发模式之前，先简单了解一下MVC开发模式
 1. Web开发中的请求-响应模型
    在Web世界里，具体步骤如下：
　　1、Web浏览器（如IE）发起请求，如访问http://www.iteye.com/
　　2、Web服务器（如Tomcat）接收请求，处理请求（比如用户新增，则将把用户保存一下），最后产生响应（一般为html）。
　　3、web服务器处理完成后，返回内容给web客户端（一般就是我们的浏览器），客户端对接收的内容进行处理
    （如web浏览器将会对接收到的html内容进行渲染以展示给客户）。
    因此，在Web世界里：都是Web客户端发起请求，Web服务器接收、处理并产生响应。
　　一般Web服务器是不能主动通知Web客户端更新内容。虽然现在有些技术如服务器推（如Comet）、
        还有现在的HTML5 websocket可以实现Web服务器主动通知Web客户端。
　　到此我们了解了在web开发时的请求/响应模型，接下来我们看一下标准的MVC模型是什么。

 2. 标准MVC模型概述
    MVC模型：是一种架构型的模式，本身不引入新功能，只是帮助我们将开发的结构组织的更加合理，使展示与模型分离、流程控制逻辑、
    业务逻辑调用与展示逻辑分离。

 3. MVC（Model-View-Controller）的概念
 　 首先让我们了解下MVC（Model-View-Controller）的概念：
 　 Model（模型）：数据模型，提供要展示的数据，因此包含数据和行为，可以认为是领域模型(domain)或JavaBean组件（包含数据和行为），
        不过现在一般都分离开来：Value Object（数据） 和 服务层（行为）。也就是模型提供了模型数据查询和模型数据的状态更新等功能，包括数据和业务。
    View（视图）：负责进行模型的展示，一般就是我们见到的用户界面，客户想看到的东西。
    Controller（控制器）：接收用户请求，委托给模型进行处理（状态改变），处理完毕后把返回的模型数据返回给视图，由视图负责展示。
     也就是说控制器做了个调度员的工作。
    在标准的MVC中模型能主动推数据给视图进行更新（观察者设计模式，在模型上注册视图，当模型更新时自动更新视图），
    但在Web开发中模型是无法主动推给视图（无法主动更新用户界面），因为在Web开发是请求-响应模型。
 　　那接下来我们看一下在Web里MVC是什么样子，我们称其为 Web MVC 来区别标准的MVC

 4. Web MVC概述
    Web MVC中的M(模型)-V(视图)-C(控制器)概念和标准MVC概念一样，我们再看一下Web MVC标准架构
    在Web MVC模式下，模型无法主动推数据给视图，如果用户想要视图更新，需要再发送一次请求（即请求-响应模型）。

 5. Servlet+JSP+JavaBean开发模式介绍
 　 Servlet+JSP+JavaBean架构其实可以认为就是我们所说的Web MVC模型，只是控制器采用Servlet、模型采用JavaBean、视图采用JSP

###Servlet+JSP+JavaBean开发模式的缺点
　Servlet+JSP+JavaBean(Web MVC)架构虽然实现了视图和模型分离以及控制逻辑和展示逻辑分离，但也有一些比较严重的缺点
1. Servlet作为控制器的缺点
　　此处的控制器使用Servlet，使用Servlet作为控制器有以下几个缺点：
　　1、控制逻辑可能比较复杂，其实我们可以按照规约，如请求参数submitFlag=toLogin，我们其实可以直接调用toLogin方法，
来简化控制逻辑；而且每个模块基本需要一个控制器，造成控制逻辑可能很复杂。现在流行的Web MVC框架(如Struts2)都支持"请求参数submitFlag=toAdd，
就可以直接调用toAdd方法"这样的处理机制，在Struts2中类似这样的处理机制就称为"动态方法调用"
　　2、请求参数到模型的封装比较麻烦，如果能交给框架来做这件事情，我们可以从中得到解放。
    3、选择下一个视图，严重依赖Servlet API，这样很难或基本不可能更换视图。
    4、给视图传输要展示的模型数据，也需要使用Servlet API，更换视图技术也要一起更换，很麻烦。
2. JavaBean作为模型的缺点
　　此处模型使用JavaBean，JavaBean组件类既负责收集封装数据，又要进行业务逻辑处理，这样可能造成JavaBean组件类很庞大，
所以一般现在项目都是采用三层架构，而不直接采用JavaBean。
3. 视图的缺点
   现在被绑定在JSP，很难更换视图，比如Velocity、FreeMarker；比如我要支持Excel、PDF视图等等

###Servlet+JSP+JavaBean开发模式(MVC)介绍
   Servlet+JSP+JavaBean模式(MVC)适合开发复杂的web应用，在这种模式下，servlet负责处理用户请求，jsp负责数据显示，
   javabean负责封装数据。 Servlet+JSP+JavaBean模式程序各个模块之间层次清晰，web开发推荐采用此种模式。
   这里以一个最常用的用户登录注册程序来讲解Servlet+JSP+JavaBean开发模式，通过这个用户登录注册程序综合案例，
   把之前的学过的XML、Xpath、Servlet、jsp的知识点都串联起来

###开发总结
　　通过这个Login_MVC小例子，可以了解到mvc分层架构的项目搭建，在平时的项目开发中，也都是按照如下的顺序来进行开发的：
 1. 搭建开发环境
　　　　1.1 创建web项目
　　　　1.2 导入项目所需的开发包
　　　　1.3 创建程序的包名，在java中是以包来体现项目的分层架构的
 2. 开发domain
　　把一张要操作的表当成一个VO类(VO类只定义属性以及属性对应的get和set方法，没有涉及到具体业务的操作方法)，VO表示的是值对象，
    通俗地说，就是把表中的每一条记录当成一个对象，表中的每一个字段就作为这个对象的属性。每往表中插入一条记录，
    就相当于是把一个VO类的实例对象插入到数据表中，对数据表进行操作时，都是直接把一个VO类的对象写入到表中，
    一个VO类对象就是一条记录。每一个VO对象可以表示一张表中的一行记录，VO类的名称要和表的名称一致或者对应。
 3. 开发dao
　　3.1 DAO操作接口：每一个DAO操作接口规定了，一张表在一个项目中的具体操作方法，此接口的名称最好按照如下格式编写：“I表名称Dao”。
　　　├DAO接口里面的所有方法按照以下的命名编写：
        ├更新数据库：doXxx()
        ├查询数据库：findXxx()或getXxx()
　　3.2 DAO操作接口的实现类：实现类中完成具体的增删改查操作
       ├此实现类完成的只是数据库中最核心的操作，并没有专门处理数据库的打开和关闭，因为这些操作与具体的业务操作无关。
 4. 开发service(service 对web层提供所有的业务服务)
 5. 开发web层

