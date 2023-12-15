package com.eve.onlineOrder.service;

import com.eve.onlineOrder.dao.OrderItemDao;
import com.eve.onlineOrder.entity.Customer;
import com.eve.onlineOrder.entity.MenuItem;
import com.eve.onlineOrder.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service// @Service 用于标注业务层组件，即 Service 组件。
public class OrderItemService {
    @Autowired // @Autowired 用于自动装配，将 MenuInfoService 注入到 MenuInfoController 中。
    private MenuInfoService menuInfoService;

    @Autowired // @Autowired 用于自动装配，将 CustomerService 注入到 MenuInfoController 中。
    private CustomerService customerService;

    @Autowired // @Autowired 用于自动装配，将 OrderItemDao 注入到 MenuInfoController 中。
    private OrderItemDao orderItemDao;

    public void saveOrderItem(int menuId) {
        // saveOrderItem 方法用于向数据库中插入数据。
        final OrderItem orderItem = new OrderItem();
        // OrderItem 对象用于封装订单项信息。
        // 例如，当用户点击菜单项时，需要向数据库中的 order_item 表中插入数据。
        // 因此，需要在 OrderItem 对象中封装订单项信息，然后将 OrderItem 对象插入到数据库中。
        // OrderItem 对象中封装的订单项信息包括：菜单项信息、购物车信息、数量、价格等。
        final MenuItem menuItem = menuInfoService.getMenuItem(menuId);
        // 调用 MenuInfoService 中的 getMenuItem 方法，获取菜单项信息。
        // 例如，当用户点击菜单项时，需要获取菜单项的详细信息。
        // 因此，需要在 Service 层中实现获取菜单项信息的业务逻辑。
        // 菜单项信息包括：菜单项的名称、描述、价格等。

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        // Authentication 对象用于封装认证信息。
        // 例如，当用户登录时，需要向数据库中的 customer 表和 authorities 表中插入数据。
        // 因此，需要在 Authentication 对象中封装认证信息，然后将 Authentication 对象插入到数据库中。
        // Authentication 对象中封装的认证信息包括：用户名、密码、权限等。
        // SecurityContextHolder.getContext() 用于获取 Authentication 对象。
        String username = loggedInUser.getName();
        // getName 方法用于获取用户名。
        // 例如，当用户登录时，需要获取用户名。
        // 因此，需要在 Service 层中实现获取用户名的业务逻辑。
        // 例如，String username = loggedInUser.getName();
        // 表示获取用户名，并将用户名赋值给 username 变量。
        // 因此，需要在 Service 层中实现获取用户名的业务逻辑。
        Customer customer = customerService.getCustomer(username);
        // 调用 CustomerService 中的 getCustomer 方法，获取用户信息。
        // 例如，当用户登录时，需要获取用户信息。
        // 因此，需要在 Service 层中实现获取用户信息的业务逻辑。
        // 例如，Customer customer = customerService.getCustomer(username);
        // 表示获取用户信息，并将用户信息赋值给 customer 变量。
        // 因此，需要在 Service 层中实现获取用户信息的业务逻辑。


        orderItem.setMenuItem(menuItem);// setMenuItem 方法用于设置菜单项信息。
        orderItem.setCart(customer.getCart()); // setCart 方法用于设置购物车信息。
        orderItem.setQuantity(1);// setQuantity 方法用于设置数量。
        orderItem.setPrice(menuItem.getPrice());// setPrice 方法用于设置价格。
        orderItemDao.save(orderItem);// 调用 OrderItemDao 中的 save 方法，向数据库中插入数据。
    }
}
