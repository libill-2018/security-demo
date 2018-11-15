package com.daqsoft.service.impl;

import com.daqsoft.dao.UserRepository;
import com.daqsoft.entity.User;
import com.daqsoft.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-10-11 13:55
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }
}
