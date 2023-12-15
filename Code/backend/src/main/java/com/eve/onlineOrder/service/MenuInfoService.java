package com.eve.onlineOrder.service;

import com.eve.onlineOrder.dao.MenuInfoDao;
import com.eve.onlineOrder.entity.MenuItem;
import com.eve.onlineOrder.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service 用于标注业务层组件，即 Service 组件。
// Service 层用于实现业务逻辑，例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
// 因此，需要在 Service 层中实现向数据库中插入数据的业务逻辑。
// Service 是一个泛型注解，包含了 @Component 注解的功能。
@Service
public class MenuInfoService {

    @Autowired // @Autowired 用于自动装配，将 MenuInfoDao 注入到 MenuInfoService 中。
    // MenuInfoDao 用于与数据库进行交互，包括增删改查等操作。
    private MenuInfoDao menuInfoDao;

    public List<Restaurant> getRestaurants() {
        return menuInfoDao.getRestaurants(); // 调用 MenuInfoDao 中的 getRestaurants 方法，获取所有的餐馆信息。
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        return menuInfoDao.getAllMenuItem(restaurantId);
        // 调用 MenuInfoDao 中的 getAllMenuItem 方法，获取所有的菜单项信息。
    }

    public MenuItem getMenuItem(int menuItemId) {
        return menuInfoDao.getMenuItem(menuItemId);
        // 调用 MenuInfoDao 中的 getMenuItem 方法，获取菜单项信息。
        // 例如，当用户点击菜单项时，需要获取菜单项的详细信息。
        // 因此，需要在 Service 层中实现获取菜单项信息的业务逻辑。
    }
}
