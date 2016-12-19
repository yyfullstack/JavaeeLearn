package com.smart4j.chapter2.service.impl;


import com.smart4j.chapter2.dao.ICustomerDao;
import com.smart4j.chapter2.dao.impl.CustomerDaoImpl;
import com.smart4j.chapter2.domain.Customer;
import com.smart4j.chapter2.service.ICustomerService;

import java.util.List;
import java.util.Map;

/**
 * Created by yong on 2016/10/31 0031.
 */
public class CustomerServiceImpl implements ICustomerService {

    private ICustomerDao dao = new CustomerDaoImpl();

    public List<Customer> getCustomerList() {
        return dao.getCustomerList();
    }

    public Customer getCustomer(int id) {
        return dao.getCustomer(id);
    }

    public boolean createCustomer(Map<String, Object> fieldMap) {
        return dao.createCustomer(fieldMap);
    }

    public boolean updateCustomer(int id, Map<String, Object> fieldMap) {
        return dao.updateCustomer(id, fieldMap);
    }

    public boolean deleteCustomer(int id) {
        return dao.deleteCustomer(id);
    }
}
