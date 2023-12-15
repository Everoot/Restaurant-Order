package com.eve.onlineOrder.dao;

import com.eve.onlineOrder.entity.Cart;
import com.eve.onlineOrder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // @Repository 用于标注数据访问层组件，即 Dao 组件。
// Dao 层用于与数据库进行交互，包括增删改查等操作。
// Repository 是一个泛型注解，包含了 @Component 注解的功能。
// 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
public class CartDao {
    // 因此，需要在 Dao 层中实现向数据库中插入数据的业务逻辑。
    // 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
    @Autowired // @Autowired 用于自动装配，将 SessionFactory 注入到 Cartdao 中。
    // SessionFactory 用于创建 Session 对象。
    private SessionFactory sessionFactory; // SessionFactory 用于创建 Session 对象。

    public void removeCartItem(int orderItemId){
        // removeCartItem 方法用于删除购物车项。
        Session session = null; // Session 对象用于封装与数据库的连接。

        try {
            session = sessionFactory.openSession();
            // openSession 方法用于创建 Session 对象。
            OrderItem cartItem = session.get(OrderItem.class, orderItemId);
            // get 方法用于获取数据库中的数据。
            Cart cart = cartItem.getCart();
            // getCart 方法用于获取购物车信息。
            cart.getOrderItemList().remove(cartItem);
            // getOrderItemList 方法用于获取购物车中的所有菜品。

            session.beginTransaction();
            // beginTransaction 方法用于开启事务。
            session.delete(cartItem);
            // delete 方法用于删除数据库中的数据。
            session.getTransaction().commit();
            // commit 方法用于提交事务。
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null){
                session.getTransaction().rollback();
            }
            // rollback 方法用于回滚事务。
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public void removeAllCartItems(Cart cart){
        // removeAllCartItems 方法用于删除购物车中的所有菜品。
        for (OrderItem item : cart.getOrderItemList()){
            removeCartItem(item.getId());
        }

    }

}
