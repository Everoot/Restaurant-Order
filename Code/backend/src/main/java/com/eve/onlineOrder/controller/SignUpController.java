package com.eve.onlineOrder.controller;

import com.eve.onlineOrder.entity.Customer;
import com.eve.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller // @Controller 用于标注控制层组件，即 Controller 组件。
public class SignUpController {

    @Autowired // @Autowired 用于自动装配，将 CustomerService 注入到 SignUpController 中。
    private CustomerService customerService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/signup 表示请求的 URL 为 http://localhost:8080/signup。
    // method 属性用于指定请求的方法。例如，RequestMethod.POST 表示请求的方法为 POST。
    @ResponseStatus(value= HttpStatus.CREATED)
    // @ResponseStatus 用于指定响应的状态码。
    // value 属性用于指定响应的状态码。例如，HttpStatus.CREATED 表示响应的状态码为 201。
    // HttpStatus 是一个枚举类，包含了所有的状态码。
    public void signUp(@RequestBody Customer customer){
        // @RequestBody 用于获取请求的内容。
        // 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
        // 因此，需要获取请求的内容，即 Customer 对象。
        // Customer 对象包含了用户注册时填写的信息，例如，用户名、密码、地址等。
        customerService.signUp(customer);
        // 调用 CustomerService 中的 signUp 方法，将 Customer 对象插入到数据库中。

    }


}
