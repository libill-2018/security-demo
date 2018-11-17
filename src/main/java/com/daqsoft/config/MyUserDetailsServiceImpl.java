package com.daqsoft.config;

import com.daqsoft.entity.User;
import com.daqsoft.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-10-11 11:39
 * @since JDK 1.8
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public MyUserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByName(s);
        if (null != user) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getCode());
                authorities.add(authority);
            });
            return new MyUserDetails(user, authorities);
        } else {
            // 用户不存在
            throw new UsernameNotFoundException(s);
        }
    }
}
