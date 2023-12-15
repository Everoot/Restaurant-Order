package com.eve.onlineOrder.controller;


import com.eve.onlineOrder.entity.MenuItem;
import com.eve.onlineOrder.entity.Restaurant;
import com.eve.onlineOrder.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

// @Controller 用于标注控制层组件，即 Controller 组件。
// Controller 层用于接收请求，调用 Service 层的方法，将数据返回给前端页面。
@Controller
public class MenuInfoController {
    @Autowired // @Autowired 用于自动装配，将 MenuInfoService 注入到 MenuInfoController 中。
    private MenuInfoService menuInfoService;


    @RequestMapping(value="/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/restaurant/{restaurantId}/menu
    // 表示请求的 URL 为 http://localhost:8080/restaurant/{restaurantId}/menu。
    // method 属性用于指定请求的方法。例如，RequestMethod.GET 表示请求的方法为 GET。
    // RequestMethod 是一个枚举类，包含了所有的请求方法。
    @ResponseBody
    // @ResponseBody 用于将返回的数据放到响应体中。
    // 例如，当用户访问 http://localhost:8080/restaurant/{restaurantId}/menu 时，需要将菜单信息返回给前端页面。
    // 因此，需要将菜单信息放到响应体中，然后返回给前端页面。
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId){
        return menuInfoService.getAllMenuItem(restaurantId);
        // 调用 MenuInfoService 中的 getAllMenuItem 方法，获取所有的菜单项信息。
    }

    // @RequestMapping 用于映射请求，即指定请求的 URL。
    // value 属性用于指定请求的 URL。例如，/restaurants
    //  表示请求的 URL 为 http://localhost:8080/restaurants。
    // method 属性用于指定请求的方法。例如，RequestMethod.GET 表示请求的方法为 GET。
    @RequestMapping(value="/restaurants", method = RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants(){
        // getRestaurants 方法用于获取所有的餐馆信息
        return menuInfoService.getRestaurants();
        // 调用 MenuInfoService 中的 getRestaurants 方法，获取所有的餐馆信息。
    }
}
