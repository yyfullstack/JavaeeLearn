package com.smart4j.chapter2.web.filter;

import java.sql.Connection;

/**
 * Created by yong on 2016/11/21 0021.
 * 数据库连接上下文
 */
public class ConnectionContext {

    /**
     * 构造方法私有化，将ConnectionContext设计成单例
     */
    private ConnectionContext() {

    }

    //创建ConnectionContext实例对象
    private static ConnectionContext connectionContext = new ConnectionContext();

    //获取ConnectionContext实例对象
    public static ConnectionContext getInstance() {
        return connectionContext;
    }

    // 使用ThreadLocal存储数据库连接对象
    private ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<Connection>();

    //利用ThreadLocal把获取数据库连接对象Connection和当前线程绑定
    public void bind(Connection connection) {
        connectionThreadLocal.set(connection);
    }

    //从当前线程中取出Connection对象
    public Connection getConnection() {
        return connectionThreadLocal.get();
    }

    //解除当前线程上绑定Connection
    public void remove() {
        connectionThreadLocal.remove();
    }


}
