package com.eve.onlineOrder.dao;
// dao 层用于与数据库进行交互，包括增删改查等操作。
// dao 是 data access object 的缩写。
// 例如，CustomerDao 类用于与数据库中的 customer 表进行交互。

import com.eve.onlineOrder.entity.Authorities;
import com.eve.onlineOrder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository // @Repository 用于标注数据访问组件，即 DAO 组件。
public class CustomerDao {

    @Autowired // @Autowired 用于自动装配，将 CustomerDao 注入到 CustomerService 中。
    private SessionFactory sessionFactory; // SessionFactory 用于创建 Session 对象。
    // SessionFactory 是一个线程安全的对象，一般情况下，一个项目只需要一个 SessionFactory 对象即可。
    // SessionFactory 用于创建 Session 对象，Session 对象用于与数据库进行交互。
    // SessionFactory 通过 Hibernate 的配置文件创建，配置文件中包含数据库的连接信息。
    // 例如，src/main/resources/hibernate.cfg.xml 中包含数据库的连接信息。
    // session 是一个线程不安全的对象，因此，一般情况下，一个线程只创建一个 session 对象。
    // 例如，当用户注册时，需要向数据库中的 customer 表和 authorities 表中插入数据。
    // 因此，需要创建一个 Session 对象，然后通过 Session 对象向数据库中插入数据
    public void signUp(Customer customer){
        Authorities authorities = new Authorities(); // 创建 Authorities 对象。
        // Authorities 对象用于向数据库中的 authorities 表中插入数据。
        authorities.setAuthorities("ROLE_USER");// 设置 authorities 对象的属性。
        authorities.setEmail(customer.getEmail()); // 设置 authorities 对象的属性。

        Session session = null; // 创建 Session 对象。
        try {
            session = sessionFactory.openSession(); // 通过 SessionFactory 创建 Session 对象。
            session.beginTransaction(); // 开启事务。
            session.save(authorities); // 保存 authorities 对象。
            session.save(customer); // 保存 customer 对象。
            session.getTransaction().commit(); // 提交事务。
        } catch (Exception e) { // 捕获异常。
            e.printStackTrace(); // 打印异常信息。
            if (session != null){ // 如果 session 不为空。
                session.getTransaction().rollback(); // 回滚事务。
            }
        } finally { // 最终执行。
            if (session != null) { // 如果 session 不为空。
                session.close(); // 关闭 session。
            }
        }
    }

    public Customer getCustomer(String email){ // 通过用户名获取用户信息。
        Customer customer = null; // 创建 Customer 对象。
        try (Session session = sessionFactory.openSession()) { // 通过 SessionFactory 创建 Session 对象。
            customer = session.get(Customer.class, email); // 通过 Session 对象获取 Customer 对象。
        } catch (Exception e) { // 捕获异常。
            e.printStackTrace(); // 打印异常信息。
        }
        return customer; // 返回 Customer 对象。
    }
}
