package com.eve.onlineOrder.entity;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity        // @Entity 用于声明一个实体类，与数据库中的表对应。
@Table(name = "restaurants") // @Table 用于声明实体类对应的表信息。
public class Restaurant implements Serializable {
    // serialVersionUID 用来表明类的不同版本间的兼容性。
    private static final long serialVersionUID = 1L;

    @Id // @Id 用于声明一个实体类的属性映射为数据库的主键列。
    private int id; // 餐厅id

    private String name; // 餐厅名字

    private String address; // 餐厅地址

    private String phone; // 餐厅电话

    private String imageUrl; // 餐厅图片url

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    // @OneToMany 用于声明一对多关系。
    // mappedBy 属性用于声明关系的维护端，即表中包含外键的一方。
    // cascade 属性用于指定级联操作。cascade = CascadeType.ALL 表示级联所有操作。
    // fetch 属性用于指定是否采用延迟加载。 fetch = FetchType.EAGER 表示采用立即加载。
    private List<MenuItem> menuItemList; // 菜品列表
    // 例如，当在前端页面上点击某个餐厅，就会跳转到该餐厅的菜品页面，这时候就需要通过餐厅找到该餐厅的菜品。
    // 因此，餐厅和菜品之间就是一对多的关系。
    public int getId() { // 获取餐厅id
        return id;
    }

    public void setId(int id) { // 设置餐厅id
        this.id = id;
    }

    public String getName() { // 获取餐厅名字
        return name;
    }

    public void setName(String name) { // 设置餐厅名字
        this.name = name;
    }

    public String getAddress() { // 获取餐厅地址
        return address;
    }

    public void setAddress(String address) { // 设置餐厅地址
        this.address = address;
    }

    public String getPhone() { // 获取餐厅电话
        return phone;
    }

    public void setPhone(String phone) { // 设置餐厅电话
        this.phone = phone;
    }

    public String getImageUrl() { // 获取餐厅图片url
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) { // 设置餐厅图片url
        this.imageUrl = imageUrl;
    }

    public List<MenuItem> getMenuItemList() { // 获取菜品列表
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) { // 设置菜品列表
        this.menuItemList = menuItemList;
    }
}
