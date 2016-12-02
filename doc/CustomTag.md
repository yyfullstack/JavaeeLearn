###自定义标签的作用
   自定义标签主要用于移除Jsp页面中的java代码。
###自定义标签开发和使用
####2.1自定义标签开发步骤
1. 编写一个实现Tag接口的Java类(标签处理器类)
2. 在WEB-INF/目录下新建tld文件，在tld文件中对标签处理器类进行描述
####2.2在Jsp页面中使用自定义标签
1. 使用"<%@taglib uri="标签库的uri"  prefix="标签的使用前缀"%>"指令引入要使用的标签库。
###自定义标签的执行流程
  JSP引擎遇到自定义标签时，首先创建标签处理器类的实例对象，然后按照JSP规范定义的通信规则依次调用它的方法。
1.  public void setPageContext(PageContext pc)， JSP引擎实例化标签处理器后，将调用setPageContext方法将
    JSP页面的pageContext对象传递给标签处理器，标签处理器以后可以通过这个pageContext对象与JSP页面进行通信。
2.  public void setParent(Tag t)，setPageContext方法执行完后，WEB容器接着调用的setParent方法将当前标签的
    父标签传递给当前标签处理器，如果当前标签没有父标签，则传递给setParent方法的参数值为null。
3.  public int doStartTag()，调用了setPageContext方法和setParent方法之后，WEB容器执行到自定义标签的开始标记时，
    就会调用标签处理器的doStartTag方法。
4.  public int doEndTag()，WEB容器执行完自定义标签的标签体后，就会接着去执行自定义标签的结束标记，此时，
     WEB容器会去调用标签处理器的doEndTag方法。
5.  public void release()，通常WEB容器执行完自定义标签后，标签处理器会驻留在内存中，为其它请求服务器，直至停止web应用时，
    web容器才会调用release方法。

   重点分析一下上述代码中标红色的那个 private boolean _jspx_meth_customtag_005fviewIP_005f0(javax.servlet.jsp.PageContext _jspx_page_context)方法中的代码

   ①. 这里是实例化一个viewIP标签处理器类com.yckjsoft.javaee.customtag.ViewIPTag的对象
     //   customtag:viewIP
   com.yckjsoft.javaee.customtag.ViewIPTag _jspx_th_customtag_005fviewIP_005f0 = (com.yckjsoft.javaee.customtag.ViewIPTag)
    _005fjspx_005ftagPool_005fcustomtag_005fviewIP_005fnobody.get(com.yckjsoft.javaee.customtag.ViewIPTag.class);

   ②. 实例化标签处理器后，调用setPageContext方法将JSP页面的pageContext对象传递给标签处理器
    _jspx_th_customtag_005fviewIP_005f0.setPageContext(_jspx_page_context);

   ③. setPageContext方法执行完后，接着调用的setParent方法将当前标签的父标签传递给当前标签处理器，如果当前标签没有父标签，
   则传递给setParent方法的参数值为null
   _jspx_th_customtag_005fviewIP_005f0.setParent(null);

   ④. 调用了setPageContext方法和setParent方法之后，WEB容器执行到自定义标签的开始标记时，就会调用标签处理器的doStartTag方法
   int _jspx_eval_customtag_005fviewIP_005f0 = _jspx_th_customtag_005fviewIP_005f0.doStartTag();

   ⑤. WEB容器执行完自定义标签的标签体后，就会接着去执行自定义标签的结束标记，此时，WEB容器会去调用标签处理器的doEndTag方法
   if (_jspx_th_customtag_005fviewIP_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE)
   这就是自定义标签的执行流程。


###标签API简单介绍
####1. JspTag接口
    JspTag接口是所有自定义标签的父接口，它是JSP2.0中新定义的一个标记接口，没有任何属性和方法。JspTag接口有Tag和SimpleTag两个直接子接口，
    JSP2.0以前的版本中只有Tag接口，所以把实现Tag接口的自定义标签也叫做传统标签，把实现SimpleTag接口的自定义标签叫做简单标签。
####2. Tag接口
    Tag接口是所有传统标签的父接口，其中定义了两个重要方法（doStartTag、doEndTag）方法和
    四个常量（EVAL_BODY_INCLUDE、SKIP_BODY、EVAL_PAGE、SKIP_PAGE），这两个方法和四个常量的作用如下：
  1. WEB容器在解释执行JSP页面的过程中，遇到自定义标签的开始标记就会去调用标签处理器的doStartTag方法，doStartTag方法执行完后
     可以向WEB容器返回常量EVAL_BODY_INCLUDE或SKIP_BODY。如果doStartTag方法返回EVAL_BODY_INCLUDE，WEB容器就会接着执行自定义标签
     的标签体；如果doStartTag方法返回SKIP_BODY，WEB容器就会忽略自定义标签的标签体，直接解释执行自定义标签的结束标记。
  2. WEB容器解释执行到自定义标签的结束标记时，就会调用标签处理器的doEndTag方法，doEndTag方法执行完后可以向WEB容器返回
     常量EVAL_PAGE或SKIP_PAGE。如果doEndTag方法返回常量EVAL_PAGE，WEB容器就会接着执行JSP页面中位于结束标记后面的JSP代码；
     如果doEndTag方法返回SKIP_PAGE，WEB容器就会忽略JSP页面中位于结束标记后面的所有内容。
  从doStartTag和doEndTag方法的作用和返回值的作用可以看出，开发自定义标签时可以在doStartTag方法和doEndTag方法体内
  编写合适的Java程序代码来实现具体的功能，通过控制doStartTag方法和doEndTag方法的返回值，还可以告诉WEB容器是否执行自定义标签
  中的标签体内容和JSP页面中位于自定义标签的结束标记后面的内容
####3. IterationTag接口
   IterationTag接口继承了Tag接口，并在Tag接口的基础上增加了一个doAfterBody方法和一个EVAL_BODY_AGAIN常量。
   实现IterationTag接口的标签除了可以完成Tag接口所能完成的功能外，还能够通知WEB容器是否重复执行标签体内容。
   对于实现了IterationTag接口的自定义标签，WEB容器在执行完自定义标签的标签体后，将调用标签处理器的doAfterBody方法，
   doAfterBody方法可以向WEB容器返回常量EVAL_BODY_AGAIN或SKIP_BODY。如果doAfterBody方法返回EVAL_BODY_AGAIN，
   WEB容器就会把标签体内容再重复执行一次，执行完后接着再调用doAfterBody方法，如此往复，直到doAfterBody方法返回常量SKIP_BODY，
   WEB容器才会开始处理标签的结束标记和调用doEndTag方法。

   可见，开发自定义标签时，可以通过控制doAfterBody方法的返回值来告诉WEB容器是否重复执行标签体内容，从而达到循环处理
   标签体内容的效果。例如，可以通过一个实现IterationTag接口的标签来迭代输出一个集合中的所有元素，在标签体部分指定元素的输出格式。

   在JSP API中也提供了IterationTag接口的默认实现类TagSupport，我们在编写自定义标签的标签处理器类时，可以继承和扩展TagSupport类，
   这相比实现IterationTag接口将简化开发工作。

####4、BodyTag接口
    BodyTag接口继承了IterationTag接口，并在IterationTag接口的基础上增加了两个方法（setBodyContent、doInitBody）和一个EVAL_BODY_BUFFERED常量。
    实现BodyTag接口的标签除了可以完成IterationTag接口所能完成的功能，还可以对标签体内容进行修改。对于实现了BodyTag接口的自定义标签，
    标签处理器的doStartTag方法不仅可以返回前面讲解的常量EVAL_BODY_INCLUDE或SKIP_BODY，还可以返回常量EVAL_BODY_BUFFERED。
    如果doStartTag方法返回EVAL_BODY_BUFFERED，WEB容器就会创建一个专用于捕获标签体运行结果的BodyContent对象，然后调用标签处理器
    的setBodyContent方法将BodyContent对象的引用传递给标签处理器，WEB容器接着将标签体的执行结果写入到BodyContent对象中。
    在标签处理器的后续事件方法中，可以通过先前保存的BodyContent对象的引用来获取标签体的执行结果，然后调用BodyContent对象
    特有的方法对BodyContent对象中的内容（即标签体的执行结果）进行修改和控制其输出。

 　 在JSP API中也提供了BodyTag接口的实现类BodyTagSupport，我们在编写能够修改标签体内容的自定义标签的标签处理器类时，
    可以继承和扩展BodyTagSupport类，这相比实现BodyTag接口将简化开发工作。

####5、 SimpleTag接口
　　SimpleTag接口是JSP2.0中新增的一个标签接口。由于传统标签使用三个标签接口来完成不同的功能，显得过于繁琐，不利于标签技术的推广，
    因此，SUN公司为降低标签技术的学习难度，在JSP 2.0中定义了一个更为简单、便于编写和调用的SimpleTag接口。SimpleTag接口与传统标签
    接口最大的区别在于，SimpleTag接口只定义了一个用于处理标签逻辑的doTag方法，该方法在WEB容器执行自定义标签时调用，并且只被调用一次。
    那些使用传统标签接口所完成的功能，例如是否执行标签体、迭代标签体、对标签体内容进行修改等功能都可以在doTag方法中完成。

　　在JSP API中也提供了SimpleTag接口的实现类SimpleTagSupport，我们在编写简单标签时，可以继承和扩展SimpleTagSupport类，
    这相比实现SimpleTag接口将简化开发工作。
####6、传统标签接口中的各个方法可以返回的返回值说明
     下图列举了Tag接口、IterationTag接口和BodyTag接口中的主要方法及它们分别可以返回的返回值的说明。
    doStartTag EVAL_BODY_INCLUDE  将标签体的执行结果插入到输出流中
    doStartTag SKIP_BODY  忽略标签体部分
    doStartTag EVAL_BODY_BUFFERED  将标签体的执行结果放入到一个BodyContent对象中

    doAfterBody SKIP_BODY  不在重复执行标签体的内容
    doAfterBody EVAL_BODY_AGAIN  重复执行标签体的内容

     doEndTag EVAL_PAGE 继续处理结束标签后面所有的JSP代码
     doEndTag SKIP_PAGE 忽略结束标签后面所有的JSP代码

###开发传统标签实现页面逻辑
   开发人员在编写Jsp页面时，经常还需要在页面中引入一些逻辑，例如：

   控制jsp页面某一部分内容是否执行。
   控制整个jsp页面是否执行。
   控制jsp页面内容重复执行。
   修改jsp页面内容输出。

###简单标签(SimpleTag)
    由于传统标签使用三个标签接口来完成不同的功能，显得过于繁琐，不利于标签技术的推广， SUN公司为降低标签技术的学习难度，
    在JSP 2.0中定义了一个更为简单、便于编写和调用的SimpleTag接口来实现标签的功能。
    实现SimpleTag接口的标签通常称为简单标签。简单标签共定义了5个方法：
    setJspContext方法
    setParent和getParent方法
    setJspBody方法
    doTag方法(非常重要)，简单标签使用这个方法就可以完成所有的业务逻辑
###SimpleTag方法介绍
 1. setJspContext方法
       用于把JSP页面的pageContext对象传递给标签处理器对象
 2. setParent方法
   　　用于把父标签处理器对象传递给当前标签处理器对象
 3. getParent方法
       用于获得当前标签的父标签处理器对象
 4. setJspBody方法
   　　用于把代表标签体的JspFragment对象传递给标签处理器对象
 5. doTag方法
   　　用于完成所有的标签逻辑，包括输出、迭代、修改标签体内容等。在doTag方法中可以抛出javax.servlet.jsp.SkipPageException异常，
       用于通知WEB容器不再执行JSP页面中位于结束标记后面的内容，这等效于在传统标签的doEndTag方法中返回Tag.SKIP_PAGE常量的情况。

###SimpleTag接口方法的执行顺序
   当web容器开始执行标签时，会调用如下方法完成标签的初始化：
   1. WEB容器调用标签处理器对象的setJspContext方法，将代表JSP页面的pageContext对象传递给标签处理器对象。
   2. WEB容器调用标签处理器对象的setParent方法，将父标签处理器对象传递给这个标签处理器对象。注意，只有在标签存在父标签的情况下，
   WEB容器才会调用这个方法。
   3. 如果调用标签时设置了属性，容器将调用每个属性对应的setter方法把属性值传递给标签处理器对象。如果标签的属性值是EL表达式或脚本表达式，
        则WEB容器首先计算表达式的值，然后把值传递给标签处理器对象。
   4. 如果简单标签有标签体，WEB容器将调用setJspBody方法把代表标签体的JspFragment对象传递进来。
   5. 执行标签时WEB容器调用标签处理器的doTag()方法，开发人员在方法体内通过操作JspFragment对象，就可以实现是否执行、迭代、修改标签体的目的。