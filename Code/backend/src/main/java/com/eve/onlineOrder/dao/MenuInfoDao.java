package com.eve.onlineOrder.dao;
// dao 层用于与数据库进行交互，包括增删改查等操作。
// 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
// 因此，需要在 dao 层中实现向数据库中插入数据的业务逻辑。
import com.eve.onlineOrder.entity.MenuItem;
import com.eve.onlineOrder.entity.Restaurant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository // @Repository 用于标注数据访问层组件，即 Dao 组件。
// Dao 层用于与数据库进行交互，包括增删改查等操作。
// 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
// 因此，需要在 Dao 层中实现向数据库中插入数据的业务逻辑。
// Repository 是一个泛型注解，包含了 @Component 注解的功能。
public class MenuInfoDao {
    // @Autowired 用于自动装配，将 SessionFactory 注入到 MenuInfoDao 中。
    @Autowired
    private SessionFactory sessionFactory;
    // SessionFactory 用于创建 Session 对象.
    // Session 对象用于与数据库进行交互，包括增删改查等操作。
    // SessionFactory 是一个接口，包含了创建 Session 对象的方法。
    // SessionFactory 接口的实现类是 org.hibernate.internal.SessionFactoryImpl
    public List<Restaurant> getRestaurants() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            // CriteriaBuilder 用于创建 CriteriaQuery 对象。
            CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class);
            // CriteriaQuery 用于创建 Criteria 对象。
            criteria.from(Restaurant.class);
            // from 方法用于指定查询的实体类。
            return session.createQuery(criteria).getResultList();
            // getResultList 方法用于获取查询结果。
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ArrayList<>();

    }

    public List<MenuItem> getAllMenuItem(int restaurantId){
        try(Session session = sessionFactory.openSession()){
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);
            if (restaurant != null){
                return restaurant.getMenuItemList();
                // getMenuItemList 方法用于获取菜单项列表。
            }
        } catch (Exception e) {
            e.printStackTrace();
            // printStackTrace 方法用于打印异常信息。
        }
        return new ArrayList<>();
    }

    public MenuItem getMenuItem(int menuItemId){
        try(Session session = sessionFactory.openSession()){
            return session.get(MenuItem.class, menuItemId);
            // get 方法用于获取 MenuItem 对象。
        } catch (Exception e) {
            e.printStackTrace();
            // printStackTrace 方法用于打印异常信息。
        }
        return null;
        // 如果获取 MenuItem 对象失败，则返回 null。
    }
}
