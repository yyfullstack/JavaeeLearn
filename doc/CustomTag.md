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
 ###简单标签开发的一些注意细节
 1、标签类编写细节
    开发标签类时，不要直接去实现SimpleTag接口，而是应该继承SimpleTagSupport类，SimpleTagSupport类是SimpleTag接口的一个默认实现类，
    通过继承SimpleTagSupport类，就可以直接使用SimpleTagSupport类已经实现的那些方法，如果SimpleTagSupport类的方法实现不满足业务要求，
    那么就可以根据具体的业务情况将相应的方法进行重写。
 2、tld文件中标签体类型设置细节
    我们开发好一个简单标签后，需要在tld文件中添加对该标签的描述，

    开发好一个标签后，在tld文件中使用<tag>来描述一个标签，描述的内容包括标签名(name)，标签处理器类(tag-class)，标签体的内容(body-content)。
    tld文件中有四种标签体(body-content)类型 ：empty、JSP、scriptless、tagdependent
    简单标签标签体的细节注意问题：
         在简单标签(SampleTag)中标签体body-content的值只允许是empty、scriptless、tagdependent，不允许设置成JSP,如果设置成JSP就会出现异常：
    body-content的值如果设置成empty，那么就表示该标签没有标签体，如果是设置成scriptless，那么表示该标签是有标签体的，
         但是标签体中的内容不可以是<%java代码%>，

    jsp标签技术出现的目的就是为了移除在jsp页面上编写的java代码的，如果在jsp标签中允许出现java代码，那么就违背了jsp标签技术设计时的初衷了。所以在简单标签的标签体中是不允许出现java代码的。

    传统标签标签体的细节注意问题：
         在传统标签中标签体body-content的值允许是empty、JSP、scriptless、tagdependent，body-content的值如果是设置成JSP，那么表示该标签是有标签体的，
         并且标签体的内容可以是任意的，包括java代码，如果是设置成scriptless，那么表示该标签是有标签体的，但是标签体的内容不能是java代码
        如果传统标签和简单标签的标签体body-content的值设置成tagdependent，那么就表示标签体里面的内容是给标签处理器类使用的，
    tagdependent用得比较少，了解一下即可
 3、tld文件中标签库的uri设置细节
    如果在一个项目中使用或者开发了多个标签库, 那么标签库的uri不能设置成相同的，否则在Jsp页面中通过uri引用标签库时就不知道引用
    哪一个标签库了，如果真的有那么巧，两个标签库的uri是刚好一样的,那么在jsp页面中引用标签库时如果"<%@taglib uri="/gacl" prefix="gacl" %>"这样引用，
    那么就无法判断当前引用的标签库到底是gacl.tld标签库中的标签还是simpletag.tld标签库中的标签，因为两个标签库的uri刚好都是"/gacl"，
    在两个标签库的引用uri一样的情况下，为了能够在jsp中区别到底引用的是哪个标签库，可以换一种引用方式：<%@taglib uri="要引用的标签库的tld文件目录" prefix="gacl"%>，
    使用taglib指令引入标签库时，taglib指令的uri属性指定为标签库的tld文件目录，这样就可以区别开了

    引用gacl.tld标签库：<%@taglib uri="/WEB-INF/gacl.tld" prefix="gacl"%>、
    引用simpletag.tld标签库：<%@taglib uri="/WEB-INF/simpletag.tld" prefix="gacl"%>
   所以当在项目中引用了多个标签库，如果标签库的uri刚好是一样的，就可以用这种方式解决。
###简单标签开发步骤总结
 1、编写一个类继承SimpleTagSupport类，然后根据业务需要重写SimpleTagSupport类中已经实现了的方法，一般情况下只需要重写doTag()方法即可。
 2、在WEB-INF目录下创建一个tld文件，在tld文件中添加对该标签的描述。tld文件不一定放在WEB-INF目录下，也可以放在别的目录，习惯是放在WEB-INF目录下。

 ###JspFragment类介绍
    javax.servlet.jsp.tagext.JspFragment类是在JSP2.0中定义的，它的实例对象代表JSP页面中的一段符合JSP语法规范的JSP片段，
    这段JSP片段中不能包含JSP脚本元素。
    WEB容器在处理简单标签的标签体时，会把标签体内容用一个JspFragment对象表示，并调用标签处理器对象的setJspBody方法
    把JspFragment对象传递给标签处理器对象。JspFragment类中只定义了两个方法，如下所示：
    　　getJspContext方法 用于返回代表调用页面的JspContext对象.
    public abstract void invoke(java.io.Writer out)
    　　　　用于执行JspFragment对象所代表的JSP代码片段，参数out用于指定将JspFragment对象的执行结果写入到哪个输出流对象中，
    如果 传递给参数out的值为null，则将执行结果写入到JspContext.getOut()方法返回的输出流对象中。(简而言之，可以理解为写给浏览器)

  1. invoke方法详解
  　　JspFragment.invoke方法是JspFragment最重要的方法，利用这个方法可以控制是否执行和输出标签体的内容、
  是否迭代执行标签体的内容或对标签体的执行结果进行修改后再输出。例如：
  　　在标签处理器中如果没有调用JspFragment.invoke方法，其结果就相当于忽略标签体内容；
  　　在标签处理器中重复调用JspFragment.invoke方法，则标签体内容将会被重复执行；
  　　若想在标签处理器中修改标签体内容，只需在调用invoke方法时指定一个可取出结果数据的输出流对象（例如StringWriter），
  让标签体的执行结果输出到该输出流对象中，然后从该输出流对象中取出数据进行修改后再输出到目标设备，即可达到修改标签体的目的。

###开发带属性的标签
   自定义标签可以定义一个或多个属性，这样，在JSP页面中应用自定义标签时就可以设置这些属性的值，通过这些属性为标签处理器传递参数信息，
   从而提高标签的灵活性和复用性。要想让一个自定义标签具有属性，通常需要完成两个任务：
   在标签处理器中编写每个属性对应的setter方法
   在TLD文件中描术标签的属性
   　　为自定义标签定义属性时，每个属性都必须按照JavaBean的属性命名方式，在标签处理器中定义属性名对应的setter方法，
   用来接收 JSP页面调用自定义标签时传递进来的属性值。 例如属性url，在标签处理器类中就要定义相应的setUrl(String url)方法。
   　　在标签处理器中定义相应的set方法后，JSP引擎在解析执行开始标签前，也就是调用doStartTag方法前，会调用set属性方法，为标签设置属性。

   1、tld文件中用于描述标签属性的<attribute>元素说明
   　　<tag>元素的<attribute>子元素用于描述自定义标签的一个属性，自定义标签所具有的每个属性都要对应一个<attribute>元素

   <attribute>元素的子元素说明：
    name: 必填项，用于指定属性的名称，属性名称是大小写敏感的，并且不能以jsp,_jsp,java 和孙开头
    required: 不是必填，用于指定在jsp页面中调用自定义标签时，是否必须设置这个属性，其取值包括true和false，默认值为false，
        true表示必须设置，否则可以设置也可以不设置该属性
    rtexprvalue:不是必填,rtexprvalue是runtime expression value(运行时表达式)的英文简写，用于指定属性值是一个静态值或动态值。
        其取值包括true和false，默认值为false，false表示只能为该属性指定静态文本值，true表示可以为该属性指定一个jsp动态元素，
        动态元素的结果作为属性值， 例如JSP表达式<%=value%>

   2.　　<c:when>标签和<c:otherwise>标签对应着两个不同的标签处理器类，我们希望做到的效果是，如果<c:when>标签执行了，
   那么就<c:otherwise>标签就不要再执行，那么这里面就涉及到一个问题：<c:when>标签执行的时候该如何通知<c:otherwise>标签不要执行了呢？
   这个问题就涉及到了两个标签处理器类如何做到相互通讯的问题，如果<c:when>标签执行了，就要通过某种方式告诉<c:otherwise>标签不要执行，
   那么该如何做到这样的效果呢？让<c:when>标签处理器类和<c:otherwise>标签处理器类共享同一个变量就可以做到了，那么又该怎么做
   才能够让两个标签处理器类共享同一个变量呢，标准的做法是这样的：让两个标签拥有同一个父标签。