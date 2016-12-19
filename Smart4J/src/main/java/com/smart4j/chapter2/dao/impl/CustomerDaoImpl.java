package com.smart4j.chapter2.dao.impl;


import com.smart4j.chapter2.dao.ICustomerDao;
import com.smart4j.chapter2.domain.Customer;
import com.smart4j.chapter2.util.DatabaseHelper;
import com.smart4j.chapter2.web.filter.ConnectionContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class CustomerDaoImpl implements ICustomerDao {


    public List<Customer> getCustomerList() {
        String sql = "select * from " + DatabaseHelper.getTableName(Customer.class);
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

    public Customer getCustomer(int id) {
        String sql = "select * from " + DatabaseHelper.getTableName(Customer.class) + " where id=?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }

    public boolean updateCustomer(int id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    public boolean deleteCustomer(int id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
