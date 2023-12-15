package com.eve.onlineOrder.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity         // @Entity 用于声明一个实体类，与数据库中的表对应。
@Table(name = "authorities")  // @Table 用于声明实体类对应的表信息。
public class Authorities implements Serializable { // Serializable 是一个标记接口，用于标记一个类的对象可以被序列化。
    // serialVersionUID 用来表明类的不同版本间的兼容性。
    private static final long serialVersionUID = 8734140534986494039L;

    // @Id 用于声明一个实体类的属性映射为数据库的主键列。
    @Id
    private String email;     // 用户名

    private String authorities;  // 权限

    public String getEmail() {    // 获取用户名
        return email;
    }

    public void setEmail(String email) {  // 设置用户名
        this.email = email;
    }

    public String getAuthorities() {  // 获取权限
        return authorities;
    }

    public void setAuthorities(String authorities) {  // 设置权限
        this.authorities = authorities;
    }

}
