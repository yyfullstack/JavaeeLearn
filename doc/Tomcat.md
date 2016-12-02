####Tomcat服务器端口的配置
    如果想修改tomcat服务器的启动端口，则可以在server.xml配置文件中的Connector节点进行端口修改

####Tomcat服务器虚拟目录的映射方式
    Web应用程序开发好后，如想供外界访问，需要把web应用所在的目录交给web服务器管理，这个过程称之为虚拟目录的映射。
 1. 方式一，在server.xml文件的host元素中配置
    在<Host></Host>这对标签加上<Context path="/JavaWebApp" docBase="F:\JavaWebDemoProject" />即可
    将在F盘下的JavaWebDemoProject这个JavaWeb应用映射到JavaWebApp这个虚拟目录上，
    JavaWebApp这个虚拟目录是由Tomcat服务器管理的，JavaWebApp是一个硬盘上不存在的目录，
    是我们自己随便写的一个目录，也就是虚拟的一个目录，所以称之为"虚拟目录"

    Context表示上下文，代表的就是一个JavaWeb应用，Context元素有两个属性，

   Ⅰ.path：用来配置虚似目录，必须以"/"开头。

   Ⅱ.docBase：配置此虚似目录对应着硬盘上的Web应用所在目录。

   注意：在Tomcat6之后中，不再建议在server.xml文件中使用配置context元素的方式来添加虚拟目录的映射，因为每次修改server.xml文件后，
   Tomcat服务器就必须要重新启动后才能重新加载server.xml文件

 2. 方式二，让tomcat服务器自动映射
    tomcat服务器会自动管理webapps目录下的所有web应用，并把它映射成虚似目录。换句话说，
    tomcat服务器webapps目录中的web应用，外界可以直接访问。
    如： 把F盘下的JavaWebDemoProject这个JavaWeb应用直接copy到tomcat服务器webapps目录中

 3. 方式三，在tomcat服务器的\conf\Catalina\localhost目录下添加一个以xml作为扩展名的文件，xml文件的名字可以任意取，
 比如下面的aa.xml，注意这一句话"The context path and version will be derived from the base name of the file"，
 这一句话的意思翻译过来就是"context元素的path属性源自于是这个xml文件的名字"，上面提到过，
 Context元素的path属性是用来配置虚似目录的名称的，所以虚似目录的名称就是这个xml文件的名称。

 在aa.xml文件中添加Context元素映射JavaWeb应用，代码如下：
 <Context docBase="F:\JavaWebDemoProject" />

 注意：在Context元素中并没有指明path属性来设置虚拟目录的名称，
 那么"F:\JavaWebDemoProject"映射的虚拟目录名称是神马呢，就是当前正在编辑的这个xml文件的名称aa。

 使用这种方式映射虚拟目录的最大好处是修改了配置文件后不用重启Tomcat服务器，比如将aa.xml修改成bb.xml，
 Tomcat服务器会自动Undeploying context [/aa]，然后自动信息:
  Deploying configuration descriptor D:\apache-tomcat-7.0.53\conf\Catalina\localhost\bb.xml


### Tomcat服务器配置虚似主机
    配置虚似主机就是配置一个网站。
    在Tomcat服务器配置一个虚拟主机(网站)，需要修改conf文件夹下的server.xml这个配置文件，使用Host元素进行配置，
    打开server.xml，可以看到Tomcat服务器自带的一个名称为localhost的虚拟主机(网站)

    平时我们将开发好的JavaWeb应用放到webapps文件夹下，然后就可以使用"http://localhost:端口号/JavaWebAppName"的方式去访问了，
    其实访问的就是name是"localhost"的那台虚拟主机(Host)，这台虚拟主机管理webapps文件夹下的所有web应用。

    例如：http://localhost:8080/JavaWebDemoProject/1.jsp，这个URL地址访问的就是名称
    是localhost的那台虚拟主机下的JavaWebDemoProject这个应用里面的1.jsp这个web资源。
    我们可以使用如下的方式配置一个虚拟主机，例如：  　　
    <Host name="www.gacl.cn" appBase="F:\JavaWebApps">
    </Host>

    这里我们新配置一个虚拟主机，虚拟主机的name是"www.gacl.cn"，虚拟主机"www.gacl.cn"现在管理着JavaWebApps文件夹下的所有web应用，
    平时我们在互联网上使用域名"www.baidu.com"访问百度的网站时，其实就是在访问一个名称是"www.baidu.com"的虚拟主机，
    所以当我们要访问name是"www.gacl.cn"的这个虚拟主机时，就可以使用"域名(www.gacl.cn)"去访问，
    注意一下appBase="F:\JavaWebApps"，这里的JavaWebApps文件夹代表的不是一个项目的根目录，
    而是一个存放了一个或者多个JavaWeb应用的文件夹

### 浏览器与服务器交互过程

浏览器和服务器做了以下几个操作：
1. 浏览器根据主机名"www.gacl.cn"去操作系统的Hosts文件中查找主机名对应的IP地址。
2. 浏览器如果在操作系统的Hosts文件中没有找到对应的IP地址，就去互联网上的DNS服务器上查找"www.gacl.cn"这台主机对应的IP地址。
3. 浏览器查找到"www.gacl.cn"这台主机对应的IP地址后，就使用IP地址连接到Web服务器。
4. 浏览器连接到web服务器后，就使用http协议向服务器发送请求，发送请求的过程中，浏览器会向Web服务器以Stream(流)的形式传输数据，
告诉Web服务器要访问服务器里面的哪个Web应用下的Web资源
5. 浏览器做完上面4步工作后，就开始等待，等待Web服务器把自己想要访问的1.jsp这个Web资源传输给它。
6. 服务器接收到浏览器传输的数据后，开始解析接收到的数据，
服务器解析"GET /JavaWebDemo1/1.jsp HTTP/1.1"里面的内容时知道客户端浏览器要访问的是JavaWebDemo1应用里面的1.jsp这个Web资源，
然后服务器就去读取1.jsp这个Web资源里面的内容，将读到的内容再以Stream(流)的形式传输给浏览器，
7. 浏览器拿到服务器传输给它的数据之后，就可以把数据展现给用户看了

### Tomcat的体系结构
    Tomcat服务器的启动是基于一个server.xml文件的，Tomcat启动的时候首先会启动一个Server，Server里面就会启动Service，
    Service里面就会启动多个"Connector(连接器)"，每一个连接器都在等待客户机的连接，当有用户使用浏览器去访问服务器
    上面的web资源时，首先是连接到Connector(连接器)，Connector(连接器)是不处理用户的请求的，而是将用户的请求交给
    一个Engine(引擎)去处理，Engine(引擎)接收到请求后就会解析用户想要访问的Host，然后将请求交给相应的Host，
    Host收到请求后就会解析出用户想要访问这个Host下面的哪一个Web应用,一个web应用对应一个Context。

### 互联网上的加密原理
    <Connector port="8080" protocol="HTTP/1.1"
        connectionTimeout="20000"
        edirectPort="8443" />

    这个Connector是一个没有加密的连接器，使用"http://localhost:8080/JavaWebDemoProject/Web/1.jsp"去请求服务器上的web资源
    的这个过程中，我们的请求是不加密的，要是想以一种加密的方式来访问Tomcat服务器，那么就要在Tomcat里面配置一个加密的Connector。
    要配置一个加密连接器，首先应该把互联网上的加密原理弄清楚。

####3.1对称加密

    采用单钥密码系统的加密方法，同一个密钥可以同时用作信息的加密和解密，这种加密方法称为对称加密，也称为单密钥加密。
    需要对加密和解密使用相同密钥的加密算法。由于其速度快，对称性加密通常在消息发送方需要加密大量数据时使用。对称性加密也称为密钥加密。
    所谓对称，就是采用这种加密方法的双方使用方式用同样的密钥进行加密和解密。密钥是控制加密及解密过程的指令。
    算法是一组规则，规定如何进行加密和解密。
    加密的安全性不仅取决于加密算法本身，密钥管理的安全性更是重要。因为加密和解密都使用同一个密钥，
    如何把密钥安全地传递到解密者手上就成了必须要解决的问题。
    常用的对称加密有：DES、IDEA、RC2、RC4、SKIPJACK、RC5、AES算法等


####3.2 非对称加密
    非对称加密算法需要两个密钥：公开密钥（publickey）和私有密钥（privatekey）。公开密钥与私有密钥是一对，
    如果用公开密钥对数据进行加密，只有用对应的私有密钥才能解密；如果用私有密钥对数据进行加密，
    那么只有用对应的公开密钥才能解密。因为加密和解密使用的是两个不同的密钥，所以这种算法叫作非对称加密算法。

###### 非对称加密工作原理
　1. A要向B发送信息，A和B都要产生一对用于加密和解密的公钥和私钥。
　2. A的私钥保密，A的公钥告诉B；B的私钥保密，B的公钥告诉A。
　3. A要给B发送信息时，A用B的公钥加密信息，因为A知道B的公钥。
　4. A将这个消息发给B（已经用B的公钥加密消息）。
　5. B收到这个消息后，B用自己的私钥解密A的消息。其他所有收到这个报文的人都无法解密，因为只有B才有B的私钥

    非对称性加密依然没有解决数据传输的安全性问题，比如A想向B发数据，B首先生成一对密钥(公钥和私钥)，然后将公钥发给A，
    A拿到B发给他的公钥有就可以使用公钥加密数据后发给B，然而在B公钥发送给A的这个过程中，很有可能会被第三方C截获，
    C截获到B的公钥后，也使用B的公钥加密数据，然后发给B，B接收到数据后就晕了，因为搞不清楚接收到的数据到底是A发的还是C发的，这是其中一个问题，

    另一个问题就是，C截获到B发的公钥后，C可以自己生成一对密钥(公钥和私钥)，然后发给A，A拿到公钥后就以为是B发给他的，
    然后就使用公钥加密数据发给B，发送给B的过程中被C截获下来，由于A是用C发给他的公钥加密数据的，而C有私钥，
    因此就可以解密A加密过后的内容了，而B接收到A发给他的数据后反而解不开了，因为数据是用C的公钥加密的，B没有C的私钥，所以就无法解密。

    所以，非对称性加密存在一个问题：A想向B发数据，A如何确定拿到的公钥一定是B发的呢？那么如何解决这个问题呢？
    只能靠一个第三方机构(CA机构，即证书授权中心(Certificate Authority )，或称证书授权机构)来担保。A想向B发数据，
    B首先将公钥发给CA机构，CA机构拿到B的公钥后跑到B的家里问：这是你发的公钥吗？B确认过后说是：没错，是我发的！
    那么此时CA机构就会为B的公钥做担保，生成一份数字证书给B，数字证书包含了CA的担保认证签名和B的公钥，
    B拿到CA的这份数字证书后，就发给A，A拿到数字证书后，看到上面有CA的签名，就可以确定当前拿到的公钥是B发的，
    那么就可以放心大胆地使用公钥加密数据，然后发给B了。

### https连接器
    浏览器与服务器交互时，浏览器想将数据加密后再发送给服务器，那么该怎么做呢？服务器首先要向浏览器出示一份数字证书，
    浏览器看到数字证书后，就可以使用数字证书里面的公钥加密数据，所以要想做浏览器和服务器的加密数据传输，
    那么首先得针对服务器生成一份数字证书。然后再配置一下服务器，让服务器收到浏览器的请求之后，会向浏览器出示它的数字证书。

####4.1 生成Tomcat服务器的数字证书
    keytool -genkey -alias tomcat -keyalg RSA
    命令执行完之后，操作系统的用户文件夹下面就会生成一个.keystore文件。

    keytool -list -keystore .keystore 查看所有证书
####4.2 配置https连接器
    将生成的.keystore密钥库文件拷贝到tomcat服务器的conf目录下
    修改server.xml文件，配置https连接器
    <Connector port="8443" protocol="org.apache.coyote.http11.Http11Protocol"
        maxThreads="150" SSLEnabled="true" scheme="https" secure="true"
        clientAuth="false" sslProtocol="TLS"
        keystoreFile="conf/.keystore" keystorePass="123456"/>

    在server.xml文件中配置了一个端口是8443的加密连接器，浏览器访问8443端口的连接器时，将会以加密的方式来访问web服务器，
    这个连接器收到浏览器的请求后，将会向浏览器出示一份数字证书，浏览器再用数字证书里面的公钥来加密数据，
    keystoreFile="conf/.keystore" 用来指明密钥库文件的所在路径，服务器从密钥库中提取证书时需要密码，
    keystorePass="123456"指明密钥库的访问密码。

    由于密钥库里面的证书是我们手工生成的，没有经过CA的认证，所以使用"https://localhost:8443/"访问8443的加密连接器，
    浏览器会出现"证书错误，导航已阻止"，浏览器认为当前要访问的这个主机是不安全的，不推荐继续访问，点击就可以继续访问了，