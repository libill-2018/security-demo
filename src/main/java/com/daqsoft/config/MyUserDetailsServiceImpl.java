package com.daqsoft.config;

import com.daqsoft.dao.UserRepository;
import com.daqsoft.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final UserRepository userRepository;

    public MyUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (null != user) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
            authorities.add(authority);
            return new MyUserDetails(user, authorities);
//            return new MyUserDetails(user);
            /*return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true,
                    true, true, true, authorities);*/
        } else {
            // 用户不存在
            throw new UsernameNotFoundException(s);
        }
    }
}
