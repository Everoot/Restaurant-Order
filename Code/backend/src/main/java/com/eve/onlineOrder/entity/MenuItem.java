package com.eve.onlineOrder.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "menuitem")
public class MenuItem implements Serializable {
    private static final long serialVersionUID = 7551999649936522523L;

    @Id
    private int id; // 菜品id

    private String name; // 菜品名字

    private String description; // 菜品描述

    private double price; // 菜品价格

    private String imageUrl; // 菜品图片url

    @ManyToOne // @ManyToOne 用于声明多对一关系。
    @JsonIgnore // @JsonIgnore 用于忽略 JSON 序列化和反序列化过程中的属性。
    private Restaurant restaurant; // 餐厅
    // 例如，当在前端页面上点击某个餐厅，就会跳转到该餐厅的菜品页面，这时候就需要通过餐厅找到该餐厅的菜品。
    // 因此，餐厅和菜品之间就是多对一的关系。

    public int getId() { // 获取菜品id
        return id;
    }

    public void setId(int id) { // 设置菜品id
        this.id = id;
    }

    public String getName() { // 获取菜品名字
        return name;
    }

    public void setName(String name) { // 设置菜品名字
        this.name = name;
    }

    public String getDescription() { // 获取菜品描述
        return description;
    }

    public void setDescription(String description) { // 设置菜品描述
        this.description = description;
    }

    public double getPrice() { // 获取菜品价格
        return price;
    }

    public void setPrice(double price) { // 设置菜品价格
        this.price = price;
    }

    public String getImageUrl() { // 获取菜品图片url
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) { // 设置菜品图片url
        this.imageUrl = imageUrl;
    }

    public Restaurant getRestaurant() { // 获取餐厅
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) { // 设置餐厅
        this.restaurant = restaurant;
    }
}

