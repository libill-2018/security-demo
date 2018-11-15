package com.daqsoft.dao;

import com.daqsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-10-11 11:37
 * @since JDK 1.8
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
