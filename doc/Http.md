### HTTP协议
    HTTP是hypertext transfer protocol（超文本传输协议）的简写，它是TCP/IP协议的一个应用层协议，
    用于定义WEB浏览器与WEB服务器之间交换数据的过程。客户端连上web服务器后，若想获得web服务器中的某个web资源，
    需遵守一定的通讯格式，HTTP协议用于定义客户端与web服务器通迅的格式

###HTTP1.0和HTTP1.1的区别
    在HTTP1.0协议中，客户端与web服务器建立连接后，只能获得一个web资源。
    在HTTP1.1协议，允许客户端与web服务器建立连接后，在一个连接上获取多个web资源。

### HTTP 请求
####3.1 HTTP请求包括的内容
    客户端连上服务器后，向服务器请求某个web资源，称之为客户端向服务器发送了一个HTTP请求。
    一个完整的HTTP请求包含如下内容：一个请求行、若干请求头、以及实体内容

####3.2 HTTP请求的细节——请求行
    GET /mail/1.html?name=abc&password=xyz HTTP/1.1 请求行中的GET称之为请求方式，常用的有：GET, POST
    GET方式的特点：在URL地址后附带的参数是有限制的，其数据容量通常不能超过1K。
    请求方式为POST方式，则可以在请求的实体内容中向服务器发送数据，Post方式的特点：传送的数据量无限制。

####3.3 HTTP请求的细节——消息头
    Accept:浏览器可接受的MIME类型
    Accept-Charset:浏览器可接受的字符集
    Accept-Encoding：浏览器能够进行解码的数据编码方式，比如gzip
    Accept-Language：浏览器所希望的语言种类，当服务器能够提供一种以上的语言版本时要用到
    Authorization：授权信息，通常出现在对服务器发送的WWW-Authenticate头的应答中
    Connection：表示是否需要持久连接。如果Servlet看到这里的值为"Keep-Alive"，或者看到请求使用的是HTTP 1.1
            （HTTP 1.1默认进行持久连接），它就可以利用持久连接的优点，当页面包含多个元素时（例如Applet，图片），
            显著地减少下载所需要的时间
    Content-Length：表示请求消息正文的长度
    Cookie：这是最重要的请求头信息之一
    From：请求发送者的email地址，由一些特殊的Web客户程序使用，浏览器不会用到它
    Host：初始URL中的主机和端口
    If-Modified-Since: 只有当所请求的内容在指定的日期之后又经过修改才返回它，否则返回304"Not Modified"应答
    Pragma：指定"no-cache"值表示服务器必须返回一个刷新后的文档，即使它是代理服务器而且已经有了页面的本地拷贝
    Referer：包含一个URL，用户从该URL代表的页面出发访问当前请求的页面  防盗链
    User-Agent：浏览器类型，如果Servlet返回的内容与浏览器类型有关则该值非常有用

### HTTP 响应
####4.1 HTTP响应包括的内容
    一个HTTP响应代表服务器向客户端回送的数据，它包括： 一个状态行、若干消息头、以及实体内容
####4.2 HTTP响应的细节——状态行
     状态行格式： HTTP版本号　状态码　原因叙述<CRLF>
####4.3 HTTP请求的细节——响应头
    Allow： 服务器支持哪些请求方法（如GET、POST等）
    Content-Encoding： 文档的编码（Encode）方法。只有在解码之后才可以得到Content-Type头指定的内容类型。
        利用gzip压缩文档能够显著地减少HTML文档的下载时间
    Content-Length： 表示内容长度
    Content-Language: 浏览器语言环境
    Content-Type： 表示后面的文档属于什么MIME类型
    Content-Disposition: 服务器通过这个头，告诉浏览器以下载方式打开数据
    Date： 当前的GMT时间
    Expires： 应该在什么时候认为文档已经过期，从而不再缓存它？
    Last-Modified： 文档的最后改动时间。
    Refresh： 表示浏览器应该在多少时间之后刷新文档，以秒计
    Server： 服务器名字。
    Set-Cookie： 设置和页面关联的Cookie。
    WWW-Authenticate： 客户应该在Authorization头中提供什么类型的授权信息