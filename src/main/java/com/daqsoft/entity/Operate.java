package com.daqsoft.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * 操作实体
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-15 17:20
 * @since JDK 1.8
 */
@Entity
@Table(name = "SYS_OPERATE")
@SuppressWarnings(value = {"unused"})
public class Operate {
    /**
     * id
     */
    private Long id;
    /**
     * 操作名称
     */
    private String name;
    /**
     * 操作对应url
     */
    private String url;
    /**
     * 操作对应角色
     */
    private Set<Role> roles;

    public Operate() {
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

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "SYS_ROLE_OPERATE",
            joinColumns = {@JoinColumn(name = "OPERATE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")}
    )
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operate operate = (Operate) o;
        return Objects.equals(id, operate.id) &&
                Objects.equals(name, operate.name) &&
                Objects.equals(url, operate.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, url);
    }
}
