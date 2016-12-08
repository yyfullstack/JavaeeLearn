###JDBC相关概念介绍
####1.1、数据库驱动
    这里的驱动的概念和平时听到的那种驱动的概念是一样的，比如平时购买的声卡，网卡直接插到计算机上面是不能用的，
    必须要安装相应的驱动程序之后才能够使用声卡和网卡，同样道理，我们安装好数据库之后，我们的应用程序也是不能直接使用数据库的，
    必须要通过相应的数据库驱动程序，通过驱动程序去和数据库打交道
####1.2、JDBC介绍
    SUN公司为了简化、统一对数据库的操作，定义了一套Java操作数据库的规范（接口），称之为JDBC。这套接口由数据库厂商去实现，
    这样，开发人员只需要学习jdbc接口，并通过jdbc加载具体的驱动，就可以操作数据库。
    JDBC全称为：Java Data Base Connectivity（java数据库连接），它主要由接口组成。
　　组成JDBC的２个包：
　　　java.sql
　　　javax.sql
　　开发JDBC应用需要以上2个包的支持外，还需要导入相应JDBC的数据库实现(即数据库驱动)。
###编写JDBC程序
####2.1、搭建实验环境
    在mysql中创建一个库，并创建user表和插入表的数据。

####2.2、DriverManager类讲解
    Jdbc程序中的DriverManager用于加载驱动，并创建与数据库的链接，这个API的常用方法：
    DriverManager.registerDriver(new Driver())
    DriverManager.getConnection(url, user, password)，
    注意：在实际开发中并不推荐采用registerDriver方法注册驱动。原因有二：
        1、查看Driver的源代码可以看到，如果采用此种方式，会导致驱动程序注册两次，也就是在内存中会有两个Driver对象。\
        2、程序依赖mysql的api，脱离mysql的jar包，程序将无法编译，将来程序切换底层数据库将会非常麻烦。
     　　推荐方式：Class.forName("com.mysql.jdbc.Driver");
    　　采用此种方式不会导致驱动对象在内存中重复出现，并且采用此种方式，程序仅仅只需要一个字符串，不需要依赖具体的驱动，使程序的灵活性更高。
####2.3、数据库URL讲解
    URL用于标识数据库的位置，通过URL地址告诉JDBC程序连接哪个数据库，URL的写法为：
    常用数据库URL地址的写法：
    Oracle写法：jdbc:oracle:thin:@localhost:1521:sid
    SqlServer写法：jdbc:microsoft:sqlserver://localhost:1433; DatabaseName=sid
    MySql写法：jdbc:mysql://localhost:3306/sid
    如果连接的是本地的Mysql数据库，并且连接使用的端口是3306，那么的url地址可以简写为： jdbc:mysql:///数据库
####2.4、Connection类讲解
    Jdbc程序中的Connection，它用于代表数据库的链接，Collection是数据库编程中最重要的一个对象，客户端与数据库所有交互
        都是通过connection对象完成的，这个对象的常用方法：
    createStatement()：创建向数据库发送sql的statement对象。
    prepareStatement(sql) ：创建向数据库发送预编译sql的PrepareSatement对象。
    prepareCall(sql)：创建执行存储过程的callableStatement对象。
    setAutoCommit(boolean autoCommit)：设置事务是否自动提交。
    commit() ：在链接上提交事务。
    rollback() ：在此链接上回滚事务。

####2.5、Statement类讲解
 　　Jdbc程序中的Statement对象用于向数据库发送SQL语句， Statement对象常用方法：
     executeQuery(String sql) ：用于向数据发送查询语句。
     executeUpdate(String sql)：用于向数据库发送insert、update或delete语句
     execute(String sql)：用于向数据库发送任意sql语句
     addBatch(String sql) ：把多条sql语句放到一个批处理中。
     executeBatch()：向数据库发送一批sql语句执行。
####2.6、ResultSet类讲解
    Jdbc程序中的ResultSet用于代表Sql语句的执行结果。Resultset封装执行结果时，采用的类似于表格的方式。
    ResultSet 对象维护了一个指向表格数据行的游标，初始的时候，游标在第一行之前，调用ResultSet.next() 方法，
    可以使游标指向具体的数据行，进行调用方法获取该行的数据。

　　ResultSet既然用于封装执行结果的，所以该对象提供的都是用于获取数据的get方法：
　　获取任意类型的数据
　　　　getObject(int index)
　　　　getObject(string columnName)
　　获取指定类型的数据，例如：
　　　　getString(int index)
　　　　getString(String columnName)
　　ResultSet还提供了对结果集进行滚动的方法：
    next()：移动到下一行
    Previous()：移动到前一行
    absolute(int row)：移动到指定行
    beforeFirst()：移动resultSet的最前面。
    afterLast() ：移动到resultSet的最后面。

####2.7、释放资源
     Jdbc程序运行完后，切记要释放程序在运行过程中，创建的那些与数据库进行交互的对象，这些对象通常是ResultSet, Statement和
     Connection对象，特别是Connection对象，它是非常稀有的资源，用完后必须马上释放，如果Connection不能及时、正确的关闭，
     极易导致系统宕机。Connection的使用原则是尽量晚创建，尽量早的释放。

     为确保资源释放代码能运行，资源释放代码也一定要放在finally语句中

###statement对象介绍　　
    Jdbc中的statement对象用于向数据库发送SQL语句，想完成对数据库的增删改查，只需要通过这个对象向数据库发送增删改查语句即可。
    Statement对象的executeUpdate方法，用于向数据库发送增、删、改的sql语句，executeUpdate执行完后，将会返回一个整数
    (即增删改语句导致了数据库几行数据发生了变化)。
    Statement.executeQuery方法用于向数据库发送查询语句，executeQuery方法返回代表查询结果的ResultSet对象。
###PreparedStatement对象介绍
   PreperedStatement是Statement的子类，它的实例对象可以通过调用Connection.preparedStatement()方法获得，相对于Statement对象而言：
   PreperedStatement可以避免SQL注入的问题。
   Statement会使数据库频繁编译SQL，可能造成数据库缓冲区溢出。PreparedStatement可对SQL进行预编译，从而提高数据库的执行效率。
   并且PreperedStatement对于sql中的参数，允许使用占位符的形式进行替换，简化sql语句的编写。

###基本概念
   大数据也称之为LOB(Large Objects)，LOB又分为：clob和blob，clob用于存储大文本，blob用于存储二进制数据，例如图像、声音、二进制文等。
   在实际开发中，有时是需要用程序把大文本或二进制数据直接保存到数据库中进行储存的。
   对MySQL而言只有blob，而没有clob，mysql存储大文本采用的是Text，Text和blob分别又分为：
　　TINYTEXT、TEXT、MEDIUMTEXT和LONGTEXT
　　TINYBLOB、BLOB、MEDIUMBLOB和LONGBLOB

###使用JDBC处理MySQL的大文本
　　对于MySQL中的Text类型，可调用如下方法设置
    PreparedStatement.setCharacterStream(index, reader, length);//注意length长度须设置，并且设置为int型,
　　对MySQL中的Text类型，可调用如下方法获取
    reader = resultSet.getCharacterStream(String columnLabel);
    string s = resultSet.getString(String columnLabel);

###使用JDBC处理MySQL的二进制数据
   对于MySQL中的BLOB类型，可调用如下方法设置：
   PreparedStatement. setBinaryStream(i, inputStream, length);
   对MySQL中的BLOB类型，可调用如下方法获取：
   InputStream in  = resultSet.getBinaryStream(String columnLabel);
   InputStream in  = resultSet.getBlob(String columnLabel).getBinaryStream();

###Oracle中大数据处理
   在Oracle中，LOB（Large Object，大型对象）类型的字段现在用得越来越多了。因为这种类型的字段，容量大（最多能容纳4GB的数据），
   且一个表中可以有多个这种类型的字段，很灵活，适用于数据 量非常大的业务领域（如图象、档案等）。
   LOB类型分为BLOB和CLOB两种：BLOB即二进制大型对象（Binary Large Object），适用于存贮非文本的字节流数据（如程序、图象、影音等）。
   而CLOB，即字符型大型对象（Character Large Object），则与字符集相关，适于存贮文本型的数据（如历史档案、大部头著作等）。

####3.1、JDBC处理BLOB数据
    Oracle定义了一个BLOB字段用于保存二进制数据，但这个字段并不能存放真正的二进制数据，只能向这个字段存一个指针，
    然后把数据放到指针所指向的Oracle的LOB段中， LOB段是在数据库内部表的一部分。因而在操作Oracle的Blob之前，必须获得指针
    （定位器）才能进行Blob数据的读取和写入。
    如何获得表中的Blob指针呢？ 可以先使用insert语句向表中插入一个空的blob（调用oracle的函数empty_blob()），
    这将创建一个blob的指针，然后再把这个empty的blob的指针查询出来，这样就可得到BLOB对象，从而读写blob数据了。

    1、插入空blob：insert into testblob(id,image) values(?,empty_blob())
    2、获得blob的cursor：
        select image from testblob where id=? for update 注意: 必 须加for update锁定该行，直至该行被修改完毕，保证不产生并发冲突。
        Blob b = rs.getBlob("image");
    3、利用 io和获取到的cursor往数据库读写数据
    注意：以上操作需开启事务。
###使用JDBC处理Oracle大数据总结
   通过JDBC操纵Oracle数据库的LOB字段，不外乎插入、修改、替换、读取四种方式，掌握起来并不难。观察上述程序对LOB类型字段的存取，
   我们可以看出，较之其它类型字段，有下面几个显著不同的特点：
   1、必须取消自动提交。
   　　存取操作开始前，必须用setAutoCommit(false)取消自动提交。其它类型字段则无此特殊要求。这是因为存取LOB类型字段时，
   通常要进行多次操作可以完成。不这样的话，Oracle将抛出“读取违反顺序”的错误。
   2、插入方式不同。
        LOB数据不能象其它类型数据一样直接插入（INSERT）。插入前必须先插入一个空的LOB对象，CLOB类型 的空对象为EMPTY_CLOB()，
        BLOB类型的空对象为EMPTY_BLOB()。之后通过SELECT命令查询得到先前插入的记录并锁定，继而将 空对象修改为所要插入的LOB对象。
   3、修改方式不同。
        其它类型的字段修改时，用UPDATE … SET…命令即可。而LOB类型字段，则只能用SELECT … FOR UPDATE命令将记录查询出来并锁定，
        然后才能修改。且修改也有两种改法：一是在原数据基础上的修改（即覆盖式修改），执行SELECT … FOR UPDATE后再改数据；
        二是替换（先将原数据清掉，再修改），先执行UPDATE命令将LOB字段之值设为空的LOB对象，然后进行第一种改法。建议使 用替换的方法，
        以实现与其它字段UPDATE操作后一样的效果。
   4、存取时应使用由数据库JDBC驱动程序提供的LOB操作类。
        对于Oracle数据库，应使用oracle.sql.CLOB和oracle.sql.BLOB。不使用由数据库JDBC驱动程序提供的LOB类时，程序运行时易
        于出现“抽象方法调用”的错误，这是因为JDBC所定义的java.sql.Clob与 java.sql.Blob接口，其中的一些方法并未在数据库
        厂家提供的驱动程序中真正实现。
   5、存取手段与文件操作相仿。
        对于BLOB类型，应用InputStream/OutputStream类，此类不进行编码转换，逐个字节存取。oracle.sql.BLOB类相应提供了
        getBinaryStream()和getBinaryOutputStream()两个方法，前一个 方法用于读取Oracle的BLOB字段，后一个方法用于将数据写入Oracle的BLOB字段。
        对于CLOB类型，应用Reader/Writer类，此类进行编码转换。oracle.sql.CLOB类相应 提供了getCharacterStream()和
        getCharacterOutputStream()两个方法，前一个方法用于读取Oracle的 CLOB字段，后一个方法用于将数据写入Oracle的CLOB字段。
        需要说明的是，为了大幅提高程序执行效率，对BLOB/CLOB字段的读写操作，应该使用缓冲操作类（带 Buffered前缀），
        即：BufferedInputStream，BufferedOutputStream，BufferedReader，BufferedWriter。 例程中全部使用了缓冲操作类。

###使用Statement完成批处理
 　　1、使用Statement对象添加要批量执行SQL语句，如下：
          Statement.addBatch(sql1);
          Statement.addBatch(sql2);
          Statement.addBatch(sql3);
 　　2、执行批处理SQL语句：Statement.executeBatch();
 　　3、清除批处理命令：Statement.clearBatch();

####采用Statement.addBatch(sql)方式实现批处理的优缺点
 　　采用Statement.addBatch(sql)方式实现批处理：
 　　　　优点：可以向数据库发送多条不同的ＳＱＬ语句。
 　　　　缺点：SQL语句没有预编译。
 　　　　当向数据库发送多条语句相同，但仅参数不同的SQL语句时，需重复写上很多条SQL语句。

####采用PreparedStatement.addBatch()方式实现批处理的优缺点
 　　采用PreparedStatement.addBatch()实现批处理
 　　　　优点：发送的是预编译后的SQL语句，执行效率高。
 　　　　缺点：只能应用在SQL语句相同，但参数不同的批处理中。因此此种形式的批处理经常用于在同一个表中批量插入数据，或批量更新表的数据。

###事务的概念
    事务指逻辑上的一组操作，组成这组操作的各个单元，要不全部成功，要不全部不成功。
###MySQL数据库中操作事务命令
    开启事务(start transaction)
    提交事务(commit)
    回滚事务(rollback)
###JDBC中使用事务
    当Jdbc程序向数据库获得一个Connection对象时，默认情况下这个Connection对象会自动向数据库提交在它上面发送的SQL语句。
    若想关闭这种默认提交方式，让多条SQL在一个事务中执行，可使用下列的JDBC控制事务语句
   Connection.setAutoCommit(false);//开启事务(start transaction)
   Connection.rollback();//回滚事务(rollback)
   Connection.commit();//提交事务(commit)
####设置事务回滚点
    在开发中，有时候可能需要手动设置事务的回滚点，在JDBC中使用如下的语句设置事务回滚点
    Savepoint sp = conn.setSavepoint();
    Conn.rollback(sp);
    Conn.commit();//回滚后必须通知数据库提交事务
###事务的四大特性(ACID)
 1. 原子性（Atomicity）
    原子性是指事务是一个不可分割的工作单位，事务中的操作要么全部成功，要么全部失败。比如在同一个事务中的SQL语句，
   要么全部执行成功，要么全部执行失败
 2. 一致性（Consistency）
    官网上事务一致性的概念是：事务必须使数据库从一个一致性状态变换到另外一个一致性状态。以转账为例子，A向B转账，
    假设转账之前这两个用户的钱加起来总共是2000，那么A向B转账之后，不管这两个账户怎么转，A用户的钱和B用户的钱加起来的总额还是2000，
    这个就是事务的一致性。
 3. 隔离性（Isolation）
    事务的隔离性是多个用户并发访问数据库时，数据库为每一个用户开启的事务，不能被其他事务的操作数据所干扰，多个并发事务之间要相互隔离。
 4. 持久性（Durability）
     持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来即使数据库发生故障也不应该对其有任何影响
     事务的四大特性中最麻烦的是隔离性，下面重点介绍一下事务的隔离级别

###事务的隔离级别
    多个线程开启各自事务操作数据库中数据时，数据库系统要负责隔离操作，以保证各个线程在获取数据时的准确性。

####1. 事务不考虑隔离性可能会引发的问题
    　　如果事务不考虑隔离性，可能会引发如下问题：
    　　1、脏读
    　　　　　脏读指一个事务读取了另外一个事务未提交的数据。

    　　　　　这是非常危险的，假设Ａ向Ｂ转帐100元，对应sql语句如下所示
        　　　　　　1.update account set money=money+100 where name='B';
        　　　　　　2.update account set money=money-100  where name='A';
        　　　 当第1条sql执行完，第2条还没执行(A未提交时)，如果此时Ｂ查询自己的帐户，就会发现自己多了100元钱。
        如果A等B走后再回滚，B就会损失100元。
        　
    　　2、不可重复读

    　　不可重复读指在一个事务内读取表中的某一行数据，多次读取结果不同。

    　　例如银行想查询A帐户余额，第一次查询A帐户为200元，此时A向帐户内存了100元并提交了，银行接着又进行了一次查询，
    此时A帐户为300元了。银行两次查询不一致，可能就会很困惑，不知道哪次查询是准的。
    　　不可重复读和脏读的区别是，脏读是读取前一事务未提交的脏数据，不可重复读是重新读取了前一事务已提交的数据。
    　　很多人认为这种情况就对了，无须困惑，当然是后面的为准。我们可以考虑这样一种情况，比如银行程序需要将查询结果
    分别输出到电脑屏幕和写到文件中，结果在一个事务中针对输出的目的地，进行的两次查询不一致，导致文件和屏幕中的结果不一致，
    银行工作人员就不知道以哪个为准了。

    　　3、虚读(幻读)
    　　虚读(幻读)是指在一个事务内读取到了别的事务插入的数据，导致前后读取不一致。
    　　如丙存款100元未提交，这时银行做报表统计account表中所有用户的总额为500元，然后丙提交了，这时银行再统计发现帐户为600元了，
    造成虚读同样会使银行不知所措，到底以哪个为准。

####2.  事务隔离性的设置语句

    MySQL数据库共定义了四种隔离级别：
    Serializable(串行化)：可避免脏读、不可重复读、虚读情况的发生。
    Repeatable read(可重复读)：可避免脏读、不可重复读情况的发生。
    Read committed(读已提交)：可避免脏读情况发生。
    Read uncommitted(读未提交)：最低级别，以上情况均无法保证。

    mysql数据库查询当前事务隔离级别：select @@tx_isolation
    mysql数据库默认的事务隔离级别是：Repeatable read(可重复读)
    mysql数据库设置事务隔离级别：set transaction isolation level 隔离级别名

####3.  使用MySQL数据库演示不同隔离级别下的并发问题
    同时打开两个窗口模拟2个用户并发访问数据库
1、当把事务的隔离级别设置为read uncommitted时，会引发脏读、不可重复读和虚读
　　A窗口
　　　　set transaction isolation level  read uncommitted;--设置A用户的数据库隔离级别为Read uncommitted(读未提交)
　　　　start transaction;--开启事务
　　　　select * from account;--查询A账户中现有的钱，转到B窗口进行操作
　　　　select * from account--发现a多了100元，这时候A读到了B未提交的数据（脏读）
　　B窗口
　　　　start transaction;--开启事务
　　　　update account set money=money+100 where name='A';--不要提交，转到A窗口查询

2、当把事务的隔离级别设置为read committed时，会引发不可重复读和虚读，但避免了脏读
　　A窗口
　　　　set transaction isolation level  read committed;
　　　　start transaction;
　　　　select * from account;--发现a帐户是1000元，转到b窗口
　　　　select * from account;--发现a帐户多了100,这时候，a读到了别的事务提交的数据，
两次读取a帐户读到的是不同的结果（不可重复读）
　　B窗口
　　　　start transaction;
　　　　update account set money=money+100 where name='aaa';
　　　　commit;--转到a窗口

3、当把事务的隔离级别设置为repeatable read(mysql默认级别)时，会引发虚读，但避免了脏读、不可重复读

　　A窗口
　　　　set transaction isolation level repeatable read;
　　　　start transaction;
　　　　select * from account;--发现表有4个记录，转到b窗口
　　　　select * from account;--可能发现表有5条记录，这时候发生了a读取到另外一个事务插入的数据（虚读）
　　B窗口
　　　　start transaction;
　　　　insert into account(name,money) values('ggg',1000);
　　　　commit;--转到a窗口

4、当把事务的隔离级别设置为Serializable时，会避免所有问题

　　A窗口
　　　　set transaction isolation level Serializable;
　　　　start transaction;
　　　　select * from account;--转到b窗口

　　B窗口
　　　　start transaction;
　　　　insert into account(name,money) values('ggg',1000);--发现不能插入，只能等待a结束事务才能插入

###数据库连接池
###应用程序直接获取数据库连接的缺点
   用户每次请求都需要向数据库获得链接，而数据库创建连接通常需要消耗相对较大的资源，创建时间也较长。假设网站一天10万访问量，
   数据库服务器就需要创建10万次连接，极大的浪费数据库的资源，并且极易造成数据库服务器内存溢出、拓机。如下图所示：
###使用数据库连接池优化程序性能
####2.1、数据库连接池的基本概念
　　数据库连接是一种关键的有限的昂贵的资源,这一点在多用户的网页应用程序中体现的尤为突出.对数据库连接的管理能显著影响到
整个应用程序的伸缩性和健壮性,影响到程序的性能指标.数据库连接池正式针对这个问题提出来的.数据库连接池负责分配,管理和释放数据库连接,
它允许应用程序重复使用一个现有的数据库连接,而不是重新建立一个。
    数据库连接池在初始化时将创建一定数量的数据库连接放到连接池中, 这些数据库连接的数量是由最小数据库连接数来设定的. 无论这些数据库
连接是否被使用,连接池都将一直保证至少拥有这么多的连接数量.连接池的最大数据库连接数量限定了这个连接池能占有的最大连接数,当应用程序
向连接池请求的连接数超过最大连接数量时,这些请求将被加入到等待队列中.

   数据库连接池的最小连接数和最大连接数的设置要考虑到以下几个因素:
最小连接数:是连接池一直保持的数据库连接,所以如果应用程序对数据库连接的使用量不大,将会有大量的数据库连接资源被浪费.
最大连接数:是连接池能申请的最大连接数,如果数据库连接请求超过次数,后面的数据库连接请求将被加入到等待队列中,这会影响以后的数据库操作
如果最小连接数与最大连接数相差很大:那么最先连接请求将会获利,之后超过最小连接数量的连接请求等价于建立一个新的数据库连接.不过,这些
大于最小连接数的数据库连接在使用完不会马上被释放,他将被放到连接池中等待重复使用或是空间超时后被释放.

####2.2、编写数据库连接池
    编写连接池需实现java.sql.DataSource接口。DataSource接口中定义了两个重载的getConnection方法：
    Connection getConnection()
    Connection getConnection(String username, String password)
    　　实现DataSource接口，并实现连接池功能的步骤：
    在DataSource构造函数中批量创建与数据库的连接，并把创建的连接加入LinkedList对象中。
    实现getConnection方法，让getConnection方法每次调用时，从LinkedList中取一个Connection返回给用户。
    当用户使用完Connection，调用Connection.close()方法时，Collection对象应保证将自己返回到LinkedList中,而不要把conn还给数据库。
    Collection保证将自己返回到LinkedList中是此处编程的难点。

###开源数据库连接池

   　　现在很多WEB服务器(Weblogic, WebSphere, Tomcat)都提供了DataSoruce的实现，即连接池的实现。通常我们把DataSource的实现，按其英文含义称之为数据源，数据源中都包含了数据库连接池的实现。
   　　也有一些开源组织提供了数据源的独立实现：

   DBCP 数据库连接池
   C3P0 数据库连接池
   　　在使用了数据库连接池之后，在项目的实际开发中就不需要编写连接数据库的代码了，直接从数据源获得数据库的连接。

####3.1、DBCP数据源
   　　DBCP 是 Apache 软件基金组织下的开源连接池实现，要使用DBCP数据源，需要应用程序应在系统中增加如下两个 jar 文件：

   Commons-dbcp.jar：连接池的实现
   Commons-pool.jar：连接池实现的依赖库
   　　Tomcat 的连接池正是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用程序独立使用。

####3.2、C3P0数据源
   　　C3P0是一个开源的JDBC连接池，它实现了数据源和JNDI绑定，支持JDBC3规范和JDBC2的标准扩展。目前使用它的开源项目有Hibernate，Spring等。
   C3P0数据源在项目开发中使用得比较多。
   　　c3p0与dbcp区别
   dbcp没有自动回收空闲连接的功能
   c3p0有自动回收空闲连接功能
####3.3、在应用程序中加入C3P0连接池
   　　1.导入相关jar包
       　　　c3p0-0.9.2-pre1.jar、mchange-commons-0.2.jar，如果操作的是Oracle数据库，
       那么还需要导入c3p0-oracle-thin-extras-0.9.2-pre1.jar
   　　2、在类目录下加入C3P0的配置文件：c3p0-config.xml

###配置Tomcat数据源
    在实际开发中，我们有时候还会使用服务器提供给我们的数据库连接池，比如我们希望Tomcat服务器在启动的时候可以帮我们创建一个数据库连接池，
    那么我们在应用程序中就不需要手动去创建数据库连接池，直接使用Tomcat服务器创建好的数据库连接池即可。要想让Tomcat服务器在
    启动的时候帮我们创建一个数据库连接池，那么需要简单配置一下Tomcat服务器

####4.1、JNDI技术简介
    　　JNDI(Java Naming and Directory Interface)，Java命名和目录接口，它对应于J2SE中的javax.naming包，
    　　这 套API的主要作用在于：它可以把Java对象放在一个容器中（JNDI容器），并为容器中的java对象取一个名称，以后程序想获得Java对象，
    只需 通过名称检索即可。其核心API为Context，它代表JNDI容器，其lookup方法为检索容器中对应名称的对象。
    　　Tomcat服务器创建的数据源是以JNDI资源的形式发布的，所以说在Tomat服务器中配置一个数据源实际上就是在配置一个JNDI资源，
    通过查看Tomcat文档，我们知道使用如下的方式配置tomcat服务器的数据源：
    <Context>
     <Resource name="jdbc/datasource" auth="Container"
                 type="javax.sql.DataSource" username="root" password="XDP"
                driverClassName="com.mysql.jdbc.Driver"
                 url="jdbc:mysql://localhost:3306/jdbcstudy"
               maxActive="8" maxIdle="4"/>
   </Context>
    服务器创建好数据源之后，我们的应用程序又该怎么样得到这个数据源呢，Tomcat服务器创建好数据源之后是以JNDI的形式绑定到一个JNDI容器中的，
    我们可以把JNDI想象成一个大大的容器，我们可以往这个容器中存放一些对象，一些资源，JNDI容器中存放的对象和资源都会有一个独一无二的名称，
    应用程序想从JNDI容器中获取资源时，只需要告诉JNDI容器要获取的资源的名称，JNDI根据名称去找到对应的资源后返回给应用程序。我们平时做
    javaEE开发时，服务器会为我们的应用程序创建很多资源，比如request对象，response对象，服务器创建的这些资源有两种方式提供给我们的应用程序使用：
    第一种是通过方法参数的形式传递进来，比如我们在Servlet中写的doPost和doGet方法中使用到的request对象和response对象就是服务器以参数的形式传递给我们的。
    第二种就是JNDI的方式，服务器把创建好的资源绑定到JNDI容器中去，应用程序想要使用资源时，就直接从JNDI容器中获取相应的资源即可。

    对于上面的name="jdbc/datasource"数据源资源，在应用程序中可以用如下的代码去获取
    Context initCtx = new InitialContext();
    Context envCtx = (Context) initCtx.lookup("java:comp/env");
    dataSource = (DataSource)envCtx.lookup("jdbc/datasource");

###元数据介绍
    元数据指的是"数据库"、"表"、"列"的定义信息。

#### 1.1、DataBaseMetaData元数据
    　　Connection.getDatabaseMetaData()获得代表DatabaseMetaData元数据的DatabaseMetaData对象。
    　　DataBaseMetaData对象的常用方法：
    getURL()：返回一个String类对象，代表数据库的URL。
    getUserName()：返回连接当前数据库管理系统的用户名。
    getDatabaseProductName()：返回数据库的产品名称。
    getDatabaseProductVersion()：返回数据库的版本号。
    getDriverName()：返回驱动驱动程序的名称。
    getDriverVersion()：返回驱动程序的版本号。
    isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作。
####1.2、ParameterMetaData元数据
    PreparedStatement.getParameterMetaData() 获得代表PreparedStatement元数据的ParameterMetaData对象。
    Select * from user where name=? And password=?
    ParameterMetaData对象的常用方法：

    getParameterCount()： 获得指定参数的个数
    getParameterType(int param)：获得指定参数的sql类型，MySQL数据库驱动不支持

####1.3、ResultSetMetaData元数据
    ResultSet. getMetaData() 获得代表ResultSet对象元数据的ResultSetMetaData对象。
    ResultSetMetaData对象的常用方法：

    getColumnCount() 返回resultset对象的列数
    getColumnName(int column) 获得指定列的名称
    getColumnTypeName(int column)获得指定列的类型


###使用元数据封装简单的JDBC框架
　　系统中所有实体对象都涉及到基本的CRUD操作
　　所有实体的CUD操作代码基本相同，仅仅发送给数据库的SQL语句不同而已，因此可以把CUD操作的所有相同代码抽取到工具类的一个update方法中，
并定义参数接收变化的SQL语句。

　　实体的R操作，除SQL语句不同之外，根据操作的实体不同，对ResultSet的映射也各不相同，因此可义一个query方法，
除以参数形式接收变化的SQL语句外，可以使用策略模式由qurey方法的调用者决定如何把ResultSet中的数据映射到实体对象中。