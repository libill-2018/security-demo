package com.daqsoft.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * 角色实体
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-15 14:54
 * @since JDK 1.8
 */
@Entity
@Table(name = "SYS_ROLE")
@SuppressWarnings(value = {"unused"})
public class Role {
    /**
     * id
     */
    private Long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色代码（ROLE_XXX）
     */
    private String code;
    /**
     * 角色对应的操作
     */
    private Set<Operate> operates;
    /**
     * 角色对应用户
     */
    private Set<User> users;

    public Role() {
    }

    @Id
    @GeneratedValue
    @Column(name = "ID", unique = true, nullable = false, length = 20)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToMany(targetEntity = Operate.class, fetch = FetchType.EAGER)
    @JoinTable(name = "SYS_ROLE_OPERATE",
            joinColumns = {@JoinColumn(name = "ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "OPERATE_ID")}
    )
    public Set<Operate> getOperates() {
        return operates;
    }

    public void setOperates(Set<Operate> operates) {
        this.operates = operates;
    }

    @ManyToMany(targetEntity = User.class)
    @JoinTable(name = "SYS_USER_ROLE",
            joinColumns = {@JoinColumn(name = "ROLE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_ID")}
    )
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name) &&
                Objects.equals(code, role.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, code);
    }
}
