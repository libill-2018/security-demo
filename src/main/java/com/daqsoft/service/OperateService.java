package com.daqsoft.service;

import com.daqsoft.entity.Operate;

import java.util.List;

/**
 * 简单描述一下功能
 *
 * @author LaiBin
 * @version 1.0.0
 * @date 2018-11-17 10:24
 * @since JDK 1.8
 */
public interface OperateService {

    Operate findByUrl(String url);

    List<Operate> findAll();

}
