package com.daqsoft.config;

import com.daqsoft.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-10-11 11:56
 * @since JDK 1.8
 */
public class MyUserDetails implements UserDetails {

    private User user;

    private Collection<GrantedAuthority> authorities;

    public MyUserDetails() {
    }

    public MyUserDetails(User user) {
        this.user = user;
    }

    public MyUserDetails(User user, Collection<GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
