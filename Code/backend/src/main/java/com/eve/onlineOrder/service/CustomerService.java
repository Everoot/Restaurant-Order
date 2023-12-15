package com.eve.onlineOrder.service;

import com.eve.onlineOrder.dao.CustomerDao;
import com.eve.onlineOrder.entity.Cart;
import com.eve.onlineOrder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // @Service 用于标注业务层组件，即 Service 组件。
// Service 层用于实现业务逻辑，例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
// 因此，需要在 Service 层中实现向数据库中插入数据的业务逻辑。
public class CustomerService {
    @Autowired // @Autowired 用于自动装配，将 CustomerDao 注入到 CustomerService 中。
    // CustomerDao 用于与数据库进行交互，包括增删改查等操作。
    // CustomerDao 是 data access object 的缩写。
    private CustomerDao customerDao;

    // 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
    // 因此，需要在 Service 层中实现向数据库中插入数据的业务逻辑。
    public void signUp(Customer customer){
        Cart cart = new Cart(); // 创建 Cart 对象。
        // Cart 对象用于向数据库中的 cart 表中插入数据。
        customer.setCart(cart); // 设置 cart 对象的属性。

        customer.setEnabled(true); // 设置 customer 对象的属性。

        // 调用 CustomerDao 中的 signUp 方法，将 Customer 对象插入到数据库中。
        // Customer 对象包含了用户注册时填写的信息，例如，用户名、密码、地址等。
        customerDao.signUp(customer);// 调用 CustomerDao 中的 signUp 方法，将 Customer 对象插入到数据库中。
    }

    public Customer getCustomer(String email){ // 通过用户名获取用户信息。
        return customerDao.getCustomer(email);// 调用 CustomerDao 中的 getCustomer 方法，通过用户名获取用户信息。
    }

}
