package com.eve.onlineOrder.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity // @Entity 用于声明一个实体类，与数据库中的表对应。
@Table(name = "cart") // @Table 用于声明实体类对应的表信息。
public class Cart implements Serializable { // Serializable 是一个标记接口，用于标记一个类的对象可以被序列化。
    // serialVersionUID 用来表明类的不同版本间的兼容性。
    private static final long serialVersionUID = 8436097833452420298L;

    @Id // @Id 用于声明一个实体类的属性映射为数据库的主键列。
    @GeneratedValue(strategy= GenerationType.AUTO)
    // @GeneratedValue 用于标注主键的生成策略，通过 strategy 属性指定。
// GenerationType.AUTO：主键由程序控制，是默认选项，不设置即可。
    private int id; // 购物车id

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @OneToMany 用于声明一对多关系。
    // mappedBy 属性用于声明关系的维护端，即表中包含外键的一方。cart 是维护端，因为 orderItem 表中包含外键 cart_id。
    // cascade 属性用于指定级联操作。cascade = CascadeType.ALL 表示级联所有操作。
    // fetch 属性用于指定是否采用延迟加载。 fetch = FetchType.EAGER 表示采用立即加载。
    private List<OrderItem> orderItemList; // 订单项列表
    // 例如, cart 和 orderItem 之间就是一对多的关系。

    private double totalPrice; // 菜品价格

    public int getId() { // 获取购物车id
        return id;
    }

    public void setId(int id) { // 设置购物车id
        this.id = id;
    }

    public List<OrderItem> getOrderItemList() { // 获取订单项列表
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) { // 设置订单项列表
        this.orderItemList = orderItemList;
    }

    public double getTotalPrice() { // 获取菜品价格
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) { // 设置菜品价格
        this.totalPrice = totalPrice;
    }
}
