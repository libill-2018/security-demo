package com.daqsoft.dao;

import com.daqsoft.entity.Operate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-17 10:27
 * @since JDK 1.8
 */
@Repository
public interface OperateRepository extends JpaRepository<Operate, Long> {
    Operate findByUrl(String url);
}
