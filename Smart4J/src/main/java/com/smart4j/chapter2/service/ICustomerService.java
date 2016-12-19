package com.smart4j.chapter2.service;


import com.smart4j.chapter2.domain.Customer;

import java.util.List;
import java.util.Map;

/**
 * Created by yong on 2016/10/31 0031.
 * 提供客户数据服务
 */
public interface ICustomerService {

    //获取客户列表
    List<Customer> getCustomerList();

    //获取客户
    Customer getCustomer(int id);

    //创建客户
    boolean createCustomer(Map<String, Object> fieldMap);

    //更新客户
    boolean updateCustomer(int id, Map<String, Object> fieldMap);

    //删除客户
    boolean deleteCustomer(int id);
}
