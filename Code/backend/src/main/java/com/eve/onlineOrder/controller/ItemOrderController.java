package com.eve.onlineOrder.controller;

import com.eve.onlineOrder.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller // @Controller 用于标注控制层组件，即 Controller 组件。
public class ItemOrderController {
    @Autowired // @Autowired 用于自动装配，将 OrderItemService 注入到 ItemOrderController 中。
    private OrderItemService orderItemService;


    @RequestMapping(value = "/order/{menuId}", method = RequestMethod.POST)
    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/order/{menuId}
    // 表示请求的 URL 为 http://localhost:8080/order/{menuId}。
    // method 属性用于指定请求的方法。例如，RequestMethod.POST 表示请求的方法为 POST。
    // RequestMethod 是一个枚举类，包含了所有的请求方法。
    // 例如，当用户点击菜单中的某个菜品时，需要将该菜品添加到购物车中。
    // 因此，需要向数据库中的 cart_item 表中插入数据。
    @ResponseStatus(value = HttpStatus.CREATED)
    // @ResponseStatus 用于指定响应的状态码。
    // value 属性用于指定响应的状态码。例如，HttpStatus.CREATED 表示响应的状态码为 201。
    //  HttpStatus 是一个枚举类，包含了所有的状态码。
    public void addMenuItemToCart(@PathVariable("menuId") int menuId) {
        // addMenuItemToCart 方法用于向数据库中插入数据。
        // 例如，当用户点击菜单中的某个菜品时，需要将该菜品添加到购物车中。
        // 因此，需要向数据库中的 cart_item 表中插入数据。
        // @PathVariable 用于获取请求 URL 中的参数。
        // 例如，/order/{menuId} 表示请求的 URL 为 http://localhost:8080/order/{menuId}。
        // 因此，需要在 @PathVariable 中指定 menuId 参数。
        orderItemService.saveOrderItem(menuId);
        // 调用 OrderItemService 中的 saveOrderItem 方法，向数据库中插入数据。
    }
}
