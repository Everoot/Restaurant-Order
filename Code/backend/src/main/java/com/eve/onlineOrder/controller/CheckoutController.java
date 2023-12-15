package com.eve.onlineOrder.controller;

import com.eve.onlineOrder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller // @Controller 用于标注控制层组件，即 Controller 组件。
// Controller 层用于接收请求，调用 Service 层的方法，将数据返回给前端页面。
public class CheckoutController {
    @Autowired
    private CartService cartService;

    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/checkout
    // 表示请求的 URL 为 http://localhost:8080/checkout。
    @RequestMapping(value = "/checkout", method = RequestMethod.GET)
    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/checkout
    // 表示请求的 URL 为 http://localhost:8080/checkout。
    @ResponseStatus(value = HttpStatus.OK)
    // @ResponseStatus 用于指定响应的状态码。
    // value 属性用于指定响应的状态码。例如，HttpStatus.OK 表示响应的状态码为 200。
    // HttpStatus 是一个枚举类，包含了所有的状态码
    public void checkout() { // checkout 方法用于实现结账的业务逻辑
        cartService.cleanCart(); // 调用 CartService 中的 checkout 方法，实现结账的业务逻辑。
    }
}
