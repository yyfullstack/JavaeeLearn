###Session简单介绍
    在WEB开发中，服务器可以为每个用户浏览器创建一个会话对象（session对象），注意：一个浏览器独占一个session对象(默认情况下)。
    因此，在需要保存用户数据时，服务器程序可以把用户数据写到用户浏览器独占的session中，当用户使用浏览器访问其它程序时
    ，其它程序可以从用户的session中取出该用户的数据，为用户服务

###Session和Cookie的主要区别
1. Cookie是把用户的数据写给用户的浏览器
2. Session技术把用户的数据写到用户独占的session中。
3. Session对象由服务器创建，开发人员可以调用request对象的getSession方法得到session对象

###session实现原理
####3.1 服务器是如何实现一个session为一个用户浏览器服务的？
    服务器创建session出来后，会把session的id号，以cookie的形式回写给客户机，这样，只要客户机的浏览器不关，再去访问服务器时，
    都会带着session的id号去，服务器发现客户机浏览器带session id过来了，就会使用内存中与之对应的session为之服务
###浏览器禁用Cookie后的session处理
    解决方案：URL重写
　　response.encodeRedirectURL(java.lang.String url) 用于对sendRedirect方法后的url地址进行重写。
　　response.encodeURL(java.lang.String url)用于对表单action和超链接的url地址进行重写
    当浏览器禁用了cookie后，就可以用URL重写这种解决方案解决Session数据共享问题。而且response. encodeRedirectURL(java.lang.String url)
    和response. encodeURL(java.lang.String url)是两个非常智能的方法，当检测到浏览器没有禁用cookie时，那么就不进行URL重写了
###session对象的创建和销毁时机
1. session对象的创建时机
    在程序中第一次调用request.getSession()方法时就会创建一个新的Session，可以用isNew()方法来判断Session是不是新创建的
2. session对象的销毁时机
   session对象默认30分钟没有使用，则服务器会自动销毁session，在web.xml文件中可以手工配置session的失效时间, 如：
   <!-- 设置Session的有效时间:以分钟为单位-->
        <session-config>
            <session-timeout>15</session-timeout>
        </session-config>

   当需要在程序中手动设置Session失效时，可以手工调用session.invalidate方法，摧毁session。
   HttpSession session = request.getSession();
    //手工调用session.invalidate方法，摧毁session
    session.invalidate();

###表单重复提交的常见应用场景
####1.1场景一：在网络延迟的情况下让用户有时间点击多次submit按钮导致表单重复提交
####1.2场景二：表单提交后用户点击【刷新】按钮导致表单重复提交
####1.3场景三：用户提交表单后，点击浏览器的【后退】按钮回退到表单页面后进行再次提交

###利用Session防止表单重复提交
    具体的做法：在服务器端生成一个唯一的随机标识号，专业术语称为Token(令牌)，同时在当前用户的Session域中保存这个Token。
    然后将Token发送到客户端的Form表单中，在Form表单中使用隐藏域来存储这个Token，表单提交的时候连同这个Token一起提交到服务器端，
    然后在服务器端判断客户端提交上来的Token与服务器端生成的Token是否一致，如果不一致，那就是重复提交了，此时服务器端就可以
    不处理重复提交的表单。如果相同则处理表单提交，处理完后清除当前用户的Session域中存储的标识号。

　在下列情况下，服务器程序将拒绝处理用户提交的表单请求：
1. 存储Session域中的Token(令牌)与表单提交的Token(令牌)不同。
2. 当前用户的Session中不存在Token(令牌)。
3. 用户提交的表单数据中没有Token(令牌)。