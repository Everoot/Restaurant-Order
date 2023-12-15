package com.eve.onlineOrder.controller;

import com.eve.onlineOrder.entity.Cart;
import com.eve.onlineOrder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // @Controller 用于标注控制层组件，即 Controller 组件。
// Controller 层用于接收请求，调用 Service 层的方法，将数据返回给前端页面。
// 例如，当用户访问 http://localhost:8080/cart 时，需要将购物车信息返回给前端页面。
//  因此，需要在 Controller 层中实现将购物车信息返回给前端页面的业务逻辑。
public class CartController {
    @Autowired
    private CartService cartService;
    // @Autowired 用于自动装配，将 CartService 注入到 CartController 中。


    @RequestMapping(value="/cart", method = RequestMethod.GET)
    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/cart
    // 表示请求的 URL 为 http://localhost:8080/cart。
    // method 属性用于指定请求的方法。例如，RequestMethod.GET 表示请求的方法为 GET。
    // RequestMethod 是一个枚举类，包含了所有的请求方法。
    @ResponseBody // @ResponseBody 用于将返回的数据放到响应体中。
    // 例如，当用户访问 http://localhost:8080/cart 时，需要将购物车信息返回给前端页面。
    // 因此，需要将购物车信息放到响应体中，然后返回给前端页面。
    public Cart getCart() {
        return cartService.getCart(); // 调用 CartService 中的 getCart 方法，获取购物车信息。
    }


}
