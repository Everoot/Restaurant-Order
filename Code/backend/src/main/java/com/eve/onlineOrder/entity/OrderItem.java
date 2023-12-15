package com.eve.onlineOrder.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity // @Entity 用于声明一个实体类，与数据库中的表对应。
@Table(name = "orderitem") // @Table 用于声明实体类对应的表信息。
public class OrderItem implements Serializable {    // Serializable 是一个标记接口，用于标记一个类的对象可以被序列化。
    // serialVersionUID 用来表明类的不同版本间的兼容性。
    private static final long serialVersionUID = -2455760938054036364L;

    @Id // @Id 用于声明一个实体类的属性映射为数据库的主键列。
    @GeneratedValue(strategy = GenerationType.AUTO) // @GeneratedValue 用于标注主键的生成策略，通过 strategy 属性指定。
    // GenerationType.AUTO：主键由程序控制，是默认选项，不设置即可。
    private int id; // 订单项id

    private int quantity; // 订单项数量

    private double price; // 菜品价格

    @ManyToOne // @ManyToOne 用于声明多对一关系。
    private MenuItem menuItem; // 菜品

    @ManyToOne // @ManyToOne 用于声明多对一关系。
    @JsonIgnore // @JsonIgnore 用于忽略 JSON 序列化和反序列化过程中的属性。
    private Cart cart; // 购物车
    // 例如，cart 和 orderItem 之间就是多对一的关系。

    public int getId() { // 获取订单项id
        return id;
    }

    public void setId(int id) { // 设置订单项id
        this.id = id;
    }

    public int getQuantity() { // 获取订单项数量
        return quantity;
    }

    public void setQuantity(int quantity) { // 设置订单项数量
        this.quantity = quantity;
    }

    public double getPrice() { // 获取菜品价格
        return price;
    }

    public void setPrice(double price) { // 设置菜品价格
        this.price = price;
    }

    public MenuItem getMenuItem() { // 获取菜品
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) { // 设置菜品
        this.menuItem = menuItem;
    }

    public Cart getCart() { // 获取购物车
        return cart;
    }

    public void setCart(Cart cart) { // 设置购物车
        this.cart = cart;
    }
}

