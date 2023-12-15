package com.eve.onlineOrder.service;

import com.eve.onlineOrder.dao.CartDao;
import com.eve.onlineOrder.entity.Cart;
import com.eve.onlineOrder.entity.Customer;
import com.eve.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service // @Service 用于标注业务层组件，即 Service 组件。
// Service 层用于实现业务逻辑，例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
public class CartService {
    @Autowired // @Autowired 用于自动装配，将 CustomerService 注入到 MenuInfoController 中。
    private CustomerService customerService;

    @Autowired
    private CartDao cartDao;

    public Cart getCart(){ // getCart 方法用于获取购物车信息。
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        // Authentication 对象用于封装认证信息。
        // 例如，当用户登录时，需要向数据库中的 customer 表和 authorities 表中插入数据。
        String username = loggedInUser.getName();
        // getName 方法用于获取用户名。
        // 例如，当用户登录时，需要获取用户名。
        Customer customer = customerService.getCustomer(username);
        // 调用 CustomerService 中的 getCustomer 方法，获取用户信息。
        // 例如，当用户登录时，需要获取用户信息。


        if (customer != null){
            // 如果用户信息不为空，则获取购物车信息。
            Cart cart = customer.getCart();
            // Cart 对象用于封装购物车信息。
            double totalPrice = 0;
            // totalPrice 用于封装购物车中所有菜品的总价格。
            for (OrderItem item : cart.getOrderItemList()){
                // 遍历购物车中的所有菜品。
                totalPrice += item.getPrice() * item.getQuantity();
                // 计算购物车中所有菜品的总价格。
            }

            cart.setTotalPrice(totalPrice);
            // setTotalPrice 方法用于设置购物车中所有菜品的总价格。
            return cart; // 返回购物车信息。
        }
        return new Cart(); // 如果用户信息为空，则返回一个空的购物车信息。

    }

    public void cleanCart(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        Customer customer = customerService.getCustomer(username);
        if (customer != null){
            cartDao.removeAllCartItems(customer.getCart());
        }

    }
}
