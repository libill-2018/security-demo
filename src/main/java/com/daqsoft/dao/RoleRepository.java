package com.daqsoft.dao;

import com.daqsoft.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-17 11:21
 * @since JDK 1.8
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
