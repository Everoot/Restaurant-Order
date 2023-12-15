package com.eve.onlineOrder.dao;

import com.eve.onlineOrder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // @Repository 用于标注数据访问层组件，即 Dao 组件。
// Dao 层用于与数据库进行交互，包括增删改查等操作。
// 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
public class OrderItemDao {
    @Autowired // @Autowired 用于自动装配，将 SessionFactory 注入到 OrderItemDao 中。
    // SessionFactory 用于创建 Session 对象.
    private SessionFactory sessionFactory;

    public void save(OrderItem orderItem) {// save 方法用于向数据库中插入数据。
        Session session = null; // Session 对象用于与数据库进行交互，包括增删改查等操作。
        try{
            session = sessionFactory.openSession();
            // openSession 方法用于创建 Session 对象。
            session.beginTransaction();
            // beginTransaction 方法用于开启事务。
            session.save(orderItem);
            // save 方法用于向数据库中插入数据。
            session.getTransaction().commit();
            // getTransaction 方法用于获取事务。
            // commit 方法用于提交事务。
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
                // rollback 方法用于回滚事务。
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
