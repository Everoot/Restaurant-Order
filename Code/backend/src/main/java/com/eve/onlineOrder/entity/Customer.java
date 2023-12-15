package com.eve.onlineOrder.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {
    // serialVersionUID 用来表明类的不同版本间的兼容性。
    // 2652327633296064143L 是通过序列化工具生成的。
    private static final long serialVersionUID = 2652327633296064143L;

    @Id
    private String email; // 用户名
    private String firstName;   // 名字
    private String lastName;  // 姓氏
    private String password;  // 密码
    private boolean enabled;  // 是否启用

    @OneToOne(cascade = CascadeType.ALL)     // @OneToOne 用于声明实体类属性与数据库表之间的一对一关系。
    // cascade 属性用于设置级联操作，CascadeType.ALL 表示所有级联操作。
    // 级联操作：级联操作是指对关联表的操作，会级联到关联表。
    // 例如：删除一个用户，会级联删除该用户的所有订单。
    @JoinColumn(unique = true) // @JoinColumn 用于声明实体类属性与数据库表之间的关联关系。
    // unique 属性用于设置是否为唯一约束，默认为 false。
    private Cart cart; // 购物车

    public String getEmail() { // 获取用户名
        return email;
    }

    public void setEmail(String email) { // 设置用户名
        this.email = email;
    }

    public String getFirstName() {  // 获取名字
        return firstName;
    }

    public void setFirstName(String first_name) {   // 设置名字
        this.firstName = first_name;
    }

    public String getLastName() {   // 获取姓氏
        return lastName;
    }

    public void setLastName(String last_name) {     // 设置姓氏
        this.lastName = last_name;
    }

    public String getPassword() {   // 获取密码
        return password;
    }

    public void setPassword(String password) {  // 设置密码
        this.password = password;
    }

    public boolean isEnabled() {    // 获取是否启用
    	return enabled;
    }

    public void setEnabled(boolean enabled) {   // 设置是否启用
    	this.enabled = enabled;
    }

    public Cart getCart() { // 获取购物车
        return cart;
    }

    public void setCart(Cart cart) { // 设置购物车
        this.cart = cart;
    }

}
