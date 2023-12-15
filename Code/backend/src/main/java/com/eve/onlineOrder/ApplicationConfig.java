package com.eve.onlineOrder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration // @Configuration 用于声明当前类是一个配置类，相当于一个 Spring 配置的 xml 文件。
@EnableWebMvc  // @EnableWebMvc 用于启用 Spring MVC，相当于 <mvc:annotation-driven/>。
public class ApplicationConfig {
    @Bean(name = "sessionFactory") // @Bean 用于声明当前方法的返回值是一个 Bean。
    public LocalSessionFactoryBean sessionFactory() {
        // LocalSessionFactoryBean 用于配置 SessionFactory。
        // SessionFactory 是 Hibernate 的核心，用于产生 Session 对象。
        String PACKAGE_NAME = "com.eve.onlineOrder.entity";
        // 请在此处填入 com.eve.onlineOrder.entity 的包名。
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        // LocalSessionFactoryBean 用于配置 SessionFactory。
        sessionFactory.setDataSource(dataSource());
        // setDataSource() 用于设置数据源。
        sessionFactory.setPackagesToScan(PACKAGE_NAME);
        sessionFactory.setHibernateProperties(hibernateProperties());
        // setHibernateProperties() 用于设置 Hibernate 的属性。
        return sessionFactory;
    }

    @Bean(name="dataSource") // @Bean 用于声明当前方法的返回值是一个 Bean。
    public DataSource dataSource(){
        String RDS_ENDPOINT = "localhost"; // 请在此处填入 RDS 的 endpoint。
        String USERNAME = "root";// 请在此处填入 RDS 的用户名。
        String PASSWORD = "Eve123456";// 请在此处填入 RDS 的密码。
        DriverManagerDataSource dataSource = new DriverManagerDataSource(); // DriverManagerDataSource 用于配置数据源。
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); //   setDriverClassName() 用于设置驱动类名。
        dataSource.setUrl("jdbc:mysql://" + RDS_ENDPOINT + ":3306/onlineOrder?createDatabaseIfNotExist=true&serverTimezone=UTC");
        // setUrl() 用于设置数据库的 url。
        dataSource.setUsername(USERNAME); // setUsername() 用于设置数据库的用户名。
        dataSource.setPassword(PASSWORD); // setPassword() 用于设置数据库的密码。

        return dataSource;
    }

    private final Properties hibernateProperties(){
        Properties hibernateProperties = new Properties(); // Properties 用于配置 Hibernate 的属性。
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        // setProperty() 用于设置 Hibernate 的属性。
        // hibernate.hbm2ddl.auto 用于设置 Hibernate 在启动时是否自动创建表。
        // update 表示如果表不存在，则自动创建表；如果表存在，则不创建。
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        // hibernate.dialect 用于设置 Hibernate 方言。
        // MySQL5Dialect 表示使用 MySQL 5 的方言。
        hibernateProperties.setProperty("hibernate.show_sql", "true");
        // hibernate.show_sql 用于设置 Hibernate 是否在控制台打印 SQL 语句。
        // true 表示打印 SQL 语句。
        return hibernateProperties; // 返回 Hibernate 的属性
    }
}
