###Apache的DBUtils框架学习

####1、commons-dbutils简介　
   commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库，它是对JDBC的简单封装，
   学习成本极低，并且使用dbutils能极大简化jdbc编码的工作量，同时也不会影响程序的性能。
   因此dbutils成为很多不喜欢hibernate的公司的首选。
   commons-dbutilsAPI介绍：
   org.apache.commons.dbutils.QueryRunner
   org.apache.commons.dbutils.ResultSetHandler
   工具类
   org.apache.commons.dbutils.DbUtils
####2、QueryRunner类使用讲解
   该类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。
   QueryRunner类提供了两个构造方法：

    默认的构造方法
    需要一个 javax.sql.DataSource 来作参数的构造方法。
1. QueryRunner类的主要方法

　　public Object query(Connection conn, String sql, Object[] params, ResultSetHandler rsh) throws SQLException：执行一个查询操作，在这个查询中，
    对象数组中的每个元素值被用来作为查询语句的置换参数。该方法会自行处理 PreparedStatement 和 ResultSet 的创建和关闭。

　　public Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException:　几乎与第一种方法一样；
唯一的不同在于它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource) 或使用的setDataSource 方法中重新获得 Connection。

　　public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException : 执行一个不需要置换参数的查询操作。

　　public int update(Connection conn, String sql, Object[] params) throws SQLException:用来执行一个更新（插入、更新或删除）操作。

　　public int update(Connection conn, String sql) throws SQLException：用来执行一个不需要置换参数的更新操作。


####3、ResultSetHandler接口使用讲解
     该接口用于处理java.sql.ResultSet，将数据按要求转换为另一种形式。
     ResultSetHandler接口提供了一个单独的方法：Object handle (java.sql.ResultSet .rs)

1.  ResultSetHandler接口的实现类
     ArrayHandler：把结果集中的第一行数据转成对象数组。
     ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
     BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
     BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
     ColumnListHandler：将结果集中某一列的数据存放到List中。
     KeyedHandler(name)：将结果集中的每一行数据都封装到一个Map里，再把这些map再存到一个map里，其key为指定的key。
     MapHandler：将结果集中的第一行数据封装到一个Map里，key是列名，value就是对应的值。
     MapListHandler：将结果集中的每一行数据都封装到一个Map里，然后再存放到List

 ####4、DbUtils类使用讲解
     DbUtils ：提供如关闭连接、装载JDBC驱动程序等常规工作的工具类，里面的所有方法都是静态的。主要方法如下：
     public static void close(…) throws java.sql.SQLException：　DbUtils类提供了三个重载的关闭方法。这些方法检查所提供的参数
     是不是NULL，如果不是的话，它们就关闭Connection、Statement和ResultSet。
    public static void closeQuietly(…): 这一类方法不仅能在Connection、Statement和ResultSet为NULL情况下避免关闭，
    还能隐藏一些在程序中抛出的SQLException。
    public static void commitAndCloseQuietly(Connection conn)： 用来提交连接，然后关闭连接，并且在关闭连接时不抛出SQL异常。
    public static boolean loadDriver(java.lang.String driverClassName)：这一方装载并注册JDBC驱动程序，如果成功就返回true。
    使用该方法，你不需要捕捉这个异常ClassNotFoundException。

###JDBC开发中的事务处理
   在开发中，对数据库的多个表或者对一个表中的多条数据执行更新操作时要保证对多个更新操作要么同时成功，要么都不成功，
   这就涉及到对多个更新操作的事务管理问题了。比如银行业务中的转账问题，A用户向B用户转账100元，假设A用户和B用户的钱都
   存储在Account表，那么A用户向B用户转账时就涉及到同时更新Account表中的A用户的钱和B用户的钱，用SQL来表示就是：

1. 在数据访问层(Dao)中处理事务
     对于这样的同时更新一个表中的多条数据的操作，那么必须保证要么同时成功，要么都不成功，所以需要保证这两个update操作在
     同一个事务中进行。在开发中，我们可能会在AccountDao写一个转账处理方法，上面AccountDao的这个transfer方法可以处理转账业务，
     并且保证了在同一个事务中进行，但是AccountDao的这个transfer方法是处理两个用户之间的转账业务的，已经涉及到具体的业务操作，
     应该在业务层中做，不应该出现在DAO层的，在开发中，DAO层的职责应该只涉及到基本的CRUD，不涉及具体的业务操作，
     所以在开发中DAO层出现这样的业务处理方法是一种不好的设计。
2. 在业务层(BusinessService)处理事务
   由于上述AccountDao存在具体的业务处理方法，导致AccountDao的职责不够单一，下面我们对AccountDao进行改造，让AccountDao的职责
   只是做CRUD操作，将事务的处理挪到业务层(BusinessService)，程序经过这样改造之后就比刚才好多了，AccountDao只负责CRUD，
   里面没有具体的业务处理方法了，职责就单一了，而AccountService则负责具体的业务逻辑和事务的处理，需要操作数据库时，
   就调用AccountDao层提供的CRUD方法操作数据库。
3. 使用ThreadLocal进行更加优雅的事务处理
   上面的在businessService层这种处理事务的方式依然不够优雅，为了能够让事务处理更加优雅，我们使用ThreadLocal类进行改造，
   ThreadLocal一个容器，向这个容器存储的对象，在当前线程范围内都可以取得出来，向ThreadLocal里面存东西就是向它里面的Map存东西的，
   然后ThreadLocal把这个Map挂到当前的线程底下，这样Map就只属于这个线程了，这样在service层对事务的处理看起来就更加优雅了。
   ThreadLocal类在开发中使用得是比较多的，程序运行中产生的数据要想在一个线程范围内共享，只需要把数据使用ThreadLocal进行存储即可。
4. ThreadLocal + Filter 处理事务
   上面介绍了JDBC开发中事务处理的3种方式，下面再介绍的一种使用ThreadLocal + Filter进行统一的事务处理，
   这种方式主要是使用过滤器进行统一的事务处理，我们在TransactionFilter中把获取到的数据库连接使用ThreadLocal绑定到当前线程之后，
   在DAO层还需要从ThreadLocal中取出数据库连接来操作数据库，因此需要编写一个ConnectionContext类来存储ThreadLocal，在DAO层想获取数据库连接时，
   就可以使用ConnectionContext.getInstance().getConnection()来获取，businessService层也不用处理事务和数据库连接问题了，
   这些统一在TransactionFilter中统一管理了，businessService层只需要专注业务逻辑的处理即可，
