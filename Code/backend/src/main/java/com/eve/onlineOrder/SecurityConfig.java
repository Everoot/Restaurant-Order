package com.eve.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

// @EnableWebSecurity 用于启用 Spring Security。
// Spring Security 是一个安全框架，用于实现认证和授权。
// 认证用于验证用户的身份，例如，用户登录时，需要验证用户的身份。
// 授权用于控制用户对资源的访问权限，例如，当用户访问 http://localhost:8080/checkout 时，需要验证用户是否具有访问权限。
// 因此，需要在 SecurityConfig 类中实现认证和授权的业务逻辑。

//https://docs.spring.io/spring-security/site/docs/5.5.5/reference/html5/

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurerAdapter 是一个抽象类，用于实现认证和授权的业务逻辑。
    // 因此，需要继承 WebSecurityConfigurerAdapter 类，并实现 configure 方法。
    @Autowired // @Autowired 用于自动装配，将 DataSource 注入到 SecurityConfig 中。
    private DataSource dataSource;

    @Override // @Override 用于表示重写父类的方法。
    protected void configure(HttpSecurity http) throws Exception{
        // configure 方法用于实现认证和授权的业务逻辑。
        // 例如，当用户访问 http://localhost:8080/checkout 时，需要验证用户是否具有访问权限。
        // 因此，需要在 configure 方法中实现认证和授权的业务逻辑。
        http
                .csrf().disable() // csrf 用于防止跨站请求伪造。 disable() 用于禁用 csrf。
                .formLogin() // formLogin 用于实现基于表单的认证。
                .failureForwardUrl("/login?error=true"); // failureForwardUrl 用于指定认证失败时的跳转 URL。
        http
                .authorizeRequests() // authorizeRequests 用于实现基于 URL 的授权。
                .antMatchers("/order/*", "/cart","/checkout").hasAuthority("ROLE_USER")
                .anyRequest().permitAll(); // authorizeRequests 用于实现基于 URL 的授权。
        // antMatchers 用于指定 URL，hasAnyAuthority 用于指定访问该 URL 需要的权限。
        // 例如，antMatchers("/order/*", "/cart").hasAnyAuthority("ROLE_USER")
        // 表示访问 /order/* 和 /cart 时，需要具有 ROLE_USER 权限。
        // anyRequest 用于指定所有的请求，permitAll 用于指定所有的请求都允许访问。
        // 例如，anyRequest().permitAll() 表示所有的请求都允许访问。
    }

    @Override // @Override 用于表示重写父类的方法。
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        // protected 是一个访问修饰符，用于指定该方法的访问权限。
        // 它与public、private、default等访问修饰符不同，它可以修饰类的成员变量、成员方法和构造方法。
        // protected 修饰的成员变量、成员方法和构造方法可以被同一个包中的其他类访问，也可以被不同包中的子类访问。
        // 例如，protected void configure(AuthenticationManagerBuilder auth) throws Exception
        // 表示该方法可以被同一个包中的其他类访问，也可以被不同包中的子类访问。
        // configure 方法用于实现认证的业务逻辑。
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email=?");
        // jdbcAuthentication 用于实现基于 JDBC 的认证。
        // usersByUsernameQuery 用于指定查询用户的 SQL 语句。
        // authoritiesByUsernameQuery 用于指定查询用户权限的 SQL 语句。
        // 例如，usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")
        // 表示查询 email、password 和 enabled 字段，其中 email 字段用于查询用户，password 字段用于查询密码，enabled 字段用于查询用户是否可用。
        // authoritiesByUsernameQuery("SELECT email, authority FROM authorities WHERE email=?")
    }

    @SuppressWarnings("deprecation") // @SuppressWarnings 用于抑制编译器警告。
    @Bean // @Bean 用于声明当前方法的返回值是一个 Bean。
    // Bean 是 Spring 的核心，它是由 Spring IoC 容器管理的对象。
    // Spring IoC 容器是一个管理 Bean 的容器，它负责创建 Bean 对象、装配 Bean 之间的依赖关系、配置 Bean 的属性等。
    public static NoOpPasswordEncoder passwordEncoder() {
        // NoOpPasswordEncoder 用于实现密码的编码。
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        // getInstance() 用于获取 NoOpPasswordEncoder 的实例。
        // NoOpPasswordEncoder 是一个密码编码器，它不对密码进行任何编码。
        // 例如，当用户注册时，用户输入的密码为 123456，NoOpPasswordEncoder 不对密码进行任何编码，直接将密码保存到数据库中。
        // 因此，当用户登录时，用户输入的密码为 123456，NoOpPasswordEncoder 不对密码进行任何编码，直接将密码与数据库中的密码进行比较。
        // 如果两者相同，则认证成功；否则，认证失败。
    }
}
