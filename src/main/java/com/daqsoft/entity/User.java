package com.daqsoft.entity;

import javax.persistence.*;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-10-11 11:32
 * @since JDK 1.8
 */
@Entity
@Table(name = "SYS_USER")
@SuppressWarnings(value = {"unused"})
public class User {
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
